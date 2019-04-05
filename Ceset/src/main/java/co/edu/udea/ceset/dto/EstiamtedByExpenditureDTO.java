package co.edu.udea.ceset.dto;

public class EstiamtedByExpenditureDTO {
    private Integer id;

    private String description;

    private Double quantity1;

    private String measureUnit;

    private Double quantity2;

    private Double stimatedValue;
    private String typeE;

    private double totalValue;

    private double fp;

    private double unitValFP;

    private double totalValFP;

    private String observation;

    private EstimatedDTO idEstimated;

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

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getFp() {
        return fp;
    }

    public void setFp(double fp) {
        this.fp = fp;
    }

    public double getUnitValFP() {
        return unitValFP;
    }

    public void setUnitValFP(double unitValFP) {
        this.unitValFP = unitValFP;
    }

    public double getTotalValFP() {
        return totalValFP;
    }

    public void setTotalValFP(double totalValFP) {
        this.totalValFP = totalValFP;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public EstimatedDTO getIdEstimated() {
        return idEstimated;
    }

    public void setIdEstimated(EstimatedDTO idEstimated) {
        this.idEstimated = idEstimated;
    }
}
