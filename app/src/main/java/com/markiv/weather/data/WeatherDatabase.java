package com.markiv.weather.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by vikrambd on 6/7/17.
 */

@Database(entities = {WeatherConditions.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract WeatherConditionsDao weatherConditionsDao();
}
