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
import co.edu.udea.ceset.dto.entities.Groupe;
import co.edu.udea.ceset.dto.entities.Themes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan
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
            Groupe idGroup = themes.getIdGroup();
            if (idGroup != null) {
                idGroup = em.getReference(idGroup.getClass(), idGroup.getIdGroup());
                themes.setIdGroup(idGroup);
            }
            em.persist(themes);
            if (idGroup != null) {
                idGroup.getThemesCollection().add(themes);
                idGroup = em.merge(idGroup);
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
            Groupe idGroupOld = persistentThemes.getIdGroup();
            Groupe idGroupNew = themes.getIdGroup();
            if (idGroupNew != null) {
                idGroupNew = em.getReference(idGroupNew.getClass(), idGroupNew.getIdGroup());
                themes.setIdGroup(idGroupNew);
            }
            themes = em.merge(themes);
            if (idGroupOld != null && !idGroupOld.equals(idGroupNew)) {
                idGroupOld.getThemesCollection().remove(themes);
                idGroupOld = em.merge(idGroupOld);
            }
            if (idGroupNew != null && !idGroupNew.equals(idGroupOld)) {
                idGroupNew.getThemesCollection().add(themes);
                idGroupNew = em.merge(idGroupNew);
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
            Groupe idGroup = themes.getIdGroup();
            if (idGroup != null) {
                idGroup.getThemesCollection().remove(themes);
                idGroup = em.merge(idGroup);
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
