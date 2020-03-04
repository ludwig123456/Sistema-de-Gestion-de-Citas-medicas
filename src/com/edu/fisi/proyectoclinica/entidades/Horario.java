/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.entidades;

import com.edu.fisi.proyectoclinica.dao.TO.HorarioTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LinKinPark
 */
public class Horario implements Comparable<Horario>{
    public static List<String> dias = new ArrayList<String>(Arrays.asList("Lunes", "Martes", "Miercoles",
            "Jueves", "Viernes", "Sabado", "Domingo"));
    public static List<String> horas = new ArrayList<String>(Arrays.asList("8 - 12", "12 - 16", "16 - 20"));
    private int dia;
    private LocalTime horaInicio;
    private LocalTime  horaFin;
   // private int hora;
    private String idmedico;
    private int idsede;
    public Horario(HorarioTO horario){
         this.dia = horario.getDia();
        this.horaInicio = horario.getHoraInicio();
        this.horaFin = horario.getHoraFin();
        this.idmedico = horario.getIdmedico();
        this.idsede = horario.getIdsede();
        
    }
    public Horario(int dia, LocalTime horaInicio, LocalTime horaFin, String idmedico, int idsede) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idmedico = idmedico;
        this.idsede = idsede;
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

    
//    public Horario(){
//        dia = 0;
//        horaInicio = 0;
//        horaFin = 0;
//    }
//
//    public Horario(int dia, int horaInicio, int horaFin) {
//        this.dia = dia;
//        this.horaInicio = horaInicio;
//        this.horaFin = horaFin;
//    }
//    public Horario (LocalDate dia, int horaInicio, int horaFin){
//        this.dia = dia.getDayOfWeek().getValue();
//        this.horaInicio = horaInicio;
//        this.horaFin = horaFin;
//    }
//    public Horario (LocalDate dia, String hora1){
//        this.dia = dia.getDayOfWeek().getValue();
//        switch (hora1) {
//            case "8 - 12":
//                horaInicio = 8;
//                horaFin = 12;
//                this.hora=1;
//                break;
//            case "12 - 16":
//                horaInicio = 12;
//                horaFin = 16;
//                this.hora=2;
//                break;
//            case "16 - 20":
//                horaInicio = 16;
//                horaFin = 20;
//                this.hora=3;
//                break;
//            default:
//                horaInicio = -1;
//                horaFin = -1;
//                break;
//        }
//
//    }
//
//    public Horario(String dia, String hora1) {
//        switch (dia) {
//            case "Lunes":
//                this.dia = 1;
//                break;
//            case "Martes":
//                this.dia = 2;
//                break;
//            case "Miercoles":
//                this.dia = 3;
//                break;
//            case "Jueves":
//                this.dia = 4;
//                break;
//            case "Viernes":
//                this.dia = 5;
//                break;
//            case "Sabado":
//                this.dia = 6;
//                break;
//            case "Domingo":
//                this.dia = 7;
//                break;
//            default:
//                this.dia = -1;
//        }
//        switch (hora1) {
//            case "8 - 12":
//                horaInicio = 8;
//                horaFin = 12;
//                this.hora=1;
//                break;
//            case "12 - 16":
//                horaInicio = 12;
//                horaFin = 16;
//                this.hora=2;
//                break;
//            case "16 - 20":
//                horaInicio = 16;
//                horaFin = 20;
//                this.hora=3;
//                break;
//            default:
//                horaInicio = -1;
//                horaFin = -1;
//                break;
//        }
//    }


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

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            Horario x = (Horario) o;

            return x.dia == dia && x.horaFin == horaFin && x.horaInicio == horaInicio;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo (Horario horari){
        if(dia < horari.dia){
            return -1;
        } else if (dia > horari.dia){
            return 1;
        } else {

                return horaInicio.compareTo(horari.horaInicio);

        }
    }

    public String diaToString() {
        String day = "Dia no valido";
        switch (dia) {
            case 1:
                day = "Lunes";
                break;
            case 2:
                day = "Martes";
                break;
            case 3:
                day = "Miercoles";
                break;
            case 4:
                day = "Jueves";
                break;
            case 5:
                day = "Viernes";
                break;
            case 6:
                day = "Sabado";
                break;
            case 7:
                day = "Domingo";
                break;
        }
        return day;
    }
//    public void horaT(int i){
//        String hor="hora no valida";
//        switch (i) {
//            case 1:
//                horaInicio = 8;
//                horaFin = 12;
//                this.hora=1;
//                break;
//            case 2:
//                horaInicio = 12;
//                horaFin = 16;
//                this.hora=2;
//                break;
//            case 3:
//                horaInicio = 16;
//                horaFin = 20;
//                this.hora=3;
//                break;
//            default:
//                horaInicio = -1;
//                horaFin = -1;
//                break;
//        }
//    }
    public  HorarioTO horarioTo(){
        return new HorarioTO(dia, horaInicio,horaFin,idmedico,idsede);
    }
    @Override
    public String toString() {
        return diaToString() + " " + horaInicio + " - " + horaFin;
    }
}
