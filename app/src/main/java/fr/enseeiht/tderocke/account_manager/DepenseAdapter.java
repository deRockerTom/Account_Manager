package fr.enseeiht.tderocke.account_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DepenseAdapter extends ArrayAdapter<DepenseAffichee> {
    public DepenseAdapter(Context context, ArrayList<DepenseAffichee> depenses) {
        super(context, 0, depenses);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        DepenseAffichee depense = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        TextView id = (TextView) convertView.findViewById(R.id.textViewId);
        TextView nom = (TextView) convertView.findViewById(R.id.textViewNom);
        TextView date = (TextView) convertView.findViewById(R.id.textViewDate);
        TextView frequency = (TextView) convertView.findViewById(R.id.textViewFrequence);
        TextView value = (TextView) convertView.findViewById(R.id.textViewMontant);

        id.setText(depense.getId());
        nom.setText(depense.getNom());
        date.setText(depense.getDate());
        frequency.setText(depense.getFrequency());
        value.setText(String.valueOf(depense.getValue()));

        return convertView;
    }
}
