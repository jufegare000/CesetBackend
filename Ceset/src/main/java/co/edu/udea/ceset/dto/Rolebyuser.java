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
@Table(name = "tbl_rolebyuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolebyuser.findAll", query = "SELECT r FROM Rolebyuser r")
    , @NamedQuery(name = "Rolebyuser.findById", query = "SELECT r FROM Rolebyuser r WHERE r.id = :id")})
public class Rolebyuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "idRole", referencedColumnName = "idRole")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rolec idRole;
    @JoinColumn(name = "idUser", referencedColumnName = "IdUser")
    @ManyToOne(fetch = FetchType.LAZY)
    private User idUser;

    public Rolebyuser() {
    }

    public Rolebyuser(Integer id) {
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

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.Rolebyuser[ id=" + id + " ]";
    }
    
}
