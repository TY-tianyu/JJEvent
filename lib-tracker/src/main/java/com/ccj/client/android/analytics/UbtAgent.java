package com.ccj.client.android.analytics;

import android.content.Context;
import android.util.Log;

import com.ccj.client.android.analytics.bean.AkcEventModel;
import com.ccj.client.android.analytics.enums.AkcEventType;

import static com.ccj.client.android.analytics.Constant.TAG;

public class UbtAgent {

    public static void onEvent(AkcEventModel akcEventModel) {
        Log.d(TAG, "onEvent:    " + akcEventModel.toString());
        Event.event("onEvent "
                , akcEventModel.toString()
                , "onEvent");
    }

    public static void onAppLaunchEvent(Context context) {
        Log.d(TAG, "Lifecycle: onAppCreate…");
        Event.event("event Lifecycle: onAppCreate…"
                , new AkcEventModel.Builder(context)
                        .setActionType(AkcEventType.launch)
                        .build()
                        .toString()
                , "onAppLaunchEvent");
    }

    public static void onAppActiveEvent(Context context , String spm) {
        Log.d(TAG, "Lifecycle: Returning to foreground…");
        Event.event("event Lifecycle: Returning to foreground…"
                , new AkcEventModel.Builder(context)
                        .setActionType(AkcEventType.active)
                        .setSpm(spm)
                        .build()
                        .toString()
                , "onAppActiveEvent");
    }

    public static void onAppDeactiveEvent(Context context , String spm) {

        Log.d(TAG, "Lifecycle: Moving to background…");
        Event.event("event Lifecycle: Moving to background…"
                , new AkcEventModel.Builder(context)
                        .setActionType(AkcEventType.deactive)
                        .setSpm(spm)
                        .build()
                        .toString()
                , "onAppDeactiveEvent");

    }

    /**
     * spm    string

     事件的编号，对于用户事件，标识用户操作的页面元素；对于系统事件，标识事件发生时所处的页面或者某个后台动作，spm的定义由“spm定义文档”提供。


     refSpm   string

     前一个事件的SPM，例如一个按钮的spm是1.10.123，点击以后跳到页面1.11，对于1.11的页面事件来说，refSpm就是1.10.123。

     只有actionType为pageview的事件需要传该参数
     */
    public static void onPageViewEvent(Context context , String refSpm , String spm) {
        Log.d(TAG, "onPageViewEvent:    refSpm=" + refSpm + "    spm="+spm);
        Event.event("onPageViewEvent "
                , new AkcEventModel.Builder(context)
                        .setActionType(AkcEventType.pageview)
                        .setRefSpm(refSpm)
                        .setSpm(spm)
                        .build()
                        .toString()
                , "onPageViewEvent");

    }

    /**
     *
     * @param context
     * @param spm
     * @param tab  元素偏移量，固定为2位
     * @param mi    页面中的当前tab，直接传tab上的文字即可，事件所在的元素在tab内时需要，由“spm定义文档”提供
     */
    public static void onClickEvent(Context context , String spm , AkcEventModel.CurrentPropertiesBean currentPropertiesBean , String tab , String mi) {
        Log.d(TAG, "onClickEvent:    "+ "    spm="+spm);
        Event.event("onClickEvent "
                , new AkcEventModel.Builder(context)
                        .setActionType(AkcEventType.click)
                        .setSpm(spm)
                        .setCurrentProperties(currentPropertiesBean)
                        .setTab(tab)
                        .setMi(mi)
                        .build()
                        .toString()
                , "onClickEvent");

    }






}
