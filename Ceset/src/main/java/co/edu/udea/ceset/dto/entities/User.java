/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto.entities;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "tbl_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "User.findByNameUser", query = "SELECT u FROM User u WHERE u.nameUser = :nameUser"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByDateCreation", query = "SELECT u FROM User u WHERE u.dateCreation = :dateCreation"),
    @NamedQuery(name = "User.findByStates", query = "SELECT u FROM User u WHERE u.states = :states")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdUser")
    @Expose
    private Integer idUser;
    @Size(max = 100)
    @Column(name = "nameUser")
    @Expose
    private String nameUser;
    @Size(max = 32)
    @Column(name = "password")
    private String password;
    @Column(name = "dateCreation")
    @Expose
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Size(max = 20)
    @Column(name = "states")
    @Expose
    private String states;
    @JoinColumn(name = "IdPerson", referencedColumnName = "IdPerson")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person idPerson;
    @OneToMany(mappedBy = "idUser", fetch = FetchType.LAZY)
    private Collection<Academicactivity> academicactivityCollection;
    @OneToMany(mappedBy = "idUser", fetch = FetchType.LAZY)
    @Expose
    private Collection<Rolebyuser> rolebyuserCollection;
    @OneToMany(mappedBy = "idUser", fetch = FetchType.LAZY)
    private Collection<Portafolio> portafolioCollection;

    public User() {
    }

    public User(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation() {
        Date dateCreation = new Date();
        this.dateCreation = dateCreation;
    }

    public String getStates() {
        return states;
    }

    public void setStates() {
        String states = "Activo";
        this.states = states;
    }

    public Person getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Person idPerson) {
        this.idPerson = idPerson;
    }

    @XmlTransient
    public Collection<Academicactivity> getAcademicactivityCollection() {
        return academicactivityCollection;
    }

    public void setAcademicactivityCollection(Collection<Academicactivity> academicactivityCollection) {
        this.academicactivityCollection = academicactivityCollection;
    }

    @XmlTransient
    public Collection<Rolebyuser> getRolebyuserCollection() {
        return rolebyuserCollection;
    }

    public void setRolebyuserCollection(Collection<Rolebyuser> rolebyuserCollection) {
        this.rolebyuserCollection = rolebyuserCollection;
    }

    @XmlTransient
    public Collection<Portafolio> getPortafolioCollection() {
        return portafolioCollection;
    }

    public void setPortafolioCollection(Collection<Portafolio> portafolioCollection) {
        this.portafolioCollection = portafolioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.entities.User[ idUser=" + idUser + " ]";
    }
    
}
