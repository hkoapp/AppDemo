package hk.my.example.appdemo;

import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class SharedFunction {

    public static void SetNavigationView(int checkedMenuItemId, int resourceID, NavigationView navigationView, Menu navigationViewMenu, MenuItem homeMenuItem, MenuItem checkedMenuItem) {
        if (navigationView != null) {
            navigationViewMenu = navigationView.getMenu();

            if (checkedMenuItemId == 0) {
                // Check the a menu item as the home menu item
                homeMenuItem = navigationViewMenu.findItem(resourceID);
                homeMenuItem.setChecked(true);
                checkedMenuItemId = homeMenuItem.getItemId();
            } else {
                // Restore the checked menu item from savedInstanceState
                checkedMenuItem = navigationViewMenu.findItem(checkedMenuItemId);
                checkedMenuItem.setChecked(true);
            }
        }
    }

    public static String BottomBarAction(@IdRes int tabId) {
        String activity = "home";
        switch (tabId) {
            case R.id.tab_home:
                activity = "home";
                break;
            case R.id.tab_weather:
                activity = "weather";
                break;
            case R.id.tab_warning:
                activity = "warning";
                break;
            case R.id.tab_information:
                activity = "information";
                break;
            case R.id.tab_service:
                activity = "service";
                break;
        }
        Log.println(Log.ERROR, "Activity: ", activity);
        return activity;
    }

}
