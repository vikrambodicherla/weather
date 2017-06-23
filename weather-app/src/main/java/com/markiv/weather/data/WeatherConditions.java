package com.markiv.weather.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import com.markiv.weather.network.WeatherConditionsResponse;

/**
 * Created by vikrambd on 6/7/17.
 */

@Entity(primaryKeys = {"place"})
public class WeatherConditions {
    public String place;
    public String city;
    public String country;
    public String region;

    public String code;
    public String date;
    public String temp;
    public String text;

    public WeatherConditions() {
    }

    @Ignore
    public WeatherConditions(String place, String city, String country, String region, String code, String date, String temp, String text) {
        this.place = place;
        this.city = city;
        this.country = country;
        this.region = region;
        this.code = code;
        this.date = date;
        this.temp = temp;
        this.text = text;
    }

    @Ignore
    public WeatherConditions(String place, WeatherConditionsResponse.Channel channel) {
        this.place = place;

        if (channel.location != null) {
            city = channel.location.city;
            country = channel.location.country;
            region = channel.location.region;
        }

        if (channel.item != null && channel.item.condition != null) {
            code = channel.item.condition.code;
            date = channel.item.condition.date;
            temp = channel.item.condition.temp;
            text = channel.item.condition.text;
        }
    }
}
