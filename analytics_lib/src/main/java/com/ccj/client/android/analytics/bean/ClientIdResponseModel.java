package com.ccj.client.android.analytics.bean;


import com.ccj.client.android.analytics.net.gson.EGson;

/**
 * code : 0
 * success : true
 * message : 成功
 * data : {"clientId":"b1b8f85b51274a4499677407c13f86fc"}
 */
public class ClientIdResponseModel {

    private int code;
    private boolean success;
    private String message;
    /**
     * clientId : b1b8f85b51274a4499677407c13f86fc
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String clientId;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }
    }

    @Override
    public String toString() {
        return new EGson().toJson(this);
    }
}
