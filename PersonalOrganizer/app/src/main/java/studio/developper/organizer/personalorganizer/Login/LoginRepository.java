package studio.developper.organizer.personalorganizer.Login;

import studio.developper.organizer.personalorganizer.entities.User;

/**
 * Created by Esteban_R on 9/7/2016.
 */
public interface LoginRepository {
    void signUp(final String user, final String password);
    void signIn(String user, String password);
    boolean checkAlreadyAuthenticated();
    User returnLoginUser();

}
