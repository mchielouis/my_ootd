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
 * @author Michelle
 */
public class UserTab extends Fragment {
    
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, 
            Bundle savedInstanceState) {
        Log.d("UserTab.java","inflating user_tab");
        View inflaterView = inflater.inflate(R.layout.user_tab, container, false);
        
        ///open db connection in usource
        Log.d("UserDAO","opening usource connection");
        final UserDAO u_source = new UserDAO(this.getActivity());
        u_source.open();
        
        ///List container for user objects
        ArrayList<User> users = u_source.selectAllUsers();
        
        ///instantiate View representation for list of user objects, defined in user_tab.xml
        ListView listview = (ListView)inflaterView.findViewById(R.id.UserListView);
        
        ///instantiate custom adapter to convert array to views and attach adapter to listview
        Log.d("UserTab.java","listview =" + listview);
        final UserAdapter useradapter = new UserAdapter(this.getActivity(), users);
        listview.setAdapter(useradapter);
        
        //button to open popupedittext
        Button buttonAddUser = (Button)inflaterView.findViewById(R.id.ButtonAddUser);
        Log.d("UserTab.java","buttonAddUser =" + buttonAddUser);
        buttonAddUser.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
                Log.d("UserTab.java", "buttonAddUser clicked");
                View popupView = getActivity().getLayoutInflater().inflate(R.layout.user_edittext_popup, (ViewGroup)getActivity().findViewById(R.id.popupEditText));
                Log.d("UserTab.java", "popupView =" + popupView);
                
                //initialize popupWindow with popupView and set width and length to wrap content
                //show popup at buttonAddUser, update
                final PopupWindow popupEditText = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupEditText.showAsDropDown(getActivity().findViewById(R.id.ButtonAddUser));
                popupEditText.update(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                
                //button to send edittext to userdao for queryexistence check and insertion
                //getText from editTextInPopUp
                Button buttonEditText = (Button) popupView.findViewById(R.id.ButtonEditText);
                final EditText editTextInPopUp = (EditText) popupView.findViewById(R.id.editTextInPopUp);
                
                
                Log.d("UserTab.java", "buttonEditText =" + buttonEditText);
                buttonEditText.setOnClickListener(new View.OnClickListener() 
                {
                    public void onClick(View v) 
                    {
                        //string from edittext 
                        String _username = editTextInPopUp.getText().toString();
                        //if string null, dismiss popup, else attempt insert
                        if(_username.trim().length() == 0) 
                            popupEditText.dismiss();
                        else {
                            Log.d("UserTab.java", "_username =" + _username);
                            u_source.insertUser(_username);
                            useradapter.update(u_source.selectAllUsers());
                            popupEditText.dismiss();
                        }
                    }
                });
                
            }
        });
        
        return inflaterView;
    }
}
