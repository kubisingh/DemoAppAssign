package com.example.weatherapp.Utils;

import android.content.Context;

import com.example.weatherapp.WeatherPojoModels.WeatherModel;
import com.google.gson.Gson;

public class ModelHelper {

    public static WeatherModel getWeatherModelFromFile(Context cn, String filename){
        WeatherModel eventResponseList;
        Gson gson = new Gson();
        eventResponseList  = gson.fromJson(FileUtils.getStringFileData(cn,filename), WeatherModel.class);
        return eventResponseList;
    }

}
