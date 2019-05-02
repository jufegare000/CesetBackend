/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.edu.udea.ceset.dto.entities.Expenditurebygroup;
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
public class ExpenditurebygroupDAO implements Serializable {

    public ExpenditurebygroupDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Expenditurebygroup expenditurebygroup) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(expenditurebygroup);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Expenditurebygroup expenditurebygroup) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            expenditurebygroup = em.merge(expenditurebygroup);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = expenditurebygroup.getId();
                if (findExpenditurebygroup(id) == null) {
                    throw new NonexistentEntityException("The expenditurebygroup with id " + id + " no longer exists.");
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
            Expenditurebygroup expenditurebygroup;
            try {
                expenditurebygroup = em.getReference(Expenditurebygroup.class, id);
                expenditurebygroup.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The expenditurebygroup with id " + id + " no longer exists.", enfe);
            }
            em.remove(expenditurebygroup);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Expenditurebygroup> findExpenditurebygroupEntities() {
        return findExpenditurebygroupEntities(true, -1, -1);
    }

    public List<Expenditurebygroup> findExpenditurebygroupEntities(int maxResults, int firstResult) {
        return findExpenditurebygroupEntities(false, maxResults, firstResult);
    }

    private List<Expenditurebygroup> findExpenditurebygroupEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Expenditurebygroup.class));
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

    public Expenditurebygroup findExpenditurebygroup(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Expenditurebygroup.class, id);
        } finally {
            em.close();
        }
    }

    public int getExpenditurebygroupCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Expenditurebygroup> rt = cq.from(Expenditurebygroup.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
