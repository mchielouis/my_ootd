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
 * @author root
 */
public class UserDAO {

//db objects
    private SQLiteDatabase database;
    private MySQLiteHelper dbhelper;

//doa objects
    private User currentuser;

//constructor, calls static method getInstance of MySQLiteHelper
    //public UserDAO() {}
    public UserDAO(Context context) {
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
//set current user, restricted to UserDAO functionality
    private void setUser(User user) {
            this.currentuser=user;
    }

//create a User object instance 
    //from cursor elements
    //by setting user members 
    public User createUser(Cursor cursor) {
            User user= new User(cursor.getLong(0),cursor.getString(1));
            return user;
    }

//retrieve all User Object instances saved on device
    public ArrayList<User> selectAllUsers() {
            //create List to store users
            ArrayList<User> users = new ArrayList<User>();
            //perform select
            Cursor cursor = database.query(MySQLiteHelper.user.getTableName(), MySQLiteHelper.user.getCols(),null,null,null,null,null,null);
            //move cursor to first tuple
            cursor.moveToFirst();
            //when cursor shifts
                ///create user from cursor 
                ///add user to List of user objects
                ///shift cursor
           //close cursor, return User object list
            while (!cursor.isAfterLast()) {
                    User user = createUser(cursor);
                    users.add(user);
                    cursor.moveToNext();
            }
            cursor.close();
            Log.d("UserDAO","Selecting Users from user table. Cursor: "+cursor);
            return users;
    }

//insert User object data into "_user_table"	
    public User insertUser(String username) {
            long insertID;
            Cursor existenceChecker = database.query(MySQLiteHelper.user.getTableName(), MySQLiteHelper.user.getCols(),MySQLiteHelper.user.getCols()[1] + "=?",new String[] {username},null,null,null,null); 
            if (username.trim().length()==0) 
                return null;
            else if (existenceChecker.getCount() == 0) {
                //put data values into ContentValue class
                ContentValues con_val_pair = new ContentValues();
                con_val_pair.put(MySQLiteHelper.user.getCols()[1], username);

                //call insert
                insertID = database.insert(MySQLiteHelper.user.getTableName(), null, con_val_pair);

                //move cursor to user at insertID (returned from database.insert) and return
                Cursor cursor = database.query(MySQLiteHelper.user.getTableName(), MySQLiteHelper.user.getCols(), MySQLiteHelper.user.getCols()[0] + "=" + insertID, null, null, null, null, null);
                cursor.moveToFirst();
                User user = new User(cursor.getLong(0), cursor.getString(1));
                cursor.close();
                Log.d("UserDAO", "inserting User into user table. id: " + insertID + " username: " + username);
                return user;
            } else {
                return null;
            }
    }


}
