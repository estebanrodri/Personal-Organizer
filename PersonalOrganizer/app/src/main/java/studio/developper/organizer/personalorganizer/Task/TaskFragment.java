package studio.developper.organizer.personalorganizer.Task;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import studio.developper.organizer.personalorganizer.R;
import studio.developper.organizer.personalorganizer.entities.Task;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment implements TaskView, OnItemClickListener {

    TasksListAdapter adapter;
    TaskPresenter taskPresenter;

    @Bind(R.id.txtTask) TextView txtTask;
    @Bind(R.id.recyclerViewTasks) RecyclerView recyclerView;
    @Bind(R.id.progressBar) ProgressBar progressBar;
    @Bind(R.id.addTask) FloatingActionButton addTask;
    @Bind(R.id.view_container_task) FrameLayout container;


    public TaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        ButterKnife.bind(this, view);

        taskPresenter = new TaskPresenterImpl();

        List<Task> TaskList;
        try {
            TaskList = taskPresenter.getTasks();
        }catch(Exception ex){
            TaskList = new ArrayList<>();
        }

        //this.adapter = new TasksListAdapter(TaskList);
        initAdapter(TaskList);
        setupRecyclerView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        taskPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        taskPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        taskPresenter.onDestroy();
        super.onDestroy();
    }

    private void initAdapter(List<Task> tasksList) {
        if (adapter == null) {
            adapter = new TasksListAdapter(tasksList);
            adapter.setOnItemClickListener(this);
        }
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void showList() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    @OnClick(R.id.addTask)
    public void handleEventAddTask() {
        // Agregar una nueva tarea
        //Snackbar.make(container, "Add Password", Snackbar.LENGTH_SHORT).show();
        startActivity(new Intent(this.getActivity(), AddTaskActivity.class));
    }

    @Override
    public void onPasswordError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setTask(List<Task> items) {
        adapter.setItems(items);
    }

    @Override
    public void onItemClick(Task task) {
        System.out.println("Click sobre elemento");
        String msg = task.getTaskName() +"  "+ task.getStartDate() ;
        //Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(password.getLinkOfSite()));
        //startActivity(intent);

        Intent intent = new Intent(this.getActivity(), TaskDetailActivity.class);
        intent.putExtra("id", task.getId()+ "");
        intent.putExtra("name", task.getTaskName());
        intent.putExtra("start_date", task.getStartDate());
        intent.putExtra("start_time", task.getStartTime());
        intent.putExtra("end_time", task.getEndTime());
        intent.putExtra("end_date", task.getDueDate());
        intent.putExtra("description", task.getDescription());
        startActivity(intent);
    }
}