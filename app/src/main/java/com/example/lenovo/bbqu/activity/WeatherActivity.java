package com.example.lenovo.bbqu.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.bbqu.R;
import com.example.lenovo.bbqu.controller.GetAndParseJson;
import com.example.lenovo.bbqu.model.Weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherActivity extends AppCompatActivity {

    private List<Weather> listWeather;
    // private ListView list;
    private TextView city,cityid,weatherinfo,temp1,temp2,ptime;
    private ImageView img1,img2;

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case GetAndParseJson.PARSESUCCWSS:
                    listWeather=(List<Weather>) msg.obj;
                    // initData();
                    show();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_weather);
        GetAndParseJson getAndParseJson=new GetAndParseJson(mHandler);
        getAndParseJson.getJsonFromInternet();

        city = (TextView)findViewById(R.id.city_info);
        cityid = (TextView)findViewById(R.id.cityid_info);
        temp1 = (TextView)findViewById(R.id.temp1_info);
        temp2 = (TextView)findViewById(R.id.temp2_info);
        weatherinfo = (TextView)findViewById(R.id.weather_info);
        ptime = (TextView)findViewById(R.id.ptime_info);

        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
    }
    public void finish(View v){
        finish();
    }
    private void show() {
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        for (Weather weather : listWeather) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("city", weather.getCity());
            item.put("cityid", weather.getCityid());
            item.put("temp1", weather.getTemp1());
            item.put("temp2", weather.getTemp2());
            item.put("weather", weather.getWeather());
            item.put("img1", weather.getImg1());
            item.put("img2", weather.getImg2());
            items.add(item);

            city.setText(weather.getCity());
            cityid.setText(weather.getCityid());
            temp1.setText(weather.getTemp1());
            temp2.setText(weather.getTemp2());
            weatherinfo.setText(weather.getWeather());
            ptime.setText(weather.getPtime());
        }
    }
}
