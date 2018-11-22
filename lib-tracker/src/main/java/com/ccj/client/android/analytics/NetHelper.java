package com.ccj.client.android.analytics;

import android.content.Context;

import com.ccj.client.android.analytics.bean.AkcEventListModel;
import com.ccj.client.android.analytics.bean.AkcEventModel;
import com.ccj.client.android.analytics.bean.ClientIdResponseModel;
import com.ccj.client.android.analytics.bean.EventBean;
import com.ccj.client.android.analytics.net.core.AuthFailureError;
import com.ccj.client.android.analytics.net.core.Request;
import com.ccj.client.android.analytics.net.core.RequestQueue;
import com.ccj.client.android.analytics.net.core.Response;
import com.ccj.client.android.analytics.net.core.Tools.EVolley;
import com.ccj.client.android.analytics.net.core.VolleyError;
import com.ccj.client.android.analytics.net.core.VolleyLog;
import com.ccj.client.android.analytics.net.gson.EGson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ccj.client.android.analytics.Constant.TAG;

/**
 * 网络模块, 网络不好,需要缓存到本地.
 * Created by chenchangjun on 18/2/8.
 */

public class NetHelper {

    private static NetHelper NetHelper;
    private static boolean isLoading = false;
    private static OnNetResponseListener responseListener;
    private RequestQueue queue;
    private Context context;

    private NetHelper(Context context, OnNetResponseListener responseListener) {
        NetHelper.responseListener = responseListener;
        queue = EVolley.newRequestQueue(context);
        this.context = context;

    }

    public static NetHelper create(Context context, OnNetResponseListener responseListener) {

        if (NetHelper == null) {
            synchronized (NetHelper.class) {//双重检查
                if (NetHelper == null) {
                    NetHelper = new NetHelper(context, responseListener);

                }
            }
        }
        return NetHelper;
    }

    public static boolean getIsLoading() {
        return isLoading;
    }

    public void sendEvent(String style, List<EventBean> list) {

        switch (style) {

            case Constant.EVENT_TYPE_PV:
                loadData(list);
                break;
            case Constant.EVENT_TYPE_EVENT:
                loadData(list);
                break;
            case Constant.EVENT_TYPE_EXPOSE:
                loadData(list);
                break;
            default:
                loadData(list);

                break;
        }

    }

    public void loadData(List<EventBean> list) {


        isLoading = true;


        Map headers = new HashMap();
        headers.put("Content-Type", "application/json");

        List akcEventLst = new ArrayList();


        for (EventBean eventBean : list)
        {
            akcEventLst.add(new EGson().fromJson(eventBean.getEa() , AkcEventModel.class));
        }

        AkcEventListModel akcEventListModel = new AkcEventListModel();
        akcEventListModel.setEventList(akcEventLst);

        final String jsonBody = new EGson().toJson(akcEventListModel);


        Logger.logWrite(TAG, "push eventList-->" + jsonBody);


        GsonRequest request = new GsonRequest(Request.Method.POST
                , Constant.COLLECT_URL, ClientIdResponseModel.class, headers, null,
                new Response.Listener<ClientIdResponseModel>() {
                    @Override
                    public void onResponse(ClientIdResponseModel response) {
                        int code = response.getCode();
                        String msg = "";
                        Logger.logWrite(TAG, response.toString());

                        if (response.isSuccess()) {
                            responseListener.onPushSuccess();
                            Logger.logWrite(TAG, "--onPushSuccess--");

                        } else {
                            responseListener.onPushEorr(code);
                            Logger.logWrite(TAG, "--onPushError--");

                        }

                        isLoading = false;

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Logger.logWrite(TAG, "--onVolleyError--");
                        responseListener.onPushFailed();
                        isLoading = false;
                    }
                }
        ){
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



        queue.add(request);


    }

}
