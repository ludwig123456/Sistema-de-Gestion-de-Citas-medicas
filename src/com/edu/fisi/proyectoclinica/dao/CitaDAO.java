/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.dao;

import com.edu.fisi.proyectoclinica.dao.TO.CitaTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Nagamine
 */
public interface CitaDAO extends DAOCRUD<CitaTO,Integer> {
     @Override
     boolean create(CitaTO Cita);
     @Override
     boolean update(CitaTO Cita);
     @Override
     CitaTO  read(Integer id);
     @Override
     List<CitaTO> readAll();
     @Override
     boolean delete(Integer id);
    
     public int contar();
     public int read1(Integer dia, LocalTime i, LocalTime f, String med, int sed,LocalDate fech);
     public List<CitaTO> readDNI(String dni);
     public List<CitaTO> readEsp(String esp);
}
