package co.edu.udea.ceset.bl;


import co.edu.udea.ceset.dao.AcademicactivityDAO;
import co.edu.udea.ceset.dto.AcademicActivityDTO;
import co.edu.udea.ceset.dto.Academicactivity;
import co.edu.udea.ceset.dto.Estimated;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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


    
    public Collection<Academicactivity> obtenerTodods(){
        return this.obtenerAcadDAO().findAcademicactivityEntities();
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
     
      public Academicactivity crear(AcademicActivityDTO academicactivity) {
          Academicactivity acad = new Academicactivity();
          List<Estimated> miEstimadoL = (List<Estimated>) academicactivity.getEstimatedCollection();
          Estimated miEstimado = miEstimadoL.get(0);
          Estimated miEstimadoN = EstimatedBL.getInstance().crear(miEstimado);
          // teniendo creado el nuevo estimado se debe setear a la actividad a crear
          
          acad.setear(academicactivity);
          //miEstimadoL.clear();
          miEstimadoL.set(0, miEstimadoN);
          acad.setEstimatedCollection(miEstimadoL);
         return obtenerAcadDAO().create(acad);
    }
      
    public Academicactivity getById(int n){
        return obtenerAcadDAO().findAcademicactivity(n);
    }

}
