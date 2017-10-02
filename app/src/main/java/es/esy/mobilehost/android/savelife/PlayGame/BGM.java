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

    /** Stop the music */

//   public class MyPhoneStateListener extends PhoneStateListener {
//        @Override
//        public void onCallStateChanged(int state, String phoneNumber) {
//            switch (state) {
//                //電話狀態是閒置的
//                case TelephonyManager.CALL_STATE_IDLE:
//                    mediaPlayer.start();
//                    break;
//                //電話狀態是接起的
//                case TelephonyManager.CALL_STATE_OFFHOOK:
//                    break;
//                //電話狀態是響起的
//                case TelephonyManager.CALL_STATE_RINGING:
//                    mediaPlayer.pause();
//                    break;
//                default:
//                    break;
//            }
//        }
//    }

}
