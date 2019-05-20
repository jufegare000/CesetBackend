/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.edu.udea.ceset.dto.entities.Notifficationbycheck;
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
public class NotifficationbycheckDAO implements Serializable {

    public NotifficationbycheckDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Notifficationbycheck notifficationbycheck) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(notifficationbycheck);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Notifficationbycheck notifficationbycheck) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            notifficationbycheck = em.merge(notifficationbycheck);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = notifficationbycheck.getId();
                if (findNotifficationbycheck(id) == null) {
                    throw new NonexistentEntityException("The notifficationbycheck with id " + id + " no longer exists.");
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
            Notifficationbycheck notifficationbycheck;
            try {
                notifficationbycheck = em.getReference(Notifficationbycheck.class, id);
                notifficationbycheck.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notifficationbycheck with id " + id + " no longer exists.", enfe);
            }
            em.remove(notifficationbycheck);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Notifficationbycheck> findNotifficationbycheckEntities() {
        return findNotifficationbycheckEntities(true, -1, -1);
    }

    public List<Notifficationbycheck> findNotifficationbycheckEntities(int maxResults, int firstResult) {
        return findNotifficationbycheckEntities(false, maxResults, firstResult);
    }

    private List<Notifficationbycheck> findNotifficationbycheckEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Notifficationbycheck.class));
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

    public Notifficationbycheck findNotifficationbycheck(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Notifficationbycheck.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotifficationbycheckCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Notifficationbycheck> rt = cq.from(Notifficationbycheck.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
