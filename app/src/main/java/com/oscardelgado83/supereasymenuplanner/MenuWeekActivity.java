package com.oscardelgado83.supereasymenuplanner;

import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.oscardelgado83.supereasymenuplanner.model.dao.Course;
import com.oscardelgado83.supereasymenuplanner.model.database.DBHelper;

import org.apache.commons.lang3.text.WordUtils;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;


public class MenuWeekActivity extends ActionBarActivity implements DayMenuCardFragment.OnFragmentInteractionListener {

    private static final String LOG_TAG = MenuWeekActivity.class.getSimpleName();

    private DBHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_week);
        ButterKnife.inject(this);

        List<Course> allCourses = getHelper().getCourseDao().queryForAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_week, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    private DBHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        DayMenuCardFragment fr = (DayMenuCardFragment) fragment;

        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        String[] weekdays = symbols.getWeekdays();
        String weekday = null;

        switch (fragment.getId()) {
            case R.id.fragment:
                weekday = weekdays[2]; // It starts on Sun, and 0 is empty
                break;
            case R.id.fragment2:
                weekday = weekdays[3];
                break;
            case R.id.fragment3:
                weekday = weekdays[4];
                break;
            case R.id.fragment4:
                weekday = weekdays[5];
                break;
            case R.id.fragment5:
                weekday = weekdays[6];
                break;
            default:
                break;
        }
        fr.setWeekDayString(WordUtils.capitalize(weekday));
    }
}
