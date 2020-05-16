package com.example.covid_savar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.sql.CommonDataSource;

public class Splash_screen extends AppCompatActivity {
Animation topanim,bottomanim;
ImageView coronimage;
TextView logo,mine;
ProgressBar progressBar;
private  static  int SPLASH_TIME=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
  topanim=AnimationUtils.loadAnimation(this,R.anim.top);
   bottomanim=AnimationUtils.loadAnimation(this,R.anim.bottom);
        coronimage=findViewById(R.id.coronaicon);
        logo=findViewById(R.id.logotTV);
        mine=findViewById(R.id.mineTv);
progressBar=findViewById(R.id.dataProgress);

        coronimage.setAnimation(topanim);
logo.setAnimation(bottomanim);
mine.setAnimation(bottomanim);
        progressBar.setVisibility(View.VISIBLE);
new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {


            NetworkInfo activeNetwork = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))
                    .getActiveNetworkInfo();

            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {

                // Load Webview
                startActivity(new Intent(Splash_screen.this, MainActivity.class));
            } else {
                Toast.makeText(Splash_screen.this, "Check internet Connection", Toast.LENGTH_SHORT).show();

            }

        }

},SPLASH_TIME);



    }
}
