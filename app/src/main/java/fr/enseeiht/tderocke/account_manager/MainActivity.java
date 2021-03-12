package fr.enseeiht.tderocke.account_manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private final static String TAG = "MainActivity";

    private Spinner spinner;

    String[] Choices;

    protected Adapter initializedAdapter = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        Choices = getResources().getStringArray(R.array.choix_menu);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // workaround for avoid the very first call
        // from http://stackoverflow.com/questions/3637704/how-to-avoid-having-android-spinner-call-itemselectedlistener-when-setting-adapt

        // Always ignore the initial selection performed after setAdapter
        if( initializedAdapter !=parent.getAdapter() )
        {
            initializedAdapter = parent.getAdapter();
            Log.i( TAG, "first call to onItemSelected" );
        }
        // Choix d'action par l'utilisateur
        else {
            Intent newInt;
            switch (position) {
                case 1:
                    newInt = new Intent( this, AddTransaction.class );
                    startActivity(newInt);
                    break;
                case 2:
                    newInt = new Intent( this, Historic.class );
                    startActivity(newInt);
                    break;
                case 3:
                    newInt = new Intent( this, FixDepenses.class );
                    startActivity(newInt);
                    break;
                default:
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}