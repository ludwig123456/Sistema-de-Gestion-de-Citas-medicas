/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.entidades;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.edu.fisi.proyectoclinica.Estructuras.listMed;
import com.edu.fisi.proyectoclinica.dao.TO.MedicoTO;
import java.time.LocalDate;

/**
 *
 * @author LaboratorioFISI
 */
public class Medico implements Comparable<Medico> {
    private String codigo;
    private String nombres;
    private String apellidos;
    private String especialidad;
    private LocalDate fechaN;
    private String genero;
    private String direccion;
    private int celular;
    private int telefono;
    public Medico (MedicoTO medico){
          this.codigo = medico.getCodigo();
        this.nombres = medico.getNombres();
        this.apellidos = medico.getApellidos();
        this.especialidad = medico.getEspecialidad();
        this.fechaN = medico.getFechaN();
        this.genero = medico.getGenero();
        this.direccion = medico.getDireccion();
        this.celular = medico.getCelular();
        this.telefono = medico.getTelefono();
    }
    public Medico(String codigo,String apellidos, String especialidad) {
        this.apellidos = apellidos;
        this.especialidad = especialidad;
    }

    public Medico(String codigo, String nombres, String apellidos, String especialidad, LocalDate fechaN, String genero, String direccion, int celular, int telefono) {
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    public Medico() {
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public int compareTo(Medico medico) {

        Medico medico2 = medico;
        if (codigo.compareTo(medico2.getCodigo())<0){
            return -1;
        }
        else if(codigo.compareTo(medico2.getCodigo())==0){
            return 0;
        }
        else{
            return 1;
        }
    }
//
//    public void SethorarioRandom() {
//        Random random = new Random();
//        ArrayList dias = new ArrayList();
//        for (int i = 0; i <= random.nextInt(3); i++) {
//            int dia = random.nextInt(7);
//            int hora = random.nextInt(3);
//            if(!dias.contains(dia)) {
//                dias.add(dia);
//                Horario horario = new Horario(Horario.getDias().get(dia), Horario.getHoras().get(hora));
//                horarios.add(horario);
//            } else {
//                i--;
//            }
//        }
//        horarios.sort(Horario::compareTo);
//    }
//    
//    public static Medico buscarPorNomApe(String nombres, String apellidos) {
//        Nodo<Medico> aux = listMed.getCabecera();
//        Medico med = null;
//        boolean encontrado = false;
//        while ((aux != null) && (!encontrado)) {
//
//            if ((aux.getDato().getApellidos().toLowerCase().contains(apellidos.toLowerCase() ))
//                    && (aux.getDato().getNombres().toLowerCase().contains(nombres.toLowerCase() ))) {
//
//                med = aux.getDato();
//                encontrado=true;
//            }
//            aux = aux.getSgte();
//        }
//
//        return med;
//    }
//
//    public static ObservableList<Medico> buscarPorApe(String apellidos) {
//        ObservableList<Medico> lista = FXCollections.observableArrayList();
//        Nodo<Medico> aux = listMed.getCabecera();
//        Medico med = null;
//        boolean encontrado = false;
//        while ((aux != null) && (!encontrado)) {
//
//            if ((aux.getDato().getApellidos().toLowerCase().contains(apellidos.toLowerCase() ))
//                    || (aux.getDato().getNombres().toLowerCase().contains(apellidos.toLowerCase() ))) {
//
//                med = aux.getDato();
//                lista.add(med);
//            }
//            aux = aux.getSgte();
//        }
//
//        return lista;
//    }
    
//    @Override
//    public String toString(){
//        return "Dr. " + apellidos + " "+nombres + "\n" + especialidad;
//    }
    public MedicoTO toMedicoTo(){
        return new MedicoTO(codigo, nombres, apellidos, especialidad, fechaN, genero, direccion, celular, telefono);
    }
    @Override
    public String toString() {
        return "Medico{" + "codigo=" + codigo + ", nombres=" + nombres + ", apellidos=" + apellidos + ", especialidad=" + especialidad + ", fechaN=" + fechaN + ", genero=" + genero + ", direccion=" + direccion + ", celular=" + celular + ", telefono=" + telefono + '}';
    }


}
