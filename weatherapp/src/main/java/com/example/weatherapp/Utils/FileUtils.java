package com.example.weatherapp.Utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    public static String getStringFileData(Context cn,String filename){
            try {
                InputStream inputStream=cn.getAssets().open(filename);
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes, 0, bytes.length);
                String json = new String(bytes);
                return json;
            } catch (IOException e) {
                return null;
            }
    }
}
