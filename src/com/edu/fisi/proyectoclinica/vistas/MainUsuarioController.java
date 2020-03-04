package com.edu.fisi.proyectoclinica.vistas;

import com.edu.fisi.proyectoclinica.dao.databaseDAO.EspecialidadesDAODB;
import com.edu.fisi.proyectoclinica.dao.databaseDAO.CitaDAODB;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.edu.fisi.proyectoclinica.Estructuras.*;
import com.edu.fisi.proyectoclinica.dao.EspecialidadesDAO;
import com.edu.fisi.proyectoclinica.dao.HorarioDAO;
import com.edu.fisi.proyectoclinica.dao.MedicoDAO;
import com.edu.fisi.proyectoclinica.dao.PacienteDAO;
import com.edu.fisi.proyectoclinica.dao.SedeDAO;
import com.edu.fisi.proyectoclinica.dao.TO.CitaTO;
import com.edu.fisi.proyectoclinica.dao.TO.PacienteTO;
import com.edu.fisi.proyectoclinica.dao.TO.SedeTO;
import com.edu.fisi.proyectoclinica.dao.factoriaDAO.FactoriaDAO;
import com.edu.fisi.proyectoclinica.entidades.Cita;
import com.edu.fisi.proyectoclinica.entidades.Especialidad;
import com.edu.fisi.proyectoclinica.entidades.Horario;
import com.edu.fisi.proyectoclinica.entidades.Medico;
import com.edu.fisi.proyectoclinica.entidades.Paciente;
import com.edu.fisi.proyectoclinica.entidades.Sede;
import com.edu.fisi.proyectoclinica.vistas.factory.FactoryVentanas;
import com.edu.fisi.proyectoclinica.vistas.factory.Ventana;
import com.edu.fisi.proyectoclinica.vistas.factory.tipoVentana;
import java.sql.Date;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainUsuarioController extends Ventana implements Initializable {

    private FactoriaDAO factory = FactoriaDAO.getDAOFactoria(FactoriaDAO.tipoDAO.database);
    private HorarioDAO repHoraio = factory.getHorarioDAO();
    private List<Horario> horario = new ArrayList<>();
    private PacienteDAO repPaciente = factory.getPacienteDAO();
    private MedicoDAO repMedico = factory.getMedicoDAO();
    //TODO cambiar esto
    private CitaDAODB repCita = (CitaDAODB) factory.getCitaDAO();
    private SedeDAO repSedes = factory.getSedeDAO();
    private EspecialidadesDAO repEspecialidades = new EspecialidadesDAODB();
    // Medico med = null;
    /*   private ArbolAVL<Paciente> pacientes = new ArbolAVL<Paciente>();
    private ListaSimple<Medico> listMed = new ListaSimple<Medico>();
    private ColaPrioridad<Cita> colaCita = new ColaPrioridad<>();*/

    @FXML
    private Button btnCrearCitaEspecialidad;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField txtDniCita;

    @FXML
    private JFXComboBox<Horario> choiceHorarios = new JFXComboBox<>();

    @FXML
    private JFXComboBox<String> choiceEspecialidad = new JFXComboBox<String>();

    @FXML
    private Button btnCrearCitaMedico;

    @FXML
    private Button btnBuscarMed;

    @FXML
    private TextArea txtMostrarHorario;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private Button btnCrearHistoria;

    @FXML
    private TextField txtCelular;

    @FXML
    private JFXComboBox<Character> comboGenero;

    @FXML
    private JFXComboBox<String> comboTipoSangre;

    @FXML
    private Button btnBuscarPaciente;

    @FXML
    private JFXComboBox<String> choiceEspecialidadesCita = new JFXComboBox<>();

    @FXML
    private TextField txtBuscarDNI;
    @FXML
    private TextField txtBuscarCitaDNI;

    @FXML
    private TextField txtBuscarApellido;

    @FXML
    private TextField txtBuscarNombre;

    @FXML
    private Button btnMostrarHistoria;

    @FXML
    private TextArea txtMostrar;

    @FXML
    private TextArea txtMostrarAtencion;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtDNI;

    @FXML
    private TextField txtCitaNombre;

    @FXML
    private TextField txtCitaApellido;

    @FXML
    private DatePicker fechaNac;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtTalla;

    @FXML
    private Button btnCerrar;

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, String> columDNI1;

    @FXML
    private TableColumn<Cita, String> columApellidos1;

    @FXML
    private TableColumn<Cita, String> columMedico;

    @FXML
    private TableColumn<Cita, String> columEspecialidad;

    @FXML
    private TableColumn<Cita, String> columFecha;

    @FXML
    private TableColumn<Cita, String> columHora;    

    @FXML
    private TableColumn<Cita, String> columSede;

    @FXML
    private TableView<Cita> tablaCitas1;

    @FXML
    private TableColumn<Cita, String> columDNI11;

    @FXML
    private TableColumn<Cita, String> columApellidos11;

    @FXML
    private TableColumn<Cita, String> columMedico1;

    @FXML
    private TableColumn<Cita, String> columEspecialidad1;

    @FXML
    private TableColumn<Cita, String> columFecha1;

    @FXML
    private TableColumn<Cita, String> columHora1;

    @FXML
    private TableColumn<Cita, String> columNumCita;

    @FXML
    private TableColumn<Cita, String> columNumCita1;

    @FXML
    private TableColumn<?, ?> columDNI;

    @FXML
    private TableColumn<?, ?> columApellidos;

    @FXML
    private TableView<PacienteTO> tablePacientes;
    @FXML
    private TableColumn<?, ?> columNombres;

    @FXML
    private TableColumn<PacienteTO, String> columFechaN;

    @FXML
    private TableColumn<PacienteTO, String> columTelef;

    @FXML
    private TableColumn<PacienteTO, String> columCel;

    @FXML
    private TableColumn<?, ?> columTipoSangre;

    @FXML
    private TableColumn<?, ?> columGenero;

    @FXML
    private TextField txtAtencionCita;

    @FXML
    private TextField txtMostarAtencion;

    @FXML
    private DatePicker fechaCitas;
    @FXML
    private JFXComboBox<Sede> choiceSedes = new JFXComboBox<Sede>();

    double xOffset = 0;
    double yOffset = 0;
    @FXML
    private Parent root;

    public Parent getRoot() {
        return root;
    }

    public double getxOffset() {
        return xOffset;
    }

    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }

    public void BtnCerrar(MouseEvent evento) {
        FactoryVentanas.createVentana(tipoVentana.login);
        ((Node) (evento.getSource())).getScene().getWindow().hide();
    }

    public void BtnSacarCitaEspecialidad(ActionEvent event) throws SQLException {
        int idSedes = 0;
        try {
            Integer.parseInt(txtDniCita.getText());
            String p = choiceSedes.getValue().getNombre();
            Paciente paciente = new Paciente(repPaciente.read(txtDniCita.getText()));

            SedeTO a = repSedes.readN(p);
            Cita cita = null;
            if (repPaciente.read(txtDniCita.getText()) != null) {
//                System.out.println("hi");
                try {
                    if (fechaCitas.getValue() == null) {
                        cita = new Cita(repCita.sacarCita(
                                choiceEspecialidad.getValue(),
                                txtDniCita.getText(),
                                a.getIdSede()
                        ));
                    } else {
                        if (fechaCitas.getValue().isAfter(LocalDate.now())) {
                            cita = new Cita(repCita.sacarCitaF(
                                    choiceEspecialidad.getValue(),
                                    txtDniCita.getText(),
                                    a.getIdSede(), fechaCitas.getValue()
                            ));
                        } else if (fechaCitas.getValue().isBefore(LocalDate.now())) {
                            throw new Exception();
                        }
                    }
                } catch (NullPointerException j) {

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Fecha antigua");
                    alert.showAndWait();
                    choiceEspecialidad.setItems(FXCollections.observableList(Especialidad.getEspecialidades()));

                }
                try {
                    if (cita == null) {
                        throw new Exception();
                    }
                    if (cita.getHora() != null) {
                        tablaCitas.getItems().add(cita);
//                        tableCitas.add(cita);
//                        tablaCitas.setItems(tableCitas);
//                    Cita.AumentarNumCita();
//                    cita.setNumero(Cita.getNumCita());
                        String stringCita = "Num Cita: " + cita.getIdCita() + "\nPaciente: " + paciente.getApellidos() + " " + paciente.getNombres()
                                + "\nEspecialidad: " + cita.getMedico().getEspecialidad() + "\nMedico: Dr. " + cita.getMedico().getApellidos() + " " + cita.getMedico().getNombres()
                                + "\n" + cita.getFecha().toString() + "\n" + cita.getHora().toString() + "\n" + cita.getHora();
                        txtMostrarHorario.setText(stringCita);
                    }
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("No hay horarios Disponibles");
                    alert.showAndWait();
                    choiceEspecialidad.setItems(FXCollections.observableList(Especialidad.getEspecialidades()));

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Paciente no encontrado");
                alert.showAndWait();
                txtBuscarDNI.setText("");
            }
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Solo numeros al dni");
            alert.showAndWait();
            txtBuscarDNI.setText("");
        } catch (NullPointerException n) {

        } catch (Exception e) {

            e.printStackTrace();
        }
//        try {
//            String dni = txtDniCita.getText();
//            Integer.parseInt(dni);
//            Paciente nuevo = new Paciente(dni);
//            nuevo = pacientes.buscar(nuevo);
//            String especialidad = choiceEspecialidad.getValue();
//            LocalDate fecha = LocalDate.now();
//            MedicoHorario medicoHorario = MedicoHorario.buscarDiaMasProximo(fecha.getDayOfWeek().getValue(), especialidad);
//            if(nuevo!=null){
//                if (medicoHorario != null) {
//                Medico medico = medicoHorario.getMedico();
//                Horario horario = medicoHorario.getHorario();
//                if (medico != null && horario != null) {
//                    DayOfWeek day = DayOfWeek.of(horario.getDia());
//                    fecha = fecha.with(TemporalAdjusters.next(day));
//                    Cita cita = new Cita(nuevo, horario, medico, fecha);
//                    cita = colaCita.encolarCita(cita);
//                    tablaCitas.setItems(tableCitas);
//                    tableCitas.add(cita);
//                    Cita.AumentarNumCita();
//                    cita.setNumero(Cita.getNumCita());
//                    String stringCita = "Num Cita: " + cita.getNumero() + "\nPaciente: " + nuevo.getApellidos() + " " + nuevo.getNombres()
//                            + "\nEspecialidad: " + cita.getMedico().getEspecialidad() + "\nMedico: Dr. " + cita.getMedico().getApellidos() + " " + cita.getMedico().getNombres()
//                            + "\n" + cita.getFecha().toString() + "\n" + cita.getHora().toString() + "\n" + cita.getHoraExacta();
//                    txtMostrarHorario.setText(stringCita);
//                }
//            } else {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle(null);
//                alert.setHeaderText(null);
//                alert.setContentText("No hay horarios Disponibles");
//                alert.showAndWait();
//                txtBuscarDNI.setText("");
//            }
//            }else{
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle(null);
//                alert.setHeaderText(null);
//                alert.setContentText("Base de datos de pacientes vacio");
//                alert.showAndWait();
//                txtBuscarDNI.setText("");
//            }
//
//        } catch (NumberFormatException numberFormatException) {
//
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle(null);
//            alert.setHeaderText(null);
//            alert.setContentText("Solo numeros al dni");
//            alert.showAndWait();
//            txtBuscarDNI.setText("");
//        } catch (Exception e) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle(null);
//            alert.setHeaderText(null);
//            alert.setContentText("Paciente no encontrado");
//            alert.showAndWait();
//            txtBuscarDNI.setText("");
//            e.printStackTrace();
//        }
    }

    public void btnBuscarCitaDNI(ActionEvent event) throws SQLException {
        try {
//            Paciente paci = new Paciente(repPaciente.read(txtBuscarDNI.getText()));
//            txtBuscarDNI.setText("");

            ArrayList<Cita> cita = new ArrayList<>();
            repCita.readDNI(txtBuscarCitaDNI.getText()).forEach(action -> cita.add(new Cita(action)));
            tablaCitas.setItems(FXCollections.observableArrayList(cita));
            txtBuscarCitaDNI.setText("");
            
//            Integer.parseInt(dni);
//            Paciente paci = new Paciente(dni);
//            Paciente pac2 = new Paciente(dni);
//            Long timeI = System.nanoTime();
//            pac2 = pacientes1.buscar(pac2);
//            Long timeF = System.nanoTime();
//            Long idk = (timeF - timeI);
//
//            Long timeIn = System.nanoTime();
//            paci = pacientes.buscar(paci);
//            Long timeFi = System.nanoTime();
//            Long idk1 = (timeFi - timeIn);
//
//
//            double seg1 = idk / 1000000000.0;
//            double seg2 = idk1 / 1000000000.0;

            //pacientes.insertar(paci);
            
            
            // txtTiempoArbol.setText(seg2 + " seg");
            // txtTiempoListaSaltos.setText(seg1 + " seg");
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Solo numeros al dni");
            alert.showAndWait();
            txtBuscarDNI.setText("");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No existe en la base de datos");
            alert.showAndWait();
            txtBuscarDNI.setText("");
            e.printStackTrace();
        }
////////        try {
////////            Integer.parseInt(txtBuscarCitaDNI.getText());
////////            if (repPaciente.read(txtBuscarCitaDNI.getText()) != null) {
////////                repCita.ps = repCita.conexion.prepareStatement("SELECT * FROM cita  WHERE dni LIKE ?");
////////                repCita.ps.setString(1, txtBuscarCitaDNI.getText());
////////                repCita.rs = repCita.ps.executeQuery();
////////                tablaCitas.getItems().clear();
////////                ArrayList arr = new ArrayList();
////////                while (repCita.rs.next()) {
////////
////////                    arr.add(new Cita(
////////                            repCita.rs.getInt("idcita"),
////////                            new Medico(repMedico.read(repCita.rs.getString("idmedico"))),
////////                            new Paciente(repPaciente.read(repCita.rs.getString("dni"))),
////////                            repCita.rs.getDate("fecha").toLocalDate(),
////////                            repCita.rs.getTime("hora").toLocalTime(),
////////                            new Sede(repSedes.read(repCita.rs.getInt("idsede")))
////////                    ));
////////
////////                }
////////                if (arr.isEmpty()) {
////////                    Alert alert = new Alert(Alert.AlertType.WARNING);
////////                    alert.setTitle("ERROR");
////////                    alert.setHeaderText(null);
////////                    alert.setContentText("Cita(s) no encontrada(s)");
////////                    alert.showAndWait();
////////                } else {
////////                    tablaCitas.setItems(FXCollections.observableArrayList(arr));
////////                }
////////            }
////////        } catch (NumberFormatException e) {
////////            Stage stage = (Stage) btnBuscarMed.getScene().getWindow();
////////            Alert alert = new Alert(Alert.AlertType.INFORMATION);
////////            alert.setTitle(null);
////////            alert.setHeaderText(null);
////////            alert.setContentText("Solo numeros al DNI");
////////            alert.showAndWait();
////////            txtBuscarDNI.setText("");
////////        } catch (Exception e) {
////////            e.printStackTrace();
////////        }
//        try {
//            int i = Integer.parseInt(txtBuscarCitaDNI.getText());
//            Paciente paci = pacientes.buscar(new Paciente(txtBuscarCitaDNI.getText()));
//            if (paci != null) {
//                ObservableList<Cita> citas = colaCita.buscarLista(new Cita(new Paciente(txtBuscarCitaDNI.getText()), null, null, null), Cita.compararDNI);
//                if (citas.size() > 0) {
//                    tablaCitas.setItems(citas);
//                } else {
//
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle(null);
//                    alert.setHeaderText(null);
//                    alert.setContentText("El paciente " + paci.getApellidos() + " " + paci.getNombres() + " no tiene citas registradas");
//                    alert.showAndWait();
//                    txtBuscarDNI.setText("");
//
//                }
//            } else {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle(null);
//                alert.setHeaderText(null);
//                alert.setContentText("No se encontro paciente");
//                alert.showAndWait();
//                txtBuscarDNI.setText("");
//            }
//        } catch (NumberFormatException e) {
//            Stage stage = (Stage) btnBuscarMed.getScene().getWindow();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle(null);
//            alert.setHeaderText(null);
//            alert.setContentText("Solo numeros al DNI");
//            alert.showAndWait();
//            txtBuscarDNI.setText("");
//        }
    }

    public void btnBuscarCitaEspecialidad(ActionEvent event) {
        try {
            ArrayList<Cita> cita = new ArrayList<>();
            System.out.println(choiceEspecialidadesCita.getValue());
            repCita.readEsp(choiceEspecialidadesCita.getValue()).forEach(action -> cita.add(new Cita(action)));
            tablaCitas.setItems(FXCollections.observableArrayList(cita));
            choiceEspecialidadesCita.setValue("");
//////
//////            if (!choiceEspecialidad.getValue().isEmpty()) {
//////                repCita.ps = repCita.conexion.prepareStatement("SELECT idcita,idmedico,dni,fecha,hora,idsede"
//////                        + " FROM cita as c JOIN medico as m"
//////                        + " ON m.idmedico = c.idmedico  WHERE especialidad LIKE ?");
//////                repCita.ps.setString(1, choiceEspecialidad.getValue());
//////                repCita.rs = repCita.ps.executeQuery();
//////                tablaCitas.getItems().clear();
//////                ArrayList arr = new ArrayList();
//////                while (repCita.rs.next()) {
//////
//////                    arr.add(new Cita(
//////                            repCita.rs.getInt("idcita"),
//////                            new Medico(repMedico.read(repCita.rs.getString("idmedico"))),
//////                            new Paciente(repPaciente.read(repCita.rs.getString("dni"))),
//////                            repCita.rs.getDate("fecha").toLocalDate(),
//////                            repCita.rs.getTime("hora").toLocalTime(),
//////                            new Sede(repSedes.read(repCita.rs.getInt("idsede")))
//////                    ));
//////
//////                }
//////                if (arr.isEmpty()) {
//////                    Alert alert = new Alert(Alert.AlertType.WARNING);
//////                    alert.setTitle("ERROR");
//////                    alert.setHeaderText(null);
//////                    alert.setContentText("Cita(s) no encontrada(s)");
//////                    alert.showAndWait();
//////                } else {
//////                    tablaCitas.setItems(FXCollections.observableArrayList(arr));
//////                }
//////            } else {
//////                Alert alert = new Alert(Alert.AlertType.WARNING);
//////                alert.setTitle("ERROR");
//////                alert.setHeaderText(null);
//////                alert.setContentText("Elija una especialidad");
//////                alert.showAndWait();
//////            }
        } catch (RuntimeException ev) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No se encontro citas de la especialidad");
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Error en la busqueda");
            alert.showAndWait();
            e.printStackTrace();
        }
//        try{
//        ObservableList<Cita> citas = colaCita.buscarLista(new Cita(null, null, new Medico(null, null, choiceEspecialidadesCita.getValue()), null), Cita.compararEspecialidad);
//        if (citas.size() > 0) {
//            tablaCitas.setItems(citas);
//        } else {
//
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle(null);
//            alert.setHeaderText(null);
//            alert.setContentText("No se encontro citas de la especialidad");
//            alert.showAndWait();
//            txtBuscarDNI.setText("");
//        }}
//        catch(RuntimeException ev){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle(null);
//            alert.setHeaderText(null);
//            alert.setContentText("No se encontro citas de la especialidad");
//            alert.showAndWait();
//
//        }
    }

    public void btnMostrarCitas(ActionEvent event) {
        try {
            ArrayList<Cita> a = new ArrayList<>();
            repCita.readAll().forEach(action -> a.add(new Cita(action)));
            tablaCitas.setItems(FXCollections.observableArrayList(a));

        } catch (RuntimeException ev) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No hay citas");
            alert.showAndWait();

        }
    }

    public void BtnBuscarMed(ActionEvent event) {

        boolean c = true;
        try {
            try {
                if (txtCitaNombre.getText().matches(".*\\d+.*")) {
                    throw new Exception();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Ingrese el nombre del medico");
                alert.showAndWait();
                txtCitaNombre.setText("");
                c = false;
            }
            try {
                if (txtCitaApellido.getText().matches(".*\\d+.*")) {
                    throw new Exception();
                }
            } catch (Exception e) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Ingrese el apellido del medico");
                alert.showAndWait();
                txtCitaApellido.setText("");
                c = false;
            }
//
            if (c) {

                Medico med = new Medico(repMedico.readMedico(txtCitaApellido.getText(), txtCitaNombre.getText()));
                choiceEspecialidad.setValue(med.getEspecialidad());
                if (med != null) {
//                    horario = med.getHorario();
                    repHoraio.read(med.getCodigo()).forEach(action -> horario.add(new Horario(action)));
                    choiceHorarios.setItems(FXCollections.observableArrayList((List) horario));
                } else {
                    List<Horario> vacio = new ArrayList<>();
                    choiceHorarios.setItems(FXCollections.observableArrayList((List) vacio));
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Medico encontrado");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Medico no encontrado");
            alert.showAndWait();

        }
    }

    public void BtnSacarCitaMedico(ActionEvent event) {

        try {
            String dni = txtDniCita.getText();
            Integer.parseInt(dni);
            Paciente nuevo = new Paciente(repPaciente.read(txtDniCita.getText()));
            Medico med = new Medico(repMedico.readMedico(txtCitaApellido.getText(), txtCitaNombre.getText()));
//            nuevo = pacientes.buscar(nuevo);
//            ArrayList<Horario> a = new ArrayList<>();
//            repHoraio.read(med.getCodigo()).forEach(action-> a.add(new Horario(action)));          

            Horario horari = choiceHorarios.getValue();
            LocalDate fecha = fechaCitas.getValue();
            LocalDate hoy = LocalDate.now();
            int b = horari.getDia();
            int a = fecha.getDayOfWeek().getValue();
            if ((fecha.isAfter(hoy)) && (a == b)) {
                if (nuevo != null) {
                    if (med != null) {
                        if (horari != null) {
                            try {

                                Cita cita = null;
                                cita = new Cita(repCita.sacarC(horari.getHoraInicio(), horari.getHoraFin(),
                                        med.getCodigo(), horari.getIdsede(), fecha, dni));
                                if (cita == null) {
                                    throw new Exception();
                                }
                                if (cita.getHora() != null) {
                                    tablaCitas.getItems().add(cita);
                                    //   tablaCitas.setItems(tableCitas);
//                    Cita.AumentarNumCita();
//                    cita.setNumero(Cita.getNumCita());
                                    String stringCita = "Num Cita: " + cita.getIdCita() + "\nPaciente: " + nuevo.getApellidos() + " " + nuevo.getNombres()
                                            + "\nEspecialidad: " + cita.getMedico().getEspecialidad() + "\nMedico: Dr. " + cita.getMedico().getApellidos() + " " + cita.getMedico().getNombres()
                                            + "\n" + cita.getFecha().toString() + "\n" + cita.getHora().toString() + "\n" + cita.getHora();
                                    txtMostrarHorario.setText(stringCita);
                                }
                            } catch (Exception e) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle(null);
                                alert.setHeaderText(null);
                                alert.setContentText("No hay horarios Disponibles");
                                alert.showAndWait();
                                choiceEspecialidad.setItems(FXCollections.observableList(Especialidad.getEspecialidades()));

                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(null);
                            alert.setHeaderText(null);
                            alert.setContentText("No hay horarios Disponibles");
                            alert.showAndWait();
                            choiceEspecialidad.setItems(FXCollections.observableList(Especialidad.getEspecialidades()));
                            txtBuscarDNI.setText("");
                        }
                    } else {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Medico no encontrado");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Paciente no encontrado");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Error en la fecha");
                alert.showAndWait();
            }
        } catch (NumberFormatException numberFormatException) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Solo numeros al dni");
            alert.showAndWait();
            txtBuscarDNI.setText("");
        } catch (NullPointerException j) {

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Error");
            alert.showAndWait();
        }
    }

    public void BtnAñadirPaciente(ActionEvent event) {

        boolean c = true;

        try {
            try {
                if (txtNombres.getText().matches(".*\\d+.*")) {
                    throw new Exception();
                }
            } catch (Exception e) {
                txtNombres.setText("");
                c = false;
            }
            try {
                if (txtApellidos.getText().matches(".*\\d+.*")) {
                    throw new Exception();
                }
            } catch (Exception e) {
                txtApellidos.setText("");
                c = false;
            }

            try {

                Integer.parseInt(txtDNI.getText());

            } catch (NumberFormatException numberFormatException) {
                //alert
                txtDNI.setText("");
                c = false;
            }
            try {

                Integer.parseInt(txtTelefono.getText());

            } catch (NumberFormatException numberFormatException) {
                //alert
                txtTelefono.setText("");
                c = false;
            }
            try {

                Integer.parseInt(txtCelular.getText());

            } catch (NumberFormatException numberFormatException) {
                //alert
                txtCelular.setText("");
                c = false;
            }
            if (c) {
                Paciente paciente = new Paciente(
                        txtDNI.getText(),
                        txtNombres.getText(),
                        txtApellidos.getText(),
                        txtDireccion.getText(),
                        fechaNac.getValue(),
                        txtTelefono.getText(),
                        txtCelular.getText(),
                        comboGenero.getValue(),
                        comboTipoSangre.getValue()
                );
                repPaciente.create(paciente.toPacienteTO());
                tablePacientes.getItems().add(paciente.toPacienteTO());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Paciente añadido");
                alert.showAndWait();
                txtDNI.setText("");
                txtNombres.setText("");
                txtApellidos.setText("");
                txtDireccion.setText("");
                fechaNac.getEditor().setText("");
                txtTelefono.setText("");
                txtCelular.setText("");
                comboGenero.getSelectionModel().selectFirst();
                comboTipoSangre.getSelectionModel().selectFirst();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Ingrese los datos correctamente");
            alert.showAndWait();
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

//                Paciente paciente = new Paciente(txtDNI.getText(), txtNombres.getText(), txtApellidos.getText(), Float.parseFloat(txtPeso.getText()), Float.parseFloat(txtTalla.getText()), fechaNac.getValue());
//                pacientes.insertar(paciente);
//                tablePacientes.add(paciente);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle(null);
//                alert.setHeaderText(null);
//                alert.setContentText("Paciente añadido");
//                alert.showAndWait();
//                txtNombres.setText("");
//                txtApellidos.setText("");
//                txtDNI.setText("");
//                txtPeso.setText("");
//                txtTalla.setText("");
//            }
//        } catch (Exception e) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle(null);
//                alert.setHeaderText(null);
//                alert.setContentText("Error al añadir paciente");
//                alert.showAndWait();
//            e.printStackTrace();
//        }
    }

    public void btnBuscarPaciente(ActionEvent event) {
        try {
            Paciente paci = new Paciente(repPaciente.read(txtBuscarDNI.getText()));
            txtBuscarDNI.setText("");
            
//            Integer.parseInt(dni);
//            Paciente paci = new Paciente(dni);
//            Paciente pac2 = new Paciente(dni);
//            Long timeI = System.nanoTime();
//            pac2 = pacientes1.buscar(pac2);
//            Long timeF = System.nanoTime();
//            Long idk = (timeF - timeI);
//
//            Long timeIn = System.nanoTime();
//            paci = pacientes.buscar(paci);
//            Long timeFi = System.nanoTime();
//            Long idk1 = (timeFi - timeIn);
//
//
//            double seg1 = idk / 1000000000.0;
//            double seg2 = idk1 / 1000000000.0;

            //pacientes.insertar(paci);
            
            if (paci != null) {
                //txtMostrar.setText(paci.toString() + "\n\n tiempo lista salto: " + idk + "\n\n tiempo arbol: " + idk1);
                ObservableList<PacienteTO> result = FXCollections.observableArrayList();
                result.add(paci.toPacienteTO());
                tablePacientes.setItems(result);
            }
            // txtTiempoArbol.setText(seg2 + " seg");
            // txtTiempoListaSaltos.setText(seg1 + " seg");
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Solo numeros al dni");
            alert.showAndWait();
            txtBuscarDNI.setText("");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No existe en la base de datos");
            alert.showAndWait();
            txtBuscarDNI.setText("");
            e.printStackTrace();
        }
    }

    public void btnEliminarCita(ActionEvent event) {
        try {
            repCita.delete(Integer.valueOf(txtAtencionCita.getText()));
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        /*
        try {
            String nroCita = txtAtencionCita.getText();
            Integer.parseInt(txtAtencionCita.getText());
            Cita cita = new Cita(nroCita);
            //Cita cita;
            cita = colaCita.eliminar(cita, Cita.compararNumero);
            if(cita!=null){
            String stringCita = "Num Cita: " + cita.getNumero() + "\nPaciente: " + cita.getPaciente().getApellidos() + " " + cita.getPaciente().getNombres()
                    + "\nEspecialidad: " + cita.getMedico().getEspecialidad() + "\nMedico: Dr. " + cita.getMedico().getApellidos() + " " + cita.getMedico().getNombres()
                    + "\n" + cita.getFecha().toString() + "\n" + cita.getHora().toString() + "\n" + cita.getHoraExacta();

            txtMostrarAtencion.setText(stringCita);
            tableCitas.remove(cita);
            tablaCitas.setItems(tableCitas);
            }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No se encontro la cita");
            alert.showAndWait();
            txtAtencionCita.setText("");
            }
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Solo numeros al dni");
            alert.showAndWait();
            txtAtencionCita.setText("");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No se encontro la cita");
            alert.showAndWait();
            txtAtencionCita.setText("");
            e.printStackTrace();
        }*/
    }

    public void btnAtender(ActionEvent event) {
        /*try {
            String nroCita = txtAtencionCita.getText();
            Integer.parseInt(txtAtencionCita.getText());
            Cita cita = new Cita(nroCita);
            cita = colaCita.eliminar(cita, Cita.compararNumero);
            if(cita!=null){
            atencion.insertar(cita);

            String stringCita = "Num Cita: " + cita.getNumero() + "\nPaciente: " + cita.getPaciente().getApellidos() + " " + cita.getPaciente().getNombres()
                    + "\nEspecialidad: " + cita.getMedico().getEspecialidad() + "\nMedico: Dr. " + cita.getMedico().getApellidos() + " " + cita.getMedico().getNombres()
                    + "\n" + cita.getFecha().toString() + "\n" + cita.getHora().toString() + "\n" + cita.getHoraExacta();

            txtMostrarAtencion.setText(stringCita);

            tableAtencion.add(cita);
            tableCitas.remove(cita);
            tablaCitas.setItems(tableCitas);

            tablaCitas1.setItems(tableAtencion);
            }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No se encontro la cita");
            alert.showAndWait();
            txtAtencionCita.setText("");
            }

        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Solo numeros al dni");
            alert.showAndWait();
            txtAtencionCita.setText("");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No se encontro la cita");
            alert.showAndWait();
            txtAtencionCita.setText("");
            e.printStackTrace();
        }
         */
    }

    public void btnMostrarTodo(ActionEvent event) {
        try {
            tablePacientes.setItems(FXCollections.observableArrayList(repPaciente.readAll()));

        } catch (RuntimeException ev) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No hay pacientes");
            alert.showAndWait();

        }
    }

    @FXML
    public void cerrar(MouseEvent event) {
        System.exit(0);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println(listMed.mostrar());
        Especialidad.setEspecialidades(repEspecialidades.readAll());
        Especialidad.setPrecio(repEspecialidades.readAllP());
//         choiceEspecialidad.setItems(FXCollections.observableArrayList(repEspecialidades.readAll()));
        choiceEspecialidad.setItems(FXCollections.observableList(Especialidad.getEspecialidades()));
        choiceEspecialidadesCita.setItems(FXCollections.observableList(Especialidad.getEspecialidades()));
        choiceHorarios.setItems(FXCollections.observableArrayList((List) horario));
        comboGenero.getItems().addAll('N', 'M', 'F');
        comboGenero.getSelectionModel().selectFirst();
        comboTipoSangre.getItems().addAll("No especificado", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "B-");
        comboTipoSangre.getSelectionModel().selectFirst();
        //limitar los digitos del dni
        txtDNI.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (txtDNI.getText().length() == 8) {
                    event.consume();
                }
            }
        });
        txtBuscarDNI.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (txtBuscarDNI.getText().length() == 8) {
                    event.consume();
                }
            }
        });
        txtDniCita.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (txtDniCita.getText().length() == 8) {
                    event.consume();
                }
            }
        });
        txtCelular.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (txtCelular.getText().length() == 9) {
                    event.consume();
                }
            }
        });
        txtTelefono.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (txtTelefono.getText().length() == 7) {
                    event.consume();
                }
            }
        });

