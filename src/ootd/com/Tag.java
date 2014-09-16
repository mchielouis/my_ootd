/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ootd.com;

/**
 *
 * @author Phil
 */
public class Tag {
    private long tagID;
    private String tag;
    
    public Tag(){}
    public Tag(long tagID, String tag) {
        this.tagID=tagID;
        this.tag=tag;
    }
    
    public long getTagID() {
        return this.tagID;
    }
    
    public String getTag() {
        return this.tag;
    }
}
