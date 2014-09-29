/*
Tag object stores string identifier for tag and id for database storage reference.
 */

package ootd.com;

/**
 *
 * @author Michelle
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
