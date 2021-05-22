package com.example.drbot;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class findDrViewHolder extends RecyclerView.ViewHolder{

    TextView Dr_name,Dr_speciality;

    public findDrViewHolder(@NonNull View itemView){
        super(itemView);


        Dr_name = itemView.findViewById(R.id.doc_name);
        Dr_speciality = itemView.findViewById(R.id.speciality);

    }

}
