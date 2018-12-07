/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.servicios;

import co.edu.udea.ceset.bl.PersonBL;
import co.edu.udea.ceset.bl.RolBL;
import co.edu.udea.ceset.bl.RoleByUserBL;
import co.edu.udea.ceset.dao.PersonDAO;
import co.edu.udea.ceset.dto.Person;
import co.edu.udea.ceset.dto.Rolebyuser;
import co.edu.udea.ceset.dto.Rolec;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path; //Librería servicios web
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/persona")
public class PersonServicio {
    private final String nombrePU = "ceset_PU";
    
    @Context
    SecurityContext securityContext;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public void createPerson(Person prsn) {
        
        obtenerPersonDAO().create(prsn);  
    }
      private PersonDAO obtenerPersonDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        PersonDAO DAO = new PersonDAO(emf);
        return DAO;
    }
      
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAll () {
        return PersonBL.getInstance().obtenerTodods();
    }
    
}
