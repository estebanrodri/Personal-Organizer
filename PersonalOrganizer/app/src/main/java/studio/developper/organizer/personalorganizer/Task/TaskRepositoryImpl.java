package studio.developper.organizer.personalorganizer.Task;

import java.util.List;

import studio.developper.organizer.personalorganizer.Libs.EventBus;
import studio.developper.organizer.personalorganizer.PersonalOrganizerApp;
import studio.developper.organizer.personalorganizer.entities.Task;
import studio.developper.organizer.personalorganizer.entities.User;

/**
 * Created by Esteban_R on 12/7/2016.
 */
public class TaskRepositoryImpl implements TaskRepository{

    private final EventBus eventBus;

    public TaskRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public List<Task> getTaskList() {
        return Task.getAllTask();
    }

    @Override
    public boolean saveTask(Task task) {
        String taskName = task.getTaskName();
        String startDate = task.getStartDate().toString();
        System.out.println("****Salvando valores*****");
        boolean is_save = false;

        //UserInstance userInstance = UserInstance.getInstance("");
        User currentUser = PersonalOrganizerApp.getUser();
        //currentUser.setUser(userInstance.getUser_name()); //Obtener usuario logueado
        task.setUser(currentUser.getUser());   // Establecer enlace con llave foránea

        if (!taskName.equals("") || !startDate.equals("")) {
            try{
                task.save(); //Guardar
                System.out.println("***Guardado******");
                postEvent("Tarea Guardada");
                is_save = true;
            }catch(Exception ex){
                postEvent("No se pudo guardar");
                System.out.println("No se pudo guardar");
                System.out.println(ex);
                is_save = false;
            }
        }
        return is_save;
    }

    @Override
    public void delete(Task task) {
        if(task != null){
            task.delete();
            System.out.println("Eliminando");
        }
    }


    private void postEvent(String error) {
        TaskEvent event = new TaskEvent();
        event.setError(error);
        eventBus.post(event);
    }

    private void postEvent(List<Task> items) {
        TaskEvent event = new TaskEvent();
        event.setPasswords(items);
        eventBus.post(event);
    }
}
