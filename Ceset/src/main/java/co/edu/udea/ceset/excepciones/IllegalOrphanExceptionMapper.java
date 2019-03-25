package co.edu.udea.ceset.excepciones;

import co.udea.edu.co.dto.entities.MensajeError;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Esta clase mapea la Excepcion IllegalOrphanException a un Response
 * @author edevargas
 */
@Provider
public class IllegalOrphanExceptionMapper implements ExceptionMapper<IllegalOrphanException>{
    
   
    @Override
    public Response toResponse(IllegalOrphanException ex) {
        MensajeError mensajeError =  new MensajeError(ex.getMessage(), 500, "Error de elemento huérfano."); 
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(mensajeError)
                    .build();
    }
    
}
