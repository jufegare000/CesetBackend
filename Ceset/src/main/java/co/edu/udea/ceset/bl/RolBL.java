/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.bl;

import co.edu.udea.ceset.dao.RolDAO;
import co.udea.edu.co.dto.entities.Rolec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.apache.log4j.Logger;
import javax.persistence.Persistence;
/**
 *
 * @author jufeg
 */
public class RolBL {

    private static RolBL singletonInstance = new RolBL();
    
    private static final Logger LOG = Logger.getLogger(RolBL.class.getName());
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final String nombrePU = "ceset_PU";

    public static RolBL getInstance() {
        synchronized (RolBL.class) {
            if (singletonInstance == null) {
                singletonInstance = new RolBL();
            }
        }
        return singletonInstance;
    }

    protected Object readResolve() {
        return getInstance();
    }

    public List<Rolec> getAll() {
        return obtenerRolDao().findRolecEntities();
    }

    public RolDAO obtenerRolDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        RolDAO DAO = new RolDAO(emf);
        return DAO;

    }
   
    public Rolec obtenerPorId(int id) {
        return obtenerRolDao().findRolec(id);
    }
    
    public void crear(String descripcion) {
        Rolec rol = new Rolec();
        rol.setDescription(descripcion);
        
        rol.setCreatedAt(new Date());
        rol.setUpdatedAt(new Date());
        rol.setStates("Active");
        
        obtenerRolDao().create(rol);
    }

    public static Logger getLog() {
        return LOG;
    }
}
