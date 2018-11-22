package com.ccj.client.android.analytics;

import android.content.Context;

import com.ccj.client.android.analytics.utils.EDeviceUtils;

import java.util.List;

/**
 * 推送任务,可静态执行
  判断网络, 对数据库中数据进行上传. 上传完毕,删除db相应数据.

 * Created by chenchangjun on 18/2/24.
 */

 class PushTask {

    private static volatile String cut_point_date = "";//校验数据库最新数据时间戳


    protected static synchronized void pushEvent() {

        Logger.logWrite(Constant.TAG, "timer schedule pushEvent is start-->" + cut_point_date);
        Logger.logWrite(Constant.TAG, " timer schedule pushEvent run  on thread-->"+Thread.currentThread().getName());

        Context context = EventManager.getContext();
        if (context==null){
            Logger.logWrite(Constant.TAG, " JJEventManager.getContext() 为空,返回");
            return;
        }

        //0. 判断client ID是否获取到
        if (!EventManager.isClientIdValid()) {
            Logger.logWrite(Constant.TAG, " timer schedule 判断是否获取到Client ID, 未获取, 返回");
        }

        //1.判断网络状况是否良好
        if (!EDeviceUtils.isNetworkConnected(context)) {
            Logger.logWrite(Constant.TAG, " timer schedule 判断网络状况是否良好, 网络未连接, 返回");

            return;
        }

        //2.判断是否正在进行网络请求.`isLoading=false` 才能继续.(类似于线程锁)
        if (NetHelper.getIsLoading()) {
            Logger.logWrite(Constant.TAG, " timer schedule 正在进行网络请求,返回");

            return;
        }

        //3.校验数据库最新数据时间戳vs当前时间.
        cut_point_date = EventDecorator.getIT();



        //4.获取小于当前时间的数据 集合`push_list`.

        List list = DBHelper.getEventListByDate(cut_point_date);

        if (list == null || list.size() == 0) {
            Logger.logWrite(Constant.TAG, "list.size() == 0  cancel push");
            return;
        }

        NetHelper.create(EventManager.getContext(), new OnNetResponseListener() {
            @Override
            public void onPushSuccess() {
                //5*请求成功,返回值正确, 删除`cut_point_date`之前的数据
                DBHelper.deleteEventListByDate(cut_point_date);
                EventDecorator.clearEventNum();

            }

            @Override
            public void onPushEorr(int errorCode) {
                //.请求成功,返回值错误,根据接口返回值,进行处理.
            }

            @Override
            public void onPushFailed() {
                //请求失败;不做处理.

            }
        }).sendEvent(Constant.EVENT_TYPE_DEFAULT, list);


    }

}
