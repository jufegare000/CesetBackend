/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.edu.udea.ceset.dto.entities.Check;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.udea.ceset.dto.entities.Expenditure;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Expenditure idExpend = check.getIdExpend();
            if (idExpend != null) {
                idExpend = em.getReference(idExpend.getClass(), idExpend.getIdExpend());
                check.setIdExpend(idExpend);
            }
            em.persist(check);
            if (idExpend != null) {
                idExpend.getCheckCollection().add(check);
                idExpend = em.merge(idExpend);
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
            if (idExpendNew != null) {
                idExpendNew = em.getReference(idExpendNew.getClass(), idExpendNew.getIdExpend());
                check.setIdExpend(idExpendNew);
            }
            check = em.merge(check);
            if (idExpendOld != null && !idExpendOld.equals(idExpendNew)) {
                idExpendOld.getCheckCollection().remove(check);
                idExpendOld = em.merge(idExpendOld);
            }
            if (idExpendNew != null && !idExpendNew.equals(idExpendOld)) {
                idExpendNew.getCheckCollection().add(check);
                idExpendNew = em.merge(idExpendNew);
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
