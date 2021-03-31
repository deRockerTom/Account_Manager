package fr.enseeiht.tderocke.account_manager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class FixDepenses extends Activity  implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private Spinner spinner;
    private ListView listView;

    protected Adapter initializedAdapter = null;

    private String TAG = "FixDepenses";
    private ArrayAdapter depensesArrayAdapter;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fix_depenses);

        dataBaseHelper = new DataBaseHelper(this);

        spinner = (Spinner) findViewById(R.id.spinner2);
        listView = (ListView) findViewById(R.id.list_view);

        spinner.setOnItemSelectedListener(this);

        listView.setOnItemClickListener(this);



    }


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
                    ShowDepensesOnListView(dataBaseHelper);
                    Toast.makeText(this, "All Fixes", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    ShowAnnuallyDepensesOnListView(dataBaseHelper);
                    Toast.makeText(this, "Annually", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    ShowMonthlyDepensesOnListView(dataBaseHelper);
                    Toast.makeText(this, "Monthly", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    ShowWeeklyDepensesOnListView(dataBaseHelper);
                    Toast.makeText(this, "Weekly", Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    ShowDailyDepensesOnListView(dataBaseHelper);
                    Toast.makeText(this, "Daily", Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void ShowDepensesOnListView(DataBaseHelper dataBaseHelper2) {
        depensesArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataBaseHelper2.getFix());
        listView.setAdapter((depensesArrayAdapter));
    }

    private void ShowDailyDepensesOnListView(DataBaseHelper dataBaseHelper2) {
        depensesArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataBaseHelper2.getDaily());
        listView.setAdapter((depensesArrayAdapter));
    }

    private void ShowWeeklyDepensesOnListView(DataBaseHelper dataBaseHelper2) {
        depensesArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataBaseHelper2.getWeekly());
        listView.setAdapter((depensesArrayAdapter));
    }

    private void ShowMonthlyDepensesOnListView(DataBaseHelper dataBaseHelper2) {
        depensesArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataBaseHelper2.getMonthly());
        listView.setAdapter((depensesArrayAdapter));
    }

    private void ShowAnnuallyDepensesOnListView(DataBaseHelper dataBaseHelper2) {
        depensesArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataBaseHelper2.getAnnually());
        listView.setAdapter((depensesArrayAdapter));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Depense depense = (Depense) parent.getItemAtPosition(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Confirmation de suppression");
        builder.setMessage("Êtes-vous sûrs de vouloir supprimer le virement " + depense.getFrequency() + " " + depense.getNom() + " de " + String.valueOf(depense.getValue()) + " € ?");
        builder.setPositiveButton("Confirmer",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBaseHelper.deleteOneFix(depense);
                        switch (spinner.getSelectedItemPosition()) {
                            case 1:
                                ShowDepensesOnListView(dataBaseHelper);
                                Toast.makeText(FixDepenses.this, "All Fixes", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                ShowAnnuallyDepensesOnListView(dataBaseHelper);
                                Toast.makeText(FixDepenses.this, "Annually", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                ShowMonthlyDepensesOnListView(dataBaseHelper);
                                Toast.makeText(FixDepenses.this, "Monthly", Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                ShowWeeklyDepensesOnListView(dataBaseHelper);
                                Toast.makeText(FixDepenses.this, "Weekly", Toast.LENGTH_SHORT).show();
                                break;
                            case 5:
                                ShowDailyDepensesOnListView(dataBaseHelper);
                                Toast.makeText(FixDepenses.this, "Daily", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                        }
                    }
                });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
