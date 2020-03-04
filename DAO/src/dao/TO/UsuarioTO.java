/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.TO;

import java.io.Serializable;

/**
 *
 * @author Favio
 */
public class UsuarioTO implements Serializable{
    private String username;
    private String password;
    private String salt;
    private String type;

    public UsuarioTO(String username, String password, String salt, String type) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
