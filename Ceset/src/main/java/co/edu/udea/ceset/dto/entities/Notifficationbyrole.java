/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "tbl_notifficationbyrole")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notifficationbyrole.findAll", query = "SELECT n FROM Notifficationbyrole n"),
    @NamedQuery(name = "Notifficationbyrole.findById", query = "SELECT n FROM Notifficationbyrole n WHERE n.id = :id"),
    @NamedQuery(name = "Notifficationbyrole.findByIdRole", query = "SELECT n FROM Notifficationbyrole n WHERE n.idRole = :idRole"),
    @NamedQuery(name = "Notifficationbyrole.findByIdNotif", query = "SELECT n FROM Notifficationbyrole n WHERE n.idNotif = :idNotif")})
public class Notifficationbyrole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "idRole")
    private Integer idRole;
    @Column(name = "idNotif")
    private Integer idNotif;

    public Notifficationbyrole() {
    }

    public Notifficationbyrole(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(Integer idNotif) {
        this.idNotif = idNotif;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notifficationbyrole)) {
            return false;
        }
        Notifficationbyrole other = (Notifficationbyrole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.entities.Notifficationbyrole[ id=" + id + " ]";
    }
    
}
