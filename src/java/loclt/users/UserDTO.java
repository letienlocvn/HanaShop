/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.users;

import java.io.Serializable;

/**
 *
 * @author WIN
 */
public class UserDTO implements Serializable {

    private String username, password, fullName;
    private int role;

    public UserDTO(String username, String password, String fullName, int role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }
    public UserDTO(String username, String fullName){
        this.username = username;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    
    

}
