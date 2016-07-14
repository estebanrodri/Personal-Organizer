package studio.developper.organizer.personalorganizer.Login;

import studio.developper.organizer.personalorganizer.entities.User;

/**
 * Created by Esteban_R on 9/7/2016.
 */
public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImpl() {

        this.loginRepository = new LoginRepositoryImpl();
    }

    @Override
    public boolean checkAlreadyAuthenticated() {

        return loginRepository.checkAlreadyAuthenticated();
    }

    @Override
    public void doSignUp(String user, String password) {

        loginRepository.signUp(user, password);
    }

    @Override
    public void doSignIn(String user, String password) {

        loginRepository.signIn(user, password);
    }

    @Override
    public User returnLoginUser(){
        return loginRepository.returnLoginUser();
    }
}
