/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.entidades;

import com.edu.fisi.proyectoclinica.dao.MedicoDAO;
import com.edu.fisi.proyectoclinica.dao.PacienteDAO;
import com.edu.fisi.proyectoclinica.dao.SedeDAO;
import com.edu.fisi.proyectoclinica.dao.TO.CitaTO;
import com.edu.fisi.proyectoclinica.dao.factoriaDAO.FactoriaDAO;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author LinKinPark
 */
public class Cita implements  Comparable<Cita>{
    
    private int idCita ;
    private Medico medico;
    private Paciente paciente;
    private LocalDate fecha;
    private LocalTime hora;
    private Sede sede;
    private FactoriaDAO factory= FactoriaDAO.getDAOFactoria(FactoriaDAO.tipoDAO.database);
    private MedicoDAO med = factory.getMedicoDAO();
    private PacienteDAO pac = factory.getPacienteDAO();
    private SedeDAO sed = factory.getSedeDAO();
    public Cita (CitaTO cita){
        this.idCita = cita.getIdCita();
        this.medico = new Medico(med.read(cita.getidMedico()));
        this.paciente = new Paciente(pac.read(cita.getDNIpaciente()));
        this.fecha = cita.getFecha();
        this.hora = cita.getHora();
        this.sede = new Sede(sed.read(cita.getidSede()));
    }
    public Cita(int idCita, Medico idMedico, Paciente dni, LocalDate fecha, LocalTime hora, Sede idsede) {
        this.idCita = idCita;
        this.medico = idMedico;
        this.paciente = dni;
        this.fecha = fecha;
        this.hora = hora;
        this.sede = idsede;
    }

    public Cita(Medico idMedico, Paciente dni, LocalDate fecha, LocalTime hora, Sede idsede) {
        this.medico = idMedico;
        this.paciente = dni;
        this.fecha = fecha;
        this.hora = hora;
        this.sede = idsede;
        
    }

    public Cita() {
    }

    @Override
    public int compareTo (Cita cita){
        if(fecha.compareTo(cita.fecha) < 0){
            return -1;
        } else if (fecha.compareTo(cita.fecha) > 0){
            return 1;
        } else {
            if (hora.compareTo(cita.hora) < 0){
                return -1;
            } else if( hora.compareTo(cita.hora) > 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }


    public Sede getIdsede() {
        return sede;
    }

    public void setIdsede(Sede idsede) {
        this.sede = idsede;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setIdMedico(Medico idMedico) {
        this.medico = idMedico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente dni) {
        this.paciente = dni;
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

    @Override
    public String toString() {
        return "Cita{" +
                "idCita=" + idCita +
                ", idMedico=" + medico +
                ", dni=" + paciente +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", sede=" + sede+
                '}';
    }
    public CitaTO toCitaTo(){
        return new CitaTO(idCita, medico.getCodigo(), paciente.getDni(), fecha, hora, sede.getIdSede());
    }
}
