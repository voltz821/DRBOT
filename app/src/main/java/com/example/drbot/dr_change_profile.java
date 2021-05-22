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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link dr_change_profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class dr_change_profile extends Fragment {

    EditText drname, drspeciality, drphoneno;
    Button updatebtn;

    DatabaseReference mUserRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String dr_name, dr_speciality, dr_phone;



    public dr_change_profile() {

    }


    public static dr_change_profile newInstance() {
        dr_change_profile fragment = new dr_change_profile();

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
        View v = inflater.inflate(R.layout.fragment_dr_change_profile, container, false);

        drname = v.findViewById(R.id.updt_dr_name);
        drspeciality = v.findViewById(R.id.chng_speciality);
        drphoneno = v.findViewById(R.id.updtphone);
        updatebtn = v.findViewById(R.id.updtBTN);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Doctors");

        mUserRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    dr_name = snapshot.child("dr_name").getValue().toString();
                    dr_speciality = snapshot.child("dr_speciality").getValue().toString();
                    dr_phone = snapshot.child("dr_phone").getValue().toString();

                    drname.setText(dr_name);
                    drspeciality.setText(dr_speciality);
                    drphoneno.setText(dr_phone);
                } else {
                    Toast.makeText(getActivity(), "Data not Exists", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "" + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    if (isNameChanged() || isSpecialityChanged() || isPhoneChanged()) {
                        Toast.makeText(getActivity(), "Data has been Updated", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getActivity(), "Data is same and not Updated", Toast.LENGTH_SHORT).show();
                }
            }


        });

        return v;
    }

    private boolean validate() {
        Boolean result = false;

        String dr_name = drname.getText().toString();
        String dr_speciality = drspeciality.getText().toString();
        String dr_phone = drphoneno.getText().toString();

        if (dr_name.isEmpty() || dr_speciality.isEmpty() || dr_phone.isEmpty()){
            Toast.makeText(getActivity(), "Fill all details" , Toast.LENGTH_SHORT).show();
        }else {
            result = true;
        }
        return result;
    }

    private boolean isPhoneChanged() {
        if(!dr_phone.equals(drphoneno.getText().toString())){

            mUserRef.child(mAuth.getUid()).child("dr_phone").setValue(drphoneno.getText().toString());
            dr_phone = drphoneno.getText().toString();
            return true;
        }else {
            return false;
        }
    }

    private boolean isSpecialityChanged() {
        if(!dr_speciality.equals(drspeciality.getText().toString())){

            mUserRef.child(mAuth.getUid()).child("dr_speciality").setValue(drspeciality.getText().toString());
            dr_speciality = drspeciality.getText().toString();
            return true;
        }else {
            return false;
        }
    }

    private boolean isNameChanged() {
        if(!dr_name.equals(drname.getText().toString())){

            mUserRef.child(mAuth.getUid()).child("dr_name").setValue(drname.getText().toString());
            dr_name = drname.getText().toString();
            return true;
        }else {
            return false;
        }
    }



}