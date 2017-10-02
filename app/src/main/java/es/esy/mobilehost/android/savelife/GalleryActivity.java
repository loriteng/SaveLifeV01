package es.esy.mobilehost.android.savelife;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import es.esy.mobilehost.android.savelife.Data.UserDataDAO;
import es.esy.mobilehost.android.savelife.PlayGame.BGM;

//圖鑑畫面
public class GalleryActivity extends MenuActivity {

    private GridView simpleGrid;
    public static final String KEY = "DataSet";
    //DB資料的內容讀取 :
    UserDataDAO mGDB = new UserDataDAO(this);
    //取得資料庫的指標
    Cursor mCursor = mGDB.getAllCursor();
    //遊戲音樂
    private BGM bgm;

    //音效播放物件
    private SoundPool soundPool;
    private int sound01;

    int info[] = {
            R.string.logo1, R.string.logo2, R.string.logo3, R.string.logo4,
            R.string.logo5, R.string.logo6, R.string.logo7, R.string.logo8,
            R.string.logo9, R.string.logo10, R.string.logo11, R.string.logo12,
            R.string.logo13, R.string.logo14,R.string.logo15, R.string.logo16,
            R.string.logo17, R.string.logo18, R.string.logo19,R.string.logo20};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        if(!bgm.getMediaPlayer().isPlaying()){
            bgm.getMediaPlayer().start();
        }
        mCursor.moveToPosition(getDate("id"));
        simpleGrid = (GridView) findViewById(R.id.simpleGridView);
        GalleryAdapter customAdapter = new GalleryAdapter(getApplicationContext(), photoList());
        simpleGrid.setAdapter(customAdapter);

        //音效檔準備
        soundPool = new SoundPool(4, AudioManager.STREAM_SYSTEM,0);
        sound01 = soundPool.load(this, R.raw.sound01, 0);

        //判斷選擇的圖鑑是否顯示訊息
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                soundPool.play(sound01,1 ,1, 0, 0, 1); //點擊按鈕出現音效
                // set an Intent to Another Activity
                if(photoList()[position] != R.drawable.galleryback) {
                    Intent intent = new Intent(GalleryActivity.this, GalleryDetail.class);
                    intent.putExtra("image", photoList()[position]); // put image data in Intent
                    intent.putExtra("text", info[position]); // put image info data in Intent
                    startActivity(intent); // start Intent

                }else{

                }
            }
        });
    }

    //右上Menu

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //圖鑑的陣列　判斷是否開啟圖鑑
    public int[] photoList(){

        int photoList[] ={
                getStringData("a01").equals("0") ? R.drawable.galleryback : R.drawable.p1,
                getStringData("a02").equals("0") ? R.drawable.galleryback : R.drawable.p2,
                getStringData("a03").equals("0") ? R.drawable.galleryback : R.drawable.p3,
                getStringData("a04").equals("0") ? R.drawable.galleryback : R.drawable.p4,
                getStringData("a05").equals("0") ? R.drawable.galleryback : R.drawable.p5,
                getStringData("a06").equals("0") ? R.drawable.galleryback : R.drawable.p6,
                getStringData("a07").equals("0") ? R.drawable.galleryback : R.drawable.p7,
                getStringData("a08").equals("0") ? R.drawable.galleryback : R.drawable.p8,
                getStringData("a09").equals("0") ? R.drawable.galleryback : R.drawable.p9,
                getStringData("a10").equals("0") ? R.drawable.galleryback : R.drawable.p10,
                getStringData("a11").equals("0") ? R.drawable.galleryback : R.drawable.p11,
                getStringData("a12").equals("0") ? R.drawable.galleryback : R.drawable.p12,
                getStringData("a13").equals("0") ? R.drawable.galleryback : R.drawable.p13 ,
                getStringData("a14").equals("0") ? R.drawable.galleryback : R.drawable.p14,
                getStringData("a15").equals("0") ? R.drawable.galleryback : R.drawable.p15 ,
                getStringData("a16").equals("0") ? R.drawable.galleryback : R.drawable.p16,
                getStringData("a17").equals("0") ? R.drawable.galleryback : R.drawable.p17,
                getStringData("a18").equals("0") ? R.drawable.galleryback : R.drawable.p18,
                getStringData("a19").equals("0") ? R.drawable.galleryback : R.drawable.p19,
                getStringData("a20").equals("0") ? R.drawable.galleryback : R.drawable.p20,
        };
        return photoList;
    }

    //取得xml資料
    public int getDate(String key) {
        SharedPreferences spref = getApplication().getSharedPreferences(KEY, Context.MODE_PRIVATE);
        int strValue = spref.getInt(key, 0);
        return strValue;
    }
    //取得xmlString的資料
    public String getStringData(String key) {
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



