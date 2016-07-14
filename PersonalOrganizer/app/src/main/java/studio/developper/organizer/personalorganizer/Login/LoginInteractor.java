package studio.developper.organizer.personalorganizer.Login;

import studio.developper.organizer.personalorganizer.entities.User;

/**
 * Created by Esteban_R on 9/7/2016.
 */
public interface LoginInteractor {

    boolean checkAlreadyAuthenticated();
    void doSignUp(String user, String password);
    void doSignIn(String user, String password);
    User returnLoginUser();

}
