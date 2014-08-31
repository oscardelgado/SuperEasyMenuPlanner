package com.oscardelgado83.supereasymenuplanner.model.dao;

import com.j256.ormlite.field.DatabaseField;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by oscar on 31/08/14.
 */
public class Week {

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField(foreign = true)
    private List<MenuDay> days;

    @Override
    public String toString() {
        return "Week{" + "id=" + id + ", days=" + days + '}';
    }

    public String printWeekDescription() {

        String message = "Semana del %s al %s";

        Date firstDate = days.get(0).getMenuDate();
        Date lastDate = days.get(6).getMenuDate();

        String firstMonth = new SimpleDateFormat("M", Locale.getDefault()).format(firstDate);
        String lastMonth = new SimpleDateFormat("M", Locale.getDefault()).format(lastDate);

        String firstDay;
        if (firstMonth.equals(lastMonth)) {
            firstDay = new SimpleDateFormat("d", Locale.getDefault()).format(firstDate);
        } else {
            firstDay = new SimpleDateFormat("d 'de' MMMM", Locale.getDefault()).format(firstDate);
        }

        String lastDay = new SimpleDateFormat("d 'de' MMMM", Locale.getDefault()).format(lastDate);

        return String.format(
                message,
                firstDay,
                lastDay);
    }

    public void setDays(List<MenuDay> days) {
        this.days = days;
    }
}
