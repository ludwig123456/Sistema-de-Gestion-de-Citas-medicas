/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Lab02-Pc11
 */
public interface DAOCRUD<E, K> {
     boolean create(E e);
     boolean update(E e);
     E read(K id);
     List<E> readAll();
     boolean delete(K id);

}
