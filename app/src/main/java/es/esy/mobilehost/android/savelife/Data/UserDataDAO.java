package es.esy.mobilehost.android.savelife.Data;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/7.
 *///
//玩家資料
public class UserDataDAO extends Activity {
    //// 表格名稱
    public static final String TABLE_NAME = "pcd";

    // 編號表格欄位名稱，固定不變
    public static final String USER_ID = "_id";
    public static final String USER_NAME = "name";
    public static final String ANIMAL_01 = "a01",ANIMAL_02 = "a02",
            ANIMAL_03 = "a03",ANIMAL_04 = "a04",ANIMAL_05 = "a05",
            ANIMAL_06 = "a06",ANIMAL_07 = "a07",ANIMAL_08 = "a08",
            ANIMAL_09 = "a09",ANIMAL_10 = "a10",ANIMAL_11 = "a11",
            ANIMAL_12 = "a12",ANIMAL_13 = "a13",ANIMAL_14 = "a14",
            ANIMAL_15 = "a15",ANIMAL_16 = "a16",ANIMAL_17 = "a17",
            ANIMAL_18 = "a18",ANIMAL_19 = "a19",ANIMAL_20 = "a20";


    // 所有欄位名稱陣列，把所有表格欄位名稱變數湊起來建立一個字串陣列
    public static final String[] COLUMNS =
            {
             USER_ID, USER_NAME,
             ANIMAL_01,ANIMAL_02,ANIMAL_03,ANIMAL_04,ANIMAL_05,
             ANIMAL_06,ANIMAL_07,ANIMAL_08,ANIMAL_09,ANIMAL_10,
             ANIMAL_11,ANIMAL_12,ANIMAL_13,ANIMAL_14,ANIMAL_15,
             ANIMAL_16,ANIMAL_17,ANIMAL_18,ANIMAL_19,ANIMAL_20
            };

