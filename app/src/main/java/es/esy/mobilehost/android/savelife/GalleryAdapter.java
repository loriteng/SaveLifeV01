package es.esy.mobilehost.android.savelife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/9/13.
 */

//點選動物圖鑑移動至該動物的詳細資料
public class GalleryAdapter extends BaseAdapter {
    Context context;
    //動物圖鑑陣列
    int photoList[];
    LayoutInflater inflter;

    public GalleryAdapter(Context applicationContext, int[] photoList) {
        this.context = applicationContext;
        this.photoList = photoList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return photoList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //取得的畫面事件
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.galleryview, null); // inflate the layout
        ImageView icon = (ImageView) view.findViewById(R.id.icon); // get the reference of ImageView
        icon.setImageResource(photoList[i]); // set logo images
        return view;
    }
}