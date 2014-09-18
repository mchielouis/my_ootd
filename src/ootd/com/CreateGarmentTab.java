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
import java.util.*;
/**
 *
 * @author Phil
 */
public class CreateGarmentTab extends Fragment {

    EditText editTextTypeTag, editTextColorTag, editTextPatternTag, editTextMaterialTag;
    HorizontalListView listViewTypeTag;
    HorizontalListView listViewColorTag;
    GridView gridViewTag;
    ArrayList<Tag> typetags = new ArrayList<Tag>();
    ArrayList<Tag> colortags = new ArrayList<Tag>();
    TagAdapter typeTagAdapter;
    TagAdapter colorTagAdapter;
  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
            Bundle savedInstanceState) {
        View inflaterView = inflater.inflate(R.layout.create_garment_tab, container, false);
         ///open db connection in usource
        Log.d("CreateGarmentTab.java","opening usource connection");
        
        //creating DAOs and doing temporary inserts: type
        final TagDAO t_source = new TagDAO(this.getActivity(),MySQLiteHelper.type);
        t_source.open();
        t_source.insertTag("skirt");
        t_source.insertTag("skort");
        t_source.insertTag("skarf");
        t_source.insertTag("sklirppppppp");
        t_source.insertTag("sk-sk-sk-zip");
        t_source.insertTag("sklililidap");
        t_source.insertTag("skeedaddle");
        //:color
        final TagDAO c_source = new TagDAO(this.getActivity(), MySQLiteHelper.color);
        c_source.open();
        c_source.insertTag("lavender");
        c_source.insertTag("peach");
        c_source.insertTag("matcha");
        
        //inflating edittexts and listviews
        editTextTypeTag = (EditText) inflaterView.findViewById(R.id.EditTextTypeTag);
        editTextColorTag = (EditText) inflaterView.findViewById(R.id.EditTextColorTag);
        //editTextPatternTag = (EditText) inflaterView.findViewById(R.id.EditTextPatternTag);
        //editTextMaterialTag = (EditText) inflaterView.findViewById(R.id.EditTextMaterialTag);
        
        listViewTypeTag = (HorizontalListView) inflaterView.findViewById(R.id.ListViewTypeTag);
        listViewColorTag = (HorizontalListView) inflaterView.findViewById(R.id.ListViewColorTag);
        Log.d("CreateGarmentTab.java","listviewtypetag = " + listViewTypeTag); 
        Log.d("CreateGarmentTab.java","listviewcolortag = " + listViewColorTag);
        
        //creating tagadapters
        typeTagAdapter = new TagAdapter(this.getActivity(),typetags);
        colorTagAdapter = new TagAdapter(this.getActivity(), colortags);
        //attach tagadapter to listview
        Log.d("CreateGarmentTab.java", "typetagadapter = "+ typeTagAdapter);
        listViewTypeTag.setAdapter(typeTagAdapter);
        listViewColorTag.setAdapter(colorTagAdapter);
        
        //attach textwatcher to edittext
        editTextTypeTag.addTextChangedListener(new GarmentTabTextWatcher(editTextTypeTag, t_source, typetags));
        editTextColorTag.addTextChangedListener(new GarmentTabTextWatcher(editTextColorTag, c_source, colortags));
        
        
        return inflaterView;
    }
    
    //inner class implements textwatcher
    //saves view passed in with constructor
    //uses to identify which listview to populate with suggestions
    private class GarmentTabTextWatcher implements TextWatcher {
        private TextView watchedView;
        public TagDAO tag_source;
        private GarmentTabTextWatcher(TextView view, TagDAO tag_source, ArrayList<Tag> tags) {
            this.watchedView = view;
            //passing t_source, should consider implementing textwatcher in parent class
            this.tag_source = tag_source;
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {  
            switch(watchedView.getId()) {
                case R.id.EditTextTypeTag: 
                    //Log.d("CreateGarmentTab.java","before text chenged, view: "+ watchedView);
                    
            }
        }
        
        public void onTextChanged(CharSequence s, int start, int before, int count) {
             switch(watchedView.getId()) {
                case R.id.EditTextTypeTag: 
                    //Log.d("CreateGarmentTab.java","on text chenged, view: "+ watchedView);
                    
            }
        }
        
        public void afterTextChanged(Editable s) {
            switch(watchedView.getId()) {
                case R.id.EditTextTypeTag:
                    Log.d("CreateGarmentTab.java","type text changed:"+ s.toString());
                    typetags = tag_source.selectTagWhere(s.toString());
                    typeTagAdapter.update(typetags);
                    //Log.d("CreateGarmentTab.java", "first type tag selected: " +typetags.get(0).getTag());
                    //check if empty edit text
                    break;
                case R.id.EditTextColorTag:
                    Log.d("CreateGarmentTab.java","color text changed:"+ s.toString());
                    colortags = tag_source.selectTagWhere(s.toString());
                    colorTagAdapter.update(colortags);
                    //Log.d("CreateGarmentTag.java","first color tag selected: " +colortags.get(0).getTag());
                    break;
            }        
            if (watchedView.getText().toString().trim().length() == 0) {
                        typeTagAdapter.clear();
                        colorTagAdapter.clear();
                    }
            
        }
    };
}
