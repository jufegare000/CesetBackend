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

}
