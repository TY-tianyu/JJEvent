package com.ccj.client.android.analytics;

import android.text.TextUtils;

import com.ccj.client.android.analytics.bean.EventBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class ExposeTask implements Runnable {


    private String exposeID;
    private String ec;
    private String ea;
    private Map mapEcp;


    public ExposeTask(String exposeID, String ec, String ea, Map mapEcp) {
        this.exposeID = exposeID;
        this.ec = ec;
        this.ea = ea;
        this.mapEcp = mapEcp;
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
            //ELogger.logWrite(EConstant.TAG, "thread-"+Thread.currentThread().getName());


            EventBean bean = EventDecorator.generateExposedBean(exposeID, ec, ea, mapEcp);


            if (bean == null) {
                Logger.logWrite(Constant.TAG, "expose bean == null");
                return;
            }


            Logger.logWrite(Constant.TAG, "thread-"+Thread.currentThread().getName()+",expose " + bean.toString());


            if (!TextUtils.isEmpty(bean.getExposed_id())) {//如果数据中有exposed id则执行 查看操作
                List<EventBean> resultList = DBHelper.getEventByExposedID(bean.getExposed_id());

                if (resultList != null && resultList.size() > 0) {//如果存在id,则更新
                    Logger.logError(Constant.TAG, "Exposed_id-"+bean.toString()+"exposed id 有重复数据 ");

                    DBHelper.updateEventBean(bean, bean.getExposed_id());
                } else {
                    DBHelper.addEventData(bean);//不存在id,则新增
                    EventDecorator.pushEventByNum();
                }
            } else {//如果没有exposedid 则正常添加
                DBHelper.addEventData(bean);
                EventDecorator.pushEventByNum();
            }




        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    @Override
    public String toString() {
        return "ExposeTask{" +
                "exposeID='" + exposeID + '\'' +
                ", ec='" + ec + '\'' +
                ", ea='" + ea + '\'' +
                ", mapEcp=" + mapEcp +
                '}';
    }
}
