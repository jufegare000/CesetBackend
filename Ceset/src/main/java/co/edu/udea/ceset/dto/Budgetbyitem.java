/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto;

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
@Table(name = "tbl_budgetbyitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Budgetbyitem.findAll", query = "SELECT b FROM Budgetbyitem b")
    , @NamedQuery(name = "Budgetbyitem.findById", query = "SELECT b FROM Budgetbyitem b WHERE b.id = :id")})
public class Budgetbyitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idBudget", referencedColumnName = "IdBudget")
    @ManyToOne(fetch = FetchType.LAZY)
    private Budget idBudget;
    @JoinColumn(name = "idItem", referencedColumnName = "IdItem")
    @ManyToOne(fetch = FetchType.LAZY)
    private Item idItem;

    public Budgetbyitem() {
    }

    public Budgetbyitem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Budget getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Budget idBudget) {
        this.idBudget = idBudget;
    }

    public Item getIdItem() {
        return idItem;
    }

    public void setIdItem(Item idItem) {
        this.idItem = idItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.Budgetbyitem[ id=" + id + " ]";
    }
    
}