/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.TO;

import java.io.Serializable;
import java.time.LocalTime;

/**
 *
 * @author Favio
 */
public class HorarioTO implements Serializable{
     private int dia;
    private LocalTime horaInicio;
    private LocalTime  horaFin;
   // private int hora;
    private String idmedico;
    private int idsede;

    public HorarioTO(int dia, LocalTime horaInicio, LocalTime horaFin, String idmedico, int idsede) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idmedico = idmedico;
        this.idsede = idsede;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getIdmedico() {
        return idmedico;
    }

    public void setIdmedico(String idmedico) {
        this.idmedico = idmedico;
    }

    public int getIdsede() {
        return idsede;
    }

    public void setIdsede(int idsede) {
        this.idsede = idsede;
    }
    
}
