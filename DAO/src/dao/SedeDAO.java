/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.TO.SedeTO;
import java.util.List;

/**
 *
 * @author Nagamine
 */
public interface SedeDAO extends DAOCRUD<SedeTO,Integer> {
     @Override
     boolean create(SedeTO Cita);
     @Override
     boolean update(SedeTO Cita);
     @Override
     SedeTO  read(Integer id);
     @Override
     List<SedeTO> readAll();
     @Override
     boolean delete(Integer id);
     
     public SedeTO readN(String id);
     public int contar();
    
} 