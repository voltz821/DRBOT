package com.example.drbot;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class findUserViewHolder extends RecyclerView.ViewHolder {

    TextView username,usersurname;

    public findUserViewHolder(@NonNull View itemView){
        super(itemView);


        username = itemView.findViewById(R.id.username);
        usersurname = itemView.findViewById(R.id.usersurname);

    }


}
