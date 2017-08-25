package com.example.android.fitme;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.fitme.data.AlimentContract;
import com.example.android.fitme.domain.Aliment;

/**
 * Created by vlad on 23.08.2017.
 */

public class AddAlimentDialog extends DialogFragment {

    public static AddAlimentDialog newInstance() {
        AddAlimentDialog addAlimentDialog = new AddAlimentDialog();
        return addAlimentDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View addDialogView = inflater.inflate(R.layout.add_aliment_dialog, null, false);
        final EditText alimentNameET = (EditText) addDialogView.findViewById(R.id.et_aliment_name);
        final EditText alimentProtET = (EditText) addDialogView.findViewById(R.id.et_aliment_protein);
        final EditText alimentCarbsET = (EditText) addDialogView.findViewById(R.id.et_aliment_carbs);
        final EditText alimentFatET = (EditText) addDialogView.findViewById(R.id.et_aliment_fats);
        Button addAlimentBtn = (Button) addDialogView.findViewById(R.id.btn_ok_add_aliment);
        Button cancelAddAlimentBtn = (Button) addDialogView.findViewById(R.id.btn_cancel_add_aliment);

        addAlimentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = alimentNameET.getText().toString();
                String protein = alimentProtET.getText().toString();
                String carbs = alimentCarbsET.getText().toString();
                String fat = alimentFatET.getText().toString();

                if (validateInput(name, protein, carbs, fat)) {
                    Aliment aliment = new Aliment(name, protein, carbs, fat);
                    addAliment(aliment);
                }
            }
        });

        cancelAddAlimentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return addDialogView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog addAlimentDialog = super.onCreateDialog(savedInstanceState);
        addAlimentDialog.setTitle(R.string.enter_aliment_label);
        return addAlimentDialog;
    }

    private boolean validateInput(String name, String protein, String carbs, String fats) {
        if ((name.length() != 0) && (protein.length() != 0) && (carbs.length() != 0) && (fats.length() != 0)) {
            return true;
        } else {
            return false;
        }
    }

    public void addAliment(Aliment aliment) {
        // Insert new task data via a ContentResolver
        // Create new empty ContentValues object
        ContentValues contentValues = new ContentValues();
        // Put the task description and selected mPriority into the ContentValues

        contentValues.put(AlimentContract.AlimentEntry.COLUMN_NAME, aliment.getName());
        contentValues.put(AlimentContract.AlimentEntry.COLUMN_PROTEINS, aliment.getProtein());
        contentValues.put(AlimentContract.AlimentEntry.COLUMN_CARBS, aliment.getCarbs());
        contentValues.put(AlimentContract.AlimentEntry.COLUMN_FATS, aliment.getFats());

        // Insert the content values via a ContentResolver
        Uri uri = getActivity().getContentResolver().insert(AlimentContract.AlimentEntry.CONTENT_URI, contentValues);

        // Display the URI that's returned with a Toast
        // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete
        if (uri != null) {
            Toast.makeText(getDialog().getContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }

        // Finish activity (this returns back to MainActivity)
        getDialog().dismiss();
        getActivity().recreate();

    }

}
