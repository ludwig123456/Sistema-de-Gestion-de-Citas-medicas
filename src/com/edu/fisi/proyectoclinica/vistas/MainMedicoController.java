package com.edu.fisi.proyectoclinica.vistas;

import static com.edu.fisi.proyectoclinica.Estructuras.c;
import com.edu.fisi.proyectoclinica.dao.CitaDAO;
import com.edu.fisi.proyectoclinica.dao.HistoriaClinicaDAO;
import com.edu.fisi.proyectoclinica.entidades.Cita;
import com.edu.fisi.proyectoclinica.entidades.HistoriaClinica;
import com.edu.fisi.proyectoclinica.entidades.Paciente;
import com.edu.fisi.proyectoclinica.vistas.factory.FactoryVentanas;
import com.edu.fisi.proyectoclinica.vistas.factory.Ventana;
import com.edu.fisi.proyectoclinica.vistas.factory.tipoVentana;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.edu.fisi.proyectoclinica.dao.TO.HistoriaClinicaTO;
import com.edu.fisi.proyectoclinica.dao.factoriaDAO.FactoriaDAO;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class MainMedicoController extends Ventana implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    private FactoriaDAO factory = FactoriaDAO.getDAOFactoria(FactoriaDAO.tipoDAO.database);
    private HistoriaClinicaDAO hisRep = factory.getHistoriaClinicaDAO();
    private CitaDAO citaRepositorio = factory.getCitaDAO();
    private Cita cita;
    @FXML
    private Parent root;

    public Parent getRoot() {
        return root;
    }
    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCelular;

    @FXML
    private TextField txtFechaNac;

    @FXML
    private TextField txtGenero;

    @FXML
    private TextField txtTipoSangre;

    @FXML
    private TextField txtDNI;

    @FXML
    private TextField txtResDiagnostico;

    @FXML
    private TextArea txtReceta;

    @FXML
    private TextArea txtDisgnosticoCompleto;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtTalla;

    @FXML
    private TextField txtPulso;

    @FXML
    private TextField txtPresion;

    @FXML
    private JFXTextField txtResDiagnostico1;

    @FXML
    private JFXTextArea txtReceta1;

    @FXML
    private JFXTextArea txtDisgnosticoCompleto1;

    @FXML
    private JFXTextField txtPeso1;

    @FXML
    private JFXTextField txtTalla1;

    @FXML
    private JFXTextField txtPulso1;

    @FXML
    private JFXTextField txtPresion1;

    private List<HistoriaClinicaTO> hs;
    int indiceActual=0;
    @FXML
    public void cerrar(MouseEvent event) {
        System.exit(0);
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
        // Hide this current window (if this is what you want)

    }

    public void atender(Event event) {
        boolean ca = true;
        Boolean s = null;
        try {
            try {
                if (txtDisgnosticoCompleto.getText().matches(".*\\d+.*")) {
                    throw new Exception();
                }
            } catch (Exception e) {
                txtDisgnosticoCompleto.setText("");
                ca = false;
            }
            try {
                if (txtResDiagnostico.getText().matches(".*\\d+.*")) {
                    throw new Exception();
                }
            } catch (Exception e) {
                txtResDiagnostico.setText("");
                ca = false;
            }
            try {
                if (txtReceta.getText().matches(".*\\d+.*")) {
                    throw new Exception();
                }
            } catch (Exception e) {
                txtReceta.setText("");
                ca = false;
            }

            try {
                Integer.parseInt(txtPresion.getText());
            } catch (NumberFormatException numberFormatException) {
                //alert
                txtPresion.setText("");
                ca = false;
            }
            try {
                Integer.parseInt(txtPulso.getText());
            } catch (NumberFormatException numberFormatException) {
                //alert
                txtPulso.setText("");
                ca = false;
            }
            try {

                Integer.parseInt(txtTalla.getText());

            } catch (NumberFormatException numberFormatException) {
                //alert
                txtTalla.setText("");
                ca = false;
            }

            BigDecimal peso = new BigDecimal(txtPeso.getText());
            peso = peso.setScale(2, BigDecimal.ROUND_HALF_EVEN);

//            try {
//
//                Integer.parseInt(txtPeso.getText());
//
//            } catch (NumberFormatException numberFormatException) {
//                //alert
//                txtPeso.setText("");
//                ca = false;
//            }
            if (ca) {
                HistoriaClinica clin = new HistoriaClinica(
                        c.getPaciente().getDni(),
                        c.getIdCita(),
                        peso.floatValue(),
                        Integer.parseInt(txtTalla.getText()),
                        LocalDate.now(),
                        LocalTime.now(),
                        c.getMedico().getCodigo(),
                        Integer.parseInt(txtPresion.getText()),
                        Integer.parseInt(txtPulso.getText()),
                        txtResDiagnostico.getText(),
                        txtDisgnosticoCompleto.getText(),
                        txtReceta.getText()
                );
                if (clin != null) {
                    System.out.println(peso);
                    s = hisRep.create(clin.toHistoriaClinicaTO());
                    citaRepositorio.delete(c.getIdCita());
//                    System.out.println(s);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("¡Historia Clinica añadida!");
                    alert.showAndWait();
                } else {

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
            System.out.println(e.getMessage());
        }
    }

    void initData(Cita cita) {
//        System.out.println(cita);
        this.cita = cita;

//        Paciente paciente = cita.getPaciente();
//        txtDNI.setText(paciente.getDni());
//        txtApellidos.setText(paciente.getApellidos());
//        txtCelular.setText(paciente.getCelular());
//        txtFechaNac.setText(paciente.getFechaNacimiento().toString());
//        txtDireccion.setText(paciente.getDireccion());
//        txtNombres.setText(paciente.getNombres());
//        txtGenero.setText(String.valueOf( paciente.getGenero()));
//        txtTipoSangre.setText(paciente.getTipoSangre());
//        txtTelefono.setText(paciente.getTelefono());
        //paciente.setText(customer.getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Paciente paciente = c.getPaciente();
        txtDNI.setText(paciente.getDni());
        txtApellidos.setText(paciente.getApellidos());
        txtCelular.setText(paciente.getCelular());
        txtFechaNac.setText(paciente.getFechaNacimiento().toString());
        txtDireccion.setText(paciente.getDireccion());
        txtNombres.setText(paciente.getNombres());
        txtGenero.setText(String.valueOf(paciente.getGenero()));
        txtTipoSangre.setText(paciente.getTipoSangre());
        txtTelefono.setText(paciente.getTelefono());
//        System.out.println("init");
        try {
            //System.out.println(listMed.mostrar());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        hs=hisRep.readDNI(c.getPaciente().getDni());     
     
        try{
            set(hs.get(indiceActual));
            
        }catch(Exception e){
            System.out.println("");
        }
        System.out.println(hs.size());
        
  
       
        
        
    }
    public void siguiente(ActionEvent e){
        //if(indiceActual>hs.size()) indiceActual=hs.size()-1;
        if(indiceActual<0) indiceActual=0;
        indiceActual++;
        if(hs.size()>indiceActual){
            set(hs.get(indiceActual));
        }
    }
    public void atras(ActionEvent e){
        if(indiceActual>hs.size()) indiceActual=hs.size()-1;
        //if(indiceActual<0) indiceActual=0;
        indiceActual--;
        if((hs.size()>indiceActual)&&(indiceActual>=0)){
            set(hs.get(indiceActual));
        }
    }

    private void set(HistoriaClinicaTO cc) {

        txtResDiagnostico1.setText(cc.getDiagnosticoB());

        txtReceta1.setText(cc.getReceta());

        txtDisgnosticoCompleto1.setText(cc.getDiagnosticoC());
        txtPeso1.setText(String.valueOf(cc.getPeso()));
        txtTalla1.setText(String.valueOf(cc.getTalla()));

        txtPulso1.setText(String.valueOf(cc.getPulso()));
        txtPresion1.setText(String.valueOf(cc.getPresion()));
    }
}
