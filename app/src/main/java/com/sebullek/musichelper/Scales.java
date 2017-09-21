package com.sebullek.musichelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Sebullek on 15.05.2017.
 */

public class Scales extends AppCompatActivity {


    Spinner spinnersNote;
    Spinner spinnersChord;

    Button buttonGetScale;
    TextView textVewScale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scales);


        spinnersNote = (Spinner) findViewById(R.id.spNote);
        spinnersChord = (Spinner) findViewById(R.id.spChord);

        ArrayAdapter<String> adpNotes= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Notes));
        adpNotes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adpChords= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Scales));
        adpChords.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnersNote.setAdapter(adpNotes);
        spinnersChord.setAdapter(adpChords);

        spinnersNote.setOnItemSelectedListener(OnItemEvent);
        spinnersChord.setOnItemSelectedListener(OnItemEvent);


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
                if (adapterView.getId() == R.id.spNote) {

                    if (spinnersNote.getSelectedItem().equals("-")) {
                        //It is some SpinnerNote

                        //Log.i(TAG, "PUSTO");
                        spinnersChord.setSelection(0);
                        spinnersChord.setEnabled(false);
                    }
                    else {
                        spinnersChord.setEnabled(true);
                        //sp_chord_1.setSelection(1);
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

            textVewScale.setText("SCALE: " + oScale.getScale(
                    spinnersNote.getSelectedItem().toString(),
                    spinnersChord.getSelectedItem().toString()));
        }
    };
}
