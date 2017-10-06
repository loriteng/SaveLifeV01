package es.esy.mobilehost.android.savelife.PlayGame;


import android.media.MediaPlayer;

/**
 * Created by Administrator on 2017/9/30.
 */
public class BGM {
    private static MediaPlayer mediaPlayer;

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        BGM.mediaPlayer = mediaPlayer;
    }
}
