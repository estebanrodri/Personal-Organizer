package studio.developper.organizer.personalorganizer.Login;

/**
 * Created by Esteban_R on 9/7/2016.
 */
public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
    void setUserName(String userName);

}
