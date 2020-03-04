/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.seguridad;

/**
 *
 * @author nagamine
 */
public class Hash {
    private String salt;
    private String Hash;

    public Hash(String salt, String Hash) {
        this.salt = salt;
        this.Hash = Hash;
    }

 

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return Hash;
    }

    public void setHash(String Hash) {
        this.Hash = Hash;
    }
    
    
}
