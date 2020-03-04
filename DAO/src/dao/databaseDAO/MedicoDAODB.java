/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.databaseDAO;

import dao.MedicoDAO;
import dao.TO.MedicoTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MedicoDAODB implements MedicoDAO {

    //Para preparar consultas con parametros
    private PreparedStatement ps;
    //Para obtener el resultado de la ejecucion de la consulta
    private ResultSet rs;
    //Para poder almacenar la conexion

    //Para obtener la conexion
    private AccesoDB acceso;

    public MedicoDAODB() {
        acceso = AccesoDB.getInstance();
        //conexion = acceso.getConexion();
    }

    @Override
    public boolean create(MedicoTO e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            //Obtener la conexion a la BD

            //Preparamos la consulta a ejecutar
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement(
                    "INSERT INTO medico VALUES(?,?,?,?,?,?,?,?,?)");
            //Indicamos los parametros para la consulta
            ps.setString(1, e.getCodigo());
            ps.setString(2, e.getNombres());
            ps.setString(3, e.getApellidos());
            ps.setString(4, e.getEspecialidad());
            ps.setDate(5, java.sql.Date.valueOf(e.getFechaN()));
            ps.setString(6, e.getGenero());
            ps.setString(7, e.getDireccion());
            ps.setInt(8, e.getCelular());
            ps.setInt(9, e.getTelefono());

            // ps.set(4, a); //sqlDate.toLocalDate(date);java.sql.Date.valueOf( localDate );
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
    public boolean update(MedicoTO e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("UPDATE medico "
                    + "SET nombre = ?, apellido = ?, especialidad = ?, fecha_nac = ?, genero = ?,"
                    + " direccion = ?, celular = ?, telefono = ?"
                    + " WHERE idmedico = ?");

            ps.setString(1, e.getNombres());
            ps.setString(2, e.getApellidos());
            ps.setString(3, e.getEspecialidad());
            ps.setDate(4, java.sql.Date.valueOf(e.getFechaN()));
            ps.setString(5, e.getGenero());
            ps.setString(6, e.getDireccion());
            ps.setInt(7, e.getCelular());
            ps.setInt(8, e.getTelefono());
            ps.setString(9, e.getCodigo());
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
    public MedicoTO read(String dni) {
        Connection conexion = null;
        MedicoTO medicoBuscado = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM medico WHERE idmedico = ?");
            ps.setString(1, dni);
            rs = ps.executeQuery();

            if (rs.next()) {
                medicoBuscado = new MedicoTO(
                        rs.getString("idmedico"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("especialidad"),
                        rs.getDate("fecha_nac").toLocalDate(),
                        rs.getString("genero"),
                        rs.getString("direccion"),
                        rs.getInt("celular"),
                        rs.getInt("telefono")
                );
                //Leeemos de la bd

//                String id = rs.getString("codigo");
//                String nombres = rs.getString("nombre");
//                String apellidos = rs.getString("apellido");
//                String especialidad = rs.getString("especialidad");
//
//                //Seteamos los valores
//                medicoBuscado.setCodigo(id);
//                medicoBuscado.setNombres(nombres);
//                medicoBuscado.setApellidos(apellidos);
//                medicoBuscado.setEspecialidad(especialidad);
//                //medicoBuscado.setFechaNacimiento(nacimiento);  //array de horarios              

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
        return medicoBuscado;

    }
    
    @Override
    public MedicoTO readMedico(String apellidos,String nombre) {
        Connection conexion = null;
        MedicoTO medicoBuscado = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM medico WHERE apellido = ? AND nombre = ?");
            ps.setString(1, apellidos);
            ps.setString(2, nombre);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                medicoBuscado = new MedicoTO(
                        rs.getString("idmedico"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("especialidad"),
                        rs.getDate("fecha_nac").toLocalDate(),
                        rs.getString("genero"),
                        rs.getString("direccion"),
                        rs.getInt("celular"),
                        rs.getInt("telefono")
                ); 
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
        return medicoBuscado;

    }

    @Override
    public List<MedicoTO> readAll() {
        Connection conexion = null;
        List<MedicoTO> listaMedico = new ArrayList<>();
         MedicoTO nuevoMedico = null;
        try {
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM medico limit 500");
           // ps.setString(1, apellido);
            rs = ps.executeQuery();
           
            while (rs.next()) {
                
               listaMedico.add(new MedicoTO(
                        rs.getString("idmedico"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("especialidad"),
                        rs.getDate("fecha_nac").toLocalDate(),
                        rs.getString("genero"),
                        rs.getString("direccion"),
                        rs.getInt("celular"),
                        rs.getInt("telefono")                
                ));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAODB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listaMedico;

    }

    @Override
    public List<MedicoTO> buscarMedicoPorApellido(String apellido) {
        Connection conexion = null;
        List<MedicoTO> listaMedico = new ArrayList<>();
         MedicoTO nuevoMedico = null;
        try {
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM medico WHERE apellido LIKE ?");
            ps.setString(1, apellido);
            rs = ps.executeQuery();
           
            while (rs.next()) {
                
               listaMedico.add(new MedicoTO(
                        rs.getString("idmedico"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("especialidad"),
                        rs.getDate("fecha_nac").toLocalDate(),
                        rs.getString("genero"),
                        rs.getString("direccion"),
                        rs.getInt("celular"),
                        rs.getInt("telefono")                
                ));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAODB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listaMedico;

    }


         @Override
    public List<MedicoTO> buscarPorEspecialidad(String especialidad){
        Connection conexion = null;
        ArrayList<MedicoTO> arr = new ArrayList();
        try {
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM medico WHERE especialidad = ?");
            ps.setString(1, especialidad);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                arr.add(new MedicoTO(
                        rs.getString("idmedico"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("especialidad"),
                        rs.getDate("fecha_nac").toLocalDate(),
                        rs.getString("genero"),
                        rs.getString("direccion"),
                        rs.getInt("celular"),
                        rs.getInt("telefono")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAODB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arr;
    }
    @Override
    public int contar(){
        Connection conexion = null;
        try {
            //Para un procedimiento almacenado
            conexion = acceso.getConexion();
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("select COUNT(*) FROM medico");
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
    public boolean delete(String id) {
        Connection conexion = null;
        boolean resultado = true;
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("DELETE FROM medico WHERE idmedico = ?");
            ps.setString(1, id);
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

    
