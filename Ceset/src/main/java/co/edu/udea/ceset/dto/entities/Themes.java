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
 * @author jufeg
 */
@Entity
@Table(name = "tbl_themes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Themes.findAll", query = "SELECT t FROM Themes t")
    , @NamedQuery(name = "Themes.findByIdTheme", query = "SELECT t FROM Themes t WHERE t.idTheme = :idTheme")
    , @NamedQuery(name = "Themes.findByDescription", query = "SELECT t FROM Themes t WHERE t.description = :description")
    , @NamedQuery(name = "Themes.findByResponsible", query = "SELECT t FROM Themes t WHERE t.responsible = :responsible")
    , @NamedQuery(name = "Themes.findByResponsbileDocument", query = "SELECT t FROM Themes t WHERE t.responsbileDocument = :responsbileDocument")
    , @NamedQuery(name = "Themes.findByHours", query = "SELECT t FROM Themes t WHERE t.hours = :hours")
    , @NamedQuery(name = "Themes.findByResponsibleEmail", query = "SELECT t FROM Themes t WHERE t.responsibleEmail = :responsibleEmail")
    , @NamedQuery(name = "Themes.findByContactNumber", query = "SELECT t FROM Themes t WHERE t.contactNumber = :contactNumber")
    , @NamedQuery(name = "Themes.findByInitDate", query = "SELECT t FROM Themes t WHERE t.initDate = :initDate")
    , @NamedQuery(name = "Themes.findByEndDate", query = "SELECT t FROM Themes t WHERE t.endDate = :endDate")
    , @NamedQuery(name = "Themes.findByDependency", query = "SELECT t FROM Themes t WHERE t.dependency = :dependency")
    , @NamedQuery(name = "Themes.findByUniversityLink", query = "SELECT t FROM Themes t WHERE t.universityLink = :universityLink")})
public class Themes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTheme")
    private Integer idTheme;
    @Size(max = 1000)
    @Column(name = "description")
    private String description;
    @Size(max = 1000)
    @Column(name = "responsible")
    private String responsible;
    @Size(max = 20)
    @Column(name = "responsbileDocument")
    private String responsbileDocument;
    @Column(name = "hours")
    private Integer hours;
    @Size(max = 150)
    @Column(name = "responsibleEmail")
    private String responsibleEmail;
    @Size(max = 20)
    @Column(name = "contactNumber")
    private String contactNumber;
    @Column(name = "initDate")
    @Temporal(TemporalType.DATE)
    private Date initDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Size(max = 500)
    @Column(name = "dependency")
    private String dependency;
    @Size(max = 500)
    @Column(name = "universityLink")
    private String universityLink;
    @JoinColumn(name = "idCohort", referencedColumnName = "IdCohort")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cohort idCohort;

    public Themes() {
    }

    public Themes(Integer idTheme) {
        this.idTheme = idTheme;
    }

    public Integer getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(Integer idTheme) {
        this.idTheme = idTheme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getResponsbileDocument() {
        return responsbileDocument;
    }

    public void setResponsbileDocument(String responsbileDocument) {
        this.responsbileDocument = responsbileDocument;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getResponsibleEmail() {
        return responsibleEmail;
    }

    public void setResponsibleEmail(String responsibleEmail) {
        this.responsibleEmail = responsibleEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public String getUniversityLink() {
        return universityLink;
    }

    public void setUniversityLink(String universityLink) {
        this.universityLink = universityLink;
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
        hash += (idTheme != null ? idTheme.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.Themes[ idTheme=" + idTheme + " ]";
    }
    
}
