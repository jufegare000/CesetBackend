package co.edu.udea.ceset.bl;

import co.edu.udea.ceset.dao.AcademicactivityDAO;
import co.edu.udea.ceset.dto.AcademicActivityDTO;
import co.edu.udea.ceset.dto.entities.Academicactivity;
import co.edu.udea.ceset.dto.entities.Estimated;
import co.edu.udea.ceset.utilities.Utilities;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.edu.udea.ceset.dto.entities.Estimatedbyexpenditure;
import org.modelmapper.ModelMapper;

/**
 * Clase de la Lógica del negocio para Usuarios
 *
 * @author edevargas
 */
public class AcademicActivityBL implements Serializable {

    private static final long serialVersionUID = 4044273845736534004L;
    private static AcademicActivityBL singletonInstance = new AcademicActivityBL();
    private final String nombrePU = "ceset_PU";

    private AcademicActivityBL() {
    }

    public static AcademicActivityBL getInstance() {
        if (singletonInstance == null) { // Single Checked
            synchronized (AcademicActivityBL.class) {
                if (singletonInstance == null) { // Double checked
                    singletonInstance = new AcademicActivityBL();
                }
            }
        }
        return singletonInstance;
    }

    /**
     * Replacing the stream object with the same instance object, to maintain
     * singleton.
     *
     * @return
     */
    protected Object readResolve() {
        return getInstance();
    }

    public String obtenerTodods() {
        String parsed;
        Collection<Academicactivity> todas = this.obtenerAcadDAO().findAcademicactivityEntities();
        for (Academicactivity acad : todas) {
            this.organizaActividad(acad);
        }
        parsed = Utilities.jasonizer(todas);
        return parsed;
    }

    public void organizaActividad(Academicactivity acad) {
        acad.getIdUser().setPassword("");
        acad.getIdUser().setIdPerson(null);
        acad.getIdUser().setRolebyuserCollection(null);
        acad.getIdUser().setAcademicactivityCollection(null);
    }

    /**
     * Método que rotorna el DAO para Actividades Académicas
     *
     * @return AcademicactivityDAO
     */
    private AcademicactivityDAO obtenerAcadDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        AcademicactivityDAO DAO = new AcademicactivityDAO(emf);
        return DAO;
    }

    public String crear(AcademicActivityDTO academicactivity) {
        ModelMapper modelMapper = new ModelMapper();
        Academicactivity acad = modelMapper.map(academicactivity, Academicactivity.class);

        // Academicactivity acad = new Academicactivity();
        //List<Estimated> miEstimadoL = (List<Estimated>) acad.getEstimatedCollection();
        //Estimated miEstimado = miEstimadoL.get(0);
        //Collection<Estimatedbyexpenditure> expends = miEstimado.getEstimatedbyexpenditureCollection();
        List<Estimatedbyexpenditure> expendsCres = new LinkedList<>();
        Estimatedbyexpenditure nu = null;
        /*for (Estimatedbyexpenditure expend : expends) {
            nu = EstemExpendBL.getInstance().crear(expend);
            expendsCres.add(nu);
        }*/
        //miEstimado.setEstimatedbyexpenditureCollection(null);
        //miEstimado.setEstimatedbyexpenditureCollection(expendsCres);
//        Estimated miEstimadoN = EstimatedBL.getInstance().crear(miEstimado);

        Academicactivity creado;
        String retorno;
        // teniendo creado el nuevo estimado se debe setear a la actividad a crear
       // miEstimadoL.set(0, miEstimadoN);
        //acad.setEstimatedCollection(miEstimadoL);
        creado = obtenerAcadDAO().create(acad);
        this.organizaActividad(acad);
        retorno = Utilities.jasonizer(creado);

        return retorno;
    }

    public Academicactivity getById(int n) {
        return obtenerAcadDAO().findAcademicactivity(n);
    }

}
