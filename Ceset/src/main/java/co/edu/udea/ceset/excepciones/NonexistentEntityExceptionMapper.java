package co.edu.udea.ceset.excepciones;


import co.edu.udea.ceset.dto.MensajeError;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Esta clase mapea la Excepcion DataNotFoundException a un Response
 * @author edevargas
 */
@Provider
public class NonexistentEntityExceptionMapper implements ExceptionMapper<NonexistentEntityException>{
    
   
    @Override
    public Response toResponse(NonexistentEntityException ex) {
        MensajeError mensajeError =  new MensajeError(ex.getMessage(), 404, "El elemento que intentó obtener no fue encontrado"); 
            return Response.status(Status.NOT_FOUND)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(mensajeError)
                    .build();
    }
    
}
