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
import co.udea.edu.co.dto.entities.Rolec;
import co.udea.edu.co.dto.entities.Permission;
import co.udea.edu.co.dto.entities.Rolebypermission;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class RolebypermissionDAO implements Serializable {

    public RolebypermissionDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rolebypermission rolebypermission) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rolec idRole = rolebypermission.getIdRole();
            if (idRole != null) {
                idRole = em.getReference(idRole.getClass(), idRole.getIdRole());
                rolebypermission.setIdRole(idRole);
            }
            Permission idPermission = rolebypermission.getIdPermission();
            if (idPermission != null) {
                idPermission = em.getReference(idPermission.getClass(), idPermission.getIdPermission());
                rolebypermission.setIdPermission(idPermission);
            }
            em.persist(rolebypermission);
            if (idRole != null) {
                idRole.getRolebypermissionCollection().add(rolebypermission);
                idRole = em.merge(idRole);
            }
            if (idPermission != null) {
                idPermission.getRolebypermissionCollection().add(rolebypermission);
                idPermission = em.merge(idPermission);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rolebypermission rolebypermission) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rolebypermission persistentRolebypermission = em.find(Rolebypermission.class, rolebypermission.getId());
            Rolec idRoleOld = persistentRolebypermission.getIdRole();
            Rolec idRoleNew = rolebypermission.getIdRole();
            Permission idPermissionOld = persistentRolebypermission.getIdPermission();
            Permission idPermissionNew = rolebypermission.getIdPermission();
            if (idRoleNew != null) {
                idRoleNew = em.getReference(idRoleNew.getClass(), idRoleNew.getIdRole());
                rolebypermission.setIdRole(idRoleNew);
            }
            if (idPermissionNew != null) {
                idPermissionNew = em.getReference(idPermissionNew.getClass(), idPermissionNew.getIdPermission());
                rolebypermission.setIdPermission(idPermissionNew);
            }
            rolebypermission = em.merge(rolebypermission);
            if (idRoleOld != null && !idRoleOld.equals(idRoleNew)) {
                idRoleOld.getRolebypermissionCollection().remove(rolebypermission);
                idRoleOld = em.merge(idRoleOld);
            }
            if (idRoleNew != null && !idRoleNew.equals(idRoleOld)) {
                idRoleNew.getRolebypermissionCollection().add(rolebypermission);
                idRoleNew = em.merge(idRoleNew);
            }
            if (idPermissionOld != null && !idPermissionOld.equals(idPermissionNew)) {
                idPermissionOld.getRolebypermissionCollection().remove(rolebypermission);
                idPermissionOld = em.merge(idPermissionOld);
            }
            if (idPermissionNew != null && !idPermissionNew.equals(idPermissionOld)) {
                idPermissionNew.getRolebypermissionCollection().add(rolebypermission);
                idPermissionNew = em.merge(idPermissionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rolebypermission.getId();
                if (findRolebypermission(id) == null) {
                    throw new NonexistentEntityException("The rolebypermission with id " + id + " no longer exists.");
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
            Rolebypermission rolebypermission;
            try {
                rolebypermission = em.getReference(Rolebypermission.class, id);
                rolebypermission.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolebypermission with id " + id + " no longer exists.", enfe);
            }
            Rolec idRole = rolebypermission.getIdRole();
            if (idRole != null) {
                idRole.getRolebypermissionCollection().remove(rolebypermission);
                idRole = em.merge(idRole);
            }
            Permission idPermission = rolebypermission.getIdPermission();
            if (idPermission != null) {
                idPermission.getRolebypermissionCollection().remove(rolebypermission);
                idPermission = em.merge(idPermission);
            }
            em.remove(rolebypermission);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rolebypermission> findRolebypermissionEntities() {
        return findRolebypermissionEntities(true, -1, -1);
    }

    public List<Rolebypermission> findRolebypermissionEntities(int maxResults, int firstResult) {
        return findRolebypermissionEntities(false, maxResults, firstResult);
    }

    private List<Rolebypermission> findRolebypermissionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rolebypermission.class));
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

    public Rolebypermission findRolebypermission(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rolebypermission.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolebypermissionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rolebypermission> rt = cq.from(Rolebypermission.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
