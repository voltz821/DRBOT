package com.example.drbot;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatMyViewHolder extends RecyclerView.ViewHolder {

    TextView firstUserText,secondUserText;
    ImageView firstUserProfile,secondUserProfile;

    public ChatMyViewHolder(@NonNull View itemView) {
        super(itemView);

        firstUserProfile = itemView.findViewById(R.id.firstUserProfile);
        secondUserProfile = itemView.findViewById(R.id.SecondUserProfile);
        firstUserText = itemView.findViewById(R.id.firstUserText);
        secondUserText = itemView.findViewById(R.id.secondUserText);
    }
}
