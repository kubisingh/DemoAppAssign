package com.example.uiviewsrc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public abstract class PickSources extends AppCompatActivity {
    public static final String ENVI="environment";
    public static final String SRC_TYPE="srctype";
    public static final int MOCK=1;
    public static final int API=2;
    public static final int responseCode=4400;
    int selectedvalue=1;
    String envi_value=null;
    RadioGroup pickgroup;
    Button go;

    public abstract void getValues(String envi, int type);

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_sources);
        if(getIntent()!=null && getIntent().hasExtra(ENVI)){
            envi_value=getIntent().getStringExtra(ENVI);
        }
        pickgroup = (RadioGroup)findViewById(R.id.pickgroup);
        go = (Button)findViewById(R.id.submit_pick);
        pickgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.mock){
                    selectedvalue=MOCK;
                }else if(checkedId==R.id.api){
                    selectedvalue=API;
                }
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getValues(envi_value,selectedvalue);
            }
        });
    }
}
