 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dto.entities;

import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author edevargas 
 */
@XmlRootElement 
public class MensajeError {
    private String mensajeError;
    private int codigoError;
    private String documentacion;

    public MensajeError() {
    }

    
    public MensajeError(String mensajeError, int codigoError, String documentacion) {
        super();
        this.mensajeError = mensajeError;
        this.codigoError = codigoError;
        this.documentacion = documentacion;
    }
 
    
    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public int getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(int codigoError) {
        this.codigoError = codigoError;
    }

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }
    
}
