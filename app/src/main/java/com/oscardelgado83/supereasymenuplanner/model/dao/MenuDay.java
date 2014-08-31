package com.oscardelgado83.supereasymenuplanner.model.dao;

import com.j256.ormlite.field.DatabaseField;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by oscar on 31/08/14.
 */
public class MenuDay {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DatabaseField(generatedId = true)
    private Long id;
    private Date menuDate;

//    @ManyToOne
    @DatabaseField(foreign = true)
    private Course firstCourse;
//    @ManyToOne
    @DatabaseField(foreign = true)
    private Course secondCourse;

    /**
     * Get the value of secondCourse
     *
     * @return the value of secondCourse
     */
    public Course getSecondCourse() {
        return secondCourse;
    }

    /**
     * Set the value of secondCourse
     *
     * @param secondCourse new value of secondCourse
     */
    public void setSecondCourse(Course secondCourse) {
        this.secondCourse = secondCourse;
    }


    /**
     * Get the value of firstCourse
     *
     * @return the value of firstCourse
     */
    public Course getFirstCourse() {
        return firstCourse;
    }

    /**
     * Set the value of firstCourse
     *
     * @param firstCourse new value of firstCourse
     */
    public void setFirstCourse(Course firstCourse) {
        this.firstCourse = firstCourse;
    }


    /**
     * Get the value of menuDate
     *
     * @return the value of menuDate
     */
    public Date getMenuDate() {
        return menuDate;
    }

    /**
     * Set the value of menuDate
     *
     * @param menuDate new value of menuDate
     */
    public void setMenuDate(Date menuDate) {
        this.menuDate = menuDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuDay)) {
            return false;
        }
        MenuDay other = (MenuDay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MenuDay{" + "id=" + id + ", menuDate=" + menuDate + ", firstCourse=" + firstCourse + ", secondCourse=" + secondCourse + "}\n";
    }

    public String getWeekDayName() {
        Format format =  new SimpleDateFormat("EEEE", Locale.getDefault());
        return format.format(menuDate);
    }
}
