/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.properties;

import java.util.ResourceBundle;
import java.io.Serializable;

/**
 *
 * @author edevargas
 */
public class PropiedadesCeset implements Serializable {

    private static final long serialVersionUID = 2659588778996579335L;
    private static PropiedadesCeset singletonInstance = new PropiedadesCeset();
    // Ubicado en src/main/resources. Si no están los properties en esta ruta, maven no lo compila
    private ResourceBundle propiedadesAplicacion = ResourceBundle.getBundle("application");
    private ResourceBundle propiedadesAutorizacion = ResourceBundle.getBundle("authorization");

    private PropiedadesCeset() {
    }

    public static PropiedadesCeset getInstance() {
        if (singletonInstance == null) { // Single Checked
            synchronized (PropiedadesCeset.class) {
                if (singletonInstance == null) { // Double checked
                    singletonInstance = new PropiedadesCeset();
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

    public ResourceBundle getPropiedadesAplicacion() {
        return propiedadesAplicacion;
    }

    public void setPropiedadesAplicacion(ResourceBundle propiedadesAplicacion) {
        this.propiedadesAplicacion = propiedadesAplicacion;
    }

    public ResourceBundle getPropiedadesAutorizacion() {
        return propiedadesAutorizacion;
    }

    public void setPropiedadesAutorizacion(ResourceBundle propiedadesAutorizacion) {
        this.propiedadesAutorizacion = propiedadesAutorizacion;
    }

    
    
}
