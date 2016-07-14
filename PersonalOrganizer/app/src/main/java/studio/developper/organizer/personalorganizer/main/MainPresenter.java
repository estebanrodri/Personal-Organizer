package studio.developper.organizer.personalorganizer.main;

/**
 * Created by Esteban_R on 10/7/2016.
 */
public interface MainPresenter {

    void onCreate();
    void onDestroy();
    void logout();
    void onEventMainThread(MainEvent event);

}
