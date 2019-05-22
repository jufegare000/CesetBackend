package co.edu.udea.ceset.dto;

import java.util.Date;

public class BudgetByExpenditureDTO {
    private Integer id;

    private String description;

    private Double quantity1;

    private String measureUnit;

    private Double quantity2;

    private Double stimatedValue;

    private String typeE;

    private Double totalValue;

    private Double fp;

    private Double unitValFP;

    private Double totalValFP;

    private String observation1;

    private String observation2;

    private Boolean checked;

    private Date actualizationDate;

    private BudgetDTO idBudget;

    public BudgetByExpenditureDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Double getFp() {
        return fp;
    }

    public void setFp(Double fp) {
        this.fp = fp;
    }

    public Double getUnitValFP() {
        return unitValFP;
    }

    public void setUnitValFP(Double unitValFP) {
        this.unitValFP = unitValFP;
    }

    public Double getTotalValFP() {
        return totalValFP;
    }

    public void setTotalValFP(Double totalValFP) {
        this.totalValFP = totalValFP;
    }

    public String getObservation1() {
        return observation1;
    }

    public void setObservation1(String observation1) {
        this.observation1 = observation1;
    }

    public String getObservation2() {
        return observation2;
    }

    public void setObservation2(String observation2) {
        this.observation2 = observation2;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Date getActualizationDate() {
        return actualizationDate;
    }

    public void setActualizationDate(Date actualizationDate) {
        this.actualizationDate = actualizationDate;
    }

    public BudgetDTO getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(BudgetDTO idBudget) {
        this.idBudget = idBudget;
    }
}
