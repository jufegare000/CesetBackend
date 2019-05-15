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

import co.edu.udea.ceset.dto.entities.Academicactivity;
import co.edu.udea.ceset.dto.entities.Budget;
import co.edu.udea.ceset.dto.entities.Budgetbyitem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class BudgetDAO implements Serializable {

    public BudgetDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Budget budget) {
        if (budget.getBudgetbyitemCollection() == null) {
            budget.setBudgetbyitemCollection(new ArrayList<Budgetbyitem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Academicactivity idActivity = budget.getIdActivity();
            if (idActivity != null) {
                idActivity = em.getReference(idActivity.getClass(), idActivity.getIdAcad());
                budget.setIdActivity(idActivity);
            }
            Collection<Budgetbyitem> attachedBudgetbyitemCollection = new ArrayList<Budgetbyitem>();
            for (Budgetbyitem budgetbyitemCollectionBudgetbyitemToAttach : budget.getBudgetbyitemCollection()) {
                budgetbyitemCollectionBudgetbyitemToAttach = em.getReference(budgetbyitemCollectionBudgetbyitemToAttach.getClass(), budgetbyitemCollectionBudgetbyitemToAttach.getId());
                attachedBudgetbyitemCollection.add(budgetbyitemCollectionBudgetbyitemToAttach);
            }
            budget.setBudgetbyitemCollection(attachedBudgetbyitemCollection);
            em.persist(budget);

            if (idActivity != null) {
                idActivity.getBudgetCollection().add(budget);
                idActivity = em.merge(idActivity);
            }
            for (Budgetbyitem budgetbyitemCollectionBudgetbyitem : budget.getBudgetbyitemCollection()) {
                Budget oldIdBudgetOfBudgetbyitemCollectionBudgetbyitem = budgetbyitemCollectionBudgetbyitem.getIdBudget();
                budgetbyitemCollectionBudgetbyitem.setIdBudget(budget);
                budgetbyitemCollectionBudgetbyitem = em.merge(budgetbyitemCollectionBudgetbyitem);
                if (oldIdBudgetOfBudgetbyitemCollectionBudgetbyitem != null) {
                    oldIdBudgetOfBudgetbyitemCollectionBudgetbyitem.getBudgetbyitemCollection().remove(budgetbyitemCollectionBudgetbyitem);
                    oldIdBudgetOfBudgetbyitemCollectionBudgetbyitem = em.merge(oldIdBudgetOfBudgetbyitemCollectionBudgetbyitem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Budget budget) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Budget persistentBudget = em.find(Budget.class, budget.getIdBudget());

            Academicactivity idActivityOld = persistentBudget.getIdActivity();
            Academicactivity idActivityNew = budget.getIdActivity();
            Collection<Budgetbyitem> budgetbyitemCollectionOld = persistentBudget.getBudgetbyitemCollection();
            Collection<Budgetbyitem> budgetbyitemCollectionNew = budget.getBudgetbyitemCollection();

            if (idActivityNew != null) {
                idActivityNew = em.getReference(idActivityNew.getClass(), idActivityNew.getIdAcad());
                budget.setIdActivity(idActivityNew);
            }
            Collection<Budgetbyitem> attachedBudgetbyitemCollectionNew = new ArrayList<Budgetbyitem>();
            for (Budgetbyitem budgetbyitemCollectionNewBudgetbyitemToAttach : budgetbyitemCollectionNew) {
                budgetbyitemCollectionNewBudgetbyitemToAttach = em.getReference(budgetbyitemCollectionNewBudgetbyitemToAttach.getClass(), budgetbyitemCollectionNewBudgetbyitemToAttach.getId());
                attachedBudgetbyitemCollectionNew.add(budgetbyitemCollectionNewBudgetbyitemToAttach);
            }
            budgetbyitemCollectionNew = attachedBudgetbyitemCollectionNew;
            budget.setBudgetbyitemCollection(budgetbyitemCollectionNew);
            budget = em.merge(budget);
        
            if (idActivityOld != null && !idActivityOld.equals(idActivityNew)) {
                idActivityOld.getBudgetCollection().remove(budget);
                idActivityOld = em.merge(idActivityOld);
            }
            if (idActivityNew != null && !idActivityNew.equals(idActivityOld)) {
                idActivityNew.getBudgetCollection().add(budget);
                idActivityNew = em.merge(idActivityNew);
            }
            for (Budgetbyitem budgetbyitemCollectionOldBudgetbyitem : budgetbyitemCollectionOld) {
                if (!budgetbyitemCollectionNew.contains(budgetbyitemCollectionOldBudgetbyitem)) {
                    budgetbyitemCollectionOldBudgetbyitem.setIdBudget(null);
                    budgetbyitemCollectionOldBudgetbyitem = em.merge(budgetbyitemCollectionOldBudgetbyitem);
                }
            }
            for (Budgetbyitem budgetbyitemCollectionNewBudgetbyitem : budgetbyitemCollectionNew) {
                if (!budgetbyitemCollectionOld.contains(budgetbyitemCollectionNewBudgetbyitem)) {
                    Budget oldIdBudgetOfBudgetbyitemCollectionNewBudgetbyitem = budgetbyitemCollectionNewBudgetbyitem.getIdBudget();
                    budgetbyitemCollectionNewBudgetbyitem.setIdBudget(budget);
                    budgetbyitemCollectionNewBudgetbyitem = em.merge(budgetbyitemCollectionNewBudgetbyitem);
                    if (oldIdBudgetOfBudgetbyitemCollectionNewBudgetbyitem != null && !oldIdBudgetOfBudgetbyitemCollectionNewBudgetbyitem.equals(budget)) {
                        oldIdBudgetOfBudgetbyitemCollectionNewBudgetbyitem.getBudgetbyitemCollection().remove(budgetbyitemCollectionNewBudgetbyitem);
                        oldIdBudgetOfBudgetbyitemCollectionNewBudgetbyitem = em.merge(oldIdBudgetOfBudgetbyitemCollectionNewBudgetbyitem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = budget.getIdBudget();
                if (findBudget(id) == null) {
                    throw new NonexistentEntityException("The budget with id " + id + " no longer exists.");
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
            Budget budget;
            try {
                budget = em.getReference(Budget.class, id);
                budget.getIdBudget();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The budget with id " + id + " no longer exists.", enfe);
            }

            Academicactivity idActivity = budget.getIdActivity();
            if (idActivity != null) {
                idActivity.getBudgetCollection().remove(budget);
                idActivity = em.merge(idActivity);
            }
            Collection<Budgetbyitem> budgetbyitemCollection = budget.getBudgetbyitemCollection();
            for (Budgetbyitem budgetbyitemCollectionBudgetbyitem : budgetbyitemCollection) {
                budgetbyitemCollectionBudgetbyitem.setIdBudget(null);
                budgetbyitemCollectionBudgetbyitem = em.merge(budgetbyitemCollectionBudgetbyitem);
            }
            em.remove(budget);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Budget> findBudgetEntities() {
        return findBudgetEntities(true, -1, -1);
    }

    public List<Budget> findBudgetEntities(int maxResults, int firstResult) {
        return findBudgetEntities(false, maxResults, firstResult);
    }

    private List<Budget> findBudgetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Budget.class));
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

    public Budget findBudget(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Budget.class, id);
        } finally {
            em.close();
        }
    }

    public int getBudgetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Budget> rt = cq.from(Budget.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
