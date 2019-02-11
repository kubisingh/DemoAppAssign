package com.example.demoappassign;

import android.content.Intent;

import com.example.uiviewsrc.PickSources;
import com.example.weatherapp.appBuild.HomeView.WeatherMainView;

public class SourceActivity extends PickSources {
    @Override
    public void getValues(String envi, int type) {
        Intent in = new Intent(this, WeatherView.class);
        in.putExtra(WeatherMainView.ENVI,envi);
        in.putExtra(WeatherMainView.SRC_TYPE,type);
        startActivity(in);
        finish();
    }
}
