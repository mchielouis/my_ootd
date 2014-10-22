/*
CreateGarmentTab class:
    Fragment extension designed to be a user interface for selecting and entering
    descriptors (or "tags") to describe a garment being created. The tag categories thus defined
    are referenced from DOMs (Database object metadata) initialized in MySQLiteHelper via 
    tagDAOs (tag database access objects).
TagAdapter class:
    ArrayAdapter extension provides arraylist container for tag objects as clickable buttons,
    with functionality for adding tags to a persisting list (the collection of descriptors
    for the garment being created) and removing them.
GarmentTabTextWatcher class:
    TextWatcher extension 
 */

package ootd.com;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.text.*;
import android.util.*;
import android.view.Gravity;
import java.util.*;
/**
 *
 * @author Michelle
 */
public class CreateGarmentTab extends Fragment {

    static EditText editTextTypeTag, editTextColorTag, editTextPatternTag, editTextMaterialTag;
    static ListView listViewTypeTag, listViewSelectedTag;
    static GridView gridViewTag;
    static ArrayList<Tag> typetags = new ArrayList<Tag>();
    static ArrayList<Tag> colortags = new ArrayList<Tag>();
    static ArrayList<Tag> patterntags = new ArrayList<Tag>();
    static ArrayList<Tag> materialtags = new ArrayList<Tag>();
    static ArrayList<Tag> selectedtags = new ArrayList<Tag>();
    static ArrayList<Tag> addedtags = new ArrayList<Tag>();
    static TagAdapter typeTagAdapter, colorTagAdapter, selectedTagAdapter, addedTagAdapter;

    
  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
            Bundle savedInstanceState) {
        View inflaterView = inflater.inflate(R.layout.create_garment_tab, container, false);
         ///open db connection in usource
        Log.d("CreateGarmentTab.java","opening usource connection");
        
        //creating DAOs from DOMs in mysqlitehelper and doing temporary inserts: type
        
        final TagTypeDAO type_source = new TagTypeDAO(this.getActivity());
        type_source.open();
        type_source.insertTag("type");
        type_source.insertTag("color");
        type_source.insertTag("pattern");
        type_source.insertTag("material");
        
        final TagDAO tag_source = new TagDAO(this.getActivity());
        tag_source.open();
        tag_source.insertTag("skirt", "type");
        tag_source.insertTag("red", "color");
        tag_source.insertTag("polka dot", "pattern");
        tag_source.insertTag("linen", "material");
        //:color
//        final TagDAO c_source = new TagDAO(this.getActivity(), MySQLiteHelper.color);
//        c_source.open();
//        c_source.insertTag("cream");
//        c_source.insertTag("navy");
//        c_source.insertTag("peach");
//        //:pattern
//        final TagDAO p_source = new TagDAO(this.getActivity(), MySQLiteHelper.pattern);
//        p_source.open();
//        p_source.insertTag("polka dot");
//        p_source.insertTag("herringbone");
//        //:material
//        final TagDAO m_source = new TagDAO(this.getActivity(), MySQLiteHelper.material);
//        m_source.open();
//        m_source.insertTag("nylon");
//        m_source.insertTag("corduroy");
//        m_source.insertTag("linen");
        
        //inflating edittexts and listviews
        editTextTypeTag = (EditText) inflaterView.findViewById(R.id.EditTextTypeTag);
        //editTextColorTag = (EditText) inflaterView.findViewById(R.id.EditTextColorTag);
        //editTextPatternTag = (EditText) inflaterView.findViewById(R.id.EditTextPatternTag);
        //editTextMaterialTag = (EditText) inflaterView.findViewById(R.id.EditTextMaterialTag);
        
        listViewTypeTag = (ListView) inflaterView.findViewById(R.id.ListViewTypeTag);
        Log.d("CreateGarmentTab.java","listviewtypetag = " + listViewTypeTag); 
        
        //creating tagadapters
        typeTagAdapter = new TagAdapter(this.getActivity(),typetags);
        selectedTagAdapter = new TagAdapter(this.getActivity(), selectedtags);
        addedTagAdapter = new TagAdapter(this.getActivity(), addedtags);
        
        //attach tagadapter to listview
        Log.d("CreateGarmentTab.java", "typetagadapter = "+ typeTagAdapter);
        listViewTypeTag.setAdapter(typeTagAdapter);
        //listViewSelectedTag.setAdapter(selectedTagAdapter);
        
        //attach textwatcher to edittexts
        editTextTypeTag.addTextChangedListener(new GarmentTabTextWatcher(editTextTypeTag, tag_source, typetags));
        //editTextColorTag.addTextChangedListener(new GarmentTabTextWatcher(editTextColorTag, c_source, colortags));
        //editTextPatternTag.addTextChangedListener(new GarmentTabTextWatcher(editTextPatternTag, p_source, patterntags));
        //editTextMaterialTag.addTextChangedListener(new GarmentTabTextWatcher(editTextMaterialTag, m_source, materialtags));
        
        return inflaterView;
    }
    
    public void clearEditTexts() {
        editTextTypeTag.setText("");
        //editTextColorTag.setText("");
        //editTextMaterialTag.setText("");
        //editTextPatternTag.setText("");
        
    }
    
    //inner class tagadapter extends arrayadapter
    //provides arraylist container for tag objects
    //with functionality for adding tags to a persisting list and removing them
    public class TagAdapter extends ArrayAdapter<Tag> {
    
        private Context context;
        ArrayList<Tag> tags;
        Toast tagToast;
        TagDAO source;
        //constructor calls super arrayadapter class, intializes with tag list
        public TagAdapter(Context context, ArrayList<Tag> tags) {
            super(context, R.layout.tag_list_item, tags);
            this.context = context;
            this.tags = tags;
        }
        
        public void setContext(Context context) {
            this.context = context;
        }
        public void setSource(TagDAO source) {
            this.source = source;
        }
        public TagDAO getSource() {
            return source;
        }
        public void setTags(ArrayList<Tag> newtags) {
            this.tags = newtags;
        }

        public ArrayList<Tag> getTags() {
            return this.tags;
        }
        
        //clears, resets and adds new tags, notifies adapter that data set changed
        public void update(ArrayList<Tag> newtags) {
            this.clear();
            this.setTags(newtags);
            this.addAll(tags);
            this.notifyDataSetChanged();
        }

        protected TagAdapter getThisAdapter() {
            return this;
        }

        @Override
        public View getView(int position, View converterView, ViewGroup parent) {
            if (converterView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                converterView = inflater.inflate(R.layout.tag_list_item, parent, false);
            }
            
            //each "tag" is presented as a button 
            Button buttonTag = (Button) converterView.findViewById(R.id.Tag);
            final Tag tag = this.tags.get(position);
            if (tag != null) {
                //button text retreived from tag object at position in arraylist
                buttonTag.setText(tag.getTag());
            }

            buttonTag.setOnClickListener(new View.OnClickListener() {
                //iterates through selectedtags backwards
                //checks string equality on each tag, returns true when found or false
                private boolean containsWhere(Tag clickedtag) {
                    for (int j = selectedtags.size() - 1; j >= 0; j--) {
                        //Log.d("TagAdapter.java", "addedtag " + j + " = " + selectedtags.get(j).getTag());
                        if (selectedtags.get(j).getTag().equals(clickedtag.getTag())) {
                            //Log.d("TagAdapter.java", "found existing tag" + clickedtag.getTag());
                            return true;
                        }
                    }
                    //Log.d("TagAdapter.java", "didn't find existing tag" + clickedtag.getTag());
                    return false;
                }

                public void onClick(View v) {

                    if (!containsWhere(tag) && getThisAdapter() == typeTagAdapter) {
                        //Log.d("TagAdapter.java", "clicked adapter= " + getThisAdapter());
                        //Log.d("TagAdapter.java","selectedtags "+ selectedtags +" does not contain tag "+tag+" "+tag.getTag());
                        //add tag to selectedtags in creategarmenttab
                        selectedtags.add(tag);
                        //Log.d("TagAdapter.java", "added tag= " + tag + " to " + selectedtags);
                        //addedtags.add(tag);
                        CharSequence tagged = (CharSequence) tag.getTag();

                        //toast "tagged" at 100 in from top right
                        tagToast = Toast.makeText(context, "Tagged " + tagged + ", tap to remove", Toast.LENGTH_SHORT);
                        tagToast.setGravity(Gravity.TOP | Gravity.RIGHT, 100, 100);
                        tagToast.show();

                        //updates tagadaptere
                        //Log.d("TagAdapter.java","updating adapter " + selectedTagAdapter + " with selectedtags "+ selectedtags);
                        selectedTagAdapter.update((ArrayList<Tag>)selectedtags.clone());
                        clearEditTexts();
                        //Log.d("TagAdapter.java","selectedtags = "+selectedtags+" after update");
                        //Log.d("TagAdapter.java","tags in adapter = "+selectedTagAdapter.getTags());
                    } 
                    else if (!containsWhere(tag) && getThisAdapter() == addedTagAdapter) {
                        //insert tag into database
                        TagDAO tag_source = addedTagAdapter.getSource();
                        tag_source.open();
                        tag_source.insertTag(addedTagAdapter.getItem(0).getTag(),"type");
                         selectedtags.add(tag);
                        //Log.d("TagAdapter.java", "added tag= " + tag + " to " + selectedtags);
                        //addedtags.add(tag);
                        CharSequence tagged = (CharSequence) tag.getTag();

                        //toast "tagged" at 100 in from top right
                        tagToast = Toast.makeText(context, "Tagged " + tagged + ", tap to remove", Toast.LENGTH_SHORT);
                        tagToast.setGravity(Gravity.TOP | Gravity.RIGHT, 100, 100);
                        tagToast.show();

                        //updates tagadaptere
                        //Log.d("TagAdapter.java","updating adapter " + selectedTagAdapter + " with selectedtags "+ selectedtags);
                        selectedTagAdapter.update((ArrayList<Tag>)selectedtags.clone());
                        clearEditTexts();
                        
                    }
                    else if (containsWhere(tag) && getThisAdapter() == selectedTagAdapter) {
                        //Log.d("TagAdapter.java", "clicked adapter= " + getThisAdapter());
                        //Log.d("TagAdapter.java", "does contain");
                        //remove
                        selectedtags.remove(tag);
                        //Log.d("TagAdapter.java", "removed tag= " + tag + " from " + selectedtags);
                        CharSequence tagged = (CharSequence) tag.getTag();

                        //"removed"
                        tagToast = Toast.makeText(context, "Removed " + tagged, Toast.LENGTH_SHORT);
                        tagToast.setGravity(Gravity.TOP | Gravity.RIGHT, 100, 100);
                        tagToast.show();

                        //updates tagadapter
                        update((ArrayList<Tag>)selectedtags.clone());
                    }
                }
            });

            return converterView;
        }

    }
    //inner class implements textwatcher
    //saves view passed in with constructor
    //uses to identify which tagDAO to reference for retreiving tag list
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
                    //Log.d("CreateGarmentTab.java","type text changed:"+ s.toString());
                    typetags = tag_source.selectTagWhere(s.toString());
                    if (typetags.isEmpty()) {
                        //if no tags in database match text entry, display new tag 
                        Tag newtag = new Tag(0, s.toString());
                        addedtags.clear();
                        addedtags.add(newtag);
                        addedTagAdapter.setSource(tag_source);
                        listViewTypeTag.setAdapter(addedTagAdapter);
                        //if no tags in database match text entry, display already selected tags
                        //selectedTagAdapter.update((ArrayList<Tag>)selectedtags.clone());
                        //listViewTypeTag.setAdapter(selectedTagAdapter);
                    }    
                    else {
                        //if tags in database match text entry, populate list with matches
                        typeTagAdapter.update(typetags);
                        listViewTypeTag.setAdapter(typeTagAdapter);
                        //watchedView.setText("");
                    }
                    //Log.d("CreateGarmentTab.java", "first type tag selected: " +typetags.get(0).getTag());
                    break;
