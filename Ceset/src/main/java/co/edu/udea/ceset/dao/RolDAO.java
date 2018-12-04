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
import co.edu.udea.ceset.dto.Rolebypermission;
import java.util.ArrayList;
import java.util.Collection;
import co.edu.udea.ceset.dto.Rolebyuser;
import co.edu.udea.ceset.dto.Rolec;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jufeg
 */
public class RolDAO implements Serializable {

    public RolDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rolec rolec) {
        if (rolec.getRolebypermissionCollection() == null) {
            rolec.setRolebypermissionCollection(new ArrayList<Rolebypermission>());
        }
        if (rolec.getRolebyuserCollection() == null) {
            rolec.setRolebyuserCollection(new ArrayList<Rolebyuser>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Rolebypermission> attachedRolebypermissionCollection = new ArrayList<Rolebypermission>();
            for (Rolebypermission rolebypermissionCollectionRolebypermissionToAttach : rolec.getRolebypermissionCollection()) {
                rolebypermissionCollectionRolebypermissionToAttach = em.getReference(rolebypermissionCollectionRolebypermissionToAttach.getClass(), rolebypermissionCollectionRolebypermissionToAttach.getId());
                attachedRolebypermissionCollection.add(rolebypermissionCollectionRolebypermissionToAttach);
            }
            rolec.setRolebypermissionCollection(attachedRolebypermissionCollection);
            Collection<Rolebyuser> attachedRolebyuserCollection = new ArrayList<Rolebyuser>();
            for (Rolebyuser rolebyuserCollectionRolebyuserToAttach : rolec.getRolebyuserCollection()) {
                rolebyuserCollectionRolebyuserToAttach = em.getReference(rolebyuserCollectionRolebyuserToAttach.getClass(), rolebyuserCollectionRolebyuserToAttach.getId());
                attachedRolebyuserCollection.add(rolebyuserCollectionRolebyuserToAttach);
            }
            rolec.setRolebyuserCollection(attachedRolebyuserCollection);
            em.persist(rolec);
            for (Rolebypermission rolebypermissionCollectionRolebypermission : rolec.getRolebypermissionCollection()) {
                Rolec oldIdRoleOfRolebypermissionCollectionRolebypermission = rolebypermissionCollectionRolebypermission.getIdRole();
                rolebypermissionCollectionRolebypermission.setIdRole(rolec);
                rolebypermissionCollectionRolebypermission = em.merge(rolebypermissionCollectionRolebypermission);
                if (oldIdRoleOfRolebypermissionCollectionRolebypermission != null) {
                    oldIdRoleOfRolebypermissionCollectionRolebypermission.getRolebypermissionCollection().remove(rolebypermissionCollectionRolebypermission);
                    oldIdRoleOfRolebypermissionCollectionRolebypermission = em.merge(oldIdRoleOfRolebypermissionCollectionRolebypermission);
                }
            }
            for (Rolebyuser rolebyuserCollectionRolebyuser : rolec.getRolebyuserCollection()) {
                Rolec oldIdRoleOfRolebyuserCollectionRolebyuser = rolebyuserCollectionRolebyuser.getIdRole();
                rolebyuserCollectionRolebyuser.setIdRole(rolec);
                rolebyuserCollectionRolebyuser = em.merge(rolebyuserCollectionRolebyuser);
                if (oldIdRoleOfRolebyuserCollectionRolebyuser != null) {
                    oldIdRoleOfRolebyuserCollectionRolebyuser.getRolebyuserCollection().remove(rolebyuserCollectionRolebyuser);
                    oldIdRoleOfRolebyuserCollectionRolebyuser = em.merge(oldIdRoleOfRolebyuserCollectionRolebyuser);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rolec rolec) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rolec persistentRolec = em.find(Rolec.class, rolec.getIdRole());
            Collection<Rolebypermission> rolebypermissionCollectionOld = persistentRolec.getRolebypermissionCollection();
            Collection<Rolebypermission> rolebypermissionCollectionNew = rolec.getRolebypermissionCollection();
            Collection<Rolebyuser> rolebyuserCollectionOld = persistentRolec.getRolebyuserCollection();
            Collection<Rolebyuser> rolebyuserCollectionNew = rolec.getRolebyuserCollection();
            Collection<Rolebypermission> attachedRolebypermissionCollectionNew = new ArrayList<Rolebypermission>();
            for (Rolebypermission rolebypermissionCollectionNewRolebypermissionToAttach : rolebypermissionCollectionNew) {
                rolebypermissionCollectionNewRolebypermissionToAttach = em.getReference(rolebypermissionCollectionNewRolebypermissionToAttach.getClass(), rolebypermissionCollectionNewRolebypermissionToAttach.getId());
                attachedRolebypermissionCollectionNew.add(rolebypermissionCollectionNewRolebypermissionToAttach);
            }
            rolebypermissionCollectionNew = attachedRolebypermissionCollectionNew;
            rolec.setRolebypermissionCollection(rolebypermissionCollectionNew);
            Collection<Rolebyuser> attachedRolebyuserCollectionNew = new ArrayList<Rolebyuser>();
            for (Rolebyuser rolebyuserCollectionNewRolebyuserToAttach : rolebyuserCollectionNew) {
                rolebyuserCollectionNewRolebyuserToAttach = em.getReference(rolebyuserCollectionNewRolebyuserToAttach.getClass(), rolebyuserCollectionNewRolebyuserToAttach.getId());
                attachedRolebyuserCollectionNew.add(rolebyuserCollectionNewRolebyuserToAttach);
            }
            rolebyuserCollectionNew = attachedRolebyuserCollectionNew;
            rolec.setRolebyuserCollection(rolebyuserCollectionNew);
            rolec = em.merge(rolec);
            for (Rolebypermission rolebypermissionCollectionOldRolebypermission : rolebypermissionCollectionOld) {
                if (!rolebypermissionCollectionNew.contains(rolebypermissionCollectionOldRolebypermission)) {
                    rolebypermissionCollectionOldRolebypermission.setIdRole(null);
                    rolebypermissionCollectionOldRolebypermission = em.merge(rolebypermissionCollectionOldRolebypermission);
                }
            }
            for (Rolebypermission rolebypermissionCollectionNewRolebypermission : rolebypermissionCollectionNew) {
                if (!rolebypermissionCollectionOld.contains(rolebypermissionCollectionNewRolebypermission)) {
                    Rolec oldIdRoleOfRolebypermissionCollectionNewRolebypermission = rolebypermissionCollectionNewRolebypermission.getIdRole();
                    rolebypermissionCollectionNewRolebypermission.setIdRole(rolec);
                    rolebypermissionCollectionNewRolebypermission = em.merge(rolebypermissionCollectionNewRolebypermission);
                    if (oldIdRoleOfRolebypermissionCollectionNewRolebypermission != null && !oldIdRoleOfRolebypermissionCollectionNewRolebypermission.equals(rolec)) {
                        oldIdRoleOfRolebypermissionCollectionNewRolebypermission.getRolebypermissionCollection().remove(rolebypermissionCollectionNewRolebypermission);
                        oldIdRoleOfRolebypermissionCollectionNewRolebypermission = em.merge(oldIdRoleOfRolebypermissionCollectionNewRolebypermission);
                    }
                }
            }
            for (Rolebyuser rolebyuserCollectionOldRolebyuser : rolebyuserCollectionOld) {
                if (!rolebyuserCollectionNew.contains(rolebyuserCollectionOldRolebyuser)) {
                    rolebyuserCollectionOldRolebyuser.setIdRole(null);
                    rolebyuserCollectionOldRolebyuser = em.merge(rolebyuserCollectionOldRolebyuser);
                }
            }
            for (Rolebyuser rolebyuserCollectionNewRolebyuser : rolebyuserCollectionNew) {
                if (!rolebyuserCollectionOld.contains(rolebyuserCollectionNewRolebyuser)) {
                    Rolec oldIdRoleOfRolebyuserCollectionNewRolebyuser = rolebyuserCollectionNewRolebyuser.getIdRole();
                    rolebyuserCollectionNewRolebyuser.setIdRole(rolec);
                    rolebyuserCollectionNewRolebyuser = em.merge(rolebyuserCollectionNewRolebyuser);
                    if (oldIdRoleOfRolebyuserCollectionNewRolebyuser != null && !oldIdRoleOfRolebyuserCollectionNewRolebyuser.equals(rolec)) {
                        oldIdRoleOfRolebyuserCollectionNewRolebyuser.getRolebyuserCollection().remove(rolebyuserCollectionNewRolebyuser);
                        oldIdRoleOfRolebyuserCollectionNewRolebyuser = em.merge(oldIdRoleOfRolebyuserCollectionNewRolebyuser);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rolec.getIdRole();
                if (findRolec(id) == null) {
                    throw new NonexistentEntityException("The rolec with id " + id + " no longer exists.");
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
            Rolec rolec;
            try {
                rolec = em.getReference(Rolec.class, id);
                rolec.getIdRole();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolec with id " + id + " no longer exists.", enfe);
            }
            Collection<Rolebypermission> rolebypermissionCollection = rolec.getRolebypermissionCollection();
            for (Rolebypermission rolebypermissionCollectionRolebypermission : rolebypermissionCollection) {
                rolebypermissionCollectionRolebypermission.setIdRole(null);
                rolebypermissionCollectionRolebypermission = em.merge(rolebypermissionCollectionRolebypermission);
            }
            Collection<Rolebyuser> rolebyuserCollection = rolec.getRolebyuserCollection();
            for (Rolebyuser rolebyuserCollectionRolebyuser : rolebyuserCollection) {
                rolebyuserCollectionRolebyuser.setIdRole(null);
                rolebyuserCollectionRolebyuser = em.merge(rolebyuserCollectionRolebyuser);
            }
            em.remove(rolec);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rolec> findRolecEntities() {
        return findRolecEntities(true, -1, -1);
    }

    public List<Rolec> findRolecEntities(int maxResults, int firstResult) {
        return findRolecEntities(false, maxResults, firstResult);
    }

    private List<Rolec> findRolecEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rolec.class));
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

    public Rolec findRolec(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rolec.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolecCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rolec> rt = cq.from(Rolec.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