//        txtAtencionCita.setOnKeyTyped((Event event) -> {
//            if (txtAtencionCita.getText().length() == 6) {
//                event.consume();
//            }
//        }
//        );
        columDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        columApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        columFechaN.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        columTelef.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columCel.setCellValueFactory(new PropertyValueFactory<>("celular"));
        columTipoSangre.setCellValueFactory(new PropertyValueFactory<>("tipoSangre"));
        columGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        ArrayList<Sede> sede = new ArrayList();
        repSedes.readAll().forEach(action -> sede.add(new Sede(action)));
        choiceSedes.setItems(FXCollections.observableArrayList(sede));

        tablePacientes.setItems(FXCollections.observableArrayList(repPaciente.readAll()));
        columNumCita.setCellValueFactory(new PropertyValueFactory<>("idCita"));
        columDNI1.setCellValueFactory(cellData -> {
            String data = cellData.getValue().getPaciente().getDni();
            return new ReadOnlyStringWrapper(data);
        });
        columApellidos1.setCellValueFactory(cellData -> {
            String data = cellData.getValue().getPaciente().getApellidos();
            return new ReadOnlyStringWrapper(data);
        });;
        columMedico.setCellValueFactory(cellData -> {
            String data = "Dr. " + cellData.getValue().getMedico().getApellidos();
            return new ReadOnlyStringWrapper(data);
        });
        columEspecialidad.setCellValueFactory(cellData -> {
            String data = cellData.getValue().getMedico().getEspecialidad();
            return new ReadOnlyStringWrapper(data);
        });
       
        columFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        columSede.setCellValueFactory(cellData -> {
            String data = cellData.getValue().getIdsede().getNombre();
            return new ReadOnlyStringWrapper(data);
        });
       
        ArrayList<Cita> a = new ArrayList<>();
        repCita.readAll().forEach(action -> a.add(new Cita(action)));
        tablaCitas.setItems(FXCollections.observableArrayList(a));
