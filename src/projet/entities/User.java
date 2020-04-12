/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.entities;

/**
 *
 * @author acer
 */
public class User {
    
    private  int id;
    private  String password,roles,username,email;
       int enable;
    public User() {
    }

    public User(int id, int enable, String password, String roles, String username, String email) {
        this.id = id;
        this.enable = enable;
        this.password = password;
        this.roles = roles;
        this.username = username;
        this.email = email;
    }

    public User(int enable, String password, String roles, String username, String email) {
        this.enable = enable;
        this.password = password;
        this.roles = roles;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
  
  
    
}
