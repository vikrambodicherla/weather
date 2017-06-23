package com.markiv.weather.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.markiv.weather.network.WeatherConditionsResponse;
import com.markiv.weather.network.YahooWeatherService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vikrambd on 6/7/17.
 */

public class WeatherRepository {
    private final YahooWeatherService yahooWeatherService;
    private final WeatherDatabase weatherDatabase;
    private ExecutorService executorService;

    public WeatherRepository(Context context) {
        this.yahooWeatherService = new Retrofit.Builder().
                baseUrl("https://query.yahooapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(YahooWeatherService.class);

        executorService = Executors.newSingleThreadExecutor();

        this.weatherDatabase = Room.databaseBuilder(context, WeatherDatabase.class,
                "weather-database").build();
    }

    public LiveData<List<WeatherConditions>> getWeatherConditions(final List<String> places) throws IOException {
        LiveData<List<WeatherConditions>> cachedData = weatherDatabase.weatherConditionsDao()
                .getWeatherConditionsForPlaces(places);
        if (cachedData.getValue() != null && cachedData.getValue().size() > 0) {
            return cachedData;
        } else {
            final MutableLiveData<List<WeatherConditions>> mutableLiveData = new MutableLiveData<>();
            yahooWeatherService.getWeather(getUrl(places)).enqueue(new Callback<WeatherConditionsResponse>() {
                @Override
                public void onResponse(Call<WeatherConditionsResponse> call, Response<WeatherConditionsResponse> response) {
                    if (response.isSuccessful()) {
                        insertAndNotify(response.body().extractWeatherConditions(places),
                                mutableLiveData);
                    }
                }

                @Override
                public void onFailure(Call<WeatherConditionsResponse> call, Throwable t) {

                }
            });
            return mutableLiveData;
        }
    }

    private void insertAndNotify(final List<WeatherConditions> weatherConditionsList,
                                 final MutableLiveData<List<WeatherConditions>> liveData) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                weatherDatabase.weatherConditionsDao()
                        .insertWeatherConditions(weatherConditionsList);
                liveData.postValue(weatherConditionsList);
            }
        });
    }

    private String getUrl(List<String> places) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String place : places) {
            stringBuilder.append(",").append("'" + place + "'");
        }

        return "https://query.yahooapis.com/v1/public/yql?" +
                "q=select location,item.condition from weather.forecast " +
                "where woeid in (select woeid from geo.places(1) where text in " +
                "(" + stringBuilder.substring(1).toString() + "))" +
                "&format=json";
    }
}
