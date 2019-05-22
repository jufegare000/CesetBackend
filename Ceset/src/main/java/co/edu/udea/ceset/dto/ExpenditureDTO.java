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
public class ExpenditureDTO {
    private Integer idExpend;
    private Double quantity1;
    private String measureUnit;
    private Double quantity2;
    private Double stimatedValue;
    private String typeE;
    private Collection<ItemDTO> expenditurebyitemCollection;
    private Collection<CheckDTO> checkCollection;

    public ExpenditureDTO() {
    }

    public Integer getIdExpend() {
        return idExpend;
    }

    public void setIdExpend(Integer idExpend) {
        this.idExpend = idExpend;
    }

    public Double getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(Double quantity1) {
        this.quantity1 = quantity1;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public Double getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(Double quantity2) {
        this.quantity2 = quantity2;
    }

    public Double getStimatedValue() {
        return stimatedValue;
    }

    public void setStimatedValue(Double stimatedValue) {
        this.stimatedValue = stimatedValue;
    }

    public String getTypeE() {
        return typeE;
    }

    public void setTypeE(String typeE) {
        this.typeE = typeE;
    }

    public Collection<ItemDTO> getExpenditurebyitemCollection() {
        return expenditurebyitemCollection;
    }

    public void setExpenditurebyitemCollection(Collection<ItemDTO> expenditurebyitemCollection) {
        this.expenditurebyitemCollection = expenditurebyitemCollection;
    }

    public Collection<CheckDTO> getCheckCollection() {
        return checkCollection;
    }

    public void setCheckCollection(Collection<CheckDTO> checkCollection) {
        this.checkCollection = checkCollection;
    }
}
