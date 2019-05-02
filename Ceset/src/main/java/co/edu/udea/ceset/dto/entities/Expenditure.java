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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jufeg
 */
@Entity
@Table(name = "tbl_expenditure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Expenditure.findAll", query = "SELECT e FROM Expenditure e")
    , @NamedQuery(name = "Expenditure.findByIdExpend", query = "SELECT e FROM Expenditure e WHERE e.idExpend = :idExpend")
    , @NamedQuery(name = "Expenditure.findByDescription", query = "SELECT e FROM Expenditure e WHERE e.description = :description")
    , @NamedQuery(name = "Expenditure.findByQuantity1", query = "SELECT e FROM Expenditure e WHERE e.quantity1 = :quantity1")
    , @NamedQuery(name = "Expenditure.findByMeasureUnit", query = "SELECT e FROM Expenditure e WHERE e.measureUnit = :measureUnit")
    , @NamedQuery(name = "Expenditure.findByQuantity2", query = "SELECT e FROM Expenditure e WHERE e.quantity2 = :quantity2")
    , @NamedQuery(name = "Expenditure.findByStimatedValue", query = "SELECT e FROM Expenditure e WHERE e.stimatedValue = :stimatedValue")
    , @NamedQuery(name = "Expenditure.findByTypeE", query = "SELECT e FROM Expenditure e WHERE e.typeE = :typeE")})
public class Expenditure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdExpend")
    private Integer idExpend;
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
    @OneToMany(mappedBy = "idExpend", fetch = FetchType.LAZY)
    private Collection<Expenditurebyitem> expenditurebyitemCollection;
    @OneToMany(mappedBy = "idExpend", fetch = FetchType.LAZY)
    private Collection<Check> checkCollection;

    public Expenditure() {
    }

    public Expenditure(Integer idExpend) {
        this.idExpend = idExpend;
    }

    public Integer getIdExpend() {
        return idExpend;
    }

    public void setIdExpend(Integer idExpend) {
        this.idExpend = idExpend;
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

    @XmlTransient
    public Collection<Expenditurebyitem> getExpenditurebyitemCollection() {
        return expenditurebyitemCollection;
    }

    public void setExpenditurebyitemCollection(Collection<Expenditurebyitem> expenditurebyitemCollection) {
        this.expenditurebyitemCollection = expenditurebyitemCollection;
    }

    @XmlTransient
    public Collection<Check> getCheckCollection() {
        return checkCollection;
    }

    public void setCheckCollection(Collection<Check> checkCollection) {
        this.checkCollection = checkCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExpend != null ? idExpend.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.Expenditure[ idExpend=" + idExpend + " ]";
    }
    
}
