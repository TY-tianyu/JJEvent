package com.ccj.client.android.analytics;

/**
 * Created by chenchangjun on 18/2/9.
 */

 public interface OnNetResponseListener  {

    void onPushSuccess();
    void onPushEorr(int errorCode);
    void onPushFailed();



}
