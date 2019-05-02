/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.udea.ceset.dto.entities;

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
@Table(name = "tbl_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g"),
    @NamedQuery(name = "Groupe.findByIdGroup", query = "SELECT g FROM Groupe g WHERE g.idGroup = :idGroup"),
    @NamedQuery(name = "Groupe.findByNumbers", query = "SELECT g FROM Groupe g WHERE g.numbers = :numbers"),
    @NamedQuery(name = "Groupe.findByInitDate", query = "SELECT g FROM Groupe g WHERE g.initDate = :initDate"),
    @NamedQuery(name = "Groupe.findByFinDate", query = "SELECT g FROM Groupe g WHERE g.finDate = :finDate"),
    @NamedQuery(name = "Groupe.findByStates", query = "SELECT g FROM Groupe g WHERE g.states = :states")})
public class Groupe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGroup")
    private Integer idGroup;
    @Column(name = "numbers")
    private Integer numbers;
    @Column(name = "initDate")
    @Temporal(TemporalType.DATE)
    private Date initDate;
    @Column(name = "finDate")
    @Temporal(TemporalType.DATE)
    private Date finDate;
    @Size(max = 20)
    @Column(name = "states")
    private String states;
    @JoinColumn(name = "IdCohort", referencedColumnName = "IdCohort")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cohort idCohort;

    public Groupe() {
    }

    public Groupe(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getFinDate() {
        return finDate;
    }

    public void setFinDate(Date finDate) {
        this.finDate = finDate;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public Cohort getIdCohort() {
        return idCohort;
    }

    public void setIdCohort(Cohort idCohort) {
        this.idCohort = idCohort;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroup != null ? idGroup.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString() {
        return "co.udea.edu.co.dto.entities.Groupe[ idGroup=" + idGroup + " ]";
    }

}
