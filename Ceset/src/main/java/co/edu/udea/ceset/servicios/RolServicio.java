/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.servicios;

import co.edu.udea.ceset.bl.RolBL;
import co.edu.udea.ceset.bl.RoleByUserBL;
import co.edu.udea.ceset.dto.entities.Permission;
import co.edu.udea.ceset.dto.entities.Rolebypermission;
import co.edu.udea.ceset.dto.entities.Rolebyuser;
import co.edu.udea.ceset.dto.entities.Rolec;
import java.util.ArrayList;
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
    
    
    @POST
    @PermitAll
    @Path("/permisos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Collection<Permission> createRole(List<Rolec>  rol) {
        Collection<Rolebypermission> actual;
        Collection<Permission> perm = new ArrayList<>();
        Rolec act;
        for(Rolec elem: rol){
            act = RolBL.getInstance().obtenerPorId(elem.getIdRole());
            actual = act.getRolebypermissionCollection();
            for(Rolebypermission rbp: actual){
                perm.add(rbp.getIdPermission());
            }
        }        
        return perm;
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
   @Consumes("application/json")
    @GET
    @PermitAll
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
    
    @GET
    @Path("/rolebyuser")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rolebyuser> getRolebyU() {
        return RoleByUserBL.getInstance().obtenerTodods();
    }
}
