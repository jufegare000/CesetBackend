/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.bl;

import co.edu.udea.ceset.dao.RolDao;
import co.edu.udea.ceset.dto.Rolec;
import java.util.Collection;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author jufeg
 */
public class RolBL {

    private static RolBL singletonInstance = new RolBL();

    private static final Logger LOG = Logger.getLogger(RolBL.class.getName());

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

    public Collection<Rolec> getAll() {
        return obtenerRolDao().getAll();
    }

    public RolDao obtenerRolDao() {
        return new RolDao();
    }
    
    public Rolec obtenerPorId(int id) {
        return obtenerRolDao().getById(id);
    }
    
    public String crear(String nombre, String descripcion) {
        Rolec rol = new Rolec();
;
        rol.setDescription(descripcion);
        
        rol.setCreatedAt(new Date());
        rol.setUpdatedAt(new Date());
        rol.setStates("Active");
        
        return obtenerRolDao().create(rol);
    }

    public static Logger getLog() {
        return LOG;
    }
}
