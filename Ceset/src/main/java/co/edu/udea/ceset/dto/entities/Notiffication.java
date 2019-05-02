/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto.entities;

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
@Table(name = "tbl_notiffication")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notiffication.findAll", query = "SELECT n FROM Notiffication n")
    , @NamedQuery(name = "Notiffication.findByIdNotif", query = "SELECT n FROM Notiffication n WHERE n.idNotif = :idNotif")
    , @NamedQuery(name = "Notiffication.findByDescription", query = "SELECT n FROM Notiffication n WHERE n.description = :description")
    , @NamedQuery(name = "Notiffication.findByNotiftype", query = "SELECT n FROM Notiffication n WHERE n.notiftype = :notiftype")})
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
    @Size(max = 200)
    @Column(name = "notiftype")
    private String notiftype;
    @Size(max=200)
    @Column(name="State")
    private String stated;
    @OneToMany(mappedBy = "idNotif", fetch = FetchType.LAZY)
    private Collection<Notifficationbycheck> notifficationbycheckCollection;
    @OneToMany(mappedBy = "idNotif", fetch = FetchType.LAZY)
    private Collection<Notifficationbyrole> notifficationbyroleCollection;

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

    public String getStated() {
        return stated;
    }

    public void setStated(String stated) {
        this.stated = stated;
    }
    
    

    @XmlTransient
    public Collection<Notifficationbycheck> getNotifficationbycheckCollection() {
        return notifficationbycheckCollection;
    }

    public void setNotifficationbycheckCollection(Collection<Notifficationbycheck> notifficationbycheckCollection) {
        this.notifficationbycheckCollection = notifficationbycheckCollection;
    }

    @XmlTransient
    public Collection<Notifficationbyrole> getNotifficationbyroleCollection() {
        return notifficationbyroleCollection;
    }

    public void setNotifficationbyroleCollection(Collection<Notifficationbyrole> notifficationbyroleCollection) {
        this.notifficationbyroleCollection = notifficationbyroleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotif != null ? idNotif.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.Notiffication[ idNotif=" + idNotif + " ]";
    }
    
}
