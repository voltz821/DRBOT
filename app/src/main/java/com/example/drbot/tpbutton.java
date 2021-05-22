package com.example.drbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tpbutton extends AppCompatActivity {

    private Button strtbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpbutton);

        strtbtn=findViewById(R.id.btnstart);

        strtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(tpbutton.this,home.class);
                startActivity(intent);
            }
        });

    }
}