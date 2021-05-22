package com.example.drbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;
//    FragmentManager fragmentManager;
//    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Fragment fragment =  AboutUs.newInstance();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.container,fragment,"AboutUs");
//        transaction.commit();



        nav = (NavigationView)findViewById(R.id.navmenu);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mAuth = FirebaseAuth.getInstance();

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new ChatBot()).commit();
        nav.setCheckedItem(R.id.menu_chatbot);

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            Fragment temp;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {

                switch (menuItem.getItemId())
                {
                    case R.id.menu_chatbot :
                       // Toast.makeText(getApplicationContext(),"Chatbot is Here",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        temp = new ChatBot();
                        break;

                    /*case R.id.menu_chatlist :
                        drawerLayout.closeDrawer((GravityCompat.START));
                        temp = new Chatlist();
                        break;
*/
                    case R.id.menu_doctors :
                        drawerLayout.closeDrawer((GravityCompat.START));
                     //   temp = new users_chat_dr();
                        startActivity(new Intent(home.this,user_search_dr.class));
                        break;

                    case R.id.menu_settings :
                        drawerLayout.closeDrawer(GravityCompat.START);
                        temp = new Settings();
                        break;


                    case R.id.menu_about_us :
                        drawerLayout.closeDrawer(GravityCompat.START);
                        temp = new AboutUs();
                        break;

                    case R.id.menu_logout :
                        Toast.makeText(getApplicationContext(),"Logging Out",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mAuth.signOut();
                        Intent intent = new Intent(home.this,Login.class);
                        startActivity(intent);
                        finish();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container,temp).commit();


                return true;
            }
        });

    }



}