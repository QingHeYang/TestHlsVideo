package sohero.com.testhlsvideo;

import io.reactivex.Observable;

/**
 * 项目名称:sohero.com.testhlsvideo
 * 类创建者:QHY.
 * 时间:2019/5/7
 * 类说明:Main页面接口
 */
public interface IMainContract {
    interface IMainPersenter {

        /**
         * 获取权限
         */
        void getPermission();

        /**
         * 下载文件，自己封装的RxJava
         */
        void getHlsFile();

        /**
         * 刷新sb,用了RxLifeCycle,防止内存泄露,感觉快用了Rx全家桶了
         */
        void refreshProgress();

        /**
         * 删除文件
         */
        void deleteHlsFile();
    }

    interface IMainView {
        /**
         * 获取权限成功
         */
        void getPermissionSuccess();

        /**
         * 获取权限失败
         */
        void getPermissionFailed(String msg);

        /**
         * 下载文件成功
         */
        void getHlsFileSuccess();

        /**
         * 下载文件失败
         *
         * @param msg 错误信息
         */
        void getHlsFileFailed(String msg);

        /**
         * 刷新seekBar的progress，一秒一次
         */
        void refreshProgress();

        /**
         * 播放完成，删除文件成功
         */
        void deleteHlsFileSuccess();

        /**
         * 播放完成，删除文件失败
         */
        void deleteHlsFileFailed(String msg);
    }

    interface  IMainModel{

        /**
         * 下载文件,耗时操作
         */
        Observable<Boolean> getHlsFile();

        /**
         * 删除文件，耗时操作
         */
        Observable<Boolean> deleteHlsFile();
    }
}
