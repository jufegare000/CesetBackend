/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.cnf.JDBCConnectionPool;
import co.edu.udea.ceset.dto.Rolec;
import co.edu.udea.ceset.utilities.Utilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import org.apache.log4j.Logger;

/**
 *
 * @author jufeg
 */
public class RolDao extends JDBCConnectionPool {
    private final Logger logger = Logger.getLogger(getClass());
    
    public String create(Rolec rol) {
        PreparedStatement psCreateRol = null;
        ResultSet rs = null;
        try {
            psCreateRol = getConnection().prepareStatement(
                    "INSERT INTO `tbl_role`(`description`, `CreatedAt`, " + ""
                            + "`UpdatedAt`, `States`) VALUES (" + 
                          
                            rol.getDescription()+ "\", \"" + 
                            rol.sqlDateFormat(rol.getCreatedAt()) + "\", \"" + 
                            rol.sqlDateFormat(rol.getUpdatedAt()) + "\", \"" + 
                            rol.getStates() + "\")"
            );
            psCreateRol.executeUpdate();
        } catch (SQLException e) {
            Utilities.LoggerError(logger, e.getMessage());
            return e.getMessage();
        } catch (Exception e) {
            Utilities.LoggerError(logger, e.getMessage());
            return e.getMessage();
        } finally{
            try {
                close(psCreateRol, rs);
            } catch (Exception e) {
                Utilities.LoggerError(e.getMessage());
                return e.getMessage();
            }
        }
        return "Se insertó el registro correctamente";
    }
    
    public Rolec getById(int id) {
        PreparedStatement psGetRol = null;
        ResultSet rs = null;
        Rolec rol = new Rolec();
        
        try {
            psGetRol = getConnection().prepareStatement(
                    "SELECT * FROM tbl_rol WHERE id = " + id);
            rs = psGetRol.executeQuery();
            if(rs != null) {
                while (rs.next()) {
                    rol = mapRol(rs);
                }
            }
        } catch (SQLException e){
            Utilities.LoggerError(logger, e.getMessage());
        } 
        
        catch (Exception e) {
                Utilities.LoggerError(logger, e.getMessage());
        } finally{
            try {
                close(psGetRol, rs);
            } catch (Exception e) {
                Utilities.LoggerError(e.getMessage());
            }
        }
        return rol;
    }
    
    public Collection<Rolec> getAll(){
        PreparedStatement psGetAll = null;
        ResultSet rs = null;
        Collection<Rolec> listaRoles = new LinkedList<>();
        
        try {
            psGetAll = getConnection().prepareStatement(
                     "Select * from tbl_rol");
            rs = psGetAll.executeQuery();
            if(rs != null){
                while(rs.next()){
                    Rolec rol = mapRol(rs);
                    listaRoles.add(rol);
                    
                }
            }
        }catch (SQLException e){
            Utilities.LoggerError(logger, e.getMessage());
        } 
        
        catch (Exception e) {
                Utilities.LoggerError(logger, e.getMessage());
        }finally{
            try {
                close(psGetAll, rs);
            } catch (Exception e) {
                Utilities.LoggerError(e.getMessage());
            }
        }
        return listaRoles;
    }
    
    private Rolec mapRol(ResultSet rs) throws SQLException{
        Rolec rol = new Rolec();
        try {
            rol.setIdRole(rs.getInt("idRole"));
            rol.setDescription(rs.getString("description"));
            rol.setCreatedAt(rs.getDate("CreatedAt"));
            rol.setUpdatedAt(rs.getDate("UpdatedAt"));
            rol.setStates(rs.getString("States"));
        } catch (SQLException e) {
            throw e;
        }
        return rol;
    }
}
