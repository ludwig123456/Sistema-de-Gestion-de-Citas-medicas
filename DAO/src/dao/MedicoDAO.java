/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.TO.MedicoTO;
import java.util.List;

/**
 *
 * @author Nagamine
 */
public interface MedicoDAO extends DAOCRUD<MedicoTO,String> {
     @Override
     boolean create(MedicoTO Cita);
     @Override
     boolean update(MedicoTO Cita);
     @Override
     MedicoTO  read(String id);
     MedicoTO  readMedico(String id, String n);       
     @Override
     List<MedicoTO> readAll();
     @Override
     boolean delete(String id);
     List<MedicoTO> buscarMedicoPorApellido(String nombre);
     List<MedicoTO> buscarPorEspecialidad(String especialidad);
     public int contar();
    
} 
