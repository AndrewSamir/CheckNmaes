package samir.andrew.checknmaes.interfaces;

import com.google.firebase.database.DataSnapshot;

public interface InterfaceGetDataFromFirebase
{
    // id is selected id from dialog
    // name is selected name
    // flag witch dialog clciked
    void onGetDataFromFirebase(DataSnapshot dataSnapshot, String flag);

}
