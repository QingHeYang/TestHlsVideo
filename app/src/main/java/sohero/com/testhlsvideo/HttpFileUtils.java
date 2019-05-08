package sohero.com.testhlsvideo;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 项目名称:sohero.com.testhlsvideo
 * 类创建者:QHY.
 * 时间:2019/4/29
 * 类说明:下载m3u8文件
 */
public class HttpFileUtils {
    public static boolean downLoadFile(String httpUrl, String name) {
        String HLS_PATH = Environment.getExternalStorageDirectory().getPath() + "/HlsTemp/";
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(3000);
            conn.connect();

            //读到流中
            InputStream is = conn.getInputStream();
            byte buf[] = new byte[1024];
            int len = 0;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = is.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }

            //新建文件
            File file = new File(HLS_PATH);
            if (!file.exists()) {
                file.mkdir();
            }
            File m3u8file = new File(HLS_PATH, name);

            //写到流中
            FileOutputStream fos = new FileOutputStream(m3u8file);
            fos.write(bos.toByteArray());

            //关闭流
            fos.close();
            bos.close();
            is.close();
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return writeFile(HLS_PATH + name);

    }

    private static boolean writeFile(String path) {
        File file = new File(path);
        FileReader reader = null;
        try {
            reader = new FileReader(file);

            BufferedReader bReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String s = "";
            int line = 0;
            while ((s = bReader.readLine()) != null) {
                if (line >= 7 && line % 2 == 1) {
                    sb.append(ContentValues.NET_PATH  + s + "\n");
                } else {
                    sb.append(s + "\n");
                }
                line += 1;
            }
            String str = sb.toString();
            Log.v("Tag", str);
            String[] strs = str.split("\"");
            str = strs[0] + "\"" + ContentValues.KEY_PATH + "\"" + strs[2];
            FileWriter writer = new FileWriter(file);
            writer.write(str);
            writer.flush();
            writer.close();
            bReader.close();
            reader.close();
            //handler.sendEmptyMessage(0x002);
            Log.v("Tag", str);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
