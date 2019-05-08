package sohero.com.testhlsvideo;

import android.app.Application;
import android.content.Context;

/**
 * 项目名称:sohero.com.testhlsvideo
 * 类创建者:QHY.
 * 时间:2019/4/29
 * 类说明:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Context getContext(){
        return this;
    }
}
