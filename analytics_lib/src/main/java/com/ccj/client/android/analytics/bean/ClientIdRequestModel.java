package com.ccj.client.android.analytics.bean;


import com.ccj.client.android.analytics.EConstant;
import com.ccj.client.android.analytics.net.gson.EGson;

/**
 * appId : com.aikucun.apptest
 * appVersion : 2.4.6
 * deviceBrand : iPhone
 * deviceId : 2974870C-BE7F-4240-B96D-5CE80479F987
 * deviceIdType : IDFA
 * deviceModel : iPhone
 * deviceType : iPhone (6)
 * download : AppStore
 * osType : iOS
 * osVersion : 10.3.1
 * platform : iOS
 * screenX : 414
 * screenY : 736
 * sdkVersion : 0.1
 */
public class ClientIdRequestModel {

    private String appId;
    private String appVersion;
    private String deviceBrand;
    private String deviceId;
    private String deviceIdType;
    private String deviceModel;
    private String deviceType;
    private String download;
    private String osType;
    private String osVersion;
    private String platform;
    private int screenX;
    private int screenY;
    private String sdkVersion;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceIdType() {
        return deviceIdType;
    }

    public void setDeviceIdType(String deviceIdType) {
        this.deviceIdType = deviceIdType;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getScreenX() {
        return screenX;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    @Override
    public String toString() {
        return new EGson().toJson(this);
    }
}
