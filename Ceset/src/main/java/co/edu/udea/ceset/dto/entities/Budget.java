/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto.entities;

import com.google.gson.annotations.Expose;

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
 * @author Juan
 */
@Entity
@Table(name = "tbl_budget")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Budget.findAll", query = "SELECT b FROM Budget b"),
    @NamedQuery(name = "Budget.findByIdBudget", query = "SELECT b FROM Budget b WHERE b.idBudget = :idBudget"),
    @NamedQuery(name = "Budget.findByTotalRealBudget", query = "SELECT b FROM Budget b WHERE b.totalRealBudget = :totalRealBudget"),
    @NamedQuery(name = "Budget.findByMprovisedBudget", query = "SELECT b FROM Budget b WHERE b.mprovisedBudget = :mprovisedBudget"),
    @NamedQuery(name = "Budget.findByContributionsUdeA", query = "SELECT b FROM Budget b WHERE b.contributionsUdeA = :contributionsUdeA"),
    @NamedQuery(name = "Budget.findByContributionsFaculty", query = "SELECT b FROM Budget b WHERE b.contributionsFaculty = :contributionsFaculty"),
        @NamedQuery(name = "Budget.findLast", query = "SELECT b FROM Budget b ORDER BY b.idBudget DESC")})
public class Budget implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdBudget")
    @Expose
    private Integer idBudget;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalRealBudget")
    @Expose
    private Double totalRealBudget;
    @Column(name = "mprovisedBudget")
    @Expose
    private Double mprovisedBudget;
    @Column(name = "contributionsUdeA")
    @Expose
    private Double contributionsUdeA;
    @Column(name = "contributionsFaculty")
    @Expose
    private Double contributionsFaculty;
    @OneToMany(mappedBy = "idBudget", fetch = FetchType.LAZY)
    @Expose
    private Collection<Budgetbyexpenditure> budgetbyexpenditureCollection;
    @JoinColumn(name = "idGroup", referencedColumnName = "idGroup")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Groupe idGroup;

    public Budget() {
    }

    public Budget(int idBdg) {
        this.setIdBudget(idBdg);
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

    @XmlTransient
    public Collection<Budgetbyexpenditure> getBudgetbyexpenditureCollection() {
        return budgetbyexpenditureCollection;
    }

    public void setBudgetbyexpenditureCollection(Collection<Budgetbyexpenditure> budgetbyexpenditureCollection) {
        this.budgetbyexpenditureCollection = budgetbyexpenditureCollection;
    }

    public Groupe getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Groupe idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBudget != null ? idBudget.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Budget)) {
            return false;
        }
        Budget other = (Budget) object;
        if ((this.idBudget == null && other.idBudget != null) || (this.idBudget != null && !this.idBudget.equals(other.idBudget))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.entities.Budget[ idBudget=" + idBudget + " ]";
    }
    
}
