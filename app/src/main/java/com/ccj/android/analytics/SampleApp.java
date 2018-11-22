package com.ccj.android.analytics;

import android.app.Application;

import com.ccj.client.android.analytics.EventManager;
import com.ccj.client.android.analytics.LifecycleListener;
import com.ccj.client.android.analytics.UbtAgent;

import static com.ccj.client.android.analytics.LifecycleListener.setupLifecycleListener;
//import com.ccj.client.android.analytics.SampleLifecycleListener;

public class SampleApp extends Application {

    private LifecycleListener sampleLifecycleListener;



    @Override
    public void onCreate() {
        super.onCreate();

        initUbtEvent();


    }

    private void initUbtEvent() {
        EventManager.Builder builder = new EventManager.Builder(this);
        builder
                .setApiHost(BuildConfig.UBT_API_HOST)
                .setAppVersion(BuildConfig.VERSION_NAME)
//                .setDownloadChannel(App.getAppConfig().getChannel())
//                .setUserId(!TextUtils.isEmpty(App.getAppConfig().getUserId()) ? App.getAppConfig().getUserId() : null)
                .setDebug(true)
                .start();

//        AppLifecycleHandler lifeCycleHandler = new AppLifecycleHandler((AppLifecycleHandler.LifeCycleDelegate)this);
//        this.registerLifecycleHandler(lifeCycleHandler);
        sampleLifecycleListener = new LifecycleListener(getApplicationContext());
        setupLifecycleListener(sampleLifecycleListener);

        UbtAgent.onAppLaunchEvent(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
