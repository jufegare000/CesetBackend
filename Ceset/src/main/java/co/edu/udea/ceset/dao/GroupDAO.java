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
import co.edu.udea.ceset.dto.entities.Cohort;
import co.edu.udea.ceset.dto.entities.Groupe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan
 */
public class GroupDAO implements Serializable {

    public GroupDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Groupe groupe) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cohort idCohort = groupe.getIdCohort();
            if (idCohort != null) {
                idCohort = em.getReference(idCohort.getClass(), idCohort.getIdCohort());
                groupe.setIdCohort(idCohort);
            }
            em.persist(groupe);
            if (idCohort != null) {
                idCohort.getGroupeCollection().add(groupe);
                idCohort = em.merge(idCohort);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Groupe groupe) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Groupe persistentGroupe = em.find(Groupe.class, groupe.getIdGroup());
            Cohort idCohortOld = persistentGroupe.getIdCohort();
            Cohort idCohortNew = groupe.getIdCohort();
            if (idCohortNew != null) {
                idCohortNew = em.getReference(idCohortNew.getClass(), idCohortNew.getIdCohort());
                groupe.setIdCohort(idCohortNew);
            }
            groupe = em.merge(groupe);
            if (idCohortOld != null && !idCohortOld.equals(idCohortNew)) {
                idCohortOld.getGroupeCollection().remove(groupe);
                idCohortOld = em.merge(idCohortOld);
            }
            if (idCohortNew != null && !idCohortNew.equals(idCohortOld)) {
                idCohortNew.getGroupeCollection().add(groupe);
                idCohortNew = em.merge(idCohortNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = groupe.getIdGroup();
                if (findGroupe(id) == null) {
                    throw new NonexistentEntityException("The groupe with id " + id + " no longer exists.");
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
            Groupe groupe;
            try {
                groupe = em.getReference(Groupe.class, id);
                groupe.getIdGroup();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The groupe with id " + id + " no longer exists.", enfe);
            }
            Cohort idCohort = groupe.getIdCohort();
            if (idCohort != null) {
                idCohort.getGroupeCollection().remove(groupe);
                idCohort = em.merge(idCohort);
            }
            em.remove(groupe);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Groupe> findGroupeEntities() {
        return findGroupeEntities(true, -1, -1);
    }

    public List<Groupe> findGroupeEntities(int maxResults, int firstResult) {
        return findGroupeEntities(false, maxResults, firstResult);
    }

    private List<Groupe> findGroupeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Groupe.class));
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

    public Groupe findGroupe(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Groupe.class, id);
        } finally {
            em.close();
        }
    }

    public int getGroupeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Groupe> rt = cq.from(Groupe.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
