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
import co.edu.udea.ceset.dto.entities.Rolec;
import co.edu.udea.ceset.dto.entities.Rolebyuser;
import co.edu.udea.ceset.dto.entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan
 */
public class RolebyuserDAO implements Serializable {

    public RolebyuserDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rolebyuser rolebyuser) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rolec idRole = rolebyuser.getIdRole();
            if (idRole != null) {
                idRole = em.getReference(idRole.getClass(), idRole.getIdRole());
                rolebyuser.setIdRole(idRole);
            }
            User idUser = rolebyuser.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                rolebyuser.setIdUser(idUser);
            }
            em.persist(rolebyuser);
            if (idRole != null) {
                idRole.getRolebyuserCollection().add(rolebyuser);
                idRole = em.merge(idRole);
            }
            if (idUser != null) {
                idUser.getRolebyuserCollection().add(rolebyuser);
                idUser = em.merge(idUser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rolebyuser rolebyuser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rolebyuser persistentRolebyuser = em.find(Rolebyuser.class, rolebyuser.getId());
            Rolec idRoleOld = persistentRolebyuser.getIdRole();
            Rolec idRoleNew = rolebyuser.getIdRole();
            User idUserOld = persistentRolebyuser.getIdUser();
            User idUserNew = rolebyuser.getIdUser();
            if (idRoleNew != null) {
                idRoleNew = em.getReference(idRoleNew.getClass(), idRoleNew.getIdRole());
                rolebyuser.setIdRole(idRoleNew);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                rolebyuser.setIdUser(idUserNew);
            }
            rolebyuser = em.merge(rolebyuser);
            if (idRoleOld != null && !idRoleOld.equals(idRoleNew)) {
                idRoleOld.getRolebyuserCollection().remove(rolebyuser);
                idRoleOld = em.merge(idRoleOld);
            }
            if (idRoleNew != null && !idRoleNew.equals(idRoleOld)) {
                idRoleNew.getRolebyuserCollection().add(rolebyuser);
                idRoleNew = em.merge(idRoleNew);
            }
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getRolebyuserCollection().remove(rolebyuser);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getRolebyuserCollection().add(rolebyuser);
                idUserNew = em.merge(idUserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rolebyuser.getId();
                if (findRolebyuser(id) == null) {
                    throw new NonexistentEntityException("The rolebyuser with id " + id + " no longer exists.");
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
            Rolebyuser rolebyuser;
            try {
                rolebyuser = em.getReference(Rolebyuser.class, id);
                rolebyuser.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolebyuser with id " + id + " no longer exists.", enfe);
            }
            Rolec idRole = rolebyuser.getIdRole();
            if (idRole != null) {
                idRole.getRolebyuserCollection().remove(rolebyuser);
                idRole = em.merge(idRole);
            }
            User idUser = rolebyuser.getIdUser();
            if (idUser != null) {
                idUser.getRolebyuserCollection().remove(rolebyuser);
                idUser = em.merge(idUser);
            }
            em.remove(rolebyuser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rolebyuser> findRolebyuserEntities() {
        return findRolebyuserEntities(true, -1, -1);
    }

    public List<Rolebyuser> findRolebyuserEntities(int maxResults, int firstResult) {
        return findRolebyuserEntities(false, maxResults, firstResult);
    }

    private List<Rolebyuser> findRolebyuserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rolebyuser.class));
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

    public Rolebyuser findRolebyuser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rolebyuser.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolebyuserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rolebyuser> rt = cq.from(Rolebyuser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
