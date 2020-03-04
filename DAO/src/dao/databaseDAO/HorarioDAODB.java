/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.databaseDAO;

import dao.HorarioDAO;
import dao.TO.HorarioTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HorarioDAODB implements HorarioDAO {

   
    //Para preparar consultas con parametros
    private PreparedStatement ps;
    //Para obtener el resultado de la ejecucion de la consulta
    private ResultSet rs;
    //Para poder almacenar la conexion

    //Para obtener la conexion
    private AccesoDB acceso;

    public HorarioDAODB() {
        acceso = AccesoDB.getInstance();
        //conexion = acceso.getConexion();

    }

    @Override
    public boolean create(HorarioTO e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            //Obtener la conexion a la BD

            //Preparamos la consulta a ejecutar
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement(
                    "INSERT INTO horario VALUES(?,?,?,?,?)");
            //Indicamos los parametros para la consulta
            ps.setInt(1, e.getDia());
            ps.setTime(2, java.sql.Time.valueOf(e.getHoraInicio()));
            ps.setTime(3, java.sql.Time.valueOf(e.getHoraFin()));
            ps.setString(4, e.getIdmedico());
            ps.setInt(5, e.getIdsede());

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
    public boolean update(HorarioTO e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("UPDATE horario "
                    + "SET hora_fin = ?, idsede = ?"
                    + " WHERE idmedico = ? AND dia = ? AND hora_inicio = ?");

            ps.setTime(1, java.sql.Time.valueOf(e.getHoraFin()));
            ps.setInt(2, e.getIdsede());
            ps.setString(3, e.getIdmedico());
            ps.setInt(4, e.getDia());
            ps.setTime(5, java.sql.Time.valueOf(e.getHoraInicio()));
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
    public List<HorarioTO> read(String id) { // no vale porqe horario tiene 3 claves PK pero 
        ArrayList<HorarioTO>  listaHorario = new ArrayList<>(); //tengo qe usarlo por ser metodo abstracto
        Connection conexion = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM horario "
                    + "WHERE idmedico = ? ");
            ps.setString(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                HorarioTO nuevoHorario = new HorarioTO(
                        rs.getInt("dia"),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_fin").toLocalTime(),
                        rs.getString("idmedico"),
                        rs.getInt("idsede"));
                listaHorario.add(nuevoHorario);

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
        return listaHorario;

    }
    @Override
    public int contar(){
        Connection conexion = null;
        try {
            //Para un procedimiento almacenado
            conexion = acceso.getConexion();
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("select COUNT(*) FROM horario");
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
    public HorarioTO read(String id, int dia, int horaI) {
        Connection conexion = null;
        HorarioTO horarioBuscado = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM horario "
                    + "WHERE idmedico = ? AND dia = ? AND hora_inicio = ?");
            ps.setString(1, id);
            ps.setInt(2, dia);
            ps.setInt(3, horaI);
            rs = ps.executeQuery();

            if (rs.next()) {
                horarioBuscado = new HorarioTO(
                        rs.getInt("dia"),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_fin").toLocalTime(),
                        rs.getString("idmedico"),
                        rs.getInt("idsede")
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
        return horarioBuscado;

    }

    @Override
    public List<HorarioTO> readAll() {
        Connection conexion = null;
        List<HorarioTO> listaHorario = new ArrayList<>();
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM horario");
            rs = ps.executeQuery();

            while (rs.next()) {
                HorarioTO nuevoHorario = new HorarioTO(
                        rs.getInt("dia"),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_fin").toLocalTime(),
                        rs.getString("idmedico"),
                        rs.getInt("idsede"));
                listaHorario.add(nuevoHorario);
            }

        } catch (SQLException ex) {

        }finally{
             try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaHorario;

    }

      @Override
    public List<HorarioTO>  horarioMin(String especialidad, int idsede, int dia) {
        Connection conexion = null;
        List<HorarioTO> listaHorario = new ArrayList<>();
        
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM horario h join medico m on h.idmedico = m.idmedico "
                    + "WHERE especialidad = ? AND idsede = ? AND dia = ? order by hora_inicio asc;");
//            ps = conexion.prepareStatement("SELECT dia,hora_inicio,hora_fin,m.idmedico,idsede FROM horario h join medico m on h.idmedico = m.idmedico "
//                    + "WHERE especialidad = ? AND idsede = ? AND dia = ? order by hora_inicio asc;");
            ps.setString(1, especialidad);
            ps.setInt(2, idsede);
            ps.setInt(3, dia);

            rs = ps.executeQuery();
            
            while (rs.next()) {
                    HorarioTO nuevoHorario = new HorarioTO(
                            rs.getInt("dia"),
                            rs.getTime("hora_inicio").toLocalTime(),
                            rs.getTime("hora_fin").toLocalTime(),
                            rs.getString("idmedico"),
                            rs.getInt("idsede"));
                    listaHorario.add(nuevoHorario);
                
            }
        } catch (SQLException ex) {

        }finally{
             try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaHorario;

    }

    @Override
    public boolean delete(String id) {
        Connection conexion = null;
        boolean resultado = true;
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("DELETE FROM horario WHERE idmedico = ?");//no vale
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

    @Override
    public boolean delete(HorarioTO horario) {
        Connection conexion = null;
        boolean resultado = false;
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("DELETE FROM horario "
                    + "WHERE idmedico = ? AND dia = ? AND hora_inicio = ? AND hora_fin = ?");
            ps.setString(1, horario.getIdmedico());
            ps.setInt(2, horario.getDia());
            ps.setTime(3, java.sql.Time.valueOf(horario.getHoraInicio()));
            ps.setTime(4, java.sql.Time.valueOf(horario.getHoraFin()));
            ps.execute();
            resultado = true;
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
