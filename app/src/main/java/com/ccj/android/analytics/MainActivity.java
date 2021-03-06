package com.ccj.android.analytics;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ccj.client.android.analytics.Event;
import com.ccj.client.android.analytics.EventManager;
import com.ccj.client.android.analytics.bean.AkcEventModel;
import com.ccj.client.android.analytics.enums.AkcEventType;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "TAG";
    TextView tv_show;
    Button btn_event, btn_pv, btn_cancel, btn_start,btn_push,btn_request_client_id;
    private int i = 0, j = 0;
    int haha=11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_show = findViewById(R.id.tv_show);
        btn_event = findViewById(R.id.btn_event);
        btn_pv = findViewById(R.id.btn_pv);
        btn_push=findViewById(R.id.btn_push);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_start = findViewById(R.id.btn_start);
        btn_request_client_id = findViewById(R.id.btn_request_client_id);


        verifyStoragePermissions(MainActivity.this);


        /**
         * 多线程操作
         */
        btn_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int k = 1; k <= 5; k++) {

                    AkcEventModel akcEventModel = AkcEventModel.makeModel(MainActivity.this);
                    akcEventModel.setActionType(AkcEventType.launch.getType());
                    Event.event("event " + k, akcEventModel.toString(),"event el");

                }
             /*   //添加自定义参数ecp,ecp默认为null
                Map ecp = new HashMap();
                ecp.put("自定义key1", "自定义value1");
                ecp.put("自定义key2", "自定义value2");
                JJEvent.expose("ss", "首页", "点击" + "button" + (++i), ecp);*/
            }
        });

        /**
         * 主动推送
         * */
        btn_pv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加自定义参数ecp,ecp默认为null
                Map ecp = new HashMap();
                ecp.put("自定义key1", "自定义value1");
                ecp.put("自定义key2", "自定义value2");
                Event.expose("ss1", "首页", "点击" + "button" + (++i), ecp);
                EventManager.pushEvent();
            }
        });


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //JJEventManager.init(getApplication(), "第二次cookie", "dt", "cid", true); //方式1


                EventManager.Builder builder = new EventManager.Builder(getApplication()); //方式2
                builder
                        .setApiHost(BuildConfig.UBT_API_HOST)
                        .setAppVersion(BuildConfig.VERSION_NAME)
                        .setHostCookie("s test=cookie String;")//cookie(只会初始化调用一次,后续上传不会再调用)
                        .setDebug(true)//是否是debug
//                        .setSidPeriodMinutes(15)//sid改变周期
//                        .setPushLimitMinutes(1)//多少分钟 push一次
//                        .setPushLimitNum(10)//多少条 就主动进行push
//                        .setCookieIntercept(new CookieFacade() {
//                            @Override
//                            public String getRequestCookies() { //宿主cookie通用参数 动态插入器(每次上传都会执行该方法)
//                                return "cookie-->"+(++haha);
//                            }
//                        })
                        .start();//开始*/


            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventManager.destoryEventService();
                // EDBHelper.deleteEventListByLimit(0, 1);
                // ELogger.logWrite(TAG, "getEventListByRows-->" + EDBHelper.getEventRowCount());
            }
        });

        btn_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventManager.pushEvent();
                Toast.makeText(MainActivity.this,"saefsdfasdf-->"+i,Toast.LENGTH_SHORT).show();
            }
        });


    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };


    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
