/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.TO.PacienteTO;
import java.util.List;

/**
 *
 * @author Nagamine
 */
public interface PacienteDAO extends DAOCRUD<PacienteTO,String> {
     @Override
     boolean create(PacienteTO Cita);
     @Override
     boolean update(PacienteTO Cita);
     @Override
     PacienteTO read(String id);
     @Override
     List<PacienteTO> readAll();
     @Override
     boolean delete(String id);
 
     public int contar();
     
    
} 