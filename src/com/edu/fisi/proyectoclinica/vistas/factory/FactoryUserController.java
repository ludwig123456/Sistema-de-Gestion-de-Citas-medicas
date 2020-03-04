/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.vistas.factory;


import com.edu.fisi.proyectoclinica.vistas.MainAdminController;
import com.edu.fisi.proyectoclinica.vistas.MainUsuarioController;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Soporte-Bolsista
 */
public class FactoryUserController extends FactoryVentanas {
    
    @Override
    public Ventana MetodoFabrica() {
        
        try {
            //FXMLLoader f = load(Objects.requireNonNull(getClass().getClassLoader().getResource("com/edu/fisi/proyectoclinica/vistas/MainAdmin.fxml")));
            String titulo = "Clinica Aquiles Matto - Usuario";
            FXMLLoader fxmlLoader = new FXMLLoader(MainAdminController.class.getResource("MainUsuario.fxml"));
            Parent root = fxmlLoader.load();
            //MainAdminController .instanciar().getEscritorio().getChildren().add(((Ventana) fxmlLoader.getController()));
            MainUsuarioController main= (MainUsuarioController) fxmlLoader.getController();
                    
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            root.setOnMousePressed(evento -> {
                main.setxOffset(evento.getSceneX());
                main.setyOffset(evento.getSceneY());
            });
            root.setOnMouseDragged(evento -> {
                stage.setX(evento.getScreenX() - main.getxOffset());
                stage.setY(evento.getScreenY() - main.getyOffset());
            });
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
            return main;
        } catch (IOException ex) {
            Logger.getLogger(FactoryUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
}