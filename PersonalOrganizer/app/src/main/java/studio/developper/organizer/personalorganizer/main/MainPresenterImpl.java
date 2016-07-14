package studio.developper.organizer.personalorganizer.main;

import org.greenrobot.eventbus.Subscribe;
import studio.developper.organizer.personalorganizer.Libs.EventBus;
import studio.developper.organizer.personalorganizer.Libs.GreenRobotEventBus;

/**
 * Created by Esteban_R on 10/7/2016.
 */
public class MainPresenterImpl implements MainPresenter{
    MainView view;
    EventBus eventBus;
    SessionInteractor sessionInteractor;

    public MainPresenterImpl(MainView view, SessionInteractor sessionInteractor) {
        this.view = view;
        this.eventBus = new GreenRobotEventBus();
        this.sessionInteractor = sessionInteractor;
    }

    public MainPresenterImpl(MainView view){
        this.eventBus = new GreenRobotEventBus();
        this.view = view;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(MainEvent event) {
        String error = event.getError();
        if (this.view != null) {
            switch (event.getType()) {
                case MainEvent.UPLOAD_INIT:
                    //view.onUploadInit();
                    break;
                case MainEvent.UPLOAD_COMPLETE:
                    //view.onUploadComplete();
                    break;
                case MainEvent.UPLOAD_ERROR:
                    //view.onUploadError(error);
                    break;
            }
        }
    }

    @Override
    public void logout() {
        //sessionInteractor.logout();
        this.onDestroy();
    }

}