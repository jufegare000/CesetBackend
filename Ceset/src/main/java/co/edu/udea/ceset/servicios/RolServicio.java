/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.servicios;

import co.edu.udea.ceset.bl.RolBL;
import co.edu.udea.ceset.dto.Rol;
import java.util.Collection;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path; //Librería servicios web
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/rol")
public class RolServicio {
    
    @Context
    SecurityContext securityContext;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createRole(Rol rol) {
        return RolBL.getInstance().crear(rol.getId(), rol.getNombre(), rol.getEstado());
    }
    /*
    @Consumes("application/json")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Rol> getAll () {
        return RolBL.getInstance().getAll();
    }
    */
    @Consumes("application/json")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll () {
        return "Hola";
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Rol obtenerPorId(@PathParam("id") int id) {
        return RolBL.getInstance().obtenerPorId(id);
    }
}
