/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ootd.com;

import android.app.Activity;
import android.os.Bundle;
import java.util.List;
import java.util.Random;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.ArrayAdapter;
/**
 *
 * @author root C:\Users\Phil\Documents\NetBeansProjects\my_ootd\bin\my_ootd-debug.apk
 */
public class DBActivity extends Activity {
//dao  object	
    //private UserClosetDAO uc_source;
    //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //super class Activity.onCreate called upon app creation
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

        //open db connection in ucsource
        //uc_source= new UserClosetDAO(this.getApplicationContext());
        //uc_source.open();

        //container for users
        //List<User> user_vals=uc_source.selectAllUsers();

        //simpleCursorAdapter shows cursor elements in list view
        //ArrayAdapter<User> usradapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1,user_vals);
        //setListAdapter(usradapter);
    }

    //called via onClick attr in main.xml
//    public void onClick(View view) {
//            @SuppressWarnings("unchecked")
//            ArrayAdapter<User> usradapter = (ArrayAdapter<User>) getListAdapter();
//            User user=null;
//            switch (view.getId()) {
//            case R.id.add:
//                    String[] test={"testname","testemail"};
//                    usradapter.add(uc_source.insertUser(test[0],test[1]));
//                    break;
//            }
//            usradapter.notifyDataSetChanged();	
//    }

      @Override
      protected void onResume() {
        //uc_source.open();
        super.onResume();
      }

      @Override
      protected void onPause() {
        //uc_source.close();
        super.onPause();
      }

}
