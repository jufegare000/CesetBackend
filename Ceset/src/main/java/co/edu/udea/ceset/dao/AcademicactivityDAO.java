/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.edu.udea.ceset.dto.Academicactivity;
import co.edu.udea.ceset.dto.Academicactivity_;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.udea.ceset.dto.User;
import co.edu.udea.ceset.dto.Budget;
import java.util.ArrayList;
import java.util.Collection;
import co.edu.udea.ceset.dto.Estimated;
import co.edu.udea.ceset.dto.Cohort;
import co.edu.udea.ceset.dto.Discount;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.Join;


/**
 *
 * @author jufeg
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
        if (academicactivity.getBudgetCollection() == null) {
            academicactivity.setBudgetCollection(new ArrayList<Budget>());
        }
        if (academicactivity.getEstimatedCollection() == null) {
            academicactivity.setEstimatedCollection(new ArrayList<Estimated>());
        }
        if (academicactivity.getCohortCollection() == null) {
            academicactivity.setCohortCollection(new ArrayList<Cohort>());
        }
        if (academicactivity.getDiscountCollection() == null) {
            academicactivity.setDiscountCollection(new ArrayList<Discount>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User idUser = academicactivity.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                academicactivity.setIdUser(idUser);
            }
            Collection<Budget> attachedBudgetCollection = new ArrayList<Budget>();
            for (Budget budgetCollectionBudgetToAttach : academicactivity.getBudgetCollection()) {
                budgetCollectionBudgetToAttach = em.getReference(budgetCollectionBudgetToAttach.getClass(), budgetCollectionBudgetToAttach.getIdBudget());
                attachedBudgetCollection.add(budgetCollectionBudgetToAttach);
            }
            academicactivity.setBudgetCollection(attachedBudgetCollection);
            Collection<Estimated> attachedEstimatedCollection = new ArrayList<Estimated>();
            for (Estimated estimatedCollectionEstimatedToAttach : academicactivity.getEstimatedCollection()) {
                estimatedCollectionEstimatedToAttach = em.getReference(estimatedCollectionEstimatedToAttach.getClass(), estimatedCollectionEstimatedToAttach.getIdEstimated());
                attachedEstimatedCollection.add(estimatedCollectionEstimatedToAttach);
            }
            academicactivity.setEstimatedCollection(attachedEstimatedCollection);
            Collection<Cohort> attachedCohortCollection = new ArrayList<Cohort>();
            for (Cohort cohortCollectionCohortToAttach : academicactivity.getCohortCollection()) {
                cohortCollectionCohortToAttach = em.getReference(cohortCollectionCohortToAttach.getClass(), cohortCollectionCohortToAttach.getIdCohort());
                attachedCohortCollection.add(cohortCollectionCohortToAttach);
            }
            academicactivity.setCohortCollection(attachedCohortCollection);
            Collection<Discount> attachedDiscountCollection = new ArrayList<Discount>();
            for (Discount discountCollectionDiscountToAttach : academicactivity.getDiscountCollection()) {
                discountCollectionDiscountToAttach = em.getReference(discountCollectionDiscountToAttach.getClass(), discountCollectionDiscountToAttach.getIdDiscount());
                attachedDiscountCollection.add(discountCollectionDiscountToAttach);
            }
            academicactivity.setDiscountCollection(attachedDiscountCollection);
            em.persist(academicactivity);
            if (idUser != null) {
                idUser.getAcademicactivityCollection().add(academicactivity);
                idUser = em.merge(idUser);
            }
            for (Budget budgetCollectionBudget : academicactivity.getBudgetCollection()) {
                Academicactivity oldIdActivityOfBudgetCollectionBudget = budgetCollectionBudget.getIdActivity();
                budgetCollectionBudget.setIdActivity(academicactivity);
                budgetCollectionBudget = em.merge(budgetCollectionBudget);
                if (oldIdActivityOfBudgetCollectionBudget != null) {
                    oldIdActivityOfBudgetCollectionBudget.getBudgetCollection().remove(budgetCollectionBudget);
                    oldIdActivityOfBudgetCollectionBudget = em.merge(oldIdActivityOfBudgetCollectionBudget);
                }
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
            for (Cohort cohortCollectionCohort : academicactivity.getCohortCollection()) {
                Academicactivity oldIdActivityOfCohortCollectionCohort = cohortCollectionCohort.getIdActivity();
                cohortCollectionCohort.setIdActivity(academicactivity);
                cohortCollectionCohort = em.merge(cohortCollectionCohort);
                if (oldIdActivityOfCohortCollectionCohort != null) {
                    oldIdActivityOfCohortCollectionCohort.getCohortCollection().remove(cohortCollectionCohort);
                    oldIdActivityOfCohortCollectionCohort = em.merge(oldIdActivityOfCohortCollectionCohort);
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
            lAcad = em.createNamedQuery("Academicactivity.findByIdAcad")
                    .setParameter("idAcad", academicactivity.getIdAcad())
                    .getResultList(); // Retorna la persona recién creada
                                      // Para asigmarlo al usuario a crear
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
            User idUserOld = persistentAcademicactivity.getIdUser();
            User idUserNew = academicactivity.getIdUser();
            Collection<Budget> budgetCollectionOld = persistentAcademicactivity.getBudgetCollection();
            Collection<Budget> budgetCollectionNew = academicactivity.getBudgetCollection();
            Collection<Estimated> estimatedCollectionOld = persistentAcademicactivity.getEstimatedCollection();
            Collection<Estimated> estimatedCollectionNew = academicactivity.getEstimatedCollection();
            Collection<Cohort> cohortCollectionOld = persistentAcademicactivity.getCohortCollection();
            Collection<Cohort> cohortCollectionNew = academicactivity.getCohortCollection();
            Collection<Discount> discountCollectionOld = persistentAcademicactivity.getDiscountCollection();
            Collection<Discount> discountCollectionNew = academicactivity.getDiscountCollection();
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                academicactivity.setIdUser(idUserNew);
            }
            Collection<Budget> attachedBudgetCollectionNew = new ArrayList<Budget>();
            for (Budget budgetCollectionNewBudgetToAttach : budgetCollectionNew) {
                budgetCollectionNewBudgetToAttach = em.getReference(budgetCollectionNewBudgetToAttach.getClass(), budgetCollectionNewBudgetToAttach.getIdBudget());
                attachedBudgetCollectionNew.add(budgetCollectionNewBudgetToAttach);
            }
            budgetCollectionNew = attachedBudgetCollectionNew;
            academicactivity.setBudgetCollection(budgetCollectionNew);
            Collection<Estimated> attachedEstimatedCollectionNew = new ArrayList<Estimated>();
            for (Estimated estimatedCollectionNewEstimatedToAttach : estimatedCollectionNew) {
                estimatedCollectionNewEstimatedToAttach = em.getReference(estimatedCollectionNewEstimatedToAttach.getClass(), estimatedCollectionNewEstimatedToAttach.getIdEstimated());
                attachedEstimatedCollectionNew.add(estimatedCollectionNewEstimatedToAttach);
            }
            estimatedCollectionNew = attachedEstimatedCollectionNew;
            academicactivity.setEstimatedCollection(estimatedCollectionNew);
            Collection<Cohort> attachedCohortCollectionNew = new ArrayList<Cohort>();
            for (Cohort cohortCollectionNewCohortToAttach : cohortCollectionNew) {
                cohortCollectionNewCohortToAttach = em.getReference(cohortCollectionNewCohortToAttach.getClass(), cohortCollectionNewCohortToAttach.getIdCohort());
                attachedCohortCollectionNew.add(cohortCollectionNewCohortToAttach);
            }
            cohortCollectionNew = attachedCohortCollectionNew;
            academicactivity.setCohortCollection(cohortCollectionNew);
            Collection<Discount> attachedDiscountCollectionNew = new ArrayList<Discount>();
            for (Discount discountCollectionNewDiscountToAttach : discountCollectionNew) {
                discountCollectionNewDiscountToAttach = em.getReference(discountCollectionNewDiscountToAttach.getClass(), discountCollectionNewDiscountToAttach.getIdDiscount());
                attachedDiscountCollectionNew.add(discountCollectionNewDiscountToAttach);
            }
            discountCollectionNew = attachedDiscountCollectionNew;
            academicactivity.setDiscountCollection(discountCollectionNew);
            academicactivity = em.merge(academicactivity);
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getAcademicactivityCollection().remove(academicactivity);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getAcademicactivityCollection().add(academicactivity);
                idUserNew = em.merge(idUserNew);
            }
            for (Budget budgetCollectionOldBudget : budgetCollectionOld) {
                if (!budgetCollectionNew.contains(budgetCollectionOldBudget)) {
                    budgetCollectionOldBudget.setIdActivity(null);
                    budgetCollectionOldBudget = em.merge(budgetCollectionOldBudget);
                }
            }
            for (Budget budgetCollectionNewBudget : budgetCollectionNew) {
                if (!budgetCollectionOld.contains(budgetCollectionNewBudget)) {
                    Academicactivity oldIdActivityOfBudgetCollectionNewBudget = budgetCollectionNewBudget.getIdActivity();
                    budgetCollectionNewBudget.setIdActivity(academicactivity);
                    budgetCollectionNewBudget = em.merge(budgetCollectionNewBudget);
                    if (oldIdActivityOfBudgetCollectionNewBudget != null && !oldIdActivityOfBudgetCollectionNewBudget.equals(academicactivity)) {
                        oldIdActivityOfBudgetCollectionNewBudget.getBudgetCollection().remove(budgetCollectionNewBudget);
                        oldIdActivityOfBudgetCollectionNewBudget = em.merge(oldIdActivityOfBudgetCollectionNewBudget);
                    }
                }
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
            for (Cohort cohortCollectionOldCohort : cohortCollectionOld) {
                if (!cohortCollectionNew.contains(cohortCollectionOldCohort)) {
                    cohortCollectionOldCohort.setIdActivity(null);
                    cohortCollectionOldCohort = em.merge(cohortCollectionOldCohort);
                }
            }
            for (Cohort cohortCollectionNewCohort : cohortCollectionNew) {
                if (!cohortCollectionOld.contains(cohortCollectionNewCohort)) {
                    Academicactivity oldIdActivityOfCohortCollectionNewCohort = cohortCollectionNewCohort.getIdActivity();
                    cohortCollectionNewCohort.setIdActivity(academicactivity);
                    cohortCollectionNewCohort = em.merge(cohortCollectionNewCohort);
                    if (oldIdActivityOfCohortCollectionNewCohort != null && !oldIdActivityOfCohortCollectionNewCohort.equals(academicactivity)) {
                        oldIdActivityOfCohortCollectionNewCohort.getCohortCollection().remove(cohortCollectionNewCohort);
                        oldIdActivityOfCohortCollectionNewCohort = em.merge(oldIdActivityOfCohortCollectionNewCohort);
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
            User idUser = academicactivity.getIdUser();
            if (idUser != null) {
                idUser.getAcademicactivityCollection().remove(academicactivity);
                idUser = em.merge(idUser);
            }
            Collection<Budget> budgetCollection = academicactivity.getBudgetCollection();
            for (Budget budgetCollectionBudget : budgetCollection) {
                budgetCollectionBudget.setIdActivity(null);
                budgetCollectionBudget = em.merge(budgetCollectionBudget);
            }
            Collection<Estimated> estimatedCollection = academicactivity.getEstimatedCollection();
            for (Estimated estimatedCollectionEstimated : estimatedCollection) {
                estimatedCollectionEstimated.setIdAcad(null);
                estimatedCollectionEstimated = em.merge(estimatedCollectionEstimated);
            }
            Collection<Cohort> cohortCollection = academicactivity.getCohortCollection();
            for (Cohort cohortCollectionCohort : cohortCollection) {
                cohortCollectionCohort.setIdActivity(null);
                cohortCollectionCohort = em.merge(cohortCollectionCohort);
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

    public Collection<Academicactivity> findAcademicactivityEntities() {
        return findAcademicactivityEntities(true, -1, -1);
    }

    public Collection<Academicactivity> findAcademicactivityEntities(int maxResults, int firstResult) {
        return findAcademicactivityEntities(false, maxResults, firstResult);
    }

    private Collection<Academicactivity> findAcademicactivityEntities(boolean all, int maxResults, int firstResult) {
        Root<Academicactivity> acad;
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            //acad = cq.from(Academicactivity.class); // Se trae al root
            //Join<Academicactivity, Estimated> estimated = acad.join(Academicactivity_.estimatedCollection);
            
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
