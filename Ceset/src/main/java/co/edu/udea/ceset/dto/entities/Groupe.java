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
import javax.persistence.CascadeType;
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
@Table(name = "tbl_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g"),
    @NamedQuery(name = "Groupe.findByIdGroup", query = "SELECT g FROM Groupe g WHERE g.idGroup = :idGroup"),
    @NamedQuery(name = "Groupe.findByNumbers", query = "SELECT g FROM Groupe g WHERE g.numbers = :numbers"),
    @NamedQuery(name = "Groupe.findByInitDate", query = "SELECT g FROM Groupe g WHERE g.initDate = :initDate"),
    @NamedQuery(name = "Groupe.findByFinDate", query = "SELECT g FROM Groupe g WHERE g.finDate = :finDate"),
    @NamedQuery(name = "Groupe.findByStates", query = "SELECT g FROM Groupe g WHERE g.states = :states"),
    @NamedQuery(name = "Groupe.findByClassroom", query = "SELECT g FROM Groupe g WHERE g.classroom = :classroom"),
    @NamedQuery(name = "Groupe.findBySchedule", query = "SELECT g FROM Groupe g WHERE g.schedule = :schedule")})
public class Groupe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGroup")
    @Expose
    private Integer idGroup;
    @Column(name = "numbers")
    @Expose
    private Integer numbers;
    @Column(name = "initDate")
    @Temporal(TemporalType.DATE)
    @Expose
    private Date initDate;
    @Column(name = "finDate")
    @Temporal(TemporalType.DATE)
    @Expose
    private Date finDate;
    @Size(max = 20)
    @Column(name = "states")
    @Expose
    private String states;
    @Size(max = 100)
    @Column(name = "classroom")
    @Expose
    private String classroom;
    @Size(max = 1000)
    @Column(name = "schedule")
    @Expose
    private String schedule;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGroup", fetch = FetchType.LAZY)
    @Expose
    private Collection<Budget> budgetCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGroup", fetch = FetchType.LAZY)
    @Expose
    private Collection<Themes> themesCollection;
    @JoinColumn(name = "idAcad", referencedColumnName = "IdAcad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Academicactivity idAcad;

    public Groupe() {
    }

    public Groupe(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getFinDate() {
        return finDate;
    }

    public void setFinDate(Date finDate) {
        this.finDate = finDate;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @XmlTransient
    public Collection<Budget> getBudgetCollection() {
        return budgetCollection;
    }

    public void setBudgetCollection(Collection<Budget> budgetCollection) {
        this.budgetCollection = budgetCollection;
    }

    @XmlTransient
    public Collection<Themes> getThemesCollection() {
        return themesCollection;
    }

    public void setThemesCollection(Collection<Themes> themesCollection) {
        this.themesCollection = themesCollection;
    }

    public Academicactivity getIdAcad() {
        return idAcad;
    }

    public void setIdAcad(Academicactivity idAcad) {
        this.idAcad = idAcad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroup != null ? idGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if ((this.idGroup == null && other.idGroup != null) || (this.idGroup != null && !this.idGroup.equals(other.idGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.entities.Groupe[ idGroup=" + idGroup + " ]";
    }
    
}
