package com.markiv.weather.data;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by vikrambd on 6/14/17.
 */

public class WeatherPoller extends JobService implements LifecycleObserver {
    private final LifecycleOwner lifecycleOwner;

    private WeatherPoller(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        this.lifecycleOwner.getLifecycle().addObserver(this);
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    /*public static WeatherPoller poll(Context context, LifecycleOwner lifecycleOwner) {
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        Job job = dispatcher.newJobBuilder().setService(WeatherPoller.class)
                .setTag("weatherpoller").setRecurring(true).setLifetime(Lifetime.FOREVER)
                .setTrigger(Trigger.executionWindow(0, 10))
                .setReplaceCurrent(false).setConstraints(Constraint.ON_ANY_NETWORK)
                .build();
        dispatcher.mustSchedule(job);
    }*/
}
