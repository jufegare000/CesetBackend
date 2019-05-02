/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto.entities;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jufeg
 */
@Entity
@Table(name = "tbl_estimatedbyitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estimatedbyitem.findAll", query = "SELECT e FROM Estimatedbyitem e")
    , @NamedQuery(name = "Estimatedbyitem.findById", query = "SELECT e FROM Estimatedbyitem e WHERE e.id = :id")})
public class Estimatedbyitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Expose
    @JoinColumn(name = "idItem", referencedColumnName = "IdItem")
    @ManyToOne(fetch = FetchType.LAZY)
    private Item idItem;
    @JoinColumn(name = "idEstimated", referencedColumnName = "IdEstimated")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estimated idEstimated;

    public Estimatedbyitem() {
    }

    public Estimatedbyitem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getIdItem() {
        return idItem;
    }

    public void setIdItem(Item idItem) {
        this.idItem = idItem;
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
        return "co.edu.udea.ceset.dto.Estimatedbyitem[ id=" + id + " ]";
    }
    
}
