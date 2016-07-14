package studio.developper.organizer.personalorganizer.Libs;

import android.widget.ImageView;

/**
 * Created by Esteban_R on 9/7/2016.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object object);

}
