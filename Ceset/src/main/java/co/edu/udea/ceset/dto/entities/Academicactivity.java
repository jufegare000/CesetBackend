/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "tbl_academicactivity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Academicactivity.findAll", query = "SELECT a FROM Academicactivity a"),
    @NamedQuery(name = "Academicactivity.findByIdAcad", query = "SELECT a FROM Academicactivity a WHERE a.idAcad = :idAcad"),
    @NamedQuery(name = "Academicactivity.findByNameActivity", query = "SELECT a FROM Academicactivity a WHERE a.nameActivity = :nameActivity"),
    @NamedQuery(name = "Academicactivity.findByActivityType", query = "SELECT a FROM Academicactivity a WHERE a.activityType = :activityType"),
    @NamedQuery(name = "Academicactivity.findByDependency", query = "SELECT a FROM Academicactivity a WHERE a.dependency = :dependency"),
    @NamedQuery(name = "Academicactivity.findByCodReune", query = "SELECT a FROM Academicactivity a WHERE a.codReune = :codReune"),
    @NamedQuery(name = "Academicactivity.findBySigepCode", query = "SELECT a FROM Academicactivity a WHERE a.sigepCode = :sigepCode"),
    @NamedQuery(name = "Academicactivity.findByActaCode", query = "SELECT a FROM Academicactivity a WHERE a.actaCode = :actaCode"),
    @NamedQuery(name = "Academicactivity.findByObservaciones", query = "SELECT a FROM Academicactivity a WHERE a.observaciones = :observaciones"),
    @NamedQuery(name = "Academicactivity.findByInitDateact", query = "SELECT a FROM Academicactivity a WHERE a.initDateact = :initDateact"),
    @NamedQuery(name = "Academicactivity.findByEndDateact", query = "SELECT a FROM Academicactivity a WHERE a.endDateact = :endDateact"),
    @NamedQuery(name = "Academicactivity.findByInvestigationGroup", query = "SELECT a FROM Academicactivity a WHERE a.investigationGroup = :investigationGroup"),
    @NamedQuery(name = "Academicactivity.findByCoordinatorName", query = "SELECT a FROM Academicactivity a WHERE a.coordinatorName = :coordinatorName"),
    @NamedQuery(name = "Academicactivity.findByContactTelephone", query = "SELECT a FROM Academicactivity a WHERE a.contactTelephone = :contactTelephone"),
    @NamedQuery(name = "Academicactivity.findByContactEmail", query = "SELECT a FROM Academicactivity a WHERE a.contactEmail = :contactEmail"),
    @NamedQuery(name = "Academicactivity.findByDurationMonths", query = "SELECT a FROM Academicactivity a WHERE a.durationMonths = :durationMonths"),
    @NamedQuery(name = "Academicactivity.findByContractType", query = "SELECT a FROM Academicactivity a WHERE a.contractType = :contractType"),
    @NamedQuery(name = "Academicactivity.findByContractEntity", query = "SELECT a FROM Academicactivity a WHERE a.contractEntity = :contractEntity"),
    @NamedQuery(name = "Academicactivity.findByContractInit", query = "SELECT a FROM Academicactivity a WHERE a.contractInit = :contractInit"),
    @NamedQuery(name = "Academicactivity.findByContractEnd", query = "SELECT a FROM Academicactivity a WHERE a.contractEnd = :contractEnd"),
    @NamedQuery(name = "Academicactivity.findByCreationDate", query = "SELECT a FROM Academicactivity a WHERE a.creationDate = :creationDate"),
    @NamedQuery(name = "Academicactivity.findByState", query = "SELECT a FROM Academicactivity a WHERE a.state = :state"),
    @NamedQuery(name = "Academicactivity.findLast", query = "SELECT e FROM Academicactivity e ORDER BY e.idAcad DESC")})
