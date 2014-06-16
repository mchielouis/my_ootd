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
public class Closet {
    
    private long closetID;
    private String closet_name;
    private long userID;

    public long getID() {
            return this.closetID;
    }
    public void setID(long id) {
            this.closetID=id;
    }

    public String getName() {
            return this.closet_name;
    }
    public void setName(String name) {
            this.closet_name=name;
    }

    public long getUserID() {
            return this.userID;
    }
    public void setUserID(long id) {
            this.userID=id;
    }
    
}
