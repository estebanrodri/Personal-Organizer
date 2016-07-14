package studio.developper.organizer.personalorganizer.Login;

import studio.developper.organizer.personalorganizer.PersonalOrganizerApp;
import studio.developper.organizer.personalorganizer.entities.User;
import studio.developper.organizer.personalorganizer.Libs.EventBus;
import studio.developper.organizer.personalorganizer.Libs.GreenRobotEventBus;

/**
 * Created by Esteban_R on 9/7/2016.
 */
public class LoginRepositoryImpl implements LoginRepository {

    private EventBus eventBus;
    private User user;

    public LoginRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public LoginRepositoryImpl() {
    }

    private void setUser(String user, String password){
        this.user = new User(user, password);
    }

    private User getUser(){
        return this.user;
    }


    @Override
    public void signUp(String user, String password) {
        System.out.println("******");
        System.out.println("Inicio de método de registro");
        System.out.println("******");
        setUser(user, password);            //Creamos el usuario
        User creating_user = getUser();

        User checkUser = User.checkingExistUser(creating_user.getUser(), creating_user.getPassword());

        if(checkUser != null){
            //El usuario ya está creado
            postEvent(LoginEvent.onSignUpError, "El usuario ya existe");
            System.out.println("Usuario Existe");
        }
        else{ //El usuario no está creado
            System.out.println("Registrando nuevo usuario");
            registerNewUser();
            signIn(user, password);
        }

    }
    private void registerNewUser() {
        String user_name = getUser().getUser();
        String password = getUser().getPassword();

        if (user_name != "" && password != "") {
            try{
                getUser().save(); //Guardar Usuario
                System.out.println("Guardado");
                postEvent(LoginEvent.onSignUpSuccess, "Usuario registrado");
            }catch(Exception ex){
                postEvent(LoginEvent.onSignUpError, "No se pudo registrar al usuario");
            }
        }
    }

    @Override
    public void signIn(String user, String password) {

        setUser(user, password);            //Creamos el usuario
        User user_login = getUser();
        try {
            //Revisar si el usuario existe
            User checkUser = User.checkingExistUser(user_login.getUser(), user_login.getPassword());
            if (checkUser != null) {
                System.out.println("***Usuario Encontrado******");
                System.out.println(Integer.toString(checkUser.getId()) + "  " + checkUser.getUser() + "  " + checkUser.getPassword());
                System.out.println("******Encontrado***");

                //Establecer como usuario de la aplicación
                //UserInstance userInstance = UserInstance.getInstance(user);
                PersonalOrganizerApp.setUser(checkUser);
                //User current_user = new User(user_login.getUser(), user_login.getPassword());
                //userInstance(user_login.getUser()); //Usuario logueado
                System.out.println("usando usuario");

                postEvent(LoginEvent.onSignInSuccess, "Ingreso correcto");
                System.out.println("Verificado");
            }
            else{
                System.out.println("***Usuario NO Encontrado******");
                postEvent(LoginEvent.onSignInError, "El usuario no existe");
            }

        }catch(Exception ex){
            String strMsg = "Error "+ex.toString();
            System.out.println(strMsg);
            postEvent(LoginEvent.onSignInError, strMsg);
        }
    }

    @Override
    public boolean checkAlreadyAuthenticated() {

        User user_login = getUser();  //Obtener el usuario
        try {
            if (user_login.exists() && user_login != null) {
                //El usuario ya está en la BD
                postEvent(LoginEvent.onSignInSuccess, "Ingreso correcto");
                return true;
            } else { //El usuario no está creado
                postEvent(LoginEvent.onSignInError);
                return false;
            }
        }catch(NullPointerException nullExc){
            //postEvent(LoginEvent.onSignInError);
            return true;
        }
    }

    @Override
    public User returnLoginUser(){
        return getUser();
    }

    private void initSignIn(){//DataSnapshot snapshot){
        /*User currentUser = snapshot.getValue(User.class);

        if (currentUser == null) {
            registerNewUser();
        }
        helper.changeUserConnectionStatus(User.ONLINE);
        postEvent(LoginEvent.onSignInSuccess);*/
    }

    private void postEvent(int type) {
        postEvent(type, null);
    }

    private void postEvent(int type, String errorMessage) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMesage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

}