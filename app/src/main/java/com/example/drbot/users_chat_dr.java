package com.example.drbot;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;

import com.example.drbot.Utills.Doctors;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class users_chat_dr extends Fragment {

    FirebaseRecyclerOptions<Doctors>options;
    FirebaseRecyclerAdapter<Doctors,findDrViewHolder>adapter;
    Toolbar toolbar;
    DatabaseReference mUserRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    RecyclerView recyclerView;




    public users_chat_dr() {
        // Required empty public constructor
    }


    public static users_chat_dr newInstance() {
        users_chat_dr fragment = new users_chat_dr();
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

        View v = inflater.inflate(R.layout.fragment_users_chat_dr, container, false);

        toolbar = v.findViewById(R.id.app_bar);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Search Doctors");

       /* recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/

        mUserRef = FirebaseDatabase.getInstance().getReference().child("Doctors");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        loadUsers("");

        return v;
    }

    private void loadUsers(String s) {
        Query query = mUserRef.orderByChild("dr_name").startAt(s).endAt(s+"\uf8ff");
    options = new FirebaseRecyclerOptions.Builder<Doctors>().setQuery(query,Doctors.class).build();
    adapter = new FirebaseRecyclerAdapter<Doctors,findDrViewHolder>(options) {
        @Override
        protected void onBindViewHolder(@NonNull findDrViewHolder holder, int position, @NonNull Doctors model) {

            holder.Dr_name.setText(model.getDr_name());
            holder.Dr_speciality.setText(model.getDr_speciality());

        }

        @NonNull
        @Override
        public findDrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_find_dr,parent,false);


            return new findDrViewHolder(view);
        }
    };

 /*   adapter.startListening();
    recyclerView.setAdapter(adapter);*/

    }


}