//                case R.id.EditTextColorTag:
//                    Log.d("CreateGarmentTab.java","color text changed:"+ s.toString());
//                    colortags = tag_source.selectTagWhere(s.toString());
//                    if (colortags.isEmpty()){
//                        Tag newtag = new Tag(0, s.toString());
//                        addedtags.clear();
//                        addedtags.add(newtag);
//                        addedTagAdapter.setSource(tag_source);
//                        listViewTypeTag.setAdapter(addedTagAdapter);
//                        //selectedTagAdapter.update(selectedtags);
//                        //listViewTypeTag.setAdapter(selectedTagAdapter);
//                    }
//                    else {
//                        typeTagAdapter.update(colortags);
//                        listViewTypeTag.setAdapter(typeTagAdapter);
//                    }
//                    break;
//                case R.id.EditTextPatternTag:
//                    patterntags = tag_source.selectTagWhere(s.toString());
//                    if (patterntags.isEmpty()){
//                        Tag newtag = new Tag(0, s.toString());
//                        addedtags.clear();
//                        addedtags.add(newtag);
//                        addedTagAdapter.setSource(tag_source);
//                        listViewTypeTag.setAdapter(addedTagAdapter);
//                        //selectedTagAdapter.update(selectedtags);
//                        //listViewTypeTag.setAdapter(selectedTagAdapter);
//                    }
//                    else {
//                        typeTagAdapter.update(patterntags);
//                        listViewTypeTag.setAdapter(typeTagAdapter);
//                    }
//                    break;
//                case R.id.EditTextMaterialTag:
//                    materialtags = tag_source.selectTagWhere(s.toString());
//                    if (materialtags.isEmpty()){
//                        Tag newtag = new Tag(0, s.toString());
//                        addedtags.clear();
//                        addedtags.add(newtag);
//                        addedTagAdapter.setSource(tag_source);
//                        listViewTypeTag.setAdapter(addedTagAdapter);
//                        //selectedTagAdapter.update(selectedtags);
//                        //listViewTypeTag.setAdapter(selectedTagAdapter);
//                    }
//                    else {
//                        typeTagAdapter.update(materialtags);
//                        listViewTypeTag.setAdapter(typeTagAdapter);
//                    }
                    }
            
            if (watchedView.getText().toString().trim().length() == 0) {                    
                    //if edittext is empty, display already selected tags
                    listViewTypeTag.setAdapter(selectedTagAdapter);
                    //Log.d("TagAdapter.java","showing adapter "+listViewTypeTag.getAdapter() + "with selectedtags "+ selectedtags);
                    }
        }
    };
}
