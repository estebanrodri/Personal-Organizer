package studio.developper.organizer.personalorganizer.Password;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import studio.developper.organizer.personalorganizer.Libs.EventBus;
import studio.developper.organizer.personalorganizer.Libs.GreenRobotEventBus;
import studio.developper.organizer.personalorganizer.entities.Password;

/**
 * Created by Esteban_R on 11/7/2016.
 */
public class PasswordPresenterImpl implements PasswordPresenter{

    private EventBus eventBus;
    private PasswordView passwordView;
    private PasswordInteractor passwordInteractor;
    private Password password;

    public PasswordPresenterImpl() {
        this.eventBus = new GreenRobotEventBus();
        this.passwordInteractor = new PasswordInteractorImpl();
    }

    public PasswordPresenterImpl(EventBus eventBus, PasswordView passwordView, PasswordInteractor passwordInteractor) {
        this.eventBus = eventBus;
        this.passwordView = passwordView;
        this.passwordInteractor = passwordInteractor;
    }

    @Override
    @Subscribe
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        this.passwordView = null;
    }

    @Override
    public boolean savePassword(Password password){
        return this.passwordInteractor.savePassword(password);
    }

    @Override
    public List<Password> getPasswords() {
        if (this.passwordView != null){
            passwordView.hideList();
            passwordView.showProgress();
        }
        return this.passwordInteractor.getPasswordItemsList();
    }

    @Override
    @Subscribe
    public void onEventMainThread(PasswordEvent event) {
        String errorMsg = event.getError();
        if (this.passwordView != null) {
            passwordView.showList();
            passwordView.hideProgress();
            if (errorMsg != null) {
                this.passwordView.onPasswordError(errorMsg);
            } else {
                List<Password> items = event.getPasswords();
                if (items != null && !items.isEmpty()) {
                    this.passwordView.setPasswords(items);
                }
            }
        }
        String mensaje;
        switch (event.getEventType()) {
            case PasswordEvent.onSignInError:
                //mensaje("Evento de error al ingresar");
                //onSignInError(event.getErrorMesage());
                break;
            case PasswordEvent.onSaveSuccess:
                //mensaje("Evento de éxito al ingresar");
                //onSignInSuccess();
                break;
            case PasswordEvent.onSignUpError:
                //mensaje("Evento de error al registrar");
                //onSignUpError(event.getErrorMesage());
                break;
            case PasswordEvent.onSignUpSuccess:
                //mensaje("Evento de éxito al registrar");
                //onSignUpSuccess();
                break;
            case PasswordEvent.onFailedToRecoverSession:
                //mensaje("Evento de error al recuperar sesión");
                //onFailedToRecoverSession();
                break;
        }
    }

    @Override
    public void delete(Password password){
        this.passwordInteractor.delete(password);
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}