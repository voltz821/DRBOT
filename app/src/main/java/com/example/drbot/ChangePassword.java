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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangePassword#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePassword extends Fragment {

     Button passupdtbtn;
     EditText newpass;
    FirebaseUser firebaseUser;


    public ChangePassword() {
        // Required empty public constructor
    }


    public static ChangePassword newInstance() {
        ChangePassword fragment = new ChangePassword();
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
        View v =  inflater.inflate(R.layout.fragment_change_password, container, false);

        passupdtbtn = v.findViewById(R.id.btnUpdatePass);
        newpass = v.findViewById(R.id.updtPass);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();




        passupdtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernewpassword = newpass.getText().toString();
                firebaseUser.updatePassword(usernewpassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getActivity(),"Password Changed",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(),"Failed To Update Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });





        return v;
    }
}