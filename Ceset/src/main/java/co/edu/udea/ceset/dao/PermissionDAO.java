/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.udea.edu.co.dto.entities.Permission;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.udea.edu.co.dto.entities.Rolebypermission;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class PermissionDAO implements Serializable {

    public PermissionDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Permission permission) {
        if (permission.getRolebypermissionCollection() == null) {
            permission.setRolebypermissionCollection(new ArrayList<Rolebypermission>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Rolebypermission> attachedRolebypermissionCollection = new ArrayList<Rolebypermission>();
            for (Rolebypermission rolebypermissionCollectionRolebypermissionToAttach : permission.getRolebypermissionCollection()) {
                rolebypermissionCollectionRolebypermissionToAttach = em.getReference(rolebypermissionCollectionRolebypermissionToAttach.getClass(), rolebypermissionCollectionRolebypermissionToAttach.getId());
                attachedRolebypermissionCollection.add(rolebypermissionCollectionRolebypermissionToAttach);
            }
            permission.setRolebypermissionCollection(attachedRolebypermissionCollection);
            em.persist(permission);
            for (Rolebypermission rolebypermissionCollectionRolebypermission : permission.getRolebypermissionCollection()) {
                Permission oldIdPermissionOfRolebypermissionCollectionRolebypermission = rolebypermissionCollectionRolebypermission.getIdPermission();
                rolebypermissionCollectionRolebypermission.setIdPermission(permission);
                rolebypermissionCollectionRolebypermission = em.merge(rolebypermissionCollectionRolebypermission);
                if (oldIdPermissionOfRolebypermissionCollectionRolebypermission != null) {
                    oldIdPermissionOfRolebypermissionCollectionRolebypermission.getRolebypermissionCollection().remove(rolebypermissionCollectionRolebypermission);
                    oldIdPermissionOfRolebypermissionCollectionRolebypermission = em.merge(oldIdPermissionOfRolebypermissionCollectionRolebypermission);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Permission permission) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Permission persistentPermission = em.find(Permission.class, permission.getIdPermission());
            Collection<Rolebypermission> rolebypermissionCollectionOld = persistentPermission.getRolebypermissionCollection();
            Collection<Rolebypermission> rolebypermissionCollectionNew = permission.getRolebypermissionCollection();
            Collection<Rolebypermission> attachedRolebypermissionCollectionNew = new ArrayList<Rolebypermission>();
            for (Rolebypermission rolebypermissionCollectionNewRolebypermissionToAttach : rolebypermissionCollectionNew) {
                rolebypermissionCollectionNewRolebypermissionToAttach = em.getReference(rolebypermissionCollectionNewRolebypermissionToAttach.getClass(), rolebypermissionCollectionNewRolebypermissionToAttach.getId());
                attachedRolebypermissionCollectionNew.add(rolebypermissionCollectionNewRolebypermissionToAttach);
            }
            rolebypermissionCollectionNew = attachedRolebypermissionCollectionNew;
            permission.setRolebypermissionCollection(rolebypermissionCollectionNew);
            permission = em.merge(permission);
            for (Rolebypermission rolebypermissionCollectionOldRolebypermission : rolebypermissionCollectionOld) {
                if (!rolebypermissionCollectionNew.contains(rolebypermissionCollectionOldRolebypermission)) {
                    rolebypermissionCollectionOldRolebypermission.setIdPermission(null);
                    rolebypermissionCollectionOldRolebypermission = em.merge(rolebypermissionCollectionOldRolebypermission);
                }
            }
            for (Rolebypermission rolebypermissionCollectionNewRolebypermission : rolebypermissionCollectionNew) {
                if (!rolebypermissionCollectionOld.contains(rolebypermissionCollectionNewRolebypermission)) {
                    Permission oldIdPermissionOfRolebypermissionCollectionNewRolebypermission = rolebypermissionCollectionNewRolebypermission.getIdPermission();
                    rolebypermissionCollectionNewRolebypermission.setIdPermission(permission);
                    rolebypermissionCollectionNewRolebypermission = em.merge(rolebypermissionCollectionNewRolebypermission);
                    if (oldIdPermissionOfRolebypermissionCollectionNewRolebypermission != null && !oldIdPermissionOfRolebypermissionCollectionNewRolebypermission.equals(permission)) {
                        oldIdPermissionOfRolebypermissionCollectionNewRolebypermission.getRolebypermissionCollection().remove(rolebypermissionCollectionNewRolebypermission);
                        oldIdPermissionOfRolebypermissionCollectionNewRolebypermission = em.merge(oldIdPermissionOfRolebypermissionCollectionNewRolebypermission);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = permission.getIdPermission();
                if (findPermission(id) == null) {
                    throw new NonexistentEntityException("The permission with id " + id + " no longer exists.");
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
            Permission permission;
            try {
                permission = em.getReference(Permission.class, id);
                permission.getIdPermission();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The permission with id " + id + " no longer exists.", enfe);
            }
            Collection<Rolebypermission> rolebypermissionCollection = permission.getRolebypermissionCollection();
            for (Rolebypermission rolebypermissionCollectionRolebypermission : rolebypermissionCollection) {
                rolebypermissionCollectionRolebypermission.setIdPermission(null);
                rolebypermissionCollectionRolebypermission = em.merge(rolebypermissionCollectionRolebypermission);
            }
            em.remove(permission);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Permission> findPermissionEntities() {
        return findPermissionEntities(true, -1, -1);
    }

    public List<Permission> findPermissionEntities(int maxResults, int firstResult) {
        return findPermissionEntities(false, maxResults, firstResult);
    }

    private List<Permission> findPermissionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Permission.class));
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

    public Permission findPermission(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Permission.class, id);
        } finally {
            em.close();
        }
    }

    public int getPermissionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Permission> rt = cq.from(Permission.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
