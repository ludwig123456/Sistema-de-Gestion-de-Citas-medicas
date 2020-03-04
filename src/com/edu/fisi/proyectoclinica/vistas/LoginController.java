
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.vistas;

import com.edu.fisi.proyectoclinica.dao.TO.UsuarioTO;
import com.edu.fisi.proyectoclinica.login.Usuario;
import com.edu.fisi.proyectoclinica.vistas.factory.FactoryVentanas;
import com.edu.fisi.proyectoclinica.vistas.factory.Ventana;
import com.edu.fisi.proyectoclinica.vistas.factory.tipoVentana;
import com.edu.fisi.proyectoclinica.dao.factoriaDAO.FactoriaDAO;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.edu.fisi.proyectoclinica.dao.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author Nagamine
 */
public class LoginController extends Ventana implements Initializable {

    private FactoriaDAO factory = FactoriaDAO.getDAOFactoria(FactoriaDAO.tipoDAO.database);
    private UsuarioDAO login = factory.getLoginDAO();
    private double xOffset = 0;
    private double yOffset = 0;
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

    @FXML
    private PasswordField textFieldContra;
    @FXML
    private TextField textFieldUsuario;
    @FXML
    public JFXButton btnIngresar;

    public void botonIngresar(ActionEvent event) {
        try {
            String contra = textFieldContra.getText();
            String name = textFieldUsuario.getText();
            String type = "";
            boolean encontrado = false;
            UsuarioTO n = login.read(name);
            if (n != null) {
                Usuario nuevo = new Usuario(n);
                if (nuevo.ValidarLog(contra)) {
                    type = nuevo.getType();
                    encontrado = true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Contraseña incorrecta");
                    alert.setContentText("La contraseña esta equivocada");
                    alert.showAndWait();
                }

            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Usuario incorrecto");
                alert.setContentText("El usuario no existe");
                alert.showAndWait();

            }
            Parent root;
            String titulo = "";
            if (encontrado) {

                if (type.equals("U")) {
                    FactoryVentanas.createVentana(tipoVentana.usuario);
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else if (type.equals("A")) {
                    FactoryVentanas.createVentana(tipoVentana.admin);
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else if (type.equals("M")) {
                    FactoryVentanas.createVentana(tipoVentana.Medico);
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                }

                // Hide this current window (if this is what you want)
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Error de conexion");
            alert.setContentText("No se encontro la base de datos");
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
        textFieldContra.setOnKeyPressed(
                event -> {
                    switch (event.getCode()) {
                        case ENTER:
                            btnIngresar.fire();
                    }
                }
        );
        textFieldUsuario.setOnKeyPressed(
                event -> {
                    switch (event.getCode()) {
                        case ENTER:
                            btnIngresar.fire();
                    }
                }
        );
        btnIngresar.setOnKeyPressed(
                event -> {
                    switch (event.getCode()) {
                        case ENTER:
                            btnIngresar.fire();
                    }
                }
        );
    }

}
