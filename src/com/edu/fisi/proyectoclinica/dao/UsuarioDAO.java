/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.dao;

import com.edu.fisi.proyectoclinica.dao.TO.UsuarioTO;
import java.util.List;

/**
 *
 * @author Nagamine
 */
public interface UsuarioDAO extends DAOCRUD<UsuarioTO,String> {
     @Override
     boolean create(UsuarioTO Cita);
     @Override
     boolean update(UsuarioTO Cita);
     @Override
     UsuarioTO  read(String id);
     @Override
     List<UsuarioTO> readAll();
     @Override
     boolean delete(String id);
     public int contar();
     List<String> getTiposUsuario();
    
} 
