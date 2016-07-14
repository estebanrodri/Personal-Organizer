package studio.developper.organizer.personalorganizer.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import studio.developper.organizer.personalorganizer.Login.LoginActivity;
import studio.developper.organizer.personalorganizer.Password.PasswordFragment;
import studio.developper.organizer.personalorganizer.PersonalOrganizerApp;
import studio.developper.organizer.personalorganizer.R;
import studio.developper.organizer.personalorganizer.Task.TaskFragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class OrganizerMainActivity extends AppCompatActivity implements MainView {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.view_container) ViewPager view_container;

    MainPresenter presenter;
    MainSectionsPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_main);

        ButterKnife.bind(this);

        setupTabs();
        setupNavigation();
        presenter = new MainPresenterImpl(this);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        presenter.logout();
        //this.sharedPreferences.edit().clear().commit();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    private void setupNavigation() {

        String user_name = " " + PersonalOrganizerApp.getUser().getUser();

        toolbar.setTitle(user_name);
        setSupportActionBar(toolbar);

        view_container.setAdapter(adapter);
        tabLayout.setupWithViewPager(view_container);
    }

    private void setupTabs() {
        // Establecer Tabs
        String[] titles = new String[]{ getString(R.string.main_title_task),
                getString(R.string.main_title_password)}; //Establecer Títulos
        Fragment[] fragments = new Fragment[]{ new TaskFragment(), new PasswordFragment()};
        //Establecer Fragmentos

        this.adapter = new MainSectionsPagerAdapter(
                getSupportFragmentManager(), fragments, titles);
    }

}
