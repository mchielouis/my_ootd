/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ootd.com;


import android.os.Bundle;
import android.view.*;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
/**
 *
 * @author Phil
 */
public class PopUpEditText {
    PopupWindow popupEditText;
    Button buttonEditText;
    EditText editText;
    String text;
    
    public PopUpEditText(ViewGroup container, LayoutInflater inflater){
        View popupView = inflater.inflate(R.layout.user_edittext_popup, container, false);
        popupEditText = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        buttonEditText = (Button)popupView.findViewById(R.id.ButtonEditText);
        buttonEditText.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 //
                 //
             }
         });
    }
    
}
