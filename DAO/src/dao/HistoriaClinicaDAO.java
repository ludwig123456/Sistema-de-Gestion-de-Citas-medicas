/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.TO.HistoriaClinicaTO;
import java.util.List;

/**
 *
 * @author Nagamine
 */
public interface HistoriaClinicaDAO extends DAOCRUD<HistoriaClinicaTO,String>{
     @Override
     boolean create(HistoriaClinicaTO Cita);
     @Override
     boolean update(HistoriaClinicaTO Cita);
     @Override
     HistoriaClinicaTO  read(String id);
     @Override
     List<HistoriaClinicaTO> readAll();
     List<HistoriaClinicaTO> readDNI(String s);
     @Override
     boolean delete(String id);
 
      public int contar();
}
