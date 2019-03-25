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
import co.udea.edu.co.dto.entities.Item;
import co.udea.edu.co.dto.entities.Estimated;
import co.udea.edu.co.dto.entities.Estimatedbyitem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class EstimatedbyitemDAO implements Serializable {

    public EstimatedbyitemDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estimatedbyitem estimatedbyitem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Item idItem = estimatedbyitem.getIdItem();
            if (idItem != null) {
                idItem = em.getReference(idItem.getClass(), idItem.getIdItem());
                estimatedbyitem.setIdItem(idItem);
            }
            Estimated idEstimated = estimatedbyitem.getIdEstimated();
            if (idEstimated != null) {
                idEstimated = em.getReference(idEstimated.getClass(), idEstimated.getIdEstimated());
                estimatedbyitem.setIdEstimated(idEstimated);
            }
            em.persist(estimatedbyitem);
            if (idItem != null) {
                idItem.getEstimatedbyitemCollection().add(estimatedbyitem);
                idItem = em.merge(idItem);
            }
            if (idEstimated != null) {
                idEstimated.getEstimatedbyitemCollection().add(estimatedbyitem);
                idEstimated = em.merge(idEstimated);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estimatedbyitem estimatedbyitem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estimatedbyitem persistentEstimatedbyitem = em.find(Estimatedbyitem.class, estimatedbyitem.getId());
            Item idItemOld = persistentEstimatedbyitem.getIdItem();
            Item idItemNew = estimatedbyitem.getIdItem();
            Estimated idEstimatedOld = persistentEstimatedbyitem.getIdEstimated();
            Estimated idEstimatedNew = estimatedbyitem.getIdEstimated();
            if (idItemNew != null) {
                idItemNew = em.getReference(idItemNew.getClass(), idItemNew.getIdItem());
                estimatedbyitem.setIdItem(idItemNew);
            }
            if (idEstimatedNew != null) {
                idEstimatedNew = em.getReference(idEstimatedNew.getClass(), idEstimatedNew.getIdEstimated());
                estimatedbyitem.setIdEstimated(idEstimatedNew);
            }
            estimatedbyitem = em.merge(estimatedbyitem);
            if (idItemOld != null && !idItemOld.equals(idItemNew)) {
                idItemOld.getEstimatedbyitemCollection().remove(estimatedbyitem);
                idItemOld = em.merge(idItemOld);
            }
            if (idItemNew != null && !idItemNew.equals(idItemOld)) {
                idItemNew.getEstimatedbyitemCollection().add(estimatedbyitem);
                idItemNew = em.merge(idItemNew);
            }
            if (idEstimatedOld != null && !idEstimatedOld.equals(idEstimatedNew)) {
                idEstimatedOld.getEstimatedbyitemCollection().remove(estimatedbyitem);
                idEstimatedOld = em.merge(idEstimatedOld);
            }
            if (idEstimatedNew != null && !idEstimatedNew.equals(idEstimatedOld)) {
                idEstimatedNew.getEstimatedbyitemCollection().add(estimatedbyitem);
                idEstimatedNew = em.merge(idEstimatedNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estimatedbyitem.getId();
                if (findEstimatedbyitem(id) == null) {
                    throw new NonexistentEntityException("The estimatedbyitem with id " + id + " no longer exists.");
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
            Estimatedbyitem estimatedbyitem;
            try {
                estimatedbyitem = em.getReference(Estimatedbyitem.class, id);
                estimatedbyitem.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estimatedbyitem with id " + id + " no longer exists.", enfe);
            }
            Item idItem = estimatedbyitem.getIdItem();
            if (idItem != null) {
                idItem.getEstimatedbyitemCollection().remove(estimatedbyitem);
                idItem = em.merge(idItem);
            }
            Estimated idEstimated = estimatedbyitem.getIdEstimated();
            if (idEstimated != null) {
                idEstimated.getEstimatedbyitemCollection().remove(estimatedbyitem);
                idEstimated = em.merge(idEstimated);
            }
            em.remove(estimatedbyitem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estimatedbyitem> findEstimatedbyitemEntities() {
        return findEstimatedbyitemEntities(true, -1, -1);
    }

    public List<Estimatedbyitem> findEstimatedbyitemEntities(int maxResults, int firstResult) {
        return findEstimatedbyitemEntities(false, maxResults, firstResult);
    }

    private List<Estimatedbyitem> findEstimatedbyitemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estimatedbyitem.class));
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

    public Estimatedbyitem findEstimatedbyitem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estimatedbyitem.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstimatedbyitemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estimatedbyitem> rt = cq.from(Estimatedbyitem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
