/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ootd.com;

/**
 *
 * @author root
 */
public class User {
    private long userID;
    private String username;
    private String email;
	
    public long getID() {
            return userID;
    }
    public void setID(long id) {
            this.userID=id;
    }

    public String getUsername() {
            return username;
    }
    public void setUsername(String name) {
            this.username=name;
    }

    public String getEmail() {
            return email;
    }
    public void setEmail(String email) {
            this.email=email;
    }

	    
}
