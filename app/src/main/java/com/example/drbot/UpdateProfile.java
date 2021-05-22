package com.example.drbot;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UpdateProfile extends Fragment {

    EditText uname, usurname, uphoneno;
    Button updt;

    DatabaseReference mUserRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String username,usersurname,userphno;

    public UpdateProfile() {
        // Required empty public constructor
    }


    public static UpdateProfile newInstance() {
        UpdateProfile fragment = new UpdateProfile();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_profile, container, false);

        uname = v.findViewById(R.id.updt_dr_name);
        usurname = v.findViewById(R.id.chng_speciality);
        uphoneno = v.findViewById(R.id.updtphone);
        updt = v.findViewById(R.id.updtBTN);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users");


        mUserRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                     username = snapshot.child("username").getValue().toString();
                     usersurname = snapshot.child("usersurname").getValue().toString();
                     userphno = snapshot.child("userphno").getValue().toString();

                    uname.setText(username);
                    usurname.setText(usersurname);
                    uphoneno.setText(userphno);
                } else {
                    Toast.makeText(getActivity(), "Data not Exists", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "" + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

       updt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    if (isNameChanged() || isSurnameChanged() || isPhoneNoChanged()) {
                        Toast.makeText(getActivity(), "Data has been Updated", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getActivity(), "Data is same and not Updated", Toast.LENGTH_SHORT).show();
                }
            }


        });


        return v;
    }

  /*  public void update(View view){



    }*/


    private boolean isNameChanged() {
        if(!username.equals(uname.getText().toString())){

            mUserRef.child(mAuth.getUid()).child("username").setValue(uname.getText().toString());
            username = uname.getText().toString();
            return true;


        }else {
            return false;
        }
    }

    private boolean isSurnameChanged(){
        if(!usersurname.equals(usurname.getText().toString())){

            mUserRef.child(mAuth.getUid()).child("usersurname").setValue(usurname.getText().toString());
            usersurname = usurname.getText().toString();
            return true;


        }else {
            return false;
        }
    }

    private boolean isPhoneNoChanged() {
        if(!userphno.equals(uphoneno.getText().toString())){

            mUserRef.child(mAuth.getUid()).child("userphno").setValue(uphoneno.getText().toString());
            userphno = uphoneno.getText().toString();
            return true;


        }else {
            return false;
        }
    }

    private Boolean validate() {
        Boolean result = false;

        String username = uname.getText().toString();
        String usersurname = usurname.getText().toString();
        String userphno = uphoneno.getText().toString();

        if (username.isEmpty() || usersurname.isEmpty() || userphno.isEmpty()){
            Toast.makeText(getActivity(), "Fill all details" , Toast.LENGTH_SHORT).show();
        }else {
            result = true;
        }
        return result;
    }





}