/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author LaboratorioFISI
 */
public class Especialidad {

    static List<String> especialidades = new ArrayList<String>(Arrays.asList("Medicina Gnral", "Pediatria", "Otorrinolaringologia", "Neurologia",
            "Urologia", "Ginecologia"));
    static List<Float> precio = new ArrayList<Float>();

    public static void setEspecialidades(List<String> especialidades) {
        Especialidad.especialidades = especialidades;
    }

    public static Float getPrecio(String esp) {

        for (int i = 0; i < especialidades.size(); i++) {
            if (especialidades.get(i) == esp) {
                return precio.get(i);
            }
        }
        return null;
    }

    public static void setPrecio(List<Float> precio) {
        Especialidad.precio = precio;
    }

    public static List<String> getEspecialidades() {
        return especialidades;
    }

    public static void AñadirEspecialidad(String especialidad) {
        especialidades.add(especialidad);
    }
}
