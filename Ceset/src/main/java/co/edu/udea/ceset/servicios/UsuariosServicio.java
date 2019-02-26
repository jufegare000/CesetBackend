package co.edu.udea.ceset.servicios;


import co.edu.udea.ceset.auth.AuthUtils;
import co.edu.udea.ceset.auth.Token;
import co.edu.udea.ceset.bl.PersonBL;
import co.edu.udea.ceset.bl.UsuarioBL;
import co.edu.udea.ceset.dto.Person;
import co.edu.udea.ceset.dto.Rol;
import co.edu.udea.ceset.dto.Rolec;
import co.edu.udea.ceset.dto.User;
import co.edu.udea.ceset.dto.Usuario;
import com.nimbusds.jose.JOSEException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.management.relation.Role;
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
@Path("/usuarios")
public class UsuariosServicio implements Serializable {

    private static final long serialVersionUID = -9066585482051897942L;
    
    @Context
    SecurityContext securityContext;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public void creatUser(User usr) {

          Person prs;
         prs = usr.getIdPerson();
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
    // Mandar la información por post o por header y la clave la deben enviar encriptada. hs264 ejemplo.
    /**
     * Métdodo para autenticar un usuario.
     *
     * @param usuario a autenticar
     * @param clave cifrada del usuario
     * @return usuario en formatio JSON
     * @throws JOSEException
     */
    @GET
    @PermitAll
    @Path("/autenticar")
    @Produces("application/json")
    public Response autenticar(
            @QueryParam("usuario") String usuario
            ) throws JOSEException, IOException {
                int rol = 0;
                User usuarioAutenticado;
                //usuarioAutenticado = UsuarioBL.getInstance().autenticar(usuario)
                usuarioAutenticado = new User();
                usuarioAutenticado.setIdUser(1);
usuarioAutenticado.setNameUser("usuario.prueba");
List<Rolec> roles = new ArrayList<Rolec>();
Rolec rol1 = new Rolec();
rol1.setIdRole(1);
Rolec rol2 = new Rolec();
rol2.setIdRole(2);
roles.add(rol1);
roles.add(rol2);

usuarioAutenticado.setRoles(roles);
if (usuarioAutenticado != null) {
                    // Verifico si el usuario tiene permisos para entrar con el rol elegido
                    for (Iterator<Rolec> it = usuarioAutenticado.getRoles().iterator(); it.hasNext();) {
                        Rolec rolIt = it.next();
                        
                        // Devuelves un Response con un entity de tipo Token. 
                        // En el token están los datos del usuario autenticado
                            Token token = AuthUtils.createToken("auth-backend", usuarioAutenticado);
                            return Response.status(200)
                                    .type(MediaType.APPLICATION_JSON)
                                    .entity(token)
                                    .build();
                        
                    }
                }
        
        return null;

    }

   
    /**
     * Método que retorna un Usuario dado un id
     *
     * @param id : id del Usuario
     * @return Usuario : Usuario en formato Json
     */
    @GET
    @Path("/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public User obtenerPorId(@PathParam("idUser") int id) {
        return UsuarioBL.getInstance().obtenerPorId(id);
    } 

}
