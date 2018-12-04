/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.servicios;

import co.edu.udea.ceset.bl.RolBL;
import co.edu.udea.ceset.dto.Rolec;
import java.util.Collection;
import java.util.List;
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
    public void createRole(Rolec rol) {
         RolBL.getInstance().crear(rol.getDescription());
    }
/*
    @Consumes("application/json")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Rolec> getAll () {
        return RolBL.getInstance().getAll();
    }
  
    */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rolec> getAll () {
        return RolBL.getInstance().getAll();
    }
    
    @GET
    @Path("/{idRole}")
    @Produces(MediaType.APPLICATION_JSON)
    public Rolec obtenerPorId(@PathParam("idRole") int id) {
        return RolBL.getInstance().obtenerPorId(id);
    }
}
