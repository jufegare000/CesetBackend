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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   
    private Double totalRealBudget;

    private Double mprovisedBudget;

    private Double contributionsUdeA;

    private Double contributionsFaculty;

    private CohortDTO idCohort;

    private AcademicActivityDTO idActivity;

    private Collection<ItemDTO> items;
}
