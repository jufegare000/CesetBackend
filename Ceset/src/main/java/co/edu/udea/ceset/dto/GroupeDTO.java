package co.edu.udea.ceset.dto;

import co.edu.udea.ceset.dto.entities.Academicactivity;

import java.util.Collection;
import java.util.Date;

public class GroupeDTO {
    private Integer idGroup;

    private Integer numbers;

    private Date initDate;

    private Date finDate;

    private String states;

    private String classroom;

    private String schedule;

    private Collection<BudgetDTO> budgetCollection;

    private Collection<ThemesDTO> themesCollection;

    private AcademicActivityDTO idAcad;

    public GroupeDTO() {
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

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Collection<BudgetDTO> getBudgetCollection() {
        return budgetCollection;
    }

    public void setBudgetCollection(Collection<BudgetDTO> budgetCollection) {
        this.budgetCollection = budgetCollection;
    }

    public Collection<ThemesDTO> getThemesCollection() {
        return themesCollection;
    }

    public void setThemesCollection(Collection<ThemesDTO> themesCollection) {
        this.themesCollection = themesCollection;
    }

    public AcademicActivityDTO getIdAcad() {
        return idAcad;
    }

    public void setIdAcad(AcademicActivityDTO idAcad) {
        this.idAcad = idAcad;
    }
}
