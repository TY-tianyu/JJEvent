package com.ccj.client.android.analytics.bean;


/**
 * clientId : clientId，必须
 * spm : 事件编号，按照appId.页面.模块.子模块1.子模块2定义，如果没有5级，则后面的部分省略，例如只有三级，格式为1.10.123
 * refSpm : 前一个SPM点，例如一个按钮的spm是1.10.123，点击以后跳到页面1.11，对于1.11的页面事件来说，refSpm就是1.10.123
 * url : 当前页面url，App如果没有统一的URL Scheme定义可以暂时不传
 * clientTs : 事件发生的客户端时间，精确到毫秒的unix时间戳
 * logType : 枚举：user/system，用户触发/系统自动
 * actionType : 枚举：click/pageview/launch/quit/active/deactive/expose/scroll/fatal/error
 * pvid : 当前页面浏览标识，页面创建时生成，页面压栈出栈时不变
 * tab : tab名，适用于页面中有多个tab时标识处于哪个tab
 * mi : 元素偏移量，如2.3
 * mainProperties : {"key":"value"}
 * currentProperties : {"key":"value"}
 * lon : 121.123456
 * lat : 31.987654
 * country : 国家
 * province : 省
 * city : 城市
 * orientation : 0
 * download : 华为应用市场/AppStore
 * utm : 仅限于android打了带utm的渠道包时，否则不传
 * osVersion : 操作系统版本
 * deviceIdType : 设备标识的类型，IDFA/ANDROID_ID/IMEI/NONE，没有则传NONE
 * deviceId : 与deviceIdType一致的设备标识
 * carrier : 运营商
 * network : WIFI,4G,3G
 * platform : ios/android/js/wxma
 * appVersion : 1.0.0
 * sdkVersion : sdk版本，如1.0.0
 */
public class AkcEventModel {

    private String clientId;
    private String spm;
    private String refSpm;
    private String url;
    private long clientTs;
    private String logType;
    private String actionType;
    private String pvid;
    private String tab;
    private String mi;
    /**
     * key : value
     */

    private MainPropertiesBean mainProperties;
    /**
     * key : value
     */

    private CurrentPropertiesBean currentProperties;
    private double lon;
    private double lat;
    private String country;
    private String province;
    private String city;
    private int orientation;
    private String download;
    private String utm;
    private String osVersion;
    private String deviceIdType;
    private String deviceId;
    private String carrier;
    private String network;
    private String platform;
    private String appVersion;
    private String sdkVersion;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSpm() {
        return spm;
    }

    public void setSpm(String spm) {
        this.spm = spm;
    }

    public String getRefSpm() {
        return refSpm;
    }

    public void setRefSpm(String refSpm) {
        this.refSpm = refSpm;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getClientTs() {
        return clientTs;
    }

    public void setClientTs(long clientTs) {
        this.clientTs = clientTs;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getPvid() {
        return pvid;
    }

    public void setPvid(String pvid) {
        this.pvid = pvid;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getMi() {
        return mi;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public MainPropertiesBean getMainProperties() {
        return mainProperties;
    }

    public void setMainProperties(MainPropertiesBean mainProperties) {
        this.mainProperties = mainProperties;
    }

    public CurrentPropertiesBean getCurrentProperties() {
        return currentProperties;
    }

    public void setCurrentProperties(CurrentPropertiesBean currentProperties) {
        this.currentProperties = currentProperties;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getUtm() {
        return utm;
    }

    public void setUtm(String utm) {
        this.utm = utm;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDeviceIdType() {
        return deviceIdType;
    }

    public void setDeviceIdType(String deviceIdType) {
        this.deviceIdType = deviceIdType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public static class MainPropertiesBean {
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    public static class CurrentPropertiesBean {
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}