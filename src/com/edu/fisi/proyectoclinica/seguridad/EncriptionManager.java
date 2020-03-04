/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.fisi.proyectoclinica.seguridad;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nagamine
 */
public class EncriptionManager {

    public Hash encriptar(String pass) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
            StringBuilder sal = new StringBuilder();
            for (int i = 0; i < salt.length; i++) {
                sal.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
            }
            md.update(sal.toString().getBytes(StandardCharsets.UTF_8));
            byte[] hashedPassword = md.digest(pass.getBytes(StandardCharsets.UTF_8));

            StringBuilder generatedPassword = new StringBuilder();
            for (int i = 0; i < hashedPassword.length; i++) {
                generatedPassword.append(Integer.toString((hashedPassword[i] & 0xff) + 0x100, 16).substring(1));
            }

            return new Hash(sal.toString(), generatedPassword.toString());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean validarContraseña(Hash h, String pass) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(h.getSalt().getBytes(StandardCharsets.UTF_8));

            byte[] hashedPassword = md.digest(pass.getBytes(StandardCharsets.UTF_8));

            StringBuilder generatedPassword = new StringBuilder();
            for (int i = 0; i < hashedPassword.length; i++) {
                generatedPassword.append(Integer.toString((hashedPassword[i] & 0xff) + 0x100, 16).substring(1));
            }

            if(h.getHash().equals(generatedPassword.toString())){
                return true;
            }else{
                return false;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void mostrarContraseña(Hash h, String pass) {
        boolean a;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(h.getSalt().getBytes(StandardCharsets.UTF_8));

            byte[] hashedPassword = md.digest(pass.getBytes(StandardCharsets.UTF_8));

            StringBuilder generatedPassword = new StringBuilder();
            for (int i = 0; i < hashedPassword.length; i++) {
                generatedPassword.append(Integer.toString((hashedPassword[i] & 0xff) + 0x100, 16).substring(1));
            }

            if(h.getHash().equals(generatedPassword.toString())){
                a=true;
            }else{
                a=false;
            }
            System.out.println(generatedPassword.toString());
            System.out.println(a);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
