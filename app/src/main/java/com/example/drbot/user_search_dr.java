package com.example.drbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
//import android.widget.Toolbar;

import com.example.drbot.Utills.Doctors;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class user_search_dr extends AppCompatActivity {

    FirebaseRecyclerOptions<Doctors>options;
    FirebaseRecyclerAdapter<Doctors,findDrViewHolder>adapter;
    Toolbar toolbar;

    DatabaseReference mUserRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_dr);

       toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Find Doctors");

        mUserRef = FirebaseDatabase.getInstance().getReference().child("Doctors");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        recyclerView = findViewById(R.id.recyclerVieW);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LoadUsers("");


    }

    private void LoadUsers(String s) {
        Query query = mUserRef.orderByChild("dr_name").startAt(s).endAt(s+"\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<Doctors>().setQuery(query, Doctors.class).build();
        adapter = new FirebaseRecyclerAdapter<Doctors, findDrViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull findDrViewHolder holder, int position, @NonNull Doctors model) {
            holder.Dr_name.setText(model.getDr_name());
            holder.Dr_speciality.setText(model.getDr_speciality());

                //For loading next ASctivity or for messaging doctor
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_search_dr.this,user_dr_chatmain.class);
                    intent.putExtra("OtherUserID",getRef(position).getKey().toString());
                    startActivity(intent);
                }
            });



            }




            @NonNull
            @Override
            public findDrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_find_dr,parent,false);
                return new findDrViewHolder(view);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                LoadUsers(s);
                return false;
            }
        });
        return true;
    }



}