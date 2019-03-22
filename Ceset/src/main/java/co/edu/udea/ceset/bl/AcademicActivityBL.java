package co.edu.udea.ceset.bl;


import co.edu.udea.ceset.dao.AcademicactivityDAO;
import co.edu.udea.ceset.dto.AcademicActivityDTO;
import co.edu.udea.ceset.dto.Academicactivity;
import co.edu.udea.ceset.dto.Estimated;
import co.edu.udea.ceset.utilities.Utilities;
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


    
    public String obtenerTodods(){
        String parsed;
        Collection<Academicactivity> todas = this.obtenerAcadDAO().findAcademicactivityEntities();
        for (Academicactivity acad   : todas) {
                this.organizaActividad(acad);
            }
        parsed = Utilities.jasonizer(todas);
        return parsed;
    }

    public void organizaActividad(Academicactivity acad){
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
          Academicactivity acad = new Academicactivity();
          List<Estimated> miEstimadoL = (List<Estimated>) academicactivity.getEstimatedCollection();
          Estimated miEstimado = miEstimadoL.get(0);
          Estimated miEstimadoN = EstimatedBL.getInstance().crear(miEstimado);
          Academicactivity creado;
          String retorno;
          // teniendo creado el nuevo estimado se debe setear a la actividad a crear
          
          acad.setear(academicactivity);
          //miEstimadoL.clear();
          miEstimadoL.set(0, miEstimadoN);
          acad.setEstimatedCollection(miEstimadoL);
          creado = obtenerAcadDAO().create(acad);
          this.organizaActividad(acad);
          retorno = Utilities.jasonizer(creado);
          
         return retorno;
    }
      
    public Academicactivity getById(int n){
        return obtenerAcadDAO().findAcademicactivity(n);
    }

}
