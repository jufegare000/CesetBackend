/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.bl;

import co.edu.udea.ceset.dao.RolDao;
import co.edu.udea.ceset.dto.Rol;
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

    public Collection<Rol> getAll() {
        return obtenerRolDao().getAll();
    }

    public RolDao obtenerRolDao() {
        return new RolDao();
    }
    
    public Rol obtenerPorId(int id) {
        return obtenerRolDao().getById(id);
    }
    
    public String crear(int id, String nombre, String estado) {
        Rol rol = new Rol();
        rol.setId(id);
        rol.setNombre(nombre);
        rol.setEstado(estado);
        rol.setFechaCreacion(new Date());
        rol.setFechaModificacion(new Date());
        
        return obtenerRolDao().create(rol);
    }

    public static Logger getLog() {
        return LOG;
    }
}
