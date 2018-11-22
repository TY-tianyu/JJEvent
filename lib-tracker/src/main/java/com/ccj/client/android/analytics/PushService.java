package com.ccj.client.android.analytics;

import java.util.Timer;
import java.util.TimerTask;

import static com.ccj.client.android.analytics.Constant.SWITCH_OFF;

/**
 *  *  * 后台服务,app启动时开启.
 * Created by chenchangjun on 18/2/24.
 */

 class PushService {

    private static PushService pushService;

    /**
     * Timer不保证任务执行的十分精确。
     * Timer类的线程安全的。
     */
    private  Timer timer = new Timer();
    private TimerTask task;
    private static boolean timerHasCanceled=false;


    private void init() {
        Logger.logWrite(Constant.TAG, " TimerTask init  on thread-->"+Thread.currentThread().getName());
        task = new TimerTask() {
            @Override
            public void run() {
                //经过验证 run  on thread-->Timer-0,并不在主线程,省去了我的工作...
                if (SWITCH_OFF) {
                    return;
                }
                PushTask.pushEvent();
            }
        };
        if (timerHasCanceled){
            timer=new Timer();
            timerHasCanceled=false;
        }
        double periodD =  Constant.PUSH_CUT_DATE * 1000;
        int period = Double.valueOf(periodD).intValue();
        timer.schedule(task, period, period );//延时20秒后执行,每次执行间隔PUSH_CUT_DATE分钟
    }


    /**
     * 主动调用push操作,运行在主线程中.
     */
    public void excutePushEvent() {
        if (SWITCH_OFF) {
            Logger.logWrite(Constant.TAG, " excutePushEvent  is SWITCH_OFF,please check SWITCH_OFF is true or false!");
            return;
        }
        PushTask.pushEvent();
    }


    /**
     * 停止 推送 服务
     */
    public void stopEventService() {
        Logger.logWrite(Constant.TAG, " EPushService is stop");
        if (task != null && timer != null) {
            timer.cancel();
            task.cancel();
            timerHasCanceled=true;

        }

    }





    /**************generate*****************/


    /**
     * 启动 定时推送服务
     */
    public static void startService() {
        if (pushService!=null){
            getSingleInstance().init();
        }else {
            getSingleInstance();
        }

    }

    /**
     * 获取 推送服务 单例
     * @return
     */
    public static PushService getSingleInstance() {

        if (pushService == null) {
            synchronized (EventManager.class) {//双重检查
                if (pushService == null) {
                    pushService = new PushService();

                }
            }
        }
        return pushService;
    }


    private PushService() {
        init();
    }


}
