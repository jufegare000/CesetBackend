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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jufeg
 */
@Entity
@Table(name = "tbl_check")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Check.findAll", query = "SELECT c FROM Check c")
    , @NamedQuery(name = "Check.findByIdCheck", query = "SELECT c FROM Check c WHERE c.idCheck = :idCheck")
    , @NamedQuery(name = "Check.findByIdRealValue", query = "SELECT c FROM Check c WHERE c.idRealValue = :idRealValue")
    , @NamedQuery(name = "Check.findByDescription", query = "SELECT c FROM Check c WHERE c.description = :description")
    , @NamedQuery(name = "Check.findByState", query = "SELECT c FROM Check c WHERE c.state = :state")})
public class Check implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCheck")
    private Integer idCheck;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "idRealValue")
    private Double idRealValue;
    @Size(max = 2000)
    @Column(name = "Description")
    private String description;
    @Size(max = 20)
    @Column(name = "state")
    private String state;
    @JoinColumn(name = "IdExpend", referencedColumnName = "IdExpend")
    @ManyToOne(fetch = FetchType.LAZY)
    private Expenditure idExpend;
    @OneToMany(mappedBy = "idCheck", fetch = FetchType.LAZY)
    private Collection<Notifficationbycheck> notifficationbycheckCollection;

    public Check() {
    }

    public Check(Integer idCheck) {
        this.idCheck = idCheck;
    }

    public Integer getIdCheck() {
        return idCheck;
    }

    public void setIdCheck(Integer idCheck) {
        this.idCheck = idCheck;
    }

    public Double getIdRealValue() {
        return idRealValue;
    }

    public void setIdRealValue(Double idRealValue) {
        this.idRealValue = idRealValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Expenditure getIdExpend() {
        return idExpend;
    }

    public void setIdExpend(Expenditure idExpend) {
        this.idExpend = idExpend;
    }

    @XmlTransient
    public Collection<Notifficationbycheck> getNotifficationbycheckCollection() {
        return notifficationbycheckCollection;
    }

    public void setNotifficationbycheckCollection(Collection<Notifficationbycheck> notifficationbycheckCollection) {
        this.notifficationbycheckCollection = notifficationbycheckCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCheck != null ? idCheck.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.Check[ idCheck=" + idCheck + " ]";
    }
    
}
