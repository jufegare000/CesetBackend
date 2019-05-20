/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.IllegalOrphanException;
import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.udea.ceset.dto.entities.Academicactivity;
import co.edu.udea.ceset.dto.entities.Budget;
import co.edu.udea.ceset.dto.entities.Groupe;
import java.util.ArrayList;
import java.util.Collection;
import co.edu.udea.ceset.dto.entities.Themes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan
 */
public class GroupeDAO implements Serializable {

    public GroupeDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Groupe groupe) {
        if (groupe.getBudgetCollection() == null) {
            groupe.setBudgetCollection(new ArrayList<Budget>());
        }
        if (groupe.getThemesCollection() == null) {
            groupe.setThemesCollection(new ArrayList<Themes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Academicactivity idAcad = groupe.getIdAcad();
            if (idAcad != null) {
                idAcad = em.getReference(idAcad.getClass(), idAcad.getIdAcad());
                groupe.setIdAcad(idAcad);
            }
            Collection<Budget> attachedBudgetCollection = new ArrayList<Budget>();
            for (Budget budgetCollectionBudgetToAttach : groupe.getBudgetCollection()) {
                budgetCollectionBudgetToAttach = em.getReference(budgetCollectionBudgetToAttach.getClass(), budgetCollectionBudgetToAttach.getIdBudget());
                attachedBudgetCollection.add(budgetCollectionBudgetToAttach);
            }
            groupe.setBudgetCollection(attachedBudgetCollection);
            Collection<Themes> attachedThemesCollection = new ArrayList<Themes>();
            for (Themes themesCollectionThemesToAttach : groupe.getThemesCollection()) {
                themesCollectionThemesToAttach = em.getReference(themesCollectionThemesToAttach.getClass(), themesCollectionThemesToAttach.getIdTheme());
                attachedThemesCollection.add(themesCollectionThemesToAttach);
            }
            groupe.setThemesCollection(attachedThemesCollection);
            em.persist(groupe);
            if (idAcad != null) {
                idAcad.getGroupeCollection().add(groupe);
                idAcad = em.merge(idAcad);
            }
            for (Budget budgetCollectionBudget : groupe.getBudgetCollection()) {
                Groupe oldIdGroupOfBudgetCollectionBudget = budgetCollectionBudget.getIdGroup();
                budgetCollectionBudget.setIdGroup(groupe);
                budgetCollectionBudget = em.merge(budgetCollectionBudget);
                if (oldIdGroupOfBudgetCollectionBudget != null) {
                    oldIdGroupOfBudgetCollectionBudget.getBudgetCollection().remove(budgetCollectionBudget);
                    oldIdGroupOfBudgetCollectionBudget = em.merge(oldIdGroupOfBudgetCollectionBudget);
                }
            }
            for (Themes themesCollectionThemes : groupe.getThemesCollection()) {
                Groupe oldIdGroupOfThemesCollectionThemes = themesCollectionThemes.getIdGroup();
                themesCollectionThemes.setIdGroup(groupe);
                themesCollectionThemes = em.merge(themesCollectionThemes);
                if (oldIdGroupOfThemesCollectionThemes != null) {
                    oldIdGroupOfThemesCollectionThemes.getThemesCollection().remove(themesCollectionThemes);
                    oldIdGroupOfThemesCollectionThemes = em.merge(oldIdGroupOfThemesCollectionThemes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Groupe groupe) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Groupe persistentGroupe = em.find(Groupe.class, groupe.getIdGroup());
            Academicactivity idAcadOld = persistentGroupe.getIdAcad();
            Academicactivity idAcadNew = groupe.getIdAcad();
            Collection<Budget> budgetCollectionOld = persistentGroupe.getBudgetCollection();
            Collection<Budget> budgetCollectionNew = groupe.getBudgetCollection();
            Collection<Themes> themesCollectionOld = persistentGroupe.getThemesCollection();
            Collection<Themes> themesCollectionNew = groupe.getThemesCollection();
            List<String> illegalOrphanMessages = null;
            for (Budget budgetCollectionOldBudget : budgetCollectionOld) {
                if (!budgetCollectionNew.contains(budgetCollectionOldBudget)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Budget " + budgetCollectionOldBudget + " since its idGroup field is not nullable.");
                }
            }
            for (Themes themesCollectionOldThemes : themesCollectionOld) {
                if (!themesCollectionNew.contains(themesCollectionOldThemes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Themes " + themesCollectionOldThemes + " since its idGroup field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idAcadNew != null) {
                idAcadNew = em.getReference(idAcadNew.getClass(), idAcadNew.getIdAcad());
                groupe.setIdAcad(idAcadNew);
            }
            Collection<Budget> attachedBudgetCollectionNew = new ArrayList<Budget>();
            for (Budget budgetCollectionNewBudgetToAttach : budgetCollectionNew) {
                budgetCollectionNewBudgetToAttach = em.getReference(budgetCollectionNewBudgetToAttach.getClass(), budgetCollectionNewBudgetToAttach.getIdBudget());
                attachedBudgetCollectionNew.add(budgetCollectionNewBudgetToAttach);
            }
            budgetCollectionNew = attachedBudgetCollectionNew;
            groupe.setBudgetCollection(budgetCollectionNew);
            Collection<Themes> attachedThemesCollectionNew = new ArrayList<Themes>();
            for (Themes themesCollectionNewThemesToAttach : themesCollectionNew) {
                themesCollectionNewThemesToAttach = em.getReference(themesCollectionNewThemesToAttach.getClass(), themesCollectionNewThemesToAttach.getIdTheme());
                attachedThemesCollectionNew.add(themesCollectionNewThemesToAttach);
            }
            themesCollectionNew = attachedThemesCollectionNew;
            groupe.setThemesCollection(themesCollectionNew);
            groupe = em.merge(groupe);
            if (idAcadOld != null && !idAcadOld.equals(idAcadNew)) {
                idAcadOld.getGroupeCollection().remove(groupe);
                idAcadOld = em.merge(idAcadOld);
            }
            if (idAcadNew != null && !idAcadNew.equals(idAcadOld)) {
                idAcadNew.getGroupeCollection().add(groupe);
                idAcadNew = em.merge(idAcadNew);
            }
            for (Budget budgetCollectionNewBudget : budgetCollectionNew) {
                if (!budgetCollectionOld.contains(budgetCollectionNewBudget)) {
                    Groupe oldIdGroupOfBudgetCollectionNewBudget = budgetCollectionNewBudget.getIdGroup();
                    budgetCollectionNewBudget.setIdGroup(groupe);
                    budgetCollectionNewBudget = em.merge(budgetCollectionNewBudget);
                    if (oldIdGroupOfBudgetCollectionNewBudget != null && !oldIdGroupOfBudgetCollectionNewBudget.equals(groupe)) {
                        oldIdGroupOfBudgetCollectionNewBudget.getBudgetCollection().remove(budgetCollectionNewBudget);
                        oldIdGroupOfBudgetCollectionNewBudget = em.merge(oldIdGroupOfBudgetCollectionNewBudget);
                    }
                }
            }
            for (Themes themesCollectionNewThemes : themesCollectionNew) {
                if (!themesCollectionOld.contains(themesCollectionNewThemes)) {
                    Groupe oldIdGroupOfThemesCollectionNewThemes = themesCollectionNewThemes.getIdGroup();
                    themesCollectionNewThemes.setIdGroup(groupe);
                    themesCollectionNewThemes = em.merge(themesCollectionNewThemes);
                    if (oldIdGroupOfThemesCollectionNewThemes != null && !oldIdGroupOfThemesCollectionNewThemes.equals(groupe)) {
                        oldIdGroupOfThemesCollectionNewThemes.getThemesCollection().remove(themesCollectionNewThemes);
                        oldIdGroupOfThemesCollectionNewThemes = em.merge(oldIdGroupOfThemesCollectionNewThemes);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Budget> budgetCollectionOrphanCheck = groupe.getBudgetCollection();
            for (Budget budgetCollectionOrphanCheckBudget : budgetCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Groupe (" + groupe + ") cannot be destroyed since the Budget " + budgetCollectionOrphanCheckBudget + " in its budgetCollection field has a non-nullable idGroup field.");
            }
            Collection<Themes> themesCollectionOrphanCheck = groupe.getThemesCollection();
            for (Themes themesCollectionOrphanCheckThemes : themesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Groupe (" + groupe + ") cannot be destroyed since the Themes " + themesCollectionOrphanCheckThemes + " in its themesCollection field has a non-nullable idGroup field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Academicactivity idAcad = groupe.getIdAcad();
            if (idAcad != null) {
                idAcad.getGroupeCollection().remove(groupe);
                idAcad = em.merge(idAcad);
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
