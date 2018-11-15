package com.ccj.client.android.analytics.enums;

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



