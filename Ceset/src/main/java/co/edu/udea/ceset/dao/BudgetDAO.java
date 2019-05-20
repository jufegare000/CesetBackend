/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.edu.udea.ceset.dto.entities.Budget;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.udea.ceset.dto.entities.Groupe;
import co.edu.udea.ceset.dto.entities.Budgetbyexpenditure;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan
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
        if (budget.getBudgetbyexpenditureCollection() == null) {
            budget.setBudgetbyexpenditureCollection(new ArrayList<Budgetbyexpenditure>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Groupe idGroup = budget.getIdGroup();
            if (idGroup != null) {
                idGroup = em.getReference(idGroup.getClass(), idGroup.getIdGroup());
                budget.setIdGroup(idGroup);
            }
            Collection<Budgetbyexpenditure> attachedBudgetbyexpenditureCollection = new ArrayList<Budgetbyexpenditure>();
            for (Budgetbyexpenditure budgetbyexpenditureCollectionBudgetbyexpenditureToAttach : budget.getBudgetbyexpenditureCollection()) {
                budgetbyexpenditureCollectionBudgetbyexpenditureToAttach = em.getReference(budgetbyexpenditureCollectionBudgetbyexpenditureToAttach.getClass(), budgetbyexpenditureCollectionBudgetbyexpenditureToAttach.getId());
                attachedBudgetbyexpenditureCollection.add(budgetbyexpenditureCollectionBudgetbyexpenditureToAttach);
            }
            budget.setBudgetbyexpenditureCollection(attachedBudgetbyexpenditureCollection);
            em.persist(budget);
            if (idGroup != null) {
                idGroup.getBudgetCollection().add(budget);
                idGroup = em.merge(idGroup);
            }
            for (Budgetbyexpenditure budgetbyexpenditureCollectionBudgetbyexpenditure : budget.getBudgetbyexpenditureCollection()) {
                Budget oldIdBudgetOfBudgetbyexpenditureCollectionBudgetbyexpenditure = budgetbyexpenditureCollectionBudgetbyexpenditure.getIdBudget();
                budgetbyexpenditureCollectionBudgetbyexpenditure.setIdBudget(budget);
                budgetbyexpenditureCollectionBudgetbyexpenditure = em.merge(budgetbyexpenditureCollectionBudgetbyexpenditure);
                if (oldIdBudgetOfBudgetbyexpenditureCollectionBudgetbyexpenditure != null) {
                    oldIdBudgetOfBudgetbyexpenditureCollectionBudgetbyexpenditure.getBudgetbyexpenditureCollection().remove(budgetbyexpenditureCollectionBudgetbyexpenditure);
                    oldIdBudgetOfBudgetbyexpenditureCollectionBudgetbyexpenditure = em.merge(oldIdBudgetOfBudgetbyexpenditureCollectionBudgetbyexpenditure);
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
            Groupe idGroupOld = persistentBudget.getIdGroup();
            Groupe idGroupNew = budget.getIdGroup();
            Collection<Budgetbyexpenditure> budgetbyexpenditureCollectionOld = persistentBudget.getBudgetbyexpenditureCollection();
            Collection<Budgetbyexpenditure> budgetbyexpenditureCollectionNew = budget.getBudgetbyexpenditureCollection();
            if (idGroupNew != null) {
                idGroupNew = em.getReference(idGroupNew.getClass(), idGroupNew.getIdGroup());
                budget.setIdGroup(idGroupNew);
            }
            Collection<Budgetbyexpenditure> attachedBudgetbyexpenditureCollectionNew = new ArrayList<Budgetbyexpenditure>();
            for (Budgetbyexpenditure budgetbyexpenditureCollectionNewBudgetbyexpenditureToAttach : budgetbyexpenditureCollectionNew) {
                budgetbyexpenditureCollectionNewBudgetbyexpenditureToAttach = em.getReference(budgetbyexpenditureCollectionNewBudgetbyexpenditureToAttach.getClass(), budgetbyexpenditureCollectionNewBudgetbyexpenditureToAttach.getId());
                attachedBudgetbyexpenditureCollectionNew.add(budgetbyexpenditureCollectionNewBudgetbyexpenditureToAttach);
            }
            budgetbyexpenditureCollectionNew = attachedBudgetbyexpenditureCollectionNew;
            budget.setBudgetbyexpenditureCollection(budgetbyexpenditureCollectionNew);
            budget = em.merge(budget);
            if (idGroupOld != null && !idGroupOld.equals(idGroupNew)) {
                idGroupOld.getBudgetCollection().remove(budget);
                idGroupOld = em.merge(idGroupOld);
            }
            if (idGroupNew != null && !idGroupNew.equals(idGroupOld)) {
                idGroupNew.getBudgetCollection().add(budget);
                idGroupNew = em.merge(idGroupNew);
            }
            for (Budgetbyexpenditure budgetbyexpenditureCollectionOldBudgetbyexpenditure : budgetbyexpenditureCollectionOld) {
                if (!budgetbyexpenditureCollectionNew.contains(budgetbyexpenditureCollectionOldBudgetbyexpenditure)) {
                    budgetbyexpenditureCollectionOldBudgetbyexpenditure.setIdBudget(null);
                    budgetbyexpenditureCollectionOldBudgetbyexpenditure = em.merge(budgetbyexpenditureCollectionOldBudgetbyexpenditure);
                }
            }
            for (Budgetbyexpenditure budgetbyexpenditureCollectionNewBudgetbyexpenditure : budgetbyexpenditureCollectionNew) {
                if (!budgetbyexpenditureCollectionOld.contains(budgetbyexpenditureCollectionNewBudgetbyexpenditure)) {
                    Budget oldIdBudgetOfBudgetbyexpenditureCollectionNewBudgetbyexpenditure = budgetbyexpenditureCollectionNewBudgetbyexpenditure.getIdBudget();
                    budgetbyexpenditureCollectionNewBudgetbyexpenditure.setIdBudget(budget);
                    budgetbyexpenditureCollectionNewBudgetbyexpenditure = em.merge(budgetbyexpenditureCollectionNewBudgetbyexpenditure);
                    if (oldIdBudgetOfBudgetbyexpenditureCollectionNewBudgetbyexpenditure != null && !oldIdBudgetOfBudgetbyexpenditureCollectionNewBudgetbyexpenditure.equals(budget)) {
                        oldIdBudgetOfBudgetbyexpenditureCollectionNewBudgetbyexpenditure.getBudgetbyexpenditureCollection().remove(budgetbyexpenditureCollectionNewBudgetbyexpenditure);
                        oldIdBudgetOfBudgetbyexpenditureCollectionNewBudgetbyexpenditure = em.merge(oldIdBudgetOfBudgetbyexpenditureCollectionNewBudgetbyexpenditure);
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
            Groupe idGroup = budget.getIdGroup();
            if (idGroup != null) {
                idGroup.getBudgetCollection().remove(budget);
                idGroup = em.merge(idGroup);
            }
            Collection<Budgetbyexpenditure> budgetbyexpenditureCollection = budget.getBudgetbyexpenditureCollection();
            for (Budgetbyexpenditure budgetbyexpenditureCollectionBudgetbyexpenditure : budgetbyexpenditureCollection) {
                budgetbyexpenditureCollectionBudgetbyexpenditure.setIdBudget(null);
                budgetbyexpenditureCollectionBudgetbyexpenditure = em.merge(budgetbyexpenditureCollectionBudgetbyexpenditure);
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
