package com.edu.fisi.proyectoclinica.vistas;

import static com.edu.fisi.proyectoclinica.Estructuras.c;
import com.edu.fisi.proyectoclinica.dao.CitaDAO;
import com.edu.fisi.proyectoclinica.entidades.Cita;
import com.edu.fisi.proyectoclinica.vistas.factory.FactoryVentanas;
import com.edu.fisi.proyectoclinica.vistas.factory.Ventana;
import com.edu.fisi.proyectoclinica.vistas.factory.tipoVentana;
import com.edu.fisi.proyectoclinica.dao.TO.CitaTO;
import com.edu.fisi.proyectoclinica.dao.databaseDAO.CitaDAODB;
import com.edu.fisi.proyectoclinica.dao.factoriaDAO.FactoriaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class LogMedicoController extends Ventana {
    private FactoriaDAO factory = FactoriaDAO.getDAOFactoria(FactoriaDAO.tipoDAO.database);
    private CitaDAO citaRepositorio = factory.getCitaDAO();
    @FXML
    private TextField txtAtencionCita;
    
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Parent root;

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
    
public Parent getRoot() {
       return root;
    }
    @FXML
    void btnAtender(ActionEvent event) {
        try {
            CitaTO cita = citaRepositorio.read(Integer.parseInt(txtAtencionCita.getText()));
            
            if (cita != null) {
                    c =new Cita(cita) ;
                    
                MainMedicoController mainMedicoController = (MainMedicoController)FactoryVentanas.createVentana(tipoVentana.mainMedico);
                ((Node) (event.getSource())).getScene().getWindow().hide();
                mainMedicoController.initData(c);
                
//                citaRepositorio.create(cita);
                
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("¡cita no encontrada!");
                alert.showAndWait();
            }
        } catch ( NumberFormatException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch ( Exception e) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("¡cita no encontrada!");
                alert.showAndWait();
        }
    }

    @FXML
    public void BtnCerrar(MouseEvent evento) {
          FactoryVentanas.createVentana(tipoVentana.login);
            ((Node) (evento.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void btnEliminarCita(ActionEvent event) {
        citaRepositorio.delete(Integer.parseInt(txtAtencionCita.getText()));
    }

    @FXML
    void cerrar(MouseEvent event) {
        System.exit(0);
    }


    


}
