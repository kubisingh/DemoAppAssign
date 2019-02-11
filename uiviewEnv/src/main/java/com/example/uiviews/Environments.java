package com.example.uiviews;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public abstract class Environments extends AppCompatActivity {
    public static final String DEV="Dev";
    public static final String UAT="UAT";
    static final int RequestCode=5500;
    TextView dev,uat;
    EditText display;
    Button submit;

    public abstract void onSubmitData(String envr);

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environments);
        dev=(TextView)findViewById(R.id.dev);
        uat=(TextView)findViewById(R.id.uat);
        display=(EditText)findViewById(R.id.selected_value);
        submit=(Button)findViewById(R.id.submit_env);
        dev.setText(DEV);
        uat.setText(UAT);
        display.setText(DEV);
        dev.setOnClickListener(onlick);
        uat.setOnClickListener(onlick);
        display.setOnClickListener(onlick);
        submit.setOnClickListener(onlick);
    }

    View.OnClickListener onlick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id == R.id.dev){
                dev.setBackgroundColor(getResources().getColor(R.color.selected));
                uat.setBackgroundColor(getResources().getColor(R.color.unselected));
                display.setText(DEV);
            }else if(id == R.id.uat){
                dev.setBackgroundColor(getResources().getColor(R.color.unselected));
                uat.setBackgroundColor(getResources().getColor(R.color.selected));
                display.setText(UAT);
            }else if(id == R.id.submit_env){
                String value = display.getText().toString().trim();
                if(!value.isEmpty() && (value.equals(DEV) || value.equals(UAT))){
                    onSubmitData(value);
               }else{
                    Toast.makeText(Environments.this, "Invalid Environment", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

}
