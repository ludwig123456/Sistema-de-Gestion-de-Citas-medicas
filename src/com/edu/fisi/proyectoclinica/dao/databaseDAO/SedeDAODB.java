/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.dao.databaseDAO;

import com.edu.fisi.proyectoclinica.dao.SedeDAO;
import com.edu.fisi.proyectoclinica.dao.TO.SedeTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coarita
 */
public class SedeDAODB implements SedeDAO {

    private PreparedStatement ps;
    //Para obtener el resultado de la ejecucion de la consulta
    private ResultSet rs;
    //Para poder almacenar la conexion

    //Para obtener la conexion
    private AccesoDB acceso;

    public SedeDAODB() {
        acceso = AccesoDB.getInstance();
        //conexion = acceso.getConexion();

    }

    @Override
    public boolean create(SedeTO e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            //Obtener la conexion a la BD

            //Preparamos la consulta a ejecutar
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement(
                    "INSERT INTO sedes VALUES(?,?,?,?)");
            //Indicamos los parametros para la consulta
            ps.setInt(1, e.getIdSede());
            ps.setString(2, e.getNombre());
            ps.setString(3, String.valueOf(e.getDireccion()));
            ps.setInt(4, e.getTelefono());
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
    public boolean update(SedeTO e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("UPDATE sedes "
                    + "SET nombre = ?, direccion = ?, telefono = ?"
                    + " WHERE idsedes = ?");

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getDireccion());
            ps.setInt(3, e.getTelefono());
            ps.setInt(4, e.getIdSede());
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
    public SedeTO read(Integer id) {
        SedeTO buscado = null;
        Connection conexion = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM sedes WHERE idsedes = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                buscado = new SedeTO(
                        rs.getInt("idsedes"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getInt("telefono")
                );
                //medicoBuscado.setFechaNacimiento(nacimiento);  //array de horarios

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
    public SedeTO readN(String id) {
        SedeTO buscado = null;
        Connection conexion = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM sedes WHERE nombre = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                buscado = new SedeTO(
                        rs.getInt("idsedes"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getInt("telefono")
                );
                //medicoBuscado.setFechaNacimiento(nacimiento);  //array de horarios

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return buscado;

    }
    public int contar(){
        Connection conexion = null;
        try {
            //Para un procedimiento almacenado
            conexion = acceso.getConexion();
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("select COUNT(*) FROM sedes");
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

    @Override
    public List<SedeTO> readAll() {
        Connection conexion = null;
        List<SedeTO> listaSedes = new ArrayList<>();
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM sedes");
            rs = ps.executeQuery();

            while (rs.next()) {
                SedeTO cita = new SedeTO(
                        rs.getInt("idsedes"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getInt("telefono")
                );
                listaSedes.add(cita);
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
        return listaSedes;

    }

    @Override
    public boolean delete(Integer id) {
        Connection conexion = null;
        boolean resultado = true;
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("DELETE FROM sede WHERE idsedes = ?");
            ps.setInt(1, id);
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
   

}
