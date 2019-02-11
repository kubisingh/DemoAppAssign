package com.example.weatherapp.appBuild.HomeView;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.weatherapp.R;
import com.example.weatherapp.UIUtils.WeatherRecycleAdapter;
import com.example.weatherapp.Utils.Constants;
import com.example.weatherapp.WeatherPojoModels.WeatherModel;
import com.example.weatherapp.appBuild.MoreDetailView.MoreDetail;
import com.example.weatherapp.component.ListClickListner;

import java.util.ArrayList;
import java.util.List;

import static com.example.weatherapp.appBuild.MoreDetailView.MoreDetail.SERIALABLE_OBJ;

public abstract class WeatherMainView extends AppCompatActivity {
    public static final String ENVI="environment";
    public static final String SRC_TYPE="srctype";
    public static int FLAG=1;
    public String envi_value;
    public int networkType;
    WeatherViewModel weatherViewModel;
    RecyclerView recyclerView;
    WeatherRecycleAdapter adapter;
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wather_main_view);
       // Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        if(getIntent()!=null ){
           if(getIntent().hasExtra(ENVI))
               envi_value=getIntent().getStringExtra(ENVI);
           if(getIntent().hasExtra(SRC_TYPE))
               networkType=getIntent().getIntExtra(SRC_TYPE, Constants.NetworkType.MOCK);

            FLAG=networkType;
        }
        recyclerView = (RecyclerView)findViewById(R.id.rcycleweather);

        weatherViewModel= ViewModelProviders.of(WeatherMainView.this).get(WeatherViewModel.class);
        weatherViewModel.getWeatherData().observe(WeatherMainView.this, weatherobserver);
        adapter = new WeatherRecycleAdapter(new ArrayList<WeatherModel>(),listClickListner);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    weatherViewModel.refreshData();
            }
        });
    }


    Observer<List<WeatherModel>> weatherobserver =new Observer<List<WeatherModel>>() {
        @Override
        public void onChanged(@Nullable List<WeatherModel> weatherModels) {
            adapter.notifyData(weatherModels);
        }
    };

    ListClickListner listClickListner = new ListClickListner() {
        @Override
        public void onClick(WeatherModel weatherModel) {
            Intent in = new Intent(WeatherMainView.this, MoreDetail.class);
            in.putExtra(SERIALABLE_OBJ,weatherModel.getCurrently());
            startActivity(in);
        }
    };
}
