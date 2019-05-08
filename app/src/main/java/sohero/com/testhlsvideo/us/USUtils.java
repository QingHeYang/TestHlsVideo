package sohero.com.testhlsvideo.us;


/**
 * 项目名称:qhy.com.unlimitedswordutils
 * 类创建者:QHY.
 * 时间:2019/4/19
 * 类说明:
 */

public class USUtils {
    private static volatile USUtils singleton;

    private USUtils() {}

    public static USUtils getInstance() {
        if (singleton == null) {
            synchronized (USUtils.class) {
                if (singleton == null) {
                    singleton = new USUtils();
                }
            }
        }
        return singleton;
    }

    public UsImage useImage(){
        return UsImage.getInstance();
    }

    public UsPermission usePermission(){
        return UsPermission.getInstance();
    }

    public UsObservable useRx(){
        return UsObservable.getInstance();
    }

    public UsGson useGson(){
        return new UsGson();
    }
}
