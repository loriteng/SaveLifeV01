package es.esy.mobilehost.android.savelife;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import es.esy.mobilehost.android.savelife.Data.UserData;
import es.esy.mobilehost.android.savelife.Data.UserDataDAO;
import es.esy.mobilehost.android.savelife.PlayGame.BGM;

//遊戲登入畫面
public class LoginActivity extends Activity implements View.OnClickListener{
    //玩家資料物件
    private UserDataDAO userDataDAO;
    //xml檔名稱
    private static final String KEY = "DataSet";
    //遊戲音樂
    private BGM bgm;

    //音效播放物件
    private SoundPool soundPool;
    private int sound01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            if(bgm.getMediaPlayer() == null) {
                bgm.setMediaPlayer(MediaPlayer.create(this, R.raw.try_everything));
                bgm.getMediaPlayer().start();
            }} catch (Exception e) {}
        if(!bgm.getMediaPlayer().isPlaying()){
            bgm.getMediaPlayer().start();
        }

        //音效檔準備
        soundPool = new SoundPool(4, AudioManager.STREAM_SYSTEM,0);
        sound01 = soundPool.load(this, R.raw.sound01, 0);

        // 建立資料存取物件
        userDataDAO = new UserDataDAO(this);

        findViewById(R.id.newUser).setOnClickListener(this);
        findViewById(R.id.Benter).setOnClickListener(this);
        findViewById(R.id.BdeleteData).setOnClickListener(this);
        userDataDAO.rgetnameData((RadioButton)findViewById(R.id.rBUser01), 0);
        userDataDAO.rgetnameData((RadioButton)findViewById(R.id.rBUser02), 1);
        userDataDAO.rgetnameData((RadioButton)findViewById(R.id.rBUser03), 2);
        setIDData();
    }
    //按鈕判斷事件
    public void onClick(View view) {
        soundPool.play(sound01,1 ,1, 0, 0, 1); //點擊按鈕出現音效
        RadioGroup Iwant = (RadioGroup) findViewById(R.id.radioGroup);
        UserDataDAO userDataDAO = new UserDataDAO(LoginActivity.this);
        Cursor mCursor = userDataDAO.getAllCursor();
        switch(view.getId()) {
            //新增帳號鈕的判斷事件
            case R.id.newUser:
                if (!mCursor.moveToPosition(2)){
                    startActivity(new Intent().setClass(LoginActivity.this, NewUserActivity.class));
                    //finish();
                }else{
                    Toast.makeText(LoginActivity.this,"帳號已滿",Toast.LENGTH_SHORT).show();
                }
                break;
            //開始遊戲鈕的判斷事件
            case R.id.Benter:
                //判斷選擇的項目資源 ID是不是所選擇的該項ID
                if (Iwant.getCheckedRadioButtonId() == R.id.rBUser01||
                        Iwant.getCheckedRadioButtonId() == R.id.rBUser02||
                        Iwant.getCheckedRadioButtonId() == R.id.rBUser03){
                    switch (Iwant.getCheckedRadioButtonId()){
                        case R.id.rBUser01:
                            if (mCursor.moveToPosition(0)){
                                setUserData(0);
                                setIntData("id",Integer.valueOf(getData("_id0")));
                                startActivity(new Intent().setClass(LoginActivity.this, HomeActivity.class));
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this,"此帳戶未創建",Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case R.id.rBUser02:
                            if (mCursor.moveToPosition(1)){
                                setUserData(1);
                                setIntData("id",Integer.valueOf(getData("_id1")));
                                startActivity(new Intent().setClass(LoginActivity.this, HomeActivity.class));
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this,"此帳戶未創建",Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case R.id.rBUser03:
                            if (mCursor.moveToPosition(2)) {
                                setUserData(2);
                                setIntData("id",Integer.valueOf(getData("_id2")));
                                startActivity(new Intent().setClass(LoginActivity.this, HomeActivity.class));
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this,"此帳戶未創建",Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                }else {
                    Toast.makeText(LoginActivity.this,"請選擇遊戲帳號",Toast.LENGTH_SHORT).show();
                }
                break;
            //刪除帳號鈕的判斷事件
            case R.id.BdeleteData:
                //判斷選擇的項目資源 ID是不是所選擇的該項ID
                if (Iwant.getCheckedRadioButtonId() == R.id.rBUser01||
                        Iwant.getCheckedRadioButtonId() == R.id.rBUser02||
                        Iwant.getCheckedRadioButtonId() == R.id.rBUser03){
                    switch (Iwant.getCheckedRadioButtonId()){
                        case R.id.rBUser01:
                            if (mCursor.moveToPosition(0)){
                                deleteCheck(Integer.valueOf(getData("_id0")));
                            }else {
                                Toast.makeText(LoginActivity.this,"此帳戶未創建",Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case R.id.rBUser02:
                            if (mCursor.moveToPosition(1)){
                                deleteCheck(Integer.valueOf(getData("_id1")));
                            }else{
                                Toast.makeText(LoginActivity.this,"此帳戶未創建",Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case R.id.rBUser03:
                            if (mCursor.moveToPosition(2)) {
                                deleteCheck(Integer.valueOf(getData("_id2")));
                            }else {
                                Toast.makeText(LoginActivity.this,"此帳戶未創建",Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                }else {
                    Toast.makeText(LoginActivity.this,"請選擇遊戲帳號",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //刪除資料的確認
    public void  deleteCheck(final int id){
        new AlertDialog.Builder(LoginActivity.this)
                .setTitle("確認視窗")
                .setMessage("確定要刪除帳號嗎?")
                .setPositiveButton("確定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                userDataDAO.delete(id);
                                startActivity(new Intent().setClass(LoginActivity.this, LoginActivity.class));
                                finish();
                            }
                        })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        }).show();
    }

    //儲存選擇的帳號個別資料
    public void setUserData(int i) {
        //DB資料的內容讀取 :
        UserDataDAO userDataDAO = new UserDataDAO(this);
        Cursor mCursor = userDataDAO.getAllCursor();

        try {
            if (!mCursor.moveToPosition(i)) {
            } else {
                setIntData("Pid",i);
                setStringData("name",mCursor.getString(mCursor.getColumnIndex("name")));
                setStringData("a01",mCursor.getString(mCursor.getColumnIndex("a01")));
                setStringData("a02",mCursor.getString(mCursor.getColumnIndex("a02")));
                setStringData("a03",mCursor.getString(mCursor.getColumnIndex("a03")));
                setStringData("a04",mCursor.getString(mCursor.getColumnIndex("a04")));
                setStringData("a05",mCursor.getString(mCursor.getColumnIndex("a05")));
                setStringData("a06",mCursor.getString(mCursor.getColumnIndex("a06")));
                setStringData("a07",mCursor.getString(mCursor.getColumnIndex("a07")));
                setStringData("a08",mCursor.getString(mCursor.getColumnIndex("a08")));
                setStringData("a09",mCursor.getString(mCursor.getColumnIndex("a09")));
                setStringData("a10",mCursor.getString(mCursor.getColumnIndex("a10")));
                setStringData("a11",mCursor.getString(mCursor.getColumnIndex("a11")));
                setStringData("a12",mCursor.getString(mCursor.getColumnIndex("a12")));
                setStringData("a13",mCursor.getString(mCursor.getColumnIndex("a13")));
                setStringData("a14",mCursor.getString(mCursor.getColumnIndex("a14")));
                setStringData("a15",mCursor.getString(mCursor.getColumnIndex("a15")));
                setStringData("a16",mCursor.getString(mCursor.getColumnIndex("a16")));
                setStringData("a17",mCursor.getString(mCursor.getColumnIndex("a17")));
                setStringData("a18",mCursor.getString(mCursor.getColumnIndex("a18")));
                setStringData("a19",mCursor.getString(mCursor.getColumnIndex("a19")));
                setStringData("a20",mCursor.getString(mCursor.getColumnIndex("a20")));
                setIntData("SaveTime", 0);
                setIntData("SaveLsColume", 0);
                setIntData("SaveLsRow", 0);
            }
        } catch (Exception e) {
            Toast.makeText(this, "錯誤",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //儲存所有的帳號ID
    public void setIDData() {
        //DB資料的內容讀取 :
        UserDataDAO userDataDAO = new UserDataDAO(this);
        Cursor mCursor = userDataDAO.getAllCursor();
        try {
            for (int i = 0; i < 2 ;i++){
             if (!mCursor.moveToPosition(i)) {
                }else {
                 setStringData("_id"+ i,mCursor.getString(mCursor.getColumnIndex("_id")));
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "錯誤",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //DB跟XML的資料取得跟讀取的方法
    public void setStringData(String key, String value) {
        SharedPreferences spref = getApplication().getSharedPreferences(KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor PE = spref.edit();
        PE.putString(key, value);
        PE.commit();
    }
    public void setIntData(String key, int value) {
        SharedPreferences spref = getApplication().getSharedPreferences(KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor PE = spref.edit();
        PE.putInt(key, value);
        PE.commit();
    }
    public String getData(String key) {
        SharedPreferences spref = getApplication().getSharedPreferences(KEY, Context.MODE_PRIVATE);
        String strValue = spref.getString(key, null);
        return strValue;
    }

    //離開遊戲畫面時呼叫timeset.pause()暫停時間
    @Override
    protected void onPause() {
        userDataDAO.rgetnameData((RadioButton)findViewById(R.id.rBUser01), 0);
        userDataDAO.rgetnameData((RadioButton)findViewById(R.id.rBUser02), 1);
        userDataDAO.rgetnameData((RadioButton)findViewById(R.id.rBUser03), 2);
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
        userDataDAO.rgetnameData((RadioButton)findViewById(R.id.rBUser01), 0);
        userDataDAO.rgetnameData((RadioButton)findViewById(R.id.rBUser02), 1);
        userDataDAO.rgetnameData((RadioButton)findViewById(R.id.rBUser03), 2);
        try {
            if(bgm == null) {
                bgm.getMediaPlayer().start();
            }} catch (Exception e) {}
        super.onRestart();
    }


}
