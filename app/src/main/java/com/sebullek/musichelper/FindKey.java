package com.sebullek.musichelper;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by Sebullek on 15.05.2017.
 */

public class FindKey extends AppCompatActivity {



    Spinner[] spinnersNote = new Spinner[6];
    Spinner[] spinnersChord = new Spinner[6];

    int[] spinnersNoteId = new int[6];
    int[] spinnersChordId = new int[6];

    private static final String TAG = "Scales";


    private static final String DEFAULD_SPINNER_NOTE_ID = "spNote";
    private static final String DEFAULD_SPINNER_CHORD_ID = "spChord";


    Button buttonGetScale;
    TextView textVewScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findkey);



        for (int i = 0 ; i < 6 ; i++) {

            //For objects where ID's names are the same with different numbers
            //spinnersNoteId[0] = R.id.spNote1;
            //spinnersChordId[0] = R.id.spChord1;
            spinnersNoteId[i] = getResources().getIdentifier(DEFAULD_SPINNER_NOTE_ID + (i+1), "id", "com.sebullek.musichelper");
            spinnersChordId[i] = getResources().getIdentifier(DEFAULD_SPINNER_CHORD_ID + (i+1), "id", "com.sebullek.musichelper");
        }

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

        for (int i = 0 ; i < 6 ; i++) {
            spinnersNote[i] =  (Spinner) findViewById(spinnersNoteId[i]);
            spinnersNote[i].setAdapter(adpNotes);

            spinnersChord[i] = (Spinner) findViewById(spinnersChordId[i]);
            spinnersChord[i].setAdapter(adpChords);

            spinnersNote[i].setOnItemSelectedListener(OnItemEvent);
            spinnersChord[i].setOnItemSelectedListener(OnItemEvent);
        }


        buttonGetScale = (Button) findViewById(R.id.bGetScale);
        textVewScale = (TextView) findViewById(R.id.tvScale);
        buttonGetScale.setOnClickListener(GetScale);
    }

    private AdapterView.OnItemSelectedListener OnItemEvent = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //Log.i(TAG, "parent.getSelectedItem() = " + adapterView.getSelectedItem());

            //Find Spinner
            for (int y = 0 ; y < 6 ; y++) {
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




    private View.OnClickListener GetScale = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Scale oScale = new Scale();

            for (int i = 0 ; i < 6 ; i++) {
                oScale.addChord(spinnersNote[i].getSelectedItem().toString(),
                        spinnersChord[i].getSelectedItem().toString());
            }

            String sScale = oScale.getChords();


            String allScales = "";
            for (int i = 0 ; i < oScale.allScales.length ; i++) {
                for (int j = 0; j < oScale.allScales[i].length; j++) {
                    allScales += oScale.allScales[i][j];
                }
                allScales += " \n ||";
            }
            //Log.i(TAG, "allScales = " + oScale.getScale());

            textVewScale.setText("SCALES: " + oScale.getKey());
        }
    };
}
