package hk.my.example.appdemo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;


public class HomePage extends AppCompatActivity implements MainFragment.Callbacks {

    private final String CHECKED_MENU_ITEM_ID = "HomePage.CHECKED_MENU_ITEM_ID";
    private String bbAction;
    private int checkedMenuItemId = 0;
    private int resourceId;

    private NavigationView navigationView;
    private Menu navigationViewMenu;
    private MenuItem homeMenuItem;
    private MenuItem checkedMenuItem;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private BottomBar bottomBar;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    protected SharedFunction sharedFunction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_page);

        // Initiate the widgets
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        bottomBar = (BottomBar) findViewById(R.id.bottom_bar);

        // Initiate the fragment manager
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        // content_container is a fragment layout
        if (findViewById(R.id.content_container) != null) {
            if (savedInstanceState != null) {
                // Restore the previously checked menu item
                checkedMenuItemId = savedInstanceState.getInt(CHECKED_MENU_ITEM_ID);
            } else {
                // Return the FragmentManager(MainFragment) for interacting with fragments associated with this activity --> (content_container)
                fragmentTransaction.add(R.id.content_container, MainFragment.newInstance("home"));
                fragmentTransaction.commit();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Initiate the navigation menu
        resourceId = R.id.nav_gowise;
        sharedFunction.SetNavigationView(checkedMenuItemId, resourceId, navigationView, navigationViewMenu, homeMenuItem, checkedMenuItem);

        // Set the navigation listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // Set the selected menu item to checked
                menuItem.setChecked(true);
                checkedMenuItemId = menuItem.getItemId();

                bbAction = sharedFunction.BarAction(checkedMenuItemId);
                Log.println(Log.ERROR, "Tab ID: ", checkedMenuItemId + "   Action: " + bbAction);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, MainFragment.newInstance(bbAction));
                fragmentTransaction.commit();

                // Close the drawer
                drawer.closeDrawers();

                return true;
            }
        });

        // Toolbar activity
        toolbar = (Toolbar) findViewById(R.id.ab_toolbar);
        setSupportActionBar(toolbar);

        // ActionBar activity
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // BottomBar activity
        bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        // Bottom bar tab select listener
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Log.println(Log.ERROR, "Tab ID before: ", tabId + "   Action: " + bbAction);
                bbAction = sharedFunction.BarAction(tabId);
                Log.println(Log.ERROR, "Tab ID after: ", tabId + "   Action: " + bbAction);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, MainFragment.newInstance(bbAction));
                fragmentTransaction.commit();
                Toast.makeText(getApplicationContext(), "Tab ID: " + tabId + "   Action: " + bbAction, Toast.LENGTH_SHORT).show();
            }
        });

        // Bottom bar tab re-select listener
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                bbAction = sharedFunction.BarAction(tabId);
                Log.println(Log.ERROR, "Tab ID after: ", tabId + "   Action: " + bbAction);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, MainFragment.newInstance(bbAction));
                fragmentTransaction.commit();
                Toast.makeText(getApplicationContext(), "Unselected Tab ID: " + tabId + "   Action: " + bbAction, Toast.LENGTH_SHORT).show();
            }
        });

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
