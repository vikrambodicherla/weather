package com.markiv.weather.network;

import com.markiv.weather.data.WeatherConditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vikrambd on 6/7/17.
 */

public class WeatherConditionsResponse {
    Query query;

    public static class Query {
        Results results;
    }

    public static class Results {
        Channel[] channel;
    }

    public static class Channel {
        public Location location;
        public Item item;
    }

    public static class Location {
        public String city;
        public String country;
        public String region;
    }

    public static class Item {
        public Condition condition;
    }

    public static class Condition {
        public String code;
        public String date;
        public String temp;
        public String text;
    }

    public List<WeatherConditions> extractWeatherConditions(List<String> place) {
        if (query != null
                && query.results != null
                && query.results.channel != null && query.results.channel.length > 0) {
            Channel[] channels = query.results.channel;
            List<WeatherConditions> weatherConditions = new ArrayList<>();
            for (int i = 0; i < channels.length; i++) {
                weatherConditions.add(new WeatherConditions(place.get(i), channels[i]));
            }
            return weatherConditions;
        }

        return null;
    }
}
