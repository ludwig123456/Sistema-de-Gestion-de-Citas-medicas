/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica;

import com.edu.fisi.proyectoclinica.entidades.Cita;
import com.edu.fisi.proyectoclinica.login.Usuario;
import com.edu.fisi.proyectoclinica.dao.databaseDAO.UsuarioDAODB;
import com.edu.fisi.proyectoclinica.dao.databaseDAO.MedicoDAODB;
import com.edu.fisi.proyectoclinica.dao.databaseDAO.PacienteDAODB;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.edu.fisi.proyectoclinica.dao.databaseDAO.AccesoDB;
import java.sql.SQLException;

//lista con saltos vs arbol binario
//poner indicador para diferenciar 
//tiempo de ejecucion lista saltos 5s max
//arbol 4-7s max
// cita proxima presentacion
/**
 *
 * @author Nagamine
 */
public class MainLoader extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        // ArbolAVL<Paciente> pacientes= new ArbolAVL<>();
        //ListaSimple<Medico> medicos = new ListaSimple<>();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource( "com/edu/fisi/vistas/MainUsuario.fxml"));
        //MainAdminController aController = loader.<MainAdminController>getController();
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/edu/fisi/proyectoclinica/vistas/Login.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Parent loading = FXMLLoader.load(getClass().getClassLoader().getResource("com/edu/fisi/proyectoclinica/vistas/loading.fxml"));

        Scene scene = new Scene(loading);
        Scene login = new Scene(root);
        stage.setScene(scene);
        Task task = new Task() {
            @Override
            public Boolean call() {
                
//                Usuario admin = new Usuario("admin", "123", 'A'); //temporal
//                Usuario user = new Usuario("user", "123", 'U'); //temporal
//                Estructuras.usuarios.agregarTermino(admin);
//                Estructuras.usuarios.agregarTermino(user);
                try {
                    AccesoDB a = AccesoDB.getInstance();
                    a.getConexion();
              
                   // PacienteRepositorio pacRep = new PacienteRepositorio();
                    //MedicoRepositorio medRep = new MedicoRepositorio();
                    //LoginRepositorio logRep = new LoginRepositorio();
//                    pacientes = pacient.readAlltoArbol();
//                    pacientes1 = pacient.readAlltoJumpList();
                   // listMed = medic.readAlltoLista();
                    //Generador.citasRandom(700);
                } catch (NullPointerException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Error de conexion a base de datos");
                    alert.showAndWait();

                } catch (Exception e) {
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Error de conexion a base de datos");
                    alert.showAndWait();
                    e.printStackTrace();

                    return false;
                }
                return true;

            }
        };

        task.setOnRunning((e) -> {
            stage.show();
            loading.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            loading.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });
        });
        task.setOnSucceeded((e) -> {
            stage.hide();
            stage.setScene(login);
            stage.show();
            root.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });
            // process return value again in JavaFX thread
        });
        task.setOnFailed((e) -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Error de conexion a base de datos");
            alert.showAndWait();
            stage.hide();
            stage.setScene(login);
            stage.show();
            root.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });
            // eventual error handling by catching exceptions from task.get()
        });
        new Thread(task).start();

        // aController.init(pacientes, medicos);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        AccesoDB acceso = new AccesoDB();
//        Connection con = acceso.getConexion();
//        PreparedStatement ps;
//        ResultSet res;
//        
//        ps=con.prepareStatement("SELECT * FROM paciente");
//        res= ps.executeQuery();
//        while(res.next()){
//            System.out.println(res.getString("nombres"));
//        }
//        
//        
        //      ListaSimple<Medico> medicos = new ListaSimple<>();
//
        //ArbolAVL<Paciente> pacientes= new ArbolAVL<>();
