package com.example.covid_savar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private TextView TVcases,Tvrecovered,Tvdeaths,TVtodaydeaths;
    SimpleArcLoader simpleArcLoader;
    PieChart pieChart;
    ProgressBar dataProgressBar;
    BottomNavigationView bottomNavigationView;
    private long onbackpressedtime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TVcases=findViewById(R.id.TVcases);
        TVtodaydeaths=findViewById(R.id.TVTodayDeaths);
        Tvrecovered=findViewById(R.id.TVrecovered);
        Tvdeaths=findViewById(R.id.TVdeaths);
        simpleArcLoader=findViewById(R.id.loader);
        bottomNavigationView=findViewById(R.id.bottom_nav_view);
        dataProgressBar=findViewById(R.id.dataProgress);
        pieChart=findViewById(R.id.piechart);;
        fetchdata();
        bottomNavigationView.setSelectedItemId(R.id.global);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.global:
                        return  true;

                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),about.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.own:
                        startActivity(new Intent(getApplicationContext(),Countrywise.class));
                        overridePendingTransition(0,0);
                        return true;
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
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Title")
                .setMessage("Do you really want to exit?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.finishAffinity(MainActivity.this);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    private void fetchdata() {
        String url="https://corona.lmao.ninja/v2/all";
       /* simpleArcLoader.start();*/
        dataProgressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    Tvdeaths.setText(jsonObject.getString("deaths"));
                    Tvrecovered.setText(jsonObject.getString("recovered"));
                    TVcases.setText(jsonObject.getString("cases"));
                    TVtodaydeaths.setText(jsonObject.getString("active"));
                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(TVcases.getText().toString()), Color.parseColor("#FFA726")));
                    pieChart.addPieSlice(new PieModel("deaths",Integer.parseInt(Tvdeaths.getText().toString()), Color.parseColor("#EF5350")));
                    pieChart.addPieSlice(new PieModel("recovered",Integer.parseInt(Tvrecovered.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChart.addPieSlice(new PieModel("active",Integer.parseInt(TVtodaydeaths.getText().toString()), Color.parseColor("#29B6F6")));

                    pieChart.startAnimation();

                       dataProgressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
