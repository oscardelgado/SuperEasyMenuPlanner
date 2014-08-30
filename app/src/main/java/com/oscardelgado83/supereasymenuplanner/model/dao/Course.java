package com.oscardelgado83.supereasymenuplanner.model.dao;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by oscar on 17/08/14.
 */
public class Course {

    public enum CourseType {
        FIRST, SECOND;
    };

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    CourseType courseType;

    @DatabaseField
    String name;

    public Course() {
        // needed by ormlite
    }

    public Course(CourseType type, String name) {
        this.courseType = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseType=" + courseType +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}
