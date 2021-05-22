package com.example.drbot.Utills;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drbot.R;

import java.util.ArrayList;

public class ChatBotAdapter extends RecyclerView.Adapter<ChatBotAdapter.ViewHolder> {

    ArrayList<CheckIsBot> messagelist;

    public ChatBotAdapter(ArrayList<CheckIsBot> messagelist) {
        this.messagelist = messagelist;
    }


    @NonNull
    @Override
    public ChatBotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(viewType,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatBotAdapter.ViewHolder holder, int position) {

        holder.msg.setText(messagelist.get(position).getTextmessage());
    }

    public int getItemViewType(int positon){

          if(messagelist.get(positon).isme){
              return R.layout.my_message;
          }

        return R.layout.bot_message;
    }

    @Override
    public int getItemCount() {
        return messagelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView msg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            msg=itemView.findViewById(R.id.my_msg);
        }
    }
}
