/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.TO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Favio
 */
public class HistoriaClinicaTO implements Serializable {
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

    public HistoriaClinicaTO(int codigo, String dni, int idcita, float peso, int talla, LocalDate fecha, LocalTime hora, String idmedico, int presion, int pulso, String diagnosticoB, String diagnosticoC, String receta) {
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
     
}
