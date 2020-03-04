package com.edu.fisi.proyectoclinica;

import com.edu.fisi.proyectoclinica.entidades.Cita;
import com.edu.fisi.proyectoclinica.entidades.Medico;
import com.edu.fisi.proyectoclinica.entidades.Paciente;
import com.edu.fisi.proyectoclinica.login.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Estructuras {
    public static LinkedList <Medico> listMed = new LinkedList<Medico>();
    public static PriorityQueue <Cita> colaCita = new PriorityQueue<>();
   // public static ObservableList<Cita> tableCitas = FXCollections.observableArrayList();
    public static Cita c =new Cita();

}
