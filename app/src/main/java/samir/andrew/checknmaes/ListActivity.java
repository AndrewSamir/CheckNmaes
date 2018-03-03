package samir.andrew.checknmaes;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import samir.andrew.checknmaes.adapters.AdapterComeModel;
import samir.andrew.checknmaes.adapters.AdapterModel;
import samir.andrew.checknmaes.interfaces.InterfaceGetDataFromFirebase;
import samir.andrew.checknmaes.models.ModelData;
import samir.andrew.checknmaes.utlities.DataEnum;
import samir.andrew.checknmaes.utlities.HandleAddDataToFirebase;
import samir.andrew.checknmaes.utlities.HandleGetDataFromFirebase;

public class ListActivity extends AppCompatActivity implements InterfaceGetDataFromFirebase
{

    AdapterModel adapterModel;
    AdapterComeModel adapterComeModel;
    List<ModelData> modelDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvListNames = findViewById(R.id.rvListNames);
        modelDataList = new ArrayList<>();
        adapterModel = new AdapterModel(modelDataList, this);
        adapterComeModel = new AdapterComeModel(modelDataList, this);
        rvListNames.setLayoutManager(new GridLayoutManager(this, 1));
        Intent intent = getIntent();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialogAdd();
            }
        });

        if (intent.getBooleanExtra("add", true))
            rvListNames.setAdapter(adapterModel);
        else
        {
            rvListNames.setAdapter(adapterComeModel);
            fab.setVisibility(View.GONE);
        }
        HandleGetDataFromFirebase.getInstance(this).setGetDataFromFirebaseInterface(this);
        HandleGetDataFromFirebase.getInstance(this).callGetAllNames(DataEnum.callGetAllNames.name());

    }

    @Override
    public void onGetDataFromFirebase(DataSnapshot dataSnapshot, String flag)
    {
        if (flag.equals(DataEnum.callGetAllNames.name()))
        {
            modelDataList = new ArrayList<>();
            adapterComeModel.clearAllListData();
            adapterModel.clearAllListData();
            for (DataSnapshot child : dataSnapshot.getChildren())
            {
                ModelData modelItem = child.getValue(ModelData.class);
                modelItem.setKeyString(child.getKey());
                modelDataList.add(modelItem);
            }
            adapterModel.addAll(modelDataList);
            adapterComeModel.addAll(modelDataList);
        }
    }

    public void showDialogAdd()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_add);

        final EditText edtDialogAdd = dialog.findViewById(R.id.edtDialogAdd);
        Button tibDialogAddOk = dialog.findViewById(R.id.tibDialogAddOk);
        Button tibDialogAddCancel = dialog.findViewById(R.id.tibDialogAddCancel);

        tibDialogAddCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });

        tibDialogAddOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (edtDialogAdd.getText().toString().length() > 0)
                {
                    ModelData modelData = new ModelData(edtDialogAdd.getText().toString());
                    HandleAddDataToFirebase.getInstance(ListActivity.this).callAddNewMember("flag", modelData);
                    dialog.dismiss();
                } else
                    dialog.dismiss();
            }
        });

        dialog.show();

    }

}
