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
import co.udea.edu.co.dto.entities.Cohort;
import co.udea.edu.co.dto.entities.Themes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class ThemesDAO implements Serializable {

    public ThemesDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Themes themes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cohort idCohort = themes.getIdCohort();
            if (idCohort != null) {
                idCohort = em.getReference(idCohort.getClass(), idCohort.getIdCohort());
                themes.setIdCohort(idCohort);
            }
            em.persist(themes);
            if (idCohort != null) {
                idCohort.getThemesCollection().add(themes);
                idCohort = em.merge(idCohort);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Themes themes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Themes persistentThemes = em.find(Themes.class, themes.getIdTheme());
            Cohort idCohortOld = persistentThemes.getIdCohort();
            Cohort idCohortNew = themes.getIdCohort();
            if (idCohortNew != null) {
                idCohortNew = em.getReference(idCohortNew.getClass(), idCohortNew.getIdCohort());
                themes.setIdCohort(idCohortNew);
            }
            themes = em.merge(themes);
            if (idCohortOld != null && !idCohortOld.equals(idCohortNew)) {
                idCohortOld.getThemesCollection().remove(themes);
                idCohortOld = em.merge(idCohortOld);
            }
            if (idCohortNew != null && !idCohortNew.equals(idCohortOld)) {
                idCohortNew.getThemesCollection().add(themes);
                idCohortNew = em.merge(idCohortNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = themes.getIdTheme();
                if (findThemes(id) == null) {
                    throw new NonexistentEntityException("The themes with id " + id + " no longer exists.");
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
            Themes themes;
            try {
                themes = em.getReference(Themes.class, id);
                themes.getIdTheme();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The themes with id " + id + " no longer exists.", enfe);
            }
            Cohort idCohort = themes.getIdCohort();
            if (idCohort != null) {
                idCohort.getThemesCollection().remove(themes);
                idCohort = em.merge(idCohort);
            }
            em.remove(themes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Themes> findThemesEntities() {
        return findThemesEntities(true, -1, -1);
    }

    public List<Themes> findThemesEntities(int maxResults, int firstResult) {
        return findThemesEntities(false, maxResults, firstResult);
    }

    private List<Themes> findThemesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Themes.class));
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

    public Themes findThemes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Themes.class, id);
        } finally {
            em.close();
        }
    }

    public int getThemesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Themes> rt = cq.from(Themes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
