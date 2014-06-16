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
/**
 *
 * @author root
 */
public class UserClosetDAO {

//db objects
    private SQLiteDatabase database;
    private MySQLiteHelper dbhelper;

//doa objects
    private User currentuser;

//constructor, calls static method getInstance of MySQLiteHelper
    //public UserClosetDAO() {}
    public UserClosetDAO(Context context) {
            dbhelper= MySQLiteHelper.getInstance(context);
    }

//try to open db for writing
    public void open() throws SQLException {
            database = dbhelper.getWritableDatabase();
    }
//close db connection
    public void close() {
            dbhelper.close();
    }
//get current user-psuedo sessionID for now
    public User getUser() {
            return this.currentuser;
    }
//set current user, restricted to UserClosetDAO functionality
    private void setUser(User user) {
            this.currentuser=user;
    }

//create a User instance and set appropriately
    public User createUser(Cursor cursor) {
            User user= new User();
            user.setID(cursor.getLong(0));
            user.setUsername(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            return user;
    }

//retrieve all users saved on device
    public List<User> selectAllUsers() {
            //create List to store users
            List<User> users = new ArrayList<User>();
            //perform select
            Cursor cursor = database.query(MySQLiteHelper._user_table, MySQLiteHelper.user_cols,null,null,null,null,null,null);
            //move cursor to first tuple
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                    User user = createUser(cursor);
                    users.add(user);
                    cursor.moveToNext();
            }
            cursor.close();
            return users;
    }

//insert user data into table	
    public User insertUser(String username, String email) {
            long insertID;

            //put data values into ContentValue class
            ContentValues con_val_pair = new ContentValues();
            con_val_pair.put(MySQLiteHelper._username, username);
            con_val_pair.put(MySQLiteHelper._email, email);

            //call insert
            insertID=database.insert(MySQLiteHelper._user_table, null, con_val_pair);

            //move cursor to user just entered and return
            Cursor cursor = database.query(MySQLiteHelper._user_table, MySQLiteHelper.user_cols,MySQLiteHelper._userID +"="+insertID,null,null,null,null,null);
            cursor.moveToFirst();
            User user = createUser(cursor);
            cursor.close();
            return user;
    }

//create a Closet instance and set appropriately
    public Closet createCloset(Cursor cursor) {
            Closet closet = new Closet();
            closet.setID(cursor.getLong(0));
            closet.setName(cursor.getString(1));
            closet.setUserID(cursor.getLong(2));
            return closet;
    }
//insert closet into data table, given current user	
    public Closet insertCloset(String closet_name, String userID) {
            long insertID;

            //put data values into ContentValue class
            ContentValues con_val_pair = new ContentValues();
            con_val_pair.put(MySQLiteHelper._closet_name, closet_name);
            con_val_pair.put(MySQLiteHelper._userID, userID);

            //call insert
            insertID=database.insert(MySQLiteHelper._closet_table, null, con_val_pair);

            //move cursor to user just entered and return
            Cursor cursor = database.query(MySQLiteHelper._closet_table, MySQLiteHelper.closet_cols,MySQLiteHelper._closetID +"="+insertID,null,null,null,null,null);
            cursor.moveToFirst();
            Closet closet = createCloset(cursor);
            cursor.close();
            return closet;
    }
//retrieve all closets, given currentuser
    public List<Closet> selectAllClosets() {
            //create List to store users
            List<Closet> closets = new ArrayList<Closet>();
            //perform select
            Cursor cursor = database.query(MySQLiteHelper._closet_table, MySQLiteHelper.closet_cols,MySQLiteHelper._userID + "="+this.currentuser.getID(),null,null,null,null,null);
            //move cursor to first tuple
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                    Closet closet = createCloset(cursor);
                    closets.add(closet);
                    cursor.moveToNext();
            }
            cursor.close();
            return closets;
    }

}
