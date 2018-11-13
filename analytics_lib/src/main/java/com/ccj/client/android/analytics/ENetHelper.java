package com.ccj.client.android.analytics;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import com.ccj.client.android.analytics.bean.ClientIdResponseModel;
import com.ccj.client.android.analytics.bean.EventBean;
import com.ccj.client.android.analytics.bean.ResultBean;
import com.ccj.client.android.analytics.net.core.AuthFailureError;
import com.ccj.client.android.analytics.net.core.DefaultRetryPolicy;
import com.ccj.client.android.analytics.net.core.Request;
import com.ccj.client.android.analytics.net.core.RequestQueue;
import com.ccj.client.android.analytics.net.core.Response;
import com.ccj.client.android.analytics.net.core.RetryPolicy;
import com.ccj.client.android.analytics.net.core.Tools.EVolley;
import com.ccj.client.android.analytics.net.core.VolleyError;
import com.ccj.client.android.analytics.net.core.VolleyLog;
import com.ccj.client.android.analytics.net.gson.EGson;
import com.ccj.client.android.analytics.net.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ccj.client.android.analytics.EConstant.TAG;

/**
 * 网络模块, 网络不好,需要缓存到本地.
 * Created by chenchangjun on 18/2/8.
 */

public class ENetHelper {

    private static ENetHelper ENetHelper;
    private static boolean isLoading = false;
    private static OnNetResponseListener responseListener;
    private RequestQueue queue;
    private Context context;

    private ENetHelper(Context context, OnNetResponseListener responseListener) {
        ENetHelper.responseListener = responseListener;
        queue = EVolley.newRequestQueue(context);
        this.context = context;

    }

    public static ENetHelper create(Context context, OnNetResponseListener responseListener) {

        if (ENetHelper == null) {
            synchronized (ENetHelper.class) {//双重检查
                if (ENetHelper == null) {
                    ENetHelper = new ENetHelper(context, responseListener);

                }
            }
        }
        return ENetHelper;
    }

    public static boolean getIsLoading() {
        return isLoading;
    }

    public void sendEvent(String style, List<EventBean> list) {

        switch (style) {

            case EConstant.EVENT_TYPE_PV:
                loadData(list);
                break;
            case EConstant.EVENT_TYPE_EVENT:
                loadData(list);
                break;
            case EConstant.EVENT_TYPE_EXPOSE:
                loadData(list);
                break;
            default:
                loadData(list);

                break;
        }

    }

    public void loadData(List<EventBean> list) {


        isLoading = true;

        EGson EGson = new GsonBuilder().disableHtmlEscaping().create();

        Map headers = new HashMap();
        headers.put("Content-Type", "application/json");

        Map map = new HashMap();
        map.put("list", EGson.toJson(list));
        ELogger.logWrite(TAG, "push map-->" + map.toString());


        EGsonRequest request = new EGsonRequest<>(Request.Method.POST
                , EConstant.COLLECT_URL, ResultBean.class, headers, null,
                new Response.Listener<ResultBean>() {
                    @Override
                    public void onResponse(ResultBean response) {
                        int code = response.getError_code();
                        String msg = "";
                        ELogger.logWrite(TAG, response.toString());

                        if (code == 0) {
                            responseListener.onPushSuccess();
                            ELogger.logWrite(TAG, "--onPushSuccess--");

                        } else {
                            responseListener.onPushEorr(code);
                            ELogger.logWrite(TAG, "--onPushError--");

                        }

                        isLoading = false;

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ELogger.logWrite(TAG, "--onVolleyError--");
                        responseListener.onPushFailed();
                        isLoading = false;
                    }
                }
        );
        queue.add(request);


    }

    public void requestClientId(final String jsonBody) {

        isLoading = true;

        EGson EGson = new GsonBuilder().disableHtmlEscaping().create();

        Map headers = new HashMap();
        headers.put("Content-Type", "application/json");

        Map map = new HashMap();
        map.put("data", jsonBody);
        ELogger.logWrite(TAG, "push map-->" + map.toString());


        EGsonRequest request = new EGsonRequest(Request.Method.POST, EConstant.CLIENT_ID_URL
                , ClientIdResponseModel.class, headers, null,
                new Response.Listener<ClientIdResponseModel>() {
                    @Override
                    public void onResponse(ClientIdResponseModel response) {
                        int code = response.getCode();
                        boolean isSuccess = response.isSuccess();
                        String clientId = response.getData().getClientId();
                        String msg = response.getMessage();
                        ELogger.logWrite(TAG, response.toString());

                        if (isSuccess) {
                            ELogger.logWrite(TAG, "--获取Client ID 成功--");


                            if (true == verifyStoragePermissions(context)){
                                writeClientId2SD(clientId);
                            } else {

                            }



                            responseListener.onPushSuccess();

                        } else {
                            responseListener.onPushEorr(code);
                            ELogger.logWrite(TAG, "--获取Client ID 失败--");

                        }

                        isLoading = false;

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ELogger.logWrite(TAG, "--onVolleyError--");
                        responseListener.onPushFailed();
                        isLoading = false;
                    }
                }
        ) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return jsonBody == null ? null : jsonBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", jsonBody, "utf-8");
                    return null;
                }
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                30*1000,
                3,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }



    public static boolean verifyStoragePermissions(Context context) {
        //检测是否有写的权限
        int permission = ActivityCompat.checkSelfPermission(context,
                "android.permission.WRITE_EXTERNAL_STORAGE");
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // 没有写的权限，去申请写的权限，会弹出对话框
            return false;
        } else {
            return true;
        }
    }

    private void writeClientId2SD(String clientId){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
            File saveFile = new File(sdCardDir , "akc");
            FileOutputStream outStream = null;
            try {
                outStream = new FileOutputStream(saveFile);
                outStream.write(clientId.getBytes());
                outStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }




}
