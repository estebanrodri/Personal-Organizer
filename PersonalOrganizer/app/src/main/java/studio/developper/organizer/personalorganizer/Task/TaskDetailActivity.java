package studio.developper.organizer.personalorganizer.Task;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import studio.developper.organizer.personalorganizer.PersonalOrganizerApp;
import studio.developper.organizer.personalorganizer.R;
import studio.developper.organizer.personalorganizer.entities.Task;
import studio.developper.organizer.personalorganizer.main.OrganizerMainActivity;

public class TaskDetailActivity extends AppCompatActivity {

    @Bind(R.id.task_txt_name) TextView taskTxtName;
    @Bind(R.id.task_start_date) TextView taskStartDate;
    @Bind(R.id.task_start_time) TextView taskStartTime;
    @Bind(R.id.task_end_date) TextView taskEndDate;
    @Bind(R.id.task_end_time) TextView taskEndTime;
    @Bind(R.id.task_txt_description) TextView taskTxtDescription;
    @Bind(R.id.task_id) TextView task_id;
    @Bind(R.id.view_detail_container_task) LinearLayout container;


    TaskPresenter taskPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        ButterKnife.bind(this);
        setValues(savedInstanceState);

        taskPresenter = new TaskPresenterImpl();
    }

    @OnClick(R.id.task_delete)
    public void deleteTask() {
        //String msg = "Delete Task";
        //Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
        Task task = new Task();
        task.setUser(PersonalOrganizerApp.getUser().getUser());
        task.setId(Integer.parseInt(task_id.getText().toString()));
        task.setTaskName(taskTxtName.getText().toString());
        task.setStartDate(taskStartDate.getText().toString());
        task.setStartTime(taskStartTime.getText().toString());
        task.setDueDate(taskEndDate.getText().toString());
        task.setEndTime(taskEndTime.getText().toString());
        task.setDescription(taskTxtDescription.getText().toString());
        System.out.println("Eliminando");

        taskPresenter.delete(task);
        navigateToMainScreen();
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this, OrganizerMainActivity.class));
    }

    public void setValues(Bundle extras) {

        extras = getIntent().getExtras();
        if (extras != null) {
            task_id.setText(extras.getString("id"));
            taskTxtName.setText(extras.getString("name"));
            taskStartDate.setText(extras.getString("start_date"));
            taskStartTime.setText(extras.getString("start_time"));
            taskEndDate.setText(extras.getString("end_date"));
            taskEndTime.setText(extras.getString("end_time"));
            taskTxtDescription.setText(extras.getString("description"));
        }
    }
}
