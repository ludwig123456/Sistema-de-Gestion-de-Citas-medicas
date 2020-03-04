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
 * @author Nagamine
 */
public class CitaTO implements Serializable {
     private int idCita ;
    private String idmedico;
    private String DNIpaciente;
    private LocalDate fecha;
    private LocalTime hora;
    private int idSede;

    public CitaTO(int idCita, String medico, String paciente, LocalDate fecha, LocalTime hora, int sede) {
        this.idCita = idCita;
        this.idmedico = medico;
        this.DNIpaciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
        this.idSede = sede;
    }
    public CitaTO( String medico, String paciente, LocalDate fecha, LocalTime hora, int sede) {
        
        this.idmedico = medico;
        this.DNIpaciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
        this.idSede = sede;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getidMedico() {
        return idmedico;
    }

    public void setMedico(String medico) {
        this.idmedico = medico;
    }

    public String getDNIpaciente() {
        return DNIpaciente;
    }

    public void setDNIpaciente(String paciente) {
        this.DNIpaciente = paciente;
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

    public int getidSede() {
        return idSede;
    }

    public void setidSede(int sede) {
        this.idSede = sede;
    }
    
}
