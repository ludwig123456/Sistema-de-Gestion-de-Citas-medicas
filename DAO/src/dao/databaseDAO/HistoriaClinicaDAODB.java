/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.databaseDAO;

import dao.HistoriaClinicaDAO;
import dao.TO.HistoriaClinicaTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HistoriaClinicaDAODB implements HistoriaClinicaDAO {

    //Para preparar consultas con parametros
    private PreparedStatement ps;
    //Para obtener el resultado de la ejecucion de la consulta
    private ResultSet rs;
    //Para poder almacenar la conexion
    //Para obtener la conexion
    private AccesoDB acceso;

    public HistoriaClinicaDAODB() {
        acceso = AccesoDB.getInstance();
        //conexion = acceso.getConexion();

    }

    @Override
    public boolean create(HistoriaClinicaTO e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            //Obtener la conexion a la BD

            //Preparamos la consulta a ejecutar
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement(
                    "INSERT INTO historiaclinica VALUES(default,?,?,?,?,?,?,?,?,?,?,?,?)");
            //Indicamos los parametros para la consulta
            ps.setString(1, e.getDni());
            ps.setInt(2, e.getIdcita());
            ps.setFloat(3, e.getPeso());
            ps.setInt(4, e.getTalla());
            ps.setDate(5, java.sql.Date.valueOf(e.getFecha()));
            ps.setTime(6, java.sql.Time.valueOf(e.getHora()));
            ps.setString(7, e.getIdmedico());
            ps.setInt(8, e.getPresion());
            ps.setInt(9, e.getPulso());
            ps.setString(10, e.getDiagnosticoB());
            ps.setString(11, e.getDiagnosticoC());
            ps.setString(12, e.getReceta());

            // ps.set(4, a); //sqlDate.toLocalDate(date);java.sql.Date.valueOf( localDate );
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        }finally {
             try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;

    }
    @Override
    public int contar(){
        Connection conexion = null;
        try {
            //Para un procedimiento almacenado
            conexion = acceso.getConexion();
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("select COUNT(*) FROM historiaclinica");
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
    public boolean update(HistoriaClinicaTO e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("UPDATE historiaclinica "
                    + "SET dni = ?, idcita = ?, peso = ?, talla = ?, fecha = ?,"
                    + " hora = ?, idmedico = ?, presion = ?, pulso = ?, diagnosticoBreve = ?,"
                    + " diagnosticoCompleto = ?, receta = ?"
                    + " WHERE numHistoria = ?");

            
            ps.setString(1, e.getDni());
            ps.setInt(2, e.getIdcita());
            ps.setFloat(3, e.getPeso());
            ps.setInt(4, e.getTalla());
            ps.setDate(5, java.sql.Date.valueOf(e.getFecha()));
            ps.setTime(6, java.sql.Time.valueOf(e.getHora()));
            ps.setString(7, e.getIdmedico());
            ps.setInt(8, e.getPresion());
            ps.setInt(9, e.getPulso());
            ps.setString(10, e.getDiagnosticoB());
            ps.setString(11, e.getDiagnosticoC());
            ps.setString(12, e.getReceta());
            ps.setInt(13, e.getCodigo());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        }finally {
             try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public HistoriaClinicaTO read(String numHistoria) {
        HistoriaClinicaTO HistoriaCBuscada = null;
        Connection conexion = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM historiaclinica WHERE numHistoria = ?");
            ps.setString(1, numHistoria);
            rs = ps.executeQuery();

            if (rs.next()) {
                HistoriaCBuscada = new HistoriaClinicaTO(
                        rs.getInt("numHistoria"),
                        rs.getString("dni"),
                        rs.getInt("idcita"),
                        rs.getFloat("peso"),
                        rs.getInt("talla"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("idmedico"),
                        rs.getInt("presion"),
                        rs.getInt("pulso"),
                        rs.getString("diagnosticoBreve"),
                        rs.getString("diagnosticoCompleto"),
                        rs.getString("receta")
                );     

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally {
              try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return HistoriaCBuscada;

    }

    @Override
    public List<HistoriaClinicaTO> readAll() {
        List<HistoriaClinicaTO> listaHistoriaC = new ArrayList<>();
        Connection conexion = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM historiaclinica");
            rs = ps.executeQuery();

            while (rs.next()) {
              HistoriaClinicaTO nuevaHistoriaC = new HistoriaClinicaTO(
                        rs.getInt("numHistoria"),
                        rs.getString("dni"),
                        rs.getInt("idcita"),
                        rs.getFloat("peso"),
                        rs.getInt("talla"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("idmedico"),
                        rs.getInt("presion"),
                        rs.getInt("pulso"),
                        rs.getString("diagnosticoBreve"),
                        rs.getString("diagnosticoCompleto"),
                        rs.getString("receta"));
                listaHistoriaC.add(nuevaHistoriaC);                
            }

        } catch (SQLException ex) {

        }finally {
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaHistoriaC;

    }

    @Override
    public boolean delete(String id) {
        Connection conexion = null;
        boolean resultado = true;
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("DELETE FROM historiaclinica WHERE idmedico = ?");
            ps.setString(1, id);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        }finally {
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public List<HistoriaClinicaTO> readDNI(String s) {
         List<HistoriaClinicaTO> listaHistoriaC = new ArrayList<>();
        Connection conexion = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM historiaclinica where dni = ?");
            ps.setString(1, s);
            rs = ps.executeQuery();

            while (rs.next()) {
              HistoriaClinicaTO nuevaHistoriaC = new HistoriaClinicaTO(
                        rs.getInt("numHistoria"),
                        rs.getString("dni"),
                        rs.getInt("idcita"),
                        rs.getFloat("peso"),
                        rs.getInt("talla"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("idmedico"),
                        rs.getInt("presion"),
                        rs.getInt("pulso"),
                        rs.getString("diagnosticoBreve"),
                        rs.getString("diagnosticoCompleto"),
                        rs.getString("receta"));
                listaHistoriaC.add(nuevaHistoriaC);                
            }

        } catch (SQLException ex) {

        }finally {
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaHistoriaC;
    }
 
}
