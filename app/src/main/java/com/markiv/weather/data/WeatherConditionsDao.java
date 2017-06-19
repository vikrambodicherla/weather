package com.markiv.weather.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by vikrambd on 6/7/17.
 */

@Dao
public interface WeatherConditionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeatherConditions(List<WeatherConditions> weatherConditions);

    @Query("SELECT * FROM WeatherConditions WHERE place in (:places)")
    LiveData<List<WeatherConditions>> getWeatherConditionsForPlaces(List<String> places);
}
