/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.dao.factoriaDAO;

import com.edu.fisi.proyectoclinica.dao.*;
import com.edu.fisi.proyectoclinica.dao.databaseDAO.*;

/**
 *
 * @author Nagamine
 */
public class DBFactoriaDAO extends FactoriaDAO {

    @Override
    public PacienteDAO getPacienteDAO() {
        return new PacienteDAODB();
    }

    @Override
    public MedicoDAO getMedicoDAO() {
        return new MedicoDAODB();
    }

    @Override
    public SedeDAO getSedeDAO() {
        return new SedeDAODB();
    }

    @Override
    public CitaDAO getCitaDAO() {
        return new CitaDAODB();
    }

    @Override
    public UsuarioDAO getLoginDAO() {
        return new UsuarioDAODB();
    }

    @Override
    public HorarioDAO getHorarioDAO() {
        return new HorarioDAODB();
    }

    @Override
    public EspecialidadesDAO getEspecialidadesDAO() {
        return new EspecialidadesDAODB();
    }

    @Override
    public HistoriaClinicaDAO getHistoriaClinicaDAO() {
        return new HistoriaClinicaDAODB();
    }
    
}
