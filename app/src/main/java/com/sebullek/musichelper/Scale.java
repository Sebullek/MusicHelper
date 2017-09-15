package com.sebullek.musichelper;

import android.util.Log;

/**
 * Created by Sebullek on 15.05.2017.
 */

public class Scale {

    private static final String TAG = "Scales";

    private String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "H"};

    private int[] major_interval = {0, 2, 2, 1, 2, 2, 2, 1};

    private int[] minor_interval = {0, 2, 1, 2, 2, 1, 2, 2};

    private String scale;

    public String[][] allScales = new String [12][12];

    // C C# D D# E F F# G G# A A# H
    private String[][] chords = new String[12][2];

    public Scale(){

        for (int i = 0 ; i < chords.length ; i++) {
            for (int j = 0 ; j < chords[i].length ; j++) {
                chords[i][j] = "-";
            }
        }
        //allScales[0][] = {"Major",	"-",	"minor",	"-",	"minor",	"Major",	"-",	"Major",	"-",	"minor",	"-",	"dim"};


        for (int i = 0 ; i < allScales.length ; i++) {
            for (int j = 0 ; j < allScales[i].length ; j++) {

                if (i == 0) {
                    //First scale -> C major
                    if (j == 0 || j == 5 || j == 7) {
                        //major chords
                        allScales[i][j] = "Major";
                    } else {
                        if (j == 2 || j == 4 || j == 9) {
                            //minor chords
                            allScales[i][j] = "minor";
                        } else {
                            if (j == 11) {
                                //dim cohrds
                                allScales[i][j] = "dim";
                            } else {
                                //no chords
                                allScales[i][j] = "-";
                            }
                        }
                    }
                } else {
                    //The rest scales
                    if (j == 0) {
                        allScales[i][j] = allScales[i-1][11];
                    } else {
                        allScales[i][j] = allScales[i-1][j-1];
                    }
                }
            }
        }
    }

    public void addChord(String newNote, String newChord) {
        for (int i = 0 ; i < notes.length ; i++) {
            if (notes[i].equals(newNote)) {
                chords[i][0] = newNote;
                chords[i][1] = newChord;
            }
        }
    }

    public String getChords() {
        String allChords = "";
        for (int i = 0 ; i < chords.length ; i++) {
            //allChords += " " + chords[i][0] + chords[i][1];

                if (!chords[i][0].equals("-")) {
                    allChords += " " + chords[i][0];

                    if (!chords[i][1].equals("Major")) {
                        if (!chords[i][1].equals("minor")) {
                            allChords += "dim";

                        } else {
                            allChords += "m";
                        }
                    }
                }

        }
        return allChords;
    }

    public String getKey() {

        scale = "";
        for (int i = 0 ; i < allScales.length ; i++) {

            //Log.i(TAG, "SCALE nr " + i);

            for (int j = 0; j < allScales[i].length; j++) {
                //Log.i(TAG, "CHORD nr " + j + ": " + allScales[i][j]);
                //Log.i(TAG, "Chord = " + chords[j][0] + chords[j][1]);

                if (!chords[j][0].equals("-")) {
                    if (chords[j][1].equals(allScales[i][j])) {
                        //Log.i(TAG, "PASUJE!");
                        //Log.i(TAG, "Chord = " + chords[j][0] + chords[j][1]);

                    } else  {
                        break;
                    }
                }
                if (j == 11) {
                    scale += " " + notes[i];
                }
            }
        }
        if (scale.equals("")) {
            scale = "ERROR!";
        }
        return scale;
    }
}
