package studio.developper.organizer.personalorganizer.Password;

import android.content.Intent;
import android.net.Uri;
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
import studio.developper.organizer.personalorganizer.entities.Password;
import studio.developper.organizer.personalorganizer.main.OrganizerMainActivity;

public class PasswordDetailActivity extends AppCompatActivity {

    @Bind(R.id.password_txt_site)
    TextView passwordTxtSite;
    @Bind(R.id.password_txt_user)
    TextView passwordTxtUser;
    @Bind(R.id.password_txt_password)
    TextView passwordTxtPassword;
    @Bind(R.id.password_txt_link)
    TextView passwordTxtLink;
    @Bind(R.id.view_detail_container_password)
    LinearLayout container;
    @Bind(R.id.password_id) TextView passwordId;

    PasswordPresenter passwordPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_detail);
        ButterKnife.bind(this);

        setValues(savedInstanceState);
        passwordPresenter = new PasswordPresenterImpl();
    }

    public void setValues(Bundle extras) {

        extras = getIntent().getExtras();
        if (extras != null) {
            passwordId.setText(extras.getString("id"));
            passwordTxtSite.setText(extras.getString("site"));
            passwordTxtUser.setText(extras.getString("user"));
            passwordTxtPassword.setText(extras.getString("pass"));
            passwordTxtLink.setText(extras.getString("link"));
        }
    }


    @OnClick(R.id.password_view_link)
    public void onGoToLink() {
        String strUrl = passwordTxtLink.getText().toString();
        if (strUrl != "") {

            if (strUrl.contains("https://") || strUrl.contains("http://")) {
                //
            } else {
                strUrl = "http://" + passwordTxtLink.getText().toString();
            }

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(strUrl));
            startActivity(i);
        } else {
            String msg = "Complete the value link";
            Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.password_delete)
    public void delete() {

        Password password = new Password();
        password.setId(Integer.parseInt(passwordId.getText().toString()));
        password.setSite(passwordTxtSite.getText().toString());
        password.setUserValue(passwordTxtUser.getText().toString());
        password.setPasswordValue(passwordTxtPassword.getText().toString());
        password.setLinkOfSite(passwordTxtLink.getText().toString());
        password.setUser(PersonalOrganizerApp.getUser().getUser());

        passwordPresenter.delete(password);
        navigateToMainScreen();
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this, OrganizerMainActivity.class));
    }
}