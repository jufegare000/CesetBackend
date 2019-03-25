/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.bl;

import co.edu.udea.ceset.dao.RolebyuserDAO;
import co.udea.edu.co.dto.entities.Rolebyuser;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jufeg
 */
public class RoleByUserBL implements Serializable{
     private static final long serialVersionUID = 4044273845736534004L;
    private static RoleByUserBL singletonInstance = new RoleByUserBL();
    private final String nombrePU = "ceset_PU";

    private RoleByUserBL() {
    }

    public static RoleByUserBL getInstance() {
        if (singletonInstance == null) { // Single Checked
            synchronized (RoleByUserBL.class) {
                if (singletonInstance == null) { // Double checked
                    singletonInstance = new RoleByUserBL();
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

   
    public List<Rolebyuser> obtenerTodods(){
        return this.obtenerDAO().findRolebyuserEntities();
    }

    /**
     * Método para obtener una usuario dado un Id
     *
     * @param id : Id del Usuario
     * @return : Usuario
     */
    public Rolebyuser obtenerPorId(int id) {
        return obtenerDAO().findRolebyuser(id);
    }

    

    /**
     * Método que rotorna el DAO para rolebyuser
     *
     * @return RolebyuserDAO
     */

    
     private RolebyuserDAO obtenerDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        RolebyuserDAO DAO = new RolebyuserDAO(emf);
        return DAO;
    }

}
