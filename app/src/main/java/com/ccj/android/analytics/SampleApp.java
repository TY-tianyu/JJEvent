package com.ccj.android.analytics;

import android.app.Application;

import com.ccj.client.android.analytics.SampleLifecycleListener;

public class SampleApp extends Application {

    private SampleLifecycleListener sampleLifecycleListener;

    @Override
    public void onCreate() {
        super.onCreate();

        sampleLifecycleListener = new SampleLifecycleListener();

        sampleLifecycleListener.setupLifecycleListener(sampleLifecycleListener);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
