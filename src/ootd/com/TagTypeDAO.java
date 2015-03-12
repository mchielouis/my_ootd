/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class TagTypeDAO {
    
//db objects
    private SQLiteDatabase database;
    private MySQLiteHelper dbhelper;
    
    //constructor, calls static method getInstance of MySQLiteHelper 
//and sets database object metadata of the table the adapter will perform queries on
    public TagTypeDAO(Context context) {
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
    
    public void insertTag(String tagtype) {
        
        long insertID;
        Cursor existenceChecker = database.query(dbhelper._tagtype_table, dbhelper.tagtype_cols, dbhelper.tagtype_cols[0] + "=?", new String[] {tagtype}, null, null, null);
        if (existenceChecker.getCount() == 0) {
            //put data values into contentValue class
            ContentValues con_val_pair = new ContentValues();
            con_val_pair.put(dbhelper.tagtype_cols[0], tagtype);
            
            //call insert
            //move cursor to tag at insertID
            insertID = database.insert(dbhelper._tagtype_table, null, con_val_pair);
            //Cursor cursor = database.query(dbhelper._tagtype_table, dbhelper.tagtype_cols, dbhelper.tagtype_cols[0] + "=" + insertID, null, null, null, null);
            //cursor.moveToFirst();
            //Tag returntag = new Tag(cursor.getLong(0),cursor.getString(1));
            //cursor.close();
            //Log.d("TagDAO.java","inserting Tag id: " + insertID + "tag: " + tag);
           //return returntag;
        } else {
            //return null;
        }
    }
}
