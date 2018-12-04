 package co.edu.udea.ceset.excepciones;

import co.edu.udea.ceset.dto.MensajeError;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Para todas las excepciones RollbackException, se aplica este mapper
 * @author edevargas
 */
@Provider
public class BusinessLogicalExepectionMapper implements ExceptionMapper<BusinessLogicalException>{
    
   
    @Override
    public Response toResponse(BusinessLogicalException ex) {
        MensajeError mensajeError =  new MensajeError(ex.getMessage(), 400, "Error de Lógica del negocio"); 
            return Response.status(Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(mensajeError)
                    .build();
    }


    
}
