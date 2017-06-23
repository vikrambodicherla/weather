package com.markiv.weather.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.markiv.weather.R;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner {
    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<String> places = Arrays.asList("Delhi", "Rome",
                "New York", "Seattle", "Singapore", "Madrid", "San Francisco", "Sunnyvale");

        WeatherViewModel viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        try {
            WeatherItemAdapter adapter = new WeatherItemAdapter(this, viewModel.getWeatherConditions(places), this);
            ((RecyclerView) findViewById(R.id.list)).setAdapter(adapter);
        }
        catch (IOException e) {
            Toast.makeText(this, "Failed to get weather", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
