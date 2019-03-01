/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jufeg
 */
@Entity
@Table(name = "tbl_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser")
    , @NamedQuery(name = "User.findByNameUser", query = "SELECT u FROM User u WHERE u.nameUser = :nameUser")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByDateCreation", query = "SELECT u FROM User u WHERE u.dateCreation = :dateCreation")
    , @NamedQuery(name = "User.findByStates", query = "SELECT u FROM User u WHERE u.states = :states")})
public class User implements Serializable {

    @OneToMany(mappedBy = "idUser", fetch = FetchType.LAZY)
    private Collection<Academicactivity> academicactivityCollection;

    @OneToMany(mappedBy = "idUser", fetch = FetchType.LAZY)
    @Expose
    private Collection<Rolebyuser> rolebyuserCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdUser")
    private Integer idUser;
    @Size(max = 15)
    @Column(name = "nameUser")
    private String nameUser;
    @Size(max = 32)
    @Column(name = "password")
    private String password;
    @Column(name = "dateCreation")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-mm-dd hh:mm:ss")
    private Date dateCreation;
    @Size(max = 20)
    @Column(name = "states")
    private String states;
    @JoinColumn(name = "IdPerson", referencedColumnName = "IdPerson")
    @ManyToOne(fetch = FetchType.LAZY)
    @Expose
    private Person idPerson;
    
    @Transient
    private List<Rolec> roles;

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
        this.dateCreation = new Date();
    }

    public String getStates() {
        return states;
    }

    public void setStates() {
        this.states = "En espera";
    }

    public Person getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Person idPerson) {
        this.idPerson = idPerson;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

   
    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.User[ idUser=" + idUser + " ]";
    }
    
    public List<Rolec> getRoles(){
        //lógica para obtener roles
        return roles;
    }

    public void setRoles(List<Rolec> roles){
        this.roles = roles;
    }
    @XmlTransient
    public Collection<Rolebyuser> getRolebyuserCollection() {
        return rolebyuserCollection;
    }

    public void setRolebyuserCollection(Collection<Rolebyuser> rolebyuserCollection) {
        this.rolebyuserCollection = rolebyuserCollection;
    }

    @XmlTransient
    public Collection<Academicactivity> getAcademicactivityCollection() {
        return academicactivityCollection;
    }

    public void setAcademicactivityCollection(Collection<Academicactivity> academicactivityCollection) {
        this.academicactivityCollection = academicactivityCollection;
    }
}
