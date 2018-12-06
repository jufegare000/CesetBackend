package co.edu.udea.ceset.bl;


import co.edu.udea.ceset.dao.PersonDAO;
import co.edu.udea.ceset.dao.UserDAO;
import co.edu.udea.ceset.dao.UsuarioDAO;
import co.edu.udea.ceset.dto.Person;
import co.edu.udea.ceset.dto.User;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase de la Lógica del negocio para Usuarios
 *
 * @author edevargas
 */
public class PersonBL implements Serializable {

    private static final long serialVersionUID = 4044273845736534004L;
    private static PersonBL singletonInstance = new PersonBL();
    private final String nombrePU = "ceset_PU";

    private PersonBL() {
    }

    public static PersonBL getInstance() {
        if (singletonInstance == null) { // Single Checked
            synchronized (PersonBL.class) {
                if (singletonInstance == null) { // Double checked
                    singletonInstance = new PersonBL();
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


    
    public List<Person> obtenerTodods(){
        return this.obtenerPersonDAO().findPersonEntities();
    }

    /**
     * Método para obtener una usuario dado un Id
     *
     * @param id : Id del Usuario
     * @return : Usuario
     */
    public User obtenerPorId(int id) {
        return obtenerUsuarioDAO().obtenerPorId(id);
    }

    

    /**
     * Método que rotorna el DAO para Usuarios
     *
     * @return UsuarioDAO
     */
    private UsuarioDAO obtenerUsuarioDAO() {
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO;
    }
    
     private PersonDAO obtenerPersonDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        PersonDAO DAO = new PersonDAO(emf);
        return DAO;
    }
     
      public void crear(Person prsn) {
        
        obtenerPersonDAO().create(prsn);
    }

}
