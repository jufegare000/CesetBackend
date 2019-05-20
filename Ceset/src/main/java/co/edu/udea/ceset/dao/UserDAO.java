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
import co.edu.udea.ceset.dto.entities.Person;
import co.edu.udea.ceset.dto.entities.Academicactivity;
import java.util.ArrayList;
import java.util.Collection;
import co.edu.udea.ceset.dto.entities.Rolebyuser;
import co.edu.udea.ceset.dto.entities.Portafolio;
import co.edu.udea.ceset.dto.entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan
 */
public class UserDAO implements Serializable {

    public UserDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getAcademicactivityCollection() == null) {
            user.setAcademicactivityCollection(new ArrayList<Academicactivity>());
        }
        if (user.getRolebyuserCollection() == null) {
            user.setRolebyuserCollection(new ArrayList<Rolebyuser>());
        }
        if (user.getPortafolioCollection() == null) {
            user.setPortafolioCollection(new ArrayList<Portafolio>());
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
            Collection<Academicactivity> attachedAcademicactivityCollection = new ArrayList<Academicactivity>();
            for (Academicactivity academicactivityCollectionAcademicactivityToAttach : user.getAcademicactivityCollection()) {
                academicactivityCollectionAcademicactivityToAttach = em.getReference(academicactivityCollectionAcademicactivityToAttach.getClass(), academicactivityCollectionAcademicactivityToAttach.getIdAcad());
                attachedAcademicactivityCollection.add(academicactivityCollectionAcademicactivityToAttach);
            }
            user.setAcademicactivityCollection(attachedAcademicactivityCollection);
            Collection<Rolebyuser> attachedRolebyuserCollection = new ArrayList<Rolebyuser>();
            for (Rolebyuser rolebyuserCollectionRolebyuserToAttach : user.getRolebyuserCollection()) {
                rolebyuserCollectionRolebyuserToAttach = em.getReference(rolebyuserCollectionRolebyuserToAttach.getClass(), rolebyuserCollectionRolebyuserToAttach.getId());
                attachedRolebyuserCollection.add(rolebyuserCollectionRolebyuserToAttach);
            }
            user.setRolebyuserCollection(attachedRolebyuserCollection);
            Collection<Portafolio> attachedPortafolioCollection = new ArrayList<Portafolio>();
            for (Portafolio portafolioCollectionPortafolioToAttach : user.getPortafolioCollection()) {
                portafolioCollectionPortafolioToAttach = em.getReference(portafolioCollectionPortafolioToAttach.getClass(), portafolioCollectionPortafolioToAttach.getId());
                attachedPortafolioCollection.add(portafolioCollectionPortafolioToAttach);
            }
            user.setPortafolioCollection(attachedPortafolioCollection);
            em.persist(user);
            if (idPerson != null) {
                idPerson.getUserCollection().add(user);
                idPerson = em.merge(idPerson);
            }
            for (Academicactivity academicactivityCollectionAcademicactivity : user.getAcademicactivityCollection()) {
                User oldIdUserOfAcademicactivityCollectionAcademicactivity = academicactivityCollectionAcademicactivity.getIdUser();
                academicactivityCollectionAcademicactivity.setIdUser(user);
                academicactivityCollectionAcademicactivity = em.merge(academicactivityCollectionAcademicactivity);
                if (oldIdUserOfAcademicactivityCollectionAcademicactivity != null) {
                    oldIdUserOfAcademicactivityCollectionAcademicactivity.getAcademicactivityCollection().remove(academicactivityCollectionAcademicactivity);
                    oldIdUserOfAcademicactivityCollectionAcademicactivity = em.merge(oldIdUserOfAcademicactivityCollectionAcademicactivity);
                }
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
            for (Portafolio portafolioCollectionPortafolio : user.getPortafolioCollection()) {
                User oldIdUserOfPortafolioCollectionPortafolio = portafolioCollectionPortafolio.getIdUser();
                portafolioCollectionPortafolio.setIdUser(user);
                portafolioCollectionPortafolio = em.merge(portafolioCollectionPortafolio);
                if (oldIdUserOfPortafolioCollectionPortafolio != null) {
                    oldIdUserOfPortafolioCollectionPortafolio.getPortafolioCollection().remove(portafolioCollectionPortafolio);
                    oldIdUserOfPortafolioCollectionPortafolio = em.merge(oldIdUserOfPortafolioCollectionPortafolio);
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
            Collection<Academicactivity> academicactivityCollectionOld = persistentUser.getAcademicactivityCollection();
            Collection<Academicactivity> academicactivityCollectionNew = user.getAcademicactivityCollection();
            Collection<Rolebyuser> rolebyuserCollectionOld = persistentUser.getRolebyuserCollection();
            Collection<Rolebyuser> rolebyuserCollectionNew = user.getRolebyuserCollection();
            Collection<Portafolio> portafolioCollectionOld = persistentUser.getPortafolioCollection();
            Collection<Portafolio> portafolioCollectionNew = user.getPortafolioCollection();
            if (idPersonNew != null) {
                idPersonNew = em.getReference(idPersonNew.getClass(), idPersonNew.getIdPerson());
                user.setIdPerson(idPersonNew);
            }
            Collection<Academicactivity> attachedAcademicactivityCollectionNew = new ArrayList<Academicactivity>();
            for (Academicactivity academicactivityCollectionNewAcademicactivityToAttach : academicactivityCollectionNew) {
                academicactivityCollectionNewAcademicactivityToAttach = em.getReference(academicactivityCollectionNewAcademicactivityToAttach.getClass(), academicactivityCollectionNewAcademicactivityToAttach.getIdAcad());
                attachedAcademicactivityCollectionNew.add(academicactivityCollectionNewAcademicactivityToAttach);
            }
            academicactivityCollectionNew = attachedAcademicactivityCollectionNew;
            user.setAcademicactivityCollection(academicactivityCollectionNew);
            Collection<Rolebyuser> attachedRolebyuserCollectionNew = new ArrayList<Rolebyuser>();
            for (Rolebyuser rolebyuserCollectionNewRolebyuserToAttach : rolebyuserCollectionNew) {
                rolebyuserCollectionNewRolebyuserToAttach = em.getReference(rolebyuserCollectionNewRolebyuserToAttach.getClass(), rolebyuserCollectionNewRolebyuserToAttach.getId());
                attachedRolebyuserCollectionNew.add(rolebyuserCollectionNewRolebyuserToAttach);
            }
            rolebyuserCollectionNew = attachedRolebyuserCollectionNew;
            user.setRolebyuserCollection(rolebyuserCollectionNew);
            Collection<Portafolio> attachedPortafolioCollectionNew = new ArrayList<Portafolio>();
            for (Portafolio portafolioCollectionNewPortafolioToAttach : portafolioCollectionNew) {
                portafolioCollectionNewPortafolioToAttach = em.getReference(portafolioCollectionNewPortafolioToAttach.getClass(), portafolioCollectionNewPortafolioToAttach.getId());
                attachedPortafolioCollectionNew.add(portafolioCollectionNewPortafolioToAttach);
            }
            portafolioCollectionNew = attachedPortafolioCollectionNew;
            user.setPortafolioCollection(portafolioCollectionNew);
            user = em.merge(user);
            if (idPersonOld != null && !idPersonOld.equals(idPersonNew)) {
                idPersonOld.getUserCollection().remove(user);
                idPersonOld = em.merge(idPersonOld);
            }
            if (idPersonNew != null && !idPersonNew.equals(idPersonOld)) {
                idPersonNew.getUserCollection().add(user);
                idPersonNew = em.merge(idPersonNew);
            }
            for (Academicactivity academicactivityCollectionOldAcademicactivity : academicactivityCollectionOld) {
                if (!academicactivityCollectionNew.contains(academicactivityCollectionOldAcademicactivity)) {
                    academicactivityCollectionOldAcademicactivity.setIdUser(null);
                    academicactivityCollectionOldAcademicactivity = em.merge(academicactivityCollectionOldAcademicactivity);
                }
            }
            for (Academicactivity academicactivityCollectionNewAcademicactivity : academicactivityCollectionNew) {
                if (!academicactivityCollectionOld.contains(academicactivityCollectionNewAcademicactivity)) {
                    User oldIdUserOfAcademicactivityCollectionNewAcademicactivity = academicactivityCollectionNewAcademicactivity.getIdUser();
                    academicactivityCollectionNewAcademicactivity.setIdUser(user);
                    academicactivityCollectionNewAcademicactivity = em.merge(academicactivityCollectionNewAcademicactivity);
                    if (oldIdUserOfAcademicactivityCollectionNewAcademicactivity != null && !oldIdUserOfAcademicactivityCollectionNewAcademicactivity.equals(user)) {
                        oldIdUserOfAcademicactivityCollectionNewAcademicactivity.getAcademicactivityCollection().remove(academicactivityCollectionNewAcademicactivity);
                        oldIdUserOfAcademicactivityCollectionNewAcademicactivity = em.merge(oldIdUserOfAcademicactivityCollectionNewAcademicactivity);
                    }
                }
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
            for (Portafolio portafolioCollectionOldPortafolio : portafolioCollectionOld) {
                if (!portafolioCollectionNew.contains(portafolioCollectionOldPortafolio)) {
                    portafolioCollectionOldPortafolio.setIdUser(null);
                    portafolioCollectionOldPortafolio = em.merge(portafolioCollectionOldPortafolio);
                }
            }
            for (Portafolio portafolioCollectionNewPortafolio : portafolioCollectionNew) {
                if (!portafolioCollectionOld.contains(portafolioCollectionNewPortafolio)) {
                    User oldIdUserOfPortafolioCollectionNewPortafolio = portafolioCollectionNewPortafolio.getIdUser();
                    portafolioCollectionNewPortafolio.setIdUser(user);
                    portafolioCollectionNewPortafolio = em.merge(portafolioCollectionNewPortafolio);
                    if (oldIdUserOfPortafolioCollectionNewPortafolio != null && !oldIdUserOfPortafolioCollectionNewPortafolio.equals(user)) {
                        oldIdUserOfPortafolioCollectionNewPortafolio.getPortafolioCollection().remove(portafolioCollectionNewPortafolio);
                        oldIdUserOfPortafolioCollectionNewPortafolio = em.merge(oldIdUserOfPortafolioCollectionNewPortafolio);
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
            Collection<Academicactivity> academicactivityCollection = user.getAcademicactivityCollection();
            for (Academicactivity academicactivityCollectionAcademicactivity : academicactivityCollection) {
                academicactivityCollectionAcademicactivity.setIdUser(null);
                academicactivityCollectionAcademicactivity = em.merge(academicactivityCollectionAcademicactivity);
            }
            Collection<Rolebyuser> rolebyuserCollection = user.getRolebyuserCollection();
            for (Rolebyuser rolebyuserCollectionRolebyuser : rolebyuserCollection) {
                rolebyuserCollectionRolebyuser.setIdUser(null);
                rolebyuserCollectionRolebyuser = em.merge(rolebyuserCollectionRolebyuser);
            }
            Collection<Portafolio> portafolioCollection = user.getPortafolioCollection();
            for (Portafolio portafolioCollectionPortafolio : portafolioCollection) {
                portafolioCollectionPortafolio.setIdUser(null);
                portafolioCollectionPortafolio = em.merge(portafolioCollectionPortafolio);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> getAll() {
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

    public User autenticar(User user) {
        EntityManager em = null;
        em = getEntityManager();
        User usuario = null;
        String nameUser = user.getNameUser();
        String pass = user.getPassword();
        List<User> luser = em.createNamedQuery("User.findByNameUser")
                .setParameter("nameUser", nameUser)
                .getResultList();
        usuario = luser.get(0);
        if (usuario == null || !usuario.getPassword().equals(pass)) {
            return null;
        }

        return usuario;

    }

}
