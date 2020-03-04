package com.edu.fisi.proyectoclinica.vistas;

import com.edu.fisi.proyectoclinica.dao.CitaDAO;
import com.edu.fisi.proyectoclinica.dao.EspecialidadesDAO;
import com.edu.fisi.proyectoclinica.dao.PacienteDAO;
import com.edu.fisi.proyectoclinica.dao.SedeDAO;
import com.edu.fisi.proyectoclinica.dao.TO.HorarioTO;
import com.edu.fisi.proyectoclinica.dao.TO.MedicoTO;
import com.edu.fisi.proyectoclinica.dao.databaseDAO.MedicoDAODB;
import com.edu.fisi.proyectoclinica.dao.factoriaDAO.FactoriaDAO;
import com.edu.fisi.proyectoclinica.entidades.Especialidad;
import com.edu.fisi.proyectoclinica.entidades.Horario;
import com.edu.fisi.proyectoclinica.entidades.Medico;
import com.edu.fisi.proyectoclinica.entidades.Sede;
import com.edu.fisi.proyectoclinica.login.Usuario;
import com.edu.fisi.proyectoclinica.vistas.factory.FactoryVentanas;
import com.edu.fisi.proyectoclinica.vistas.factory.Ventana;
import com.edu.fisi.proyectoclinica.vistas.factory.tipoVentana;
import com.jfoenix.controls.JFXComboBox;
import com.edu.fisi.proyectoclinica.dao.HorarioDAO;
import com.edu.fisi.proyectoclinica.dao.MedicoDAO;
import com.edu.fisi.proyectoclinica.dao.TO.UsuarioTO;
import com.edu.fisi.proyectoclinica.dao.UsuarioDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

public class MainAdminController extends Ventana implements Initializable {

    // private ArbolAVL<Paciente> pacientes = new ArbolAVL<Paciente>();
    //private ListaSimple<Medico> listMed = new ListaSimple<Medico>();
    private List<Horario> horario = new ArrayList<>();
    private FactoriaDAO factoryTO = FactoriaDAO.getDAOFactoria(FactoriaDAO.tipoDAO.database);
    //TODO cambiar el acceso a bd
    private MedicoDAO medRepo =  factoryTO.getMedicoDAO();//cambiar
    private EspecialidadesDAO espRepo = factoryTO.getEspecialidadesDAO();
    private HorarioDAO horarioRepo = factoryTO.getHorarioDAO();
    private SedeDAO sedeRepo = factoryTO.getSedeDAO();
    private PacienteDAO pacRepo = factoryTO.getPacienteDAO();
    private CitaDAO citaRepo = factoryTO.getCitaDAO();
    private UsuarioDAO userRepo = factoryTO.getLoginDAO();
  @FXML
    private AnchorPane root;
    
public Parent getRoot() {
       return root.getParent();
    }
    @FXML
    private Button btnAniadir;

    @FXML
    private TextField txtApellidoMed;

    @FXML
    private TextField txtNombresMed;

    @FXML
    private JFXComboBox<String> choiceEspecialidad = new JFXComboBox<String>();

    @FXML
    private JFXComboBox<String> comboEspecialidad = new JFXComboBox<String>();

    @FXML
    private JFXComboBox<String> choiceEliminarEspecialidad = new JFXComboBox<String>();

    @FXML
    private TextField txtBuscarMed = new TextField();

    @FXML
    private TextField txtNuevaEspecialidad = new TextField();

    @FXML
    private TextField txtCodigoMed;

    @FXML
    private Button btnAniadirHorario;

    @FXML
    private TextField txtCodigoMed2;

    @FXML
    private TextArea txtMostrarHorario;

    @FXML
    private JFXComboBox<String> choiceDias = new JFXComboBox<String>();

    @FXML
    private JFXComboBox<LocalTime> choiceHoraFin = new JFXComboBox<>();

    @FXML
    private JFXComboBox<LocalTime> choiceHoraIni = new JFXComboBox<>();

    @FXML
    private JFXComboBox<Sede> choiceSede = new JFXComboBox<>();