public class Academicactivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdAcad")
    @Expose
    private Integer idAcad;
    @Size(max = 300)
    @Column(name = "nameActivity")
    @Expose
    private String nameActivity;
    @Size(max = 150)
    @Column(name = "activityType")
    @Expose
    private String activityType;
    @Size(max = 150)
    @Column(name = "dependency")
    @Expose
    private String dependency;
    @Size(max = 20)
    @Column(name = "codReune")
    @Expose
    private String codReune;
    @Size(max = 20)
    @Column(name = "sigepCode")
    @Expose
    private String sigepCode;
    @Size(max = 20)
    @Column(name = "actaCode")
    @Expose
    private String actaCode;
    @Size(max = 1000)
    @Column(name = "observaciones")
    @Expose
    private String observaciones;
    @Column(name = "initDateact")
    @Temporal(TemporalType.DATE)
    @Expose
    private Date initDateact;
    @Column(name = "endDateact")
    @Temporal(TemporalType.DATE)
    @Expose
    private Date endDateact;
    @Size(max = 200)
    @Column(name = "investigationGroup")
    @Expose
    private String investigationGroup;
    @Size(max = 400)
    @Column(name = "coordinatorName")
    @Expose
    private String coordinatorName;
    @Size(max = 15)
    @Column(name = "contactTelephone")
    @Expose
    private String contactTelephone;
    @Size(max = 150)
    @Column(name = "contactEmail")
    @Expose
    private String contactEmail;
    @Column(name = "durationMonths")
    @Expose
    private Integer durationMonths;
    @Size(max = 100)
    @Column(name = "contractType")
    @Expose
    private String contractType;
    @Size(max = 100)
    @Column(name = "contractEntity")
    @Expose
    private String contractEntity;
    @Column(name = "contractInit")
    @Temporal(TemporalType.DATE)
    @Expose
    private Date contractInit;
    @Column(name = "contractEnd")
    @Temporal(TemporalType.DATE)
    @Expose
    private Date contractEnd;
    @Column(name = "creationDate")
    @Temporal(TemporalType.DATE)
    @Expose
    private Date creationDate;
    @Size(max = 50)
    @Column(name = "state")
    @Expose
    private String state;
    @OneToMany(mappedBy = "idAcad", fetch = FetchType.LAZY)
    @Expose
    private Collection<Estimated> estimatedCollection;
    @JoinColumn(name = "idPort", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Portafolio idPort;
    @JoinColumn(name = "idUser", referencedColumnName = "IdUser")
    @ManyToOne(fetch = FetchType.LAZY)
    private User idUser;
    @OneToMany(mappedBy = "idAcad", fetch = FetchType.LAZY)
    @Expose
    private Collection<Groupe> groupeCollection;
    @OneToMany(mappedBy = "idAcad", fetch = FetchType.LAZY)
    @Expose
    private Collection<Discount> discountCollection;

    public Academicactivity() {
    }

    public Academicactivity(int idAcad) {
        this.setIdAcad(idAcad);
    }

    public Academicactivity(Integer idAcad) {
        this.idAcad = idAcad;
    }

    public Integer getIdAcad() {
        return idAcad;
    }

    public void setIdAcad(Integer idAcad) {
        this.idAcad = idAcad;
    }

    public String getNameActivity() {
        return nameActivity;
    }

    public void setNameActivity(String nameActivity) {
        this.nameActivity = nameActivity;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public String getCodReune() {
        return codReune;
    }

    public void setCodReune(String codReune) {
        this.codReune = codReune;
    }

    public String getSigepCode() {
        return sigepCode;
    }

    public void setSigepCode(String sigepCode) {
        this.sigepCode = sigepCode;
    }

    public String getActaCode() {
        return actaCode;
    }

    public void setActaCode(String actaCode) {
        this.actaCode = actaCode;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getInitDateact() {
        return initDateact;
    }

    public void setInitDateact(Date initDateact) {
        this.initDateact = initDateact;
    }

    public Date getEndDateact() {
        return endDateact;
    }

    public void setEndDateact(Date endDateact) {
        this.endDateact = endDateact;
    }

    public String getInvestigationGroup() {
        return investigationGroup;
    }

    public void setInvestigationGroup(String investigationGroup) {
        this.investigationGroup = investigationGroup;
    }

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
    }

    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Integer getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractEntity() {
        return contractEntity;
    }

    public void setContractEntity(String contractEntity) {
        this.contractEntity = contractEntity;
    }

    public Date getContractInit() {
        return contractInit;
    }

    public void setContractInit(Date contractInit) {
        this.contractInit = contractInit;
    }

    public Date getContractEnd() {
        return contractEnd;
    }

    public void setContractEnd(Date contractEnd) {
        this.contractEnd = contractEnd;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlTransient
    public Collection<Estimated> getEstimatedCollection() {
        return estimatedCollection;
    }

    public void setEstimatedCollection(Collection<Estimated> estimatedCollection) {
        this.estimatedCollection = estimatedCollection;
    }

    public Portafolio getIdPort() {
        return idPort;
    }

    public void setIdPort(Portafolio idPort) {
        this.idPort = idPort;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public Collection<Groupe> getGroupeCollection() {
        return groupeCollection;
    }

    public void setGroupeCollection(Collection<Groupe> groupeCollection) {
        this.groupeCollection = groupeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcad != null ? idAcad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Academicactivity)) {
            return false;
        }
        Academicactivity other = (Academicactivity) object;
        if ((this.idAcad == null && other.idAcad != null) || (this.idAcad != null && !this.idAcad.equals(other.idAcad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.entities.Academicactivity[ idAcad=" + idAcad + " ]";
    }

    @XmlTransient
    public Collection<Discount> getDiscountCollection() {
        return discountCollection;
    }

    public void setDiscountCollection(Collection<Discount> discountCollection) {
        this.discountCollection = discountCollection;
    }

}
