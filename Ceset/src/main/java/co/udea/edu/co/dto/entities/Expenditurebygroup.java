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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tbl_expenditurebygroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Expenditurebygroup.findAll", query = "SELECT e FROM Expenditurebygroup e"),
    @NamedQuery(name = "Expenditurebygroup.findById", query = "SELECT e FROM Expenditurebygroup e WHERE e.id = :id"),
    @NamedQuery(name = "Expenditurebygroup.findByIdGroup", query = "SELECT e FROM Expenditurebygroup e WHERE e.idGroup = :idGroup"),
    @NamedQuery(name = "Expenditurebygroup.findByDescription", query = "SELECT e FROM Expenditurebygroup e WHERE e.description = :description"),
    @NamedQuery(name = "Expenditurebygroup.findByQuantity1", query = "SELECT e FROM Expenditurebygroup e WHERE e.quantity1 = :quantity1"),
    @NamedQuery(name = "Expenditurebygroup.findByMeasureUnit", query = "SELECT e FROM Expenditurebygroup e WHERE e.measureUnit = :measureUnit"),
    @NamedQuery(name = "Expenditurebygroup.findByQuantity2", query = "SELECT e FROM Expenditurebygroup e WHERE e.quantity2 = :quantity2"),
    @NamedQuery(name = "Expenditurebygroup.findByStimatedValue", query = "SELECT e FROM Expenditurebygroup e WHERE e.stimatedValue = :stimatedValue"),
    @NamedQuery(name = "Expenditurebygroup.findByTypeE", query = "SELECT e FROM Expenditurebygroup e WHERE e.typeE = :typeE"),
    @NamedQuery(name = "Expenditurebygroup.findByTotalValue", query = "SELECT e FROM Expenditurebygroup e WHERE e.totalValue = :totalValue"),
    @NamedQuery(name = "Expenditurebygroup.findByFp", query = "SELECT e FROM Expenditurebygroup e WHERE e.fp = :fp"),
    @NamedQuery(name = "Expenditurebygroup.findByUnitValFP", query = "SELECT e FROM Expenditurebygroup e WHERE e.unitValFP = :unitValFP"),
    @NamedQuery(name = "Expenditurebygroup.findByTotalValFP", query = "SELECT e FROM Expenditurebygroup e WHERE e.totalValFP = :totalValFP"),
    @NamedQuery(name = "Expenditurebygroup.findByObservation", query = "SELECT e FROM Expenditurebygroup e WHERE e.observation = :observation"),
    @NamedQuery(name = "Expenditurebygroup.findByChecked", query = "SELECT e FROM Expenditurebygroup e WHERE e.checked = :checked"),
    @NamedQuery(name = "Expenditurebygroup.findByStated", query = "SELECT e FROM Expenditurebygroup e WHERE e.stated = :stated"),
    @NamedQuery(name = "Expenditurebygroup.findByModificationDate", query = "SELECT e FROM Expenditurebygroup e WHERE e.modificationDate = :modificationDate")})
public class Expenditurebygroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "idGroup")
    private Integer idGroup;
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
    @Column(name = "observation")
    private String observation;
    @Column(name = "checked")
    private Boolean checked;
    @Size(max = 12)
    @Column(name = "stated")
    private String stated;
    @Column(name = "modificationDate")
    @Temporal(TemporalType.DATE)
    private Date modificationDate;

    public Expenditurebygroup() {
    }

    public Expenditurebygroup(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getStated() {
        return stated;
    }

    public void setStated(String stated) {
        this.stated = stated;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString() {
        return "co.udea.edu.co.dto.entities.Expenditurebygroup[ id=" + id + " ]";
    }
    
}
