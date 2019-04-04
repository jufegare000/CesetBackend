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
import co.udea.edu.co.dto.entities.Estimated;
import co.udea.edu.co.dto.entities.Estimatedbyexpenditure;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan
 */
public class EstimatedbyexpenditureDAO implements Serializable {

    public EstimatedbyexpenditureDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estimatedbyexpenditure estimatedbyexpenditure) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estimated idEstimated = estimatedbyexpenditure.getIdEstimated();
            if (idEstimated != null) {
                idEstimated = em.getReference(idEstimated.getClass(), idEstimated.getIdEstimated());
                estimatedbyexpenditure.setIdEstimated(idEstimated);
            }
            em.persist(estimatedbyexpenditure);
            if (idEstimated != null) {
                idEstimated.getEstimatedbyexpenditureCollection().add(estimatedbyexpenditure);
                idEstimated = em.merge(idEstimated);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estimatedbyexpenditure estimatedbyexpenditure) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estimatedbyexpenditure persistentEstimatedbyexpenditure = em.find(Estimatedbyexpenditure.class, estimatedbyexpenditure.getId());
            Estimated idEstimatedOld = persistentEstimatedbyexpenditure.getIdEstimated();
            Estimated idEstimatedNew = estimatedbyexpenditure.getIdEstimated();
            if (idEstimatedNew != null) {
                idEstimatedNew = em.getReference(idEstimatedNew.getClass(), idEstimatedNew.getIdEstimated());
                estimatedbyexpenditure.setIdEstimated(idEstimatedNew);
            }
            estimatedbyexpenditure = em.merge(estimatedbyexpenditure);
            if (idEstimatedOld != null && !idEstimatedOld.equals(idEstimatedNew)) {
                idEstimatedOld.getEstimatedbyexpenditureCollection().remove(estimatedbyexpenditure);
                idEstimatedOld = em.merge(idEstimatedOld);
            }
            if (idEstimatedNew != null && !idEstimatedNew.equals(idEstimatedOld)) {
                idEstimatedNew.getEstimatedbyexpenditureCollection().add(estimatedbyexpenditure);
                idEstimatedNew = em.merge(idEstimatedNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estimatedbyexpenditure.getId();
                if (findEstimatedbyexpenditure(id) == null) {
                    throw new NonexistentEntityException("The estimatedbyexpenditure with id " + id + " no longer exists.");
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
            Estimatedbyexpenditure estimatedbyexpenditure;
            try {
                estimatedbyexpenditure = em.getReference(Estimatedbyexpenditure.class, id);
                estimatedbyexpenditure.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estimatedbyexpenditure with id " + id + " no longer exists.", enfe);
            }
            Estimated idEstimated = estimatedbyexpenditure.getIdEstimated();
            if (idEstimated != null) {
                idEstimated.getEstimatedbyexpenditureCollection().remove(estimatedbyexpenditure);
                idEstimated = em.merge(idEstimated);
            }
            em.remove(estimatedbyexpenditure);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estimatedbyexpenditure> findEstimatedbyexpenditureEntities() {
        return findEstimatedbyexpenditureEntities(true, -1, -1);
    }

    public List<Estimatedbyexpenditure> findEstimatedbyexpenditureEntities(int maxResults, int firstResult) {
        return findEstimatedbyexpenditureEntities(false, maxResults, firstResult);
    }

    private List<Estimatedbyexpenditure> findEstimatedbyexpenditureEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estimatedbyexpenditure.class));
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

    public Estimatedbyexpenditure findEstimatedbyexpenditure(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estimatedbyexpenditure.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstimatedbyexpenditureCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estimatedbyexpenditure> rt = cq.from(Estimatedbyexpenditure.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
