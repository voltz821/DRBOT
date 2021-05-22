package com.example.drbot;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings extends Fragment {

    Button chngpass;
    Button chngprofile;

    public Settings() {
        // Required empty public constructor
    }


    public static Settings newInstance() {
        Settings fragment = new Settings();
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
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        chngpass = v.findViewById(R.id.updt_pass);

        chngpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChangePassword secondfragment = new ChangePassword();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container,secondfragment).commit();


            }
        });

        chngprofile = v.findViewById(R.id.updt_prof);

        chngprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateProfile firstfragment = new UpdateProfile();
                FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                transaction1.replace(R.id.container,firstfragment).commit();
            }
        });


        return v;
    }
}