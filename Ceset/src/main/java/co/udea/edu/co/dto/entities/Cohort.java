/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.udea.edu.co.dto.entities;

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
 * @author jufeg
 */
@Entity
@Table(name = "tbl_cohort")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cohort.findAll", query = "SELECT c FROM Cohort c")
    , @NamedQuery(name = "Cohort.findByIdCohort", query = "SELECT c FROM Cohort c WHERE c.idCohort = :idCohort")
    , @NamedQuery(name = "Cohort.findByInitDate", query = "SELECT c FROM Cohort c WHERE c.initDate = :initDate")
    , @NamedQuery(name = "Cohort.findByEndDate", query = "SELECT c FROM Cohort c WHERE c.endDate = :endDate")
    , @NamedQuery(name = "Cohort.findByStates", query = "SELECT c FROM Cohort c WHERE c.states = :states")})
public class Cohort implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCohort")
    private Integer idCohort;
    @Column(name = "initDate")
    @Temporal(TemporalType.DATE)
    private Date initDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Size(max = 50)
    @Column(name = "states")
    private String states;
    @OneToMany(mappedBy = "idCohort", fetch = FetchType.LAZY)
    private Collection<Budget> budgetCollection;
    @OneToMany(mappedBy = "idCohort", fetch = FetchType.LAZY)
    private Collection<Themes> themesCollection;
    @JoinColumn(name = "idActivity", referencedColumnName = "IdAcad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Academicactivity idActivity;

    public Cohort() {
    }

    public Cohort(Integer idCohort) {
        this.idCohort = idCohort;
    }

    public Integer getIdCohort() {
        return idCohort;
    }

    public void setIdCohort(Integer idCohort) {
        this.idCohort = idCohort;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
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

    public Academicactivity getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Academicactivity idActivity) {
        this.idActivity = idActivity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCohort != null ? idCohort.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.Cohort[ idCohort=" + idCohort + " ]";
    }
    
}
