/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto;

import java.util.Collection;

/**
 *
 * @author jufeg
 */
public class CheckDTO {
    private Integer idCheck;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    private Double idRealValue;

    private String description;

    private String state;

    private ExpenditureDTO idExpend;

    private Collection<NotifficationDTO> notifficationbycheckCollection;

    public CheckDTO() {
    }

    public Integer getIdCheck() {
        return idCheck;
    }

    public void setIdCheck(Integer idCheck) {
        this.idCheck = idCheck;
    }

    public Double getIdRealValue() {
        return idRealValue;
    }

    public void setIdRealValue(Double idRealValue) {
        this.idRealValue = idRealValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ExpenditureDTO getIdExpend() {
        return idExpend;
    }

    public void setIdExpend(ExpenditureDTO idExpend) {
        this.idExpend = idExpend;
    }

    public Collection<NotifficationDTO> getNotifficationbycheckCollection() {
        return notifficationbycheckCollection;
    }

    public void setNotifficationbycheckCollection(Collection<NotifficationDTO> notifficationbycheckCollection) {
        this.notifficationbycheckCollection = notifficationbycheckCollection;
    }
    
    
}
