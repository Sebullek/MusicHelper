package com.sebullek.musichelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button b_scales;
    private Button b_find_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_scales = (Button)findViewById(R.id.b_scales);
        b_find_key = (Button)findViewById(R.id.b_findkey);

        b_scales.setOnClickListener(this);
        b_find_key.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        Intent intent;
        switch (view.getId()) {

            case R.id.b_scales:

                intent = new Intent(this, Scales.class);
                startActivity(intent);

                break;

            case R.id.b_findkey:

                intent = new Intent(this, FindKey.class);
                startActivity(intent);

                break;

            default:
                break;
        }

    }

}
