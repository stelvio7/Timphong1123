package com.nhm.timphong.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.util.Log;
import android.widget.EditText;

import com.nhm.timphong.R;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ThisUtil {

    public static ImageLoaderConfiguration getConfig(Context context){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .diskCacheExtraOptions(720, 1280, null)
                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(10 * 1024 * 1024))
                .memoryCacheSize(10 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCacheSize(100 * 1024 * 1024)
                .diskCacheFileCount(300)
                .build();
        return config;
    }

    public static DisplayImageOptions getImageLoaderOption(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.bg) // 로딩중 이미지 설정
                .showImageForEmptyUri(R.drawable.bg) // Uri주소가 잘못되었을경우(이미지없을때)
                .showImageOnFail(R.drawable.bg) // 로딩 실패시
                .resetViewBeforeLoading(false)  // 로딩전에 뷰를 리셋하는건데 false로 하세요 과부하!
                        // .delayBeforeLoading(0) // 로딩전 딜레이라는데 필요한일이 있을까요..?ㅋㅋ
                .cacheInMemory(true) // 메모리케시 사용여부
                .cacheOnDisk(true) // 디스크캐쉬를 사용여부(사용하세요왠만하면)
                .considerExifParams(false) // 사진이미지의 회전률 고려할건지
                .build();

        return options;
    }

    public static void saveStrPrefer(Context context, String prefName, String str){
        SharedPreferences pref = context.getSharedPreferences(Util.getApplicationName(context), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(prefName, str);
        editor.commit();
    }

    public static boolean getBoolPrefer(Context context, String prefName){
        SharedPreferences pref = context.getSharedPreferences(Util.getApplicationName(context), Context.MODE_PRIVATE);
        return pref.getBoolean(prefName, false);
    }

    public static void saveBoolPrefer(Context context, String prefName, boolean isAutoChecked){
        SharedPreferences pref = context.getSharedPreferences(Util.getApplicationName(context), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(prefName, isAutoChecked);
        editor.commit();
    }

    public static String getStrPrefer(Context context, String prefName){
        SharedPreferences pref = context.getSharedPreferences(Util.getApplicationName(context), Context.MODE_PRIVATE);
        return pref.getString(prefName, "");
    }

    public static void saveIntPrefer(Context context, String prefName, int isAutoChecked){
        SharedPreferences pref = context.getSharedPreferences(Util.getApplicationName(context), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(prefName, isAutoChecked);
        editor.commit();
    }

    public static int getIntPrefer(Context context, String prefName){
        SharedPreferences pref = context.getSharedPreferences(Util.getApplicationName(context), Context.MODE_PRIVATE);
        return pref.getInt(prefName, 0);
    }

    //DB 새로 저장하기
    public static void dbInitialize(Context ctx, String packageDir, String dbName, String copy2dbName) {
        // check
        File folder = new File(packageDir + "databases");
        folder.mkdirs();
        File outfile = new File(packageDir + "databases/" + copy2dbName + ".db");
        if (outfile.length() <= 0) {
            AssetManager assetManager = ctx.getResources().getAssets();
            try {
                InputStream is = assetManager.open(dbName + ".db", AssetManager.ACCESS_BUFFER);
                long filesize = is.available();
                byte [] tempdata = new byte[(int)filesize];
                is.read(tempdata);
                is.close();
                boolean a = outfile.createNewFile();

                FileOutputStream fo = new FileOutputStream(outfile);
                fo.write(tempdata);
                fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int parseInt(String str){
        return Integer.parseInt(str.equals("") ? "0" : str);
    }

    public static String checkNulltoZero(EditText editText){
        if(editText.getText() == null) {
            return "0";
        }
        return editText.getText().toString().trim();
    }
}