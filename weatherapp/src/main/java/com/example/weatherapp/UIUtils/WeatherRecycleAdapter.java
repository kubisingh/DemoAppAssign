package com.example.weatherapp.UIUtils;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.weatherapp.R;
import com.example.weatherapp.Utils.DateTimeUtil;
import com.example.weatherapp.WeatherPojoModels.WeatherModel;
import com.example.weatherapp.component.ListClickListner;

import java.util.List;

public class WeatherRecycleAdapter extends RecyclerView.Adapter<WeatherRecycleAdapter.MyViewHolder> {

private List<WeatherModel> weather=null;
private ListClickListner event;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, time,temp,summ;
        CardView card_view;

        public MyViewHolder(View view) {
            super(view);
            card_view=(CardView)view.findViewById(R.id.card_view);
            title = (TextView) view.findViewById(R.id.cityname);
            time = (TextView) view.findViewById(R.id.txt_time);
            temp = (TextView) view.findViewById(R.id.txt_temp);
            summ = (TextView) view.findViewById(R.id.txt_summ);
        }
    }

    public WeatherRecycleAdapter(List<WeatherModel> list,ListClickListner event) {
        this.weather = list;
        this.event=event;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listrow, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WeatherModel obj = weather.get(position);
        holder.title.setText(obj.getTimezone());
        holder.time.setText(DateTimeUtil.convert_MiSecToTimeString(DateTimeUtil.convert_SecToMilli(obj.getCurrently().getTime())));
        holder.temp.setText(String.valueOf(obj.getCurrently().getTemperature()));
        holder.summ.setText(obj.getCurrently().getSummary());
        holder.card_view.setTag(obj);
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.onClick((WeatherModel) v.getTag());
            }
        });


    }

    @Override
    public int getItemCount() {
        return weather.size();
    }

    public void notifyData(List<WeatherModel> weatherList){
        weather.clear();
        weather.addAll(weatherList);
        notifyDataSetChanged();
    }
}