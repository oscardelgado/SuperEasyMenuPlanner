package com.oscardelgado83.supereasymenuplanner;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.oscardelgado83.supereasymenuplanner.model.dao.SimpleData;
import com.oscardelgado83.supereasymenuplanner.model.database.DBHelper;

import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MenuWeekActivity extends ActionBarActivity implements DayMenuCardFragment.OnFragmentInteractionListener {

    private static final String LOG_TAG = MenuWeekActivity.class.getSimpleName();

    @InjectView(R.id.simpleTest)
    TextView simpleTestTV;

    private DBHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_week);
        ButterKnife.inject(this);

        doSampleDatabaseStuff("onCreate", simpleTestTV);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * Do our sample database stuff. (ORMLite first test)
     */
    private void doSampleDatabaseStuff(String action, TextView tv) {
        // get our dao
        RuntimeExceptionDao<SimpleData, Integer> simpleDao = getHelper().getSimpleDataDao();
        // query for all of the data objects in the database
        List<SimpleData> list = simpleDao.queryForAll();
        // our string builder for building the content-view
        StringBuilder sb = new StringBuilder();
        sb.append("got ").append(list.size()).append(" entries in ").append(action).append("\n");

        // if we already have items in the database
        int simpleC = 0;
        for (SimpleData simple : list) {
            sb.append("------------------------------------------\n");
            sb.append("[").append(simpleC).append("] = ").append(simple).append("\n");
            simpleC++;
        }
        sb.append("------------------------------------------\n");
        for (SimpleData simple : list) {
            simpleDao.delete(simple);
            sb.append("deleted id ").append(simple.getId()).append("\n");
            Log.i(LOG_TAG, "deleting simple(" + simple.getId() + ")");
            simpleC++;
        }

        int createNum;
        do {
            createNum = new Random().nextInt(3) + 1;
        } while (createNum == list.size());
        for (int i = 0; i < createNum; i++) {
            // create a new simple object
            long millis = System.currentTimeMillis();
            SimpleData simple = new SimpleData(millis);
            // store it in the database
            simpleDao.create(simple);
            Log.i(LOG_TAG, "created simple(" + millis + ")");
            // output it
            sb.append("------------------------------------------\n");
            sb.append("created new entry #").append(i + 1).append(":\n");
            sb.append(simple).append("\n");
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                // ignore
            }
        }

        tv.setText(sb.toString());
        Log.i(LOG_TAG, "Done with page at " + System.currentTimeMillis());
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
            databaseHelper =
                    OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return databaseHelper;
    }
}
