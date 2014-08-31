package com.oscardelgado83.supereasymenuplanner;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.oscardelgado83.supereasymenuplanner.model.dao.Course;
import com.oscardelgado83.supereasymenuplanner.model.dao.MenuDay;
import com.oscardelgado83.supereasymenuplanner.model.dao.Week;
import com.oscardelgado83.supereasymenuplanner.model.database.DBHelper;

import org.apache.commons.lang3.text.WordUtils;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MenuWeekActivity extends ActionBarActivity implements DayMenuCardFragment.OnFragmentInteractionListener {

    private static final String LOG_TAG = MenuWeekActivity.class.getSimpleName();

    private DBHelper databaseHelper = null;

    @InjectView(R.id.dateTV)
    TextView dateTV;

    Week currentWeek;
    Calendar currentDay;

    private List<Course> availableCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_week);
        ButterKnife.inject(this);

        currentDay = GregorianCalendar.getInstance();
        currentWeek = initializeWeek();
        dateTV.setText(currentWeek.printWeekDescription());
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
            case R.id.fragment6:
                weekday = weekdays[7];

//                List<Course> firstCurses = getHelper().getCourseDao().queryForEq("courseType", Course.CourseType.FIRST); //TODO
//                List<Course> secondCurses = getHelper().getCourseDao().queryForEq("courseType", Course.CourseType.SECOND); //TODO
//                Course firstCourse = firstCurses.get(0); //TODO
//                Course secondCourse = secondCurses.get(1); //TODO
//                fr.setFirstCourse(firstCourse);
//                fr.setSecondCourse(secondCourse);

                break;
            case R.id.fragment7:
                weekday = weekdays[1];
                break;
            default:
                break;
        }
        fr.setWeekDayString(WordUtils.capitalize(weekday));

    }

    private Week initializeWeek() {
        Week currentWeek = new Week();
        List<MenuDay> days = new ArrayList<MenuDay>();
//        int offset = currentDay.get(Calendar.DAY_OF_WEEK) - currentDay.getFirstDayOfWeek();
        int offset = currentDay.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
        if (offset < 0) {
            offset += 7;
        }
        for (int i = 0; i < 7; i++) {
            MenuDay newDay = new MenuDay();
            Calendar cal = new GregorianCalendar(
                    currentDay.get(Calendar.YEAR),
                    currentDay.get(Calendar.MONTH),
                    currentDay.get(Calendar.DAY_OF_MONTH) - offset + i);
            newDay.setMenuDate(new Date(cal.getTimeInMillis()));
            days.add(newDay);
        }
        currentWeek.setDays(days);
        return currentWeek;
    }

    @OnClick(R.id.left_arrowBT)
    public void previous() {
        currentDay.add(Calendar.DATE, -7);
        refreshWeek();
    }

    @OnClick(R.id.right_arrowBT)
    public void next() {
        currentDay.add(Calendar.DATE, 7);
        refreshWeek();
    }

    @OnClick(R.id.todayBT)
    public final void goToToday() {
        currentDay = GregorianCalendar.getInstance();
        refreshWeek();
    }

    private void refreshWeek() {
//        EntityManager em = emf.createEntityManager();

//        try {
//            findPersistedWeek(em);
//        } catch (NoResultException e) {
//            logger.info("no result. CurrentWeek={}", currentWeek);
        currentWeek = initializeWeek();
        dateTV.setText(currentWeek.printWeekDescription());
//        } finally {
//            em.close();
//        }

        availableCourses = getHelper().getCourseDao().queryForAll();
    }
}
