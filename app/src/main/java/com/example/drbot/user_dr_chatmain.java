package com.example.drbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drbot.Utills.Chat;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class user_dr_chatmain extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText input;
    ImageView img_send,msg_send;
    TextView docs_name,status;
    String otheruserID;
    DatabaseReference mUserRef,smsRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String OtherDoctorName;
    FirebaseRecyclerOptions<Chat>options;
    FirebaseRecyclerAdapter<Chat,ChatMyViewHolder>adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dr_chatmain);

        otheruserID = getIntent().getStringExtra("OtherUserID");

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        input = findViewById(R.id.inputsms);
        msg_send = findViewById(R.id.btnSend);
        docs_name = findViewById(R.id.dr_NAME);
        status = findViewById(R.id.status);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Doctors");
        smsRef = FirebaseDatabase.getInstance().getReference().child("Message");


        LoadOtherUser();

        msg_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }
        });

        LoadSMS();


    }

    private void LoadSMS() {
        options = new FirebaseRecyclerOptions.Builder<Chat>().setQuery(smsRef.child(mUser.getUid()).child(otheruserID),Chat.class).build();
        adapter = new FirebaseRecyclerAdapter<Chat, ChatMyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ChatMyViewHolder holder, int position, @NonNull Chat model) {
                if (model.getUserID().equals(mUser.getUid())){
                    holder.firstUserText.setVisibility(View.GONE);
                    holder.firstUserProfile.setVisibility(View.GONE);
                    holder.secondUserText.setVisibility(View.VISIBLE);
                    holder.secondUserProfile.setVisibility(View.VISIBLE);

                    holder.secondUserText.setText(model.getSms());
                }
                else {
                    holder.firstUserText.setVisibility(View.VISIBLE);
                    holder.firstUserProfile.setVisibility(View.VISIBLE);
                    holder.secondUserText.setVisibility(View.GONE);
                    holder.secondUserProfile.setVisibility(View.GONE);

                    holder.firstUserText.setText(model.getSms());
                }
            }

            @NonNull
            @Override
            public ChatMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview_textmsgbox,parent,false);
                return new ChatMyViewHolder(view);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }


    private void sendSMS() {
        String sms = input.getText().toString();
        if (sms.isEmpty())
        {
         Toast.makeText(this,"Enter Text Below to send Message",Toast.LENGTH_SHORT).show();
        }
        else
            {
                HashMap hashMap = new HashMap();
                hashMap.put("sms",sms);
                hashMap.put("userID",mUser.getUid());
                smsRef.child(otheruserID).child(mUser.getUid()).push().updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful())
                        {
                            smsRef.child(mUser.getUid()).child(otheruserID).push().updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if (task.isSuccessful()){
                                                        input.setText(null);
                                                        Toast.makeText(user_dr_chatmain.this,"Message Sent",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
            }
    }



    private void LoadOtherUser() {
        mUserRef.child(otheruserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    OtherDoctorName = snapshot.child("dr_name").getValue().toString();

                    docs_name.setText(OtherDoctorName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(user_dr_chatmain.this,""+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


}