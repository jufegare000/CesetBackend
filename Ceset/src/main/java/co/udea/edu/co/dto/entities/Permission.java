/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.udea.edu.co.dto.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jufeg
 */
@Entity
@Table(name = "tbl_permission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permission.findAll", query = "SELECT p FROM Permission p")
    , @NamedQuery(name = "Permission.findByIdPermission", query = "SELECT p FROM Permission p WHERE p.idPermission = :idPermission")
    , @NamedQuery(name = "Permission.findByShortDescription", query = "SELECT p FROM Permission p WHERE p.shortDescription = :shortDescription")
    , @NamedQuery(name = "Permission.findByDescripcion", query = "SELECT p FROM Permission p WHERE p.descripcion = :descripcion")})
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPermission")
    @Expose
    private Integer idPermission;
    @Size(max = 10)
    @Column(name = "shortDescription")
    @Expose
    private String shortDescription;
    @Size(max = 100)
    @Column(name = "descripcion")
    @Expose
    private String descripcion;
    @OneToMany(mappedBy = "idPermission", fetch = FetchType.LAZY)
    private Collection<Rolebypermission> rolebypermissionCollection;

    public Permission() {
    }

    public Permission(Integer idPermission) {
        this.idPermission = idPermission;
    }

    public Integer getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Integer idPermission) {
        this.idPermission = idPermission;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Rolebypermission> getRolebypermissionCollection() {
        return rolebypermissionCollection;
    }

    public void setRolebypermissionCollection(Collection<Rolebypermission> rolebypermissionCollection) {
        this.rolebypermissionCollection = rolebypermissionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermission != null ? idPermission.hashCode() : 0);
        return hash;
    }

    

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.Permission[ idPermission=" + idPermission + " ]";
    }
    
}
