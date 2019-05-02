package co.edu.udea.ceset.servicios;

import co.edu.udea.ceset.bl.CohortBL;
import co.edu.udea.ceset.dto.CohortDTO;
import co.edu.udea.ceset.dto.entities.Cohort;
import com.nimbusds.jose.JOSEException;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class CohortServicio {
    private static final long serialVersionUID = -9066585482051897942L;

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCohort(CohortDTO cohort) throws JOSEException, IOException {
        String nueva;
        nueva = CohortBL.getInstance().crear(cohort);

        return Response
                .status(Response.Status.ACCEPTED)
                .type(MediaType.APPLICATION_JSON)
                .entity(nueva)
                .build();
    }

    @GET
    @Path("/{idCohort}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cohort obtenerPorId(@PathParam("idCohort") int id) {
        return CohortBL.getInstance().getById(id);
    }

}
