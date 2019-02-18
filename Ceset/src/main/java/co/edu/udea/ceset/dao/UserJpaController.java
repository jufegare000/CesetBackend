/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.udea.ceset.dto.Person;
import co.edu.udea.ceset.dto.Rolebyuser;
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
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getRolebyuserCollection() == null) {
            user.setRolebyuserCollection(new ArrayList<Rolebyuser>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Person idPerson = user.getIdPerson();
            if (idPerson != null) {
                idPerson = em.getReference(idPerson.getClass(), idPerson.getIdPerson());
                user.setIdPerson(idPerson);
            }
            Collection<Rolebyuser> attachedRolebyuserCollection = new ArrayList<Rolebyuser>();
            for (Rolebyuser rolebyuserCollectionRolebyuserToAttach : user.getRolebyuserCollection()) {
                rolebyuserCollectionRolebyuserToAttach = em.getReference(rolebyuserCollectionRolebyuserToAttach.getClass(), rolebyuserCollectionRolebyuserToAttach.getId());
                attachedRolebyuserCollection.add(rolebyuserCollectionRolebyuserToAttach);
            }
            user.setRolebyuserCollection(attachedRolebyuserCollection);
            em.persist(user);
            if (idPerson != null) {
                idPerson.getUserCollection().add(user);
                idPerson = em.merge(idPerson);
            }
            for (Rolebyuser rolebyuserCollectionRolebyuser : user.getRolebyuserCollection()) {
                User oldIdUserOfRolebyuserCollectionRolebyuser = rolebyuserCollectionRolebyuser.getIdUser();
                rolebyuserCollectionRolebyuser.setIdUser(user);
                rolebyuserCollectionRolebyuser = em.merge(rolebyuserCollectionRolebyuser);
                if (oldIdUserOfRolebyuserCollectionRolebyuser != null) {
                    oldIdUserOfRolebyuserCollectionRolebyuser.getRolebyuserCollection().remove(rolebyuserCollectionRolebyuser);
                    oldIdUserOfRolebyuserCollectionRolebyuser = em.merge(oldIdUserOfRolebyuserCollectionRolebyuser);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getIdUser());
            Person idPersonOld = persistentUser.getIdPerson();
            Person idPersonNew = user.getIdPerson();
            Collection<Rolebyuser> rolebyuserCollectionOld = persistentUser.getRolebyuserCollection();
            Collection<Rolebyuser> rolebyuserCollectionNew = user.getRolebyuserCollection();
            if (idPersonNew != null) {
                idPersonNew = em.getReference(idPersonNew.getClass(), idPersonNew.getIdPerson());
                user.setIdPerson(idPersonNew);
            }
            Collection<Rolebyuser> attachedRolebyuserCollectionNew = new ArrayList<Rolebyuser>();
            for (Rolebyuser rolebyuserCollectionNewRolebyuserToAttach : rolebyuserCollectionNew) {
                rolebyuserCollectionNewRolebyuserToAttach = em.getReference(rolebyuserCollectionNewRolebyuserToAttach.getClass(), rolebyuserCollectionNewRolebyuserToAttach.getId());
                attachedRolebyuserCollectionNew.add(rolebyuserCollectionNewRolebyuserToAttach);
            }
            rolebyuserCollectionNew = attachedRolebyuserCollectionNew;
            user.setRolebyuserCollection(rolebyuserCollectionNew);
            user = em.merge(user);
            if (idPersonOld != null && !idPersonOld.equals(idPersonNew)) {
                idPersonOld.getUserCollection().remove(user);
                idPersonOld = em.merge(idPersonOld);
            }
            if (idPersonNew != null && !idPersonNew.equals(idPersonOld)) {
                idPersonNew.getUserCollection().add(user);
                idPersonNew = em.merge(idPersonNew);
            }
            for (Rolebyuser rolebyuserCollectionOldRolebyuser : rolebyuserCollectionOld) {
                if (!rolebyuserCollectionNew.contains(rolebyuserCollectionOldRolebyuser)) {
                    rolebyuserCollectionOldRolebyuser.setIdUser(null);
                    rolebyuserCollectionOldRolebyuser = em.merge(rolebyuserCollectionOldRolebyuser);
                }
            }
            for (Rolebyuser rolebyuserCollectionNewRolebyuser : rolebyuserCollectionNew) {
                if (!rolebyuserCollectionOld.contains(rolebyuserCollectionNewRolebyuser)) {
                    User oldIdUserOfRolebyuserCollectionNewRolebyuser = rolebyuserCollectionNewRolebyuser.getIdUser();
                    rolebyuserCollectionNewRolebyuser.setIdUser(user);
                    rolebyuserCollectionNewRolebyuser = em.merge(rolebyuserCollectionNewRolebyuser);
                    if (oldIdUserOfRolebyuserCollectionNewRolebyuser != null && !oldIdUserOfRolebyuserCollectionNewRolebyuser.equals(user)) {
                        oldIdUserOfRolebyuserCollectionNewRolebyuser.getRolebyuserCollection().remove(rolebyuserCollectionNewRolebyuser);
                        oldIdUserOfRolebyuserCollectionNewRolebyuser = em.merge(oldIdUserOfRolebyuserCollectionNewRolebyuser);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getIdUser();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getIdUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            Person idPerson = user.getIdPerson();
            if (idPerson != null) {
                idPerson.getUserCollection().remove(user);
                idPerson = em.merge(idPerson);
            }
            Collection<Rolebyuser> rolebyuserCollection = user.getRolebyuserCollection();
            for (Rolebyuser rolebyuserCollectionRolebyuser : rolebyuserCollection) {
                rolebyuserCollectionRolebyuser.setIdUser(null);
                rolebyuserCollectionRolebyuser = em.merge(rolebyuserCollectionRolebyuser);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
