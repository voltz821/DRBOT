package com.example.drbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Dr_Home extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr__home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.dr_toolbar);
        setSupportActionBar(toolbar);

        nav = (NavigationView)findViewById(R.id.dr_navmenu);
        drawerLayout = (DrawerLayout)findViewById(R.id.dr_drawer);
        mAuth = FirebaseAuth.getInstance();


        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.dr_container,new AboutUs()).commit();
        nav.setCheckedItem(R.id.menu_about_us);

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment temp;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem1 ) {
                switch (menuItem1.getItemId())
                {

                    /*case R.id.menu_chatlist :
                        drawerLayout.closeDrawer((GravityCompat.START));
                        temp = new dr_chatlist();
                        break;*/

                    case R.id.menu_patients :
                        drawerLayout.closeDrawer((GravityCompat.START));
                       // temp = new dr_chat_users();
                        startActivity(new Intent(Dr_Home.this,dr_search_user.class));
                        break;

                    case R.id.menu_settings :
                        drawerLayout.closeDrawer(GravityCompat.START);
                        temp = new dr_settings();
                        break;


                    case R.id.menu_about_us :
                        drawerLayout.closeDrawer(GravityCompat.START);
                        temp = new AboutUs();
                        break;

                    case R.id.menu_logout :
                        Toast.makeText(getApplicationContext(),"Logging Out",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mAuth.signOut();
                        Intent intent = new Intent(Dr_Home.this,Dr_Login.class);
                        startActivity(intent);
                        finish();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.dr_container,temp).commit();


                return true;
            }
        });

    }
}