//
//        columDNI1.setCellValueFactory((cellData -> {
//            String data = cellData.getValue().getPaciente().getPaciente();
//            return new ReadOnlyStringWrapper(data);
//        }));
//        columApellidos1.setCellValueFactory((cellData -> {
//            String data = cellData.getValue().getPaciente().getNombres() + " " + cellData.getValue().getPaciente().getApellidos();
//            return new ReadOnlyStringWrapper(data);
//        }));
//        columMedico.setCellValueFactory((cellData -> {
//            String data = "Dr. " + cellData.getValue().getMedico().getApellidos();
//            return new ReadOnlyStringWrapper(data);
//        }));
//        columHora.setCellValueFactory((cellData -> {
//            String data = cellData.getValue().getHoraExacta();
//            return new ReadOnlyStringWrapper(data);
//        }));
//        columEspecialidad.setCellValueFactory((cellData -> {
//            String data = cellData.getValue().getMedico().getEspecialidad();
//            return new ReadOnlyStringWrapper(data);
//        }));
//        columFecha.setCellValueFactory((cellData -> {
//            String data = cellData.getValue().getFecha().toString();
//            return new ReadOnlyStringWrapper(data);
//        }));
//        columNumCita.setCellValueFactory((cellData -> {
//            String data = cellData.getValue().getNumero();
//            return new ReadOnlyStringWrapper(data);
//        }));
//
//         columDNI11.setCellValueFactory((cellData -> {
//            String data = cellData.getValue().getPaciente().getPaciente();
//            return new ReadOnlyStringWrapper(data);
//        }));
//        columApellidos11.setCellValueFactory((cellData -> {
//            String data = cellData.getValue().getPaciente().getNombres() + " " + cellData.getValue().getPaciente().getApellidos();
//            return new ReadOnlyStringWrapper(data);
//        }));
//        columMedico1.setCellValueFactory((cellData -> {
//            String data = "Dr. " + cellData.getValue().getMedico().getApellidos();
//            return new ReadOnlyStringWrapper(data);
//        }));
//        columHora1.setCellValueFactory((cellData -> {
//            String data = cellData.getValue().getHoraExacta();
//            return new ReadOnlyStringWrapper(data);
//        }));
//        columEspecialidad1.setCellValueFactory((cellData -> {
//            String data = cellData.getValue().getMedico().getEspecialidad();
//            return new ReadOnlyStringWrapper(data);
//        }));
//        columFecha1.setCellValueFactory((cellData -> {
//            String data = cellData.getValue().getFecha().toString();
//            return new ReadOnlyStringWrapper(data);
//        }));
//        columNumCita1.setCellValueFactory((cellData -> {
//            String data = cellData.getValue().getNumero();
//            return new ReadOnlyStringWrapper(data);
//        }));
//        tablaCitas1.setItems(tableAtencion);

    }
}
