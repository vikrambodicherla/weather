package com.markiv.weather;

import android.app.Application;
import android.content.Context;

import com.markiv.weather.data.WeatherRepository;

/**
 * Created by vikrambd on 6/7/17.
 */

public class WeatherApplication extends Application {
    WeatherRepository weatherRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        weatherRepository = new WeatherRepository(this);
    }

    public static WeatherRepository getWeatherRepository(Context context) {
        return ((WeatherApplication) (context.getApplicationContext())).weatherRepository;
    }
}
