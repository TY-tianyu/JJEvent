package com.ccj.client.android.analytics;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.Context;
import android.util.Log;

import com.ccj.client.android.analytics.bean.AkcEventModel;
import com.ccj.client.android.analytics.enums.AkcEventType;

import static com.ccj.client.android.analytics.Constant.TAG;

public final class LifecycleListener implements LifecycleObserver {

    private Context context;


    public LifecycleListener(Context context) {
        this.context = context;
    }

    @OnLifecycleEvent(android.arch.lifecycle.Lifecycle.Event.ON_CREATE)
    public final void onAppCreate() {
        Log.d(TAG, "Lifecycle: onAppCreate…");
        AkcEventModel akcEventModel = AkcEventModel.makeModel(context);
        akcEventModel.setActionType(AkcEventType.launch.getType());
        Event.event("event Lifecycle: onAppCreate…", akcEventModel.toString(), "event el");


    }

    @OnLifecycleEvent(android.arch.lifecycle.Lifecycle.Event.ON_START)
    public final void onMoveToForeground() {
        Log.d(TAG, "Lifecycle: Returning to foreground…");
        AkcEventModel akcEventModel = AkcEventModel.makeModel(context);
        akcEventModel.setActionType(AkcEventType.active.getType());
        Event.event("event Lifecycle: Returning to foreground…", akcEventModel.toString(), "event el");
    }

    @OnLifecycleEvent(android.arch.lifecycle.Lifecycle.Event.ON_STOP)
    public final void onMoveToBackground() {
        Log.d(TAG, "Lifecycle: Moving to background…");
        AkcEventModel akcEventModel = AkcEventModel.makeModel(context);
        akcEventModel.setActionType(AkcEventType.deactive.getType());
        Event.event("event Lifecycle: Moving to background…", akcEventModel.toString(), "event el");
    }

    public static final void setupLifecycleListener( LifecycleObserver lifecycleListener) {
        LifecycleOwner lifecycleOwner = ProcessLifecycleOwner.get();
        lifecycleOwner.getLifecycle().addObserver(lifecycleListener);
    }
}
