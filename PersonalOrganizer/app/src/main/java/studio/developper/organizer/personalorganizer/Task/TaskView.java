package studio.developper.organizer.personalorganizer.Task;

import java.util.List;

import studio.developper.organizer.personalorganizer.entities.Task;

/**
 * Created by Esteban_R on 11/7/2016.
 */
public interface TaskView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();
    void handleEventAddTask();
    void onPasswordError(String error);
    void setTask(List<Task> items);
}
