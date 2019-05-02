/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.servicios;


import co.edu.udea.ceset.bl.AcademicActivityBL;
import co.edu.udea.ceset.bl.PortafolioBL;
import co.edu.udea.ceset.dto.AcademicActivityDTO;
import co.udea.edu.co.dto.entities.Portafolio;
import com.nimbusds.jose.JOSEException;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author Juan
 */
@Path("/portafolio")
public class PortafolioServicio implements Serializable{
      private static final long serialVersionUID = -9066585482051897942L;

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPortafolio(Portafolio port) throws JOSEException, IOException {
        String nueva;
        nueva = PortafolioBL.getInstance().crear(port);
        
        return Response
                .status(Response.Status.ACCEPTED)
                .type(MediaType.APPLICATION_JSON)
                .entity(nueva)
                .build();
    }
    @Path("/todas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        String acad = PortafolioBL.getInstance().obtenerTodods();

        return acad;
    }
}
