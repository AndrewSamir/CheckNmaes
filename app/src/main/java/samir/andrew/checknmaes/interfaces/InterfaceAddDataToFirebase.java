package samir.andrew.checknmaes.interfaces;

/**
 * Created by lenovo on 2/23/2016.
 */
public interface InterfaceAddDataToFirebase {
    // id is selected id from dialog
    // name is selected name
    // flag witch dialog clciked
    void onDataAddedSuccess(String flag);

    void onDataAddedSuccess(String flag, int position);

    void onDataAddedFailed(String flag);


}
