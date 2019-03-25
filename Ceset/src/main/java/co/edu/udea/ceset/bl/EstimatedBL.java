package co.edu.udea.ceset.bl;


import co.edu.udea.ceset.dao.EstimatedDao;
import co.udea.edu.co.dto.entities.Estimated;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase de la Lógica del negocio para Usuarios
 *
 * @author edevargas
 */
public class EstimatedBL implements Serializable {

    private static final long serialVersionUID = 4044273845736534004L;
    private static EstimatedBL singletonInstance = new EstimatedBL();
    private final String nombrePU = "ceset_PU";

    private EstimatedBL() {
    }

    public static EstimatedBL getInstance() {
        if (singletonInstance == null) { // Single Checked
            synchronized (EstimatedBL.class) {
                if (singletonInstance == null) { // Double checked
                    singletonInstance = new EstimatedBL();
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

    /**
     * Método que rotorna el DAO para Personas
     *
     * @return PersonDAO
     */

    
     private EstimatedDao getEstimatedDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        EstimatedDao DAO = new EstimatedDao(emf);
        return DAO;
    }
     
      public Estimated crear(Estimated estmd) {
        return getEstimatedDAO().create(estmd);
    }

      public List<Estimated> getAll() {
        return getEstimatedDAO().findEstimatedEntities();
    }
}
