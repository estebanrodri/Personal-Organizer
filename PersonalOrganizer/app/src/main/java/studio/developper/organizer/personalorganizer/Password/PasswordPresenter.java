package studio.developper.organizer.personalorganizer.Password;

import java.util.List;

import studio.developper.organizer.personalorganizer.entities.Password;

/**
 * Created by Esteban_R on 11/7/2016.
 */
public interface PasswordPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    List<Password> getPasswords();
    boolean savePassword(Password password);
    void onEventMainThread(PasswordEvent event);
    void delete(Password password);
}
