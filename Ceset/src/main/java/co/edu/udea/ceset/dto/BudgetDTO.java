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
public class BudgetDTO {
    private Integer idBudget;

    private Double totalRealBudget;

    private Double mprovisedBudget;

    private Double contributionsUdeA;

    private Double contributionsFaculty;

    private Collection<BudgetByExpenditureDTO> budgetbyexpenditureCollection;

    private GroupeDTO idGroup;

    public BudgetDTO() {
    }

    public Integer getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Integer idBudget) {
        this.idBudget = idBudget;
    }

    public Double getTotalRealBudget() {
        return totalRealBudget;
    }

    public void setTotalRealBudget(Double totalRealBudget) {
        this.totalRealBudget = totalRealBudget;
    }

    public Double getMprovisedBudget() {
        return mprovisedBudget;
    }

    public void setMprovisedBudget(Double mprovisedBudget) {
        this.mprovisedBudget = mprovisedBudget;
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

    public Collection<BudgetByExpenditureDTO> getBudgetbyexpenditureCollection() {
        return budgetbyexpenditureCollection;
    }

    public void setBudgetbyexpenditureCollection(Collection<BudgetByExpenditureDTO> budgetbyexpenditureCollection) {
        this.budgetbyexpenditureCollection = budgetbyexpenditureCollection;
    }

    public GroupeDTO getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(GroupeDTO idGroup) {
        this.idGroup = idGroup;
    }
}
