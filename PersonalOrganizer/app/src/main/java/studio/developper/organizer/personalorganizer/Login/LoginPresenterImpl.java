package studio.developper.organizer.personalorganizer.Login;

import org.greenrobot.eventbus.Subscribe;

import studio.developper.organizer.personalorganizer.Libs.EventBus;
import studio.developper.organizer.personalorganizer.Libs.GreenRobotEventBus;

/**
 * Created by Esteban_R on 9/7/2016.
 */
public class LoginPresenterImpl implements LoginPresenter {

    EventBus eventBus;
    LoginView loginView;
    LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void onCreate(){
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser(){
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }

        if (loginInteractor.checkAlreadyAuthenticated()){
            loginView.enableInputs();
            loginView.hideProgress();
        }
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.onSignInError:
                mensaje("Evento de error al ingresar");
                onSignInError(event.getErrorMesage());
                break;
            case LoginEvent.onSignInSuccess:
                mensaje("Evento de éxito al ingresar");
                onSignInSuccess();
                break;
            case LoginEvent.onSignUpError:
                mensaje("Evento de error al registrar");
                onSignUpError(event.getErrorMesage());
                break;
            case LoginEvent.onSignUpSuccess:
                mensaje("Evento de éxito al registrar");
                onSignUpSuccess();
                break;
            case LoginEvent.onFailedToRecoverSession:
                mensaje("Evento de error al recuperar sesión");
                onFailedToRecoverSession();
                break;
        }
    }

    public void mensaje(String msj){
        System.out.println("*********");
        System.out.println(msj);
        System.out.println("*********");
    }

    @Override
    public void validateLogin(String user, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(user, password);
    }

    @Override
    public void registerNewUser(String user, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignUp(user, password);
    }

    private void setUserName(){
        String user_name = loginInteractor.returnLoginUser().getUser();
        loginView.setUserName(user_name);

    }

    private void onSignInSuccess() {
        if (loginView != null) {
            setUserName();
            loginView.navigateToMainScreen();
        }
    }

    private void onSignUpSuccess() {
        if (loginView != null) {
            System.out.println("Registrado");
            loginView.newUserSuccess();
            setUserName();
            loginView.navigateToMainScreen();
        }
    }

    private void onSignInError(String error) {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSignUpError(String error) {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }

    private void onFailedToRecoverSession() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
        }
    }
}
