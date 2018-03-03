package samir.andrew.checknmaes.utlities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sdsmdg.tastytoast.TastyToast;

import developer.mokadim.projectmate.dialog.IndicatorStyle;
import samir.andrew.checknmaes.R;
import samir.andrew.checknmaes.interfaces.InterfaceAddDataToFirebase;
import samir.andrew.checknmaes.models.ModelData;


/**
 * Created by lenovo on 6/28/2017.
 */

public class HandleAddDataToFirebase
{
    private static Context context;
    private static HandleAddDataToFirebase instance = null;
    private InterfaceAddDataToFirebase clickListener;
    private static DatabaseReference myRef;

    public static HandleAddDataToFirebase getInstance(Context context)
    {

        HandleAddDataToFirebase.context = context;

        if (instance == null)
        {
            instance = new HandleAddDataToFirebase();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            myRef = database.getReference();
            myRef.keepSynced(true);

        }
        return instance;
    }

    public void setClickDialogListener(InterfaceAddDataToFirebase itemClickListener)
    {
        this.clickListener = itemClickListener;
    }

    public void callAddAbsent(final String flag, String key, final int adapterPosition, String status)
    {
//        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
//        progressDialog.show();

        if (HelpMe.getInstance(context).isOnline())
        {

            DatabaseReference myRefJobs = myRef.child(context.getString(R.string.names))
                    .child(key).child("absent");

            myRefJobs.setValue(status, new DatabaseReference.CompletionListener()
            {
                public void onComplete(DatabaseError error, DatabaseReference ref)
                {

                    if (error == null)
                    {
                        clickListener.onDataAddedSuccess(flag, adapterPosition);
                    } else
                    {
                        clickListener.onDataAddedFailed(flag);
                    }

//                    progressDialog.dismiss();
                }
            });
        } else
        {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
//            progressDialog.dismiss();
        }
    }

    public void callAddNewMember(final String flag, ModelData modelData)
    {
//        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
//        progressDialog.show();

        if (HelpMe.getInstance(context).isOnline())
        {

            DatabaseReference myRefJobs = myRef.child(context.getString(R.string.names));

            myRefJobs.push().setValue(modelData, new DatabaseReference.CompletionListener()
            {
                public void onComplete(DatabaseError error, DatabaseReference ref)
                {

                    if (error == null)
                    {
                        clickListener.onDataAddedSuccess(flag);
                    } else
                    {
                        clickListener.onDataAddedFailed(flag);
                    }

//                    progressDialog.dismiss();
                }
            });
        } else
        {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
//            progressDialog.dismiss();
        }

    }

    public void callAddComeToBus(final String flag, String key, final int adapterPosition, String status)
    {
//        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
//        progressDialog.show();

        if (HelpMe.getInstance(context).isOnline())
        {

            DatabaseReference myRefJobs = myRef.child(context.getString(R.string.names))
                    .child(key).child("comeToBus");

            myRefJobs.setValue(status, new DatabaseReference.CompletionListener()
            {
                public void onComplete(DatabaseError error, DatabaseReference ref)
                {

                    if (error == null)
                    {
                        clickListener.onDataAddedSuccess(flag, adapterPosition);
                    } else
                    {
                        clickListener.onDataAddedFailed(flag);
                    }

//                    progressDialog.dismiss();
                }
            });
        } else
        {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
//            progressDialog.dismiss();
        }

    }

}
