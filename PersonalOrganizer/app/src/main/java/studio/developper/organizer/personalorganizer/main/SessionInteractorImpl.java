package studio.developper.organizer.personalorganizer.main;

/**
 * Created by Esteban_R on 10/7/2016.
 */
public class SessionInteractorImpl implements SessionInteractor {
    MainRepository repository;

    public SessionInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logout() {
        repository.logout();
    }

}