    @FXML
    private JFXComboBox<Horario> choiceEliminar = new JFXComboBox<Horario>();

    @FXML
    private TableColumn<?, ?> columCodigo;

    @FXML
    private TableColumn<?, ?> columApellidos;

    @FXML
    private TableColumn<?, ?> columNombres;

    @FXML
    private TableColumn<MedicoTO, String> columEspecialidad;

    @FXML
    private TableColumn<MedicoTO, String> columHorarios;

    @FXML
    private TextField numPacientes;

    @FXML
    private TextField numMedicos;

    @FXML
    private TextField numCitas;

    @FXML
    private DatePicker choiceFechaNac;

    @FXML
    private ComboBox<String> choiceGenero;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtCelular;

    @FXML
    private TextField txtTelefono;

    @FXML
    private Button btnNuevaEspecialidad;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEliminarTodo;

    @FXML
    private TableView<MedicoTO> tableMedicos;

    @FXML
    private TableColumn<?, ?> columGenero;

    @FXML
    private TableColumn<?, ?> columCelular;

    @FXML
    private TableColumn<?, ?> columTelefono;

    @FXML
    private Button btnBuscarMedico;

    @FXML
    private Button btnMostrarTodo;

    @FXML
    private Button btnBuscarMedico1;

    @FXML
    private Button btnEliminarTodo1;

    @FXML
    private TextField txtAddUsuario;

    @FXML
    private PasswordField txtAddPassword;

    @FXML
    private ComboBox<String> choiceAddTipoUser;

    @FXML
    private TextField TxtDelUsuario;

    double xOffset = 0;
    double yOffset = 0;

