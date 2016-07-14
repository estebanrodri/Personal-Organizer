package studio.developper.organizer.personalorganizer.Task;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import studio.developper.organizer.personalorganizer.Libs.EventBus;
import studio.developper.organizer.personalorganizer.Libs.GreenRobotEventBus;
import studio.developper.organizer.personalorganizer.entities.Task;

/**
 * Created by Esteban_R on 12/7/2016.
 */
public class TaskPresenterImpl implements TaskPresenter {

    private EventBus eventBus;
    private TaskView taskView;
    private TaskInteractor taskInteractor;
    private Task task;

    public TaskPresenterImpl() {
        this.eventBus = new GreenRobotEventBus();
        this.taskInteractor = new TaskInteractorImpl();
    }

    public TaskPresenterImpl(EventBus eventBus, TaskView taskView, TaskInteractor taskInteractor, Task task) {
        this.eventBus = eventBus;
        this.taskView = taskView;
        this.taskInteractor = taskInteractor;
        this.task = task;
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
        this.taskView = null;
    }

    @Override
    public List<Task> getTasks() {
        if (this.taskView != null){
            taskView.hideList();
            taskView.showProgress();
        }
        return this.taskInteractor.getTaskList();
    }

    @Override
    public boolean saveTask(Task task) {
        return this.taskInteractor.saveTask(task);
    }

    @Override
    @Subscribe
    public void onEventMainThread(TaskEvent event) {
        String errorMsg = event.getError();
        if (this.taskView != null) {
            taskView.showList();
            taskView.hideProgress();
            if (errorMsg != null) {
                this.taskView.onPasswordError(errorMsg);
            } else {
                List<Task> items = event.getPasswords();
                if (items != null && !items.isEmpty()) {
                    this.taskView.setTask(items);
                }
            }
        }
        String mensaje;
        switch (event.getEventType()) {
            case TaskEvent.onSignInError:
                //mensaje("Evento de error al ingresar");
                //onSignInError(event.getErrorMesage());
                break;
            case TaskEvent.onSaveSuccess:
                //mensaje("Evento de éxito al ingresar");
                //onSignInSuccess();
                break;
            case TaskEvent.onSignUpError:
                //mensaje("Evento de error al registrar");
                //onSignUpError(event.getErrorMesage());
                break;
            case TaskEvent.onSignUpSuccess:
                //mensaje("Evento de éxito al registrar");
                //onSignUpSuccess();
                break;
            case TaskEvent.onFailedToRecoverSession:
                //mensaje("Evento de error al recuperar sesión");
                //onFailedToRecoverSession();
                break;
        }
    }

    @Override
    public void delete(Task task){
        this.taskInteractor.delete(task);
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
