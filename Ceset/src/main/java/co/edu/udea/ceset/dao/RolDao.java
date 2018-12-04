/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dao.cnf.JDBCConnectionPool;
import co.edu.udea.ceset.dto.Rol;
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
    
    public String create(Rol rol) {
        PreparedStatement psCreateRol = null;
        ResultSet rs = null;
        try {
            psCreateRol = getConnection().prepareStatement(
                    "INSERT INTO wfc_rol VALUES (" + 
                            rol.getId() + ", \"" +
                            rol.getNombre() + "\", \"" + 
                            rol.getEstado() + "\", \"" + 
                            rol.sqlDateFormat(rol.getFechaCreacion()) + "\", \"" + 
                            rol.sqlDateFormat(rol.getFechaModificacion()) + "\")"
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
    
    public Rol getById(int id) {
        PreparedStatement psGetRol = null;
        ResultSet rs = null;
        Rol rol = new Rol();
        
        try {
            psGetRol = getConnection().prepareStatement(
                    "SELECT * FROM wfc_rol WHERE id = " + id);
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
    
    public Collection<Rol> getAll(){
        PreparedStatement psGetAll = null;
        ResultSet rs = null;
        Collection<Rol> listaRoles = new LinkedList<>();
        
        try {
            psGetAll = getConnection().prepareStatement(
                     "Select * from wfc_rol");
            rs = psGetAll.executeQuery();
            if(rs != null){
                while(rs.next()){
                    Rol rol = mapRol(rs);
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
    
    private Rol mapRol(ResultSet rs) throws SQLException{
        Rol rol = new Rol();
        try {
            rol.setId(rs.getInt("id"));
            rol.setNombre(rs.getString("descripcion"));
            rol.setEstado(rs.getString("estado"));
            rol.setFechaCreacion(rs.getDate("fechaCreacion"));
            rol.setFechaModificacion(rs.getDate("fechaModificacion"));
        } catch (SQLException e) {
            throw e;
        }
        return rol;
    }
}
