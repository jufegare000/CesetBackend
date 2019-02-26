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
import co.edu.udea.ceset.dto.Check;
import co.edu.udea.ceset.dto.Expenditure;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class ExpenditureDAO implements Serializable {

    public ExpenditureDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Expenditure expenditure) {
        if (expenditure.getExpenditurebyitemCollection() == null) {
            expenditure.setExpenditurebyitemCollection(new ArrayList<Expenditurebyitem>());
        }
        if (expenditure.getCheckCollection() == null) {
            expenditure.setCheckCollection(new ArrayList<Check>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Expenditurebyitem> attachedExpenditurebyitemCollection = new ArrayList<Expenditurebyitem>();
            for (Expenditurebyitem expenditurebyitemCollectionExpenditurebyitemToAttach : expenditure.getExpenditurebyitemCollection()) {
                expenditurebyitemCollectionExpenditurebyitemToAttach = em.getReference(expenditurebyitemCollectionExpenditurebyitemToAttach.getClass(), expenditurebyitemCollectionExpenditurebyitemToAttach.getId());
                attachedExpenditurebyitemCollection.add(expenditurebyitemCollectionExpenditurebyitemToAttach);
            }
            expenditure.setExpenditurebyitemCollection(attachedExpenditurebyitemCollection);
            Collection<Check> attachedCheckCollection = new ArrayList<Check>();
            for (Check checkCollectionCheckToAttach : expenditure.getCheckCollection()) {
                checkCollectionCheckToAttach = em.getReference(checkCollectionCheckToAttach.getClass(), checkCollectionCheckToAttach.getIdCheck());
                attachedCheckCollection.add(checkCollectionCheckToAttach);
            }
            expenditure.setCheckCollection(attachedCheckCollection);
            em.persist(expenditure);
            for (Expenditurebyitem expenditurebyitemCollectionExpenditurebyitem : expenditure.getExpenditurebyitemCollection()) {
                Expenditure oldIdExpendOfExpenditurebyitemCollectionExpenditurebyitem = expenditurebyitemCollectionExpenditurebyitem.getIdExpend();
                expenditurebyitemCollectionExpenditurebyitem.setIdExpend(expenditure);
                expenditurebyitemCollectionExpenditurebyitem = em.merge(expenditurebyitemCollectionExpenditurebyitem);
                if (oldIdExpendOfExpenditurebyitemCollectionExpenditurebyitem != null) {
                    oldIdExpendOfExpenditurebyitemCollectionExpenditurebyitem.getExpenditurebyitemCollection().remove(expenditurebyitemCollectionExpenditurebyitem);
                    oldIdExpendOfExpenditurebyitemCollectionExpenditurebyitem = em.merge(oldIdExpendOfExpenditurebyitemCollectionExpenditurebyitem);
                }
            }
            for (Check checkCollectionCheck : expenditure.getCheckCollection()) {
                Expenditure oldIdExpendOfCheckCollectionCheck = checkCollectionCheck.getIdExpend();
                checkCollectionCheck.setIdExpend(expenditure);
                checkCollectionCheck = em.merge(checkCollectionCheck);
                if (oldIdExpendOfCheckCollectionCheck != null) {
                    oldIdExpendOfCheckCollectionCheck.getCheckCollection().remove(checkCollectionCheck);
                    oldIdExpendOfCheckCollectionCheck = em.merge(oldIdExpendOfCheckCollectionCheck);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Expenditure expenditure) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Expenditure persistentExpenditure = em.find(Expenditure.class, expenditure.getIdExpend());
            Collection<Expenditurebyitem> expenditurebyitemCollectionOld = persistentExpenditure.getExpenditurebyitemCollection();
            Collection<Expenditurebyitem> expenditurebyitemCollectionNew = expenditure.getExpenditurebyitemCollection();
            Collection<Check> checkCollectionOld = persistentExpenditure.getCheckCollection();
            Collection<Check> checkCollectionNew = expenditure.getCheckCollection();
            Collection<Expenditurebyitem> attachedExpenditurebyitemCollectionNew = new ArrayList<Expenditurebyitem>();
            for (Expenditurebyitem expenditurebyitemCollectionNewExpenditurebyitemToAttach : expenditurebyitemCollectionNew) {
                expenditurebyitemCollectionNewExpenditurebyitemToAttach = em.getReference(expenditurebyitemCollectionNewExpenditurebyitemToAttach.getClass(), expenditurebyitemCollectionNewExpenditurebyitemToAttach.getId());
                attachedExpenditurebyitemCollectionNew.add(expenditurebyitemCollectionNewExpenditurebyitemToAttach);
            }
            expenditurebyitemCollectionNew = attachedExpenditurebyitemCollectionNew;
            expenditure.setExpenditurebyitemCollection(expenditurebyitemCollectionNew);
            Collection<Check> attachedCheckCollectionNew = new ArrayList<Check>();
            for (Check checkCollectionNewCheckToAttach : checkCollectionNew) {
                checkCollectionNewCheckToAttach = em.getReference(checkCollectionNewCheckToAttach.getClass(), checkCollectionNewCheckToAttach.getIdCheck());
                attachedCheckCollectionNew.add(checkCollectionNewCheckToAttach);
            }
            checkCollectionNew = attachedCheckCollectionNew;
            expenditure.setCheckCollection(checkCollectionNew);
            expenditure = em.merge(expenditure);
            for (Expenditurebyitem expenditurebyitemCollectionOldExpenditurebyitem : expenditurebyitemCollectionOld) {
                if (!expenditurebyitemCollectionNew.contains(expenditurebyitemCollectionOldExpenditurebyitem)) {
                    expenditurebyitemCollectionOldExpenditurebyitem.setIdExpend(null);
                    expenditurebyitemCollectionOldExpenditurebyitem = em.merge(expenditurebyitemCollectionOldExpenditurebyitem);
                }
            }
            for (Expenditurebyitem expenditurebyitemCollectionNewExpenditurebyitem : expenditurebyitemCollectionNew) {
                if (!expenditurebyitemCollectionOld.contains(expenditurebyitemCollectionNewExpenditurebyitem)) {
                    Expenditure oldIdExpendOfExpenditurebyitemCollectionNewExpenditurebyitem = expenditurebyitemCollectionNewExpenditurebyitem.getIdExpend();
                    expenditurebyitemCollectionNewExpenditurebyitem.setIdExpend(expenditure);
                    expenditurebyitemCollectionNewExpenditurebyitem = em.merge(expenditurebyitemCollectionNewExpenditurebyitem);
                    if (oldIdExpendOfExpenditurebyitemCollectionNewExpenditurebyitem != null && !oldIdExpendOfExpenditurebyitemCollectionNewExpenditurebyitem.equals(expenditure)) {
                        oldIdExpendOfExpenditurebyitemCollectionNewExpenditurebyitem.getExpenditurebyitemCollection().remove(expenditurebyitemCollectionNewExpenditurebyitem);
                        oldIdExpendOfExpenditurebyitemCollectionNewExpenditurebyitem = em.merge(oldIdExpendOfExpenditurebyitemCollectionNewExpenditurebyitem);
                    }
                }
            }
            for (Check checkCollectionOldCheck : checkCollectionOld) {
                if (!checkCollectionNew.contains(checkCollectionOldCheck)) {
                    checkCollectionOldCheck.setIdExpend(null);
                    checkCollectionOldCheck = em.merge(checkCollectionOldCheck);
                }
            }
            for (Check checkCollectionNewCheck : checkCollectionNew) {
                if (!checkCollectionOld.contains(checkCollectionNewCheck)) {
                    Expenditure oldIdExpendOfCheckCollectionNewCheck = checkCollectionNewCheck.getIdExpend();
                    checkCollectionNewCheck.setIdExpend(expenditure);
                    checkCollectionNewCheck = em.merge(checkCollectionNewCheck);
                    if (oldIdExpendOfCheckCollectionNewCheck != null && !oldIdExpendOfCheckCollectionNewCheck.equals(expenditure)) {
                        oldIdExpendOfCheckCollectionNewCheck.getCheckCollection().remove(checkCollectionNewCheck);
                        oldIdExpendOfCheckCollectionNewCheck = em.merge(oldIdExpendOfCheckCollectionNewCheck);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = expenditure.getIdExpend();
                if (findExpenditure(id) == null) {
                    throw new NonexistentEntityException("The expenditure with id " + id + " no longer exists.");
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
            Expenditure expenditure;
            try {
                expenditure = em.getReference(Expenditure.class, id);
                expenditure.getIdExpend();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The expenditure with id " + id + " no longer exists.", enfe);
            }
            Collection<Expenditurebyitem> expenditurebyitemCollection = expenditure.getExpenditurebyitemCollection();
            for (Expenditurebyitem expenditurebyitemCollectionExpenditurebyitem : expenditurebyitemCollection) {
                expenditurebyitemCollectionExpenditurebyitem.setIdExpend(null);
                expenditurebyitemCollectionExpenditurebyitem = em.merge(expenditurebyitemCollectionExpenditurebyitem);
            }
            Collection<Check> checkCollection = expenditure.getCheckCollection();
            for (Check checkCollectionCheck : checkCollection) {
                checkCollectionCheck.setIdExpend(null);
                checkCollectionCheck = em.merge(checkCollectionCheck);
            }
            em.remove(expenditure);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Expenditure> findExpenditureEntities() {
        return findExpenditureEntities(true, -1, -1);
    }

    public List<Expenditure> findExpenditureEntities(int maxResults, int firstResult) {
        return findExpenditureEntities(false, maxResults, firstResult);
    }

    private List<Expenditure> findExpenditureEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Expenditure.class));
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

    public Expenditure findExpenditure(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Expenditure.class, id);
        } finally {
            em.close();
        }
    }

    public int getExpenditureCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Expenditure> rt = cq.from(Expenditure.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