//        for (int i = 0; i < 100; i++) {
//            Integer aux = i;
//            String p = "oli"+aux.toString();
//             Paciente oli = new Paciente(aux.toString(),"aia","aea");
//             pacientes.insertar(oli);
//        }
//       Paciente oli = new Paciente("321","aia","aea");
//       Paciente oli1 = new Paciente("80","a2a","a2e");
//       Paciente oli2 = new Paciente("521","a3a","a3e");
////       pacientes.insertar(oli1);
////       pacientes.insertar(oli2);
////       pacientes.insertar(oli);
//      // Paciente p=pacientes.buscar(oli1);
//       //Cita manuelito =new Cita(crrno, Richard, medi,"124", urología);
//       // System.out.println(manuelito.getPaciente().getApellidos());
//      //  System.out.println(p.getPaciente());
//
//        ColaPrioridad cola = new ColaPrioridad();
//        cola.encolar(23,1);
//        cola.encolar(54,1);
//        cola.encolar(15,4);
//        cola.encolar(25,2);
//        cola.encolar(12,3);
//        cola.encolar(10,4);
//        cola.encolar(421,1);
//        cola.encolar(57,7);
//        cola.encolar(14,2);
//        System.out.println(cola.mostrarElementos());
//
//        ListaCircularOrdenada<Integer> lista = new ListaCircularOrdenada();
//        lista.insertar(21);
//        lista.insertar(2);
//        lista.insertar(1);
//        lista.insertar(4);
//        lista.insertar(8);
//        System.out.println(lista.Buscar(4));
//        System.out.println(lista);
//
//        ColaPrioridad<Cita> citas = new ColaPrioridad<>();
//        LocalDate date = LocalDate.of(2018,11,14);
//        LocalDate date1 = LocalDate.of(2018,11,15);
//        Horario hora = new Horario(date,8,12);
//        Horario hora1= new Horario(date,12,16);
//        Horario hora2 = new Horario(date1,8,12);
//        Horario hora3 = new Horario(date1,16,20);
//        Cita cita = new Cita("1",hora,"12321",date);
//        Cita cita1 = new Cita("2",hora1,"12321",date);
//        Cita cita2 = new Cita("3",hora2,"12321",date1);
//        Cita cita3 = new Cita("4",hora,"12321",date);
//        Cita cita4 = new Cita("5",hora2,"12321",date1);
//        Cita cita5 = new Cita("6",hora,"12321",date);
//        Cita cita8 = new Cita("9",hora,"12321",date);
//        Cita cita6 = new Cita("7",hora2,"12321",date1);
//        Cita cita7 = new Cita("8",hora3,"12321",date);
//        Cita cita9 = new Cita("10",hora,"12321",date);
//        Cita cita10 = new Cita("1",hora,"12321",date);
//        Cita cita11 = new Cita("2",hora,"12321",date);
//        Cita cita12 = new Cita("3",hora,"12321",date);
//        Cita cita13 = new Cita("4",hora,"12321",date);
//        citas.encolarCita(cita1);
//        citas.encolarCita(cita9);
//        citas.encolarCita(cita7);
//        citas.encolarCita(cita10);
//        citas.encolarCita(cita11);
//
//        Paciente paci = new Paciente("123","Martin","Garcia Gamboa",23,31,LocalDate.of(1999,12,2));
//        pacientes.insertar(paci);
//        Medico medico = new Medico("123","Manuel","Fajardo Astete","Pediatria");
//        medico.añadirHorario(5,8,12);
//        medico.añadirHorario(1,8,12);
//        medico.añadirHorario(6,16,20);
//        listMed.agregarTermino(medico);
//        citas.encolarCita(cita1);
//        citas.encolarCita(cita2);
//        citas.encolarCita(cita3);
//        citas.encolarCita(cita4);
//        citas.encolarCita(cita5);
//        citas.encolarCita(cita6);
//        citas.encolarCita(cita7);
//        citas.encolarCita(cita5);
//        citas.encolarCita(cita6);
//        citas.encolarCita(cita);
//        citas.encolarCita(cita);
//        citas.encolarCita(cita);
//        citas.encolarCita(cita);
//        System.out.println(date.compareTo(cita.getFecha()));
//        System.out.println(citas.mostrarElementos());
        launch(args);
    }

}
