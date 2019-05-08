package sohero.com.testhlsvideo.us;

import android.app.Activity;
import android.content.Context;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;


/**
 * 项目名称:qhy.com.unlimitedswordutils
 * 类创建者:QHY.
 * 时间:2019/4/19
 * 类说明:运行时权限框架，内核RxPermission
 */

public class UsPermission {

    private static volatile UsPermission singleton;
    private Context context;
    private String permission;

    private UsPermission() {}

    public static UsPermission getInstance() {
        if (singleton == null) {
            synchronized (UsPermission.class) {
                if (singleton == null) {
                    singleton = new UsPermission();
                }
            }
        }
        return singleton;
    }

    public UsPermission setContext(Context context){
        this.context = context;
        return this;
    }

    public UsPermission setPermission(String persmission){
        this.permission = persmission;
        return this;
    }


    public  void getPermission(PermissionCallBack callBack) {
        RxPermissions permissions = new RxPermissions((Activity) context);
        permissions.request(this.permission).subscribe(callBack);
    }

    public static abstract class PermissionCallBack implements Consumer<Boolean> {

        @Override
        public void accept(Boolean b) throws Exception {
            if (b){
                success();
            }else {
                failed();
            }
        }


        public abstract void success();
        public abstract void failed();
    }
}
