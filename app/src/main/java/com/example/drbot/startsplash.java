package com.example.drbot;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class startsplash extends AppCompatActivity {

    ImageView ivTop,ivHeart,ivBeat,ivBottom;
    TextView textView;
    CharSequence charSequence;
    int index;
    long delay = 200;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startsplash);


        ivTop = findViewById(R.id.iv_top);
        ivHeart = findViewById(R.id.iv_heart);
        ivBeat = findViewById(R.id.iv_beat);
        ivBottom = findViewById(R.id.iv_bottom);
        textView = findViewById(R.id.spshtxt);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.top_wave);

        ivTop.setAnimation(animation1);

        //Initialize object animator
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                ivHeart, PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        );

        objectAnimator.setDuration(500);

        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);

        objectAnimator.setRepeatCount(ValueAnimator.REVERSE);

        objectAnimator.start();

        //Set Animate text
        animatText("Dr.BOT");

        //Load GIF
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/demoapp-ae96a.appspot.com/o/heart_beat.gif?alt=media&token=b21dddd8-782c-457c-babd-f2e922ba172b")
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivBeat);

        //Initialize Bottom animation
        Animation  animation2 = AnimationUtils.loadAnimation(this,R.anim.bottom_wave);
        //Start Bottom Activity
        ivBottom.setAnimation(animation2);

        //Initialize Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            //Redirect to Main_Activity
                startActivity(new Intent(startsplash.this
                        , MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                //finish activity
                finish();
            }
        },4000);


    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //When runnable is run
            //Set text
            textView.setText(charSequence.subSequence(0,index++));
            //Check Condition
            if (index <= charSequence.length()) {
                 //When index is equal to text length
                // Run handler
                handler.postDelayed(runnable,delay);
            }
        }

    };
    //Create animated text method
    public void animatText(CharSequence cs){
        //Set Text
        charSequence = cs;
        //Clear Index
        index = 0;
        //Clear Text
        textView.setText("");
        //Remove call back
        handler.removeCallbacks(runnable);
        //Run Handler
        handler.postDelayed(runnable,delay);

    }
}
