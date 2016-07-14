package studio.developper.organizer.personalorganizer.Password;

import java.util.List;

import studio.developper.organizer.personalorganizer.Libs.EventBus;
import studio.developper.organizer.personalorganizer.Libs.GreenRobotEventBus;
import studio.developper.organizer.personalorganizer.entities.Password;

/**
 * Created by Esteban_R on 11/7/2016.
 */
public class PasswordInteractorImpl implements PasswordInteractor{

    private PasswordRepository passwordRepository;
    private EventBus eventbus = new GreenRobotEventBus();

    public PasswordInteractorImpl() {
        passwordRepository = new PasswordRepositoryImpl(eventbus);
    }

    public PasswordInteractorImpl(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    @Override
    public List<Password> getPasswordItemsList() {
        return this.passwordRepository.getPasswords();
    }

    public boolean savePassword(Password password){
        return this.passwordRepository.savePassword(password);
    }

    @Override
    public void delete(Password password){
        this.passwordRepository.delete(password);
    }
}
