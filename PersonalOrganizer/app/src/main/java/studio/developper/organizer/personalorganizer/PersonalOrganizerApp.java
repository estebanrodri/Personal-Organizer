package studio.developper.organizer.personalorganizer;

import android.app.Application;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import studio.developper.organizer.personalorganizer.entities.User;


/**
 * Created by Esteban_R on 9/7/2016.
 */
public class PersonalOrganizerApp extends Application {

    private static PersonalOrganizerApp app;
    private static User my_user;
    public static void setUser(User user) { my_user = user; }
    public static User getUser() { return my_user; }

    public static PersonalOrganizerApp getApplication() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
        //initModules();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void initDB() {
        //FlowManager.getDatabase(PersonalOrganizerDataBase.class).getWritableDatabase();
        FlowManager.init(new FlowConfig.Builder(this).build());
        /*FlowManager.init(
                new FlowConfig.Builder(this)
                        .openDatabasesOnInit(true)
                        .build());*/

    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

}
