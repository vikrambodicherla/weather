package com.markiv.weather.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.markiv.weather.WeatherApplication;
import com.markiv.weather.data.WeatherConditions;
import com.markiv.weather.data.WeatherRepository;

import java.io.IOException;
import java.util.List;

/**
 * Created by vikrambd on 6/8/17.
 */

public class WeatherViewModel extends AndroidViewModel {
    private final WeatherRepository weatherRepository;

    public WeatherViewModel(Application application) {
        super(application);
        weatherRepository = WeatherApplication.getWeatherRepository(application);
    }

    public LiveData<List<WeatherConditions>> getWeatherConditions(List<String> places)
            throws IOException {
        return weatherRepository.getWeatherConditions(places);
    }
}
