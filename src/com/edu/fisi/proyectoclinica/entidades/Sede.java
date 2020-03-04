/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.entidades;

import com.edu.fisi.proyectoclinica.dao.TO.SedeTO;

/**
 *
 * @author Coarita
 */
public class Sede {
    private int idSede;
    private String nombre;
    private String direccion;
    private int telefono;
    public Sede (SedeTO sede){
         this.idSede = sede.getIdSede();
        this.nombre = sede.getNombre();
        this.direccion = sede.getDireccion();
        this.telefono = sede.getTelefono();
    }

    public Sede(int idSede, String nombre, String direccion, int telefono) {
        this.idSede = idSede;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre + " " + direccion;
    }
    public SedeTO toSedeTO(){
        return new SedeTO(idSede, nombre, direccion, telefono);
    }
    
}
