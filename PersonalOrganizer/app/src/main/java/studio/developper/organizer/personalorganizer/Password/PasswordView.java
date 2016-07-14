package studio.developper.organizer.personalorganizer.Password;

import java.util.List;

import studio.developper.organizer.personalorganizer.entities.Password;

/**
 * Created by Esteban_R on 11/7/2016.
 */
public interface PasswordView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();
    void handleEventAddPassword();
    void onPasswordError(String error);
    void setPasswords(List<Password> items);

}
