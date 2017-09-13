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

    Spinner sp_note_1;
    Spinner sp_chord_1;

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
        /*
        spinnersNote[0] =  (Spinner) findViewById(R.id.spNote1);
        ArrayAdapter<String> adp1= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Notes));
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersNote[0].setAdapter(adp1);


        spinnersChord[0] = (Spinner) findViewById(R.id.spChord1);
        ArrayAdapter<String> adp2= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Chords));
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersChord[0].setAdapter(adp2);

        spinnersNote[0].setOnItemSelectedListener(OnItemEvent);
        spinnersChord[0].setOnItemSelectedListener(OnItemEvent);
*/
    }

    private AdapterView.OnItemSelectedListener OnItemEvent = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //Log.i(TAG, "parent.getSelectedItem() = " + adapterView.getSelectedItem());


            for (int y = 0 ; y < 2 ; y++) {
                if (adapterView.getId() == spinnersNoteId[y]) {
                    Log.i(TAG, "spinnersNoteId["+y+"]");
                } else {
                    if (adapterView.getId() == spinnersChordId[y]) {
                        Log.i(TAG, "spinnersChord[" + y + "]");
                    }
                }
            }
            /*
            switch (adapterView.getId())
            {
                case R.id.spNote1:
                    //Log.i(TAG, "sp_note_1 OnItemSelectedListener");

                    if (spinnersNote[0].getSelectedItem().equals("-")) {
                        //Log.i(TAG, "PUSTO");
                        spinnersChord[0].setSelection(0);
                        spinnersChord[0].setEnabled(false);
                    }
                    else {
                        spinnersChord[0].setEnabled(true);
                        //sp_chord_1.setSelection(1);
                    }
                    break;
                case R.id.spChord1:
                    //Log.i(TAG, "sp_chord_1 OnItemSelectedListener");
                    break;
            }*/
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}
