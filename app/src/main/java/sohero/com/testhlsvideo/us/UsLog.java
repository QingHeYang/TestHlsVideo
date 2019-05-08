package sohero.com.testhlsvideo.us;

import android.util.Log;

/**
 * 项目名称:com.sohero.main.carknowledgehall.utils
 * 类创建者:QHY.
 * 时间:2017/12/21
 * 类说明:封装log
 */

public class UsLog {

    public static boolean IS_LOG = true;

    private static boolean IS_LOG_PAGE = true;

    public static final boolean JNUIT = true;

    private static boolean isLog() {
        return IS_LOG;
    }

    private static boolean isLogPage() {
        return IS_LOG_PAGE;
    }

    /**
     * 这个是单元测试用的Log;
     *
     * @param TAG
     * @param msg
     */
    public static void j(String TAG, Object msg) {
        if (isLog()&&isLogPage()) {
            String EndTag = getEndTag(TAG);
            if (msg == null) {
                msg = new StringBuffer("NULL_Object");
            }
            msg = msg.toString();
            System.out.println();
            System.out.println("------------   " + TAG + "   ------------");
            System.out.println("Msg is :");
            System.out.println("        " + msg);
            System.out.println("--关闭LOG-->"+"=======关闭此LOG，请移步===》"+TAG+".class");
            System.out.println("------------   " + EndTag + "   ------------");
        } else {
            System.out.println();
            System.out.println("------------   " + "没有开LOG" + "   ------------");
        }

    }

    public static void v(String TAG, Object msg) {
        if (isLog()) {
            String EndTag = getEndTag(TAG);
            if (msg == null) {
                msg = new StringBuffer("NULL_Object");
            }
            msg = msg.toString();
            Log.v("  ", "  ");
            Log.v(TAG, "------------   " + TAG + "   ------------");
            Log.v(TAG, "Msg is :");
            Log.v(TAG, "        " + msg);
            Log.i("--关闭LOG--","=======关闭此LOG，请移步===》"+TAG+".class");
            Log.v(TAG, "------------   " + EndTag + "   ------------");
        } else {
            Log.v("", "");
            Log.v(TAG, "------------   " + "没有开LOG" + "   ------------");
        }

    }

    public static void v(String TAG, Object msg, boolean isJunit) {
        if (isJunit) {
            j(TAG, msg);
        } else {
            v(TAG, msg);
        }
    }

    public static void d(String TAG, Object msg) {
        if (isLog()) {
            String EndTag = getEndTag(TAG);
            if (msg == null) {
                msg = new StringBuffer("NULL_Object");
            }
            msg = msg.toString();
            Log.d("", "");
            Log.d(TAG, "------------   " + TAG + "   ------------");
            Log.d(TAG, "Msg is :");
            Log.d(TAG, "        " + msg);
            Log.i("--关闭LOG--","=======关闭此LOG，请移步===》"+TAG+".class");
            Log.d(TAG, "------------   " + EndTag + "   ------------");
        } else {
            Log.d("", "");
            Log.d(TAG, "------------   " + "没有开LOG" + "   ------------");
        }
    }

    public static void d(String TAG, Object msg, boolean isJunit) {
        if (isJunit) {
            j(TAG, msg);
        } else {
            d(TAG, msg);
        }
    }

    public static void e(String TAG, Object msg) {
        if (isLog()) {
            String EndTag = getEndTag(TAG);
            if (msg == null) {
                msg = new StringBuffer("NULL_Object");
            }
            msg = msg.toString();
            Log.e("", "");
            Log.e(TAG, "------------   " + TAG + "   ------------");
            Log.e(TAG, "Msg is :");
            Log.e(TAG, "        " + msg);
            Log.i("--关闭LOG--","=======关闭此LOG，请移步===》"+TAG+".class");
            Log.e(TAG, "------------   " + EndTag + "   ------------");
        } else {
            Log.e(" ", " ");
            Log.e(TAG, "------------   " + "没有开LOG" + "   ------------");
        }
    }

    public static void e(String TAG, Object msg, boolean isJunit) {
        if (isJunit) {
            j(TAG, msg);
        } else {
            e(TAG, msg);
        }
    }

    public static void i(String TAG, Object msg) {
        if (isLog()) {
            String EndTag = getEndTag(TAG);
            if (msg == null) {
                msg = new StringBuffer("NULL_Object");
            }
            msg = msg.toString();
            Log.i("", "");
            Log.i(TAG, "------------   " + TAG + "   ------------");
            Log.i(TAG, "Msg is :");
            Log.i(TAG, "        " + msg);
            Log.v("--关闭LOG--","=======关闭此LOG，请移步===》"+TAG+".class");
            Log.i(TAG, "------------   " + EndTag + "   ------------");
        } else {
            Log.i("", "");
            Log.i(TAG, "------------   " + "没有开LOG" + "   ------------");
        }
    }

    public static void i(String TAG, Object msg, boolean isJunit) {
        if (isJunit) {
            j(TAG, msg);
        } else {
            i(TAG, msg);
        }
    }

    public static void w(String TAG, Object msg) {
        if (isLog()) {
            String EndTag = getEndTag(TAG);
            if (msg == null) {
                msg = new StringBuffer("NULL_Object");
            }
            msg = msg.toString();
            Log.w("", "");
            Log.w(TAG, "------------   " + TAG + "   ------------");
            Log.w(TAG, "Msg is :");
            Log.w(TAG, "        " + msg);
            Log.i("--关闭LOG--","=======关闭此LOG，请移步===》"+TAG+".class");
            Log.w(TAG, "------------   " + EndTag + "   ------------");
        } else {
            Log.w("", "");
            Log.w(TAG, "------------   " + "没有开LOG" + "   ------------");
        }
    }

    public static void w(String TAG, Object msg, boolean isJunit) {
        if (isJunit) {
            j(TAG, msg);
        } else {
            w(TAG, msg);
        }
    }

    private static String getEndTag(String TAG) {
        String END = "END";
        int tagLenth = TAG.length() - 3;
        if (tagLenth<=0){
            return END;
        }
        int lLen, rLen;
        String leftSpace = "", rightSpace = "";
        if (tagLenth % 2 == 0) {
            lLen = rLen = tagLenth / 2;
        } else {
            lLen = tagLenth / 2 + 1;
            rLen = tagLenth / 2;
        }

        for (int i = 0; i < lLen; i++) {
            leftSpace += " ";
        }

        for (int i = 0; i < rLen; i++) {
            rightSpace += " ";
        }
        return leftSpace + END + rightSpace;
    }
}
