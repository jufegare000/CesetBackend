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
import co.edu.udea.ceset.dto.entities.Estimated;
import co.edu.udea.ceset.dto.entities.Estimatedbyexpenditure;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan
 */
public class EstimatedDAO implements Serializable {

    public EstimatedDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Integer create(Estimated estimated) {
        List<Estimated> lEstmd = null;
        if (estimated.getEstimatedbyexpenditureCollection() == null) {
            estimated.setEstimatedbyexpenditureCollection(new ArrayList<Estimatedbyexpenditure>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Academicactivity idAcad = estimated.getIdAcad();
            if (idAcad != null) {
                idAcad = em.getReference(idAcad.getClass(), idAcad.getIdAcad());
                estimated.setIdAcad(idAcad);
            }
            Collection<Estimatedbyexpenditure> attachedEstimatedbyexpenditureCollection = new ArrayList<Estimatedbyexpenditure>();
            for (Estimatedbyexpenditure estimatedbyexpenditureCollectionEstimatedbyexpenditureToAttach : estimated.getEstimatedbyexpenditureCollection()) {
                estimatedbyexpenditureCollectionEstimatedbyexpenditureToAttach = em.getReference(estimatedbyexpenditureCollectionEstimatedbyexpenditureToAttach.getClass(), estimatedbyexpenditureCollectionEstimatedbyexpenditureToAttach.getId());
                attachedEstimatedbyexpenditureCollection.add(estimatedbyexpenditureCollectionEstimatedbyexpenditureToAttach);
            }
            estimated.setEstimatedbyexpenditureCollection(attachedEstimatedbyexpenditureCollection);
            em.persist(estimated);
            if (idAcad != null) {
                idAcad.getEstimatedCollection().add(estimated);
                idAcad = em.merge(idAcad);
            }
            for (Estimatedbyexpenditure estimatedbyexpenditureCollectionEstimatedbyexpenditure : estimated.getEstimatedbyexpenditureCollection()) {
                Estimated oldIdEstimatedOfEstimatedbyexpenditureCollectionEstimatedbyexpenditure = estimatedbyexpenditureCollectionEstimatedbyexpenditure.getIdEstimated();
                estimatedbyexpenditureCollectionEstimatedbyexpenditure.setIdEstimated(estimated);
                estimatedbyexpenditureCollectionEstimatedbyexpenditure = em.merge(estimatedbyexpenditureCollectionEstimatedbyexpenditure);
                if (oldIdEstimatedOfEstimatedbyexpenditureCollectionEstimatedbyexpenditure != null) {
                    oldIdEstimatedOfEstimatedbyexpenditureCollectionEstimatedbyexpenditure.getEstimatedbyexpenditureCollection().remove(estimatedbyexpenditureCollectionEstimatedbyexpenditure);
                    oldIdEstimatedOfEstimatedbyexpenditureCollectionEstimatedbyexpenditure = em.merge(oldIdEstimatedOfEstimatedbyexpenditureCollectionEstimatedbyexpenditure);
                }
            }
            em.getTransaction().commit();
        } finally {
            lEstmd = em.createNamedQuery("Estimated.findLast")
                    .setMaxResults(1)
                    .getResultList(); // retorna actividad recién creada
            if (em != null) {
                em.close();
            }
        }
        return lEstmd.get(0).getIdEstimated();
    }

    public void edit(Estimated estimated) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estimated persistentEstimated = em.find(Estimated.class, estimated.getIdEstimated());
            Academicactivity idAcadOld = persistentEstimated.getIdAcad();
            Academicactivity idAcadNew = estimated.getIdAcad();
            Collection<Estimatedbyexpenditure> estimatedbyexpenditureCollectionOld = persistentEstimated.getEstimatedbyexpenditureCollection();
            Collection<Estimatedbyexpenditure> estimatedbyexpenditureCollectionNew = estimated.getEstimatedbyexpenditureCollection();
            if (idAcadNew != null) {
                idAcadNew = em.getReference(idAcadNew.getClass(), idAcadNew.getIdAcad());
                estimated.setIdAcad(idAcadNew);
            }
            Collection<Estimatedbyexpenditure> attachedEstimatedbyexpenditureCollectionNew = new ArrayList<Estimatedbyexpenditure>();
            for (Estimatedbyexpenditure estimatedbyexpenditureCollectionNewEstimatedbyexpenditureToAttach : estimatedbyexpenditureCollectionNew) {
                estimatedbyexpenditureCollectionNewEstimatedbyexpenditureToAttach = em.getReference(estimatedbyexpenditureCollectionNewEstimatedbyexpenditureToAttach.getClass(), estimatedbyexpenditureCollectionNewEstimatedbyexpenditureToAttach.getId());
                attachedEstimatedbyexpenditureCollectionNew.add(estimatedbyexpenditureCollectionNewEstimatedbyexpenditureToAttach);
            }
            estimatedbyexpenditureCollectionNew = attachedEstimatedbyexpenditureCollectionNew;
            estimated.setEstimatedbyexpenditureCollection(estimatedbyexpenditureCollectionNew);
            estimated = em.merge(estimated);
            if (idAcadOld != null && !idAcadOld.equals(idAcadNew)) {
                idAcadOld.getEstimatedCollection().remove(estimated);
                idAcadOld = em.merge(idAcadOld);
            }
            if (idAcadNew != null && !idAcadNew.equals(idAcadOld)) {
                idAcadNew.getEstimatedCollection().add(estimated);
                idAcadNew = em.merge(idAcadNew);
            }
            for (Estimatedbyexpenditure estimatedbyexpenditureCollectionOldEstimatedbyexpenditure : estimatedbyexpenditureCollectionOld) {
                if (!estimatedbyexpenditureCollectionNew.contains(estimatedbyexpenditureCollectionOldEstimatedbyexpenditure)) {
                    estimatedbyexpenditureCollectionOldEstimatedbyexpenditure.setIdEstimated(null);
                    estimatedbyexpenditureCollectionOldEstimatedbyexpenditure = em.merge(estimatedbyexpenditureCollectionOldEstimatedbyexpenditure);
                }
            }
            for (Estimatedbyexpenditure estimatedbyexpenditureCollectionNewEstimatedbyexpenditure : estimatedbyexpenditureCollectionNew) {
                if (!estimatedbyexpenditureCollectionOld.contains(estimatedbyexpenditureCollectionNewEstimatedbyexpenditure)) {
                    Estimated oldIdEstimatedOfEstimatedbyexpenditureCollectionNewEstimatedbyexpenditure = estimatedbyexpenditureCollectionNewEstimatedbyexpenditure.getIdEstimated();
                    estimatedbyexpenditureCollectionNewEstimatedbyexpenditure.setIdEstimated(estimated);
                    estimatedbyexpenditureCollectionNewEstimatedbyexpenditure = em.merge(estimatedbyexpenditureCollectionNewEstimatedbyexpenditure);
                    if (oldIdEstimatedOfEstimatedbyexpenditureCollectionNewEstimatedbyexpenditure != null && !oldIdEstimatedOfEstimatedbyexpenditureCollectionNewEstimatedbyexpenditure.equals(estimated)) {
                        oldIdEstimatedOfEstimatedbyexpenditureCollectionNewEstimatedbyexpenditure.getEstimatedbyexpenditureCollection().remove(estimatedbyexpenditureCollectionNewEstimatedbyexpenditure);
                        oldIdEstimatedOfEstimatedbyexpenditureCollectionNewEstimatedbyexpenditure = em.merge(oldIdEstimatedOfEstimatedbyexpenditureCollectionNewEstimatedbyexpenditure);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estimated.getIdEstimated();
                if (findEstimated(id) == null) {
                    throw new NonexistentEntityException("The estimated with id " + id + " no longer exists.");
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
            Estimated estimated;
            try {
                estimated = em.getReference(Estimated.class, id);
                estimated.getIdEstimated();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estimated with id " + id + " no longer exists.", enfe);
            }
            Academicactivity idAcad = estimated.getIdAcad();
            if (idAcad != null) {
                idAcad.getEstimatedCollection().remove(estimated);
                idAcad = em.merge(idAcad);
            }
            Collection<Estimatedbyexpenditure> estimatedbyexpenditureCollection = estimated.getEstimatedbyexpenditureCollection();
            for (Estimatedbyexpenditure estimatedbyexpenditureCollectionEstimatedbyexpenditure : estimatedbyexpenditureCollection) {
                estimatedbyexpenditureCollectionEstimatedbyexpenditure.setIdEstimated(null);
                estimatedbyexpenditureCollectionEstimatedbyexpenditure = em.merge(estimatedbyexpenditureCollectionEstimatedbyexpenditure);
            }
            em.remove(estimated);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estimated> findEstimatedEntities() {
        return findEstimatedEntities(true, -1, -1);
    }

    public List<Estimated> findEstimatedEntities(int maxResults, int firstResult) {
        return findEstimatedEntities(false, maxResults, firstResult);
    }

    private List<Estimated> findEstimatedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estimated.class));
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

    public Estimated findEstimated(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estimated.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstimatedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estimated> rt = cq.from(Estimated.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
