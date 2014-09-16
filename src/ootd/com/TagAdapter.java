/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Phil
 */
public class TagAdapter extends ArrayAdapter<Tag> {
    
    private Context context;
    ArrayList<Tag> tags;
    
    public TagAdapter(Context context, ArrayList<Tag> tags) {
        super(context, R.layout.tag_list_item, tags);
        this.context = context;
        this.tags = tags;
    }
    
    public void setContext(Context context) {
        this.context=context;
    }
    public void setTags(ArrayList<Tag> newtags) {
        this.tags= newtags;
    }
    
    public void update(ArrayList<Tag> newtags) {
        this.clear();
        this.setTags(newtags);
        this.addAll(tags);
        this.notifyDataSetChanged();
    }
    
    @Override
    public View getView(int position, View converterView, ViewGroup parent) {
        if (converterView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            converterView = inflater.inflate(R.layout.tag_list_item, parent, false);
        }
            Log.d("TagAdapter.java","converterView = " + converterView);
            Button buttonTag = (Button) converterView.findViewById(R.id.Tag);
            Tag tag = this.tags.get(position);
            
            if (tag!=null) {
                buttonTag.setText(tag.getTag());
            }
            
        return converterView;
    }
}
