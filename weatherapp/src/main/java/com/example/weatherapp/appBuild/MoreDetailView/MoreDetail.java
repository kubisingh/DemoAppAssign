package com.example.weatherapp.appBuild.MoreDetailView;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.WeatherPojoModels.Currently;

public class MoreDetail extends AppCompatActivity {
    public static final String SERIALABLE_OBJ="serial_obj";
    @SuppressLint("StringFormatMatches")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_detail);

        if(getIntent()!=null && getIntent().hasExtra(SERIALABLE_OBJ)){
             Currently data=  (Currently) getIntent().getSerializableExtra(SERIALABLE_OBJ);
            ((TextView)findViewById(R.id.txt1)).setText(String.format(getString(R.string.nearestdis, new Object[]{String.valueOf(data.getNearestStormDistance())})));
            ((TextView)findViewById(R.id.txt2)).setText(String.format(getString(R.string.pint, new Object[]{String.valueOf(data.getPrecipIntensity())})));
            ((TextView)findViewById(R.id.txt3)).setText(String.format(getString(R.string.pprob, new Object[]{String.valueOf(data.getPrecipProbability())})));
            ((TextView)findViewById(R.id.txt4)).setText(String.format(getString(R.string.temp, new Object[]{String.valueOf(data.getTemperature())})));
            ((TextView)findViewById(R.id.txt5)).setText(String.format(getString(R.string.atemp, new Object[]{String.valueOf(data.getApparentTemperature())})));
            ((TextView)findViewById(R.id.txt6)).setText(String.format(getString(R.string.dpoint, new Object[]{String.valueOf(data.getDewPoint())})));
            ((TextView)findViewById(R.id.txt7)).setText(String.format(getString(R.string.humi, new Object[]{String.valueOf(data.getHumidity())})));

            ((TextView)findViewById(R.id.txt8)).setText(String.format(getString(R.string.prs, new Object[]{String.valueOf(data.getPressure())})));
            ((TextView)findViewById(R.id.txt9)).setText(String.format(getString(R.string.wspeed, new Object[]{String.valueOf(data.getWindSpeed())})));
            ((TextView)findViewById(R.id.txt10)).setText(String.format(getString(R.string.wgust, new Object[]{String.valueOf(data.getWindGust())})));
            ((TextView)findViewById(R.id.txt11)).setText(String.format(getString(R.string.wbea, new Object[]{String.valueOf(data.getWindGust())})));
            ((TextView)findViewById(R.id.txt12)).setText(String.format(getString(R.string.ccover, new Object[]{String.valueOf(data.getCloudCover())})));
            ((TextView)findViewById(R.id.txt13)).setText(String.format(getString(R.string.uindex, new Object[]{String.valueOf(data.getUvIndex())})));
            ((TextView)findViewById(R.id.txt14)).setText(String.format(getString(R.string.vis, new Object[]{String.valueOf(data.getVisibility())})));
            ((TextView)findViewById(R.id.txt15)).setText(String.format(getString(R.string.ozone, new Object[]{String.valueOf(data.getOzone())})));
        }

    }
}
