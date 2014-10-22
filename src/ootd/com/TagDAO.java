/*
TagDAO (Tag database access object) utiltiy class for retrieving and inserting string and
integer information associated 

 */

package ootd.com;
import java.util.*;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.content.Context;
import android.database.sqlite.*;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteCursor;
import android.util.*;
/**
 *
 * @author Michelle
 */
public class TagDAO {

//db objects
    private SQLiteDatabase database;
    private MySQLiteHelper dbhelper;

//constructor, calls static method getInstance of MySQLiteHelper 
//and sets database object metadata of the table the adapter will perform queries on
    public TagDAO(Context context) {
            this.dbhelper= MySQLiteHelper.getInstance(context);
    }
    
//try to open db for writing
    public void open() throws SQLException {
            this.database = dbhelper.getWritableDatabase();
    }
//close db connection
    public void close() {
            this.dbhelper.close();
    }
     
//create a Tag from cursor elements
    public Tag createTag(Cursor cursor) {
        Tag tag = new Tag(cursor.getLong(0),cursor.getString(1));
        return tag;
    }
//select types where
    public ArrayList<Tag> selectTagWhere(String key) {
        //create list to store Tags
        ArrayList<Tag> Tags = new ArrayList<Tag>();
        //create wildcarded key
        String wildcarded_key = key + '%'; 
        //perform select
        Cursor cursor = database.query(dbhelper._tag_table, dbhelper.tag_cols, dbhelper.tag_cols[1] + " LIKE ?", new String[] {wildcarded_key}, null, null, null);
        //move cursor to first tuple
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tag tag = createTag(cursor);
            Tags.add(tag);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("TagDAO.java", "Selecting Tags from Tags table. Cursor: " + cursor);
        return Tags;
    }
    
    //currently hooked up to type table by default
    public Tag insertTag(String tag, String tagtype) {
        
        long insertID;
        Cursor existenceChecker = database.query(dbhelper._tag_table, dbhelper.tag_cols, dbhelper.tag_cols[1] + "=?", new String[] {tag}, null, null, null);
        if (existenceChecker.getCount() == 0) {
            //put data values into contentValue class
            ContentValues con_val_pair = new ContentValues();
            con_val_pair.put(dbhelper.tag_cols[1], tag);
            con_val_pair.put(dbhelper.tag_cols[2], tagtype);
            
            //call insert
            //move cursor to tag at insertID
            insertID = database.insert(dbhelper._tag_table, null, con_val_pair);
            Cursor cursor = database.query(dbhelper._tag_table, dbhelper.tag_cols, dbhelper.tag_cols[0] + "=" + insertID, null, null, null, null);
            cursor.moveToFirst();
            Tag returntag = new Tag(cursor.getLong(0),cursor.getString(1));
            cursor.close();
            Log.d("TagDAO.java","inserting Tag id: " + insertID + "tag: " + tag);
            return returntag;
        } else {
            return null;
        }
    }
}
