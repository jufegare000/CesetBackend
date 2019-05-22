package co.edu.udea.ceset.dto;

import java.util.Date;

public class ThemesDTO {

    private Integer idTheme;

    private Date creationDate;

    private String description;

    private String responsible;

    private String responsbileDocument;

    private Integer hours;

    private String responsibleEmail;

    private String contactNumber;

    private Date initDate;

    private Date endDate;

    private String dependency;

    private String universityLink;

    private GroupeDTO idGroup;

    public ThemesDTO() {
    }

    public Integer getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(Integer idTheme) {
        this.idTheme = idTheme;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public GroupeDTO getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(GroupeDTO idGroup) {
        this.idGroup = idGroup;
    }
}
