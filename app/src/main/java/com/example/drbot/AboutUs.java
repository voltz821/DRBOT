package com.example.drbot;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

import static java.lang.String.*;



public class AboutUs extends Fragment {


    public AboutUs() {
        // Required empty public constructor
    }

    public static AboutUs newInstance(){
        AboutUs fragment = new AboutUs();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     //   final View view =  inflater.inflate(R.layout.fragment_about_us, container, false);

        Element adsElement = new Element();
        adsElement.setTitle("Dr.Bot a Chatbot");

        return new AboutPage(getContext())
                .isRTL(false)
                .setImage(R.drawable.menulogo)
                .setDescription("Hello Guys,This is my first Android Application Dr BOt which will help you to check your health condition anytime anywhere.It is the Beta version of the app so stay tuned for further intresting technical updates")
                .addItem(adsElement)
                .addItem(new Element().setTitle("Version 1.0"))
                .addGroup("Connect with Us")
                .addEmail("mihirmwairkar@gmail.com")
                .addFacebook("https://www.facebook.com/mihir.wairkar.5")
                .addTwitter("MihirWairkar")
                .addYoutube("https://youtube.com/channel/UCFec-h5_BRV6Z990TSyQfuw")
                .addInstagram("__miii_h_iiir__")
                .addGitHub("voltz821")
                .addItem(createCopyright())
                .create();

    }
    private Element createCopyright() {
        final Element copyright = new Element();
        String copyrightString = String.format("Copyright %d by Mihir Wairkar",Calendar.getInstance().get(Calendar.YEAR));
        copyright.setTitle(copyrightString);
        copyright.setIconDrawable(R.mipmap.ic_launcher);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),copyrightString,Toast.LENGTH_SHORT).show();
            }
        });
        return copyright;
    }
}
