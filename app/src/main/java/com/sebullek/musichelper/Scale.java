package com.sebullek.musichelper;

/**
 * Created by Sebullek on 15.05.2017.
 */

public class Scale {

    private String[] sounds = {"C", "D", "E", "F", "G", "A", "H"};

    private int[] major_interval = {0, 2, 2, 1, 2, 2, 2, 1};

    private int[] minor_interval = {0, 2, 1, 2, 2, 1, 2, 2};

    private String key, scale;

    public Scale(){
        key = "C";
        scale = "minor";
    }

    public Scale(String key, String scale){
        this.key = key;
        this.scale = scale;
    }
}
