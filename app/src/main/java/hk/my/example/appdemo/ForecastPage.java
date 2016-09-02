package hk.my.example.appdemo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;


public class ForecastPage extends AppCompatActivity implements MainFragment.Callbacks {

    private final String CHECKED_MENU_ITEM_ID = "HomePage.CHECKED_MENU_ITEM_ID";
    protected SharedFunction sharedFunction;
    private int checkedMenuItemId = 0;
    private NavigationView navigationView;
    private Menu navigationViewMenu;
    private MenuItem homeMenuItem;
    private MenuItem checkedMenuItem;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private BottomBar bottomBar;
    private String bbAction;
    private int resourceId;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_page);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                // Open the NavigationView drawer when the home icon is clicked
                drawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                // TODO: Implement settings
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // you should use the onPause() method to write any persistent data (such as user edits) to storage
    // the method onSaveInstanceState(Bundle) is called before placing the activity in such a background state,
    // allowing you to save away any dynamic instance state in your activity into the given Bundle,
    // to be later received in onCreate(Bundle) if the activity needs to be re-created.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CHECKED_MENU_ITEM_ID, checkedMenuItemId);

        // Call the super class so that it can save the view hierarchy state
        super.onSaveInstanceState(outState);
    }

    @Override
    public void passDataToActivity(String data) {

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
