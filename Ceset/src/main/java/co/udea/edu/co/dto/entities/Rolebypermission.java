/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.udea.edu.co.dto.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jufeg
 */
@Entity
@Table(name = "tbl_rolebypermission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolebypermission.findAll", query = "SELECT r FROM Rolebypermission r")
    , @NamedQuery(name = "Rolebypermission.findById", query = "SELECT r FROM Rolebypermission r WHERE r.id = :id")})
public class Rolebypermission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idRole", referencedColumnName = "idRole")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rolec idRole;
    @JoinColumn(name = "idPermission", referencedColumnName = "idPermission")
    @ManyToOne(fetch = FetchType.LAZY)
    @Expose
    private Permission idPermission;

    public Rolebypermission() {
    }

    public Rolebypermission(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rolec getIdRole() {
        return idRole;
    }

    public void setIdRole(Rolec idRole) {
        this.idRole = idRole;
    }

    public Permission getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Permission idPermission) {
        this.idPermission = idPermission;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.Rolebypermission[ id=" + id + " ]";
    }
    
}
