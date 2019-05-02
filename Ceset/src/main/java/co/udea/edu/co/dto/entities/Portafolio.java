/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.udea.edu.co.dto.entities;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
    @NamedQuery(name = "Portafolio.findByCharsPublic", query = "SELECT p FROM Portafolio p WHERE p.charsPublic = :charsPublic"), @NamedQuery(name = "Portafolio.findLast", query = "SELECT p FROM Portafolio p ORDER BY p.id DESC")
})
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
    @Size(max = 20)
    @Column(name = "serviceState")
    @Expose
    private String serviceState;
    @Size(max = 200)
    @Column(name = "nameService")
    @Expose
    private String nameService;
    @Size(max = 100)
    @Column(name = "facultyDependency")
    @Expose
    private String facultyDependency;
    @Size(max = 100)
    @Column(name = "schoolDependency")
    @Expose
    private String schoolDependency;
    @Size(max = 100)
    @Column(name = "activityType")
    private String activityType;
    @Size(max = 300)
    @Column(name = "hourDuration")
    @Expose
    private String hourDuration;
    @Column(name = "maxRecPerGroup")
    @Expose
    private Integer maxRecPerGroup;
    @Size(max = 100)
    @Column(name = "dirigidoA")
    @Expose
    private String dirigidoA;
    @Size(max = 500)
    @Column(name = "charsPublic")
    @Expose
    private String charsPublic;
    @Lob
    @Column(name = "academicDesign")
    @Expose
    private byte[] academicDesign;
    @Lob
    @Column(name = "developmentResources")
    @Expose
    private byte[] developmentResources;
    @Lob
    @Column(name = "materials")
    @Expose
    private byte[] materials;
    @Lob
    @Column(name = "sugestedHorary")
    @Expose
    private byte[] sugestedHorary;
    @Lob
    @Column(name = "postulants")
    @Expose
    private byte[] postulants;
    @Lob
    @Column(name = "exclusive")
    @Expose
    private byte[] exclusive;

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

    public byte[] getAcademicDesign() {
        return academicDesign;
    }

    public void setAcademicDesign(byte[] academicDesign) {
        this.academicDesign = academicDesign;
    }

    public byte[] getDevelopmentResources() {
        return developmentResources;
    }

    public void setDevelopmentResources(byte[] developmentResources) {
        this.developmentResources = developmentResources;
    }

    public byte[] getMaterials() {
        return materials;
    }

    public void setMaterials(byte[] materials) {
        this.materials = materials;
    }

    public byte[] getSugestedHorary() {
        return sugestedHorary;
    }

    public void setSugestedHorary(byte[] sugestedHorary) {
        this.sugestedHorary = sugestedHorary;
    }

    public byte[] getPostulants() {
        return postulants;
    }

    public void setPostulants(byte[] postulants) {
        this.postulants = postulants;
    }

    public byte[] getExclusive() {
        return exclusive;
    }

    public void setExclusive(byte[] exclusive) {
        this.exclusive = exclusive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString() {
        return "co.udea.edu.co.dto.entities.Portafolio[ id=" + id + " ]";
    }
    
}
