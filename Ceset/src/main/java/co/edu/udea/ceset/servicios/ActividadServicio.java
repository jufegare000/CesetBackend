package co.edu.udea.ceset.servicios;

import co.edu.udea.ceset.bl.AcademicActivityBL;
import co.edu.udea.ceset.dto.AcademicActivityDTO;

import co.edu.udea.ceset.dto.EstimadoDTO;
import co.edu.udea.ceset.dto.EstimatedDTO;
import co.edu.udea.ceset.dto.entities.Academicactivity;

import com.nimbusds.jose.JOSEException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import co.edu.udea.ceset.dto.EstimatedByExpenditureDTO;

/**
 */
@Path("/actividades")
public class ActividadServicio implements Serializable {

    private static final long serialVersionUID = -9066585482051897942L;

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createActivity(AcademicActivityDTO activity) throws JOSEException, IOException {
        String nueva;
        nueva = AcademicActivityBL.getInstance().crear(activity);
        
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
        String acad = AcademicActivityBL.getInstance().obtenerTodods();

        return acad;
    }

    /**
     * Método que retorna un Usuario dado un id
     *
     * @param id : id de la actividad
     * @return acad : actividad retornada
     */
    @GET
    @Path("/{idAcad}")
    @Produces(MediaType.APPLICATION_JSON)
    public Academicactivity obtenerPorId(@PathParam("idAcad") int id) {
        return AcademicActivityBL.getInstance().getById(id);
    }

}
