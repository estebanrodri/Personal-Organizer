package studio.developper.organizer.personalorganizer.Task;

import java.util.List;

import studio.developper.organizer.personalorganizer.entities.Task;

/**
 * Created by Esteban_R on 12/7/2016.
 */
public interface TaskRepository {
    List<Task> getTaskList();
    boolean saveTask(Task task);
    void delete(Task task);

}
