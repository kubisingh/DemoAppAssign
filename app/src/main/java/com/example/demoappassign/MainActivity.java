package com.example.demoappassign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.uiviews.Environments;
import com.example.uiviewsrc.PickSources;

public class MainActivity extends Environments {


    @Override
    public void onSubmitData(String envr) {
        Intent in = new Intent(this, SourceActivity.class);
        in.putExtra(SourceActivity.ENVI,envr);
        startActivity(in);
        finish();
    }
}