    public void BtnMed(ActionEvent event) {
        boolean c = true;
        try {
            try {
                if (txtNombresMed.getText().matches(".*\\d+.*")) {
                    throw new Exception();
                }
            } catch (Exception e) {
                txtNombresMed.setText("");
                c = false;
            }
            try {
                if (txtApellidoMed.getText().matches(".*\\d+.*")) {
                    throw new Exception();
                }
            } catch (Exception e) {
                txtApellidoMed.setText("");
                c = false;
            }

            try {

                Integer.parseInt(txtCodigoMed.getText());

            } catch (NumberFormatException numberFormatException) {
                //alert
                txtCodigoMed.setText("");
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

                Medico medico = new Medico(
                        txtCodigoMed.getText(),
                        txtNombresMed.getText(),
                        txtApellidoMed.getText(),
                        choiceEspecialidad.getValue(),
                        choiceFechaNac.getValue(),
                        choiceGenero.getValue(),
                        txtDireccion.getText(),
                        Integer.parseInt(txtCelular.getText()),
                        Integer.parseInt(txtTelefono.getText())
                );
                medRepo.create(medico.toMedicoTo());
                if (medico != null) {
                    medRepo.create(medico.toMedicoTo());
                    tableMedicos.getItems().add(medico.toMedicoTo());
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("¡Médico añadido!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Error al añadir medico");
                    alert.showAndWait();
                }
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Ingrese los datos correctamente");
            alert.showAndWait();
        }
//        boolean c = true;
//        try {
//            try {
//                if (txtNombresMed.getText().matches(".*\\d+.*")) {
//                    throw new Exception();
//                }
//            } catch (Exception e) {
//                txtNombresMed.setText("");
//                c = false;
//            }
//            try {
//                if (txtApellidoMed.getText().matches(".*\\d+.*")) {
//                    throw new Exception();
//                }
//            } catch (Exception e) {
//                txtApellidoMed.setText("");
//                c = false;
//            }
//            try {
//                Integer.parseInt(txtCodigoMed.getText());
//
//            } catch (NumberFormatException numberFormatException) {
//                //alert
//                txtCodigoMed.setText("");
//                c = false;
//            }
//            if (c) {
//                Medico med = listMed.buscar(txtCodigoMed.getText());
//                if (med == null) {
//                    if (!choiceEspecialidad.getValue().isEmpty()) {
//                        String a = choiceEspecialidad.getValue();
//                        Medico medico = new Medico(txtCodigoMed.getText(), txtNombresMed.getText(), txtApellidoMed.getText(), a);
//                        //System.out.println(medico);
//                        listMed.agregarTermino(medico);
//                        tableMedicos.add(medico);
//                        Alert alert = new Alert(AlertType.INFORMATION);
//                        alert.setTitle(null);
//                        alert.setHeaderText(null);
//                        alert.setContentText("¡Médico añadido!");
//                        alert.showAndWait();
//                    } else {
//                        Alert alert = new Alert(AlertType.INFORMATION);
//                        alert.setTitle(null);
//                        alert.setHeaderText(null);
//                        alert.setContentText("Elija una especialidad");
//                        alert.showAndWait();
//                    }
//                } else {
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle(null);
//                    alert.setHeaderText(null);
//                    alert.setContentText("Error al añadir medico");
//                    alert.showAndWait();
//                   
//                }
//
//            }
//        } catch (Exception e) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle(null);
//            alert.setHeaderText(null);
//            alert.setContentText("Error al añadir medico");
//            alert.showAndWait();
//            e.printStackTrace();
//        }
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

    public void btnActualizarEstadistica(ActionEvent event) {

        numPacientes.setText(Integer.toString(pacRepo.contar()));
        numMedicos.setText(Integer.toString(medRepo.contar()));
        numCitas.setText(Integer.toString(citaRepo.contar()));
        //numAtenciones.setText(Integer.toString(tableAtencion.size()));
    }

    public void AñadirEspecialidad(ActionEvent event) {
        try {
            espRepo.create(txtNuevaEspecialidad.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void btnAniadirHor(ActionEvent event) {
        try {
            Integer.parseInt(txtCodigoMed2.getText());
            Medico medi = new Medico(medRepo.read(txtCodigoMed2.getText()));
            if (medi == null) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("No existe Medico");
                alert.showAndWait();
            } else {
                if (!(choiceDias.getValue().isEmpty())) {
                    System.out.println(choiceDias.getSelectionModel().getSelectedIndex());
                    Horario h = new Horario(choiceDias.getSelectionModel().getSelectedIndex() + 1,
                            choiceHoraIni.getValue(),
                            choiceHoraFin.getValue(),
                            txtCodigoMed2.getText(),
                            choiceSede.getValue().getIdSede());
                    horarioRepo.create(h.horarioTo());
                    choiceEliminar.getItems().add(h);

                    //horario = medi.getHorario();
//                    if (!horario.contains(hora)) {
//                        horario.add(hora);
//                        horario.sort(Horario::compareTo);
//                        choiceEliminar.setItems(FXCollections.observableArrayList((List) horario));
//                    } else {
//                        Alert alert = new Alert(AlertType.WARNING);
//                        alert.setTitle("ERROR");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Ya existe el horario");
//                        alert.showAndWait();
//                    }
                    //medi.añadirHorario(hora);
                    txtMostrarHorario.setText(medi.toString());

                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Elija un horario");
                    alert.showAndWait();
                }
            }
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Solo números al codigo");
            alert.showAndWait();
            txtCodigoMed.setText("");

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Ingrese un horario");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void btnBuscarMed(ActionEvent event) {
        boolean resultado;
        String text = txtBuscarMed.getText();
        List<MedicoTO> arr = new ArrayList();
        try {
            if (text.matches("\\d+")) {
                MedicoTO med = medRepo.read(text);
                tableMedicos.getItems().clear();
                if (med == null) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Medico no encontrado");
                    alert.showAndWait();
                } else {
                    tableMedicos.getItems().add(med);
                }

            } else if (text.matches("[a-zA-Z]+")) {

                arr = medRepo.buscarMedicoPorApellido(text);

                if (arr.isEmpty()) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Medico no encontrado");
                    alert.showAndWait();
                } else {
                    tableMedicos.setItems(FXCollections.observableArrayList(arr));
                }
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Entrada no valida");
                alert.showAndWait();
            }

//            try {
//                Integer.parseInt(txtBuscarMed.getText());
//                resultado = true;
//            } catch (NumberFormatException excepcion) {
//                resultado = false;
//            }
//            ObservableList<Medico> lista = FXCollections.observableArrayList();
//            if (resultado) {
//                Medico med = listMed.buscar(txtBuscarMed.getText());
//                if (med != null) {
//                    lista.add(med);
//                }
//            } else {
//               // lista = Medico.buscarPorApe(txtBuscarMed.getText());
//            }
//            if (lista.size() > 0) {
//                tableMedicos.setItems(lista);
//            } else {
//                Alert alert = new Alert(AlertType.WARNING);
//                alert.setTitle("ERROR");
//                alert.setHeaderText(null);
//                alert.setContentText("No se encontro medico");
//                alert.showAndWait();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnMostrarTodo(ActionEvent event) {
        try {
            tableMedicos.setItems(FXCollections.observableArrayList(medRepo.readAll()));

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No hay medico");
            alert.showAndWait();
        }
    }

    public void btnAñadirUsuario(ActionEvent event) {
        try {
            if (userRepo.create(new Usuario(txtAddUsuario.getText(), txtAddPassword.getText(), choiceAddTipoUser.getValue()).toUsuarioTO())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Usuario añadido exitosamente");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("error al añadir");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("error al añadir");
            alert.showAndWait();
        }
        txtAddUsuario.setText("");
        txtAddPassword.setText("");
        choiceAddTipoUser.getSelectionModel().clearSelection();

    }

    public void btnEliminarUsuario(ActionEvent event) {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Para confirmar ingrese la credencial de un administrador");

// Set the icon (must be included in the project).
        //dialog.setGraphic();

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            UsuarioTO n = userRepo.read(usernamePassword.getKey());
            if(n != null){
            if (new Usuario(n).ValidarLog(usernamePassword.getValue())) {
                if (userRepo.delete(TxtDelUsuario.getText())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Usuario eliminado exitosamente");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("error al eliminar");
                    alert.showAndWait();
                }
                TxtDelUsuario.setText("");
            }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Credenciales incorrectas");
                    alert.showAndWait();
                }
            }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Credenciales incorrectas");
                    alert.showAndWait();
                }
        });

    }

    public void btnEliminarHor(ActionEvent event) {
        try {

            Integer.parseInt(txtCodigoMed2.getText());
            MedicoTO medi = medRepo.read(txtCodigoMed2.getText());
            //List<HorarioTO> horario = horarioRepo.read(txtCodigoMed2.getText());
            ArrayList<Horario> h = new ArrayList<>();
            //horario.forEach(a -> h.add(new Horario(a)));
            if (medi != null) {

                horarioRepo.delete(choiceEliminar.getValue().horarioTo());
                choiceEliminar.getItems().remove(choiceEliminar.getValue());
                txtMostrarHorario.setText(medi.toString());
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Medico no encontrado");
                alert.showAndWait();
            }
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Solo numeros al codigo");
            alert.showAndWait();
            txtCodigoMed2.setText("");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No hay horarios");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void BtnEliminarTodo(ActionEvent event) {
        try {

            Integer.parseInt(txtCodigoMed2.getText());
            MedicoTO medi = medRepo.read(txtCodigoMed2.getText());
            List<HorarioTO> horario = horarioRepo.read(txtCodigoMed2.getText());

            //horario.forEach(a -> h.add(new Horario(a)));
            if (medi != null) {
                for (HorarioTO h : horario) {
                    horarioRepo.delete(h);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Eliminados todos los horarios del Dr." + medi.getApellidos());
                    alert.showAndWait();
                }

                //choiceEliminar.getItems().remove(choiceEliminar.getValue());
                txtMostrarHorario.setText(medi.toString());
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Medico no encontrado");
                alert.showAndWait();
            }
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Solo numeros al codigo");
            alert.showAndWait();
            txtCodigoMed2.setText("");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No hay horarios");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void btnBuscarEspecialidad(ActionEvent event) {
        try {
            if (!comboEspecialidad.getValue().isEmpty()) {

                List arr = medRepo.buscarPorEspecialidad(comboEspecialidad.getValue());
                if (arr.isEmpty()) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay medicos de esta especialidad");
                    alert.showAndWait();
                } else {
                    tableMedicos.setItems(FXCollections.observableArrayList(arr));
                }

            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Elija una especialidad");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Error en la busqueda");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void setList() {
        ArrayList<Horario> h = new ArrayList<>();
        if (txtCodigoMed2.getText().length() == 8) {
            List<HorarioTO> horario = horarioRepo.read(txtCodigoMed2.getText());
            horario.forEach(a -> h.add(new Horario(a)));
            txtMostrarHorario.setText(h.toString());
        }
        choiceEliminar.setItems(FXCollections.observableArrayList(h));
    }

    public void BtnCerrar(MouseEvent evento) {
    
            FactoryVentanas.createVentana(tipoVentana.login);
            ((Node) (evento.getSource())).getScene().getWindow().hide();
            // Hide this current window (if this is what you want)

       
    }

    @FXML
    public void cerrar(MouseEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Especialidad.setEspecialidades(espRepo.readAll());

        choiceEspecialidad.setItems(FXCollections.observableArrayList(Especialidad.getEspecialidades()));
        
        choiceGenero.getItems().addAll("N", "M", "F");
        ArrayList<Sede> s = new ArrayList<>();
        sedeRepo.readAll().forEach(a -> s.add(new Sede(a)));
        choiceSede.setItems(FXCollections.observableList(s));
        choiceAddTipoUser.setItems(FXCollections.observableList(userRepo.getTiposUsuario()));
        ArrayList horas = new ArrayList();
        for (int i = 0; i <= 23; i++) {
            horas.add(LocalTime.of(i, 0));
        }
        choiceHoraIni.setItems(FXCollections.observableList(horas));
        choiceHoraFin.setItems(FXCollections.observableList(horas));
        choiceDias.setItems(FXCollections.observableArrayList(Horario.dias));
        //choiceHoras.setItems(FXCollections.observableArrayList(Horario.getHoras()));
        choiceEliminar.setItems(FXCollections.observableArrayList((List) horario));

        comboEspecialidad.setItems(FXCollections.observableArrayList(Especialidad.getEspecialidades()));
        txtCodigoMed2.textProperty().addListener((observable, oldValue, newValue) -> setList());
        columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        columEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        columGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        columCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        columTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
//        columHorarios.setCellValueFactory(new PropertyValueFactory<>("horarios"));
        //columHorarios.setCellValueFactory(cellData -> {
        //  String data = cellData.getValue().getHorario().toString();
        //return new ReadOnlyStringWrapper(data);
        //});
        //  System.out.println(medRepo.readAll());
        tableMedicos.setItems(FXCollections.observableArrayList(medRepo.readAll()));
        // tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        choiceEliminarEspecialidad.setItems(FXCollections.observableList(Especialidad.getEspecialidades()));
//        Callback<ListView<SedeTO>, ListCell<SedeTO>> factory = lv -> new ListCell<SedeTO>() {
//
//            @Override
//            protected void updateItem(SedeTO item, boolean empty) {
//                super.updateItem(item, empty);
//                setText(empty ? "" : item.getNombre());
//            }
//
//        };
        //   choiceSede.setCellFactory(factory);
        txtCodigoMed2.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (txtCodigoMed2.getText().length() == 8) {
                    event.consume();
                }
            }

        });
        txtCodigoMed.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (txtCodigoMed.getText().length() == 8) {
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
        //tableMedicos.setItems(FXCollections.observableArrayList(repPaciente.readAll()));
//        numPacientes.setText(Integer.toString(tablePacientes.size()));
//        numMedicos.setText(Integer.toString(tableMedicos.size()));
//        numCitas.setText(Integer.toString(tableCitas.size()));
//        numAtenciones.setText(Integer.toString(tableAtencion.size()));
    }

}
