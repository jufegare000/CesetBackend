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
@Table(name = "tbl_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
    , @NamedQuery(name = "Item.findByIdItem", query = "SELECT i FROM Item i WHERE i.idItem = :idItem")
    , @NamedQuery(name = "Item.findByDescription", query = "SELECT i FROM Item i WHERE i.description = :description")
    , @NamedQuery(name = "Item.findByTotalValue", query = "SELECT i FROM Item i WHERE i.totalValue = :totalValue")
    , @NamedQuery(name = "Item.findByStimatedValue", query = "SELECT i FROM Item i WHERE i.stimatedValue = :stimatedValue")
    , @NamedQuery(name = "Item.findByRealValue", query = "SELECT i FROM Item i WHERE i.realValue = :realValue")
    , @NamedQuery(name = "Item.findByType", query = "SELECT i FROM Item i WHERE i.type = :type")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Expose
    @Column(name = "IdItem")
    private Integer idItem;

    @Size(max = 200)
    @Expose
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalValue")
    @Expose
    private Double totalValue;
    @Column(name = "stimatedValue")
    @Expose
    private Double stimatedValue;
    @Column(name = "realValue")
    @Expose
    private Double realValue;
    @Size(max = 2)
    @Column(name = "type")
    private String type;
    @Expose
    @OneToMany(mappedBy = "idItem", fetch = FetchType.LAZY)
    private Collection<Expenditurebyitem> expenditurebyitemCollection;
    @OneToMany(mappedBy = "idItem", fetch = FetchType.LAZY)
    private Collection<Estimatedbyitem> estimatedbyitemCollection;
    @OneToMany(mappedBy = "idItem", fetch = FetchType.LAZY)
    @Expose
    private Collection<Budgetbyitem> budgetbyitemCollection;

    public Item() {
    }

    public Item(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Double getStimatedValue() {
        return stimatedValue;
    }

    public void setStimatedValue(Double stimatedValue) {
        this.stimatedValue = stimatedValue;
    }

    public Double getRealValue() {
        return realValue;
    }

    public void setRealValue(Double realValue) {
        this.realValue = realValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<Expenditurebyitem> getExpenditurebyitemCollection() {
        return expenditurebyitemCollection;
    }

    public void setExpenditurebyitemCollection(Collection<Expenditurebyitem> expenditurebyitemCollection) {
        this.expenditurebyitemCollection = expenditurebyitemCollection;
    }

    @XmlTransient
    public Collection<Estimatedbyitem> getEstimatedbyitemCollection() {
        return estimatedbyitemCollection;
    }

    public void setEstimatedbyitemCollection(Collection<Estimatedbyitem> estimatedbyitemCollection) {
        this.estimatedbyitemCollection = estimatedbyitemCollection;
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
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.Item[ idItem=" + idItem + " ]";
    }
    
}
