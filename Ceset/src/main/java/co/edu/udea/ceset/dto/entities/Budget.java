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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jufeg
 */
@Entity
@Table(name = "tbl_budget")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Budget.findAll", query = "SELECT b FROM Budget b")
    , @NamedQuery(name = "Budget.findByIdBudget", query = "SELECT b FROM Budget b WHERE b.idBudget = :idBudget")
    , @NamedQuery(name = "Budget.findByTotalRealBudget", query = "SELECT b FROM Budget b WHERE b.totalRealBudget = :totalRealBudget")
    , @NamedQuery(name = "Budget.findByMprovisedBudget", query = "SELECT b FROM Budget b WHERE b.mprovisedBudget = :mprovisedBudget")
    , @NamedQuery(name = "Budget.findByContributionsUdeA", query = "SELECT b FROM Budget b WHERE b.contributionsUdeA = :contributionsUdeA")
    , @NamedQuery(name = "Budget.findByContributionsFaculty", query = "SELECT b FROM Budget b WHERE b.contributionsFaculty = :contributionsFaculty")})
public class Budget implements Serializable {
    @OneToMany(mappedBy = "idBudget", fetch = FetchType.LAZY)
    private Collection<Budgetbyexpenditure> budgetbyexpenditureCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdBudget")
    private Integer idBudget;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalRealBudget")
    private Double totalRealBudget;
    @Column(name = "mprovisedBudget")
    private Double mprovisedBudget;
    @Column(name = "contributionsUdeA")
    private Double contributionsUdeA;
    @Column(name = "contributionsFaculty")
    private Double contributionsFaculty;
    @JoinColumn(name = "idCohort", referencedColumnName = "IdCohort")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cohort idCohort;
    @JoinColumn(name = "idActivity", referencedColumnName = "IdAcad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Academicactivity idActivity;
    @OneToMany(mappedBy = "idBudget", fetch = FetchType.LAZY)
    private Collection<Budgetbyitem> budgetbyitemCollection;

    public Budget() {
    }

    public Budget(Integer idBudget) {
        this.idBudget = idBudget;
    }

    public Integer getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Integer idBudget) {
        this.idBudget = idBudget;
    }

    public Double getTotalRealBudget() {
        return totalRealBudget;
    }

    public void setTotalRealBudget(Double totalRealBudget) {
        this.totalRealBudget = totalRealBudget;
    }

    public Double getMprovisedBudget() {
        return mprovisedBudget;
    }

    public void setMprovisedBudget(Double mprovisedBudget) {
        this.mprovisedBudget = mprovisedBudget;
    }

    public Double getContributionsUdeA() {
        return contributionsUdeA;
    }

    public void setContributionsUdeA(Double contributionsUdeA) {
        this.contributionsUdeA = contributionsUdeA;
    }

    public Double getContributionsFaculty() {
        return contributionsFaculty;
    }

    public void setContributionsFaculty(Double contributionsFaculty) {
        this.contributionsFaculty = contributionsFaculty;
    }

    public Cohort getIdCohort() {
        return idCohort;
    }

    public void setIdCohort(Cohort idCohort) {
        this.idCohort = idCohort;
    }

    public Academicactivity getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Academicactivity idActivity) {
        this.idActivity = idActivity;
    }

    @XmlTransient
    public Collection<Budgetbyitem> getBudgetbyitemCollection() {
        return budgetbyitemCollection;
    }

    public void setBudgetbyitemCollection(Collection<Budgetbyitem> budgetbyitemCollection) {
        this.budgetbyitemCollection = budgetbyitemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBudget != null ? idBudget.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.Budget[ idBudget=" + idBudget + " ]";
    }

    @XmlTransient
    public Collection<Budgetbyexpenditure> getBudgetbyexpenditureCollection() {
        return budgetbyexpenditureCollection;
    }

    public void setBudgetbyexpenditureCollection(Collection<Budgetbyexpenditure> budgetbyexpenditureCollection) {
        this.budgetbyexpenditureCollection = budgetbyexpenditureCollection;
    }
    
}
