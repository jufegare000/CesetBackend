/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.edu.udea.ceset.dto.entities.*;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan
 */
public class AcademicactivityDAO implements Serializable {

    public AcademicactivityDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Academicactivity create(Academicactivity academicactivity) {
        List<Academicactivity> lAcad = null;

        if (academicactivity.getEstimatedCollection() == null) {
            academicactivity.setEstimatedCollection(new ArrayList<Estimated>());
        }
        if (academicactivity.getGroupeCollection() == null) {
            academicactivity.setGroupeCollection(new ArrayList<Groupe>());
        }
        if (academicactivity.getDiscountCollection() == null) {
            academicactivity.setDiscountCollection(new ArrayList<Discount>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Portafolio idPort = academicactivity.getIdPort();
            if (idPort != null) {
                idPort = em.getReference(idPort.getClass(), idPort.getId());
                academicactivity.setIdPort(idPort);
            }
            User idUser = academicactivity.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                academicactivity.setIdUser(idUser);
            }
            Collection<Estimated> attachedEstimatedCollection = new ArrayList<Estimated>();
            for (Estimated estimatedCollectionEstimatedToAttach : academicactivity.getEstimatedCollection()) {
                estimatedCollectionEstimatedToAttach = em.getReference(estimatedCollectionEstimatedToAttach.getClass(), estimatedCollectionEstimatedToAttach.getIdEstimated());
                attachedEstimatedCollection.add(estimatedCollectionEstimatedToAttach);
            }
            academicactivity.setEstimatedCollection(attachedEstimatedCollection);
            Collection<Groupe> attachedGroupeCollection = new ArrayList<Groupe>();
            for (Groupe groupeCollectionGroupeToAttach : academicactivity.getGroupeCollection()) {
                groupeCollectionGroupeToAttach = em.getReference(groupeCollectionGroupeToAttach.getClass(), groupeCollectionGroupeToAttach.getIdGroup());
                attachedGroupeCollection.add(groupeCollectionGroupeToAttach);
            }
            academicactivity.setGroupeCollection(attachedGroupeCollection);
            Collection<Discount> attachedDiscountCollection = new ArrayList<Discount>();
            for (Discount discountCollectionDiscountToAttach : academicactivity.getDiscountCollection()) {
                discountCollectionDiscountToAttach = em.getReference(discountCollectionDiscountToAttach.getClass(), discountCollectionDiscountToAttach.getIdDiscount());
                attachedDiscountCollection.add(discountCollectionDiscountToAttach);
            }
            academicactivity.setDiscountCollection(attachedDiscountCollection);
            em.persist(academicactivity);
            if (idPort != null) {
                idPort.getAcademicactivityCollection().add(academicactivity);
                idPort = em.merge(idPort);
            }
            if (idUser != null) {
                idUser.getAcademicactivityCollection().add(academicactivity);
                idUser = em.merge(idUser);
            }
            for (Estimated estimatedCollectionEstimated : academicactivity.getEstimatedCollection()) {
                Academicactivity oldIdAcadOfEstimatedCollectionEstimated = estimatedCollectionEstimated.getIdAcad();
                estimatedCollectionEstimated.setIdAcad(academicactivity);
                estimatedCollectionEstimated = em.merge(estimatedCollectionEstimated);
                if (oldIdAcadOfEstimatedCollectionEstimated != null) {
                    oldIdAcadOfEstimatedCollectionEstimated.getEstimatedCollection().remove(estimatedCollectionEstimated);
                    oldIdAcadOfEstimatedCollectionEstimated = em.merge(oldIdAcadOfEstimatedCollectionEstimated);
                }
            }
            for (Groupe groupeCollectionGroupe : academicactivity.getGroupeCollection()) {
                Academicactivity oldIdAcadOfGroupeCollectionGroupe = groupeCollectionGroupe.getIdAcad();
                groupeCollectionGroupe.setIdAcad(academicactivity);
                groupeCollectionGroupe = em.merge(groupeCollectionGroupe);
                if (oldIdAcadOfGroupeCollectionGroupe != null) {
                    oldIdAcadOfGroupeCollectionGroupe.getGroupeCollection().remove(groupeCollectionGroupe);
                    oldIdAcadOfGroupeCollectionGroupe = em.merge(oldIdAcadOfGroupeCollectionGroupe);
                }
            }
            for (Discount discountCollectionDiscount : academicactivity.getDiscountCollection()) {
                Academicactivity oldIdAcadOfDiscountCollectionDiscount = discountCollectionDiscount.getIdAcad();
                discountCollectionDiscount.setIdAcad(academicactivity);
                discountCollectionDiscount = em.merge(discountCollectionDiscount);
                if (oldIdAcadOfDiscountCollectionDiscount != null) {
                    oldIdAcadOfDiscountCollectionDiscount.getDiscountCollection().remove(discountCollectionDiscount);
                    oldIdAcadOfDiscountCollectionDiscount = em.merge(oldIdAcadOfDiscountCollectionDiscount);
                }
            }
            em.getTransaction().commit();
        } finally {
            lAcad = em.createNamedQuery("Academicactivity.findLast")
                    .setMaxResults(1)
                    .getResultList(); // retorna actividad recién creada
            if (em != null) {
                em.close();
            }
        }
        return lAcad.get(0);
    }

