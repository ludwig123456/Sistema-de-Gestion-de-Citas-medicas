/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.dao.databaseDAO;

import com.edu.fisi.proyectoclinica.dao.TO.UsuarioTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.edu.fisi.proyectoclinica.dao.UsuarioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LinKinPark
 */
public class UsuarioDAODB implements UsuarioDAO {

    //Para preparar consultas con parametros
    private PreparedStatement ps;
    //Para obtener el resultado de la ejecucion de la consulta
    private ResultSet rs;
    //Para poder almacenar la conexion
    //Para obtener la conexion
    private AccesoDB acceso;

    public UsuarioDAODB() {
        acceso = AccesoDB.getInstance();
        //conexion = acceso.getConexion();

    }

    
    @Override
    public boolean create(UsuarioTO e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            //Obtener la conexion a la BD
            
            //Preparamos la consulta a ejecutar
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement(
                    "INSERT INTO usuario VALUES(?,?,?,?)");
            //Indicamos los parametros para la consulta
            ps.setString(1, e.getUsername());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getSalt());
            ps.setString(4, e.getType());            
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        }finally{
             try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;

    }


    @Override
    public boolean update(UsuarioTO e) {
        Connection conexion = null;
        boolean resultado = true;
        try {
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("UPDATE usuario "
                    + "SET username = ?, pass = ?,salt=?, tipo = ?"
                    + " WHERE username = ?");
            
           ps.setString(1, e.getUsername().toLowerCase());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getSalt());
            ps.setString(4, String.valueOf(e.getUsername()));  
           ps.setString(5, e.getUsername());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        }finally{
             try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public UsuarioTO read(String usr) {
        Connection conexion = null;
        UsuarioTO  buscado = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM usuario WHERE username = ?");
            ps.setString(1, usr);
            rs = ps.executeQuery();

            if (rs.next()) {
                String usuario = rs.getString("username").toLowerCase();
                String pass = rs.getString("pass");
                String salt = rs.getString("salt");

                String tipo =  rs.getString("tipo");
                buscado = new UsuarioTO(usuario,pass,salt,tipo);
                //Leeemos de la bd
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return buscado;

    }

    @Override
    public List<UsuarioTO> readAll() {
        Connection conexion = null;
        List<UsuarioTO> listaPaciente = new ArrayList<>();
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM usuario");
            rs = ps.executeQuery();

            while (rs.next()) {
                //Leeemos de la bd
                 String usuario = rs.getString("username").toLowerCase();
                String pass = rs.getString("pass");
                String salt = rs.getString("salt");

                String tipo =  rs.getString("tipo");
                UsuarioTO buscado = new UsuarioTO(usuario,pass,salt,tipo);
               // List<Horario> nacimiento = rs.getDate("nacimiento").toLocalDate(); //array
                
                //Seteamos los valores
           
               // nuevoMedico.setFechaNacimiento(nacimiento);
                

                listaPaciente.add(buscado);
            }

        } catch (SQLException ex) {

        }finally{
             try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaPaciente;

    }

    @Override
    public boolean delete(String usr) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("DELETE FROM usuario WHERE username = ?");
            ps.setString(1, usr);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        }finally{
             try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }
//    public Usuario log(String usr,String pas){
//        Usuario buscado = null;
//        try {
//            //Obtenemos la conexion
//            ps = conexion.prepareStatement("SELECT * FROM usuario WHERE BINARY username = ? && pass = ?");
//            ps.setString(1, usr);
//            ps.setString(2, pas);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                String usuario = rs.getString("codigo").toLowerCase();
//                String pass = rs.getString("nombres");
//                char tipo =  rs.getString("apellidos").charAt(0);
//                buscado = new Usuario(usuario,pass,tipo);
//                //Leeemos de la bd
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return buscado;
//    }
  
    public int contar(){
        Connection conexion = null;
        try {
            //Para un procedimiento almacenado
            conexion = acceso.getConexion();
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("select COUNT(*) FROM usuario");
           rs = ps.executeQuery();
             if (rs.next()) {
                 return rs.getInt(1);
             }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }finally{
             try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

//    public ListaSimple<Medico> readAlltoLista() {
//        ListaSimple<Medico> listaPaciente = new ListaSimple<Medico>();
//        try {
//            //Obtenemos la conexion
//            conexion = acceso.getConexion();
//            ps = conexion.prepareStatement("SELECT * FROM MEDICO");
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Medico nuevoMedico = new Medico();
//                //Leeemos de la bd
//                String dni = rs.getString("codigo");
//                String nombres = rs.getString("nombres");
//                String apellidos = rs.getString("apellidos");
//                String especialidad = rs.getString("especialidad");
//                nuevoMedico.SethorarioRandom();
//               // List<Horario> nacimiento = rs.getDate("nacimiento").toLocalDate(); //array
//                
//                //Seteamos los valores
//                nuevoMedico.setCodigo(dni);
//                nuevoMedico.setNombres(nombres);
//                nuevoMedico.setApellidos(apellidos);
//                nuevoMedico.setEspecialidad(especialidad);
//               // nuevoMedico.setFechaNacimiento(nacimiento);
//                
//
//                listaPaciente.agregarTermino(nuevoMedico);
//                tableMedicos.add(nuevoMedico);
//            }
//
//        } catch (SQLException ex) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle(null);
//            alert.setHeaderText(null);
//            alert.setContentText("No hay base de datos de medicos");
//            alert.showAndWait();
//        }
//        return listaPaciente;
//
//    }    
    /**
     * Devuelve los tipos de usuario que estan en la bd
     * @return  
     */
    @Override
    public List<String> getTiposUsuario() {
        Connection conexion = null;
         List<String> listaUsuarios = new ArrayList<>();
         try {
            //Para un procedimiento almacenado
            conexion = acceso.getConexion();
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("select tipo FROM usuario GROUP BY tipo");
           rs = ps.executeQuery();
             while (rs.next()) {
                 listaUsuarios.add(rs.getString(1));
             }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }finally{
             try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaUsuarios;
    }
  
}
