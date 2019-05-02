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
import co.edu.udea.ceset.dto.entities.Cohort;
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

    private User idUser;

    private Collection<Budget> budgetCollection;

    private Collection<EstimatedDTO> estimatedCollection;

    private Collection<Cohort> cohortCollection;


    private Collection<Discount> discountCollection;

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

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Collection<Budget> getBudgetCollection() {
        return budgetCollection;
    }

    public void setBudgetCollection(Collection<Budget> budgetCollection) {
        this.budgetCollection = budgetCollection;
    }

    public Collection<EstimatedDTO> getEstimatedCollection() {
        return estimatedCollection;
    }

    public void setEstimatedCollection(Collection<EstimatedDTO> estimatedCollection) {
        this.estimatedCollection = estimatedCollection;
    }

    public Collection<Cohort> getCohortCollection() {
        return cohortCollection;
    }

    public void setCohortCollection(Collection<Cohort> cohortCollection) {
        this.cohortCollection = cohortCollection;
    }

    public Collection<Discount> getDiscountCollection() {
        return discountCollection;
    }

    public void setDiscountCollection(Collection<Discount> discountCollection) {
        this.discountCollection = discountCollection;
    }
    
    
    
    
}
