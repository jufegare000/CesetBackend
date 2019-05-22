package co.edu.udea.ceset.dto;

import java.util.Collection;
import java.util.Date;

public class PortafolioDTO {
    private Integer id;

    private Date diligencyDate;

    private String serviceState;

    private String nameService;
    private String facultyDependency;

    private String schoolDependency;

    private String activityType;

    private String hourDuration;

    private Integer maxRecPerGroup;

    private String dirigidoA;

    private String charsPublic;

    private String academicDesign;

    private String developmentResources;

    private String materials;

    private String sugestedHorary;

    private String postulants;

    private String exclusive;

    private Collection<AcademicActivityDTO> academicactivityCollection;

    private UserDTO idUser;

    public PortafolioDTO() {
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

    public Collection<AcademicActivityDTO> getAcademicactivityCollection() {
        return academicactivityCollection;
    }

    public void setAcademicactivityCollection(Collection<AcademicActivityDTO> academicactivityCollection) {
        this.academicactivityCollection = academicactivityCollection;
    }

    public UserDTO getIdUser() {
        return idUser;
    }

    public void setIdUser(UserDTO idUser) {
        this.idUser = idUser;
    }
}
