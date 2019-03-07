/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.servicios;

import co.edu.udea.ceset.bl.PersonBL;

import co.edu.udea.ceset.bl.UsuarioBL;

import co.edu.udea.ceset.dto.User;
import co.edu.udea.ceset.dto.Notiffication;
import co.edu.udea.ceset.dto.Person;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path; //Librería servicios web

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/persona")
public class PersonServicio {

    private final String nombrePU = "ceset_PU";

    @Context
    SecurityContext securityContext;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createPerson(User usr) {
        Person nuevo = null;
        PersonBL prbl;
        UsuarioBL usrbl;
        Person prsn;
        Notiffication notif;
        int status = 201;
        try {
            usrbl = UsuarioBL.getInstance();
            prbl = PersonBL.getInstance();
            prsn = usr.getIdPerson(); // se obtiene la persona a crear
            // Deben ser separadas ya  que
            // la separación de responsbilidades así demanda
            nuevo = prbl.crear(prsn);
            usr.setDateCreation(); // se asigna fecha para el creador
            usr.setStates();        // estado de creación
            usr.setIdPerson(nuevo);
            usrbl.crear(usr);
            
        } catch (Exception e) {
            status = 500;
        }
      return Response.status(status)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(nuevo)
                    .build();

        //falta enviar un mensaje de retorno que indique que el usuario efectivamente se creó
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAll() {
        return PersonBL.getInstance().obtenerTodods();
    }

}
