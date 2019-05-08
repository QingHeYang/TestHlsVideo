package sohero.com.testhlsvideo.us;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名称:com.sohero.main.qhy.activity
 * 类创建者:QHY.
 * 时间:2017/11/24
 * 类说明:
 */

public abstract class UsRxJava<T> implements Observer<T> {

    /**
     * 适用于普通的耗时操作
     * 将订阅者的线程定在newThread
     * 将观察者的线程定在MainThread
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> thread_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 适用于网络请求
     * 将订阅者的线程定在ioThread
     * 将观察者的线程定在MainThread
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     *封装好的subscriber
     * @param t
     */
    public abstract void _onNext(T t);

    public abstract void _onError(String msg);

    public abstract void _onFinish(Disposable d);

    @Override
    public void onSubscribe(Disposable d) {
        _onFinish(d);
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        _onError(e.toString());
    }

    @Override
    public void onComplete() {

    }
}
