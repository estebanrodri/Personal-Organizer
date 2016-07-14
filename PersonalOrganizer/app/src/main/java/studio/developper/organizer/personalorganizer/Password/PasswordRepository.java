package studio.developper.organizer.personalorganizer.Password;

import java.util.List;

import studio.developper.organizer.personalorganizer.entities.Password;

/**
 * Created by Esteban_R on 11/7/2016.
 */
public interface PasswordRepository {
    List<Password> getPasswords();
    boolean savePassword(Password password);
    void delete(Password password);
}
