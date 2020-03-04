/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.entidades;

import com.edu.fisi.proyectoclinica.dao.TO.HistoriaClinicaTO;
import java.time.LocalDate;
import java.time.LocalTime;

public class HistoriaClinica implements Comparable<HistoriaClinica>{
    private int codigo;
    private String dni;
    private int idcita;
    private float peso;
    private int talla;
    private LocalDate fecha;
    private LocalTime hora;
    private String idmedico;
    private int presion;
    private int pulso;    
    private String diagnosticoB; 
    private String diagnosticoC;
     private String receta;
     
     public HistoriaClinica(HistoriaClinicaTO historiaClinica){
          this.codigo = historiaClinica.getCodigo();
        this.dni = historiaClinica.getDni();
        this.idcita = historiaClinica.getIdcita();
        this.peso = historiaClinica.getPeso();
        this.talla = historiaClinica.getTalla();
        this.fecha = historiaClinica.getFecha();
        this.hora = historiaClinica.getHora();
        this.idmedico = historiaClinica.getIdmedico();
        this.presion = historiaClinica.getPresion();
        this.pulso = historiaClinica.getPulso();
        this.diagnosticoB = historiaClinica.getDiagnosticoB();
        this.diagnosticoC = historiaClinica.getDiagnosticoC();
        this.receta = historiaClinica.getReceta();
     }
    public HistoriaClinica(int codigo, String medico, LocalDate fecha) {
        this.codigo = codigo;
        this.dni = medico;
        this.fecha = fecha;
    }

    public HistoriaClinica(int codigo, String dni, int idcita, float peso, int talla, LocalDate fecha, LocalTime hora, String idmedico, int presion, int pulso, String diagnosticoB, String diagnosticoC, String receta) {
        this.codigo = codigo;
        this.dni = dni;
        this.idcita = idcita;
        this.peso = peso;
        this.talla = talla;
        this.fecha = fecha;
        this.hora = hora;
        this.idmedico = idmedico;
        this.presion = presion;
        this.pulso = pulso;
        this.diagnosticoB = diagnosticoB;
        this.diagnosticoC = diagnosticoC;
        this.receta = receta;
    }
    public HistoriaClinica(String dni, int idcita, float peso, int talla, LocalDate fecha, LocalTime hora, String idmedico, int presion, int pulso, String diagnosticoB, String diagnosticoC, String receta) {
        this.dni = dni;
        this.idcita = idcita;
        this.peso = peso;
        this.talla = talla;
        this.fecha = fecha;
        this.hora = hora;
        this.idmedico = idmedico;
        this.presion = presion;
        this.pulso = pulso;
        this.diagnosticoB = diagnosticoB;
        this.diagnosticoC = diagnosticoC;
        this.receta = receta;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getIdcita() {
        return idcita;
    }

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getIdmedico() {
        return idmedico;
    }

    public void setIdmedico(String idmedico) {
        this.idmedico = idmedico;
    }

    public int getPresion() {
        return presion;
    }

    public void setPresion(int presion) {
        this.presion = presion;
    }

    public int getPulso() {
        return pulso;
    }

    public void setPulso(int pulso) {
        this.pulso = pulso;
    }

    public String getDiagnosticoB() {
        return diagnosticoB;
    }

    public void setDiagnosticoB(String diagnosticoB) {
        this.diagnosticoB = diagnosticoB;
    }

    public String getDiagnosticoC() {
        return diagnosticoC;
    }

    public void setDiagnosticoC(String diagnosticoC) {
        this.diagnosticoC = diagnosticoC;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMedico() {
        return dni;
    }

    public void setMedico(String medico) {
        this.dni = medico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    
    
    @Override
    public String toString(){
        return "Codigo: " + codigo +"\n Medico: " + dni+"\n Fecha: "+fecha+"\nDiagnostico: ";
    }

    @Override
    public int compareTo(HistoriaClinica hist) {
        if (codigo<hist.getCodigo()){
            return -1;
        }
        else if(codigo==hist.getCodigo()){
            return 0;
        }
        else{
            return 1;
        }
    }
    public HistoriaClinicaTO toHistoriaClinicaTO (){
        return new HistoriaClinicaTO(codigo, dni, idcita, peso, talla, fecha, hora, idmedico, presion, pulso, diagnosticoB, diagnosticoC, receta);
    }
}
