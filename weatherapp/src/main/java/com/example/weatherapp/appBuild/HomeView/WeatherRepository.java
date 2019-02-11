package com.example.weatherapp.appBuild.HomeView;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.weatherapp.Network.OAuthManager;
import com.example.weatherapp.Utils.Constants;
import com.example.weatherapp.Utils.ModelHelper;
import com.example.weatherapp.WeatherPojoModels.WeatherModel;
import com.example.weatherapp.component.ApiCallback;
import com.example.weatherapp.component.WDataCallBack;

import java.util.ArrayList;
import java.util.List;


public class WeatherRepository {

    private MutableLiveData<List<WeatherModel>> weather=new MutableLiveData<>();
    private Context cn;
    private WeatherRepository(){}

    public WeatherRepository(Context cn){
        this.cn=cn;
    }

    public MutableLiveData<List<WeatherModel>> getWeatherData(){
        return weather;
    }

    public void  generateData (final int networkType){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(networkType== Constants.NetworkType.MOCK){
                    List<WeatherModel> list = new ArrayList<>();
                    list.add(ModelHelper.getWeatherModelFromFile(cn,Constants.MOCK_FILE_NY));
                    list.add(ModelHelper.getWeatherModelFromFile(cn,Constants.MOCK_FILE_LA));
                    weather.postValue(list);
                }else{
                    getApiData();
                }
            }
        }).start();

    }

    void getApiData(){
        final List<WeatherModel> list = new ArrayList<>();
        OAuthManager.getInstance().getWeatherApiData(Constants.CITY_NY, new ApiCallback() {
            @Override
            public void onCallBack(boolean success, WeatherModel weatherModel) {
                if(success && weatherModel!=null){
                    list.add(weatherModel);
                }
                OAuthManager.getInstance().getWeatherApiData(Constants.CITY_LA, new ApiCallback() {
                    @Override
                    public void onCallBack(boolean success, WeatherModel weatherModel) {
                        if(success && weatherModel!=null){
                            list.add(weatherModel);
                        }
                        weather.postValue(list);
                    }
                });
            }
        });
    }


}
