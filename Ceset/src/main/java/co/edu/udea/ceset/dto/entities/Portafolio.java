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
@Table(name = "tbl_portafolio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Portafolio.findAll", query = "SELECT p FROM Portafolio p"),
    @NamedQuery(name = "Portafolio.findById", query = "SELECT p FROM Portafolio p WHERE p.id = :id"),
    @NamedQuery(name = "Portafolio.findByDiligencyDate", query = "SELECT p FROM Portafolio p WHERE p.diligencyDate = :diligencyDate"),
    @NamedQuery(name = "Portafolio.findByServiceState", query = "SELECT p FROM Portafolio p WHERE p.serviceState = :serviceState"),
    @NamedQuery(name = "Portafolio.findByNameService", query = "SELECT p FROM Portafolio p WHERE p.nameService = :nameService"),
    @NamedQuery(name = "Portafolio.findByFacultyDependency", query = "SELECT p FROM Portafolio p WHERE p.facultyDependency = :facultyDependency"),
    @NamedQuery(name = "Portafolio.findBySchoolDependency", query = "SELECT p FROM Portafolio p WHERE p.schoolDependency = :schoolDependency"),
    @NamedQuery(name = "Portafolio.findByActivityType", query = "SELECT p FROM Portafolio p WHERE p.activityType = :activityType"),
    @NamedQuery(name = "Portafolio.findByHourDuration", query = "SELECT p FROM Portafolio p WHERE p.hourDuration = :hourDuration"),
    @NamedQuery(name = "Portafolio.findByMaxRecPerGroup", query = "SELECT p FROM Portafolio p WHERE p.maxRecPerGroup = :maxRecPerGroup"),
    @NamedQuery(name = "Portafolio.findByDirigidoA", query = "SELECT p FROM Portafolio p WHERE p.dirigidoA = :dirigidoA"),
    @NamedQuery(name = "Portafolio.findByCharsPublic", query = "SELECT p FROM Portafolio p WHERE p.charsPublic = :charsPublic"),
    @NamedQuery(name = "Portafolio.findLast", query = "SELECT p FROM Portafolio p ORDER BY p.id DESC")})
public class Portafolio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @Expose
    private Integer id;
    @Column(name = "diligencyDate")
    @Temporal(TemporalType.DATE)
    @Expose
    private Date diligencyDate;
    @Size(max = 100)
    @Column(name = "serviceState")
    @Expose
    private String serviceState;
    @Size(max = 200)
    @Column(name = "nameService")
    @Expose
    private String nameService;
    @Size(max = 200)
    @Column(name = "facultyDependency")
    @Expose
    private String facultyDependency;
    @Size(max = 200)
    @Column(name = "schoolDependency")
    @Expose
    private String schoolDependency;
    @Size(max = 100)
    @Column(name = "activityType")
    @Expose
    private String activityType;
    @Size(max = 200)
    @Column(name = "hourDuration")
    @Expose
    private String hourDuration;
    @Column(name = "maxRecPerGroup")
    @Expose
    private Integer maxRecPerGroup;
    @Size(max = 300)
    @Column(name = "dirigidoA")
    @Expose
    private String dirigidoA;
    @Size(max = 500)
    @Column(name = "charsPublic")
    @Expose
    private String charsPublic;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "academicDesign")
    @Expose
    private String academicDesign;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "developmentResources")
    @Expose
    private String developmentResources;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "materials")
    @Expose
    private String materials;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "sugestedHorary")
    @Expose
    private String sugestedHorary;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "postulants")
    @Expose
    private String postulants;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "exclusive")
    @Expose
    private String exclusive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPort", fetch = FetchType.LAZY)
    @Expose
    private Collection<Academicactivity> academicactivityCollection;
    @JoinColumn(name = "IdUser", referencedColumnName = "IdUser")
    @ManyToOne(fetch = FetchType.LAZY)
    private User idUser;

    public Portafolio() {
    }

    public Portafolio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDiligencyDate() {
        return diligencyDate;
    }

    public void setDiligencyDate(Date diligencyDate) {
        this.diligencyDate = diligencyDate;
    }

    public String getServiceState() {
        return serviceState;
    }

    public void setServiceState(String serviceState) {
        this.serviceState = serviceState;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getFacultyDependency() {
        return facultyDependency;
    }

    public void setFacultyDependency(String facultyDependency) {
        this.facultyDependency = facultyDependency;
    }

    public String getSchoolDependency() {
        return schoolDependency;
    }

    public void setSchoolDependency(String schoolDependency) {
        this.schoolDependency = schoolDependency;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getHourDuration() {
        return hourDuration;
    }

    public void setHourDuration(String hourDuration) {
        this.hourDuration = hourDuration;
    }

    public Integer getMaxRecPerGroup() {
        return maxRecPerGroup;
    }

    public void setMaxRecPerGroup(Integer maxRecPerGroup) {
        this.maxRecPerGroup = maxRecPerGroup;
    }

    public String getDirigidoA() {
        return dirigidoA;
    }

    public void setDirigidoA(String dirigidoA) {
        this.dirigidoA = dirigidoA;
    }

    public String getCharsPublic() {
        return charsPublic;
    }

    public void setCharsPublic(String charsPublic) {
        this.charsPublic = charsPublic;
    }

    public String getAcademicDesign() {
        return academicDesign;
    }

    public void setAcademicDesign(String academicDesign) {
        this.academicDesign = academicDesign;
    }

    public String getDevelopmentResources() {
        return developmentResources;
    }

    public void setDevelopmentResources(String developmentResources) {
        this.developmentResources = developmentResources;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getSugestedHorary() {
        return sugestedHorary;
    }

    public void setSugestedHorary(String sugestedHorary) {
        this.sugestedHorary = sugestedHorary;
    }

    public String getPostulants() {
        return postulants;
    }

    public void setPostulants(String postulants) {
        this.postulants = postulants;
    }

    public String getExclusive() {
        return exclusive;
    }

    public void setExclusive(String exclusive) {
        this.exclusive = exclusive;
    }

    @XmlTransient
    public Collection<Academicactivity> getAcademicactivityCollection() {
        return academicactivityCollection;
    }

    public void setAcademicactivityCollection(Collection<Academicactivity> academicactivityCollection) {
        this.academicactivityCollection = academicactivityCollection;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof Portafolio)) {
            return false;
        }
        Portafolio other = (Portafolio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udea.ceset.dto.entities.Portafolio[ id=" + id + " ]";
    }
    
}
