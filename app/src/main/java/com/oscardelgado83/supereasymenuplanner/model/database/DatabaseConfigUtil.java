package com.oscardelgado83.supereasymenuplanner.model.database;

import android.view.Menu;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import com.oscardelgado83.supereasymenuplanner.model.dao.Course;
import com.oscardelgado83.supereasymenuplanner.model.dao.MenuDay;
import com.oscardelgado83.supereasymenuplanner.model.dao.Week;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by oscar on 10/08/14.
 * see http://stackoverflow.com/a/17332546/1464013
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    public static final Class<?>[] MODELS = {
            Course.class,
            MenuDay.class,
            Week.class
    };

    /**
     * This must be called as a stand alone app by a JRE instance and NOT by android.
     * It will create an ormlite config file that will make the reflection for annotation and more easier and faster.
     * <p/>
     * Make sure you have pathOfProject/build/classes/debug in your class path when running!
     * <p/>
     * Working class path:
     * <code>-classpath /usr/lib/jvm/java-7-oracle/lib/jconsole.jar:/usr/lib/jvm/java-7-oracle/lib/dt.jar:/usr/lib/jvm/java-7-oracle/lib/sa-jdi.jar:/usr/lib/jvm/java-7-oracle/lib/tools.jar:/usr/lib/jvm/java-7-oracle/lib/javafx-doclet.jar:/usr/lib/jvm/java-7-oracle/lib/ant-javafx.jar:/usr/lib/jvm/java-7-oracle/lib/javafx-mx.jar:/home/martin/workspace/idea/Project/MainProject/libs/ormlite-android-4.45.jar:/home/martin/workspace/idea/Project/MainProject/libs/ormlite-core-4.45.jar:/opt/android-studio/lib/idea_rt.jar:/home/martin/workspace/idea/Project/MainProject/build/classes/debug:/opt/android/platforms/android-16</code>
     *
     * @param args none will be used.
     * @throws Exception
     */
    public static void main(String[] args) throws SQLException, IOException {
        writeConfigFile(new File("app/src/main/res/raw/ormlite_config.txt"), MODELS);
    }
}
