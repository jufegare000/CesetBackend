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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "tbl_notiffication")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notiffication.findAll", query = "SELECT n FROM Notiffication n"),
    @NamedQuery(name = "Notiffication.findByIdNotif", query = "SELECT n FROM Notiffication n WHERE n.idNotif = :idNotif"),
    @NamedQuery(name = "Notiffication.findByDescription", query = "SELECT n FROM Notiffication n WHERE n.description = :description"),
    @NamedQuery(name = "Notiffication.findByNotiftype", query = "SELECT n FROM Notiffication n WHERE n.notiftype = :notiftype")})
public class Notiffication implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdNotif")
    private Integer idNotif;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Size(max = 12)
    @Column(name = "notiftype")
    private String notiftype;

    public Notiffication() {
    }

    public Notiffication(Integer idNotif) {
        this.idNotif = idNotif;
    }

    public Integer getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(Integer idNotif) {
        this.idNotif = idNotif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotiftype() {
        return notiftype;
    }

    public void setNotiftype(String notiftype) {
        this.notiftype = notiftype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotif != null ? idNotif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notiffication)) {
            return false;
        }
        Notiffication other = (Notiffication) object;
        if ((this.idNotif == null && other.idNotif != null) || (this.idNotif != null && !this.idNotif.equals(other.idNotif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.entities.Notiffication[ idNotif=" + idNotif + " ]";
    }
    
}
