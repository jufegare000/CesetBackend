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
import co.udea.edu.co.dto.entities.Notiffication;
import co.udea.edu.co.dto.entities.Notifficationbyrole;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class NotifficationbyroleDAO implements Serializable {

    public NotifficationbyroleDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Notifficationbyrole notifficationbyrole) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rolec idRole = notifficationbyrole.getIdRole();
            if (idRole != null) {
                idRole = em.getReference(idRole.getClass(), idRole.getIdRole());
                notifficationbyrole.setIdRole(idRole);
            }
            Notiffication idNotif = notifficationbyrole.getIdNotif();
            if (idNotif != null) {
                idNotif = em.getReference(idNotif.getClass(), idNotif.getIdNotif());
                notifficationbyrole.setIdNotif(idNotif);
            }
            em.persist(notifficationbyrole);
            if (idRole != null) {
                idRole.getNotifficationbyroleCollection().add(notifficationbyrole);
                idRole = em.merge(idRole);
            }
            if (idNotif != null) {
                idNotif.getNotifficationbyroleCollection().add(notifficationbyrole);
                idNotif = em.merge(idNotif);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Notifficationbyrole notifficationbyrole) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Notifficationbyrole persistentNotifficationbyrole = em.find(Notifficationbyrole.class, notifficationbyrole.getId());
            Rolec idRoleOld = persistentNotifficationbyrole.getIdRole();
            Rolec idRoleNew = notifficationbyrole.getIdRole();
            Notiffication idNotifOld = persistentNotifficationbyrole.getIdNotif();
            Notiffication idNotifNew = notifficationbyrole.getIdNotif();
            if (idRoleNew != null) {
                idRoleNew = em.getReference(idRoleNew.getClass(), idRoleNew.getIdRole());
                notifficationbyrole.setIdRole(idRoleNew);
            }
            if (idNotifNew != null) {
                idNotifNew = em.getReference(idNotifNew.getClass(), idNotifNew.getIdNotif());
                notifficationbyrole.setIdNotif(idNotifNew);
            }
            notifficationbyrole = em.merge(notifficationbyrole);
            if (idRoleOld != null && !idRoleOld.equals(idRoleNew)) {
                idRoleOld.getNotifficationbyroleCollection().remove(notifficationbyrole);
                idRoleOld = em.merge(idRoleOld);
            }
            if (idRoleNew != null && !idRoleNew.equals(idRoleOld)) {
                idRoleNew.getNotifficationbyroleCollection().add(notifficationbyrole);
                idRoleNew = em.merge(idRoleNew);
            }
            if (idNotifOld != null && !idNotifOld.equals(idNotifNew)) {
                idNotifOld.getNotifficationbyroleCollection().remove(notifficationbyrole);
                idNotifOld = em.merge(idNotifOld);
            }
            if (idNotifNew != null && !idNotifNew.equals(idNotifOld)) {
                idNotifNew.getNotifficationbyroleCollection().add(notifficationbyrole);
                idNotifNew = em.merge(idNotifNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = notifficationbyrole.getId();
                if (findNotifficationbyrole(id) == null) {
                    throw new NonexistentEntityException("The notifficationbyrole with id " + id + " no longer exists.");
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
            Notifficationbyrole notifficationbyrole;
            try {
                notifficationbyrole = em.getReference(Notifficationbyrole.class, id);
                notifficationbyrole.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notifficationbyrole with id " + id + " no longer exists.", enfe);
            }
            Rolec idRole = notifficationbyrole.getIdRole();
            if (idRole != null) {
                idRole.getNotifficationbyroleCollection().remove(notifficationbyrole);
                idRole = em.merge(idRole);
            }
            Notiffication idNotif = notifficationbyrole.getIdNotif();
            if (idNotif != null) {
                idNotif.getNotifficationbyroleCollection().remove(notifficationbyrole);
                idNotif = em.merge(idNotif);
            }
            em.remove(notifficationbyrole);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Notifficationbyrole> findNotifficationbyroleEntities() {
        return findNotifficationbyroleEntities(true, -1, -1);
    }

    public List<Notifficationbyrole> findNotifficationbyroleEntities(int maxResults, int firstResult) {
        return findNotifficationbyroleEntities(false, maxResults, firstResult);
    }

    private List<Notifficationbyrole> findNotifficationbyroleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Notifficationbyrole.class));
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

    public Notifficationbyrole findNotifficationbyrole(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Notifficationbyrole.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotifficationbyroleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Notifficationbyrole> rt = cq.from(Notifficationbyrole.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
