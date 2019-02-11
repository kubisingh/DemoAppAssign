package com.example.weatherapp.appBuild.HomeView;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.weatherapp.Utils.Constants;
import com.example.weatherapp.Utils.Utils;
import com.example.weatherapp.WeatherPojoModels.WeatherModel;

import java.util.List;

public class WeatherViewModel extends AndroidViewModel {

    private MutableLiveData<List<WeatherModel>> weather;
    WeatherRepository weatherRepository;
    Context cn;
    public WeatherViewModel(@NonNull Application application) {
        super(application);
        this.cn=application;
        weatherRepository=new WeatherRepository(application);
        weather=weatherRepository.getWeatherData();
        initData();
    }

    public void initData(){
        if(WeatherMainView.FLAG==Constants.NetworkType.API && !Utils.isNetworkConnected(cn)){
            weatherRepository.generateData(Constants.NetworkType.MOCK);
        }else {
            weatherRepository.generateData(WeatherMainView.FLAG);
        }

    }

    public MutableLiveData<List<WeatherModel>> getWeatherData(){
       return weather;
    }


    public void refreshData(){
            initData();
    }
}
