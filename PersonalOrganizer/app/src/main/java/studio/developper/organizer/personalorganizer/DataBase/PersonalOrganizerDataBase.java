package studio.developper.organizer.personalorganizer.DataBase;
import com.raizlabs.android.dbflow.annotation.Database;
/**
 * Created by Esteban_R on 9/7/2016.
 */


@Database(name = PersonalOrganizerDataBase.NAME, version = PersonalOrganizerDataBase.VERSION)
public class PersonalOrganizerDataBase {
    public static final int VERSION = 1;
    public static final String NAME = "PersonalOrganizer";
}
