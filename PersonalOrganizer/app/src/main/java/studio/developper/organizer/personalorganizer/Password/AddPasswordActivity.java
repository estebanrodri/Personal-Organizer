package studio.developper.organizer.personalorganizer.Password;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import studio.developper.organizer.personalorganizer.R;
import studio.developper.organizer.personalorganizer.entities.Password;
import studio.developper.organizer.personalorganizer.main.OrganizerMainActivity;

public class AddPasswordActivity extends AppCompatActivity {

    @Bind(R.id.txtSite) EditText txtSite;
    @Bind(R.id.txtUser) EditText txtUser;
    @Bind(R.id.txtPassword) EditText txtPassword;
    @Bind(R.id.txtLink) EditText txtLink;
    //@Bind(R.id.password_goLink) Button passwordGoLink;
    //@Bind(R.id.password_save) Button passwordSave;
    @Bind(R.id.container_add_password) LinearLayout container;

    Password password;
    PasswordPresenter passwordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_password);
        ButterKnife.bind(this);
        passwordPresenter = new PasswordPresenterImpl();
    }


    @OnClick(R.id.password_goLink)
    public void goToLink(View view) {
        String strUrl = txtLink.getText().toString();
        if (strUrl != "") {

            if (strUrl.contains("https://") || strUrl.contains("http://")) {
                //
            } else {
                strUrl = "http://" + txtLink.getText().toString();
            }

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(strUrl));
            startActivity(i);
        }
        else {
            String msg = "Complete the value link";
            Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.password_save)
    public void addPassword(){
        String site_name = txtSite.getText().toString().trim();
        String user_name = txtUser.getText().toString().trim();
        String password_value = txtPassword.getText().toString().trim();
        String link = txtLink.getText().toString().trim();

        System.out.println("*****Password a ingresar****");
        System.out.println(site_name +" "+ user_name +" "+ password_value +" "+ link);
        if (!site_name.equals("") || !user_name.equals("") || !password_value .equals("")) {

            password = new Password(site_name, user_name, password_value, link);
            boolean is_save = passwordPresenter.savePassword(password);

            if (is_save) {
                //Redirigir hacia la actividad principal
                startActivity(new Intent(this, OrganizerMainActivity.class));
                System.out.println("*****Guardado****");
                String msg = "Password Save";
                Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
            }else{
                String msg = "We Have a Problem";
                Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
            }

        } else {
            String msg = "Complete the values Site, User and Password";
            Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
        }
    }

}
