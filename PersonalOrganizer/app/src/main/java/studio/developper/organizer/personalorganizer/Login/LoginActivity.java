package studio.developper.organizer.personalorganizer.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import studio.developper.organizer.personalorganizer.main.OrganizerMainActivity;
import studio.developper.organizer.personalorganizer.R;

public class LoginActivity extends AppCompatActivity implements LoginView {


    @Bind(R.id.inputUser) TextInputLayout inputUser;
    @Bind(R.id.inputPassword) TextInputLayout inputPassword;
    @Bind(R.id.editTxtPassword) EditText editTxtPassword;
    @Bind(R.id.editTxtUser) EditText editTxtUser;
    @Bind(R.id.btnSignin) Button btnSignin;
    @Bind(R.id.btnSignup) Button btnSignup;
    @Bind(R.id.progressBar) ProgressBar progressBar;
    @Bind(R.id.layout_main) RelativeLayout container;

    private String user_name;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        System.out.println("******");
        System.out.println("Iniciando Actividad de Login");
        System.out.println("******");

        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
        loginPresenter.checkForAuthenticatedUser();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
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
    @OnClick(R.id.btnSignup)
    public void handleSignUp() {
        System.out.println("******");
        System.out.println("Registrando usuario");
        System.out.println("******");
        loginPresenter.registerNewUser(editTxtUser.getText().toString(),
                editTxtPassword.getText().toString());
    }

    @Override
    @OnClick(R.id.btnSignin)
    public void handleSignIn() {
        System.out.println("Inicio de sesión");
        loginPresenter.validateLogin(editTxtUser.getText().toString(),
                editTxtPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, OrganizerMainActivity.class));
    }

    @Override
    public void loginError(String error) {
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        inputPassword.setError(msgError);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(container, R.string.login_notice_message_useradded, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void newUserError(String error) {
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        inputPassword.setError(msgError);
    }


    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    private void setInputs(boolean enabled){
        btnSignin.setEnabled(enabled);
        btnSignup.setEnabled(enabled);
        inputUser.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
    }

    public String getUserName() {
        return user_name;
    }

    @Override
    public void setUserName(String user_name) {
        this.user_name = user_name;
    }
}
