/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.databaseDAO;

import dao.PacienteDAO;
import dao.TO.PacienteTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PacienteDAODB implements PacienteDAO {

    //Para preparar consultas con parametros
    private PreparedStatement ps;
    //Para obtener el resultado de la ejecucion de la consulta
    private ResultSet rs;
    //Para poder almacenar la conexion

    //Para obtener la conexion
    private AccesoDB acceso;

    public PacienteDAODB() {
        acceso = AccesoDB.getInstance();
        //conexion = acceso.getConexion();

    }

    @Override
    public boolean create(PacienteTO e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            //Obtener la conexion a la BD
            
            
            //Preparamos la consulta a ejecutar
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement(
                    "INSERT INTO paciente VALUES(?,?,?,?,?,?,?,?,?)");
            //Indicamos los parametros para la consulta
            ps.setInt(1, Integer.parseInt(e.getDni()));
            ps.setString(2, e.getNombres());
            ps.setString(3, e.getApellidos());
            ps.setString(4,e.getDireccion());
            ps.setDate(5, java.sql.Date.valueOf(e.getFechaNacimiento())); //sqlDate.toLocalDate(date);java.sql.Date.valueOf( localDate );
            ps.setInt(6,Integer.parseInt( e.getTelefono()));
            ps.setInt(7,Integer.parseInt( e.getCelular()));
            ps.setString(8, String.valueOf(e.getGenero()));
            ps.setString(9, e.getTipoSangre());
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
    public boolean update(PacienteTO e) {
        boolean resultado = true;
        Connection conexion = null;
        try {
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("UPDATE paciente "
                    + "SET nombres = ?, apellidos = ?, direccion = ?, fecha_nac = ?, telefono = ?, celular = ?,genero = ? , tipoSangre = ?"
                    + " WHERE dni = ?");

            ps.setString(1, e.getNombres());
            ps.setString(2, e.getApellidos());
            ps.setString(3,e.getDireccion());
            ps.setDate(4, java.sql.Date.valueOf(e.getFechaNacimiento())); //sqlDate.toLocalDate(date);java.sql.Date.valueOf( localDate );
            ps.setInt(5,Integer.parseInt( e.getTelefono()));
            ps.setInt(6,Integer.parseInt( e.getCelular()));
            ps.setString(7, String.valueOf(e.getGenero()));
            ps.setString(8, e.getTipoSangre());
            ps.setInt(9, Integer.parseInt(e.getDni()));
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
    public PacienteTO read(String dni) {
        Connection conexion = null;
        PacienteTO pacienteBuscado = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM paciente WHERE dni = ?");
            ps.setString(1, dni);
            rs = ps.executeQuery();

            if (rs.next()) {
                pacienteBuscado = new PacienteTO(
                        String.valueOf(rs.getInt("dni")),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_nac").toLocalDate(),
                        String.valueOf(rs.getInt("telefono")),
                        String.valueOf(rs.getInt("celular")),
                        rs.getString("genero").charAt(0),
                        rs.getString("tipoSangre"));
                
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
        return pacienteBuscado;

    }

    @Override
    public List<PacienteTO> readAll() {
        Connection conexion = null;
        List<PacienteTO> listaPaciente = new ArrayList<>();
        PacienteTO nuevoPaciente = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM paciente LIMIT 500");
            rs = ps.executeQuery();

            while (rs.next()) {
                nuevoPaciente = new PacienteTO(
                        String.valueOf(rs.getInt("dni")),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_nac").toLocalDate(),
                        String.valueOf(rs.getInt("telefono")),
                        String.valueOf(rs.getInt("celular")),
                        rs.getString("genero").charAt(0),
                        rs.getString("tipoSangre"));
                //Leeemos de la bd
           //     System.out.println(nuevoPaciente);
                listaPaciente.add(nuevoPaciente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
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
    public boolean delete(String id) {
        Connection conexion = null;
        boolean resultado = true;
        try {
            //Para un procedimiento almacenado
            conexion = acceso.getConexion();
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("DELETE FROM paciente WHERE dni = ? ");
            ps.setString(1, id);
            rs = ps.executeQuery();
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
    public int contar(){
        Connection conexion = null;
        try {
            //Para un procedimiento almacenado
            conexion = acceso.getConexion();
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("select COUNT(*) FROM paciente");
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
   
//public ArbolAVL<Paciente> readAlltoArbol() throws Exception {
//        ArbolAVL<Paciente> listaPaciente = new ArbolAVL<Paciente>();
//        try {
//            //Obtenemos la conexion
//            conexion = acceso.getConexion();
//            ps = conexion.prepareStatement("SELECT * FROM PACIENTE");
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                String dni = rs.getString("dni");
//                String nombres = rs.getString("nombres");
//                String apellidos = rs.getString("apellidos");
//                LocalDate nacimiento = rs.getDate("nacimiento").toLocalDate();
//                float peso = rs.getFloat("peso");
//                float talla = rs.getFloat("talla");
//                //Seteamos los valores
//                Paciente nuevoPaciente = new Paciente(dni,nombres,apellidos,peso,talla,nacimiento);
//
//                listaPaciente.insertar(nuevoPaciente);
//                Estructuras.tablePacientes.add(nuevoPaciente);
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage() );
//            return null;
//        }
//        return listaPaciente;
//
//    }
//public ListaSaltos<Paciente> readAlltoJumpList() {
//        ListaSaltos<Paciente> listaPaciente = new ListaSaltos<Paciente>();
//        try {
//            //Obtenemos la conexion
//            conexion = acceso.getConexion();
//            ps = conexion.prepareStatement("SELECT * FROM PACIENTE");
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                //Leeemos de la bd
//                String dni = rs.getString("dni");
//                String nombres = rs.getString("nombres");
//                String apellidos = rs.getString("apellidos");
//                LocalDate nacimiento = rs.getDate("nacimiento").toLocalDate();
//                float peso = rs.getFloat("peso");
//                float talla = rs.getFloat("talla");
//                //Seteamos los valores
//                Paciente nuevoPaciente = new Paciente(dni,nombres,apellidos,peso,talla,nacimiento);
//                listaPaciente.insertar(nuevoPaciente);
//                Estructuras.tablePacientes.add(nuevoPaciente);
//            }
//
//        } catch (SQLException ex) {
//
//        }
//        return listaPaciente;
//
//    }
//    public List<Producto> consultarConCategoria() {
//        List<Producto> listaProductos = new ArrayList<>();
//        try {
//            //Obtenemos la conexion
//            conexion = acceso.getConexion();
//            ps = conexion.prepareStatement("select p.codigo, \n"
//                    + "	   p.descripcion descripcionPro,\n"
//                    + "       p.precio,\n"
//                    + "       c.descripcion  descripcionCat\n"
//                    + "  from producto p, categoria c \n"
//                    + "  where p.Categoria_codigo = c.codigo; \n"
//                    + "	");
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Producto nuevoProducto = new Producto();
//                //Leeemos de la bd
//                int codigo = rs.getInt("codigo");
//                String descripcion = rs.getString("descripcionPro");
//                float precio = rs.getFloat("precio");
//                Categoria cat = new Categoria();
//                cat.setDescripcion(rs.getString("descripcionCat"));
//                //Seteamos los valores
//                nuevoProducto.setCodigo(codigo);
//                nuevoProducto.setDescripcion(descripcion);
//                nuevoProducto.setPrecio(precio);
//                nuevoProducto.setCat(cat);
//
//                listaProductos.add(nuevoProducto);
//            }
//
//        } catch (SQLException ex) {
//
//        }
//        return listaProductos;
//
//    }
//
//    public Producto consultarPorCodigoConCategoria(Integer id) {
//        Producto productoBuscado = null;
//        try {
//            //Obtenemos la conexion
//            conexion = acceso.getConexion();
//            ps = conexion.prepareStatement("SELECT P.codigo,\n"
//                    + "	   P.descripcion desPro,\n"
//                    + "       P.precio,\n"
//                    + "       c.codigo codCat,\n"
//                    + "       c.descripcion desCat\n"
//                    + "	FROM PRODUCTO P, CATEGORIA C\n"
//                    + "    WHERE p.CODIGO = ? "
//                    + "	  AND P.Categoria_codigo = c.CODIGO;");
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                productoBuscado = new Producto();
//                //Leeemos de la bd
//                int codigo = rs.getInt("codigo");
//                String descripcion = rs.getString("desPro");
//                float precio = rs.getFloat("precio");
//                
//                Categoria cat = new Categoria();
//                cat.setCodigo(rs.getInt("codCat"));
//                cat.setDescripcion(rs.getString("desCat"));
//                
//                //Seteamos los valores
//                productoBuscado.setCodigo(codigo);
//                productoBuscado.setDescripcion(descripcion);
//                productoBuscado.setPrecio(precio);
//                productoBuscado.setCat(cat);
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        } finally {
//            acceso.cerrarConexion(conexion, ps);
//        }
//        return productoBuscado;
//
//    }

    
}
