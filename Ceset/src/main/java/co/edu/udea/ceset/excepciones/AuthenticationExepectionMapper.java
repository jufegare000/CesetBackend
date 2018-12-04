 package co.edu.udea.ceset.excepciones;

import co.edu.udea.ceset.dto.MensajeError;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Para todas las excepciones AuthenticationException, se aplica este mapper
 * @author edevargas
 */
@Provider
public class AuthenticationExepectionMapper implements ExceptionMapper<AuthenticationException>{
    
   
    @Override
    public Response toResponse(AuthenticationException ex) {
        MensajeError mensajeError =  new MensajeError(ex.getMessage(), 401, "Error de Autenticación"); 
            return Response.status(Status.UNAUTHORIZED)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(mensajeError)
                    .build();
    }


    
}
