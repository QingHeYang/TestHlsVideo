package sohero.com.testhlsvideo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.Toast;

import sohero.com.testhlsvideo.video.media.UsIjkVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, IMainContract.IMainView, IMediaPlayer.OnPreparedListener, IMediaPlayer.OnCompletionListener {

    UsIjkVideoView ijkVideoView;//IjkPlayer,我封装了一层，用法一样
    SeekBar sb;//seekBar

    private IMainContract.IMainPersenter persenter; //persenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        persenter = new MainPersenter(this, this);
        persenter.getPermission();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        ijkVideoView =  findViewById(R.id.ijk_vv);
        sb =  findViewById(R.id.main_sb);
        sb.setOnSeekBarChangeListener(this);
    }

    @Override
    public void getPermissionSuccess() {
        startService(new Intent(MainActivity.this, MyServer.class));
        //开始下载m3u8文件
        persenter.getHlsFile();
    }

    @Override
    public void getPermissionFailed(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getHlsFileSuccess() {
        Toast.makeText(this, "下载完成，播放视频", Toast.LENGTH_SHORT).show();
        startPlayVideo();
    }

    @Override
    public void getHlsFileFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshProgress() {
        sb.setProgress(ijkVideoView.getCurrentPosition());
    }

    @Override
    public void deleteHlsFileSuccess() {
        Toast.makeText(this, "播放完成，缓存文件删除成功", Toast.LENGTH_SHORT).show();
        sb.setMax(0);
        sb.setProgress(0);
        ijkVideoView.release(true);
    }

    @Override
    public void deleteHlsFileFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * 开始播放视频,开始刷新进度
     */
    private void startPlayVideo() {
        ijkVideoView.setVideoPath(ContentValues.LOCAL_PATH + ContentValues.FILE_NAME);
        ijkVideoView.setOnPreparedListener(this);
        ijkVideoView.setOnCompletionListener(this);
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        ijkVideoView.start();
        sb.setMax((int) iMediaPlayer.getDuration());
        persenter.refreshProgress();
    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        persenter.deleteHlsFile();
    }

    /**
     * seekBar相关
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        ijkVideoView.seekTo(seekBar.getProgress());
    }


}
