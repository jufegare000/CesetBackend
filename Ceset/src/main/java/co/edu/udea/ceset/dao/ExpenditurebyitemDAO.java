/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.edu.udea.ceset.dto.entities.Expenditurebyitem;
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
public class ExpenditurebyitemDAO implements Serializable {

    public ExpenditurebyitemDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Expenditurebyitem expenditurebyitem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(expenditurebyitem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Expenditurebyitem expenditurebyitem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            expenditurebyitem = em.merge(expenditurebyitem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = expenditurebyitem.getId();
                if (findExpenditurebyitem(id) == null) {
                    throw new NonexistentEntityException("The expenditurebyitem with id " + id + " no longer exists.");
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
            Expenditurebyitem expenditurebyitem;
            try {
                expenditurebyitem = em.getReference(Expenditurebyitem.class, id);
                expenditurebyitem.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The expenditurebyitem with id " + id + " no longer exists.", enfe);
            }
            em.remove(expenditurebyitem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Expenditurebyitem> findExpenditurebyitemEntities() {
        return findExpenditurebyitemEntities(true, -1, -1);
    }

    public List<Expenditurebyitem> findExpenditurebyitemEntities(int maxResults, int firstResult) {
        return findExpenditurebyitemEntities(false, maxResults, firstResult);
    }

    private List<Expenditurebyitem> findExpenditurebyitemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Expenditurebyitem.class));
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

    public Expenditurebyitem findExpenditurebyitem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Expenditurebyitem.class, id);
        } finally {
            em.close();
        }
    }

    public int getExpenditurebyitemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Expenditurebyitem> rt = cq.from(Expenditurebyitem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
