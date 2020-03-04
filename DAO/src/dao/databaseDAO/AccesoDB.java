/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.databaseDAO;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lab02-Pc11
 */
public class AccesoDB {

    private static final String USUARIO = "root";
    private static final String PASWORD = "fisi2018";
    private static final String HOST = "bica.cwkmzboxgefl.ca-central-1.rds.amazonaws.com";//ip de remotemysql,com
    private static final String BD = "bica";
    //private DBCPDataSource dc = new DBCPDataSource();
   //   private  BasicDataSource ds = new BasicDataSource();

    

     private static AccesoDB datasource;
 // Esta es la fuente de datos que conecta con la base de datos
 private ComboPooledDataSource cpds = null;
 
 /**
  * Crea el constructor del pool, notara que este constructor es privado
  * esto con el fin de que podamos controlar cuando se crea el pool
  * @throws IOException
  * @throws SQLException
  * @throws PropertyVetoException
  */
 private AccesoDB(){
        try {
            // Configuramos la conexion a base de datos
            // Creamos la fuente de datos
            cpds = new ComboPooledDataSource();
            // Que driver de base de datos usaremos
            cpds.setDriverClass("com.mysql.jdbc.Driver");
            // La url de la base de datos a la que nos conectaremos
            cpds.setJdbcUrl("jdbc:mysql://" + HOST + "/" + BD+"?useSSL=false" );
            // Usuario de esa base de datos
            cpds.setUser(USUARIO);
            // Contraseña de la base de datos
            cpds.setPassword(PASWORD);
            cpds.setAcquireRetryDelay(200);
            // Configuramos el pool
            // Numero de conexiones con las que iniciara el pool
            cpds.setInitialPoolSize(10);
            // Minimo de conexiones que tendra el pool
            cpds.setMinPoolSize(10);
            // Numero de conexiones a crear cada incremento
            cpds.setAcquireIncrement(1);
            // Maximo numero de conexiones
            cpds.setMaxPoolSize(50);
            // Maximo de consultas
            cpds.setMaxStatements(180);
            // Maximo numero de reintentos para conectar a base de datos
            cpds.setAcquireRetryAttempts(0);
            // Que se genere una excepcion si no se puede conectar
            cpds.setBreakAfterAcquireFailure(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AccesoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 
 /**
  * Nos regresa la instancia actual del pool, en caso que no halla una instancia
  * crea una nueva y la regresa
  * @return
  */
 public static AccesoDB getInstance() {
 
  if (datasource == null) {
   datasource = new AccesoDB();
   return datasource;
  } else {
   return datasource;
  }
 }
 
 /**
  * Este metodo nos regresa una conexion a base de datos, esta la podemos
  * usar como una conexion usual
  * @return Conexion a base de datos
  
  */
 public Connection getConexion() throws SQLException  {
       
            return this.cpds.getConnection();
       
      
 }
  
     
    
//    public void cerrarConexion(PreparedStatement ps) {
//        try {
//            ps.close();
//            connectionPool.releaseConnection(conexion);
//        } catch (SQLException ex) {
//            Logger.getLogger(AccesoDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public void cerrarConexion(PreparedStatement ps, ResultSet rs) {
//        try {
//            ps.close();
//            rs.close();
//            connectionPool.releaseConnection(conexion);
//        } catch (SQLException ex) {
//            Logger.getLogger(AccesoDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

}
