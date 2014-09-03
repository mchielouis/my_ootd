/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ootd.com;
import android.app.Fragment;
import android.content.Context;
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
        //u_source.insertUser("michelle");
        ///List container for user objects
        ArrayList<User> users = u_source.selectAllUsers();
        ///instantiate View representation for list of user objects, defined in user_tab.xml
        ListView listview = (ListView)inflaterView.findViewById(R.id.UserListView);
        ///instantiate custom adapter to convert array to views and attach adapter to listview
        Log.d("UserTab.java","listview =" + listview);
        listview.setAdapter(new UserAdapter(this.getActivity(), users));
        
        //button to open popupedittext
        Button buttonAddUser = (Button)inflaterView.findViewById(R.id.ButtonAddUser);
        Log.d("UserTab.java","buttonAddUser =" + buttonAddUser);
        buttonAddUser.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                Log.d("UserTab.java", "buttonAddUser clicked");
                View popupView = getActivity().getLayoutInflater().inflate(R.layout.user_edittext_popup, (ViewGroup)getActivity().findViewById(R.id.popupEditText));
                Log.d("PopUpEditText.java", "popupView =" + popupView);
                PopupWindow popupEditText = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupEditText.showAtLocation(getActivity().findViewById(R.id.ButtonAddUser), Gravity.CENTER, 0,0);
                popupEditText.update(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                Button buttonEditText = (Button) popupView.findViewById(R.id.ButtonEditText);
                EditText editTextInPopUp = (EditText) popupView.findViewById(R.id.editTextInPopUp);
                Log.d("UserTab.java", "buttonEditText =" + buttonEditText);
                buttonEditText.setOnClickListener(new View.OnClickListener() 
                {
                    public void onClick(View v) 
                    {
                 //
                        //
                    }
                });
                
            }
        });
        return inflaterView;
    }
}
