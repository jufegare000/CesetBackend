/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.edu.udea.ceset.dto.entities.Notifficationbyrole;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Juan
 */
public class NotifficationbyDAO implements Serializable {

    public NotifficationbyDAO(EntityManagerFactory emf) {
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
            em.persist(notifficationbyrole);
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
            notifficationbyrole = em.merge(notifficationbyrole);
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
