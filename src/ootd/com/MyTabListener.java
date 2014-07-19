/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ootd.com;
import android.app.*;
import android.app.ActionBar.TabListener;
import android.app.ActionBar.Tab;
/**
 *
 * @author Michelle
 */
public class MyTabListener implements TabListener {
    Fragment fragment;
    
    //Constructor passes in and sets "fragment" fragment
    public MyTabListener(Fragment fragment) {
        this.fragment = fragment;
    }
    
    //Passes in "ft" FragmentTransaction
        ///and replaces this.fragment with  R.id."fragment_container"
        ///using ft.replace
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        ft.replace(R.id.fragment_container, fragment);
    }
    
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        ft.remove(fragment);
    }    
    public void onTabReselected(Tab tab, FragmentTransaction ft) {}
}
