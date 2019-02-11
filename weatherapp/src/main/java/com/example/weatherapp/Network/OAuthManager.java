package com.example.weatherapp.Network;

import com.example.weatherapp.Utils.Constants;
import com.example.weatherapp.WeatherPojoModels.WeatherModel;
import com.example.weatherapp.component.ApiCallback;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class OAuthManager {

    private static Retrofit retrofit = null;
    private static OAuthManager instance;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    CallInterface weatherApiInterface;
    Call call;
    private OAuthManager(){}

    public static OAuthManager getInstance(){
        if(instance==null){
            instance=new OAuthManager();
            instance.getClient();
        }
        return instance;
    }

    private void getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            weatherApiInterface = retrofit.create(CallInterface.class);
        }
    }
    public interface CallInterface {
        @GET("37.3855,-122.088")
        Call<WeatherModel> getLAWeather();

        @GET("40.7128,-74.0060")
        Call<WeatherModel> getNYWeather();
    }

    public void getWeatherApiData(String city,final ApiCallback event){

        if(city.equals(Constants.CITY_LA)){
            call = weatherApiInterface.getLAWeather();
        }else{
            call = weatherApiInterface.getNYWeather();
        }
         call.clone().enqueue(new Callback() {
             @Override
             public void onResponse(Call call, Response response) {
                 if(response.isSuccessful()){
                     WeatherModel weatherModel = (WeatherModel) response.body();
                     event.onCallBack(true,weatherModel);
                 }else{
                     event.onCallBack(false,null);
                 }
             }

             @Override
             public void onFailure(Call call, Throwable t) {
                event.onCallBack(false,null);
             }
         });

    }

}
