/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.udea.edu.co.dto.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "tbl_budgetbyexpenditure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Budgetbyexpenditure.findAll", query = "SELECT b FROM Budgetbyexpenditure b"),
    @NamedQuery(name = "Budgetbyexpenditure.findById", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.id = :id"),
    @NamedQuery(name = "Budgetbyexpenditure.findByDescription", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.description = :description"),
    @NamedQuery(name = "Budgetbyexpenditure.findByQuantity1", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.quantity1 = :quantity1"),
    @NamedQuery(name = "Budgetbyexpenditure.findByMeasureUnit", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.measureUnit = :measureUnit"),
    @NamedQuery(name = "Budgetbyexpenditure.findByQuantity2", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.quantity2 = :quantity2"),
    @NamedQuery(name = "Budgetbyexpenditure.findByStimatedValue", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.stimatedValue = :stimatedValue"),
    @NamedQuery(name = "Budgetbyexpenditure.findByTypeE", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.typeE = :typeE"),
    @NamedQuery(name = "Budgetbyexpenditure.findByTotalValue", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.totalValue = :totalValue"),
    @NamedQuery(name = "Budgetbyexpenditure.findByFp", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.fp = :fp"),
    @NamedQuery(name = "Budgetbyexpenditure.findByUnitValFP", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.unitValFP = :unitValFP"),
    @NamedQuery(name = "Budgetbyexpenditure.findByTotalValFP", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.totalValFP = :totalValFP"),
    @NamedQuery(name = "Budgetbyexpenditure.findByObservation1", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.observation1 = :observation1"),
    @NamedQuery(name = "Budgetbyexpenditure.findByObservation2", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.observation2 = :observation2"),
    @NamedQuery(name = "Budgetbyexpenditure.findByChecked", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.checked = :checked"),
    @NamedQuery(name = "Budgetbyexpenditure.findByActualizationDate", query = "SELECT b FROM Budgetbyexpenditure b WHERE b.actualizationDate = :actualizationDate")})
public class Budgetbyexpenditure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity1")
    private Double quantity1;
    @Size(max = 20)
    @Column(name = "measureUnit")
    private String measureUnit;
    @Column(name = "quantity2")
    private Double quantity2;
    @Column(name = "stimatedValue")
    private Double stimatedValue;
    @Size(max = 2)
    @Column(name = "typeE")
    private String typeE;
    @Column(name = "totalValue")
    private Double totalValue;
    @Column(name = "FP")
    private Double fp;
    @Column(name = "unitValFP")
    private Double unitValFP;
    @Column(name = "totalValFP")
    private Double totalValFP;
    @Size(max = 10000)
    @Column(name = "observation1")
    private String observation1;
    @Size(max = 10000)
    @Column(name = "observation2")
    private String observation2;
    @Column(name = "checked")
    private Boolean checked;
    @Column(name = "actualizationDate")
    @Temporal(TemporalType.DATE)
    private Date actualizationDate;
    @JoinColumn(name = "IdBudget", referencedColumnName = "IdBudget")
    @ManyToOne(fetch = FetchType.LAZY)
    private Budget idBudget;

    public Budgetbyexpenditure() {
    }

    public Budgetbyexpenditure(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(Double quantity1) {
        this.quantity1 = quantity1;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public Double getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(Double quantity2) {
        this.quantity2 = quantity2;
    }

    public Double getStimatedValue() {
        return stimatedValue;
    }

    public void setStimatedValue(Double stimatedValue) {
        this.stimatedValue = stimatedValue;
    }

    public String getTypeE() {
        return typeE;
    }

    public void setTypeE(String typeE) {
        this.typeE = typeE;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Double getFp() {
        return fp;
    }

    public void setFp(Double fp) {
        this.fp = fp;
    }

    public Double getUnitValFP() {
        return unitValFP;
    }

    public void setUnitValFP(Double unitValFP) {
        this.unitValFP = unitValFP;
    }

    public Double getTotalValFP() {
        return totalValFP;
    }

    public void setTotalValFP(Double totalValFP) {
        this.totalValFP = totalValFP;
    }

    public String getObservation1() {
        return observation1;
    }

    public void setObservation1(String observation1) {
        this.observation1 = observation1;
    }

    public String getObservation2() {
        return observation2;
    }

    public void setObservation2(String observation2) {
        this.observation2 = observation2;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Date getActualizationDate() {
        return actualizationDate;
    }

    public void setActualizationDate(Date actualizationDate) {
        this.actualizationDate = actualizationDate;
    }

    public Budget getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Budget idBudget) {
        this.idBudget = idBudget;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public String toString() {
        return "co.udea.edu.co.dto.entities.Budgetbyexpenditure[ id=" + id + " ]";
    }
    
}
