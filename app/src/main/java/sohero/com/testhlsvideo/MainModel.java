package sohero.com.testhlsvideo;

import android.os.Environment;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import sohero.com.testhlsvideo.us.USUtils;
import sohero.com.testhlsvideo.us.UsObservable;
import sohero.com.testhlsvideo.us.UsRxJava;

/**
 * 项目名称:sohero.com.testhlsvideo
 * 类创建者:QHY.
 * 时间:2019/5/7
 * 类说明:model
 */
public class MainModel implements IMainContract.IMainModel{

    private MainActivity activity;

    public MainModel(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public Observable<Boolean> getHlsFile() {
        return  USUtils.getInstance().useRx().create(new UsObservable.UsSubcribe<Boolean>() {
            @Override
            public void doSomeThings(ObservableEmitter<Boolean> e) {
                boolean result = HttpFileUtils.downLoadFile(ContentValues.NET_PATH+ContentValues.FILE_NAME, ContentValues.FILE_NAME);
                if (result) {
                    e.onNext(true);
                } else {
                    e.onError(new Throwable("download failed"));
                }
            }
        }).compose(UsRxJava.<Boolean>io_main());
    }

    @Override
    public Observable<Boolean> deleteHlsFile() {
        return USUtils.getInstance().useRx().create(new UsObservable.UsSubcribe<Boolean>() {
            @Override
            public void doSomeThings(ObservableEmitter<Boolean> e) {
                File file = new File(Environment.getExternalStorageDirectory() + "/HlsTemp/" + ContentValues.FILE_NAME);
                if (file.exists() && file.isFile()) {
                    file.delete();
                    e.onNext(true);
                } else {
                    e.onError(new Exception("no such file"));
                }
            }
        }).compose(UsRxJava.<Boolean>thread_main());
    }
}
