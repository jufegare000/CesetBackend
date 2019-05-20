/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.edu.udea.ceset.dto.entities.Estimatedbyitem;
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
public class EstimatedbyitemDAO implements Serializable {

    public EstimatedbyitemDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estimatedbyitem estimatedbyitem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estimatedbyitem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estimatedbyitem estimatedbyitem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estimatedbyitem = em.merge(estimatedbyitem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estimatedbyitem.getId();
                if (findEstimatedbyitem(id) == null) {
                    throw new NonexistentEntityException("The estimatedbyitem with id " + id + " no longer exists.");
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
            Estimatedbyitem estimatedbyitem;
            try {
                estimatedbyitem = em.getReference(Estimatedbyitem.class, id);
                estimatedbyitem.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estimatedbyitem with id " + id + " no longer exists.", enfe);
            }
            em.remove(estimatedbyitem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estimatedbyitem> findEstimatedbyitemEntities() {
        return findEstimatedbyitemEntities(true, -1, -1);
    }

    public List<Estimatedbyitem> findEstimatedbyitemEntities(int maxResults, int firstResult) {
        return findEstimatedbyitemEntities(false, maxResults, firstResult);
    }

    private List<Estimatedbyitem> findEstimatedbyitemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estimatedbyitem.class));
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

    public Estimatedbyitem findEstimatedbyitem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estimatedbyitem.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstimatedbyitemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estimatedbyitem> rt = cq.from(Estimatedbyitem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
