package studio.developper.organizer.personalorganizer.Task;

import java.util.List;

import studio.developper.organizer.personalorganizer.entities.Task;

/**
 * Created by Esteban_R on 12/7/2016.
 */
public class TaskEvent {
    private String error;
    private List<Task> tasks;
    public final static int onSignInError = 0;
    public final static int onSignUpError = 1;
    public final static int onSaveSuccess = 2;
    public final static int onSignUpSuccess = 3;
    public final static int onFailedToRecoverSession = 4;
    private int eventType;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Task> getPasswords() {
        return tasks;
    }

    public void setPasswords(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getEventType() {
        return eventType;
    }
}
