package sohero.com.testhlsvideo;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import sohero.com.testhlsvideo.us.USUtils;
import sohero.com.testhlsvideo.us.UsConst;
import sohero.com.testhlsvideo.us.UsPermission;
import sohero.com.testhlsvideo.us.UsRxJava;


/**
 * 项目名称:sohero.com.testhlsvideo
 * 类创建者:QHY.
 * 时间:2019/5/7
 * 类说明:Persenter
 */
public class MainPersenter implements IMainContract.IMainPersenter {

    private IMainContract.IMainView view;
    private IMainContract.IMainModel model;
    private MainActivity activity;


    public MainPersenter(IMainContract.IMainView view, MainActivity context) {
        this.view = view;
        this.activity = context;
        model = new MainModel(activity);
    }

    @Override
    public void getPermission() {
        USUtils.getInstance().usePermission()
                .setContext(activity)
                .setPermission(UsConst.Permission.WRITE_EXTERNAL_STORAGE)
                .getPermission(new UsPermission.PermissionCallBack() {
                    @Override
                    public void success() {
                        view.getPermissionSuccess();
                    }

                    @Override
                    public void failed() {
                        view.getPermissionFailed("please get permission for app");
                    }
                });
    }


    @Override
    public void getHlsFile() {
        model.getHlsFile()
                .subscribe(new UsRxJava<Boolean>() {
                    @Override
                    public void _onNext(Boolean s) {
                        view.getHlsFileSuccess();
                    }

                    @Override
                    public void _onError(String msg) {
                        view.getHlsFileFailed(msg);
                    }

                    @Override
                    public void _onFinish(Disposable d) {

                    }
                });
    }


    @Override
    public void refreshProgress() {
        Observable.interval(1, TimeUnit.SECONDS).compose(UsRxJava.<Long>thread_main()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                view.refreshProgress();
            }
        });
    }


    @Override
    public void deleteHlsFile() {
        model.deleteHlsFile()
                .subscribe(new UsRxJava<Boolean>() {
                    @Override
                    public void _onNext(Boolean aBoolean) {
                        view.deleteHlsFileSuccess();
                    }

                    @Override
                    public void _onError(String msg) {
                        view.deleteHlsFileFailed(msg);
                    }

                    @Override
                    public void _onFinish(Disposable d) {

                    }
                });

    }
}
