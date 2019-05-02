 package co.edu.udea.ceset.excepciones;

import co.edu.udea.ceset.dto.entities.MensajeError;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Para todas las excepciones que no estén definidas, se aplica este mapper
 * @author edevargas
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{
    
   
    @Override
    public Response toResponse(Throwable ex) {
        MensajeError mensajeError =  new MensajeError(ex.toString(), 400, "Mensaje genérico"); 
            return Response.status(Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(mensajeError)
                    .build();
    }


    
}
