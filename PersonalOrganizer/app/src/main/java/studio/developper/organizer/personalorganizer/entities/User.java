package studio.developper.organizer.personalorganizer.entities;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import studio.developper.organizer.personalorganizer.DataBase.PersonalOrganizerDataBase;

/**
 * Created by Esteban_R on 9/7/2016.
 */

@Table(database = PersonalOrganizerDataBase.class)
public class User extends BaseModel {
    @SerializedName("id")
    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    private String user;
    @Column
    private String password;

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static List<User> getAllUsers(){
        return new Select().from(User.class).queryList();
    }

    public static User checkingExistUser(String user, String password){
        // Buscar el usuario
        User found_user =
        new Select().from(User.class).where(User_Table.user.eq(user), User_Table.password.eq(password)).querySingle();
        return found_user;
    }
}