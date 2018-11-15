package com.ccj.android.analytics;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.support.annotation.NonNull;

import com.ccj.client.android.analytics.LifecycleListener;

import static com.ccj.client.android.analytics.LifecycleListener.setupLifecycleListener;
//import com.ccj.client.android.analytics.SampleLifecycleListener;

public class SampleApp extends Application {

    private LifecycleListener sampleLifecycleListener;



    @Override
    public void onCreate() {
        super.onCreate();

        sampleLifecycleListener = new LifecycleListener(getApplicationContext());

        setupLifecycleListener(sampleLifecycleListener);


    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
