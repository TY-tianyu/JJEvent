package com.ccj.android.analytics;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.support.annotation.NonNull;

import com.ccj.client.android.analytics.LifecycleListener;
//import com.ccj.client.android.analytics.SampleLifecycleListener;

public class SampleApp extends Application implements LifecycleOwner{

    private LifecycleListener sampleLifecycleListener;

    private LifecycleRegistry mLifecycleRegistry;


    @Override
    public void onCreate() {
        super.onCreate();

        sampleLifecycleListener = new LifecycleListener();

        sampleLifecycleListener.setupLifecycleListener(sampleLifecycleListener);

//        mLifecycleRegistry = new LifecycleRegistry(this);
//        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
//
//        this.getLifecycle().addObserver(new LifecycleListener());

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
