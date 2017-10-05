package es.esy.mobilehost.android.savelife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import es.esy.mobilehost.android.savelife.PlayGame.BGM;

//動物圖鑑詳細資料的畫面
public class GalleryDetail extends Menu2Activity {
    //動物圖鑑的圖片
    ImageView selectedImage;
    //動物圖鑑文字資料
    TextView selectedText;
    //遊戲音樂
    private BGM bgm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallerydetail);

        if(!bgm.getMediaPlayer().isPlaying()){
            bgm.getMediaPlayer().start();
        }

        selectedImage = (ImageView) findViewById(R.id.selectedImage); // init a ImageView
        Intent intent = getIntent(); // get Intent which we set from Previous Activity
        selectedImage.setImageResource(intent.getIntExtra("image", 0)); // get image from Intent and set it in ImageView
        selectedText =(TextView) findViewById(R.id.selectedText);
        selectedText.setMovementMethod(ScrollingMovementMethod.getInstance());
        selectedText.setText(intent.getIntExtra("text",0));

    }

    //回到遊戲畫面時呼叫timeset.pause()暫停時間
    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            if(bgm == null) {
                bgm.getMediaPlayer().start();
            }} catch (Exception e) {}
    }
    //離開遊戲畫面時呼叫timeset.pause()暫停時間
    @Override
    protected void onPause() {
        super.onPause();
        try {
            if(bgm == null) {
                //bgm = new BGM();
                bgm.getMediaPlayer().pause();
            }} catch (Exception e) {}
    }

}
