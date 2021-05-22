package com.example.drbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText username,usersurname,useremail,userphone,userpass;
    private RadioGroup radioGroup2;
    private Button signupbtn,dr_dir_btn;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;
    private StorageReference strRef;
    private FirebaseUser muser;
    String name,surname,email,phno,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUIViews();

        dr_dir_btn = (Button)findViewById(R.id.dr_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        muser = firebaseAuth.getCurrentUser();
       // myRef = FirebaseDatabase.getInstance().getReference().child("users");

        //to keep logged in
        /*if(muser != null){
            finish();
            startActivity(new Intent(MainActivity.this,tpbutton.class));
        }*/

        dr_dir_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Dr_register.class);
                startActivity(intent);

            }
        });


        TextView regblwtxt = (TextView) findViewById(R.id.regtologin);

        regblwtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, Login.class);
                startActivity(intent);

            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (validate()) {
                    name = username.getText().toString().trim();
                    surname = usersurname.getText().toString().trim();
                    email = useremail.getText().toString().trim();
                    phno = userphone.getText().toString().trim();
                    password = userpass.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendUserData();
                                startActivity(new Intent(MainActivity.this, Login.class));
                                Toast.makeText(MainActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


    private void setupUIViews() {
            username = (EditText) findViewById(R.id.userName);
            usersurname = (EditText) findViewById(R.id.userSurname);
            useremail = (EditText) findViewById(R.id.userEmail);
            userphone = (EditText) findViewById(R.id.userPhone);
            userpass = (EditText) findViewById(R.id.userPass);
            signupbtn = (Button) findViewById(R.id.signupbtn);
        }



        private Boolean validate() {
            Boolean result = false;

            String name = username.getText().toString();
            String surname = usersurname.getText().toString();
            String email = useremail.getText().toString();
            String phno = userphone.getText().toString();
            String password = userpass.getText().toString();

            if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || phno.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this,"Fill All Details",Toast.LENGTH_SHORT).show();
            } else {
                result = true;
            }
            return result;
        }

        private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Users").child(firebaseAuth.getUid());
        userProfile userProfile = new userProfile(name,surname,email,phno,password);
        myRef.setValue(userProfile);
        }

 }
