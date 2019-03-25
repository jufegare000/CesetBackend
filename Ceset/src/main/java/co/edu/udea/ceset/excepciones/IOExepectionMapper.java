 package co.edu.udea.ceset.excepciones;

import co.udea.edu.co.dto.entities.MensajeError;
import java.io.IOException;
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
public class IOExepectionMapper implements ExceptionMapper<IOException>{
    
   
    @Override
    public Response toResponse(IOException ex) {
        MensajeError mensajeError =  new MensajeError(ex.getMessage(), 401, "No autorizado"); 
            return Response.status(Status.UNAUTHORIZED)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(mensajeError)
                    .build();
    }


    
}
