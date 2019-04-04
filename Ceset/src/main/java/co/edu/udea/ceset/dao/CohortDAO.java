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
import co.udea.edu.co.dto.entities.Academicactivity;
import co.udea.edu.co.dto.entities.Budget;
import co.udea.edu.co.dto.entities.Cohort;
import java.util.ArrayList;
import java.util.Collection;
import co.udea.edu.co.dto.entities.Themes;
import co.udea.edu.co.dto.entities.Groupe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan
 */
public class CohortDAO implements Serializable {

    public CohortDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cohort cohort) {
        if (cohort.getBudgetCollection() == null) {
            cohort.setBudgetCollection(new ArrayList<Budget>());
        }
        if (cohort.getThemesCollection() == null) {
            cohort.setThemesCollection(new ArrayList<Themes>());
        }
        if (cohort.getGroupeCollection() == null) {
            cohort.setGroupeCollection(new ArrayList<Groupe>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Academicactivity idActivity = cohort.getIdActivity();
            if (idActivity != null) {
                idActivity = em.getReference(idActivity.getClass(), idActivity.getIdAcad());
                cohort.setIdActivity(idActivity);
            }
            Collection<Budget> attachedBudgetCollection = new ArrayList<Budget>();
            for (Budget budgetCollectionBudgetToAttach : cohort.getBudgetCollection()) {
                budgetCollectionBudgetToAttach = em.getReference(budgetCollectionBudgetToAttach.getClass(), budgetCollectionBudgetToAttach.getIdBudget());
                attachedBudgetCollection.add(budgetCollectionBudgetToAttach);
            }
            cohort.setBudgetCollection(attachedBudgetCollection);
            Collection<Themes> attachedThemesCollection = new ArrayList<Themes>();
            for (Themes themesCollectionThemesToAttach : cohort.getThemesCollection()) {
                themesCollectionThemesToAttach = em.getReference(themesCollectionThemesToAttach.getClass(), themesCollectionThemesToAttach.getIdTheme());
                attachedThemesCollection.add(themesCollectionThemesToAttach);
            }
            cohort.setThemesCollection(attachedThemesCollection);
            Collection<Groupe> attachedGroupeCollection = new ArrayList<Groupe>();
            for (Groupe groupeCollectionGroupeToAttach : cohort.getGroupeCollection()) {
                groupeCollectionGroupeToAttach = em.getReference(groupeCollectionGroupeToAttach.getClass(), groupeCollectionGroupeToAttach.getIdGroup());
                attachedGroupeCollection.add(groupeCollectionGroupeToAttach);
            }
            cohort.setGroupeCollection(attachedGroupeCollection);
            em.persist(cohort);
            if (idActivity != null) {
                idActivity.getCohortCollection().add(cohort);
                idActivity = em.merge(idActivity);
            }
            for (Budget budgetCollectionBudget : cohort.getBudgetCollection()) {
                Cohort oldIdCohortOfBudgetCollectionBudget = budgetCollectionBudget.getIdCohort();
                budgetCollectionBudget.setIdCohort(cohort);
                budgetCollectionBudget = em.merge(budgetCollectionBudget);
                if (oldIdCohortOfBudgetCollectionBudget != null) {
                    oldIdCohortOfBudgetCollectionBudget.getBudgetCollection().remove(budgetCollectionBudget);
                    oldIdCohortOfBudgetCollectionBudget = em.merge(oldIdCohortOfBudgetCollectionBudget);
                }
            }
            for (Themes themesCollectionThemes : cohort.getThemesCollection()) {
                Cohort oldIdCohortOfThemesCollectionThemes = themesCollectionThemes.getIdCohort();
                themesCollectionThemes.setIdCohort(cohort);
                themesCollectionThemes = em.merge(themesCollectionThemes);
                if (oldIdCohortOfThemesCollectionThemes != null) {
                    oldIdCohortOfThemesCollectionThemes.getThemesCollection().remove(themesCollectionThemes);
                    oldIdCohortOfThemesCollectionThemes = em.merge(oldIdCohortOfThemesCollectionThemes);
                }
            }
            for (Groupe groupeCollectionGroupe : cohort.getGroupeCollection()) {
                Cohort oldIdCohortOfGroupeCollectionGroupe = groupeCollectionGroupe.getIdCohort();
                groupeCollectionGroupe.setIdCohort(cohort);
                groupeCollectionGroupe = em.merge(groupeCollectionGroupe);
                if (oldIdCohortOfGroupeCollectionGroupe != null) {
                    oldIdCohortOfGroupeCollectionGroupe.getGroupeCollection().remove(groupeCollectionGroupe);
                    oldIdCohortOfGroupeCollectionGroupe = em.merge(oldIdCohortOfGroupeCollectionGroupe);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cohort cohort) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cohort persistentCohort = em.find(Cohort.class, cohort.getIdCohort());
            Academicactivity idActivityOld = persistentCohort.getIdActivity();
            Academicactivity idActivityNew = cohort.getIdActivity();
            Collection<Budget> budgetCollectionOld = persistentCohort.getBudgetCollection();
            Collection<Budget> budgetCollectionNew = cohort.getBudgetCollection();
            Collection<Themes> themesCollectionOld = persistentCohort.getThemesCollection();
            Collection<Themes> themesCollectionNew = cohort.getThemesCollection();
            Collection<Groupe> groupeCollectionOld = persistentCohort.getGroupeCollection();
            Collection<Groupe> groupeCollectionNew = cohort.getGroupeCollection();
            if (idActivityNew != null) {
                idActivityNew = em.getReference(idActivityNew.getClass(), idActivityNew.getIdAcad());
                cohort.setIdActivity(idActivityNew);
            }
            Collection<Budget> attachedBudgetCollectionNew = new ArrayList<Budget>();
            for (Budget budgetCollectionNewBudgetToAttach : budgetCollectionNew) {
                budgetCollectionNewBudgetToAttach = em.getReference(budgetCollectionNewBudgetToAttach.getClass(), budgetCollectionNewBudgetToAttach.getIdBudget());
                attachedBudgetCollectionNew.add(budgetCollectionNewBudgetToAttach);
            }
            budgetCollectionNew = attachedBudgetCollectionNew;
            cohort.setBudgetCollection(budgetCollectionNew);
            Collection<Themes> attachedThemesCollectionNew = new ArrayList<Themes>();
            for (Themes themesCollectionNewThemesToAttach : themesCollectionNew) {
                themesCollectionNewThemesToAttach = em.getReference(themesCollectionNewThemesToAttach.getClass(), themesCollectionNewThemesToAttach.getIdTheme());
                attachedThemesCollectionNew.add(themesCollectionNewThemesToAttach);
            }
            themesCollectionNew = attachedThemesCollectionNew;
            cohort.setThemesCollection(themesCollectionNew);
            Collection<Groupe> attachedGroupeCollectionNew = new ArrayList<Groupe>();
            for (Groupe groupeCollectionNewGroupeToAttach : groupeCollectionNew) {
                groupeCollectionNewGroupeToAttach = em.getReference(groupeCollectionNewGroupeToAttach.getClass(), groupeCollectionNewGroupeToAttach.getIdGroup());
                attachedGroupeCollectionNew.add(groupeCollectionNewGroupeToAttach);
            }
            groupeCollectionNew = attachedGroupeCollectionNew;
            cohort.setGroupeCollection(groupeCollectionNew);
            cohort = em.merge(cohort);
            if (idActivityOld != null && !idActivityOld.equals(idActivityNew)) {
                idActivityOld.getCohortCollection().remove(cohort);
                idActivityOld = em.merge(idActivityOld);
            }
            if (idActivityNew != null && !idActivityNew.equals(idActivityOld)) {
                idActivityNew.getCohortCollection().add(cohort);
                idActivityNew = em.merge(idActivityNew);
            }
            for (Budget budgetCollectionOldBudget : budgetCollectionOld) {
                if (!budgetCollectionNew.contains(budgetCollectionOldBudget)) {
                    budgetCollectionOldBudget.setIdCohort(null);
                    budgetCollectionOldBudget = em.merge(budgetCollectionOldBudget);
                }
            }
            for (Budget budgetCollectionNewBudget : budgetCollectionNew) {
                if (!budgetCollectionOld.contains(budgetCollectionNewBudget)) {
                    Cohort oldIdCohortOfBudgetCollectionNewBudget = budgetCollectionNewBudget.getIdCohort();
                    budgetCollectionNewBudget.setIdCohort(cohort);
                    budgetCollectionNewBudget = em.merge(budgetCollectionNewBudget);
                    if (oldIdCohortOfBudgetCollectionNewBudget != null && !oldIdCohortOfBudgetCollectionNewBudget.equals(cohort)) {
                        oldIdCohortOfBudgetCollectionNewBudget.getBudgetCollection().remove(budgetCollectionNewBudget);
                        oldIdCohortOfBudgetCollectionNewBudget = em.merge(oldIdCohortOfBudgetCollectionNewBudget);
                    }
                }
            }
            for (Themes themesCollectionOldThemes : themesCollectionOld) {
                if (!themesCollectionNew.contains(themesCollectionOldThemes)) {
                    themesCollectionOldThemes.setIdCohort(null);
                    themesCollectionOldThemes = em.merge(themesCollectionOldThemes);
                }
            }
            for (Themes themesCollectionNewThemes : themesCollectionNew) {
                if (!themesCollectionOld.contains(themesCollectionNewThemes)) {
                    Cohort oldIdCohortOfThemesCollectionNewThemes = themesCollectionNewThemes.getIdCohort();
                    themesCollectionNewThemes.setIdCohort(cohort);
                    themesCollectionNewThemes = em.merge(themesCollectionNewThemes);
                    if (oldIdCohortOfThemesCollectionNewThemes != null && !oldIdCohortOfThemesCollectionNewThemes.equals(cohort)) {
                        oldIdCohortOfThemesCollectionNewThemes.getThemesCollection().remove(themesCollectionNewThemes);
                        oldIdCohortOfThemesCollectionNewThemes = em.merge(oldIdCohortOfThemesCollectionNewThemes);
                    }
                }
            }
            for (Groupe groupeCollectionOldGroupe : groupeCollectionOld) {
                if (!groupeCollectionNew.contains(groupeCollectionOldGroupe)) {
                    groupeCollectionOldGroupe.setIdCohort(null);
                    groupeCollectionOldGroupe = em.merge(groupeCollectionOldGroupe);
                }
            }
            for (Groupe groupeCollectionNewGroupe : groupeCollectionNew) {
                if (!groupeCollectionOld.contains(groupeCollectionNewGroupe)) {
                    Cohort oldIdCohortOfGroupeCollectionNewGroupe = groupeCollectionNewGroupe.getIdCohort();
                    groupeCollectionNewGroupe.setIdCohort(cohort);
                    groupeCollectionNewGroupe = em.merge(groupeCollectionNewGroupe);
                    if (oldIdCohortOfGroupeCollectionNewGroupe != null && !oldIdCohortOfGroupeCollectionNewGroupe.equals(cohort)) {
                        oldIdCohortOfGroupeCollectionNewGroupe.getGroupeCollection().remove(groupeCollectionNewGroupe);
                        oldIdCohortOfGroupeCollectionNewGroupe = em.merge(oldIdCohortOfGroupeCollectionNewGroupe);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cohort.getIdCohort();
                if (findCohort(id) == null) {
                    throw new NonexistentEntityException("The cohort with id " + id + " no longer exists.");
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
            Cohort cohort;
            try {
                cohort = em.getReference(Cohort.class, id);
                cohort.getIdCohort();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cohort with id " + id + " no longer exists.", enfe);
            }
            Academicactivity idActivity = cohort.getIdActivity();
            if (idActivity != null) {
                idActivity.getCohortCollection().remove(cohort);
                idActivity = em.merge(idActivity);
            }
            Collection<Budget> budgetCollection = cohort.getBudgetCollection();
            for (Budget budgetCollectionBudget : budgetCollection) {
                budgetCollectionBudget.setIdCohort(null);
                budgetCollectionBudget = em.merge(budgetCollectionBudget);
            }
            Collection<Themes> themesCollection = cohort.getThemesCollection();
            for (Themes themesCollectionThemes : themesCollection) {
                themesCollectionThemes.setIdCohort(null);
                themesCollectionThemes = em.merge(themesCollectionThemes);
            }
            Collection<Groupe> groupeCollection = cohort.getGroupeCollection();
            for (Groupe groupeCollectionGroupe : groupeCollection) {
                groupeCollectionGroupe.setIdCohort(null);
                groupeCollectionGroupe = em.merge(groupeCollectionGroupe);
            }
            em.remove(cohort);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cohort> findCohortEntities() {
        return findCohortEntities(true, -1, -1);
    }

    public List<Cohort> findCohortEntities(int maxResults, int firstResult) {
        return findCohortEntities(false, maxResults, firstResult);
    }

    private List<Cohort> findCohortEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cohort.class));
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

    public Cohort findCohort(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cohort.class, id);
        } finally {
            em.close();
        }
    }

    public int getCohortCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cohort> rt = cq.from(Cohort.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

