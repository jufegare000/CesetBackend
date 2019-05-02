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
import co.edu.udea.ceset.dto.entities.Discount;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class DiscountDAO implements Serializable {

    public DiscountDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Discount discount) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Academicactivity idAcad = discount.getIdAcad();
            if (idAcad != null) {
                idAcad = em.getReference(idAcad.getClass(), idAcad.getIdAcad());
                discount.setIdAcad(idAcad);
            }
            em.persist(discount);
            if (idAcad != null) {
                idAcad.getDiscountCollection().add(discount);
                idAcad = em.merge(idAcad);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Discount discount) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Discount persistentDiscount = em.find(Discount.class, discount.getIdDiscount());
            Academicactivity idAcadOld = persistentDiscount.getIdAcad();
            Academicactivity idAcadNew = discount.getIdAcad();
            if (idAcadNew != null) {
                idAcadNew = em.getReference(idAcadNew.getClass(), idAcadNew.getIdAcad());
                discount.setIdAcad(idAcadNew);
            }
            discount = em.merge(discount);
            if (idAcadOld != null && !idAcadOld.equals(idAcadNew)) {
                idAcadOld.getDiscountCollection().remove(discount);
                idAcadOld = em.merge(idAcadOld);
            }
            if (idAcadNew != null && !idAcadNew.equals(idAcadOld)) {
                idAcadNew.getDiscountCollection().add(discount);
                idAcadNew = em.merge(idAcadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = discount.getIdDiscount();
                if (findDiscount(id) == null) {
                    throw new NonexistentEntityException("The discount with id " + id + " no longer exists.");
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
            Discount discount;
            try {
                discount = em.getReference(Discount.class, id);
                discount.getIdDiscount();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The discount with id " + id + " no longer exists.", enfe);
            }
            Academicactivity idAcad = discount.getIdAcad();
            if (idAcad != null) {
                idAcad.getDiscountCollection().remove(discount);
                idAcad = em.merge(idAcad);
            }
            em.remove(discount);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Discount> findDiscountEntities() {
        return findDiscountEntities(true, -1, -1);
    }

    public List<Discount> findDiscountEntities(int maxResults, int firstResult) {
        return findDiscountEntities(false, maxResults, firstResult);
    }

    private List<Discount> findDiscountEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Discount.class));
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

    public Discount findDiscount(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Discount.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiscountCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Discount> rt = cq.from(Discount.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
