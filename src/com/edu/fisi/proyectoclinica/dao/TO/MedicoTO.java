/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.dao.TO;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Favio
 */
public class MedicoTO implements Serializable {
      private String codigo;
    private String nombres;
    private String apellidos;
    private String especialidad;
    private LocalDate fechaN;
    private String genero;
    private String direccion;
    private int celular;
    private int telefono;

    public MedicoTO(String codigo, String nombres, String apellidos, String especialidad, LocalDate fechaN, String genero, String direccion, int celular, int telefono) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
        this.fechaN = fechaN;
        this.genero = genero;
        this.direccion = direccion;
        this.celular = celular;
        this.telefono = telefono;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDate getFechaN() {
        return fechaN;
    }

    public void setFechaN(LocalDate fechaN) {
        this.fechaN = fechaN;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
}
