/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto;

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
@Table(name = "tbl_notifficationbyrole")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notifficationbyrole.findAll", query = "SELECT n FROM Notifficationbyrole n")
    , @NamedQuery(name = "Notifficationbyrole.findById", query = "SELECT n FROM Notifficationbyrole n WHERE n.id = :id")})
public class Notifficationbyrole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "idRole", referencedColumnName = "idRole")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rolec idRole;
    @JoinColumn(name = "idNotif", referencedColumnName = "IdNotif")
    @ManyToOne(fetch = FetchType.LAZY)
    private Notiffication idNotif;

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

    public Rolec getIdRole() {
        return idRole;
    }

    public void setIdRole(Rolec idRole) {
        this.idRole = idRole;
    }

    public Notiffication getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(Notiffication idNotif) {
        this.idNotif = idNotif;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.Notifficationbyrole[ id=" + id + " ]";
    }
    
}