    // 顯示用欄位名稱陣列，
    // 在資料查詢畫面上希望顯示位置表格的編號、日期時間和說明，
    // 所以額外使用三個表格欄位名稱變數建立這個欄位名稱陣列
    public static final String[] SHOW_COLUMNS =
            {
             USER_ID, USER_NAME,
             ANIMAL_01,ANIMAL_02,ANIMAL_03,ANIMAL_04,ANIMAL_05,
             ANIMAL_06,ANIMAL_07,ANIMAL_08,ANIMAL_09,ANIMAL_10,
             ANIMAL_11,ANIMAL_12,ANIMAL_13,ANIMAL_14,ANIMAL_15,
             ANIMAL_16,ANIMAL_17,ANIMAL_18,ANIMAL_19,ANIMAL_20
            };


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USER_NAME + " REAL NOT NULL, " +
                    ANIMAL_01 + " REAL NOT NULL, " + ANIMAL_02 + " REAL NOT NULL, " + ANIMAL_03 + " REAL NOT NULL, " + ANIMAL_04 + " REAL NOT NULL, " +
                    ANIMAL_05 + " REAL NOT NULL, " + ANIMAL_06 + " REAL NOT NULL, " + ANIMAL_07 + " REAL NOT NULL, " + ANIMAL_08 + " REAL NOT NULL, " +
                    ANIMAL_09 + " REAL NOT NULL, " + ANIMAL_10 + " REAL NOT NULL, " + ANIMAL_11 + " REAL NOT NULL, " + ANIMAL_12 + " REAL NOT NULL, " +
                    ANIMAL_13 + " REAL NOT NULL, " + ANIMAL_14 + " REAL NOT NULL, " + ANIMAL_15 + " REAL NOT NULL, " + ANIMAL_16 + " REAL NOT NULL, " +
                    ANIMAL_17 + " REAL NOT NULL, " + ANIMAL_18 + " REAL NOT NULL, " + ANIMAL_19 + " REAL NOT NULL, " + ANIMAL_20 + " REAL NOT NULL"+
                    " )";

    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public UserDataDAO(Context context) {
        db = GameDBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    // 新增參數指定的物件
    public UserData insert(UserData userdata) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();
        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(USER_NAME, userdata.getUsername());
        cv.put(ANIMAL_01, userdata.getAnimalCard_1());cv.put(ANIMAL_02, userdata.getAnimalCard_2());
        cv.put(ANIMAL_03, userdata.getAnimalCard_3());cv.put(ANIMAL_04, userdata.getAnimalCard_4());
        cv.put(ANIMAL_05, userdata.getAnimalCard_5());cv.put(ANIMAL_06, userdata.getAnimalCard_6());
        cv.put(ANIMAL_07, userdata.getAnimalCard_7());cv.put(ANIMAL_08, userdata.getAnimalCard_8());
        cv.put(ANIMAL_09, userdata.getAnimalCard_9());cv.put(ANIMAL_10, userdata.getAnimalCard_10());
        cv.put(ANIMAL_11, userdata.getAnimalCard_11());cv.put(ANIMAL_12, userdata.getAnimalCard_12());
        cv.put(ANIMAL_13, userdata.getAnimalCard_13());cv.put(ANIMAL_14, userdata.getAnimalCard_14());
        cv.put(ANIMAL_15, userdata.getAnimalCard_15());cv.put(ANIMAL_16, userdata.getAnimalCard_16());
        cv.put(ANIMAL_17, userdata.getAnimalCard_17());cv.put(ANIMAL_18, userdata.getAnimalCard_18());
        cv.put(ANIMAL_19, userdata.getAnimalCard_19());cv.put(ANIMAL_20, userdata.getAnimalCard_20());
        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME, null, cv);
        // 設定編號
        userdata.setId(id);
        // 回傳結果
        return userdata;
    }

    // 修改參數指定的物件
    public boolean update(UserData userdata) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(USER_NAME, userdata.getUsername());
        cv.put(ANIMAL_01, userdata.getAnimalCard_1());cv.put(ANIMAL_02, userdata.getAnimalCard_2());
        cv.put(ANIMAL_03, userdata.getAnimalCard_3());cv.put(ANIMAL_04, userdata.getAnimalCard_4());
        cv.put(ANIMAL_05, userdata.getAnimalCard_5());cv.put(ANIMAL_06, userdata.getAnimalCard_6());
        cv.put(ANIMAL_07, userdata.getAnimalCard_7());cv.put(ANIMAL_08, userdata.getAnimalCard_8());
        cv.put(ANIMAL_09, userdata.getAnimalCard_9());cv.put(ANIMAL_10, userdata.getAnimalCard_10());
        cv.put(ANIMAL_11, userdata.getAnimalCard_11());cv.put(ANIMAL_12, userdata.getAnimalCard_12());
        cv.put(ANIMAL_13, userdata.getAnimalCard_13());cv.put(ANIMAL_14, userdata.getAnimalCard_14());
        cv.put(ANIMAL_15, userdata.getAnimalCard_15());cv.put(ANIMAL_16, userdata.getAnimalCard_16());
        cv.put(ANIMAL_17, userdata.getAnimalCard_17());cv.put(ANIMAL_18, userdata.getAnimalCard_18());
        cv.put(ANIMAL_19, userdata.getAnimalCard_19());cv.put(ANIMAL_20, userdata.getAnimalCard_20());
        // 新增一筆資料並取得編號
        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = USER_ID  + "=" + userdata.getId();

        // 執行修改資料並回傳修改的資料數量是否成功
        return  db.update(TABLE_NAME, cv, where ,null) > 0;

    }

    // 刪除參數指定編號的資料
    public boolean delete(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = USER_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where, null) > 0;
    }

     //取得所有資料的Cursor物件
    public Cursor getAllCursor() {
        Cursor result = db.query(TABLE_NAME, SHOW_COLUMNS, null, null ,null ,null ,null);
        return result;
    }

    // 取得指定編號的資料物件
    public UserData get(long id) {
        // 準備回傳結果用的物件
        UserData userData = null;
        // 使用編號為查詢條件
        String where = USER_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME, COLUMNS, where ,null ,null ,null, null ,null);
        // 如果有查詢結果
        if (result.moveToFirst()){
            // 讀取包裝一筆資料的物件
            userData = getRecord(result);
        }
        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return userData;
    }

    // 把Cursor目前的資料包裝為物件
    public UserData getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        UserData result = new UserData();
        result.setId(cursor.getLong(0));
        result.setUsername(cursor.getString(1));
        result.setAnimalCard_1(cursor.getString(2));
        result.setAnimalCard_2(cursor.getString(3));
        result.setAnimalCard_3(cursor.getString(4));
        result.setAnimalCard_4(cursor.getString(5));
        result.setAnimalCard_5(cursor.getString(6));
        result.setAnimalCard_6(cursor.getString(7));
        result.setAnimalCard_7(cursor.getString(8));
        result.setAnimalCard_8(cursor.getString(9));
        result.setAnimalCard_9(cursor.getString(10));
        result.setAnimalCard_10(cursor.getString(11));
        result.setAnimalCard_11(cursor.getString(12));
        result.setAnimalCard_12(cursor.getString(13));
        result.setAnimalCard_13(cursor.getString(14));
        result.setAnimalCard_14(cursor.getString(15));
        result.setAnimalCard_15(cursor.getString(16));
        result.setAnimalCard_16(cursor.getString(17));
        result.setAnimalCard_17(cursor.getString(18));
        result.setAnimalCard_18(cursor.getString(19));
        result.setAnimalCard_19(cursor.getString(20));
        result.setAnimalCard_20(cursor.getString(21));
        // 回傳結果
        return result;
    }

    //把帳號的名稱秀在Radious鈕上
    public void rgetnameData(RadioButton radioButton, int i) {
        //DB資料的內容讀取 :
        UserDataDAO mGDB = new UserDataDAO(this);
        //取得資料庫的指標
        Cursor mCursor = mGDB.getAllCursor();
        //將指標滑動到第一筆，取第一筆資料
        //mCursor.moveToPosition(0);
        //第一筆資料的姓名、年齡、性別、電話、地址資訊
        try {
                    if (!mCursor.moveToPosition(i)){}
                    else {
                        String Name = mCursor.getString(mCursor.getColumnIndex("name"));
                        radioButton.setText(Name);
                    }
        }catch (Exception e){
            Toast.makeText(this, "錯誤",
                    Toast.LENGTH_SHORT).show();
        }
//        如果要一次取多筆資料的話可以使用迴圈方式讀取:
//        for(int i = 0 ; i < mCursor.getCount() ; i++ )
//        {
//            //利用for迴圈切換指標位置
//            mCursor.moveToPosition(i);
//            //每筆姓名、年齡、性別、電話、地址資訊
//            String Name = mCursor.getString(mCursor.getColumnIndex("name"));
//            String Age = mCursor.getString(mCursor.getColumnIndex("age"));
//            String Sex = mCursor.getString(mCursor.getColumnIndex("sex"));
//            String Phone = mCursor.getString(mCursor.getColumnIndex("phone"));
//            String Address = mCursor.getString(mCursor.getColumnIndex("address"));
//        }
    }



}
