package sohero.com.testhlsvideo.video;

import android.os.Environment;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import sohero.com.testhlsvideo.MyApplication;
import sohero.com.testhlsvideo.nanohttp.http.IHTTPSession;
import sohero.com.testhlsvideo.nanohttp.http.NanoHTTPD;
import sohero.com.testhlsvideo.nanohttp.http.response.Response;
import sohero.com.testhlsvideo.nanohttp.http.response.Status;

import static android.content.ContentValues.TAG;
import static sohero.com.testhlsvideo.nanohttp.http.response.Response.newFixedLengthResponse;

/**
 * 项目名称:sohero.com.testhlsvideo.video
 * 类创建者:QHY.
 * 时间:2019/4/29
 * 类说明:开启httpServer，获取文件类
 */
public class HlsHttpServer extends NanoHTTPD {

    public HlsHttpServer(String hostname, int port) {
        super(hostname, port);
    }

    public HlsHttpServer(int port) {
        super(port);
    }

    @Override
    public Response serve(IHTTPSession session) {

        try {
            session.parseBody(new HashMap<String, String>());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResponseException e) {
            e.printStackTrace();
        }

        MyApplication app = new MyApplication();
        String path = Environment.getExternalStorageDirectory().getPath() + "/HlsTemp/";//获取文件夹地址
        Log.i(TAG, "uri: " + path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path + session.getUri());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            return Response.newFixedLengthResponse(Status.OK, "application/octet-stream", fis, fis.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFixedLengthResponse("404 error,no such file");
    }

}
