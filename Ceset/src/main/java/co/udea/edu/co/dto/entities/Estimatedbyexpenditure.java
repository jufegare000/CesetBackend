/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.udea.edu.co.dto.entities;

import com.google.gson.annotations.Expose;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "tbl_estimatedbyexpenditure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estimatedbyexpenditure.findAll", query = "SELECT e FROM Estimatedbyexpenditure e"),
    @NamedQuery(name = "Estimatedbyexpenditure.findById", query = "SELECT e FROM Estimatedbyexpenditure e WHERE e.id = :id"),
    @NamedQuery(name = "Estimatedbyexpenditure.findByDescription", query = "SELECT e FROM Estimatedbyexpenditure e WHERE e.description = :description"),
    @NamedQuery(name = "Estimatedbyexpenditure.findByQuantity1", query = "SELECT e FROM Estimatedbyexpenditure e WHERE e.quantity1 = :quantity1"),
    @NamedQuery(name = "Estimatedbyexpenditure.findByMeasureUnit", query = "SELECT e FROM   Estimatedbyexpenditure e WHERE e.measureUnit = :measureUnit"),
    @NamedQuery(name = "Estimatedbyexpenditure.findByQuantity2", query = "SELECT e FROM Estimatedbyexpenditure e WHERE e.quantity2 = :quantity2"),
    @NamedQuery(name = "Estimatedbyexpenditure.findByStimatedValue", query = "SELECT e FROM Estimatedbyexpenditure e WHERE e.stimatedValue = :stimatedValue"),
    @NamedQuery(name = "Estimatedbyexpenditure.findByTypeE", query = "SELECT e FROM Estimatedbyexpenditure e WHERE e.typeE = :typeE"),
    @NamedQuery(name = "Estimatedbyexpenditure.findByTotalValue", query = "SELECT e FROM Estimatedbyexpenditure e WHERE e.totalValue = :totalValue"),
    @NamedQuery(name = "Estimatedbyexpenditure.findByFp", query = "SELECT e FROM Estimatedbyexpenditure e WHERE e.fp = :fp"),
    @NamedQuery(name = "Estimatedbyexpenditure.findByUnitValFP", query = "SELECT e FROM Estimatedbyexpenditure e WHERE e.unitValFP = :unitValFP"),
    @NamedQuery(name = "Estimatedbyexpenditure.findByTotalValFP", query = "SELECT e FROM Estimatedbyexpenditure e WHERE e.totalValFP = :totalValFP"),
    @NamedQuery(name = "Estimatedbyexpenditure.findByObservation", query = "SELECT e FROM Estimatedbyexpenditure e WHERE e.observation = :observation"),
        @NamedQuery(name = "Estimatedbyexpenditure.findLast", query = "SELECT e FROM Estimatedbyexpenditure e ORDER BY e.id DESC")})
public class Estimatedbyexpenditure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Expose
    @Column(name = "description")
    private String description;
    @Expose
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity1")
    private Double quantity1;
    @Size(max = 20)
    @Expose
    @Column(name = "measureUnit")
    private String measureUnit;
    @Expose
    @Column(name = "quantity2")
    private Double quantity2;
    @Column(name = "stimatedValue")
    @Expose
    private Double stimatedValue;
    @Size(max = 2)
    @Column(name = "typeE")
    @Expose
    private String typeE;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalValue")
    @Expose
    private double totalValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FP")
    @Expose
    private double fp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unitValFP")
    @Expose
    private double unitValFP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalValFP")
    @Expose
    private double totalValFP;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10000)
    @Column(name = "observation")
    @Expose
    private String observation;
    @JoinColumn(name = "IdEstimated", referencedColumnName = "IdEstimated")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estimated idEstimated;

    public Estimatedbyexpenditure() {
    }

    public Estimatedbyexpenditure(Integer id) {
        this.id = id;
    }

    public Estimatedbyexpenditure(Integer id, double totalValue, double fp, double unitValFP, double totalValFP, String observation) {
        this.id = id;
        this.totalValue = totalValue;
        this.fp = fp;
        this.unitValFP = unitValFP;
        this.totalValFP = totalValFP;
        this.observation = observation;
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

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getFp() {
        return fp;
    }

    public void setFp(double fp) {
        this.fp = fp;
    }

    public double getUnitValFP() {
        return unitValFP;
    }

    public void setUnitValFP(double unitValFP) {
        this.unitValFP = unitValFP;
    }

    public double getTotalValFP() {
        return totalValFP;
    }

    public void setTotalValFP(double totalValFP) {
        this.totalValFP = totalValFP;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Estimated getIdEstimated() {
        return idEstimated;
    }

    public void setIdEstimated(Estimated idEstimated) {
        this.idEstimated = idEstimated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "co.udea.edu.co.dto.entities.Estimatedbyexpenditure[ id=" + id + " ]";
    }
    
}
