package co.edu.udea.ceset.bl;


import co.edu.udea.ceset.dao.AcademicactivityDAO;
import co.edu.udea.ceset.dto.Academicactivity;
import java.io.Serializable;
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


    
    public List<Academicactivity> obtenerTodods(){
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
     
      public void crear(Academicactivity academicactivity) {
         obtenerAcadDAO().create(academicactivity);
    }

}
