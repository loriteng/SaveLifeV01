package es.esy.mobilehost.android.savelife;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;


public class MenuActivity extends AppCompatActivity {

    private Context context;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private SeekBar bgControl, seVolControl;
    //音效播放物件
    private SoundPool soundPool;
    private int sound01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //音效檔準備
        soundPool = new SoundPool(4, AudioManager.STREAM_SYSTEM,0);
        sound01 = soundPool.load(this, R.raw.sound01, 0);
    }

    //建立OptionMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //設定OptionMenu
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_gallery:  //進入"動物圖鑑"
                soundPool.play(sound01,1 ,1, 0, 0, 1); //點擊按鈕出現音效
                gallery();
                break;
            case R.id.menu_volume: //進入"聲音調整"
                soundPool.play(sound01,1 ,1, 0, 0, 1); //點擊按鈕出現音效
                soundVolume1();
                break;
            case R.id.menu_copyright: //進入"資料出處"
                soundPool.play(sound01,1 ,1, 0, 0, 1); //點擊按鈕出現音效
                copyright();
                break;
            case R.id.menu_developer: //進入"製作團隊"
                soundPool.play(sound01,1 ,1, 0, 0, 1); //點擊按鈕出現音效
                developer();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //動物圖鑑
    private void gallery(){
        Intent intent = new Intent();
        intent.setClass(this, GalleryActivity.class);
        startActivity(intent);
    }

    //資料出處
    private void copyright() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.menu_copyright);
        builder.setMessage(R.string.copyright_info);
        builder.setPositiveButton(R.string.okay,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {}
                }
        );
        builder.show();
    }

    //製作團隊
    private void developer() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.menu_developer);
        builder.setMessage(R.string.developer_info);
        builder.setPositiveButton(R.string.okay,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {}
                }
        );
        builder.show();
    }

    //聲音調整
    // 音量設定 View
    public void soundVolume1() {

        // 自定Layout
        LayoutInflater inflater = getLayoutInflater();

        // 將 xml layout 轉換成視圖 View 物件
        View layout = inflater.inflate(R.layout.dialog,
                (ViewGroup) findViewById(R.id.menu_volume));

        // 自定View
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxBgVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curBgVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        int maxSeVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
        int curSeVolume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);

        // 控制聲音大小
        bgControl = (SeekBar) layout.findViewById(R.id.seekBar);
        bgControl.setMax(maxBgVolume);
        bgControl.setProgress(curBgVolume);
        bgControl.setOnSeekBarChangeListener(new MyVolControlOnSeekBarChangeListener());

        //控制音效大小
        seVolControl = (SeekBar) layout.findViewById(R.id.seekBar2);
        seVolControl.setMax(maxSeVolume);
        seVolControl.setProgress(curSeVolume);
        seVolControl.setOnSeekBarChangeListener(new MyVolControlOnSeekBarChangeListener());

//        SeekBar S1 = (SeekBar) findViewById(R.id.seekBar);
//        SeekBar S2 = (SeekBar) findViewById(R.id.seekBar2);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.button_text7);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setView(layout);
        builder.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }


    // 調整聲音大小

    private class MyVolControlOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            switch (seekBar.getId()){
                case R.id.seekBar :
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                    break;
                // 改變右聲道音量
                case R.id.seekBar2 :
                    audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, progress, 0);
                   float vol = progress/1.0F;
                  soundPool.setVolume(sound01, vol, vol);
                   soundPool.play(sound01, vol, vol, 0, 0, 1);
                    break;
            }
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }
}
