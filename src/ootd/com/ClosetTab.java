/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ootd.com;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.app.Fragment;
/**
 *
 * @author root
 */
public class ClosetTab extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
            Bundle savedInstanceState) {
        View inflaterView = inflater.inflate(R.layout.test_tab, container, false);
        TextView textview = (TextView) inflaterView.findViewById(R.id.tabtextview);
        textview.setText(R.string.Closet);
        return inflaterView;
    }
    private long closetID;
    private String closet_name;
    private long userID;

    public long getID() {
            return this.closetID;
    }
    public void setID(long id) {
            this.closetID=id;
    }

    public String getName() {
            return this.closet_name;
    }
    public void setName(String name) {
            this.closet_name=name;
    }

    public long getUserID() {
            return this.userID;
    }
    public void setUserID(long id) {
            this.userID=id;
    }
    
}
