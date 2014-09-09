package ootd.com;
import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.util.*;
import android.content.*;
import android.content.Intent;

/**
 *
 * @author Michelle
 */
public class UserAdapter extends ArrayAdapter<User> {
    //class members context, resourceid (for expanding functionality of custom adapter)
    //and List for the arrayadapter
    private Context context;
    //int userItemViewResourceId;
    ArrayList<User> users;
    
    
    //custom constructor calls ArrayAdapter constructor, sets parameters to members
    public UserAdapter(Context context, ArrayList<User> users) {
        super(context, R.layout.user_list_item, users);
        //this.userItemViewResourceId = userItemViewResourceId;
        this.context = context;
        this.users = users;
    }
    
    private void setUsers(ArrayList<User> newusers) {
        this.users = newusers;
    }
    
    public void update(ArrayList<User> newusers) {
        //clears adapter dataset, adds new data and notifies data set changed
        this.clear();
        this.setUsers(newusers);
        this.addAll(users);
        this.notifyDataSetChanged();
    }
    
    public View getView(int position, View converterView, ViewGroup parent) {
        //check if existing view is being used
        //otherwise inflate user_list_item.xml to create view, set to converterView
        Log.d("UserAdapter.java","checking if userlistitem view exists");
        if (converterView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            converterView = inflater.inflate(R.layout.user_list_item, parent, false);
        }
        //get a user object from the array list
        final User user = users.get(position);
        //set textviews username and email from user_list_item textviews by id
        TextView username = (TextView) converterView.findViewById(R.id.username);
        //make textview clickable
        username.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainTabActivity.java","calling setMenuItem");
                ((MainTabActivity)context).setMenuItem(user.getUsername());
                ((MainTabActivity)context).invalidateOptionsMenu();
            }
        });
                
        Log.d("UserAdapter.java","username=" + username);
        //populate data from user object into textview 
        Log.d("UserAdapter.java","setting textview text from user object");
        if (user!=null) {
            username.setText(user.getUsername());
        }
        return converterView;
    }  
}
