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
    GridView gridViewTag;
    ArrayList<Tag> tags = new ArrayList<Tag>();
    TagAdapter tagAdapter;
    
  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
            Bundle savedInstanceState) {
        View inflaterView = inflater.inflate(R.layout.create_garment_tab, container, false);
        
         ///open db connection in usource
        Log.d("CreateGarmentTab.java","opening usource connection");
        final TagDAO t_source = new TagDAO(this.getActivity());
        t_source.open();
        t_source.insertTag("skirt");
        t_source.insertTag("skort");
        t_source.insertTag("skarf");
        t_source.insertTag("sklirppppppp");
        t_source.insertTag("sk-sk-sk-zip");
        t_source.insertTag("sklililidap");
        t_source.insertTag("skeedaddle");
        editTextTypeTag = (EditText) inflaterView.findViewById(R.id.EditTextTypeTag);
        //editTextColorTag = (EditText) inflaterView.findViewById(R.id.EditTextColorTag);
        //editTextPatternTag = (EditText) inflaterView.findViewById(R.id.EditTextPatternTag);
        //editTextMaterialTag = (EditText) inflaterView.findViewById(R.id.EditTextMaterialTag);
        
        listViewTypeTag = (HorizontalListView) inflaterView.findViewById(R.id.ListViewTypeTag);
        //gridViewTag = (GridView) inflaterView.findViewById(R.id.GridViewTag);
        Log.d("CreateGarmentTab.java","listviewtag = " + listViewTypeTag); 
        
        tagAdapter = new TagAdapter(this.getActivity(),tags);
        //attach tagadapter to listview
        Log.d("CreateGarmentTab.java", "tagadapter = "+ tagAdapter);
        listViewTypeTag.setAdapter(tagAdapter);
        //gridViewTag.setAdapter(tagAdapter);
        //attach textwatcher to edittext
        editTextTypeTag.addTextChangedListener(new GarmentTabTextWatcher(editTextTypeTag, t_source));
        
        return inflaterView;
    }
    
    //inner class implements textwatcher
    //saves view passed in with constructor
    //uses to identify which listview to populate with suggestions
    private class GarmentTabTextWatcher implements TextWatcher {
        private View watchedView;
        public TagDAO t_source;
        private GarmentTabTextWatcher(View view, TagDAO t_source) {
            this.watchedView = view;
            //passing t_source, should consider implementing textwatcher in parent class
            this.t_source = t_source;
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {  
            switch(watchedView.getId()) {
                case R.id.EditTextTypeTag: 
                    Log.d("CreateGarmentTab.java","before text chenged, view: "+ watchedView);
                    
            }
        }
        
        public void onTextChanged(CharSequence s, int start, int before, int count) {
             switch(watchedView.getId()) {
                case R.id.EditTextTypeTag: 
                    Log.d("CreateGarmentTab.java","on text chenged, view: "+ watchedView);
                    
            }
        }
        
        public void afterTextChanged(Editable s) {
            switch(watchedView.getId()) {
                case R.id.EditTextTypeTag:
                    Log.d("CreateGarmentTab.java","after text changed:"+ s.toString());
                    tags = t_source.selectTagWhere(s.toString());
        
                    tagAdapter.update(tags);
                    Log.d("CreateGarmentTab.java", "first tag selected: " + tags.get(0).getTag());
       
            }
        }
    };
}
