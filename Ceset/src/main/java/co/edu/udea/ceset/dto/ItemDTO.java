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
public class ItemDTO {
    private Integer idItem;

    private String description;

    private Double totalValue;

    private Double stimatedValue;

    private Double realValue;

    private String type;

    private Collection<ExpenditureByItemDTO> expenditurebyitemCollection;

    private Collection<EstimatedByItemDTO> estimatedbyitemCollection;

    private Collection<BudgetDTO> budgetbyitemCollection;


    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Double getStimatedValue() {
        return stimatedValue;
    }

    public void setStimatedValue(Double stimatedValue) {
        this.stimatedValue = stimatedValue;
    }

    public Double getRealValue() {
        return realValue;
    }

    public void setRealValue(Double realValue) {
        this.realValue = realValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<ExpenditureByItemDTO> getExpenditurebyitemCollection() {
        return expenditurebyitemCollection;
    }

    public void setExpenditurebyitemCollection(Collection<ExpenditureByItemDTO> expenditurebyitemCollection) {
        this.expenditurebyitemCollection = expenditurebyitemCollection;
    }

    public Collection<EstimatedByItemDTO> getEstimatedbyitemCollection() {
        return estimatedbyitemCollection;
    }

    public void setEstimatedbyitemCollection(Collection<EstimatedByItemDTO> estimatedbyitemCollection) {
        this.estimatedbyitemCollection = estimatedbyitemCollection;
    }

    public Collection<BudgetDTO> getBudgetbyitemCollection() {
        return budgetbyitemCollection;
    }

    public void setBudgetbyitemCollection(Collection<BudgetDTO> budgetbyitemCollection) {
        this.budgetbyitemCollection = budgetbyitemCollection;
    }
}
