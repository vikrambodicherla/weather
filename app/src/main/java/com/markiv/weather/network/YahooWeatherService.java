package com.markiv.weather.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by vikrambd on 6/7/17.
 */

public interface YahooWeatherService {
    @GET
    Call<WeatherConditionsResponse> getWeather(@Url String url);
}
