package com.ccj.android.analytics;

import android.app.Application;
import android.util.Log;

import com.ccj.client.android.analytics.AppLifecycleListener;

import static com.ccj.client.android.analytics.EConstant.TAG;

public class SampleApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        AppLifecycleListener.Companion.setupLifecycleListener(new AppLifecycleListener());

    }

    @Override
    public void onTerminate() {
        Log.d(TAG, "Lifecycle: onTerminate…");
        super.onTerminate();


    }


}
