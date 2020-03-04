/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.factoriaDAO;

import dao.*;

/**
 *
 * @author Nagamine
 */
public abstract class FactoriaDAO {
    public enum tipoDAO {
        database;
    }
    public abstract PacienteDAO getPacienteDAO();
    public abstract MedicoDAO getMedicoDAO();
    public abstract SedeDAO getSedeDAO();
    public abstract CitaDAO getCitaDAO();
    public abstract UsuarioDAO getLoginDAO();
    public abstract HorarioDAO getHorarioDAO();
    public abstract EspecialidadesDAO getEspecialidadesDAO();
    public abstract HistoriaClinicaDAO getHistoriaClinicaDAO();

    public static FactoriaDAO getDAOFactoria(tipoDAO tipo){
        switch(tipo){
            case database:
                return new DBFactoriaDAO();
        }
        return null;
    }


}
