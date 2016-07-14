package studio.developper.organizer.personalorganizer.Password;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import studio.developper.organizer.personalorganizer.R;
import studio.developper.organizer.personalorganizer.entities.Password;

/**
 * Created by Esteban_R on 11/7/2016.
 */
public class PasswordsListAdapter extends RecyclerView.Adapter<PasswordsListAdapter.ViewHolder> {
    private List<Password> items;
    private OnItemClickListener clickListener;

    public PasswordsListAdapter(List<Password> items) {
        this.items = items;
    }

    public static List<Password> getItems() {
        return Password.getAllPasswords();
        //return items;
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_password, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtSite.setText(items.get(position).getSite());
        holder.txtUser.setText(items.get(position).getUserValue());

        Password password = items.get(position);
        if (this.clickListener != null) {
            holder.setOnItemClickListener(password, this.clickListener);
        }

    }

    public void setItems(List<Password> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtSite)
        TextView txtSite;

        @Bind(R.id.txtUser)
        TextView txtUser;

        private View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);

            ArrayList<String> items;
            PasswordsListAdapter adapter;


        }

        public void setOnItemClickListener(final Password element,
                                           final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(element);
                }
            });

        }

    }

}