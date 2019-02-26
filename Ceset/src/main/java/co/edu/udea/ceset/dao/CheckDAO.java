/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.edu.udea.ceset.dto.Check;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.udea.ceset.dto.Expenditure;
import co.edu.udea.ceset.dto.Notifficationbycheck;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class CheckDAO implements Serializable {

    public CheckDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Check check) {
        if (check.getNotifficationbycheckCollection() == null) {
            check.setNotifficationbycheckCollection(new ArrayList<Notifficationbycheck>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Expenditure idExpend = check.getIdExpend();
            if (idExpend != null) {
                idExpend = em.getReference(idExpend.getClass(), idExpend.getIdExpend());
                check.setIdExpend(idExpend);
            }
            Collection<Notifficationbycheck> attachedNotifficationbycheckCollection = new ArrayList<Notifficationbycheck>();
            for (Notifficationbycheck notifficationbycheckCollectionNotifficationbycheckToAttach : check.getNotifficationbycheckCollection()) {
                notifficationbycheckCollectionNotifficationbycheckToAttach = em.getReference(notifficationbycheckCollectionNotifficationbycheckToAttach.getClass(), notifficationbycheckCollectionNotifficationbycheckToAttach.getId());
                attachedNotifficationbycheckCollection.add(notifficationbycheckCollectionNotifficationbycheckToAttach);
            }
            check.setNotifficationbycheckCollection(attachedNotifficationbycheckCollection);
            em.persist(check);
            if (idExpend != null) {
                idExpend.getCheckCollection().add(check);
                idExpend = em.merge(idExpend);
            }
            for (Notifficationbycheck notifficationbycheckCollectionNotifficationbycheck : check.getNotifficationbycheckCollection()) {
                Check oldIdCheckOfNotifficationbycheckCollectionNotifficationbycheck = notifficationbycheckCollectionNotifficationbycheck.getIdCheck();
                notifficationbycheckCollectionNotifficationbycheck.setIdCheck(check);
                notifficationbycheckCollectionNotifficationbycheck = em.merge(notifficationbycheckCollectionNotifficationbycheck);
                if (oldIdCheckOfNotifficationbycheckCollectionNotifficationbycheck != null) {
                    oldIdCheckOfNotifficationbycheckCollectionNotifficationbycheck.getNotifficationbycheckCollection().remove(notifficationbycheckCollectionNotifficationbycheck);
                    oldIdCheckOfNotifficationbycheckCollectionNotifficationbycheck = em.merge(oldIdCheckOfNotifficationbycheckCollectionNotifficationbycheck);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Check check) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Check persistentCheck = em.find(Check.class, check.getIdCheck());
            Expenditure idExpendOld = persistentCheck.getIdExpend();
            Expenditure idExpendNew = check.getIdExpend();
            Collection<Notifficationbycheck> notifficationbycheckCollectionOld = persistentCheck.getNotifficationbycheckCollection();
            Collection<Notifficationbycheck> notifficationbycheckCollectionNew = check.getNotifficationbycheckCollection();
            if (idExpendNew != null) {
                idExpendNew = em.getReference(idExpendNew.getClass(), idExpendNew.getIdExpend());
                check.setIdExpend(idExpendNew);
            }
            Collection<Notifficationbycheck> attachedNotifficationbycheckCollectionNew = new ArrayList<Notifficationbycheck>();
            for (Notifficationbycheck notifficationbycheckCollectionNewNotifficationbycheckToAttach : notifficationbycheckCollectionNew) {
                notifficationbycheckCollectionNewNotifficationbycheckToAttach = em.getReference(notifficationbycheckCollectionNewNotifficationbycheckToAttach.getClass(), notifficationbycheckCollectionNewNotifficationbycheckToAttach.getId());
                attachedNotifficationbycheckCollectionNew.add(notifficationbycheckCollectionNewNotifficationbycheckToAttach);
            }
            notifficationbycheckCollectionNew = attachedNotifficationbycheckCollectionNew;
            check.setNotifficationbycheckCollection(notifficationbycheckCollectionNew);
            check = em.merge(check);
            if (idExpendOld != null && !idExpendOld.equals(idExpendNew)) {
                idExpendOld.getCheckCollection().remove(check);
                idExpendOld = em.merge(idExpendOld);
            }
            if (idExpendNew != null && !idExpendNew.equals(idExpendOld)) {
                idExpendNew.getCheckCollection().add(check);
                idExpendNew = em.merge(idExpendNew);
            }
            for (Notifficationbycheck notifficationbycheckCollectionOldNotifficationbycheck : notifficationbycheckCollectionOld) {
                if (!notifficationbycheckCollectionNew.contains(notifficationbycheckCollectionOldNotifficationbycheck)) {
                    notifficationbycheckCollectionOldNotifficationbycheck.setIdCheck(null);
                    notifficationbycheckCollectionOldNotifficationbycheck = em.merge(notifficationbycheckCollectionOldNotifficationbycheck);
                }
            }
            for (Notifficationbycheck notifficationbycheckCollectionNewNotifficationbycheck : notifficationbycheckCollectionNew) {
                if (!notifficationbycheckCollectionOld.contains(notifficationbycheckCollectionNewNotifficationbycheck)) {
                    Check oldIdCheckOfNotifficationbycheckCollectionNewNotifficationbycheck = notifficationbycheckCollectionNewNotifficationbycheck.getIdCheck();
                    notifficationbycheckCollectionNewNotifficationbycheck.setIdCheck(check);
                    notifficationbycheckCollectionNewNotifficationbycheck = em.merge(notifficationbycheckCollectionNewNotifficationbycheck);
                    if (oldIdCheckOfNotifficationbycheckCollectionNewNotifficationbycheck != null && !oldIdCheckOfNotifficationbycheckCollectionNewNotifficationbycheck.equals(check)) {
                        oldIdCheckOfNotifficationbycheckCollectionNewNotifficationbycheck.getNotifficationbycheckCollection().remove(notifficationbycheckCollectionNewNotifficationbycheck);
                        oldIdCheckOfNotifficationbycheckCollectionNewNotifficationbycheck = em.merge(oldIdCheckOfNotifficationbycheckCollectionNewNotifficationbycheck);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = check.getIdCheck();
                if (findCheck(id) == null) {
                    throw new NonexistentEntityException("The check with id " + id + " no longer exists.");
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
            Check check;
            try {
                check = em.getReference(Check.class, id);
                check.getIdCheck();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The check with id " + id + " no longer exists.", enfe);
            }
            Expenditure idExpend = check.getIdExpend();
            if (idExpend != null) {
                idExpend.getCheckCollection().remove(check);
                idExpend = em.merge(idExpend);
            }
            Collection<Notifficationbycheck> notifficationbycheckCollection = check.getNotifficationbycheckCollection();
            for (Notifficationbycheck notifficationbycheckCollectionNotifficationbycheck : notifficationbycheckCollection) {
                notifficationbycheckCollectionNotifficationbycheck.setIdCheck(null);
                notifficationbycheckCollectionNotifficationbycheck = em.merge(notifficationbycheckCollectionNotifficationbycheck);
            }
            em.remove(check);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Check> findCheckEntities() {
        return findCheckEntities(true, -1, -1);
    }

    public List<Check> findCheckEntities(int maxResults, int firstResult) {
        return findCheckEntities(false, maxResults, firstResult);
    }

    private List<Check> findCheckEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Check.class));
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

    public Check findCheck(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Check.class, id);
        } finally {
            em.close();
        }
    }

    public int getCheckCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Check> rt = cq.from(Check.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