    public void edit(Academicactivity academicactivity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Academicactivity persistentAcademicactivity = em.find(Academicactivity.class, academicactivity.getIdAcad());
            Portafolio idPortOld = persistentAcademicactivity.getIdPort();
            Portafolio idPortNew = academicactivity.getIdPort();
            User idUserOld = persistentAcademicactivity.getIdUser();
            User idUserNew = academicactivity.getIdUser();
            Collection<Estimated> estimatedCollectionOld = persistentAcademicactivity.getEstimatedCollection();
            Collection<Estimated> estimatedCollectionNew = academicactivity.getEstimatedCollection();
            Collection<Groupe> groupeCollectionOld = persistentAcademicactivity.getGroupeCollection();
            Collection<Groupe> groupeCollectionNew = academicactivity.getGroupeCollection();
            Collection<Discount> discountCollectionOld = persistentAcademicactivity.getDiscountCollection();
            Collection<Discount> discountCollectionNew = academicactivity.getDiscountCollection();
            if (idPortNew != null) {
                idPortNew = em.getReference(idPortNew.getClass(), idPortNew.getId());
                academicactivity.setIdPort(idPortNew);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                academicactivity.setIdUser(idUserNew);
            }
            Collection<Estimated> attachedEstimatedCollectionNew = new ArrayList<Estimated>();
            for (Estimated estimatedCollectionNewEstimatedToAttach : estimatedCollectionNew) {
                estimatedCollectionNewEstimatedToAttach = em.getReference(estimatedCollectionNewEstimatedToAttach.getClass(), estimatedCollectionNewEstimatedToAttach.getIdEstimated());
                attachedEstimatedCollectionNew.add(estimatedCollectionNewEstimatedToAttach);
            }
            estimatedCollectionNew = attachedEstimatedCollectionNew;
            academicactivity.setEstimatedCollection(estimatedCollectionNew);
            Collection<Groupe> attachedGroupeCollectionNew = new ArrayList<Groupe>();
            for (Groupe groupeCollectionNewGroupeToAttach : groupeCollectionNew) {
                groupeCollectionNewGroupeToAttach = em.getReference(groupeCollectionNewGroupeToAttach.getClass(), groupeCollectionNewGroupeToAttach.getIdGroup());
                attachedGroupeCollectionNew.add(groupeCollectionNewGroupeToAttach);
            }
            groupeCollectionNew = attachedGroupeCollectionNew;
            academicactivity.setGroupeCollection(groupeCollectionNew);
            Collection<Discount> attachedDiscountCollectionNew = new ArrayList<Discount>();
            for (Discount discountCollectionNewDiscountToAttach : discountCollectionNew) {
                discountCollectionNewDiscountToAttach = em.getReference(discountCollectionNewDiscountToAttach.getClass(), discountCollectionNewDiscountToAttach.getIdDiscount());
                attachedDiscountCollectionNew.add(discountCollectionNewDiscountToAttach);
            }
            discountCollectionNew = attachedDiscountCollectionNew;
            academicactivity.setDiscountCollection(discountCollectionNew);
            academicactivity = em.merge(academicactivity);
            if (idPortOld != null && !idPortOld.equals(idPortNew)) {
                idPortOld.getAcademicactivityCollection().remove(academicactivity);
                idPortOld = em.merge(idPortOld);
            }
            if (idPortNew != null && !idPortNew.equals(idPortOld)) {
                idPortNew.getAcademicactivityCollection().add(academicactivity);
                idPortNew = em.merge(idPortNew);
            }
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getAcademicactivityCollection().remove(academicactivity);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getAcademicactivityCollection().add(academicactivity);
                idUserNew = em.merge(idUserNew);
            }
            for (Estimated estimatedCollectionOldEstimated : estimatedCollectionOld) {
                if (!estimatedCollectionNew.contains(estimatedCollectionOldEstimated)) {
                    estimatedCollectionOldEstimated.setIdAcad(null);
                    estimatedCollectionOldEstimated = em.merge(estimatedCollectionOldEstimated);
                }
            }
            for (Estimated estimatedCollectionNewEstimated : estimatedCollectionNew) {
                if (!estimatedCollectionOld.contains(estimatedCollectionNewEstimated)) {
                    Academicactivity oldIdAcadOfEstimatedCollectionNewEstimated = estimatedCollectionNewEstimated.getIdAcad();
                    estimatedCollectionNewEstimated.setIdAcad(academicactivity);
                    estimatedCollectionNewEstimated = em.merge(estimatedCollectionNewEstimated);
                    if (oldIdAcadOfEstimatedCollectionNewEstimated != null && !oldIdAcadOfEstimatedCollectionNewEstimated.equals(academicactivity)) {
                        oldIdAcadOfEstimatedCollectionNewEstimated.getEstimatedCollection().remove(estimatedCollectionNewEstimated);
                        oldIdAcadOfEstimatedCollectionNewEstimated = em.merge(oldIdAcadOfEstimatedCollectionNewEstimated);
                    }
                }
            }
            for (Groupe groupeCollectionOldGroupe : groupeCollectionOld) {
                if (!groupeCollectionNew.contains(groupeCollectionOldGroupe)) {
                    groupeCollectionOldGroupe.setIdAcad(null);
                    groupeCollectionOldGroupe = em.merge(groupeCollectionOldGroupe);
                }
            }
            for (Groupe groupeCollectionNewGroupe : groupeCollectionNew) {
                if (!groupeCollectionOld.contains(groupeCollectionNewGroupe)) {
                    Academicactivity oldIdAcadOfGroupeCollectionNewGroupe = groupeCollectionNewGroupe.getIdAcad();
                    groupeCollectionNewGroupe.setIdAcad(academicactivity);
                    groupeCollectionNewGroupe = em.merge(groupeCollectionNewGroupe);
                    if (oldIdAcadOfGroupeCollectionNewGroupe != null && !oldIdAcadOfGroupeCollectionNewGroupe.equals(academicactivity)) {
                        oldIdAcadOfGroupeCollectionNewGroupe.getGroupeCollection().remove(groupeCollectionNewGroupe);
                        oldIdAcadOfGroupeCollectionNewGroupe = em.merge(oldIdAcadOfGroupeCollectionNewGroupe);
                    }
                }
            }
            for (Discount discountCollectionOldDiscount : discountCollectionOld) {
                if (!discountCollectionNew.contains(discountCollectionOldDiscount)) {
                    discountCollectionOldDiscount.setIdAcad(null);
                    discountCollectionOldDiscount = em.merge(discountCollectionOldDiscount);
                }
            }
            for (Discount discountCollectionNewDiscount : discountCollectionNew) {
                if (!discountCollectionOld.contains(discountCollectionNewDiscount)) {
                    Academicactivity oldIdAcadOfDiscountCollectionNewDiscount = discountCollectionNewDiscount.getIdAcad();
                    discountCollectionNewDiscount.setIdAcad(academicactivity);
                    discountCollectionNewDiscount = em.merge(discountCollectionNewDiscount);
                    if (oldIdAcadOfDiscountCollectionNewDiscount != null && !oldIdAcadOfDiscountCollectionNewDiscount.equals(academicactivity)) {
                        oldIdAcadOfDiscountCollectionNewDiscount.getDiscountCollection().remove(discountCollectionNewDiscount);
                        oldIdAcadOfDiscountCollectionNewDiscount = em.merge(oldIdAcadOfDiscountCollectionNewDiscount);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = academicactivity.getIdAcad();
                if (findAcademicactivity(id) == null) {
                    throw new NonexistentEntityException("The academicactivity with id " + id + " no longer exists.");
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
            Academicactivity academicactivity;
            try {
                academicactivity = em.getReference(Academicactivity.class, id);
                academicactivity.getIdAcad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The academicactivity with id " + id + " no longer exists.", enfe);
            }
            Portafolio idPort = academicactivity.getIdPort();
            if (idPort != null) {
                idPort.getAcademicactivityCollection().remove(academicactivity);
                idPort = em.merge(idPort);
            }
            User idUser = academicactivity.getIdUser();
            if (idUser != null) {
                idUser.getAcademicactivityCollection().remove(academicactivity);
                idUser = em.merge(idUser);
            }
            Collection<Estimated> estimatedCollection = academicactivity.getEstimatedCollection();
            for (Estimated estimatedCollectionEstimated : estimatedCollection) {
                estimatedCollectionEstimated.setIdAcad(null);
                estimatedCollectionEstimated = em.merge(estimatedCollectionEstimated);
            }
            Collection<Groupe> groupeCollection = academicactivity.getGroupeCollection();
            for (Groupe groupeCollectionGroupe : groupeCollection) {
                groupeCollectionGroupe.setIdAcad(null);
                groupeCollectionGroupe = em.merge(groupeCollectionGroupe);
            }
            Collection<Discount> discountCollection = academicactivity.getDiscountCollection();
            for (Discount discountCollectionDiscount : discountCollection) {
                discountCollectionDiscount.setIdAcad(null);
                discountCollectionDiscount = em.merge(discountCollectionDiscount);
            }
            em.remove(academicactivity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Academicactivity> findAcademicactivityEntities() {
        return findAcademicactivityEntities(true, -1, -1);
    }

    public List<Academicactivity> findAcademicactivityEntities(int maxResults, int firstResult) {
        return findAcademicactivityEntities(false, maxResults, firstResult);
    }

    private List<Academicactivity> findAcademicactivityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Academicactivity.class));
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

    public Academicactivity findAcademicactivity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Academicactivity.class, id);
        } finally {
            em.close();
        }
    }

    public int getAcademicactivityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Academicactivity> rt = cq.from(Academicactivity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
