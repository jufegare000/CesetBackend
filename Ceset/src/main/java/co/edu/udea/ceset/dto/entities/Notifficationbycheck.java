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
@Table(name = "tbl_notifficationbycheck")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notifficationbycheck.findAll", query = "SELECT n FROM Notifficationbycheck n")
    , @NamedQuery(name = "Notifficationbycheck.findById", query = "SELECT n FROM Notifficationbycheck n WHERE n.id = :id")})
public class Notifficationbycheck implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "idCheck", referencedColumnName = "IdCheck")
    @ManyToOne(fetch = FetchType.LAZY)
    private Check idCheck;
    @JoinColumn(name = "idNotif", referencedColumnName = "IdNotif")
    @ManyToOne(fetch = FetchType.LAZY)
    private Notiffication idNotif;

    public Notifficationbycheck() {
    }

    public Notifficationbycheck(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Check getIdCheck() {
        return idCheck;
    }

    public void setIdCheck(Check idCheck) {
        this.idCheck = idCheck;
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
        return "co.edu.udea.ceset.dto.Notifficationbycheck[ id=" + id + " ]";
    }
    
}
