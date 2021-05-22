package com.example.drbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dr_Login extends AppCompatActivity {

    private EditText dr_email,dr_pass;
    private Button login,user;
    private TextView lowertext,dr_fg_password;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr__login);

        dr_email = (EditText)findViewById(R.id.dr_login_email);
        dr_pass = (EditText)findViewById(R.id.dr_login_pass);
        login = (Button)findViewById(R.id.dr_login_button);
        lowertext = (TextView)findViewById(R.id.drlog_to_drlreg);
        user = (Button)findViewById(R.id.drlogin_to_userlogin);
        firebaseAuth = FirebaseAuth.getInstance();
        dr_fg_password = (TextView) findViewById(R.id.fg_password);

        dr_fg_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dr_Login.this,Forgot_Password.class));
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dr_Login.this,Login.class));
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String drEmail = dr_email.getText().toString().trim();
                String drPass = dr_pass.getText().toString().trim();
                if(drEmail.isEmpty() || drPass.isEmpty()){
                    Toast.makeText(Dr_Login.this,"Fill All Details",Toast.LENGTH_SHORT).show();
                }else {
                    validate(dr_email.getText().toString().trim(), dr_pass.getText().toString().trim());
                }
            }
        });

        lowertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dr_Login.this,Dr_register.class));
            }
        });

    }

    private void validate(String drname,String drPassword){
        firebaseAuth.signInWithEmailAndPassword(drname,drPassword) .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Dr_Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Dr_Login.this,Dr_Imp_Note.class));

                }else {
                    Toast.makeText(Dr_Login.this,"Login UnSuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}