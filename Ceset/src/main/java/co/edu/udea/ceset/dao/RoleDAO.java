/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.edu.udea.ceset.dto.entities.Rolec;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.udea.ceset.dto.entities.Rolebypermission;
import java.util.ArrayList;
import java.util.Collection;
import co.edu.udea.ceset.dto.entities.Rolebyuser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan
 */
public class RoleDAO implements Serializable {

    public RoleDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rolec role) {
        if (role.getRolebypermissionCollection() == null) {
            role.setRolebypermissionCollection(new ArrayList<Rolebypermission>());
        }
        if (role.getRolebyuserCollection() == null) {
            role.setRolebyuserCollection(new ArrayList<Rolebyuser>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Rolebypermission> attachedRolebypermissionCollection = new ArrayList<Rolebypermission>();
            for (Rolebypermission rolebypermissionCollectionRolebypermissionToAttach : role.getRolebypermissionCollection()) {
                rolebypermissionCollectionRolebypermissionToAttach = em.getReference(rolebypermissionCollectionRolebypermissionToAttach.getClass(), rolebypermissionCollectionRolebypermissionToAttach.getId());
                attachedRolebypermissionCollection.add(rolebypermissionCollectionRolebypermissionToAttach);
            }
            role.setRolebypermissionCollection(attachedRolebypermissionCollection);
            Collection<Rolebyuser> attachedRolebyuserCollection = new ArrayList<Rolebyuser>();
            for (Rolebyuser rolebyuserCollectionRolebyuserToAttach : role.getRolebyuserCollection()) {
                rolebyuserCollectionRolebyuserToAttach = em.getReference(rolebyuserCollectionRolebyuserToAttach.getClass(), rolebyuserCollectionRolebyuserToAttach.getId());
                attachedRolebyuserCollection.add(rolebyuserCollectionRolebyuserToAttach);
            }
            role.setRolebyuserCollection(attachedRolebyuserCollection);
            em.persist(role);
            for (Rolebypermission rolebypermissionCollectionRolebypermission : role.getRolebypermissionCollection()) {
                Rolec oldIdRoleOfRolebypermissionCollectionRolebypermission = rolebypermissionCollectionRolebypermission.getIdRole();
                rolebypermissionCollectionRolebypermission.setIdRole(role);
                rolebypermissionCollectionRolebypermission = em.merge(rolebypermissionCollectionRolebypermission);
                if (oldIdRoleOfRolebypermissionCollectionRolebypermission != null) {
                    oldIdRoleOfRolebypermissionCollectionRolebypermission.getRolebypermissionCollection().remove(rolebypermissionCollectionRolebypermission);
                    oldIdRoleOfRolebypermissionCollectionRolebypermission = em.merge(oldIdRoleOfRolebypermissionCollectionRolebypermission);
                }
            }
            for (Rolebyuser rolebyuserCollectionRolebyuser : role.getRolebyuserCollection()) {
                Rolec oldIdRoleOfRolebyuserCollectionRolebyuser = rolebyuserCollectionRolebyuser.getIdRole();
                rolebyuserCollectionRolebyuser.setIdRole(role);
                rolebyuserCollectionRolebyuser = em.merge(rolebyuserCollectionRolebyuser);
                if (oldIdRoleOfRolebyuserCollectionRolebyuser != null) {
                    oldIdRoleOfRolebyuserCollectionRolebyuser.getRolebyuserCollection().remove(rolebyuserCollectionRolebyuser);
                    oldIdRoleOfRolebyuserCollectionRolebyuser = em.merge(oldIdRoleOfRolebyuserCollectionRolebyuser);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rolec role) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rolec persistentRole = em.find(Rolec.class, role.getIdRole());
            Collection<Rolebypermission> rolebypermissionCollectionOld = persistentRole.getRolebypermissionCollection();
            Collection<Rolebypermission> rolebypermissionCollectionNew = role.getRolebypermissionCollection();
            Collection<Rolebyuser> rolebyuserCollectionOld = persistentRole.getRolebyuserCollection();
            Collection<Rolebyuser> rolebyuserCollectionNew = role.getRolebyuserCollection();
            Collection<Rolebypermission> attachedRolebypermissionCollectionNew = new ArrayList<Rolebypermission>();
            for (Rolebypermission rolebypermissionCollectionNewRolebypermissionToAttach : rolebypermissionCollectionNew) {
                rolebypermissionCollectionNewRolebypermissionToAttach = em.getReference(rolebypermissionCollectionNewRolebypermissionToAttach.getClass(), rolebypermissionCollectionNewRolebypermissionToAttach.getId());
                attachedRolebypermissionCollectionNew.add(rolebypermissionCollectionNewRolebypermissionToAttach);
            }
            rolebypermissionCollectionNew = attachedRolebypermissionCollectionNew;
            role.setRolebypermissionCollection(rolebypermissionCollectionNew);
            Collection<Rolebyuser> attachedRolebyuserCollectionNew = new ArrayList<Rolebyuser>();
            for (Rolebyuser rolebyuserCollectionNewRolebyuserToAttach : rolebyuserCollectionNew) {
                rolebyuserCollectionNewRolebyuserToAttach = em.getReference(rolebyuserCollectionNewRolebyuserToAttach.getClass(), rolebyuserCollectionNewRolebyuserToAttach.getId());
                attachedRolebyuserCollectionNew.add(rolebyuserCollectionNewRolebyuserToAttach);
            }
            rolebyuserCollectionNew = attachedRolebyuserCollectionNew;
            role.setRolebyuserCollection(rolebyuserCollectionNew);
            role = em.merge(role);
            for (Rolebypermission rolebypermissionCollectionOldRolebypermission : rolebypermissionCollectionOld) {
                if (!rolebypermissionCollectionNew.contains(rolebypermissionCollectionOldRolebypermission)) {
                    rolebypermissionCollectionOldRolebypermission.setIdRole(null);
                    rolebypermissionCollectionOldRolebypermission = em.merge(rolebypermissionCollectionOldRolebypermission);
                }
            }
            for (Rolebypermission rolebypermissionCollectionNewRolebypermission : rolebypermissionCollectionNew) {
                if (!rolebypermissionCollectionOld.contains(rolebypermissionCollectionNewRolebypermission)) {
                    Rolec oldIdRoleOfRolebypermissionCollectionNewRolebypermission = rolebypermissionCollectionNewRolebypermission.getIdRole();
                    rolebypermissionCollectionNewRolebypermission.setIdRole(role);
                    rolebypermissionCollectionNewRolebypermission = em.merge(rolebypermissionCollectionNewRolebypermission);
                    if (oldIdRoleOfRolebypermissionCollectionNewRolebypermission != null && !oldIdRoleOfRolebypermissionCollectionNewRolebypermission.equals(role)) {
                        oldIdRoleOfRolebypermissionCollectionNewRolebypermission.getRolebypermissionCollection().remove(rolebypermissionCollectionNewRolebypermission);
                        oldIdRoleOfRolebypermissionCollectionNewRolebypermission = em.merge(oldIdRoleOfRolebypermissionCollectionNewRolebypermission);
                    }
                }
            }
            for (Rolebyuser rolebyuserCollectionOldRolebyuser : rolebyuserCollectionOld) {
                if (!rolebyuserCollectionNew.contains(rolebyuserCollectionOldRolebyuser)) {
                    rolebyuserCollectionOldRolebyuser.setIdRole(null);
                    rolebyuserCollectionOldRolebyuser = em.merge(rolebyuserCollectionOldRolebyuser);
                }
            }
            for (Rolebyuser rolebyuserCollectionNewRolebyuser : rolebyuserCollectionNew) {
                if (!rolebyuserCollectionOld.contains(rolebyuserCollectionNewRolebyuser)) {
                    Rolec oldIdRoleOfRolebyuserCollectionNewRolebyuser = rolebyuserCollectionNewRolebyuser.getIdRole();
                    rolebyuserCollectionNewRolebyuser.setIdRole(role);
                    rolebyuserCollectionNewRolebyuser = em.merge(rolebyuserCollectionNewRolebyuser);
                    if (oldIdRoleOfRolebyuserCollectionNewRolebyuser != null && !oldIdRoleOfRolebyuserCollectionNewRolebyuser.equals(role)) {
                        oldIdRoleOfRolebyuserCollectionNewRolebyuser.getRolebyuserCollection().remove(rolebyuserCollectionNewRolebyuser);
                        oldIdRoleOfRolebyuserCollectionNewRolebyuser = em.merge(oldIdRoleOfRolebyuserCollectionNewRolebyuser);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = role.getIdRole();
                if (findRole(id) == null) {
                    throw new NonexistentEntityException("The role with id " + id + " no longer exists.");
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
            Rolec role;
            try {
                role = em.getReference(Rolec.class, id);
                role.getIdRole();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The role with id " + id + " no longer exists.", enfe);
            }
            Collection<Rolebypermission> rolebypermissionCollection = role.getRolebypermissionCollection();
            for (Rolebypermission rolebypermissionCollectionRolebypermission : rolebypermissionCollection) {
                rolebypermissionCollectionRolebypermission.setIdRole(null);
                rolebypermissionCollectionRolebypermission = em.merge(rolebypermissionCollectionRolebypermission);
            }
            Collection<Rolebyuser> rolebyuserCollection = role.getRolebyuserCollection();
            for (Rolebyuser rolebyuserCollectionRolebyuser : rolebyuserCollection) {
                rolebyuserCollectionRolebyuser.setIdRole(null);
                rolebyuserCollectionRolebyuser = em.merge(rolebyuserCollectionRolebyuser);
            }
            em.remove(role);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rolec> findRoleEntities() {
        return findRoleEntities(true, -1, -1);
    }

    public List<Rolec> findRoleEntities(int maxResults, int firstResult) {
        return findRoleEntities(false, maxResults, firstResult);
    }

    private List<Rolec> findRoleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rolec.class));
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

    public Rolec findRole(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rolec.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rolec> rt = cq.from(Rolec.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
