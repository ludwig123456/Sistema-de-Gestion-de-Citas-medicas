package com.edu.fisi.proyectoclinica.dao.databaseDAO;

import com.edu.fisi.proyectoclinica.dao.TO.CitaTO;
import com.edu.fisi.proyectoclinica.dao.CitaDAO;
//import dao.TO.CitaTO;
import com.edu.fisi.proyectoclinica.dao.TO.HorarioTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

public class CitaDAODB implements CitaDAO {

    //Para preparar consultas con parametros
    public PreparedStatement ps;
    //Para obtener el resultado de la ejecucion de la consulta
    public ResultSet rs;
    //Para poder almacenar la conexion

    //Para obtener la conexion
    public AccesoDB acceso;

    public CitaDAODB() {
        acceso = AccesoDB.getInstance();
        //conexion = acceso.getConexion();

    }

    public boolean create(CitaTO e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            //Obtener la conexion a la BD

            //Preparamos la consulta a ejecutar
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement(
                    "INSERT INTO cita VALUES( default , ?,?,?,?,?)");
            //Indicamos los parametros para la consulta
            ps.setString(1, e.getidMedico());
            ps.setString(2, e.getDNIpaciente());
            ps.setDate(3, java.sql.Date.valueOf(e.getFecha()));
            ps.setTime(4, java.sql.Time.valueOf(e.getHora()));
            ps.setInt(5, e.getidSede());
            // ps.set(4, a); //sqlDate.toLocalDate(date);java.sql.Date.valueOf( localDate );

            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;

    }

