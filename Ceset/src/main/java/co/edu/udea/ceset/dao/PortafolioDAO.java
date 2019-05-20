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
import co.edu.udea.ceset.dto.entities.User;
import co.edu.udea.ceset.dto.entities.Academicactivity;
import co.edu.udea.ceset.dto.entities.Portafolio;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
         List<Portafolio> nuevo;
        if (portafolio.getAcademicactivityCollection() == null) {
            portafolio.setAcademicactivityCollection(new ArrayList<Academicactivity>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User idUser = portafolio.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                portafolio.setIdUser(idUser);
            }
            Collection<Academicactivity> attachedAcademicactivityCollection = new ArrayList<Academicactivity>();
            for (Academicactivity academicactivityCollectionAcademicactivityToAttach : portafolio.getAcademicactivityCollection()) {
                academicactivityCollectionAcademicactivityToAttach = em.getReference(academicactivityCollectionAcademicactivityToAttach.getClass(), academicactivityCollectionAcademicactivityToAttach.getIdAcad());
                attachedAcademicactivityCollection.add(academicactivityCollectionAcademicactivityToAttach);
            }
            portafolio.setAcademicactivityCollection(attachedAcademicactivityCollection);
            em.persist(portafolio);
            if (idUser != null) {
                idUser.getPortafolioCollection().add(portafolio);
                idUser = em.merge(idUser);
            }
            for (Academicactivity academicactivityCollectionAcademicactivity : portafolio.getAcademicactivityCollection()) {
                Portafolio oldIdPortOfAcademicactivityCollectionAcademicactivity = academicactivityCollectionAcademicactivity.getIdPort();
                academicactivityCollectionAcademicactivity.setIdPort(portafolio);
                academicactivityCollectionAcademicactivity = em.merge(academicactivityCollectionAcademicactivity);
                if (oldIdPortOfAcademicactivityCollectionAcademicactivity != null) {
                    oldIdPortOfAcademicactivityCollectionAcademicactivity.getAcademicactivityCollection().remove(academicactivityCollectionAcademicactivity);
                    oldIdPortOfAcademicactivityCollectionAcademicactivity = em.merge(oldIdPortOfAcademicactivityCollectionAcademicactivity);
                }
            }
            em.getTransaction().commit();
        } finally {
            nuevo = em.createNamedQuery("Portafolio.findLast").setMaxResults(1).getResultList();
            if (em != null) {
                em.close();
            }
        }
        return nuevo.get(0);
    }

    public void edit(Portafolio portafolio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Portafolio persistentPortafolio = em.find(Portafolio.class, portafolio.getId());
            User idUserOld = persistentPortafolio.getIdUser();
            User idUserNew = portafolio.getIdUser();
            Collection<Academicactivity> academicactivityCollectionOld = persistentPortafolio.getAcademicactivityCollection();
            Collection<Academicactivity> academicactivityCollectionNew = portafolio.getAcademicactivityCollection();
            List<String> illegalOrphanMessages = null;
            for (Academicactivity academicactivityCollectionOldAcademicactivity : academicactivityCollectionOld) {
                if (!academicactivityCollectionNew.contains(academicactivityCollectionOldAcademicactivity)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Academicactivity " + academicactivityCollectionOldAcademicactivity + " since its idPort field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                portafolio.setIdUser(idUserNew);
            }
            Collection<Academicactivity> attachedAcademicactivityCollectionNew = new ArrayList<Academicactivity>();
            for (Academicactivity academicactivityCollectionNewAcademicactivityToAttach : academicactivityCollectionNew) {
                academicactivityCollectionNewAcademicactivityToAttach = em.getReference(academicactivityCollectionNewAcademicactivityToAttach.getClass(), academicactivityCollectionNewAcademicactivityToAttach.getIdAcad());
                attachedAcademicactivityCollectionNew.add(academicactivityCollectionNewAcademicactivityToAttach);
            }
            academicactivityCollectionNew = attachedAcademicactivityCollectionNew;
            portafolio.setAcademicactivityCollection(academicactivityCollectionNew);
            portafolio = em.merge(portafolio);
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getPortafolioCollection().remove(portafolio);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getPortafolioCollection().add(portafolio);
                idUserNew = em.merge(idUserNew);
            }
            for (Academicactivity academicactivityCollectionNewAcademicactivity : academicactivityCollectionNew) {
                if (!academicactivityCollectionOld.contains(academicactivityCollectionNewAcademicactivity)) {
                    Portafolio oldIdPortOfAcademicactivityCollectionNewAcademicactivity = academicactivityCollectionNewAcademicactivity.getIdPort();
                    academicactivityCollectionNewAcademicactivity.setIdPort(portafolio);
                    academicactivityCollectionNewAcademicactivity = em.merge(academicactivityCollectionNewAcademicactivity);
                    if (oldIdPortOfAcademicactivityCollectionNewAcademicactivity != null && !oldIdPortOfAcademicactivityCollectionNewAcademicactivity.equals(portafolio)) {
                        oldIdPortOfAcademicactivityCollectionNewAcademicactivity.getAcademicactivityCollection().remove(academicactivityCollectionNewAcademicactivity);
                        oldIdPortOfAcademicactivityCollectionNewAcademicactivity = em.merge(oldIdPortOfAcademicactivityCollectionNewAcademicactivity);
                    }
                }
            }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Academicactivity> academicactivityCollectionOrphanCheck = portafolio.getAcademicactivityCollection();
            for (Academicactivity academicactivityCollectionOrphanCheckAcademicactivity : academicactivityCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Portafolio (" + portafolio + ") cannot be destroyed since the Academicactivity " + academicactivityCollectionOrphanCheckAcademicactivity + " in its academicactivityCollection field has a non-nullable idPort field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            User idUser = portafolio.getIdUser();
            if (idUser != null) {
                idUser.getPortafolioCollection().remove(portafolio);
                idUser = em.merge(idUser);
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
