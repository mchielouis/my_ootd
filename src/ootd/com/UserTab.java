/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ootd.com;
import android.app.Fragment;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

/**
 *
 * @author root
 */
public class UserTab extends Fragment {
    @Override
    
    //Returns "inflaterview" view, 
        ///by inflating "test_tab" layout.
        ///and puting inflaterview in "container" viewgroup.
    //Creates "textview" textview. 
            ///by casting "inflaterView" view to textview
            ///and calling inflaterview.findbyviewid)
            ///using "tabtextview"
    //Sets string in "textview" to R.id.string."user".
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
            Bundle savedInstanceState) {
        View inflaterView = inflater.inflate(R.layout.test_tab, container, false);
        TextView textview = (TextView) inflaterView.findViewById(R.id.tabtextview);
        textview.setText(R.string.User);
        return inflaterView;
    }
}
