/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto;

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
@Table(name = "tbl_estimated")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estimated.findAll", query = "SELECT e FROM Estimated e")
    , @NamedQuery(name = "Estimated.findByIdEstimated", query = "SELECT e FROM Estimated e WHERE e.idEstimated = :idEstimated")
    , @NamedQuery(name = "Estimated.findByTotalBudget", query = "SELECT e FROM Estimated e WHERE e.totalBudget = :totalBudget")
    , @NamedQuery(name = "Estimated.findByImprovised", query = "SELECT e FROM Estimated e WHERE e.improvised = :improvised")
    , @NamedQuery(name = "Estimated.findByContributionsUdeA", query = "SELECT e FROM Estimated e WHERE e.contributionsUdeA = :contributionsUdeA")
    , @NamedQuery(name = "Estimated.findByContributionsFaculty", query = "SELECT e FROM Estimated e WHERE e.contributionsFaculty = :contributionsFaculty")})
public class Estimated implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdEstimated")
    private Integer idEstimated;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalBudget")
    private Double totalBudget;
    @Column(name = "improvised")
    private Double improvised;
    @Column(name = "contributionsUdeA")
    private Double contributionsUdeA;
    @Column(name = "contributionsFaculty")
    private Double contributionsFaculty;
    @JoinColumn(name = "idAcad", referencedColumnName = "IdAcad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Academicactivity idAcad;
    @OneToMany(mappedBy = "idEstimated", fetch = FetchType.LAZY)
    private Collection<Estimatedbyitem> estimatedbyitemCollection;

    public Estimated() {
    }

    public Estimated(Integer idEstimated) {
        this.idEstimated = idEstimated;
    }

    public Integer getIdEstimated() {
        return idEstimated;
    }

    public void setIdEstimated(Integer idEstimated) {
        this.idEstimated = idEstimated;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Double getImprovised() {
        return improvised;
    }

    public void setImprovised(Double improvised) {
        this.improvised = improvised;
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

    public Academicactivity getIdAcad() {
        return idAcad;
    }

    public void setIdAcad(Academicactivity idAcad) {
        this.idAcad = idAcad;
    }

    @XmlTransient
    public Collection<Estimatedbyitem> getEstimatedbyitemCollection() {
        return estimatedbyitemCollection;
    }

    public void setEstimatedbyitemCollection(Collection<Estimatedbyitem> estimatedbyitemCollection) {
        this.estimatedbyitemCollection = estimatedbyitemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstimated != null ? idEstimated.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.Estimated[ idEstimated=" + idEstimated + " ]";
    }
    
}
