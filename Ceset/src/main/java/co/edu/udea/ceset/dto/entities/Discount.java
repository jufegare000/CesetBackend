/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "tbl_discount")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Discount.findAll", query = "SELECT d FROM Discount d"),
    @NamedQuery(name = "Discount.findByIdDiscount", query = "SELECT d FROM Discount d WHERE d.idDiscount = :idDiscount"),
    @NamedQuery(name = "Discount.findByIdAcad", query = "SELECT d FROM Discount d WHERE d.idAcad = :idAcad"),
    @NamedQuery(name = "Discount.findByDescription", query = "SELECT d FROM Discount d WHERE d.description = :description"),
    @NamedQuery(name = "Discount.findByQuantity", query = "SELECT d FROM Discount d WHERE d.quantity = :quantity"),
    @NamedQuery(name = "Discount.findByValuedis", query = "SELECT d FROM Discount d WHERE d.valuedis = :valuedis"),
    @NamedQuery(name = "Discount.findByTotal", query = "SELECT d FROM Discount d WHERE d.total = :total")})
public class Discount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdDiscount")
    private Integer idDiscount;
    @Column(name = "idAcad")
    private Integer idAcad;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valuedis")
    private Double valuedis;
    @Column(name = "total")
    private Double total;

    public Discount() {
    }

    public Discount(Integer idDiscount) {
        this.idDiscount = idDiscount;
    }

    public Integer getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(Integer idDiscount) {
        this.idDiscount = idDiscount;
    }

    public Integer getIdAcad() {
        return idAcad;
    }

    public void setIdAcad(Integer idAcad) {
        this.idAcad = idAcad;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getValuedis() {
        return valuedis;
    }

    public void setValuedis(Double valuedis) {
        this.valuedis = valuedis;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiscount != null ? idDiscount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Discount)) {
            return false;
        }
        Discount other = (Discount) object;
        if ((this.idDiscount == null && other.idDiscount != null) || (this.idDiscount != null && !this.idDiscount.equals(other.idDiscount))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.entities.Discount[ idDiscount=" + idDiscount + " ]";
    }
    
}
