/*
Activity extension sets up action bar and tabs.
 */

package ootd.com;
import android.app.Fragment;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.util.*;
/**
 *
 * C:\Users\Phil\Documents\NetBeansProjects\my_ootd\bin\my_ootd-debug.apk
 */
public class MainTabActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    TextView textviewMenuItemUsername;
    Menu actionBarMenu;
    String currentUser = "User";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ToDo add your GUI initialization code here 
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.main);
        setupActionBar();
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setMenu(menu);
        MenuInflater menuinflater = getMenuInflater();
        menuinflater.inflate(R.menu.action_bar_menu, menu);
        View mview = (View) menu.findItem(R.id.TextviewUsername).getActionView();
        textviewMenuItemUsername = (TextView) mview.findViewById(R.id.ActionBarUsername); 
        textviewMenuItemUsername.setText(currentUser);
        Log.d("MainTabActivity.java","textviewMenuUsername="+textviewMenuItemUsername.getText().toString());
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override 
    public boolean onPrepareOptionsMenu(Menu menu) {
        //MenuItem textviewMenuItem = menu.findItem(R.id.TextviewUsername);
        //textviewMenuItemUsername = (TextView) textviewMenuItem.getActionView();
        return super.onPrepareOptionsMenu(menu);
    }
    
    private void setupActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowCustomEnabled(true);
        //actionBar.setDisplayShowTitleEnabled(false);
        //Declare tabs in action bar
        ActionBar.Tab userTab, closetTab, garmentTab, createGarmentTab;
        
        //instantiate Tab classes
        Fragment fragmentUser = new UserTab();
        Fragment fragmentCloset = new ClosetTab();
        Fragment fragmentGarment = new GarmentTab();
        Fragment fragmentCreateGarment = new CreateGarmentTab();
     
        //action bar tab indicators
        userTab = actionBar.newTab().setText("You");
        closetTab = actionBar.newTab().setText("Your Closets");
        garmentTab = actionBar.newTab().setText("Your Clothes");
        createGarmentTab = actionBar.newTab().setText("Add Garments");
        
        userTab.setTabListener(new MyTabListener(fragmentUser));
        closetTab.setTabListener(new MyTabListener(fragmentCloset));
        garmentTab.setTabListener(new MyTabListener(fragmentGarment));
        createGarmentTab.setTabListener(new MyTabListener(fragmentCreateGarment));
        
        actionBar.addTab(userTab);
        actionBar.addTab(closetTab);
        actionBar.addTab(garmentTab);
        actionBar.addTab(createGarmentTab);
    }
    //sets menu item, for manipulating menu during runtime
    private void setMenu(Menu menu) {
        this.actionBarMenu = menu;
    }
    
    //sets usernametextview, to be called during runtime
    public void setMenuItem(String username) {
        currentUser = username;
    }
//http://www.linux.com/learn/tutorials/761642-android-app-development-for-beginners-navigation-with-tabs
}
