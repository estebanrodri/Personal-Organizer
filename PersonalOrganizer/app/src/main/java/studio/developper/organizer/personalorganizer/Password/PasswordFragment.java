package studio.developper.organizer.personalorganizer.Password;


import android.content.Intent;
import android.os.Bundle;
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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import studio.developper.organizer.personalorganizer.R;
import studio.developper.organizer.personalorganizer.entities.Password;


/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordFragment extends Fragment implements PasswordView, OnItemClickListener {


    PasswordsListAdapter adapter;
    PasswordPresenter passwordPresenter;

    @Bind(R.id.viewFrame)
    FrameLayout container;
    @Bind(R.id.txtPassword)
    TextView txtPassword;
    @Bind(R.id.recyclerViewPassword)
    RecyclerView recyclerView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    //@Bind(R.id.addPasssword)
    //FloatingActionButton addPasssword;


    public PasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_password, container, false);
        ButterKnife.bind(this, view);

        passwordPresenter = new PasswordPresenterImpl();

        List<Password> passwordsList;
        try {
            passwordsList = passwordPresenter.getPasswords();
        }catch(Exception ex){
            passwordsList = Password.getAllPasswords();
        }

        initAdapter(passwordsList);
        setupRecyclerView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        passwordPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        passwordPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        passwordPresenter.onDestroy();
        super.onDestroy();
    }

    private void initAdapter(List<Password> passwordsList) {
        if (adapter == null) {
            adapter = new PasswordsListAdapter(passwordsList);
            adapter.setOnItemClickListener(this);
        }
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    @OnClick(R.id.addPasssword)
    public void handleEventAddPassword() {
        //Snackbar.make(container, "Add Password", Snackbar.LENGTH_SHORT).show();
        startActivity(new Intent(this.getActivity(), AddPasswordActivity.class));
    }


    @Override
    public void onItemClick(Password password) {
        System.out.println("Click sobre elemento");
        //String msg = password.getSite() +"  "+ password.getUserValue() ;
        //Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();

        Intent intent = new Intent(this.getActivity(), PasswordDetailActivity.class);
        intent.putExtra("id", password.getId()+ "");
        intent.putExtra("site", password.getSite());
        intent.putExtra("user", password.getUserValue());
        intent.putExtra("pass", password.getPasswordValue());
        intent.putExtra("link", password.getLinkOfSite());
        startActivity(intent);
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
    public void onPasswordError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPasswords(List<Password> items) {
        adapter.setItems(items);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
