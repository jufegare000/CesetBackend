/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.udea.edu.co.dto.entities.Portafolio;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Juan
 */
public class PortafolioDAO implements Serializable {

    public PortafolioDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Portafolio create(Portafolio portafolio) {
        List <Portafolio> nuevo;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(portafolio);
            em.getTransaction().commit();
        } finally {
            nuevo = em.createNamedQuery("Portafolio.findLast").setMaxResults(1).getResultList();
            if (em != null) {
                em.close();
            }
        }
        return  nuevo.get(0);
    }

    public void edit(Portafolio portafolio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            portafolio = em.merge(portafolio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = portafolio.getId();
                if (findPortafolio(id) == null) {
                    throw new NonexistentEntityException("The portafolio with id " + id + " no longer exists.");
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
            Portafolio portafolio;
            try {
                portafolio = em.getReference(Portafolio.class, id);
                portafolio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The portafolio with id " + id + " no longer exists.", enfe);
            }
            em.remove(portafolio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Portafolio> findPortafolioEntities() {
        return findPortafolioEntities(true, -1, -1);
    }

    public List<Portafolio> findPortafolioEntities(int maxResults, int firstResult) {
        return findPortafolioEntities(false, maxResults, firstResult);
    }

    private List<Portafolio> findPortafolioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Portafolio.class));
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

    public Portafolio findPortafolio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Portafolio.class, id);
        } finally {
            em.close();
        }
    }

    public int getPortafolioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Portafolio> rt = cq.from(Portafolio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
