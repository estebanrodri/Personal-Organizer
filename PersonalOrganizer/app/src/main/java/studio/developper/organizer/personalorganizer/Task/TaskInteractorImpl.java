package studio.developper.organizer.personalorganizer.Task;

import java.util.List;

import studio.developper.organizer.personalorganizer.Libs.EventBus;
import studio.developper.organizer.personalorganizer.Libs.GreenRobotEventBus;
import studio.developper.organizer.personalorganizer.entities.Task;

/**
 * Created by Esteban_R on 12/7/2016.
 */
public class TaskInteractorImpl implements TaskInteractor{

    private TaskRepository taskRepository;
    private EventBus eventbus = new GreenRobotEventBus();

    public TaskInteractorImpl() {
        this.taskRepository = new TaskRepositoryImpl(eventbus);
    }

    public TaskInteractorImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTaskList() {
        return this.taskRepository.getTaskList();
    }

    @Override
    public boolean saveTask(Task task) {
        return this.taskRepository.saveTask(task);
    }

    public void delete(Task task){
        this.taskRepository.delete(task);
    }
}
