package com.example.drbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Dr_Imp_Note extends AppCompatActivity {

    private Button agree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr__imp__note);

        agree = (Button)findViewById(R.id.agreed);

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dr_Imp_Note.this,Dr_Home.class));
            }
        });

    }
}