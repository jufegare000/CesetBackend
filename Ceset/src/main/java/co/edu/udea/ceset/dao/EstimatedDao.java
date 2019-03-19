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
import co.edu.udea.ceset.dto.Academicactivity;
import co.edu.udea.ceset.dto.Estimated;
import co.edu.udea.ceset.dto.Estimatedbyitem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class EstimatedDao implements Serializable {

    public EstimatedDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Estimated create(Estimated estimated) {
        List<Estimated> lest = null;
        if (estimated.getEstimatedbyitemCollection() == null) {
            estimated.setEstimatedbyitemCollection(new ArrayList<Estimatedbyitem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            //Academicactivity idAcad = estimated.getIdAcad();
/*            if (idAcad != null) {
                idAcad = em.getReference(idAcad.getClass(), idAcad.getIdAcad());
                estimated.setIdAcad(idAcad);
            }*/
            Collection<Estimatedbyitem> attachedEstimatedbyitemCollection = new ArrayList<Estimatedbyitem>();
            for (Estimatedbyitem estimatedbyitemCollectionEstimatedbyitemToAttach : estimated.getEstimatedbyitemCollection()) {
                estimatedbyitemCollectionEstimatedbyitemToAttach = em.getReference(estimatedbyitemCollectionEstimatedbyitemToAttach.getClass(), estimatedbyitemCollectionEstimatedbyitemToAttach.getId());
                attachedEstimatedbyitemCollection.add(estimatedbyitemCollectionEstimatedbyitemToAttach);
            }
            estimated.setEstimatedbyitemCollection(attachedEstimatedbyitemCollection);
            em.persist(estimated);
/*            if (idAcad != null) {
                idAcad.setEstimated(estimated);
                idAcad = em.merge(idAcad);
            }*/
            for (Estimatedbyitem estimatedbyitemCollectionEstimatedbyitem : estimated.getEstimatedbyitemCollection()) {
                Estimated oldIdEstimatedOfEstimatedbyitemCollectionEstimatedbyitem = estimatedbyitemCollectionEstimatedbyitem.getIdEstimated();
                estimatedbyitemCollectionEstimatedbyitem.setIdEstimated(estimated);
                estimatedbyitemCollectionEstimatedbyitem = em.merge(estimatedbyitemCollectionEstimatedbyitem);
                if (oldIdEstimatedOfEstimatedbyitemCollectionEstimatedbyitem != null) {
                    oldIdEstimatedOfEstimatedbyitemCollectionEstimatedbyitem.getEstimatedbyitemCollection().remove(estimatedbyitemCollectionEstimatedbyitem);
                    oldIdEstimatedOfEstimatedbyitemCollectionEstimatedbyitem = em.merge(oldIdEstimatedOfEstimatedbyitemCollectionEstimatedbyitem);
                }
            }
            em.getTransaction().commit();
        } finally {
              lest = em.createNamedQuery("Estimated.findByIdAcad")
                    .setParameter("document", estimated.getIdAcad())
                    .getResultList(); // Retorna la persona recién creada
                                      // Para asigmarlo al usuario a crear
  //          
            if (em != null) {
                em.close();
            }
        }
        return lest.get(0);
    }

    public void edit(Estimated estimated) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estimated persistentEstimated = em.find(Estimated.class, estimated.getIdEstimated());
//            Academicactivity idAcadOld = persistentEstimated.getIdAcad();
//            Academicactivity idAcadNew = estimated.getIdAcad();
            Collection<Estimatedbyitem> estimatedbyitemCollectionOld = persistentEstimated.getEstimatedbyitemCollection();
            Collection<Estimatedbyitem> estimatedbyitemCollectionNew = estimated.getEstimatedbyitemCollection();
/*            if (idAcadNew != null) {
                idAcadNew = em.getReference(idAcadNew.getClass(), idAcadNew.getIdAcad());
                estimated.setIdAcad(idAcadNew);
            }*/
            Collection<Estimatedbyitem> attachedEstimatedbyitemCollectionNew = new ArrayList<Estimatedbyitem>();
            for (Estimatedbyitem estimatedbyitemCollectionNewEstimatedbyitemToAttach : estimatedbyitemCollectionNew) {
                estimatedbyitemCollectionNewEstimatedbyitemToAttach = em.getReference(estimatedbyitemCollectionNewEstimatedbyitemToAttach.getClass(), estimatedbyitemCollectionNewEstimatedbyitemToAttach.getId());
                attachedEstimatedbyitemCollectionNew.add(estimatedbyitemCollectionNewEstimatedbyitemToAttach);
            }
            estimatedbyitemCollectionNew = attachedEstimatedbyitemCollectionNew;
            estimated.setEstimatedbyitemCollection(estimatedbyitemCollectionNew);
            estimated = em.merge(estimated);
            /*
            if (idAcadOld != null && !idAcadOld.equals(idAcadNew)) {
                idAcadOld.getEstimated().remove(estimated);
                idAcadOld = em.merge(idAcadOld);
            }
            if (idAcadNew != null && !idAcadNew.equals(idAcadOld)) {
                idAcadNew.getEstimated().add(estimated);
                idAcadNew = em.merge(idAcadNew);
            }
*/
            for (Estimatedbyitem estimatedbyitemCollectionOldEstimatedbyitem : estimatedbyitemCollectionOld) {
                if (!estimatedbyitemCollectionNew.contains(estimatedbyitemCollectionOldEstimatedbyitem)) {
                    estimatedbyitemCollectionOldEstimatedbyitem.setIdEstimated(null);
                    estimatedbyitemCollectionOldEstimatedbyitem = em.merge(estimatedbyitemCollectionOldEstimatedbyitem);
                }
            }
            for (Estimatedbyitem estimatedbyitemCollectionNewEstimatedbyitem : estimatedbyitemCollectionNew) {
                if (!estimatedbyitemCollectionOld.contains(estimatedbyitemCollectionNewEstimatedbyitem)) {
                    Estimated oldIdEstimatedOfEstimatedbyitemCollectionNewEstimatedbyitem = estimatedbyitemCollectionNewEstimatedbyitem.getIdEstimated();
                    estimatedbyitemCollectionNewEstimatedbyitem.setIdEstimated(estimated);
                    estimatedbyitemCollectionNewEstimatedbyitem = em.merge(estimatedbyitemCollectionNewEstimatedbyitem);
                    if (oldIdEstimatedOfEstimatedbyitemCollectionNewEstimatedbyitem != null && !oldIdEstimatedOfEstimatedbyitemCollectionNewEstimatedbyitem.equals(estimated)) {
                        oldIdEstimatedOfEstimatedbyitemCollectionNewEstimatedbyitem.getEstimatedbyitemCollection().remove(estimatedbyitemCollectionNewEstimatedbyitem);
                        oldIdEstimatedOfEstimatedbyitemCollectionNewEstimatedbyitem = em.merge(oldIdEstimatedOfEstimatedbyitemCollectionNewEstimatedbyitem);
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
//            Academicactivity idAcad = estimated.getIdAcad();
            /*
            if (idAcad != null) {
                idAcad.getEstimated().remove(estimated);
                idAcad = em.merge(idAcad);
            }*/
            Collection<Estimatedbyitem> estimatedbyitemCollection = estimated.getEstimatedbyitemCollection();
            for (Estimatedbyitem estimatedbyitemCollectionEstimatedbyitem : estimatedbyitemCollection) {
                estimatedbyitemCollectionEstimatedbyitem.setIdEstimated(null);
                estimatedbyitemCollectionEstimatedbyitem = em.merge(estimatedbyitemCollectionEstimatedbyitem);
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
