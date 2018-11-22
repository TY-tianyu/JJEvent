package com.ccj.client.android.analytics.enums;

/**
 * 统计类别
 * Created by chenchangjun on 18/2/26.
 */
public enum DeviceIdType {


    IMEI("IMEI"),
    ANDROID_ID("ANDROID_ID"),
    NONE("NONE");


    private String typeName;

    DeviceIdType(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 根据类型的名称，返回类型的枚举实例。
     *
     * @param typeName 类型名称
     */
    public static DeviceIdType fromTypeName(int typeName) {
        for (DeviceIdType type : DeviceIdType.values()) {
            if (type.getTypeName().equals(typeName)) {
                return type;
            }
        }
        return null;
    }

    public String getTypeName() {
        return this.typeName;
    }


}
