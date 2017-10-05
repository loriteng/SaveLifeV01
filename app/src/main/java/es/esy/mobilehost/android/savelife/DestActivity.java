package es.esy.mobilehost.android.savelife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import es.esy.mobilehost.android.savelife.PlayGame.BGM;

//難度設定頁面
public class DestActivity extends MenuActivity implements View.OnClickListener{
    private Context context;
    public static final String KEY = "DataSet";
    //遊戲音樂
    private BGM bgm;
    //音效播放物件
    private SoundPool soundPool;
    private int sound01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dest);
        context = this;
        if(!bgm.getMediaPlayer().isPlaying()){
            bgm.getMediaPlayer().start();
        }
        //音效檔準備
        soundPool = new SoundPool(4, AudioManager.STREAM_SYSTEM,0);
        sound01 = soundPool.load(this, R.raw.sound01, 0);

        ////帳號名稱設定
        TextView textView = (TextView) findViewById(R.id.dest_name);
        textView.setText("冒險家:" + getData("name"));


        findViewById(R.id.Bdest_lv1).setOnClickListener(this);
        findViewById(R.id.Bdest_lv2).setOnClickListener(this);
        findViewById(R.id.Bdest_lv3).setOnClickListener(this);
        findViewById(R.id.Bdest_time60).setOnClickListener(this);
        findViewById(R.id.Bdest_time45).setOnClickListener(this);
        findViewById(R.id.Bdest_time30).setOnClickListener(this);
        findViewById(R.id.DCBtton).setOnClickListener(this);
        findViewById(R.id.BHButton).setOnClickListener(this);
    }

    //所有按鈕事件
    @Override
    public void onClick(View view) {
        soundPool.play(sound01,1 ,1, 0, 0, 1); //點擊按鈕出現音效
        TextView tsarySetView = (TextView) findViewById(R.id.dest_lvset);
        TextView timeSetView = (TextView) findViewById(R.id.dest_Time);
        Button lv1 =(Button)findViewById(R.id.Bdest_lv1);
        Button lv2 =(Button)findViewById(R.id.Bdest_lv2);
        Button lv3 =(Button)findViewById(R.id.Bdest_lv3);
        Button time60 =(Button)findViewById(R.id.Bdest_time60);
        Button time45 =(Button)findViewById(R.id.Bdest_time45);
        Button time30 =(Button)findViewById(R.id.Bdest_time30);
        switch(view.getId()) {
            //簡單難度設定
            case R.id.Bdest_lv1:
                setRC(5,4);
                lv1.setTextColor(Color.RED);
                lv2.setTextColor(getResources().getColor(R.color.color_Dest));
                lv3.setTextColor(getResources().getColor(R.color.color_Dest));
                tsarySetView.setText("20"+"格");
                break;
            //普通難度設定
            case R.id.Bdest_lv2:
                setRC(6,4);
                lv2.setTextColor(Color.RED);
                lv1.setTextColor(getResources().getColor(R.color.color_Dest));
                lv3.setTextColor(getResources().getColor(R.color.color_Dest));
                tsarySetView.setText("24"+"格");
                break;
            //困難難度設定
            case R.id.Bdest_lv3:
                setRC(7,4);
                lv3.setTextColor(Color.RED);
                lv2.setTextColor(getResources().getColor(R.color.color_Dest));
                lv1.setTextColor(getResources().getColor(R.color.color_Dest));
                tsarySetView.setText("28"+"格");
                break;
            //設定時間60秒設定
            case R.id.Bdest_time60:
                setIntData("SaveTime", 60);
                time60.setTextColor(Color.RED);
                time45.setTextColor(getResources().getColor(R.color.color_Dest));
                time30.setTextColor(getResources().getColor(R.color.color_Dest));
                timeSetView.setText("60" + "秒");
                break;
            //設定時間45秒設定
            case R.id.Bdest_time45:
                setIntData("SaveTime", 45);
                time45.setTextColor(Color.RED);
                time60.setTextColor(getResources().getColor(R.color.color_Dest));
                time30.setTextColor(getResources().getColor(R.color.color_Dest));
                timeSetView.setText("45" + "秒");
                break;
            //設定時間30秒設定
            case R.id.Bdest_time30:
                setIntData("SaveTime", 30);
                time30.setTextColor(Color.RED);
                time45.setTextColor(getResources().getColor(R.color.color_Dest));
                time60.setTextColor(getResources().getColor(R.color.color_Dest));
                timeSetView.setText("30" + "秒");
                break;
            //難度設定確認事件
            case R.id.DCBtton:
                if(!tsarySetView.getText().equals("牌數")||!timeSetView.getText().equals("時間")){

                    if (!tsarySetView.getText().equals("牌數")||timeSetView.getText().equals("時間")){

                        if(!timeSetView.getText().equals("時間")){
                            startActivity(new Intent().setClass(DestActivity.this, PlayActivity.class));
                            finish();
                        }else {
                            Toast.makeText(view.getContext(), "請選擇時間", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(view.getContext(), "請選擇難度", Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(view.getContext(), "請選擇難度和時間", Toast.LENGTH_LONG).show();
                }
                break;
            //返回首頁
            case R.id.BHButton:
                startActivity(new Intent().setClass(DestActivity.this, HomeActivity.class));
                finish();
                break;
        }
    }


    //設定卡牌數量至XML檔
    public void setRC(int rowCount,int columeCount){
        setIntData("SaveLsRow", rowCount);
        setIntData("SaveLsColume", columeCount);
    }
    //設定資料至XML檔
    public void setIntData(String key, int value) {
        SharedPreferences spref = getApplication().getSharedPreferences(KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor PE = spref.edit();
        PE.putInt(key, value);
        PE.commit();
    }
    //從XML檔讀取資料
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
