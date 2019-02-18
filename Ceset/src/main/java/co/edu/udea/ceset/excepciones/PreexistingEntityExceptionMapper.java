package co.edu.udea.ceset.excepciones;


import co.edu.udea.ceset.dto.MensajeError;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Esta clase mapea la Excepcion PreexistingEntityException a un Response
 * @author edevargas
 */
@Provider
public class PreexistingEntityExceptionMapper implements ExceptionMapper<PreexistingEntityException>{
    
        
    @Override
    public Response toResponse(PreexistingEntityException ex) {
        MensajeError mensajeError =  new MensajeError(ex.getMessage(), 500, "La entidad ya existe."); 
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(mensajeError)
                    .build();
    }
    
}
