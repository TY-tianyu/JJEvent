package com.ccj.client.android.analytics;

import com.ccj.client.android.analytics.bean.EventBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class EventTask implements Runnable {


    private String ec;
    private String ea;
    private String el;
    private Map ecp;

    public EventTask(String ec, String ea, String el, Map ecp) {
        this.ec = ec;
        this.ea = ea;
        this.el = el;
        this.ecp = ecp;
    }

    @Override
    public void run() {

        if (!EventManager.hasInit) {
            Logger.logError(Constant.TAG, "please init JJEventManager!");
            return;
        }

        if (Constant.SWITCH_OFF) {
            Logger.logWrite(Constant.TAG, "the sdk is SWITCH_OFF");
            return;
        }

        try {

            EventBean bean = EventDecorator.generateEventBean(ec, ea, el, ecp);

            if (bean == null) {
                Logger.logWrite(Constant.TAG, " event bean == null");
                return;
            }

            Logger.logWrite(Constant.TAG, " event " + bean.toString());


            DBHelper.addEventData(bean);



            EventDecorator.pushEventByNum();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public String toString() {
        return "EventTask{" +
                "ec='" + ec + '\'' +
                ", ea='" + ea + '\'' +
                ", el='" + el + '\'' +
                ", ecp=" + ecp +
                '}';
    }
}