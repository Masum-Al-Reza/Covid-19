package com.example.covid_savar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.covid_savar.helper.Utils;


public class DetailActivity extends AppCompatActivity {
private  int positioncountry;
    TextView tvCountry,tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,dateupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        positioncountry=intent.getIntExtra("position",0);
        tvCountry = findViewById(R.id.tvCountry);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        dateupdate=findViewById(R.id.updateTV);


        tvCountry.setText(AffectedCountries.countryModelsList.get(positioncountry).getCountry());
        tvCases.setText(AffectedCountries.countryModelsList.get(positioncountry).getCases());
        tvRecovered.setText(AffectedCountries.countryModelsList.get(positioncountry).getRecovered());
        tvCritical.setText(AffectedCountries.countryModelsList.get(positioncountry).getCritical());
        tvActive.setText(AffectedCountries.countryModelsList.get(positioncountry).getActive());
        tvTodayCases.setText(AffectedCountries.countryModelsList.get(positioncountry).getTodaycases());
        tvTotalDeaths.setText(AffectedCountries.countryModelsList.get(positioncountry).getDeaths());
        tvTodayDeaths.setText(AffectedCountries.countryModelsList.get(positioncountry).getTodaydeaths());
        dateupdate.setText(Utils.getDateFormat(AffectedCountries.countryModelsList.get(positioncountry).getUpdated()));



    }
}
