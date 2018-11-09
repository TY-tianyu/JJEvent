package com.ccj.client.android.analytics;

/**
 * Created by chenchangjun on 18/2/8.
 */

 public class EConstant {

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
    static String CLIENT_ID_URL =
            "http://192.168.120.110:8080/ubt/api/client?appid=28765893&sig=2b4555d0dc9ed905e1802f007bd09e7478114be4&timestamp=1541663667&noncestr=cueedgaq&did=FCE05F90DF99483ABA8AAC9368C3B1BB";

    public static String COLLECT_URL = "http://192.168.120.110:8080/ubt/api/event/list"; //TODO 这里是要上传数据的接口

   static String JSON_BODY_1 = "{\n" +
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


    /**
     * click(点击)/pageview(页面访问)/launch(App启动)/quit(App退出)/
     * active(App进前台)/deactive(App退后台)/expose(曝光)/
     * scroll(滑屏)/crash(崩溃)/error(错误)
     */

    public enum AkcEventType {

        // 因为已经定义了带参数的构造器，所以在列出枚举值时必须传入对应的参数
        click("click"), pageview("pageview"), scroll("scroll")
        , launch("launch"), quit("quit")
        , active("active"), deactive("deactive"), expose("expose")
        , crash("crash"), error("error");

        // 定义一个 private 修饰的实例变量
        private String mType;

        private AkcEventType(String type) {
            this.mType = type;
        }

        public String getType() {
            return mType;
        }

        public void setType(String type) {
            this.mType = type;
        }

        @Override
        public String toString(){
            return mType;
        }
    }






}
