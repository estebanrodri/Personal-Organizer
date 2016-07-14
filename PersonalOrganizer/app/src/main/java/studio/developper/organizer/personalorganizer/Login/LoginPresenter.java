package studio.developper.organizer.personalorganizer.Login;

/**
 * Created by Esteban_R on 9/7/2016.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void onEventMainThread(LoginEvent event);
    void validateLogin(String user, String password);
    void registerNewUser(String user, String password);
}
