package studio.developper.organizer.personalorganizer.entities;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
//import java.util.Timer;
//import java.sql.Date;
//import java.sql.Time;
import java.util.List;
import studio.developper.organizer.personalorganizer.DataBase.PersonalOrganizerDataBase;
import studio.developper.organizer.personalorganizer.PersonalOrganizerApp;

/**
 * Created by Esteban_R on 9/7/2016.
 */

@Table(database = PersonalOrganizerDataBase.class)
public class Task extends BaseModel {

    /*
        Nombre Task Name
        Descripción Description
        Fecha Inicial  Start Date
        Hora Inicial  Start Time
        Fecha Vencimiento  Due Date
        Hora Vencimiento End Time

        Fecha Creación Created Date
        Estado Status  (Abierta, En Progreso, Completa, Atrasada) Open, In progress, Complete, Delated
        Nivel de Progreso (10% en 10%) progrees
        Categoría
    */

    @SerializedName("id")
    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    private String taskName;

    @Column
    private String description;

    @Column
    private String startDate;

    @Column
    private String startTime;

    @Column
    private String dueDate;

    @Column
    private String endTime;

    @Column
    private String user;

    //@Column
    //@ForeignKey(saveForeignKeyModel = true)
    //User user;

    /*Implementación para siguientes versiones*/
    //@Column
    //private String status;
    //@Column
    //private int progress;
    //@Column
    //private String category;
    //@Column
    //private String createdDate;

    public Task() {
    }

    public Task(String taskName, String description, String startDate,
                String startTime, String dueDate, String endTime) {
        this.taskName = taskName;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.dueDate = dueDate;
        this.endTime = endTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /* public String getDateFormatted(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd,yyyy"); // HH:mm
        return simpleDateFormat.format(date);
    }

    //String horaActual = new SimpleDateFormat("HH:mm:ss").format(Calendar.getIn stance().getTime());
    public String getTimeFormatted(Date time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(time);
    }*/

    /*Devolver todas las tareas que pertenecen al usuario*/
    public static List<Task> getAllTask(){

        //UserInstance userInstance = UserInstance.getInstance("");
        User currentUser = PersonalOrganizerApp.getUser();

         // Usuario actual
        List<Task> taskList;
        try{
            taskList = new Select().from(Task.class).
                where(Task_Table.user.is(currentUser.getUser())).queryList();

        }catch(NullPointerException exc){
            taskList = new ArrayList<Task>();
        }
        return taskList;
    }
}