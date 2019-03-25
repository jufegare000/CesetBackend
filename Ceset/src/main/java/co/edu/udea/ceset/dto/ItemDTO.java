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

    private Collection<ExpenditureDTO> expenditute;
    private Collection<BudgetDTO> Budget;
}
