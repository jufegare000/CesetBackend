/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao.cnf;

import co.edu.udea.ceset.utilities.Utilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author jufeg
 */
public class JDBCConnectionPool {

    private static DataSource dataSource = null;
    private Connection cnx;

    public Connection getConnection() throws SQLException, NamingException, Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (dataSource == null) {
                InitialContext initialContext = new InitialContext();
                dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/db_wf_ceset");
            }
            cnx = dataSource.getConnection();
            cnx.setAutoCommit(true);
        } catch (SQLException | NamingException e) {
            Utilities.LoggerDebug(e.getMessage());
            throw new Exception(e);
        } catch (Exception e){
            Utilities.LoggerDebug(e.getMessage());
        }
        return cnx;
    }
    
    public void close (PreparedStatement ps, ResultSet rs) throws Exception{
        try {
            if(!empty(cnx)){
                cnx.close();
            }
            if(!empty(ps)){
                ps.close();
            }
            if(!empty(rs)){
                rs.close();
            }
            
        } catch (Exception e) {
            Utilities.LoggerError(e.getMessage());
            throw e;
        }
    }
    
    public boolean empty(Object obj){
        if(obj == null){
            return true;
        }
        if(obj instanceof String){
            return obj.toString().trim().equals("");
        }
        return false;
    }
}
