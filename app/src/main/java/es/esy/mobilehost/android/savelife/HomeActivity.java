package es.esy.mobilehost.android.savelife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import es.esy.mobilehost.android.savelife.PlayGame.BGM;

//遊戲首頁
public class HomeActivity extends MenuActivity {
    //遊戲音樂
    private BGM bgm;
    //xml的檔名
    public static final String KEY = "DataSet";

    //音效播放物件
    private SoundPool soundPool;
    private int sound01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(!bgm.getMediaPlayer().isPlaying()){
            bgm.getMediaPlayer().start();
        }

        //音效檔準備
        soundPool = new SoundPool(4, AudioManager.STREAM_SYSTEM,0);
        sound01 = soundPool.load(this, R.raw.sound01, 0);

        //帳號名稱設定
        TextView textView = (TextView) findViewById(R.id.showName);
        textView.setText("冒險家:" + getData("name"));

        //進入遊戲
        findViewById(R.id.BStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(sound01,1 ,1, 0, 0, 1); //點擊按鈕出現音效
                startActivity(new Intent().setClass(HomeActivity.this, DestActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //設定xml檔資料
    public String getData(String key) {
        SharedPreferences spref = getApplication().getSharedPreferences(KEY, Context.MODE_PRIVATE);
        String strValue = spref.getString(key, null);
        return strValue;
    }

    //離開遊戲畫面時呼叫timeset.pause()暫停時間
    @Override
    protected void onPause() {
        try {
            if(bgm == null) {
                //bgm = new BGM();
                bgm.getMediaPlayer().pause();
            }} catch (Exception e) {}
        super.onPause();
    }

    //回到遊戲畫面時呼叫timeset.pause()暫停時間
    @Override
    protected void onRestart() {
        try {
            if(bgm == null) {
                bgm.getMediaPlayer().start();
            }} catch (Exception e) {}
        super.onRestart();
    }
}