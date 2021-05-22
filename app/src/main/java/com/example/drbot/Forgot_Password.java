package com.example.drbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {

    private TextView email_view;
    private Button send_mail;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        email_view = findViewById(R.id.mailview);
        send_mail = findViewById(R.id.send);
        mAuth = FirebaseAuth.getInstance();

        send_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_view.getText().toString();
                if (email.isEmpty()){
                    Toast.makeText(Forgot_Password.this,"Please Enter Your Email",Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Forgot_Password.this,"Please Check Your Email",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(Forgot_Password.this,"Email Not Sent !",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
}