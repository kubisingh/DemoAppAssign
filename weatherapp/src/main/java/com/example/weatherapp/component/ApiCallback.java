package com.example.weatherapp.component;

import com.example.weatherapp.WeatherPojoModels.WeatherModel;

public interface ApiCallback {
    public void onCallBack(boolean success, WeatherModel weatherModel);
}
