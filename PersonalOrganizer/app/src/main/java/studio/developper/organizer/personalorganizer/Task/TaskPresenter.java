package studio.developper.organizer.personalorganizer.Task;

import java.util.List;

import studio.developper.organizer.personalorganizer.entities.Task;

/**
 * Created by Esteban_R on 12/7/2016.
 */
public interface TaskPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    List<Task> getTasks();
    boolean saveTask(Task task);
    void onEventMainThread(TaskEvent event);
    void delete(Task task);
}

