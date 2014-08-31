package com.oscardelgado83.supereasymenuplanner.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.oscardelgado83.supereasymenuplanner.R;
import com.oscardelgado83.supereasymenuplanner.model.dao.Course;

import java.sql.SQLException;

import static com.oscardelgado83.supereasymenuplanner.model.dao.Course.CourseType.*;

/**
 * Created by oscar on 10/08/14.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "supereasymenuplanner.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 7;

    private RuntimeExceptionDao<Course, Integer> courseDao = null;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /**
     * What to do when your database needs to be created. Usually this entails creating the tables and loading any
     * initial data.
     * <p/>
     * <p>
     * <b>NOTE:</b> You should use the connectionSource argument that is passed into this method call or the one
     * returned by getConnectionSource(). If you use your own, a recursive call or other unexpected results may result.
     * </p>
     *
     * @param database         Database being created.
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DBHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Course.class);
        } catch (SQLException e) {
            Log.e(DBHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }

        // Insert initial data
        RuntimeExceptionDao<Course, Integer> dao = getCourseDao();

        dao.create(new Course(FIRST, "Sopa"));
        dao.create(new Course(SECOND, "Filetes"));
        dao.create(new Course(SECOND, "San jacobos"));
        dao.create(new Course(FIRST, "Esárragos"));
        dao.create(new Course(FIRST, "Tallarines"));
        dao.create(new Course(FIRST, "Ensalada"));
        dao.create(new Course(FIRST, "Espinacas"));
        dao.create(new Course(FIRST, "Alubias verdes"));
        dao.create(new Course(FIRST, "Macarrones"));
        dao.create(new Course(FIRST, "Arroz"));
        dao.create(new Course(FIRST, "Puré"));
        dao.create(new Course(SECOND, "Calamares"));
        dao.create(new Course(SECOND, "Pizza"));
        dao.create(new Course(SECOND, "Bakalao"));
        dao.create(new Course(FIRST, "Gazpacho"));
        dao.create(new Course(FIRST, "Patatas"));
        dao.create(new Course(FIRST, "Lentejas"));
        dao.create(new Course(FIRST, "Pimientos rellenos"));
        dao.create(new Course(SECOND, "Pechugas de pollo"));
        dao.create(new Course(SECOND, "Trucha"));
        dao.create(new Course(SECOND, "Hamburguesa"));
        dao.create(new Course(SECOND, "Pollo asado"));
        dao.create(new Course(SECOND, "Tortilla"));
        dao.create(new Course(SECOND, "Chuletas sajonia"));
        dao.create(new Course(SECOND, "Salchichas"));
        dao.create(new Course(SECOND, "Alitas de pollo"));
        dao.create(new Course(FIRST, "Garbanzos"));

        Log.i(DBHelper.class.getName(), "created new entries in onCreate");
    }

    /**
     * What to do when your database needs to be updated. This could mean careful migration of old data to new data.
     * Maybe adding or deleting database columns, etc..
     * <p/>
     * <p>
     * <b>NOTE:</b> You should use the connectionSource argument that is passed into this method call or the one
     * returned by getConnectionSource(). If you use your own, a recursive call or other unexpected results may result.
     * </p>
     *
     * @param database         Database being upgraded.
     * @param connectionSource To use get connections to the database to be updated.
     * @param oldVersion       The version of the current database so we can know what to do to the database.
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DBHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Course.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DBHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    public RuntimeExceptionDao<Course, Integer> getCourseDao() {
        if (courseDao == null) {
            courseDao = getRuntimeExceptionDao(Course.class);
        }
        return courseDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
//        simpleDao = null;
        courseDao = null;
    }
}
