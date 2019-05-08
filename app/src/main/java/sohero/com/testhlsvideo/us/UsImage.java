package sohero.com.testhlsvideo.us;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 项目名称:qhy.com.unlimitedswordutils
 * 类创建者:QHY.
 * 时间:2019/4/19
 * 类说明:图片加载框架，内核为Glide，二次封装而成
 */

public class UsImage {

    private static volatile UsImage singleton;

    private Context context;
    private ImageView iv;
    private String path;
    private int placeholder;

    private UsImage() {}

    public static UsImage getInstance() {
        if (singleton == null) {
            synchronized (UsImage.class) {
                if (singleton == null) {
                    singleton = new UsImage();
                }
            }
        }
        return singleton;
    }

    public UsImage setContext(Context context){
        this.context = context;
        return this;
    }

    public UsImage load(String path){
        this.path = path;
        return this;
    }

    public UsImage placeHolder(int id){
        this.placeholder = id;
        return  this;
    }

    public void into(ImageView iv){
        Glide.with(context)
                .load(path)
                .placeholder(placeholder)
                .into(iv);
    }

    /**
     *
     * @param context 上下文
     * @param url 图片地址，可用gif
     * @param placeholder 占位图
     * @param iv imageview
     */
    public void setImage(Context context, String url, int placeholder, ImageView iv){
        Glide.with(context)
                .load(url)
                .placeholder(placeholder)
                .into(iv);
    }

}
