package studio.developper.organizer.personalorganizer.Libs;

/**
 * Created by Esteban_R on 9/7/2016.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);

}
