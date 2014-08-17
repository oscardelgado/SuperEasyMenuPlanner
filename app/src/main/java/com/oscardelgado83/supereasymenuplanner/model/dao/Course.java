package com.oscardelgado83.supereasymenuplanner.model.dao;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by oscar on 17/08/14.
 */
public class Course {

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    String name;

    public Course() {
        // needed by ormlite
    }

    public Course(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
