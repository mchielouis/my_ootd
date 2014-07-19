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
 * @author Phil
 */
public class GarmentTab extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
            Bundle savedInstanceState) {
        View inflaterView = inflater.inflate(R.layout.test_tab, container, false);
        TextView textview = (TextView) inflaterView.findViewById(R.id.tabtextview);
        textview.setText(R.string.Garment);
        return inflaterView;
    }   
}
