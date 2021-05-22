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


public class dr_change_password extends Fragment {

    Button passupdtbtn;
    EditText newpass;
    FirebaseUser firebaseUser;



    public dr_change_password() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment dr_change_password.
     */
    // TODO: Rename and change types and number of parameters
    public static dr_change_password newInstance(String param1, String param2) {
        dr_change_password fragment = new dr_change_password();

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
        View v = inflater.inflate(R.layout.fragment_dr_change_password, container, false);

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