package sohero.com.testhlsvideo.us;

import com.google.gson.Gson;

/**
 * 项目名称:qhy.com.unlimitedswordutils
 * 类创建者:QHY.
 * 时间:2019/4/19
 * 类说明:
 */

public class UsGson {

    public  <T> T getBean(Object json, Class<?> T) {
        Gson gson = new Gson();
        Object object = null;
        try {
            object = gson.fromJson(json.toString(), T);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gson = null;
        return ((T) object);
    }
}
