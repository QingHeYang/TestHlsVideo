package sohero.com.testhlsvideo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.IOException;

import sohero.com.testhlsvideo.video.HlsHttpServer;

public class MyServer extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        HlsHttpServer myServer = new HlsHttpServer(8080);
        try {
            // 开启HTTP服务
            myServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
