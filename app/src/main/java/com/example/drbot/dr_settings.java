package com.example.drbot;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class dr_settings extends Fragment {

    Button chngpass;
    Button chngprofile;

    public dr_settings() {
        // Required empty public constructor
    }


    public static dr_settings newInstance() {
        dr_settings fragment = new dr_settings();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup dr_container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dr_settings, dr_container, false);

        chngpass = v.findViewById(R.id.updt_dr_pass);

        chngpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dr_change_password thirdfragment = new dr_change_password();
                FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
                transaction3.replace(R.id.dr_container,thirdfragment).commit();


            }
        });

        chngprofile = v.findViewById(R.id.updt_dr_prof);

        chngprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dr_change_profile fourthfragment = new dr_change_profile();
                FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
                transaction4.replace(R.id.dr_container,fourthfragment).commit();
            }
        });




        return v;
    }
}