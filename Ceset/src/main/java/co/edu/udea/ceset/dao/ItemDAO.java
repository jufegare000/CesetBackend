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
import co.edu.udea.ceset.dto.Expenditurebyitem;
import java.util.ArrayList;
import java.util.Collection;
import co.edu.udea.ceset.dto.Estimatedbyitem;
import co.edu.udea.ceset.dto.Budgetbyitem;
import co.edu.udea.ceset.dto.Item;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class ItemDAO implements Serializable {

    public ItemDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Item item) {
        if (item.getExpenditurebyitemCollection() == null) {
            item.setExpenditurebyitemCollection(new ArrayList<Expenditurebyitem>());
        }
        if (item.getEstimatedbyitemCollection() == null) {
            item.setEstimatedbyitemCollection(new ArrayList<Estimatedbyitem>());
        }
        if (item.getBudgetbyitemCollection() == null) {
            item.setBudgetbyitemCollection(new ArrayList<Budgetbyitem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Expenditurebyitem> attachedExpenditurebyitemCollection = new ArrayList<Expenditurebyitem>();
            for (Expenditurebyitem expenditurebyitemCollectionExpenditurebyitemToAttach : item.getExpenditurebyitemCollection()) {
                expenditurebyitemCollectionExpenditurebyitemToAttach = em.getReference(expenditurebyitemCollectionExpenditurebyitemToAttach.getClass(), expenditurebyitemCollectionExpenditurebyitemToAttach.getId());
                attachedExpenditurebyitemCollection.add(expenditurebyitemCollectionExpenditurebyitemToAttach);
            }
            item.setExpenditurebyitemCollection(attachedExpenditurebyitemCollection);
            Collection<Estimatedbyitem> attachedEstimatedbyitemCollection = new ArrayList<Estimatedbyitem>();
            for (Estimatedbyitem estimatedbyitemCollectionEstimatedbyitemToAttach : item.getEstimatedbyitemCollection()) {
                estimatedbyitemCollectionEstimatedbyitemToAttach = em.getReference(estimatedbyitemCollectionEstimatedbyitemToAttach.getClass(), estimatedbyitemCollectionEstimatedbyitemToAttach.getId());
                attachedEstimatedbyitemCollection.add(estimatedbyitemCollectionEstimatedbyitemToAttach);
            }
            item.setEstimatedbyitemCollection(attachedEstimatedbyitemCollection);
            Collection<Budgetbyitem> attachedBudgetbyitemCollection = new ArrayList<Budgetbyitem>();
            for (Budgetbyitem budgetbyitemCollectionBudgetbyitemToAttach : item.getBudgetbyitemCollection()) {
                budgetbyitemCollectionBudgetbyitemToAttach = em.getReference(budgetbyitemCollectionBudgetbyitemToAttach.getClass(), budgetbyitemCollectionBudgetbyitemToAttach.getId());
                attachedBudgetbyitemCollection.add(budgetbyitemCollectionBudgetbyitemToAttach);
            }
            item.setBudgetbyitemCollection(attachedBudgetbyitemCollection);
            em.persist(item);
            for (Expenditurebyitem expenditurebyitemCollectionExpenditurebyitem : item.getExpenditurebyitemCollection()) {
                Item oldIdItemOfExpenditurebyitemCollectionExpenditurebyitem = expenditurebyitemCollectionExpenditurebyitem.getIdItem();
                expenditurebyitemCollectionExpenditurebyitem.setIdItem(item);
                expenditurebyitemCollectionExpenditurebyitem = em.merge(expenditurebyitemCollectionExpenditurebyitem);
                if (oldIdItemOfExpenditurebyitemCollectionExpenditurebyitem != null) {
                    oldIdItemOfExpenditurebyitemCollectionExpenditurebyitem.getExpenditurebyitemCollection().remove(expenditurebyitemCollectionExpenditurebyitem);
                    oldIdItemOfExpenditurebyitemCollectionExpenditurebyitem = em.merge(oldIdItemOfExpenditurebyitemCollectionExpenditurebyitem);
                }
            }
            for (Estimatedbyitem estimatedbyitemCollectionEstimatedbyitem : item.getEstimatedbyitemCollection()) {
                Item oldIdItemOfEstimatedbyitemCollectionEstimatedbyitem = estimatedbyitemCollectionEstimatedbyitem.getIdItem();
                estimatedbyitemCollectionEstimatedbyitem.setIdItem(item);
                estimatedbyitemCollectionEstimatedbyitem = em.merge(estimatedbyitemCollectionEstimatedbyitem);
                if (oldIdItemOfEstimatedbyitemCollectionEstimatedbyitem != null) {
                    oldIdItemOfEstimatedbyitemCollectionEstimatedbyitem.getEstimatedbyitemCollection().remove(estimatedbyitemCollectionEstimatedbyitem);
                    oldIdItemOfEstimatedbyitemCollectionEstimatedbyitem = em.merge(oldIdItemOfEstimatedbyitemCollectionEstimatedbyitem);
                }
            }
            for (Budgetbyitem budgetbyitemCollectionBudgetbyitem : item.getBudgetbyitemCollection()) {
                Item oldIdItemOfBudgetbyitemCollectionBudgetbyitem = budgetbyitemCollectionBudgetbyitem.getIdItem();
                budgetbyitemCollectionBudgetbyitem.setIdItem(item);
                budgetbyitemCollectionBudgetbyitem = em.merge(budgetbyitemCollectionBudgetbyitem);
                if (oldIdItemOfBudgetbyitemCollectionBudgetbyitem != null) {
                    oldIdItemOfBudgetbyitemCollectionBudgetbyitem.getBudgetbyitemCollection().remove(budgetbyitemCollectionBudgetbyitem);
                    oldIdItemOfBudgetbyitemCollectionBudgetbyitem = em.merge(oldIdItemOfBudgetbyitemCollectionBudgetbyitem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Item item) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Item persistentItem = em.find(Item.class, item.getIdItem());
            Collection<Expenditurebyitem> expenditurebyitemCollectionOld = persistentItem.getExpenditurebyitemCollection();
            Collection<Expenditurebyitem> expenditurebyitemCollectionNew = item.getExpenditurebyitemCollection();
            Collection<Estimatedbyitem> estimatedbyitemCollectionOld = persistentItem.getEstimatedbyitemCollection();
            Collection<Estimatedbyitem> estimatedbyitemCollectionNew = item.getEstimatedbyitemCollection();
            Collection<Budgetbyitem> budgetbyitemCollectionOld = persistentItem.getBudgetbyitemCollection();
            Collection<Budgetbyitem> budgetbyitemCollectionNew = item.getBudgetbyitemCollection();
            Collection<Expenditurebyitem> attachedExpenditurebyitemCollectionNew = new ArrayList<Expenditurebyitem>();
            for (Expenditurebyitem expenditurebyitemCollectionNewExpenditurebyitemToAttach : expenditurebyitemCollectionNew) {
                expenditurebyitemCollectionNewExpenditurebyitemToAttach = em.getReference(expenditurebyitemCollectionNewExpenditurebyitemToAttach.getClass(), expenditurebyitemCollectionNewExpenditurebyitemToAttach.getId());
                attachedExpenditurebyitemCollectionNew.add(expenditurebyitemCollectionNewExpenditurebyitemToAttach);
            }
            expenditurebyitemCollectionNew = attachedExpenditurebyitemCollectionNew;
            item.setExpenditurebyitemCollection(expenditurebyitemCollectionNew);
            Collection<Estimatedbyitem> attachedEstimatedbyitemCollectionNew = new ArrayList<Estimatedbyitem>();
            for (Estimatedbyitem estimatedbyitemCollectionNewEstimatedbyitemToAttach : estimatedbyitemCollectionNew) {
                estimatedbyitemCollectionNewEstimatedbyitemToAttach = em.getReference(estimatedbyitemCollectionNewEstimatedbyitemToAttach.getClass(), estimatedbyitemCollectionNewEstimatedbyitemToAttach.getId());
                attachedEstimatedbyitemCollectionNew.add(estimatedbyitemCollectionNewEstimatedbyitemToAttach);
            }
            estimatedbyitemCollectionNew = attachedEstimatedbyitemCollectionNew;
            item.setEstimatedbyitemCollection(estimatedbyitemCollectionNew);
            Collection<Budgetbyitem> attachedBudgetbyitemCollectionNew = new ArrayList<Budgetbyitem>();
            for (Budgetbyitem budgetbyitemCollectionNewBudgetbyitemToAttach : budgetbyitemCollectionNew) {
                budgetbyitemCollectionNewBudgetbyitemToAttach = em.getReference(budgetbyitemCollectionNewBudgetbyitemToAttach.getClass(), budgetbyitemCollectionNewBudgetbyitemToAttach.getId());
                attachedBudgetbyitemCollectionNew.add(budgetbyitemCollectionNewBudgetbyitemToAttach);
            }
            budgetbyitemCollectionNew = attachedBudgetbyitemCollectionNew;
            item.setBudgetbyitemCollection(budgetbyitemCollectionNew);
            item = em.merge(item);
            for (Expenditurebyitem expenditurebyitemCollectionOldExpenditurebyitem : expenditurebyitemCollectionOld) {
                if (!expenditurebyitemCollectionNew.contains(expenditurebyitemCollectionOldExpenditurebyitem)) {
                    expenditurebyitemCollectionOldExpenditurebyitem.setIdItem(null);
                    expenditurebyitemCollectionOldExpenditurebyitem = em.merge(expenditurebyitemCollectionOldExpenditurebyitem);
                }
            }
            for (Expenditurebyitem expenditurebyitemCollectionNewExpenditurebyitem : expenditurebyitemCollectionNew) {
                if (!expenditurebyitemCollectionOld.contains(expenditurebyitemCollectionNewExpenditurebyitem)) {
                    Item oldIdItemOfExpenditurebyitemCollectionNewExpenditurebyitem = expenditurebyitemCollectionNewExpenditurebyitem.getIdItem();
                    expenditurebyitemCollectionNewExpenditurebyitem.setIdItem(item);
                    expenditurebyitemCollectionNewExpenditurebyitem = em.merge(expenditurebyitemCollectionNewExpenditurebyitem);
                    if (oldIdItemOfExpenditurebyitemCollectionNewExpenditurebyitem != null && !oldIdItemOfExpenditurebyitemCollectionNewExpenditurebyitem.equals(item)) {
                        oldIdItemOfExpenditurebyitemCollectionNewExpenditurebyitem.getExpenditurebyitemCollection().remove(expenditurebyitemCollectionNewExpenditurebyitem);
                        oldIdItemOfExpenditurebyitemCollectionNewExpenditurebyitem = em.merge(oldIdItemOfExpenditurebyitemCollectionNewExpenditurebyitem);
                    }
                }
            }
            for (Estimatedbyitem estimatedbyitemCollectionOldEstimatedbyitem : estimatedbyitemCollectionOld) {
                if (!estimatedbyitemCollectionNew.contains(estimatedbyitemCollectionOldEstimatedbyitem)) {
                    estimatedbyitemCollectionOldEstimatedbyitem.setIdItem(null);
                    estimatedbyitemCollectionOldEstimatedbyitem = em.merge(estimatedbyitemCollectionOldEstimatedbyitem);
                }
            }
            for (Estimatedbyitem estimatedbyitemCollectionNewEstimatedbyitem : estimatedbyitemCollectionNew) {
                if (!estimatedbyitemCollectionOld.contains(estimatedbyitemCollectionNewEstimatedbyitem)) {
                    Item oldIdItemOfEstimatedbyitemCollectionNewEstimatedbyitem = estimatedbyitemCollectionNewEstimatedbyitem.getIdItem();
                    estimatedbyitemCollectionNewEstimatedbyitem.setIdItem(item);
                    estimatedbyitemCollectionNewEstimatedbyitem = em.merge(estimatedbyitemCollectionNewEstimatedbyitem);
                    if (oldIdItemOfEstimatedbyitemCollectionNewEstimatedbyitem != null && !oldIdItemOfEstimatedbyitemCollectionNewEstimatedbyitem.equals(item)) {
                        oldIdItemOfEstimatedbyitemCollectionNewEstimatedbyitem.getEstimatedbyitemCollection().remove(estimatedbyitemCollectionNewEstimatedbyitem);
                        oldIdItemOfEstimatedbyitemCollectionNewEstimatedbyitem = em.merge(oldIdItemOfEstimatedbyitemCollectionNewEstimatedbyitem);
                    }
                }
            }
            for (Budgetbyitem budgetbyitemCollectionOldBudgetbyitem : budgetbyitemCollectionOld) {
                if (!budgetbyitemCollectionNew.contains(budgetbyitemCollectionOldBudgetbyitem)) {
                    budgetbyitemCollectionOldBudgetbyitem.setIdItem(null);
                    budgetbyitemCollectionOldBudgetbyitem = em.merge(budgetbyitemCollectionOldBudgetbyitem);
                }
            }
            for (Budgetbyitem budgetbyitemCollectionNewBudgetbyitem : budgetbyitemCollectionNew) {
                if (!budgetbyitemCollectionOld.contains(budgetbyitemCollectionNewBudgetbyitem)) {
                    Item oldIdItemOfBudgetbyitemCollectionNewBudgetbyitem = budgetbyitemCollectionNewBudgetbyitem.getIdItem();
                    budgetbyitemCollectionNewBudgetbyitem.setIdItem(item);
                    budgetbyitemCollectionNewBudgetbyitem = em.merge(budgetbyitemCollectionNewBudgetbyitem);
                    if (oldIdItemOfBudgetbyitemCollectionNewBudgetbyitem != null && !oldIdItemOfBudgetbyitemCollectionNewBudgetbyitem.equals(item)) {
                        oldIdItemOfBudgetbyitemCollectionNewBudgetbyitem.getBudgetbyitemCollection().remove(budgetbyitemCollectionNewBudgetbyitem);
                        oldIdItemOfBudgetbyitemCollectionNewBudgetbyitem = em.merge(oldIdItemOfBudgetbyitemCollectionNewBudgetbyitem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = item.getIdItem();
                if (findItem(id) == null) {
                    throw new NonexistentEntityException("The item with id " + id + " no longer exists.");
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
            Item item;
            try {
                item = em.getReference(Item.class, id);
                item.getIdItem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The item with id " + id + " no longer exists.", enfe);
            }
            Collection<Expenditurebyitem> expenditurebyitemCollection = item.getExpenditurebyitemCollection();
            for (Expenditurebyitem expenditurebyitemCollectionExpenditurebyitem : expenditurebyitemCollection) {
                expenditurebyitemCollectionExpenditurebyitem.setIdItem(null);
                expenditurebyitemCollectionExpenditurebyitem = em.merge(expenditurebyitemCollectionExpenditurebyitem);
            }
            Collection<Estimatedbyitem> estimatedbyitemCollection = item.getEstimatedbyitemCollection();
            for (Estimatedbyitem estimatedbyitemCollectionEstimatedbyitem : estimatedbyitemCollection) {
                estimatedbyitemCollectionEstimatedbyitem.setIdItem(null);
                estimatedbyitemCollectionEstimatedbyitem = em.merge(estimatedbyitemCollectionEstimatedbyitem);
            }
            Collection<Budgetbyitem> budgetbyitemCollection = item.getBudgetbyitemCollection();
            for (Budgetbyitem budgetbyitemCollectionBudgetbyitem : budgetbyitemCollection) {
                budgetbyitemCollectionBudgetbyitem.setIdItem(null);
                budgetbyitemCollectionBudgetbyitem = em.merge(budgetbyitemCollectionBudgetbyitem);
            }
            em.remove(item);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Item> findItemEntities() {
        return findItemEntities(true, -1, -1);
    }

    public List<Item> findItemEntities(int maxResults, int firstResult) {
        return findItemEntities(false, maxResults, firstResult);
    }

    private List<Item> findItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Item.class));
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

    public Item findItem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Item.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Item> rt = cq.from(Item.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
