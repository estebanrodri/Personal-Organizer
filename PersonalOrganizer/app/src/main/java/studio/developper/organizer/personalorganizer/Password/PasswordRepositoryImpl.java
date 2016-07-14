package studio.developper.organizer.personalorganizer.Password;

import java.util.List;

import studio.developper.organizer.personalorganizer.Libs.EventBus;
import studio.developper.organizer.personalorganizer.PersonalOrganizerApp;
import studio.developper.organizer.personalorganizer.entities.Password;
import studio.developper.organizer.personalorganizer.entities.User;

/**
 * Created by Esteban_R on 11/7/2016.
 */
public class PasswordRepositoryImpl implements PasswordRepository {
    private final EventBus eventBus;

    public PasswordRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public List<Password> getPasswords() {
        return Password.getAllPasswords();
    }

    @Override
    public boolean savePassword(Password password) {
        String user_name = password.getUserValue();
        String password_value = password.getPasswordValue();
        System.out.println("****Salvando valores*****");
        boolean is_save = false;

        //UserInstance userInstance = UserInstance.getInstance("");
        User currentUser = PersonalOrganizerApp.getUser(); //Obtener usuario logueado
        ///currentUser.setUser(userInstance.getUser_name());

        password.setUser(currentUser.getUser());   // Establecer enlace con llave foránea

        if (!user_name.equals("") || !password_value.equals("")) {
            try{
                password.save(); //Guardar
                System.out.println("***Guardado******");
                postEvent("Password Guardado");
                is_save = true;
            }catch(Exception ex){
                postEvent("No se pudo guardar");
                System.out.println("No se pudo guardar");
                System.out.println(ex);
                is_save = false;
            }
        }
        return is_save;
    }


    private void postEvent(String error) {
        PasswordEvent event = new PasswordEvent();
        event.setError(error);
        eventBus.post(event);
    }

    private void postEvent(List<Password> items) {
        PasswordEvent event = new PasswordEvent();
        event.setPasswords(items);
        eventBus.post(event);
    }

    @Override
    public void delete(Password password){
        if(password != null){
            password.delete();
        }
    }

}
