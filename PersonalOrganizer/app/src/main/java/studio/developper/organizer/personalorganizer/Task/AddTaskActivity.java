package studio.developper.organizer.personalorganizer.Task;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import studio.developper.organizer.personalorganizer.R;
import studio.developper.organizer.personalorganizer.entities.Task;
import studio.developper.organizer.personalorganizer.main.OrganizerMainActivity;

public class AddTaskActivity extends AppCompatActivity {

    Task task;
    TaskPresenter taskPresenter;
    @Bind(R.id.container_add_task)
    LinearLayout container;
    @Bind(R.id.txtTaskName)
    EditText txtTaskName;
    @Bind(R.id.txtEndDate)
    EditText txtEndDate;
    @Bind(R.id.txtEndTime)
    EditText txtEndTime;
    @Bind(R.id.txtDescription)
    EditText txtDescription;
    @Bind(R.id.txtStartDate)
    EditText txtStartDate;
    @Bind(R.id.txtStartTime)
    EditText txtStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);

        taskPresenter = new TaskPresenterImpl();
    }

    @OnClick(R.id.task_save)
    public void addTask() {
        String task_name = txtTaskName.getText().toString().trim();
        String start_date = txtStartDate.getText().toString().trim();
        String start_time = txtStartTime.getText().toString().trim();
        String endDate = txtEndDate.getText().toString().trim();
        String endTime = txtEndTime.getText().toString().trim();
        String description = txtDescription.getText().toString().trim();


        System.out.println("*****Task Adding****");
        System.out.println(task_name + " " + start_date + " " + start_time + " " + description);

        if (!task_name.equals("") && checkDate(start_date) && checkDate(endDate)
                && checkTime(start_time) && checkTime(endTime)) {

            task = new Task(task_name, description, start_date, start_time,
                    endDate, endTime);
            boolean is_save = taskPresenter.saveTask(task);

            if (is_save) {
                //Redirigir hacia la actividad principal
                startActivity(new Intent(this, OrganizerMainActivity.class));
                System.out.println("*****Guardado****");
                String msg = "Password Save";
                Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
            } else {
                String msg = "We Have a Problem";
                Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
            }

        } else {
            String msg = "Revise que los valores son correctos";
            Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
        }
    }


    public boolean checkDate(String date){
        date = date.trim();
        String values[] = date.split("/");

        //Separar valores por dos puntos :
        boolean is_correct;

        if(values.length != 3){
            is_correct = false;
        }else{

            // Día Mes Año
            String day = values[0];
            String month = values[1];
            String year = values[2];

            try{
                if(checkDay(day, month, year) && checkMonth(month) && checkYear(year)){
                    is_correct = true;
                }else{
                    is_correct = false;
                }
            }catch(Exception ex){
                is_correct = false;
            }
        }
        return is_correct;
    }

    public boolean checkTime(String time){
        time = time.trim();
        String values[] = time.split(":");

        //Separar valores por dos puntos :
        boolean is_correct;

        if(values.length != 2){
            is_correct = false;
        }else{

            // Hora 00 - 23  Minutos 00 - 59
            String hour = values[0];
            String minutes = values[1];

            try{
                if(checkHour(hour) && checkMinutes(minutes)){
                    is_correct = true;
                }else{
                    is_correct = false;
                }
            }catch(Exception ex){
                is_correct = false;
            }
        }
        return is_correct;
    }

    private boolean checkHour(String hour){

        int hourNumeric = Integer.parseInt(hour);
        boolean isCorrectHour;

        if(hourNumeric >= 0 && hourNumeric < 24){
            isCorrectHour = true;
        }else{
            isCorrectHour = false;
        }
        return isCorrectHour;
    }

    private boolean checkMinutes(String minutes){

        int minutesNumeric = Integer.parseInt(minutes);
        boolean isCorrectMinutes;

        if(minutesNumeric >= 0 && minutesNumeric < 60){
            isCorrectMinutes = true;
        }else{
            isCorrectMinutes = false;
        }
        return isCorrectMinutes;
    }

    private boolean checkDay(String day, String month, String year){

        int dayNumeric = Integer.parseInt(day);
        int monthNumeric = Integer.parseInt(month);
        int yearNumeric = Integer.parseInt(year);
        boolean isCorrectDay = false;

        /*con 31 días
        01,03, 05, 07, 08, 10, 12

        con 30 días
        04, 06, 09, 11

        con 28, 29 (Bisiesto /4)
        02*/
        if(monthNumeric == 1 || monthNumeric == 3 || monthNumeric == 5 || monthNumeric == 7
                || monthNumeric == 8 || monthNumeric == 10 || monthNumeric == 12){
            // 31 días
            if(dayNumeric >= 1 && dayNumeric <= 31){
                isCorrectDay = true;
            }else{
                isCorrectDay = false;
            }

        }

        if(monthNumeric == 4 || monthNumeric == 6 || monthNumeric == 9 || monthNumeric == 11){
            // 30 días
            if(dayNumeric >= 1 && dayNumeric <= 30){
                isCorrectDay = true;
            }else{
                isCorrectDay = false;
            }
        }

        if(monthNumeric == 2){
            //28, 29 días

            if(yearNumeric % 4 == 0){ // Si se divide entre 4 = Bisiesto

                if(dayNumeric >= 1 && dayNumeric <= 29){
                    isCorrectDay = true;
                }else{
                    isCorrectDay = false;
                }

            }else{  // No es bisiesto 28 días
                if(dayNumeric >= 1 && dayNumeric <= 28){
                    isCorrectDay = true;
                }else{
                    isCorrectDay = false;
                }
            }
        }

        return isCorrectDay;
    }

    private boolean checkMonth(String month){

        int monthNumeric = Integer.parseInt(month);
        boolean isCorrectMonth;

        if(monthNumeric >= 1 && monthNumeric <= 12){
            isCorrectMonth = true;
        }else{
            isCorrectMonth = false;
        }
        return isCorrectMonth;
    }

    private boolean checkYear(String year){

        int yearNumeric = Integer.parseInt(year);
        boolean isCorrectYear;

        if(yearNumeric >= 2000 ){
            isCorrectYear = true;
        }else{
            isCorrectYear = false;
        }
        return isCorrectYear;
    }

    /*public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(new Date());
        return date;
    }

    public void getDatetoString() {
        // dd/M/yyyy hh:mm:ss
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            String dateInString = "31-08-1982 10:20:56";
            Date date = sdf.parse(dateInString);
            System.out.println(date); //Tue Aug 31 10:20:56 SGT 1982
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/

}
