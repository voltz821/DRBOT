package com.example.drbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dr_register extends AppCompatActivity{

    private EditText drname,dremail,drphone,drpassword;
    private Spinner drspecial;
    private Button drsubmit,drtouser;
    private TextView drreg_to_Login;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    String name,email,phno,password,specialist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_register);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        drreg_to_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dr_register.this,Dr_Login.class);
                startActivity(intent);
            }
        });


        drtouser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dr_register.this,MainActivity.class);
                startActivity(intent);
            }
        });

        drsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    name = drname.getText().toString().trim();
                    email = dremail.getText().toString().trim();
                    phno = drphone.getText().toString().trim();
                    password = drpassword.getText().toString().trim();
                    specialist = drspecial.getSelectedItem().toString().trim();
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            sendUserData();
                            startActivity(new Intent(Dr_register.this,Dr_Login.class));
                            Toast.makeText(Dr_register.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Dr_register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void setupUIViews() {
        drname = (EditText) findViewById(R.id.dr_name);
        dremail = (EditText) findViewById(R.id.dr_email);
        drphone = (EditText) findViewById(R.id.dr_phone);
        drpassword = (EditText) findViewById(R.id.dr_password);
        drsubmit = (Button) findViewById(R.id.dr_submit);
        drtouser = (Button) findViewById(R.id.dr_user);
        drreg_to_Login = (TextView) findViewById(R.id.drreg_to_drlogin);
        drspecial = (Spinner)findViewById(R.id.spinner);
    }

    private Boolean validate(){
        Boolean result = false;

        String name = drname.getText().toString();
        String email = dremail.getText().toString();
        String phno = drphone.getText().toString();
        String password = drpassword.getText().toString();
        String specialist = drspecial.getSelectedItem().toString();

        if (name.isEmpty() || email.isEmpty() || phno.isEmpty() || password.isEmpty() || specialist.isEmpty()) {
            Toast.makeText(Dr_register.this,"Fill All Details",Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }

    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Doctors").child(firebaseAuth.getUid());
        DrProfile drProfile = new DrProfile(name,email,phno,password,specialist);
        myRef.setValue(drProfile);
    }


}