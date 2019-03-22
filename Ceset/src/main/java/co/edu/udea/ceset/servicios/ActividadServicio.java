package co.edu.udea.ceset.servicios;

import co.edu.udea.ceset.bl.AcademicActivityBL;
import co.edu.udea.ceset.bl.EstimatedBL;
import co.edu.udea.ceset.dto.AcademicActivityDTO;

import co.edu.udea.ceset.dto.Academicactivity;
import co.edu.udea.ceset.dto.Estimated;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.jose.JOSEException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Root resource (exposed at "usuarios" path)
 */
@Path("/actividades")
public class ActividadServicio implements Serializable {

    private static final long serialVersionUID = -9066585482051897942L;
    
    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createActivity(AcademicActivityDTO activity) throws JOSEException, IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();;
        String yeison;
        
        Academicactivity  nueva = AcademicActivityBL.getInstance().crear(activity);
//         if(activity.getIdUser() == null)
//             return Response
//                     .status(Response.Status.NOT_ACCEPTABLE)
//                     .build();
        yeison = gson.toJson(nueva).toString();
        return Response
                .status(Response.Status.ACCEPTED)
                .type(MediaType.APPLICATION_JSON)
                .entity(yeison)
                .build();
    }

    @Path("/todas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        String yeison;
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        //System.out.println(gson.toJson(user));

        Collection<Academicactivity> acad = AcademicActivityBL.getInstance().obtenerTodods();
        yeison = gson.toJson(acad).toString();
        return yeison;
    }

    /**
     * Método que retorna un Usuario dado un id
     *
     * @param id : id del Usuario
     * @return Usuario : Usuario en formato Json
     */
    @GET
    @Path("/{idAcad}")
    @Produces(MediaType.APPLICATION_JSON)
    public Academicactivity obtenerPorId(@PathParam("idAcad") int id) {
        return AcademicActivityBL.getInstance().getById(id);
    }

}
