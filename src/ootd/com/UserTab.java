/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ootd.com;
import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.util.*;
import java.util.*;

/**
 *
 * @author root
 */
public class UserTab extends Fragment {
    ///test User Object
    User testUser = new User(1, "michelle");
    
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, 
            Bundle savedInstanceState) {
        Log.d("UserTab.java","inflating user_tab");
        View inflaterView = inflater.inflate(R.layout.user_tab, container, false);
        ///open db connection in usource
        Log.d("UserDAO","opening usource connection");
        UserDAO u_source = new UserDAO(this.getActivity());
        u_source.open();
        u_source.insertUser("michelle");
        ///List container for user objects
        ArrayList<User> users = u_source.selectAllUsers();
        ///instantiate View representation for list of user objects, defined in user_tab.xml
        ListView listview = (ListView)inflaterView.findViewById(R.id.UserListView);
        ///instantiate custom adapter to convert array to views and attach adapter to listview
        Log.d("UserTab.java","listview =" + listview);
        listview.setAdapter(new UserAdapter(this.getActivity(), users));
        
      
        Button buttonAddUser = (Button)inflaterView.findViewById(R.id.ButtonAddUser);
        buttonAddUser.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 PopUpEditText popup = new PopUpEditText(container,inflater);
             }
         });
        return inflaterView;
    }
}
