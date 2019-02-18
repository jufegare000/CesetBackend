/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ceset.dao;

import co.edu.udea.ceset.dto.Person;
import co.edu.udea.ceset.dto.Rol;
import co.edu.udea.ceset.dto.User;
import co.edu.udea.ceset.dto.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author edevargas
 */
public class UsuarioDAO implements Serializable {

    /**
     * Método que retorna un usuario por defecto dado cualquier id
     *
     * @return Usuario
     */
    public User obtenerPorId(int id) {
        User usuario = new User();
        Person prsn = new Person();
        prsn.setCompleteName("Satanas");
        prsn.setDocument("66666");
        prsn.setIdPerson(2);
        
        //Datos Dummies
        usuario.setDateCreation(null);
        usuario.setIdUser(2);
        usuario.setPassword("6699");
        usuario.setStates("YormanJa");
       

        Rol rolAdmin = new Rol();
        rolAdmin.setEstado("ACT");
        rolAdmin.setFechaCreacion(new Date());
        rolAdmin.setId(1);
        rolAdmin.setNombre("ADMINISTRADOR");

        ArrayList<Rol> listaRoles = new ArrayList<Rol>();
        listaRoles.add(rolAdmin);

//        usuario.setRoles(listaRoles);

        return usuario;

    }

    /**
     * Método que retorna un usuario por defecto dado cualquier nombre de
     * usuario y clave
     *
     * @return Usuario
     */
    public User autenticar(String nombreUsuario, String clave) {
        User usuario = new User();
        
        /*usuario.setEstado("ACT");
        usuario.setFechaCreacion(new Date());
        usuario.setId(1);
        usuario.setIdentificacion("111111");
        usuario.setNombreCompleto("Jhon Doe");
        usuario.setNombreUsuario(nombreUsuario);

        Rol rolAdmin = new Rol();
        rolAdmin.setEstado("ACT");
        rolAdmin.setFechaCreacion(new Date());
        rolAdmin.setId(1);
        rolAdmin.setNombre("ADMINISTRADOR");

        Rol rolUsuario = new Rol();
        rolUsuario.setEstado("ACT");
        rolUsuario.setFechaCreacion(new Date());
        rolUsuario.setId(1);
        rolUsuario.setNombre("USUARIO");

        ArrayList<Rol> listaRoles = new ArrayList<Rol>();
        listaRoles.add(rolAdmin);
        listaRoles.add(rolUsuario);

        usuario.setRoles(listaRoles);
*/
        return usuario;

    }

}
