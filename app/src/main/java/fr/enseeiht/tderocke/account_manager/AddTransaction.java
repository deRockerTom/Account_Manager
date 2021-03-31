package fr.enseeiht.tderocke.account_manager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddTransaction extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private final static String TAG = "AddTransaction";

    private Spinner type;
    private Spinner frequence;
    private Spinner depenseRentree;
    private Button button;
    private EditText annee;
    private EditText mois;
    private EditText jour;
    private EditText montant;
    private EditText raison;

    protected Adapter initializedAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_transaction);

        type = (Spinner)findViewById(R.id.spinnerTypeDepense);
        frequence = (Spinner)findViewById(R.id.spinnerFrequence);
        depenseRentree = (Spinner)findViewById(R.id.spinnerDepenseRentree);
        annee = (EditText)findViewById(R.id.editTextNumberAnnee);
        mois = (EditText)findViewById(R.id.editTextNumberMois);
        jour = (EditText)findViewById(R.id.editTextNumberJour);
        montant = (EditText)findViewById(R.id.editTextNumberMontant);
        raison = (EditText)findViewById(R.id.editTextTextPersonNameRaison);
        button = (Button)findViewById(R.id.buttonAjouter);

        button.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        Depense depense;
        Float montantFinal;
        DataBaseHelper dataBaseHelper;
        try {
            Log.i(TAG, "Entrée dans le try");
            this.testArguments(type, frequence, depenseRentree, annee, mois, jour, montant, raison);
            Log.i(TAG, "Arguments OK");
            if (depenseRentree.getSelectedItemPosition() == 1) {
                montantFinal = -1 * Float.valueOf(montant.getText().toString());
            } else {
                montantFinal = Float.valueOf(montant.getText().toString());
            }
            Log.i(TAG, "Avant depense ok");
            depense = new Depense(-1, raison.getText().toString(), type.getSelectedItem().toString(),date(annee.getText().toString(), mois.getText().toString(), jour.getText().toString()),frequence.getSelectedItem().toString(), montantFinal);
            Log.i(TAG, "Depense check");
            dataBaseHelper = new DataBaseHelper(this);
            Log.i(TAG, "new DataBase Check");
            dataBaseHelper.addOne(depense);

            switch (type.getSelectedItemPosition()) {
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    Boolean test = dataBaseHelper.addFix(depense);
                    if(!test) {
                        Log.i(TAG, "Erreur Ajout Table Fixe");
                        Toast.makeText(AddTransaction.this, "Erreur Ajout Table Fixe", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
            }

            Toast.makeText(AddTransaction.this, "Transaction ajoutée", Toast.LENGTH_SHORT).show();
        } catch(Exception e) {
            //Toast.makeText(AddTransaction.this, "Erreur en créant la nouvelle entrée", Toast.LENGTH_SHORT).show();
        }


    }

    private static String date(String annee, String mois, String jour) {
        if (jour.length()==1) {
            jour = "0"+jour;
        }
        if (mois.length()==1) {
            mois = "0"+mois;
        }
        return annee+"-"+mois+"-"+jour;
    }

    private void testArguments(Spinner type, Spinner frequence, Spinner depenseRentree, EditText annee, EditText mois, EditText jour, EditText montant, EditText raison) throws Exception {
        Log.i( TAG, "Test des Arguments" );
        if (type.getSelectedItemPosition() == 0) {
            Toast.makeText(AddTransaction.this, "Erreur type", Toast.LENGTH_SHORT).show();
            throw new Exception();
        }
        Log.i( TAG, "type ok" );
        if (frequence.getSelectedItemPosition() == 0) {
            Toast.makeText(AddTransaction.this, "Erreur frequence", Toast.LENGTH_SHORT).show();
            throw new Exception();
        }
        Log.i( TAG, "frequence ok" );
        if (depenseRentree.getSelectedItemPosition() == 0) {
            Toast.makeText(AddTransaction.this, "Erreur depenseRentree", Toast.LENGTH_SHORT).show();
            throw new Exception();
        }
        Log.i( TAG, "depenseRentree ok" );
        if (annee.getText().toString().equals("")) {
            Toast.makeText(AddTransaction.this, "Erreur annee", Toast.LENGTH_SHORT).show();
            throw new Exception();
        }
        Log.i( TAG, "annee ok" );
        if (mois.getText().toString().equals("")) {
            Toast.makeText(AddTransaction.this, "Erreur mois", Toast.LENGTH_SHORT).show();
            throw new Exception();
        }
        Log.i( TAG, "mois ok" );
        if (jour.getText().toString().equals("")) {
            Toast.makeText(AddTransaction.this, "Erreur jour", Toast.LENGTH_SHORT).show();
            throw new Exception();
        }
        Log.i( TAG, "jour ok" );
        if (montant.getText().toString().equals("")) {
            Toast.makeText(AddTransaction.this, "Erreur montant", Toast.LENGTH_SHORT).show();
            throw new Exception();
        }
        Log.i( TAG, "montant ok" );
        if (raison.getText().toString().equals("")) {
            Toast.makeText(AddTransaction.this, "Erreur raison", Toast.LENGTH_SHORT).show();
            throw new Exception();
        }
        Log.i( TAG, "raison ok" );
    }
}
