/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.entities.Estimated;
import co.edu.udea.ceset.dto.entities.Budget;
import co.edu.udea.ceset.dto.entities.Discount;
import co.edu.udea.ceset.dto.entities.User;

import java.util.Date;
import java.util.Collection;

/**
 *
 * @author jufeg
 */
public class AcademicActivityDTO {

    private Integer idAcad;
    private String nameActivity;
    private String activityType;
    private String dependency;
    private String codReune;
    private String sigepCode;
    private String actaCode;
    private String observaciones;
    private Date initDateact;
    private Date endDateact;
    private String investigationGroup;
    private String coordinatorName;
    private String contactTelephone;
    private String contactEmail;
    private Integer durationMonths;
    private String contractType;
    private String contractEntity;
    private Date contractInit;
    private Date contractEnd;
    private Date creationDate;
    private String state;
    private PortafolioDTO idPort;
    private UserDTO idUser;
    private Collection<GroupeDTO> groupeCollection;
    private Collection<DiscountDTO> discountCollection;
    private Collection<EstimadoDTO> estimatedCollection;

    private String coValue;

    private String coEntity;

    private String coConcept;


    public String getCoValue() {
        return coValue;
    }

    public void setCoValue(String coValue) {
        this.coValue = coValue;
    }

    public String getCoEntity() {
        return coEntity;
    }

    public void setCoEntity(String coEntity) {
        this.coEntity = coEntity;
    }

    public String getCoConcept() {
        return coConcept;
    }

    public void setCoConcept(String coConcept) {
        this.coConcept = coConcept;
    }

    public Collection<DiscountDTO> getDiscountCollection() {
        return discountCollection;
    }

    public void setDiscountCollection(Collection<DiscountDTO> discountCollection) {
        this.discountCollection = discountCollection;
    }

    public AcademicActivityDTO() {

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

    public Collection<EstimadoDTO> getEstimatedCollection() {
        return estimatedCollection;
    }

    public void setEstimatedCollection(Collection<EstimadoDTO> estimatedCollection) {
        this.estimatedCollection = estimatedCollection;
    }

    public PortafolioDTO getIdPort() {
        return idPort;
    }

    public void setIdPort(PortafolioDTO idPort) {
        this.idPort = idPort;
    }

    public UserDTO getIdUser() {
        return idUser;
    }

    public void setIdUser(UserDTO idUser) {
        this.idUser = idUser;
    }

    public Collection<GroupeDTO> getGroupeCollection() {
        return groupeCollection;
    }

    public void setGroupeCollection(Collection<GroupeDTO> groupeCollection) {
        this.groupeCollection = groupeCollection;
    }
}
