package com.edu.fisi.proyectoclinica.login;

import com.edu.fisi.proyectoclinica.dao.TO.UsuarioTO;
import com.edu.fisi.proyectoclinica.seguridad.EncriptionManager;
import com.edu.fisi.proyectoclinica.seguridad.Hash;

public class Usuario implements Comparable<Usuario>{
    private EncriptionManager em = new EncriptionManager();
    private String username;
    private String password;
    private String salt;
    private String type; //usuario U, admin A

   public Usuario(UsuarioTO usuario) {
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
        this.salt = usuario.getSalt();
        this.type = usuario.getType();
    }
    
    public Usuario (String username, String pass , String type ){
         this.username = username;
        Hash h = em.encriptar(pass);
        password = h.getHash();
        salt = h.getSalt();
        this.type =type;
    }
    public Usuario(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int compareTo(Usuario o) {
        return username.compareTo(o.username);
    }
    public boolean ValidarLog(String pass){
        return em.validarContraseña(new Hash(salt, password), pass);
    }
    public UsuarioTO toUsuarioTO(){
        return new UsuarioTO(username, password, salt, type);
    }

}
