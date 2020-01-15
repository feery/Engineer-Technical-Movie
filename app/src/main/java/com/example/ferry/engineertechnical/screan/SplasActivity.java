package com.example.ferry.engineertechnical.screan;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ferry.engineertechnical.R;
import com.example.ferry.engineertechnical.utils.SessionManager;

public class SplasActivity extends AppCompatActivity {

    Thread splashTread;

    public void onAttachedToWindow(){
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionManager session = new SessionManager(getApplicationContext());

        if(!session.isLoggedIn()){

            setContentView(R.layout.activity_splas);
            StartAnimations();

        }
        else {

            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            this.startActivity(i);
            this.finish();

        }


    }
    /**
     * Start Animations and go to another activity
     */
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        anim.reset();
        LinearLayout layout= findViewById(R.id.lin_lay);
        layout.clearAnimation();
        layout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView imageView = findViewById(R.id.splash);
        imageView.clearAnimation();
        imageView.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 2500) {
                        sleep(100);
                        waited += 100;
                    }

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    // do nothing

                } finally {
                    finish();
                }

            }
        };
        splashTread.start();
    }
}
