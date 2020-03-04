/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.vistas.factory;

import com.edu.fisi.proyectoclinica.entidades.Medico;
import com.edu.fisi.proyectoclinica.vistas.LoadingController;

/**
 *
 * @author Soporte-Bolsista
 */
public abstract class FactoryVentanas {
   
     public abstract Ventana MetodoFabrica();
    public static Ventana createVentana(tipoVentana v) {
        Ventana traductor = null;
        if (null != v) switch (v) {
             case admin:
                 traductor = new FactoryAdminController().MetodoFabrica();
                 break;
             case usuario:
                 traductor = new FactoryUserController().MetodoFabrica();
                 break;
             case Medico:
                 traductor = new FactoryMedicoController().MetodoFabrica();
                 break;
             case login:
                 traductor = new FactoryLoginController().MetodoFabrica(); 
                 break;
             case mainMedico:
                traductor = new FactoryMainMedicoController().MetodoFabrica(); 
                 break;
             default:
                 break;
         }
        return traductor;
    }
}
