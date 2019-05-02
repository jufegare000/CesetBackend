/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.bl;

import co.edu.udea.ceset.dao.PortafolioDAO;
import co.edu.udea.ceset.utilities.Utilities;
import co.udea.edu.co.dto.entities.Portafolio;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;

/**
 *
 * @author Juan
 */
public class PortafolioBL {

    private static final long serialVersionUID = 4044273845736534004L;
    private static PortafolioBL singletonInstance = new PortafolioBL();
    private final String nombrePU = "ceset_PU";

    private PortafolioBL() {
    }

    public static PortafolioBL getInstance() {
        if (singletonInstance == null) { // Single Checked
            synchronized (AcademicActivityBL.class) {
                if (singletonInstance == null) { // Double checked
                    singletonInstance = new PortafolioBL();
                }
            }
        }
        return singletonInstance;
    }
    
    public String crear(Portafolio port){
        Portafolio nuevo;
        nuevo = obtenerDAO().create(port);
        String retorno = Utilities.jasonizer(nuevo);
        return retorno;
        
    }
    
    private PortafolioDAO obtenerDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombrePU);
        PortafolioDAO DAO = new PortafolioDAO(emf);
        return DAO;
    }

    public String obtenerTodods() {
        String parsed;
        Collection<Portafolio> todas = this.obtenerDAO().findPortafolioEntities();
        parsed = Utilities.jasonizer(todas);
        return parsed;
    }
}
