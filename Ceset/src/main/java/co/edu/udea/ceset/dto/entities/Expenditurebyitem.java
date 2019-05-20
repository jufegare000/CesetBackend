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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "tbl_expenditurebyitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Expenditurebyitem.findAll", query = "SELECT e FROM Expenditurebyitem e"),
    @NamedQuery(name = "Expenditurebyitem.findById", query = "SELECT e FROM Expenditurebyitem e WHERE e.id = :id"),
    @NamedQuery(name = "Expenditurebyitem.findByIdItem", query = "SELECT e FROM Expenditurebyitem e WHERE e.idItem = :idItem"),
    @NamedQuery(name = "Expenditurebyitem.findByIdExpend", query = "SELECT e FROM Expenditurebyitem e WHERE e.idExpend = :idExpend")})
public class Expenditurebyitem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "idItem")
    private Integer idItem;
    @Column(name = "IdExpend")
    private Integer idExpend;

    public Expenditurebyitem() {
    }

    public Expenditurebyitem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdExpend() {
        return idExpend;
    }

    public void setIdExpend(Integer idExpend) {
        this.idExpend = idExpend;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expenditurebyitem)) {
            return false;
        }
        Expenditurebyitem other = (Expenditurebyitem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.entities.Expenditurebyitem[ id=" + id + " ]";
    }
    
}
