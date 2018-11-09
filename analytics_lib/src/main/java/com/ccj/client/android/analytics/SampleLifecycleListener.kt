package com.ccj.client.android.analytics

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ProcessLifecycleOwner
import android.util.Log
import com.ccj.client.android.analytics.EConstant.TAG
import java.util.HashMap

/**
 * Tracks the Lifecycle of the whole application thanks to {@link LifecycleObserver}.
 * This is registered via {@link ProcessLifecycleOwner#get()} ()}. The events are designed
 * to be dispatched with delay (by design) so activity rotations don't trigger these calls.
 * See: https://developer.android.com/reference/android/arch/lifecycle/ProcessLifecycleOwner.html
 */
class SampleLifecycleListener : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        Log.d(TAG, "Lifecycle: Returning to foreground…")

        //添加自定义参数ecp,ecp默认为null
        val ecp = HashMap<String , String>()
        ecp.put("自定义key1", "自定义value1")
        ecp.put("自定义key2", "自定义value2")
        JJEvent.event("event Lifecycle: Returning to foreground…", "event ea", "event el")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onMoveToBackground() {
        Log.d(TAG, "Lifecycle: Moving to background…")

        //添加自定义参数ecp,ecp默认为null
        val ecp = HashMap<String , String>()
        ecp.put("自定义key1", "自定义value1")
        ecp.put("自定义key2", "自定义value2")
        JJEvent.event("event Lifecycle: Moving to background…", "event ea", "event el")

    }

    fun setupLifecycleListener(lifecycleListener: LifecycleObserver) {
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleListener)
    }
}