    public boolean update(CitaTO e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("UPDATE cita "
                    + "SET idmedico = ?, dni = ?, fecha = ?, idsede = ? "
                    + "WHERE idcita = ?");

            ps.setString(1, e.getidMedico());
            ps.setString(2, e.getDNIpaciente());
            ps.setDate(3, java.sql.Date.valueOf(e.getFecha()));
            ps.setTime(4, java.sql.Time.valueOf(e.getHora()));
            ps.setInt(5, e.getidSede());
            ps.setInt(6, e.getIdCita());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            };
        }
        return resultado;
    }

    public int contar() {
        Connection conexion = null;
        try {
            //Para un procedimiento almacenado
            conexion = acceso.getConexion();
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("select COUNT(*) FROM cita");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    @Override
    public CitaTO read(Integer idCita) {
        CitaTO cita = null;
        Connection conexion = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM cita WHERE idCita = ?");
            ps.setInt(1, idCita);
            rs = ps.executeQuery();

            if (rs.next()) {
                cita = new CitaTO(
                        rs.getInt("idcita"),
                        rs.getString("idmedico"),
                        rs.getString("dni"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getInt("idsede")
                );
                //medicoBuscado.setFechaNacimiento(nacimiento);  //array de horarios

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cita;

    }

    public CitaTO readId(String idmedico, String dni, LocalDate fecha, LocalTime hora, int idsede) {
        CitaTO cita = null;
        Connection conexion = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM cita WHERE idmedico = ? AND"
                    + " dni = ? AND fecha = ? AND hora = ? AND idsede = ? ");
            ps.setString(1, idmedico);
            ps.setString(2, dni);
            ps.setDate(3, java.sql.Date.valueOf(fecha));
            ps.setTime(4, java.sql.Time.valueOf(hora));
            ps.setInt(5, idsede);
            rs = ps.executeQuery();

            if (rs.next()) {
                cita = new CitaTO(
                        rs.getInt("idcita"),
                        rs.getString("idmedico"),
                        rs.getString("dni"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getInt("idsede")
                );
                //medicoBuscado.setFechaNacimiento(nacimiento);  //array de horarios

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cita;

    }

    @Override
    public int read1(Integer dia, LocalTime i, LocalTime f, String med, int sed, LocalDate fech) {
        int a = 0;
        Connection conexion = null;
        try {
            //Obtenemos la conexion

            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT COUNT(fecha)  FROM cita"
                    + " WHERE hora BETWEEN ? AND ? AND idmedico = ? AND idsede = ? AND fecha=?");

            ps.setTime(1, java.sql.Time.valueOf(i));
            ps.setTime(2, java.sql.Time.valueOf(f));
            ps.setString(3, med);
            ps.setInt(4, sed);
            ps.setDate(5, java.sql.Date.valueOf(fech));

            rs = ps.executeQuery();
            while (rs.next()) {

                a = rs.getInt(1);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return a;

    }

    @Override
    public List<CitaTO> readAll() {
        List<CitaTO> listaCita = new ArrayList<>();
        Connection conexion = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM cita");
            rs = ps.executeQuery();

            while (rs.next()) {
                CitaTO cita = new CitaTO(
                        rs.getInt("idcita"),
                        rs.getString("idmedico"),
                        rs.getString("dni"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getInt("idsede")
                );
                listaCita.add(cita);
            }

        } catch (SQLException ex) {

        } finally {
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaCita;

    }
    //TODO Cambiar

    public CitaTO sacarC(LocalTime horaInicio, LocalTime horaFin, String idmedico, int sede, LocalDate fecha, String dni) {
        boolean resultado = false;
        int p = 0;
        int dia = fecha.getDayOfWeek().getValue();
        LocalTime hora = null;

        p = read1(dia, horaInicio, horaFin, idmedico, sede, fecha);
        hora = horaInicio.plusMinutes(p * 30);
//        System.out.println(p);
        if (hora.isBefore(horaFin)) {
//            System.out.println("a3");

            CitaTO cita = new CitaTO(
                    idmedico,
                    dni,
                    fecha,
                    hora,
                    sede);
//            System.out.println(cita);
            create(cita);
            cita = readId(idmedico, dni, fecha, hora, sede);
//            resultado = true;
            return cita;
        } else {
            return null;
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Error");
//            alert.setHeaderText("Error de Datos");
//            alert.setContentText("No se encontro el horario");
//            alert.showAndWait();
        }

    }

    public CitaTO sacarCitaF(String especialidad, String dni, int sede, LocalDate fecha) {
        int cont = 0;
        int p = 0;
        int dia = (fecha.getDayOfWeek().getValue()) % 8; // obtiene la fecha dada y le aumenta un dia
        if (dia == 0) {
            dia = 1;
        }
        HorarioDAODB h = new HorarioDAODB();
        List<HorarioTO> horari = h.horarioMin(especialidad, sede, dia);
        HorarioTO horario = null;
        LocalTime hora = null;
        Boolean resultado = false;
        try {
//            for (int i = 0; i < 5; i++) {
//                if (horari == null) {
//                    dia = (dia + 1) % 7;
//                    fecha.plusDays(1);
//                    h.horarioMin(especialidad, sede, dia);
//                } else {
            while (cont < horari.size()) {
                //Obtenemos la conexionwwwwww
                horario = horari.get(cont);

                p = read1(horario.getDia(), horario.getHoraInicio(), horario.getHoraFin(), horario.getIdmedico(), sede, fecha);
                hora = horario.getHoraInicio().plusMinutes(p * 30);
                
                if (hora.isBefore(horario.getHoraFin())) {
                    CitaTO cita = new CitaTO(
                            // p, // este id no va
                            horario.getIdmedico(),
                            dni,
                            fecha,
                            hora,
                            sede);
                    create(cita);
                    cita = readId(horario.getIdmedico(), dni, fecha, hora, sede);
                    return cita;

                } else {
                    cont++;
//                    h.horarioMin(especialidad, sede,dia);
                }
            }
//                    dia = (dia + 1) % 8;
//                    if (dia == 0) {
//                        dia = 1;
//                    }
//                    fecha.plusDays(1);
//                    h.horarioMin(especialidad, sede, dia);
//                }
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public CitaTO sacarCita(String especialidad, String dni, int sede) {
        int cont = 0;
        int p = 0;
        int dia = (LocalDate.now().getDayOfWeek().getValue() + 1) % 8;
        if (dia == 0) {
            dia = 1;
        }

        LocalDate fecha = LocalDate.now().plusDays(1);
        HorarioDAODB h = new HorarioDAODB();
        List<HorarioTO> horari = h.horarioMin(especialidad, sede, dia);
        HorarioTO horario = null;
        LocalTime hora = null;
        try {
            for (int i = 0; i < 5; i++) {
                cont = 0;
                if (horari.size() == 0) {
                    dia = (dia + 1) % 8;
                    if (dia == 0) {
                        dia = 1;
                    }
                    fecha = fecha.plusDays(1);
                    h.horarioMin(especialidad, sede, dia);
                    
                } else {
                    
                    while (cont < horari.size()) {

                        horario = horari.get(cont);

                        p = read1(horario.getDia(), horario.getHoraInicio(), horario.getHoraFin(),
                                horario.getIdmedico(), sede, fecha);
                        hora = horario.getHoraInicio().plusMinutes(p * 30);
                        if (hora.isBefore(horario.getHoraFin())) {

                            CitaTO cita = new CitaTO(
                                    // p, // este id no va
                                    horario.getIdmedico(),
                                    dni,
                                    fecha,
                                    hora,
                                    sede);
                            create(cita);
                            cita = readId(horario.getIdmedico(), dni, fecha, hora, sede);
                            return cita;
                        } else {
                            cont++;
                        }
                    }
                    dia = (dia + 1) % 8;
                    if (dia == 0) {
                        dia = 1;
                    }
                     fecha = fecha.plusDays(1);
                    
                }
               horari = h.horarioMin(especialidad, sede, dia);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean delete(Integer id) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("DELETE FROM cita WHERE idcita = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

//    public ListaSimple<Medico> readAlltoLista() {
//        ListaSimple<Medico> listaPaciente = new ListaSimple<Medico>();
//        try {
//            //Obtenemos la conexion
//            conexion = acceso.getConexion();
//            ps = conexion.prepareStatement("SELECT * FROM cita");
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
//                // List<Horario> nacimiento = rs.getDate("nacimiento").toLocalDate(); //array
//
//                //Seteamos los valores
//                nuevoMedico.setCodigo(dni);
//                nuevoMedico.setNombres(nombres);
//                nuevoMedico.setApellidos(apellidos);
//                nuevoMedico.setEspecialidad(especialidad);
//                // nuevoMedico.setFechaNacimiento(nacimiento);
//
//
//                listaPaciente.agregarTermino(nuevoMedico);
//                tableMedicos.add(nuevoMedico);
//            }
//
//        } catch (SQLException ex) {
////            Alert alert = new Alert(Alert.AlertType.INFORMATION);
////            alert.setTitle(null);
////            alert.setHeaderText(null);
////            alert.setContentText("No hay base de datos de medicos");
////            alert.showAndWait();
//        }
//        return listaPaciente;

    @Override
    public List<CitaTO> readDNI(String dni) {
        List<CitaTO> listaCita = new ArrayList<>();
        Connection conexion = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM cita WHERE dni = ?");
            ps.setString(1, dni);
            rs = ps.executeQuery();

            while (rs.next()) {
                CitaTO cita = new CitaTO(
                        rs.getInt("idcita"),
                        rs.getString("idmedico"),
                        rs.getString("dni"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getInt("idsede")
                );
                listaCita.add(cita);
            }

        } catch (SQLException ex) {

        } finally {
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaCita;

    }

    @Override
    public List<CitaTO> readEsp(String esp) {
        List<CitaTO> listaCita = new ArrayList<>();
        Connection conexion = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT idcita, idmedico, dni, fecha, hora, idsede"
                    + " FROM cita join medico using (idmedico) WHERE especialidad = ?");
            ps.setString(1, esp);
            rs = ps.executeQuery();

            while (rs.next()) {
                CitaTO cita = new CitaTO(
                        rs.getInt("idcita"),
                        rs.getString("idmedico"),
                        rs.getString("dni"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getInt("idsede")
                );
                listaCita.add(cita);
            }

        } catch (SQLException ex) {

        } finally {
            try {
                conexion.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CitaDAODB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaCita;

    }
}
