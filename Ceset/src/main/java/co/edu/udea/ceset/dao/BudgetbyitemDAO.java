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
import co.udea.edu.co.dto.entities.Budgetbyitem;
import co.udea.edu.co.dto.entities.Item;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class BudgetbyitemDAO implements Serializable {

    public BudgetbyitemDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Budgetbyitem budgetbyitem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Budget idBudget = budgetbyitem.getIdBudget();
            if (idBudget != null) {
                idBudget = em.getReference(idBudget.getClass(), idBudget.getIdBudget());
                budgetbyitem.setIdBudget(idBudget);
            }
            Item idItem = budgetbyitem.getIdItem();
            if (idItem != null) {
                idItem = em.getReference(idItem.getClass(), idItem.getIdItem());
                budgetbyitem.setIdItem(idItem);
            }
            em.persist(budgetbyitem);
            if (idBudget != null) {
                idBudget.getBudgetbyitemCollection().add(budgetbyitem);
                idBudget = em.merge(idBudget);
            }
            if (idItem != null) {
                idItem.getBudgetbyitemCollection().add(budgetbyitem);
                idItem = em.merge(idItem);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Budgetbyitem budgetbyitem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Budgetbyitem persistentBudgetbyitem = em.find(Budgetbyitem.class, budgetbyitem.getId());
            Budget idBudgetOld = persistentBudgetbyitem.getIdBudget();
            Budget idBudgetNew = budgetbyitem.getIdBudget();
            Item idItemOld = persistentBudgetbyitem.getIdItem();
            Item idItemNew = budgetbyitem.getIdItem();
            if (idBudgetNew != null) {
                idBudgetNew = em.getReference(idBudgetNew.getClass(), idBudgetNew.getIdBudget());
                budgetbyitem.setIdBudget(idBudgetNew);
            }
            if (idItemNew != null) {
                idItemNew = em.getReference(idItemNew.getClass(), idItemNew.getIdItem());
                budgetbyitem.setIdItem(idItemNew);
            }
            budgetbyitem = em.merge(budgetbyitem);
            if (idBudgetOld != null && !idBudgetOld.equals(idBudgetNew)) {
                idBudgetOld.getBudgetbyitemCollection().remove(budgetbyitem);
                idBudgetOld = em.merge(idBudgetOld);
            }
            if (idBudgetNew != null && !idBudgetNew.equals(idBudgetOld)) {
                idBudgetNew.getBudgetbyitemCollection().add(budgetbyitem);
                idBudgetNew = em.merge(idBudgetNew);
            }
            if (idItemOld != null && !idItemOld.equals(idItemNew)) {
                idItemOld.getBudgetbyitemCollection().remove(budgetbyitem);
                idItemOld = em.merge(idItemOld);
            }
            if (idItemNew != null && !idItemNew.equals(idItemOld)) {
                idItemNew.getBudgetbyitemCollection().add(budgetbyitem);
                idItemNew = em.merge(idItemNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = budgetbyitem.getId();
                if (findBudgetbyitem(id) == null) {
                    throw new NonexistentEntityException("The budgetbyitem with id " + id + " no longer exists.");
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
            Budgetbyitem budgetbyitem;
            try {
                budgetbyitem = em.getReference(Budgetbyitem.class, id);
                budgetbyitem.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The budgetbyitem with id " + id + " no longer exists.", enfe);
            }
            Budget idBudget = budgetbyitem.getIdBudget();
            if (idBudget != null) {
                idBudget.getBudgetbyitemCollection().remove(budgetbyitem);
                idBudget = em.merge(idBudget);
            }
            Item idItem = budgetbyitem.getIdItem();
            if (idItem != null) {
                idItem.getBudgetbyitemCollection().remove(budgetbyitem);
                idItem = em.merge(idItem);
            }
            em.remove(budgetbyitem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Budgetbyitem> findBudgetbyitemEntities() {
        return findBudgetbyitemEntities(true, -1, -1);
    }

    public List<Budgetbyitem> findBudgetbyitemEntities(int maxResults, int firstResult) {
        return findBudgetbyitemEntities(false, maxResults, firstResult);
    }

    private List<Budgetbyitem> findBudgetbyitemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Budgetbyitem.class));
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

    public Budgetbyitem findBudgetbyitem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Budgetbyitem.class, id);
        } finally {
            em.close();
        }
    }

    public int getBudgetbyitemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Budgetbyitem> rt = cq.from(Budgetbyitem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
