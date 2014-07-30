package ootd.com;
import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.util.*;
import android.content.*;


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
    
    public View getView(int position, View converterView, ViewGroup parent) {
        //check if existing view is being used
        //otherwise inflate user_list_item.xml to create view, set to converterView
        Log.d("UserAdapter.java","checking if userlistitem view exists");
        if (converterView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            converterView = inflater.inflate(R.layout.user_list_item, parent, false);
        }
        //get a user object from the array list
        User user = users.get(position);
        //set textviews username and email from user_list_item textviews by id
        TextView username = (TextView) converterView.findViewById(R.id.username);
        Log.d("UserAdapter.java","username=" + username);
        TextView email = (TextView) converterView.findViewById(R.id.email);
        Log.d("UserAdapter.java","username=" + email);
        //populate data from user object into textview 
        Log.d("UserAdapter.java","setting textview text from user object");
        if (user!=null) {
            username.setText(user.getUsername());
            email.setText(user.getEmail());
        }
        return converterView;
    }  
}
