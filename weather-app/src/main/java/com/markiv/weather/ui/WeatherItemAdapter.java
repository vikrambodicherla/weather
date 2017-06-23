package com.markiv.weather.ui;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.markiv.weather.data.WeatherConditions;
import com.markiv.weather.databinding.WeatherItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vikrambd on 6/7/17.
 */

public class WeatherItemAdapter extends RecyclerView.Adapter<WeatherItemAdapter.WeatherItemViewHolder> {
    private final Context context;
    private final List<WeatherConditions> weatherConditions = new ArrayList<>();

    public WeatherItemAdapter(Context context) {
        this.context = context;
        this.weatherConditions.add(new WeatherConditions("Singapore", "Singapore", "Singapore", "Singaore", "Windy", "Today", "67", "Windy"));
        this.weatherConditions.add(new WeatherConditions("Singapore", "Singapore", "Singapore", "Singaore", "Windy", "Today", "67", "Windy"));
        this.weatherConditions.add(new WeatherConditions("Singapore", "Singapore", "Singapore", "Singaore", "Windy", "Today", "67", "Windy"));
    }

    public WeatherItemAdapter(Context context, LiveData<List<WeatherConditions>> weatherConditions,
                              LifecycleOwner lifecycleOwner) {
        this.context = context;
        weatherConditions.observe(lifecycleOwner, new Observer<List<WeatherConditions>>() {
            @Override
            public void onChanged(@Nullable List<WeatherConditions> weatherConditions) {
                WeatherItemAdapter.this.weatherConditions.clear();
                WeatherItemAdapter.this.weatherConditions.addAll(weatherConditions);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public WeatherItemViewHolder onCreateViewHolder(ViewGroup holder, int viewType) {
        return new WeatherItemViewHolder(
                WeatherItemBinding.inflate(LayoutInflater.from(context), holder, false));
        //return new WeatherItemViewHolder(LayoutInflater.from(context).inflate(R.layout.weather_item, holder, false));
    }

    @Override
    public void onBindViewHolder(WeatherItemViewHolder weatherItemViewHolder, int position) {
        weatherItemViewHolder.bind(weatherConditions.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherConditions.size();
    }

    public static class WeatherItemViewHolder extends RecyclerView.ViewHolder {
        private final WeatherItemBinding weatherItemBinding;

        public WeatherItemViewHolder(WeatherItemBinding binding) {
            super(binding.getRoot());
            this.weatherItemBinding = binding;
        }

        void bind(WeatherConditions weatherConditions) {
            weatherItemBinding.setWeatherconditions(weatherConditions);
            weatherItemBinding.executePendingBindings();
        }
    }

    /*public static class WeatherItemViewHolder extends RecyclerView.ViewHolder {
        TextView conditions;
        TextView temp;
        TextView place;

        public WeatherItemViewHolder(View view) {
            super(view);
            conditions = (TextView) view.findViewById(R.id.conditions);
            temp = (TextView) view.findViewById(R.id.temp);
            place = (TextView) view.findViewById(R.id.place);
        }

        public void bind(WeatherConditions weatherConditions) {
            conditions.setText(weatherConditions.text);
            temp.setText(weatherConditions.temp);
            place.setText(weatherConditions.place);
        }
    }*/
}
