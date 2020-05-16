package com.example.covid_savar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Symptoms extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        bottomNavigationView=findViewById(R.id.bottom_nav_view);

        bottomNavigationView.setSelectedItemId(R.id.symptomps);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.global:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);

                        return  true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),about.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.own:
                        startActivity(new Intent(getApplicationContext(),Countrywise.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.countries:
                        startActivity(new Intent(getApplicationContext(),AffectedCountries.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.symptomps:

                        return true;



                }



                return false;
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Symptoms.this,AffectedCountries.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();

    }
}
