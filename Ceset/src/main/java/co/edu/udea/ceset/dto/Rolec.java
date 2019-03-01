/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jufeg
 */
@Entity
@Table(name = "tbl_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolec.findAll", query = "SELECT r FROM Rolec r")
    , @NamedQuery(name = "Rolec.findByIdRole", query = "SELECT r FROM Rolec r WHERE r.idRole = :idRole")
    , @NamedQuery(name = "Rolec.findByDescription", query = "SELECT r FROM Rolec r WHERE r.description = :description")
    , @NamedQuery(name = "Rolec.findByCreatedAt", query = "SELECT r FROM Rolec r WHERE r.createdAt = :createdAt")
    , @NamedQuery(name = "Rolec.findByUpdatedAt", query = "SELECT r FROM Rolec r WHERE r.updatedAt = :updatedAt")
    , @NamedQuery(name = "Rolec.findByStates", query = "SELECT r FROM Rolec r WHERE r.states = :states")})
public class Rolec implements Serializable {

    @OneToMany(mappedBy = "idRole", fetch = FetchType.LAZY)
    private Collection<Notifficationbyrole> notifficationbyroleCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRole")
    @Expose
    private Integer idRole;
    @Size(max = 50)
    @Column(name = "description")
    @Expose
    private String description;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.DATE)
    @Expose
    private Date createdAt;
    @Column(name = "UpdatedAt")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    @Size(max = 20)
    @Column(name = "States")
    @Expose
    private String states;
    @OneToMany(mappedBy = "idRole", fetch = FetchType.LAZY)
    private Collection<Rolebypermission> rolebypermissionCollection;
    @OneToMany(mappedBy = "idRole", fetch = FetchType.LAZY)
    private Collection<Rolebyuser> rolebyuserCollection;

    

    public Rolec() {
    }

    public Rolec(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    @XmlTransient
    public Collection<Rolebypermission> getRolebypermissionCollection() {
        return rolebypermissionCollection;
    }

    public void setRolebypermissionCollection(Collection<Rolebypermission> rolebypermissionCollection) {
        this.rolebypermissionCollection = rolebypermissionCollection;
    }

    @XmlTransient
    public Collection<Rolebyuser> getRolebyuserCollection() {
        return rolebyuserCollection;
    }

    public void setRolebyuserCollection(Collection<Rolebyuser> rolebyuserCollection) {
        this.rolebyuserCollection = rolebyuserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRole != null ? idRole.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "{id:"+ String.valueOf(idRole)+",descripcion:"+description+"}";
    }

/*    public String sqlDateFormat(Date date) {
        return this.dateFormat.format(date);
    }*/

    @XmlTransient
    public Collection<Notifficationbyrole> getNotifficationbyroleCollection() {
        return notifficationbyroleCollection;
    }

    public void setNotifficationbyroleCollection(Collection<Notifficationbyrole> notifficationbyroleCollection) {
        this.notifficationbyroleCollection = notifficationbyroleCollection;
    }
}
