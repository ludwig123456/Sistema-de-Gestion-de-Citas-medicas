/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.vistas;

import com.edu.fisi.proyectoclinica.seguridad.EncriptionManager;
import com.edu.fisi.proyectoclinica.seguridad.Hash;

/**
 *
 * @author Alex
 */
public class hash {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EncriptionManager em = new EncriptionManager();
        Hash h = new Hash("94ba2e8bbf925b69816a54dda57a12c2","787a0727074ce66dae17392da4cad8a231362c302265c82c8eac252c4610ae0909ae34dd8086aa2406c2ab11f0de5499b2cbddd5970fd3e1cec60c2e85a05361");
        System.out.println(h.getHash());
        em.mostrarContraseña(h, "123");
    }
    
}
