package com.example.drbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.drbot.Utills.users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class dr_search_user extends AppCompatActivity {

    FirebaseRecyclerOptions<users> options;
    FirebaseRecyclerAdapter<users,findUserViewHolder> adapter;
    Toolbar toolbar;

    DatabaseReference mUserRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_search_user);

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search users");

        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        recyclerView = findViewById(R.id.recyclerVieW);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LoadUsers("");


    }

    private void LoadUsers(String s) {
        Query query = mUserRef.orderByChild("username").startAt(s).endAt(s+"\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<users>().setQuery(query, users.class).build();
        adapter = new FirebaseRecyclerAdapter<users,findUserViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull findUserViewHolder holder, int position, @NonNull users model) {
                holder.username.setText(model.getusername());
                holder.usersurname.setText(model.getusersurname());

                //For loading next ASctivity or for messaging doctor
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(dr_search_user.this,dr_user_chatmain.class);
                        intent.putExtra("OtherUserID",getRef(position).getKey().toString());
                        startActivity(intent);
                    }
                });

            }
            @NonNull
            @Override
            public findUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_find_user,parent,false);
                return new findUserViewHolder(view);
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