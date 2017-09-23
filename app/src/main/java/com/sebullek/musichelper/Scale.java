package com.sebullek.musichelper;

import android.util.Log;

/**
 * Created by Sebullek on 15.05.2017.
 */

public class Scale {

    private static final String TAG = "Scales";

    private static final String[] NOTES_FOR_MAJOR_SCALE = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private static final String[] NOTES_FOR_MINOR_SCALE = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
    //private String[] notes = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
    //private String[] notes = new String[12];

    private int[] major_interval = {0, 2, 2, 1, 2, 2, 2, 1};

    private int[] minor_interval = {0, 2, 1, 2, 2, 1, 2, 2};

    private int[] blues_interval = {0, 3, 2, 1, 1, 3, 2};
    private int[] minor_pentatonic_interval = {0, 3, 2, 2, 3, 2};
    private int[] major_pentatonic_interval = {0, 2, 2, 3, 2, 3};


    public String[][] allScales = new String [12][12];

    // C C# D D# E F F# G G# A A# B
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
                        allScales[i][j] = "major";
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
        for (int i = 0 ; i < NOTES_FOR_MAJOR_SCALE.length ; i++) {
            if (NOTES_FOR_MAJOR_SCALE[i].equals(newNote)) {
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

                    if (!chords[i][1].equals("major")) {
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

        String key = "";
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
                    key += " " + NOTES_FOR_MAJOR_SCALE[i];
                }
            }
        }
        if (key.equals("")) {
            key = "NO KEY!";
        }
        return key;
    }

    public String getScale(String note, String chord) {


        int index = 0;

        index = indexOfNotes(note);


        int[] intervals;

        switch (chord) {
            case "major":
                intervals = new int[major_interval.length];
                for (int i = 0 ; i < major_interval.length ; i++) {
                    intervals[i] = major_interval[i];
                }
                break;
            case "minor":
                intervals = new int[minor_interval.length];
                for (int i = 0 ; i < minor_interval.length ; i++) {
                    intervals[i] = minor_interval[i];
                }
                break;
            case "blues":
                intervals = new int[blues_interval.length];
                for (int i = 0 ; i < blues_interval.length ; i++) {
                    intervals[i] = blues_interval[i];
                }
                break;
            case "minor pentatonic":
                intervals = new int[minor_pentatonic_interval.length];
                for (int i = 0 ; i < minor_pentatonic_interval.length ; i++) {
                    intervals[i] = minor_pentatonic_interval[i];
                }
                break;
            case "major pentatonic":
                intervals = new int[major_pentatonic_interval.length];
                for (int i = 0 ; i < major_pentatonic_interval.length ; i++) {
                    intervals[i] = major_pentatonic_interval[i];
                }
                break;
            default:
                intervals = new int[8];
                break;
        }

        String[] notes = new String[12];
        if (chord.equals("minor") || chord.equals("minor pentatonic")) {
            for (int i = 0 ; i < NOTES_FOR_MINOR_SCALE.length ; i++) {
                notes[i] = NOTES_FOR_MINOR_SCALE[i];
            }
        } else {
            for (int i = 0 ; i < NOTES_FOR_MAJOR_SCALE.length ; i++) {
                notes[i] = NOTES_FOR_MAJOR_SCALE[i];
            }
        }

        String[] scale = new String[intervals.length];


        int copy_index = index;
        for (int i = 0 ; i < scale.length ; i++) {
            scale[i] = notes[index];
            if(i != (scale.length - 1)) {
                index += intervals[i+1];
            } else {
                index = copy_index;
            }

            if (index > 11)
            {
                index -= 12;
            }

        }

        String finalScale = "";
        for (int i = 0 ; i < scale.length ; i++) {
            finalScale += scale[i] + " ";
        }
        return finalScale;
    }

    public String transposedChord(String note, String chord, int transpose_number) {

        String transposed_chord = "";
        int index = 0;

        index = indexOfNotes(note);

        index = index + transpose_number;
        if (index < 0) {
            index = 12 + index;
        } else {
            if (index > 11) {
                index = index - 12;
            }
        }
        transposed_chord += NOTES_FOR_MAJOR_SCALE[index] + chord;

        return transposed_chord;
    }



    private int indexOfNotes(String note) {
        int index = 0;

        //Find the note;
        for (int i = 0 ; i < NOTES_FOR_MAJOR_SCALE.length ; i++) {
            if(NOTES_FOR_MAJOR_SCALE[i].equals(note)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
