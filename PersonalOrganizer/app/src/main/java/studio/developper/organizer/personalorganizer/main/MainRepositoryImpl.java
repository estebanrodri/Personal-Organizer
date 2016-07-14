package studio.developper.organizer.personalorganizer.main;

import studio.developper.organizer.personalorganizer.Libs.EventBus;

/**
 * Created by Esteban_R on 10/7/2016.
 */
public class MainRepositoryImpl implements MainRepository{

    private EventBus eventBus;

    public MainRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void logout() {
        //firebase.logout();
    }


    private void post(int type){
        post(type, null);
    }

    private void post(int type, String error){
        MainEvent event = new MainEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }

}
