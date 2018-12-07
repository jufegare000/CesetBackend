/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.edu.udea.ceset.dto.Person;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.udea.ceset.dto.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class PersonDAO implements Serializable {

    public PersonDAO(EntityManagerFactory emf) {
        this.emf = emf;

    }
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Person person) {
        List<Person> lper = null;
        if (person.getUserCollection() == null) {
            person.setUserCollection(new ArrayList<User>());
        }
        EntityManager em = null;
        person.setUserCollection(null);
        try {
            em = getEntityManager();
            
            em.getTransaction().begin();
            //Transacción para traer a la persona recié c
            //lper = em.createNamedQuery("Person.findByDocument").setParameter("document", person.getDocument()).getResultList();
                
            Collection<User> attachedUserCollection = new ArrayList<User>();
            for (User userCollectionUserToAttach : person.getUserCollection()) {
                userCollectionUserToAttach = em.getReference(userCollectionUserToAttach.getClass(), userCollectionUserToAttach.getIdUser());
                attachedUserCollection.add(userCollectionUserToAttach);
            }
            person.setUserCollection(attachedUserCollection);
            em.persist(person);
            for (User userCollectionUser : person.getUserCollection()) {
                Person oldIdPersonOfUserCollectionUser = userCollectionUser.getIdPerson();
                userCollectionUser.setIdPerson(person);
                userCollectionUser = em.merge(userCollectionUser);
                if (oldIdPersonOfUserCollectionUser != null) {
                    oldIdPersonOfUserCollectionUser.getUserCollection().remove(userCollectionUser);
                    oldIdPersonOfUserCollectionUser = em.merge(oldIdPersonOfUserCollectionUser);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                
                em.close();
                
            }
        }
    }

    public void edit(Person person) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Person persistentPerson = em.find(Person.class, person.getIdPerson());
            Collection<User> userCollectionOld = persistentPerson.getUserCollection();
            Collection<User> userCollectionNew = person.getUserCollection();
            Collection<User> attachedUserCollectionNew = new ArrayList<User>();
            for (User userCollectionNewUserToAttach : userCollectionNew) {
                userCollectionNewUserToAttach = em.getReference(userCollectionNewUserToAttach.getClass(), userCollectionNewUserToAttach.getIdUser());
                attachedUserCollectionNew.add(userCollectionNewUserToAttach);
            }
            userCollectionNew = attachedUserCollectionNew;
            person.setUserCollection(userCollectionNew);
            person = em.merge(person);
            for (User userCollectionOldUser : userCollectionOld) {
                if (!userCollectionNew.contains(userCollectionOldUser)) {
                    userCollectionOldUser.setIdPerson(null);
                    userCollectionOldUser = em.merge(userCollectionOldUser);
                }
            }
            for (User userCollectionNewUser : userCollectionNew) {
                if (!userCollectionOld.contains(userCollectionNewUser)) {
                    Person oldIdPersonOfUserCollectionNewUser = userCollectionNewUser.getIdPerson();
                    userCollectionNewUser.setIdPerson(person);
                    userCollectionNewUser = em.merge(userCollectionNewUser);
                    if (oldIdPersonOfUserCollectionNewUser != null && !oldIdPersonOfUserCollectionNewUser.equals(person)) {
                        oldIdPersonOfUserCollectionNewUser.getUserCollection().remove(userCollectionNewUser);
                        oldIdPersonOfUserCollectionNewUser = em.merge(oldIdPersonOfUserCollectionNewUser);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = person.getIdPerson();
                if (findPerson(id) == null) {
                    throw new NonexistentEntityException("The person with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Person person;
            try {
                person = em.getReference(Person.class, id);
                person.getIdPerson();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The person with id " + id + " no longer exists.", enfe);
            }
            Collection<User> userCollection = person.getUserCollection();
            for (User userCollectionUser : userCollection) {
                userCollectionUser.setIdPerson(null);
                userCollectionUser = em.merge(userCollectionUser);
            }
            em.remove(person);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Person> findPersonEntities() {
        return findPersonEntities(true, -1, -1);
    }

    public List<Person> findPersonEntities(int maxResults, int firstResult) {
        return findPersonEntities(false, maxResults, firstResult);
    }

    private List<Person> findPersonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Person.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Person findPerson(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Person.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Person> rt = cq.from(Person.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
   
    
}
