/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.utilities;

/**
 *
 * @author jufeg
 */
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 *
 * @author Corrales Bros
 */
public class Utilities {

    private final Logger logger = Logger.getLogger(getClass());

    public static void LoggerDebug(String msg) {

        Utilities utils = new Utilities();
        utils.logger.debug("SIBU-Debug: " + msg);
    }

    public static void LoggerDebug(Logger logger, String msg) {
        String msgDebug;

        msgDebug = "SIBU-Debug: " + msg;

        logger.debug(msgDebug);
    }

    public static String LoggerErrorWithReturnMessage(Logger logger, String msg) {
        String msgError;

        msgError = "SIBU-Error: " + msg;

        logger.error(msgError);

        return msgError;
    }

    public static void LoggerError(Logger logger, String msg) {
        String msgError;

        Date currentDate = new Date();
        msgError = "ADM-Error " + currentDate.toString() + ", Mensaje: " + msg;

        logger.error(msgError);
    }

    public static void LoggerError(String msg) {

        Utilities utils = new Utilities();
        utils.logger.error("SIBU-Error:" + msg);
    }
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}