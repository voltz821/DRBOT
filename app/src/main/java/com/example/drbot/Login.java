package com.example.drbot;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.emulators.EmulatedServiceSettings;


public class Login extends AppCompatActivity {

    private EditText clientemail,clientpass;
    private Button login,utd;
    private TextView lowertxt,forgotpass;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser muser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        clientemail = (EditText)findViewById(R.id.useremail);
        clientpass = (EditText)findViewById(R.id.userpassword);
        login = (Button)findViewById(R.id.lgnbtn);
        lowertxt = (TextView)findViewById(R.id.logintoreg);
        utd = (Button)findViewById(R.id.userlogin_to_drlogin);
        firebaseAuth = FirebaseAuth.getInstance();
        forgotpass = (TextView)findViewById(R.id.fg_pass);
     //   muser = firebaseAuth.getCurrentUser();


        //Forgot Password
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Forgot_Password.class));
            }
        });



        utd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Dr_Login.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clientEmail = clientemail.getText().toString().trim();
                String clientPass = clientpass.getText().toString().trim();
                if(clientEmail.isEmpty() || clientPass.isEmpty()){
                    Toast.makeText(Login.this,"Fill All Details",Toast.LENGTH_SHORT).show();
                }else {
                    validate(clientemail.getText().toString().trim(), clientpass.getText().toString().trim());
                }
            }
        });

        lowertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
    }

    private void validate(String username,String userPassword){
        firebaseAuth.signInWithEmailAndPassword(username,userPassword) .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,tpbutton.class));

                }else {
                    Toast.makeText(Login.this,"Login UnSuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}