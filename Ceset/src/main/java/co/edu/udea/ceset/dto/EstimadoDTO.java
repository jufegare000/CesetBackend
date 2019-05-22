package co.edu.udea.ceset.dto;

import java.util.Collection;

public class EstimadoDTO {

    private Integer idEstimated;
    private Double totalBudget;

    private Double improvised;

    private Double contributionsUdeA;

    private Double contributionsFaculty;

    private Collection<EstimatedByExpenditureDTO> estimatedbyexpenditureCollection;

    public EstimadoDTO() {
    }

    public Integer getIdEstimated() {
        return idEstimated;
    }

    public void setIdEstimated(Integer idEstimated) {
        this.idEstimated = idEstimated;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Double getImprovised() {
        return improvised;
    }

    public void setImprovised(Double improvised) {
        this.improvised = improvised;
    }

    public Double getContributionsUdeA() {
        return contributionsUdeA;
    }

    public void setContributionsUdeA(Double contributionsUdeA) {
        this.contributionsUdeA = contributionsUdeA;
    }

    public Double getContributionsFaculty() {
        return contributionsFaculty;
    }

    public void setContributionsFaculty(Double contributionsFaculty) {
        this.contributionsFaculty = contributionsFaculty;
    }

    public Collection<EstimatedByExpenditureDTO> getEstimatedbyexpenditureCollection() {
        return estimatedbyexpenditureCollection;
    }

    public void setEstimatedbyexpenditureCollection(Collection<EstimatedByExpenditureDTO> estimatedbyexpenditureCollection) {
        this.estimatedbyexpenditureCollection = estimatedbyexpenditureCollection;
    }
}
