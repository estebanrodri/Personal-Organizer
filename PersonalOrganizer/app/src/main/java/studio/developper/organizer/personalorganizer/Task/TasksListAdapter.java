package studio.developper.organizer.personalorganizer.Task;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import studio.developper.organizer.personalorganizer.R;
import studio.developper.organizer.personalorganizer.entities.Task;

/**
 * Created by Esteban_R on 11/7/2016.
 */
public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.ViewHolder> {

    private List<Task> items;
    private OnItemClickListener clickListener;

    public TasksListAdapter(List<Task> items) {
        this.items = items;
    }

    public static List<Task> getItems() {
        return Task.getAllTask();
        //return items;
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtTaskName.setText(items.get(position).getTaskName());
        String endDate = items.get(position).getStartDate() +"  "+ items.get(position).getStartTime();
        holder.txtEndDate.setText(endDate);

        Task password = items.get(position);
        if (this.clickListener != null) {
            holder.setOnItemClickListener(password, this.clickListener);
        }

    }

    public void setItems(List<Task> newItems) {
            items.addAll(newItems);
            notifyDataSetChanged();
            }

    @Override
    public int getItemCount() {
            return items.size();
            }

    static class ViewHolder extends RecyclerView.ViewHolder {
        //txtTaskName, txtStatua
        @Bind(R.id.txtTaskName)
        TextView txtTaskName;

        @Bind(R.id.txtEndDate)
        TextView txtEndDate;

        private View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);

            //ArrayList<String> items;
            //TasksListAdapter adapter;
        }

        public void setOnItemClickListener(final Task element,
                                           final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(element);
                }
            });
        }
    }// Clase estática

}