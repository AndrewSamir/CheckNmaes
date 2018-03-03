package samir.andrew.checknmaes.utlities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

import developer.mokadim.projectmate.dialog.IndicatorStyle;
import samir.andrew.checknmaes.R;
import samir.andrew.checknmaes.interfaces.InterfaceDailogClicked;
import samir.andrew.checknmaes.interfaces.InterfaceGetDataFromFirebase;


/**
 * Created by lenovo on 6/28/2017.
 */

public class HandleGetDataFromFirebase
{
    private static Context context;
    private static HandleGetDataFromFirebase instance = null;
    private InterfaceGetDataFromFirebase clickListener;
    private InterfaceDailogClicked interfaceDailogClicked;
    private static FirebaseDatabase database;
    private static DatabaseReference myRef;

    public static HandleGetDataFromFirebase getInstance(Context context)
    {

        HandleGetDataFromFirebase.context = context;

        if (instance == null)
        {
            instance = new HandleGetDataFromFirebase();
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference();
            myRef.keepSynced(true);

        }
        return instance;
    }

    public void setGetDataFromFirebaseInterface(InterfaceGetDataFromFirebase itemClickListener)
    {
        this.clickListener = itemClickListener;
    }

    public void setListnerToDialog(InterfaceDailogClicked interfaceDailogClicked)
    {
        this.interfaceDailogClicked = interfaceDailogClicked;
    }

    public void callGetAllNames(final String flag)
    {
//        final Dialog progressDialog = new ProgressDialog(context, 1).show();
//        progressDialog.show();
        if (isOnline())
        {
            DatabaseReference myRefJobs = myRef.child(context.getString(R.string.names));
            myRefJobs.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    clickListener.onGetDataFromFirebase(dataSnapshot, flag);
//                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError error)
                {
                    TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
//                    progressDialog.dismiss();
                }
            });
        } else
        {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
//            progressDialog.dismiss();
        }
    }

    private Boolean isOnline()
    {

        return true;

       /* if (Build.FINGERPRINT.contains("generic"))
            return true;

        try
        {
            Process p1 = Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            return (returnVal == 0);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;*/
    }


}
