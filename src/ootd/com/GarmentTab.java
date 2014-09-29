/*
Fragment extension displays collection of garments for currently selected closet.
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
 * @author Michelle
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
