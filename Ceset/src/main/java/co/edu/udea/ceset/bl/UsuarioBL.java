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
public class UsuarioBL implements Serializable {

    private static final long serialVersionUID = 4044273845736534004L;
    private static UsuarioBL singletonInstance = new UsuarioBL();
    private final String nombrePU = "ceset_PU";

    private UsuarioBL() {
    }

    public static UsuarioBL getInstance() {
        if (singletonInstance == null) { // Single Checked
            synchronized (UsuarioBL.class) {
                if (singletonInstance == null) { // Double checked
                    singletonInstance = new UsuarioBL();
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
     * Método que retorna un Usuario dado cualquier nombre de usuario y clave
     * @param usuario
     * @param clave
     * @return 
     */
    public User autenticar(String user, String clave) {
        return obtenerUsuarioDAO().autenticar(user, clave);
    }
    
    public List<User> obtenerTodods(){
        return this.obtenerUserDAO().getAll();
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
    
     private UserDAO obtenerUserDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        UserDAO DAO = new UserDAO(emf);
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
