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
import co.udea.edu.co.dto.entities.Budget;
import co.udea.edu.co.dto.entities.Budgetbyexpenditure;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan
 */
public class BudgetbyexpenditureDAO implements Serializable {

    public BudgetbyexpenditureDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Budgetbyexpenditure budgetbyexpenditure) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Budget idBudget = budgetbyexpenditure.getIdBudget();
            if (idBudget != null) {
                idBudget = em.getReference(idBudget.getClass(), idBudget.getIdBudget());
                budgetbyexpenditure.setIdBudget(idBudget);
            }
            em.persist(budgetbyexpenditure);
            if (idBudget != null) {
                idBudget.getBudgetbyexpenditureCollection().add(budgetbyexpenditure);
                idBudget = em.merge(idBudget);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Budgetbyexpenditure budgetbyexpenditure) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Budgetbyexpenditure persistentBudgetbyexpenditure = em.find(Budgetbyexpenditure.class, budgetbyexpenditure.getId());
            Budget idBudgetOld = persistentBudgetbyexpenditure.getIdBudget();
            Budget idBudgetNew = budgetbyexpenditure.getIdBudget();
            if (idBudgetNew != null) {
                idBudgetNew = em.getReference(idBudgetNew.getClass(), idBudgetNew.getIdBudget());
                budgetbyexpenditure.setIdBudget(idBudgetNew);
            }
            budgetbyexpenditure = em.merge(budgetbyexpenditure);
            if (idBudgetOld != null && !idBudgetOld.equals(idBudgetNew)) {
                idBudgetOld.getBudgetbyexpenditureCollection().remove(budgetbyexpenditure);
                idBudgetOld = em.merge(idBudgetOld);
            }
            if (idBudgetNew != null && !idBudgetNew.equals(idBudgetOld)) {
                idBudgetNew.getBudgetbyexpenditureCollection().add(budgetbyexpenditure);
                idBudgetNew = em.merge(idBudgetNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = budgetbyexpenditure.getId();
                if (findBudgetbyexpenditure(id) == null) {
                    throw new NonexistentEntityException("The budgetbyexpenditure with id " + id + " no longer exists.");
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
            Budgetbyexpenditure budgetbyexpenditure;
            try {
                budgetbyexpenditure = em.getReference(Budgetbyexpenditure.class, id);
                budgetbyexpenditure.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The budgetbyexpenditure with id " + id + " no longer exists.", enfe);
            }
            Budget idBudget = budgetbyexpenditure.getIdBudget();
            if (idBudget != null) {
                idBudget.getBudgetbyexpenditureCollection().remove(budgetbyexpenditure);
                idBudget = em.merge(idBudget);
            }
            em.remove(budgetbyexpenditure);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Budgetbyexpenditure> findBudgetbyexpenditureEntities() {
        return findBudgetbyexpenditureEntities(true, -1, -1);
    }

    public List<Budgetbyexpenditure> findBudgetbyexpenditureEntities(int maxResults, int firstResult) {
        return findBudgetbyexpenditureEntities(false, maxResults, firstResult);
    }

    private List<Budgetbyexpenditure> findBudgetbyexpenditureEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Budgetbyexpenditure.class));
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

    public Budgetbyexpenditure findBudgetbyexpenditure(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Budgetbyexpenditure.class, id);
        } finally {
            em.close();
        }
    }

    public int getBudgetbyexpenditureCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Budgetbyexpenditure> rt = cq.from(Budgetbyexpenditure.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
