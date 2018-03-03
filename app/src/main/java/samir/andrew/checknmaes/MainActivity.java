package samir.andrew.checknmaes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnMainAddNew = findViewById(R.id.btnMainAddNew);
        Button btnMainCheck = findViewById(R.id.btnMainCheck);

        btnMainAddNew.setOnClickListener(this);
        btnMainCheck.setOnClickListener(this);

    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnMainAddNew:
                Intent intent = new Intent(this, ListActivity.class);
                intent.putExtra("add", true);
                startActivity(intent);
                break;
            case R.id.btnMainCheck:
                Intent intent_ = new Intent(this, ListActivity.class);
                intent_.putExtra("add", false);
                startActivity(intent_);
                break;
        }
    }
}
