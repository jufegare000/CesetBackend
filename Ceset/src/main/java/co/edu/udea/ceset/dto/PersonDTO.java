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
public class PersonDTO {
    private Integer idPerson;

    private String completeName;

    private String documentType;

    private String document;

    private String email;

    private Collection<UserDTO> userCollection;
}
