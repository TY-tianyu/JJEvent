package com.ccj.client.android.analytics;

import com.ccj.client.android.analyticlib.BuildConfig;

/**
 * Created by chenchangjun on 18/2/8.
 */

 public class Constant {

    static volatile boolean SWITCH_OFF = false; //全局开关,用于在接口返回时,控制sdk是否启动
    static volatile boolean DEVELOP_MODE = true; //全局开关,开发模式切换


    public static final String TAG = "UBT-Event";



    /**
     * 数据库名称
     */
    static final String DB_NAME = "jjevent.db";
    static final int DB_VERSION = 1;//修改时,必须递增 ,


    /**
     * 接口地址
     */

    public static final String CLIENT_ID_PATH = "/ubt/api/client";

    public static  String CLIENT_ID_URL = "";

    public static final String COLLECT_PATH = "/ubt/api/event/list";

    public static  String COLLECT_URL = "";

   public static String JSON_BODY_1 = "{\n" +
           "\t\"appId\" : \"com.aikucun.apptest\",\n" +
           "    \"appVersion\" : \"2.4.6\",\n" +
           "    \"deviceBrand\" : \"iPhone\",\n" +
           "    \"deviceId\" : \"2974870C-BE7F-4240-B96D-5CE80479F987\",\n" +
           "    \"deviceIdType\" : \"IDFA\",\n" +
           "    \"deviceModel\" : \"iPhone\",\n" +
           "    \"deviceType\" : \"iPhone (6)\",\n" +
           "    \"download\" : \"AppStore\",\n" +
           "    \"osType\" : \"iOS\",\n" +
           "    \"osVersion\" : \"10.3.1\",\n" +
           "    \"platform\" : \"iOS\",\n" +
           "    \"screenX\" : 414,\n" +
           "    \"screenY\" : 736,\n" +
           "    \"sdkVersion\" : \"0.1\"\n" +
           "\t}";


    /***********===================**time schedule**=============*********/

    /**
     * 记录到达xx条,主动进行上传,默认10
     */
    static int  PUSH_CUT_NUMBER = 10;

    /**
     * 上传间隔事件 单位：秒, 默认20秒
     */
    static double PUSH_CUT_DATE = 20;

    /**
     * sid改变周期的标志 单位：秒, 默认20秒
     */
    static int PUSH_FINISH_DATE = 20;



    /**
     * 统计类别
     */
    public static final String EVENT_TYPE_DEFAULT = "default";//暂时先用默认值
    public static final String EVENT_TYPE_PV = "screenview";//屏幕
    public static final String EVENT_TYPE_EVENT = "event";//点击
    public static final String EVENT_TYPE_EXPOSE = "show";//曝光,接口不同


    public static String DownloadChannel = "";
    public static String AppVersion = "";
}
