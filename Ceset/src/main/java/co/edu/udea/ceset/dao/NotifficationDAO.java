/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.exceptions.NonexistentEntityException;
import co.udea.edu.co.dto.entities.Notiffication;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.udea.edu.co.dto.entities.Notifficationbycheck;
import java.util.ArrayList;
import java.util.Collection;
import co.udea.edu.co.dto.entities.Notifficationbyrole;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class NotifficationDAO implements Serializable {

    public NotifficationDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Notiffication notiffication) {
        if (notiffication.getNotifficationbycheckCollection() == null) {
            notiffication.setNotifficationbycheckCollection(new ArrayList<Notifficationbycheck>());
        }
        if (notiffication.getNotifficationbyroleCollection() == null) {
            notiffication.setNotifficationbyroleCollection(new ArrayList<Notifficationbyrole>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Notifficationbycheck> attachedNotifficationbycheckCollection = new ArrayList<Notifficationbycheck>();
            for (Notifficationbycheck notifficationbycheckCollectionNotifficationbycheckToAttach : notiffication.getNotifficationbycheckCollection()) {
                notifficationbycheckCollectionNotifficationbycheckToAttach = em.getReference(notifficationbycheckCollectionNotifficationbycheckToAttach.getClass(), notifficationbycheckCollectionNotifficationbycheckToAttach.getId());
                attachedNotifficationbycheckCollection.add(notifficationbycheckCollectionNotifficationbycheckToAttach);
            }
            notiffication.setNotifficationbycheckCollection(attachedNotifficationbycheckCollection);
            Collection<Notifficationbyrole> attachedNotifficationbyroleCollection = new ArrayList<Notifficationbyrole>();
            for (Notifficationbyrole notifficationbyroleCollectionNotifficationbyroleToAttach : notiffication.getNotifficationbyroleCollection()) {
                notifficationbyroleCollectionNotifficationbyroleToAttach = em.getReference(notifficationbyroleCollectionNotifficationbyroleToAttach.getClass(), notifficationbyroleCollectionNotifficationbyroleToAttach.getId());
                attachedNotifficationbyroleCollection.add(notifficationbyroleCollectionNotifficationbyroleToAttach);
            }
            notiffication.setNotifficationbyroleCollection(attachedNotifficationbyroleCollection);
            em.persist(notiffication);
            for (Notifficationbycheck notifficationbycheckCollectionNotifficationbycheck : notiffication.getNotifficationbycheckCollection()) {
                Notiffication oldIdNotifOfNotifficationbycheckCollectionNotifficationbycheck = notifficationbycheckCollectionNotifficationbycheck.getIdNotif();
                notifficationbycheckCollectionNotifficationbycheck.setIdNotif(notiffication);
                notifficationbycheckCollectionNotifficationbycheck = em.merge(notifficationbycheckCollectionNotifficationbycheck);
                if (oldIdNotifOfNotifficationbycheckCollectionNotifficationbycheck != null) {
                    oldIdNotifOfNotifficationbycheckCollectionNotifficationbycheck.getNotifficationbycheckCollection().remove(notifficationbycheckCollectionNotifficationbycheck);
                    oldIdNotifOfNotifficationbycheckCollectionNotifficationbycheck = em.merge(oldIdNotifOfNotifficationbycheckCollectionNotifficationbycheck);
                }
            }
            for (Notifficationbyrole notifficationbyroleCollectionNotifficationbyrole : notiffication.getNotifficationbyroleCollection()) {
                Notiffication oldIdNotifOfNotifficationbyroleCollectionNotifficationbyrole = notifficationbyroleCollectionNotifficationbyrole.getIdNotif();
                notifficationbyroleCollectionNotifficationbyrole.setIdNotif(notiffication);
                notifficationbyroleCollectionNotifficationbyrole = em.merge(notifficationbyroleCollectionNotifficationbyrole);
                if (oldIdNotifOfNotifficationbyroleCollectionNotifficationbyrole != null) {
                    oldIdNotifOfNotifficationbyroleCollectionNotifficationbyrole.getNotifficationbyroleCollection().remove(notifficationbyroleCollectionNotifficationbyrole);
                    oldIdNotifOfNotifficationbyroleCollectionNotifficationbyrole = em.merge(oldIdNotifOfNotifficationbyroleCollectionNotifficationbyrole);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Notiffication notiffication) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Notiffication persistentNotiffication = em.find(Notiffication.class, notiffication.getIdNotif());
            Collection<Notifficationbycheck> notifficationbycheckCollectionOld = persistentNotiffication.getNotifficationbycheckCollection();
            Collection<Notifficationbycheck> notifficationbycheckCollectionNew = notiffication.getNotifficationbycheckCollection();
            Collection<Notifficationbyrole> notifficationbyroleCollectionOld = persistentNotiffication.getNotifficationbyroleCollection();
            Collection<Notifficationbyrole> notifficationbyroleCollectionNew = notiffication.getNotifficationbyroleCollection();
            Collection<Notifficationbycheck> attachedNotifficationbycheckCollectionNew = new ArrayList<Notifficationbycheck>();
            for (Notifficationbycheck notifficationbycheckCollectionNewNotifficationbycheckToAttach : notifficationbycheckCollectionNew) {
                notifficationbycheckCollectionNewNotifficationbycheckToAttach = em.getReference(notifficationbycheckCollectionNewNotifficationbycheckToAttach.getClass(), notifficationbycheckCollectionNewNotifficationbycheckToAttach.getId());
                attachedNotifficationbycheckCollectionNew.add(notifficationbycheckCollectionNewNotifficationbycheckToAttach);
            }
            notifficationbycheckCollectionNew = attachedNotifficationbycheckCollectionNew;
            notiffication.setNotifficationbycheckCollection(notifficationbycheckCollectionNew);
            Collection<Notifficationbyrole> attachedNotifficationbyroleCollectionNew = new ArrayList<Notifficationbyrole>();
            for (Notifficationbyrole notifficationbyroleCollectionNewNotifficationbyroleToAttach : notifficationbyroleCollectionNew) {
                notifficationbyroleCollectionNewNotifficationbyroleToAttach = em.getReference(notifficationbyroleCollectionNewNotifficationbyroleToAttach.getClass(), notifficationbyroleCollectionNewNotifficationbyroleToAttach.getId());
                attachedNotifficationbyroleCollectionNew.add(notifficationbyroleCollectionNewNotifficationbyroleToAttach);
            }
            notifficationbyroleCollectionNew = attachedNotifficationbyroleCollectionNew;
            notiffication.setNotifficationbyroleCollection(notifficationbyroleCollectionNew);
            notiffication = em.merge(notiffication);
            for (Notifficationbycheck notifficationbycheckCollectionOldNotifficationbycheck : notifficationbycheckCollectionOld) {
                if (!notifficationbycheckCollectionNew.contains(notifficationbycheckCollectionOldNotifficationbycheck)) {
                    notifficationbycheckCollectionOldNotifficationbycheck.setIdNotif(null);
                    notifficationbycheckCollectionOldNotifficationbycheck = em.merge(notifficationbycheckCollectionOldNotifficationbycheck);
                }
            }
            for (Notifficationbycheck notifficationbycheckCollectionNewNotifficationbycheck : notifficationbycheckCollectionNew) {
                if (!notifficationbycheckCollectionOld.contains(notifficationbycheckCollectionNewNotifficationbycheck)) {
                    Notiffication oldIdNotifOfNotifficationbycheckCollectionNewNotifficationbycheck = notifficationbycheckCollectionNewNotifficationbycheck.getIdNotif();
                    notifficationbycheckCollectionNewNotifficationbycheck.setIdNotif(notiffication);
                    notifficationbycheckCollectionNewNotifficationbycheck = em.merge(notifficationbycheckCollectionNewNotifficationbycheck);
                    if (oldIdNotifOfNotifficationbycheckCollectionNewNotifficationbycheck != null && !oldIdNotifOfNotifficationbycheckCollectionNewNotifficationbycheck.equals(notiffication)) {
                        oldIdNotifOfNotifficationbycheckCollectionNewNotifficationbycheck.getNotifficationbycheckCollection().remove(notifficationbycheckCollectionNewNotifficationbycheck);
                        oldIdNotifOfNotifficationbycheckCollectionNewNotifficationbycheck = em.merge(oldIdNotifOfNotifficationbycheckCollectionNewNotifficationbycheck);
                    }
                }
            }
            for (Notifficationbyrole notifficationbyroleCollectionOldNotifficationbyrole : notifficationbyroleCollectionOld) {
                if (!notifficationbyroleCollectionNew.contains(notifficationbyroleCollectionOldNotifficationbyrole)) {
                    notifficationbyroleCollectionOldNotifficationbyrole.setIdNotif(null);
                    notifficationbyroleCollectionOldNotifficationbyrole = em.merge(notifficationbyroleCollectionOldNotifficationbyrole);
                }
            }
            for (Notifficationbyrole notifficationbyroleCollectionNewNotifficationbyrole : notifficationbyroleCollectionNew) {
                if (!notifficationbyroleCollectionOld.contains(notifficationbyroleCollectionNewNotifficationbyrole)) {
                    Notiffication oldIdNotifOfNotifficationbyroleCollectionNewNotifficationbyrole = notifficationbyroleCollectionNewNotifficationbyrole.getIdNotif();
                    notifficationbyroleCollectionNewNotifficationbyrole.setIdNotif(notiffication);
                    notifficationbyroleCollectionNewNotifficationbyrole = em.merge(notifficationbyroleCollectionNewNotifficationbyrole);
                    if (oldIdNotifOfNotifficationbyroleCollectionNewNotifficationbyrole != null && !oldIdNotifOfNotifficationbyroleCollectionNewNotifficationbyrole.equals(notiffication)) {
                        oldIdNotifOfNotifficationbyroleCollectionNewNotifficationbyrole.getNotifficationbyroleCollection().remove(notifficationbyroleCollectionNewNotifficationbyrole);
                        oldIdNotifOfNotifficationbyroleCollectionNewNotifficationbyrole = em.merge(oldIdNotifOfNotifficationbyroleCollectionNewNotifficationbyrole);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = notiffication.getIdNotif();
                if (findNotiffication(id) == null) {
                    throw new NonexistentEntityException("The notiffication with id " + id + " no longer exists.");
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
            Notiffication notiffication;
            try {
                notiffication = em.getReference(Notiffication.class, id);
                notiffication.getIdNotif();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notiffication with id " + id + " no longer exists.", enfe);
            }
            Collection<Notifficationbycheck> notifficationbycheckCollection = notiffication.getNotifficationbycheckCollection();
            for (Notifficationbycheck notifficationbycheckCollectionNotifficationbycheck : notifficationbycheckCollection) {
                notifficationbycheckCollectionNotifficationbycheck.setIdNotif(null);
                notifficationbycheckCollectionNotifficationbycheck = em.merge(notifficationbycheckCollectionNotifficationbycheck);
            }
            Collection<Notifficationbyrole> notifficationbyroleCollection = notiffication.getNotifficationbyroleCollection();
            for (Notifficationbyrole notifficationbyroleCollectionNotifficationbyrole : notifficationbyroleCollection) {
                notifficationbyroleCollectionNotifficationbyrole.setIdNotif(null);
                notifficationbyroleCollectionNotifficationbyrole = em.merge(notifficationbyroleCollectionNotifficationbyrole);
            }
            em.remove(notiffication);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Notiffication> findNotifficationEntities() {
        return findNotifficationEntities(true, -1, -1);
    }

    public List<Notiffication> findNotifficationEntities(int maxResults, int firstResult) {
        return findNotifficationEntities(false, maxResults, firstResult);
    }

    private List<Notiffication> findNotifficationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Notiffication.class));
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

    public Notiffication findNotiffication(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Notiffication.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotifficationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Notiffication> rt = cq.from(Notiffication.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
