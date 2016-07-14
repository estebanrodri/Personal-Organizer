package studio.developper.organizer.personalorganizer.entities;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.List;

import studio.developper.organizer.personalorganizer.DataBase.PersonalOrganizerDataBase;
import studio.developper.organizer.personalorganizer.PersonalOrganizerApp;

/**
 * Created by Esteban_R on 9/7/2016.
 */

@Table(database = PersonalOrganizerDataBase.class)
public class Password extends BaseModel {

    /*
      Datos
        Nombre del sitio
        Usuario
        Contraseña
        Enlace del sitio
    */

    @SerializedName("id")
    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    private String site;

    @Column
    private String userValue;

    @Column
    private String passwordValue;

    @Column
    private String linkOfSite;

    //@Column
    //@ForeignKey(saveForeignKeyModel = true)
    //User user;
    @Column
    private String user;

    public Password(String site, String userValue, String passwordValue, String linkOfSite) {
        this.site = site;
        this.userValue = userValue;
        this.passwordValue = passwordValue;
        this.linkOfSite = linkOfSite;
    }

    public Password() {
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUserValue() {
        return userValue;
    }

    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }

    public String getLinkOfSite() {
        return linkOfSite;
    }

    public void setLinkOfSite(String linkOfSite) {
        this.linkOfSite = linkOfSite;
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static List<Password> getAllPasswords(){

        //UserInstance userInstance = UserInstance.getInstance("");
        User currentUser = PersonalOrganizerApp.getUser();

         // Usuario actual
        List<Password> passwordList;

        try{
            passwordList = new Select().from(Password.class).
                where(Password_Table.user.is(currentUser.getUser())).queryList();

        }catch(NullPointerException exc){
            passwordList = new ArrayList<Password>();
        }
        return passwordList;
    }
}