/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ootd.com;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.text.*;
import android.util.*;
/**
 *
 * @author Phil
 */
public class CreateGarmentTab extends Fragment{

    EditText editTextGarmentType;
    ListView listViewCreateGarmentType;
  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
            Bundle savedInstanceState) {
        View inflaterView = inflater.inflate(R.layout.create_garment_tab, container, false);
        editTextGarmentType = (EditText) inflaterView.findViewById(R.id.EditTextGarmentType);
        
        //attach textwatcher to edittext
        editTextGarmentType.addTextChangedListener(new GarmentTabTextWatcher(editTextGarmentType));
        
        return inflaterView;
    }
    
    //inner class implements textwatcher
    //saves view passed in with constructor
    //uses to identify which listview to populate with suggestions
    private class GarmentTabTextWatcher implements TextWatcher {
        private View watchedView;
        private GarmentTabTextWatcher(View view) {
            this.watchedView = view;
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {  
            switch(watchedView.getId()) {
                case R.id.EditTextGarmentType: 
                    Log.d("CreateGarmentTab.java","before text chenged, view: "+ watchedView);
            }
        }
        
        public void onTextChanged(CharSequence s, int start, int before, int count) {
             switch(watchedView.getId()) {
                case R.id.EditTextGarmentType: 
                    Log.d("CreateGarmentTab.java","on text chenged, view: "+ watchedView);
            }
        }
        
        public void afterTextChanged(Editable s) {
            switch(watchedView.getId()) {
                case R.id.EditTextGarmentType:
                    Log.d("CreateGarmentTab.java","after text changed:"+ s.toString());
            }
        }
    };
}
