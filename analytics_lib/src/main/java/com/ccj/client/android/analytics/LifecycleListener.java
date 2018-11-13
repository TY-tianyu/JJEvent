package com.ccj.client.android.analytics;

import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.util.Log;


import java.util.HashMap;


import static com.ccj.client.android.analytics.EConstant.TAG;

public final class LifecycleListener implements LifecycleObserver {


    @OnLifecycleEvent(Event.ON_CREATE)
    public final void onAppCreate() {
        Log.d(TAG, "Lifecycle: onAppCreate…");

//        ENetHelper.create(JJEventManager.getContext(), new OnNetResponseListener() {
//            @Override
//            public void onPushSuccess() {
//
//            }
//
//            @Override
//            public void onPushEorr(int errorCode) {
//                //.请求成功,返回值错误,根据接口返回值,进行处理.
//            }
//
//            @Override
//            public void onPushFailed() {
//                //请求失败;不做处理.
//
//            }
//        }).requestClientId(EConstant.JSON_BODY_1);




        HashMap ecp = new HashMap();
        ecp.put("自定义key1", "自定义value1");
        ecp.put("自定义key2", "自定义value2");
        JJEvent.event("event Lifecycle: onAppCreate…", "event ea", "event el");
    }

    @OnLifecycleEvent(Event.ON_START)
    public final void onMoveToForeground() {
        Log.d(TAG, "Lifecycle: Returning to foreground…");
        HashMap ecp = new HashMap();
        ecp.put("自定义key1", "自定义value1");
        ecp.put("自定义key2", "自定义value2");
        JJEvent.event("event Lifecycle: Returning to foreground…", "event ea", "event el");
    }

    @OnLifecycleEvent(Event.ON_STOP)
    public final void onMoveToBackground() {
        Log.d(TAG, "Lifecycle: Moving to background…");
        HashMap ecp = new HashMap();
        ecp.put("自定义key1", "自定义value1");
        ecp.put("自定义key2", "自定义value2");
        JJEvent.event("event Lifecycle: Moving to background…", "event ea", "event el");
    }

    public final void setupLifecycleListener( LifecycleObserver lifecycleListener) {
        LifecycleOwner lifecycleOwner = ProcessLifecycleOwner.get();
        lifecycleOwner.getLifecycle().addObserver(lifecycleListener);
    }
}
