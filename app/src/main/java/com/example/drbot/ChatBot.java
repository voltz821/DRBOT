package com.example.drbot;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.drbot.Utills.ChatBotAdapter;
import com.example.drbot.Utills.CheckIsBot;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatBot#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatBot extends Fragment {

    EditText input;
    ImageView sendbtn;
    RecyclerView recyclerView;
    ArrayList<CheckIsBot> inputmsg;
    ChatBotAdapter chatBotAdapter;
    HashMap<String,String> question=new HashMap<String, String>();


    public ChatBot() {
        // Required empty public constructor
    }


    public static ChatBot newInstance() {
        ChatBot fragment = new ChatBot();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_chat_bot, container, false);


        //Hi Variations
        question.put("hi","Hi,How Can I Help You?");
        question.put("hello","Hi,How Can I Help You?");
        question.put("Hello","Hi,How Can I Help You?");
        question.put("Hi","Hi,How Can I Help You?");

        //What is your name Variations
        question.put("What is Your Name","I am Dr.BOT(^_^).How can i help you?");
        question.put("What is Your Name ?","I am Dr.BOT(^_^).How can i help you?");
        question.put("what is your name","I am Dr.BOT(^_^).How can i help you?");
        question.put("what is your name ?","I am Dr.BOT(^_^).How can i help you?");
        question.put("What is your name","I am Dr.BOT(^_^).How can i help you?");
        question.put("What is your name ?","I am Dr.BOT(^_^).How can i help you?");
        question.put("What is Your name ?","I am Dr.BOT(^_^).How can i help you?");

        //Covid Related questions;
        question.put("I'm not feeling Well","What exactly is happening to you ?");
        question.put("I'm not feeling well","What exactly is happening to you ?");
        question.put("Im not feeling Well","What exactly is happening to you ?");
        question.put("I'm not well","What exactly is happening to you ?");
        question.put("Im not well","What exactly is happening to you ?");
        question.put("Help me","What exactly is happening to you ?");
        question.put("help me","What exactly is happening to you ?");
        //
        question.put("Im having fever","High Fever or Low Fever?");
        question.put("I'm having fever","High Fever or Low Fever?");
        question.put("I have fever","High Fever or Low Fever?");
        question.put("fever","High Fever or Low Fever?");
        question.put("Fever","High Fever or Low Fever?");
        question.put("FEVER","High Fever or Low Fever?");

        question.put("Low Fever","Ok.Do You have any CHILLS & SHIVERING,SWEATING,HEADACHE ?");
        question.put("low Fever","Ok.Do You have any CHILLS & SHIVERING SWEATING,HEADACHE ?");
        question.put("low fever","Ok.Do You have any CHILLS & SHIVERING SWEATING,HEADACHE ?");
        question.put("Low fever","Ok.Do You have any CHILLS & SHIVERING SWEATING,HEADACHE ?");

        question.put("all 3","I have analysed your problem you have common cold.I would like to recommend you a Casual Doctor for having confirmation & Treatement");
        question.put("ALL 3","I have analysed your problem you have common cold.I would like to recommend you a Casual Doctor for having confirmation & Treatement");
        question.put("all three","I have analysed your problem you have common cold.I would like to recommend you a Casual Doctor for having confirmation & Treatement");
        question.put("All three","I have analysed your problem you have common cold.I would like to recommend you a Casual Doctor for having confirmation & Treatement");
        question.put("ALL three","I have analysed your problem you have common cold.I would like to recommend you a Casual Doctor for having confirmation & Treatement");
        question.put("ALL Three","I have analysed your problem you have common cold.I would like to recommend you a Casual Doctor for having confirmation & Treatement");
        question.put("ALL THREE","I have analysed your problem you have common cold.I would like to recommend you a Casual Doctor for having confirmation & Treatement");

        question.put("None of those","Due to climatic changes it happes to a human body.You should stay home and take rest.You will feel good after some time");
        question.put("None Of Those","Due to climatic changes it happes to a human body.You should stay home and take rest.You will feel good after some time");
        question.put("Not Any","Due to climatic changes it happes to a human body.You should stay home and take rest.You will feel good after some time");
        question.put("not any","Due to climatic changes it happes to a human body.You should stay home and take rest.You will feel good after some time");
        question.put("Not any","Due to climatic changes it happes to a human body.You should stay home and take rest.You will feel good after some time");

        question.put("High Fever","Do you have any of these symptoms:-\n difficulty breathing or shortness of breath\nchest pain or pressure\n loss of speech or movement");
        question.put("high fever","Do you have any of these symptoms:-\n difficulty breathing or shortness of breath\nchest pain or pressure\n loss of speech or movement");
        question.put("High fever","Do you have any of these symptoms:-\n difficulty breathing or shortness of breath\nchest pain or pressure\n loss of speech or movement");
        question.put("high Fever","Do you have any of these symptoms:-\n difficulty breathing or shortness of breath\nchest pain or pressure\n loss of speech or movement");

      //  question.put("","You are a severe case of COVID 19 and should maintain social distancing with proper sanitization.\n Try to get quarantine to the nearest COVID centre and contact Dr for it");
      //  question.put("","You are a severe case of COVID 19 and should maintain social distancing with proper sanitization.\n Try to get quarantine to the nearest COVID centre and contact Dr for it");
        question.put("All of those","You are a severe case of COVID 19 and should maintain social distancing with proper sanitization.\n Try to get quarantine to the nearest COVID centre and contact Dr for it");
        question.put("All of Those","You are a severe case of COVID 19 and should maintain social distancing with proper sanitization.\n Try to get quarantine to the nearest COVID centre and contact Dr for it");
        question.put("all of those","You are a severe case of COVID 19 and should maintain social distancing with proper sanitization.\n Try to get quarantine to the nearest COVID centre and contact Dr for it");
        question.put("everything","You are a severe case of COVID 19 and should maintain social distancing with proper sanitization.\n Try to get quarantine to the nearest COVID centre and contact Dr for it");
        question.put("Everything","You are a severe case of COVID 19 and should maintain social distancing with proper sanitization.\n Try to get quarantine to the nearest COVID centre and contact Dr for it");

        question.put("Not Any","Do you have any of these:- \naches and pains\n sore throat\n diarrhoea\n conjunctivitis\n headache\n loss of taste or smell\n a rash on skin, or discolouration of fingers or toes ");
        question.put("not any","Do you have any of these:- \naches and pains\n sore throat\n diarrhoea\n conjunctivitis\n headache\n loss of taste or smell\n a rash on skin, or discolouration of fingers or toes ");
        question.put("Not any","Do you have any of these:- \naches and pains\n sore throat\n diarrhoea\n conjunctivitis\n headache\n loss of taste or smell\n a rash on skin, or discolouration of fingers or toes ");
        question.put("not Any","Do you have any of these:- \naches and pains\n sore throat\n diarrhoea\n conjunctivitis\n headache\n loss of taste or smell\n a rash on skin, or discolouration of fingers or toes ");

        question.put("diarrhoea,headache","You have slight symptoms of COVID 19 go to the nearest hospital or COVID center for checkup");
        question.put("Diarrhoea,Headache","You have slight symptoms of COVID 19 go to the nearest hospital or COVID center for checkup");
        question.put("diarrhoea,headache,loss of taste or smell","You have slight symptoms of COVID 19 go to the nearest hospital or COVID center for checkup");
        question.put("Diarrhoea,Headache,loss of taste or smell","You have slight symptoms of COVID 19 go to the nearest hospital or COVID center for checkup");
        question.put("loss of taste or smell","You have slight symptoms of COVID 19 go to the nearest hospital or COVID center for checkup");
        question.put("Loss of taste or smell","You have slight symptoms of COVID 19 go to the nearest hospital or COVID center for checkup");
        question.put("Loss of Taste or smell","You have slight symptoms of COVID 19 go to the nearest hospital or COVID center for checkup");
        question.put("Loss of Taste or Smell","You have slight symptoms of COVID 19 go to the nearest hospital or COVID center for checkup");
        question.put("Loss of Taste Or smell","You have slight symptoms of COVID 19 go to the nearest hospital or COVID center for checkup");

        question.put("None","I have analysed your problem you have common cold.I would like to recommend you a Casual Doctor for having confirmation & Treatement");
        question.put("none","I have analysed your problem you have common cold.I would like to recommend you a Casual Doctor for having confirmation & Treatement");
        question.put("NONE","I have analysed your problem you have common cold.I would like to recommend you a Casual Doctor for having confirmation & Treatement");
        question.put("None","I have analysed your problem you have common cold.I would like to recommend you a Casual Doctor for having confirmation & Treatement");


        question.put("ThankYou","Your Welcome,Letme know if you need something");
        question.put("thankYou","Your Welcome,Letme know if you need something");
        question.put("thankyou","Your Welcome,Letme know if you need something");
        question.put("Thankyou","Your Welcome,Letme know if you need something");
        question.put("THANKYOU","Your Welcome,Letme know if you need something");
        question.put("THANK YOU","Your Welcome,Letme know if you need something");
        question.put("thank you","Your Welcome,Letme know if you need something");


        question.put("COVID-19","Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.Most people infected with the COVID-19 virus will experience mild to moderate respiratory illness and recover without requiring special treatment.  Older people, and those with underlying medical problems like cardiovascular disease, diabetes, chronic respiratory disease, and cancer are more likely to develop serious illness.");
        question.put("Corona Virus","Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.Most people infected with the COVID-19 virus will experience mild to moderate respiratory illness and recover without requiring special treatment.  Older people, and those with underlying medical problems like cardiovascular disease, diabetes, chronic respiratory disease, and cancer are more likely to develop serious illness.");
        question.put("Corona virus","Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.Most people infected with the COVID-19 virus will experience mild to moderate respiratory illness and recover without requiring special treatment.  Older people, and those with underlying medical problems like cardiovascular disease, diabetes, chronic respiratory disease, and cancer are more likely to develop serious illness.");
        question.put("corona virus","Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.Most people infected with the COVID-19 virus will experience mild to moderate respiratory illness and recover without requiring special treatment.  Older people, and those with underlying medical problems like cardiovascular disease, diabetes, chronic respiratory disease, and cancer are more likely to develop serious illness.");
        question.put("covid-19","Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.Most people infected with the COVID-19 virus will experience mild to moderate respiratory illness and recover without requiring special treatment.  Older people, and those with underlying medical problems like cardiovascular disease, diabetes, chronic respiratory disease, and cancer are more likely to develop serious illness.");
        question.put("covid","Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.Most people infected with the COVID-19 virus will experience mild to moderate respiratory illness and recover without requiring special treatment.  Older people, and those with underlying medical problems like cardiovascular disease, diabetes, chronic respiratory disease, and cancer are more likely to develop serious illness.");
        question.put("COVID","Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.Most people infected with the COVID-19 virus will experience mild to moderate respiratory illness and recover without requiring special treatment.  Older people, and those with underlying medical problems like cardiovascular disease, diabetes, chronic respiratory disease, and cancer are more likely to develop serious illness.");
        question.put("Covid-19","Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.Most people infected with the COVID-19 virus will experience mild to moderate respiratory illness and recover without requiring special treatment.  Older people, and those with underlying medical problems like cardiovascular disease, diabetes, chronic respiratory disease, and cancer are more likely to develop serious illness.");
        question.put("COVID19","Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.Most people infected with the COVID-19 virus will experience mild to moderate respiratory illness and recover without requiring special treatment.  Older people, and those with underlying medical problems like cardiovascular disease, diabetes, chronic respiratory disease, and cancer are more likely to develop serious illness.");
        question.put("Covid19","Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.Most people infected with the COVID-19 virus will experience mild to moderate respiratory illness and recover without requiring special treatment.  Older people, and those with underlying medical problems like cardiovascular disease, diabetes, chronic respiratory disease, and cancer are more likely to develop serious illness.");
        question.put("covid19","Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.Most people infected with the COVID-19 virus will experience mild to moderate respiratory illness and recover without requiring special treatment.  Older people, and those with underlying medical problems like cardiovascular disease, diabetes, chronic respiratory disease, and cancer are more likely to develop serious illness.");

        question.put("How to avoid Corona Virus","The best way to prevent and slow down transmission is to be well informed about the COVID-19 virus, the disease it causes and how it spreads. Protect yourself and others from infection by washing your hands or using an alcohol based rub frequently and not touching your face.The COVID-19 virus spreads primarily through droplets of saliva or discharge from the nose when an infected person coughs or sneezes, so it’s important that you also practice respiratory etiquette (for example, by coughing into a flexed elbow).");
        question.put("How to avoid CORONA VIRUS","The best way to prevent and slow down transmission is to be well informed about the COVID-19 virus, the disease it causes and how it spreads. Protect yourself and others from infection by washing your hands or using an alcohol based rub frequently and not touching your face.The COVID-19 virus spreads primarily through droplets of saliva or discharge from the nose when an infected person coughs or sneezes, so it’s important that you also practice respiratory etiquette (for example, by coughing into a flexed elbow).");
        question.put("How to avoid corona virus","The best way to prevent and slow down transmission is to be well informed about the COVID-19 virus, the disease it causes and how it spreads. Protect yourself and others from infection by washing your hands or using an alcohol based rub frequently and not touching your face.The COVID-19 virus spreads primarily through droplets of saliva or discharge from the nose when an infected person coughs or sneezes, so it’s important that you also practice respiratory etiquette (for example, by coughing into a flexed elbow).");
        question.put("COVID19 prevention","The best way to prevent and slow down transmission is to be well informed about the COVID-19 virus, the disease it causes and how it spreads. Protect yourself and others from infection by washing your hands or using an alcohol based rub frequently and not touching your face.The COVID-19 virus spreads primarily through droplets of saliva or discharge from the nose when an infected person coughs or sneezes, so it’s important that you also practice respiratory etiquette (for example, by coughing into a flexed elbow).");
        question.put("corona prevention","The best way to prevent and slow down transmission is to be well informed about the COVID-19 virus, the disease it causes and how it spreads. Protect yourself and others from infection by washing your hands or using an alcohol based rub frequently and not touching your face.The COVID-19 virus spreads primarily through droplets of saliva or discharge from the nose when an infected person coughs or sneezes, so it’s important that you also practice respiratory etiquette (for example, by coughing into a flexed elbow).");
        question.put("Covid19 prevention","The best way to prevent and slow down transmission is to be well informed about the COVID-19 virus, the disease it causes and how it spreads. Protect yourself and others from infection by washing your hands or using an alcohol based rub frequently and not touching your face.The COVID-19 virus spreads primarily through droplets of saliva or discharge from the nose when an infected person coughs or sneezes, so it’s important that you also practice respiratory etiquette (for example, by coughing into a flexed elbow).");
        question.put("Covid prevention","The best way to prevent and slow down transmission is to be well informed about the COVID-19 virus, the disease it causes and how it spreads. Protect yourself and others from infection by washing your hands or using an alcohol based rub frequently and not touching your face.The COVID-19 virus spreads primarily through droplets of saliva or discharge from the nose when an infected person coughs or sneezes, so it’s important that you also practice respiratory etiquette (for example, by coughing into a flexed elbow).");
        question.put("COVID prevention","The best way to prevent and slow down transmission is to be well informed about the COVID-19 virus, the disease it causes and how it spreads. Protect yourself and others from infection by washing your hands or using an alcohol based rub frequently and not touching your face.The COVID-19 virus spreads primarily through droplets of saliva or discharge from the nose when an infected person coughs or sneezes, so it’s important that you also practice respiratory etiquette (for example, by coughing into a flexed elbow).");
        question.put("How to prevent corona","The best way to prevent and slow down transmission is to be well informed about the COVID-19 virus, the disease it causes and how it spreads. Protect yourself and others from infection by washing your hands or using an alcohol based rub frequently and not touching your face.The COVID-19 virus spreads primarily through droplets of saliva or discharge from the nose when an infected person coughs or sneezes, so it’s important that you also practice respiratory etiquette (for example, by coughing into a flexed elbow).");




//
        input=view.findViewById(R.id.botinputsms);
        sendbtn=view.findViewById(R.id.botbtnSend);
        recyclerView=view.findViewById(R.id.botrecyclerview);
        inputmsg=new ArrayList<>();

        chatBotAdapter=new ChatBotAdapter(inputmsg);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false) );

        recyclerView.setAdapter(chatBotAdapter);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!input.getText().toString().isEmpty()) {
                    CheckIsBot checkIsBot = new CheckIsBot(input.getText().toString(), true);
                    inputmsg.add(checkIsBot);

                    if (question.containsKey(input.getText().toString())) {
                        CheckIsBot checkIsBot1 = new CheckIsBot(question.get(input.getText().toString()), false);
                        inputmsg.add(checkIsBot1);
                    } else {
                        CheckIsBot checkIsBot1 = new CheckIsBot("Sorry I didn't understood... contact Dr in menu section", false);
                        inputmsg.add(checkIsBot1);
                    }
                    input.setText("");
                    chatBotAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getActivity(), "Please Enter Something", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return  view;
    }
}