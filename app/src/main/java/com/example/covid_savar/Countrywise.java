package com.example.covid_savar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.covid_savar.helper.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.leo.simplearcloader.SimpleArcLoader;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class Countrywise extends AppCompatActivity {
    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,dateupdate;
    SimpleArcLoader simpleArcLoader;
    CircularImageView flagimage;
    ProgressBar dataProgressBar;

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Countrywise.this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
  finish();

    }

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countrywise);
      tvCases=findViewById(R.id.totalCaseTV);
        tvRecovered=findViewById(R.id.totalRecoveredTV);
        tvCritical=findViewById(R.id.criticalCaseTV);
        tvActive=findViewById(R.id.totalActiveCase);
        tvTodayCases=findViewById(R.id.todayCaseTV);
        tvTotalDeaths=findViewById(R.id.totalDeathsTV);
        tvTodayDeaths=findViewById(R.id.todayDeathTV);
        simpleArcLoader=findViewById(R.id.loader);
        dateupdate=findViewById(R.id.updateTV);
        dataProgressBar=findViewById(R.id.dataProgress);
        flagimage=findViewById(R.id.flagimagecircle);






        fetchdata();
        bottomNavigationView=findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.own);
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
                      return  true;
                    case R.id.countries:
                        startActivity(new Intent(getApplicationContext(),AffectedCountries.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.symptomps:
                        startActivity(new Intent(getApplicationContext(),Symptoms.class));
                        overridePendingTransition(0,0);
                        return true;



                }



                return false;
            }
        });

    }

    private void fetchdata() {
        final String url="https://corona.lmao.ninja/v2/countries/Bangladesh";
       simpleArcLoader.start();
      //  dataProgressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    tvRecovered.setText(jsonObject.getString("recovered"));
                    tvCases.setText(jsonObject.getString("cases"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvTodayCases.setText(jsonObject.getString("todayCases"));
                    tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                    dateupdate.setText(Utils.getDateFormat(jsonObject.getString("updated")));

                    JSONObject object = jsonObject.getJSONObject("countryInfo");
                    String flagUrl = object.getString("flag");
                    Glide.with(Countrywise.this).load(flagUrl).into(flagimage);

                  simpleArcLoader.stop();
                   simpleArcLoader.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Countrywise.this,error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
