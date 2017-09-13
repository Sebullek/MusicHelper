package com.sebullek.musichelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Sebullek on 15.05.2017.
 */

public class FindKey extends AppCompatActivity {



    Spinner[] spinnersNote = new Spinner[6];
    Spinner[] spinnersChord = new Spinner[6];

    int[] spinnersNoteId = new int[6];
    int[] spinnersChordId = new int[6];

    private static final String TAG = "Scales";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findkey);

        spinnersNoteId[0] = R.id.spNote1;
        spinnersNoteId[1] = R.id.spNote2;

        spinnersChordId[0] = R.id.spChord1;
        spinnersChordId[1] = R.id.spChord2;

        /*
        for (Spinner s: spinnersNoteId) {
            s = ?;
        }*/

        
        ArrayAdapter<String> adpNotes= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Notes));
        adpNotes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adpChords= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Chords));
        adpChords.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (int i = 0 ; i < 2 ; i++) {
            spinnersNote[i] =  (Spinner) findViewById(spinnersNoteId[i]);
            spinnersNote[i].setAdapter(adpNotes);

            spinnersChord[i] = (Spinner) findViewById(spinnersChordId[i]);
            spinnersChord[i].setAdapter(adpChords);

            spinnersNote[i].setOnItemSelectedListener(OnItemEvent);
            spinnersChord[i].setOnItemSelectedListener(OnItemEvent);
        }

    }

    private AdapterView.OnItemSelectedListener OnItemEvent = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //Log.i(TAG, "parent.getSelectedItem() = " + adapterView.getSelectedItem());

            //Find Spinner
            for (int y = 0 ; y < 2 ; y++) {
                if (adapterView.getId() == spinnersNoteId[y]) {

                    Log.i(TAG, "spinnersNoteId["+y+"]");

                    if (spinnersNote[y].getSelectedItem().equals("-")) {
                        //It is some SpinnerNote

                        //Log.i(TAG, "PUSTO");
                        spinnersChord[y].setSelection(0);
                        spinnersChord[y].setEnabled(false);
                    }
                    else {
                        spinnersChord[y].setEnabled(true);
                        //sp_chord_1.setSelection(1);
                    }
                } else {
                    if (adapterView.getId() == spinnersChordId[y]) {
                        //It is some SpinnerChord

                        Log.i(TAG, "spinnersChord[" + y + "]");
                    }
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}
