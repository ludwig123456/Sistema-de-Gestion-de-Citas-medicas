package dao.databaseDAO;

import dao.EspecialidadesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EspecialidadesDAODB  implements EspecialidadesDAO {

    //Para preparar consultas con parametros
    private PreparedStatement ps;
    //Para obtener el resultado de la ejecucion de la consulta
    private ResultSet rs;
    //Para poder almacenar la conexion
    //Para obtener la conexion
    private AccesoDB acceso;

    public EspecialidadesDAODB() {
        acceso =  AccesoDB.getInstance();
        //conexion = acceso.getConexion();

    }

    @Override
    public boolean create(String e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            //Obtener la conexion a la BD

            //Preparamos la consulta a ejecutar
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement(
                    "INSERT INTO especialidades VALUES(?)");
            //Indicamos los parametros para la consulta
            ps.setString(1, e);

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
    public boolean update(String e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("UPDATE especialidades "
                    + "SET especialidad = ?");


            ps.setString(1, e);
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
    public String read(String e) {
        String s = null;
        Connection conexion = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM especialidad WHERE especialidad = ?");
            ps.setString(1, e);
            rs = ps.executeQuery();

            if (rs.next()) {
              s = rs.getString("especialidad");

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
        return s;

    }
    @Override
     public int contar(){
         Connection conexion = null;
        try {
            //Para un procedimiento almacenado
            conexion = acceso.getConexion();
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("select COUNT(*) FROM especialidades");
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
    public List<String> readAll() {
        List<String> listaHistoriaC = new ArrayList<>();
        Connection conexion = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM especialidades");
            rs = ps.executeQuery();

            while (rs.next()) {

                listaHistoriaC.add(rs.getString("especialidad"));
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
        return listaHistoriaC;

    }

    @Override
    public boolean delete(String id) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("DELETE FROM especialidades WHERE especialidad = ?");
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
