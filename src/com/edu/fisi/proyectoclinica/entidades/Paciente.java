package com.edu.fisi.proyectoclinica.entidades;


import com.edu.fisi.proyectoclinica.dao.TO.PacienteTO;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;


public class Paciente implements Comparable {
    private String nombres;
    private String apellidos;
    private String dni;
    private String direccion;
    private String telefono;
    private String celular;
    private String tipoSangre;
    private char genero;
    private LocalDate fechaNacimiento;
    
    public Paciente (PacienteTO paciente){
      this.nombres = paciente.getNombres();
        this.apellidos = paciente.getApellidos();
        this.dni = paciente.getDni();
        this.direccion = paciente.getDireccion();
        this.telefono = paciente.getTelefono();
        this.celular = paciente.getCelular();
        this.tipoSangre = paciente.getTipoSangre();
        this.genero = paciente.getGenero();
        this.fechaNacimiento = paciente.getFechaNacimiento();
        
    }
    
    public Paciente(String dni){
        this.dni=dni;
        this.fechaNacimiento = null;
    }
    public Paciente(String dni,String nombres, String apellidos ) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni= dni;
        this.fechaNacimiento = null;
    }

    public Paciente(String dni,String nombres, String apellidos,  String direccion, LocalDate fechaNacimiento, String telefono, String celular, char genero ,String tipoSangre) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.tipoSangre = tipoSangre;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Paciente(String nombres, String apellidos, String dni, String direccion, String tipoSangre, char genero, LocalDate fechaNacimiento) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.tipoSangre = tipoSangre;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Paciente(String nombres, String apellidos, String dni, String direccion, String telefono, String tipoSangre, char genero, LocalDate fechaNacimiento) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoSangre = tipoSangre;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public Paciente() {

    }
   

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", celular='" + celular + '\'' +
                ", tipoSangre='" + tipoSangre + '\'' +
                ", genero=" + genero +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }

    @Override
    public int compareTo(Object paciente) {
        Paciente paciente2 = (Paciente) paciente;
        if (dni.compareTo(paciente2.getDni())<0){
            return -1;
        }
        else if(dni.compareTo(paciente2.getDni())>0){
            return 1;
        }
        else{
            return 0;
        }
    
    }

    public PacienteTO toPacienteTO(){
        return new PacienteTO(nombres, apellidos, dni, direccion, fechaNacimiento, telefono, celular, genero, tipoSangre);
    }
}
