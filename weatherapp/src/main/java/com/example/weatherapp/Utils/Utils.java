package com.example.weatherapp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

import java.net.InetAddress;

public class Utils {

    public static boolean isInternetConnected(){
            try {
                InetAddress ipAddr = InetAddress.getByName("google.com");
                //You can replace it with your name
                return !ipAddr.equals("");

            } catch (Exception e) {
                return false;
            }
    }

    public static boolean isNetworkConnected(Context cn) {
        ConnectivityManager cm = (ConnectivityManager)cn.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
