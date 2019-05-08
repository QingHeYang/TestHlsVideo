package sohero.com.testhlsvideo.us;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

/**
 * 项目名称:qhy.com.unlimitedswordutils.video
 * 类创建者:QHY.
 * 时间:2019/4/19
 * 类说明:
 */

public class UsObservable extends Observable {

    private UsSubcribe subcribe;


    private static volatile UsObservable singleton;

    private UsObservable() {}



    public static UsObservable getInstance() {
        if (singleton == null) {
            synchronized (UsObservable.class) {
                if (singleton == null) {
                    singleton = new UsObservable();
                }
            }
        }
        return singleton;
    }



    public static abstract class UsSubcribe<T> implements ObservableOnSubscribe<T> {

        @Override
        public void subscribe(ObservableEmitter<T> e) throws Exception {
            doSomeThings(e);
        }

        public abstract void doSomeThings(ObservableEmitter<T> e);

    }

    @Override
    protected void subscribeActual(Observer observer) {

    }


}
