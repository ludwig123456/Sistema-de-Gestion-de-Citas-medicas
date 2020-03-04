/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.dao;

import com.edu.fisi.proyectoclinica.dao.TO.HorarioTO;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Nagamine
 */
public interface HorarioDAO {
     boolean create(HorarioTO Cita);
     boolean update(HorarioTO Cita);
     List<HorarioTO> read(String id);
     List<HorarioTO> readAll();
     boolean delete(String id);
     public List<HorarioTO>  horarioMin(String especialidad, int idsede, int dia);
     public boolean delete(HorarioTO horario);
     public int contar();
}
