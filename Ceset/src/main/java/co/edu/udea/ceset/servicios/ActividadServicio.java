package co.edu.udea.ceset.servicios;


import co.edu.udea.ceset.auth.AuthUtils;
import co.edu.udea.ceset.auth.Token;
import co.edu.udea.ceset.bl.AcademicActivityBL;
import co.edu.udea.ceset.bl.PersonBL;
import co.edu.udea.ceset.bl.UsuarioBL;
import co.edu.udea.ceset.dto.Academicactivity;
import co.edu.udea.ceset.dto.Person;
import co.edu.udea.ceset.dto.Rol;
import co.edu.udea.ceset.dto.Rolec;
import co.edu.udea.ceset.dto.User;
import co.edu.udea.ceset.dto.Usuario;
import com.nimbusds.jose.JOSEException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Root resource (exposed at "usuarios" path)
 */
@Path("/actividades")
public class ActividadServicio implements Serializable {

    private static final long serialVersionUID = -9066585482051897942L;
    
    @Context
    SecurityContext securityContext;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public void createActiv(User usr) {
         //Collection<User> usrq = new LinkedList<User>();
         //UsuarioBL.getInstance().crear(prs);
         //boolean b = usrq.add(usr);
         //prs.setUserCollection(usrq);
         //PersonBL.getInstance().crear(prs, usr);
         
         //Primero se crea la persona, luego se crea el usuario y 
         //paultatinamente se debe hacer respectiva asociación
    }
    
    @Context
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> obtenerPorId() {
        return UsuarioBL.getInstance().obtenerTodods();
    }
    
    

   
    /**
     * Método que retorna un Usuario dado un id
     *
     * @param id : id del Usuario
     * @return Usuario : Usuario en formato Json
     */
    @GET
    @Path("/{idAcad}")
    @Produces(MediaType.APPLICATION_JSON)
    public Academicactivity obtenerPorId(@PathParam("idAcad") int id) {
        return AcademicActivityBL.getInstance().getById(id);
    } 

}
