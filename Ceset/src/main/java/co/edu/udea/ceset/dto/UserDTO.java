/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author jufeg
 */
public class UserDTO {

//    private Collection<Academicactivity> academicactivityCollection;

//    private Collection<Rolebyuser> rolebyuserCollection;

    private Integer idUser;

    private String nameUser;

    private String password;

    private Date dateCreation;

    private String states;

    private PersonDTO idPerson;

    private List<RoleDTO> roles;


    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public PersonDTO getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(PersonDTO idPerson) {
        this.idPerson = idPerson;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
