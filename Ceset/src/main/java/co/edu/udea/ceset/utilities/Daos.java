/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.utilities;

import co.edu.udea.ceset.dao.PersonDAO;
import co.edu.udea.ceset.dao.UserDAO;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jufeg
 */
public class Daos {
    private final String nombrePU = "ceset_PU";
    
     public UserDAO obtenerUserDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        UserDAO DAO = new UserDAO(emf);
        return DAO;
    }
     
     private PersonDAO obtenerPersonDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        PersonDAO DAO = new PersonDAO(emf);
        return DAO;
    }
}
