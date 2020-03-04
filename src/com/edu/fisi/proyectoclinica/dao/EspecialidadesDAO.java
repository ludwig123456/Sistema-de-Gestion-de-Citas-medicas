/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.dao;

import java.util.List;

/**
 *
 * @author Nagamine
 */
public interface EspecialidadesDAO extends DAOCRUD<String,String> {

     @Override
     boolean create(String Cita);
     @Override
     boolean update(String Cita);
     @Override
     String  read(String id);
     @Override
     List<String> readAll();
     @Override
     boolean delete(String id);
   
      public int contar();
      
    List<Float> readAllP();
}
