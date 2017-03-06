package com.nhm.timphong.start;

import org.apache.http.HttpStatus;

import com.google.gson.GsonBuilder;
import com.nhm.timphong.MainActivity;
import com.nhm.timphong.R;
import com.nhm.timphong.customui.CustomDialog;
import com.nhm.timphong.data.Constant;
import com.nhm.timphong.data.DeviceInfo;
import com.nhm.timphong.data.VersionInfo;
import com.nhm.timphong.home.Info;
import com.nhm.timphong.util.ThisUtil;
import com.nhm.timphong.util.Util;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class StartActivity extends Activity implements OnClickListener{
    private Handler handler;
    private CustomDialog mCustomDialog;
    private Context mContext;
    private int mDialogType = -1;
    private boolean goMain = true;
    private String appver;
    private String marketUrl;
    private String showUpdateText;
    private String showUpdateContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mContext = this.getBaseContext();

        checkVersion();
        Context context = this;
        ImageLoader.getInstance().init(ThisUtil.getConfig(mContext));
        Constant.Finish = false;
    }

    Runnable irun = new Runnable(){
        public void run(){
            //if(goMain){
            if(ThisUtil.getIntPrefer(mContext, Constant.PREF_FIRST_RUN) == Constant.FIRSTRUN){
                ThisUtil.saveIntPrefer(mContext, Constant.PREF_FIRST_RUN, Constant.NOT_FIRSTRUN);
                ThisUtil.saveIntPrefer(mContext, Constant.PREF_SET_NOTI, 0);
                ThisUtil.saveIntPrefer(mContext, Constant.PREF_SET_NOTIPOPUP, 0);
                ThisUtil.saveIntPrefer(mContext, Constant.PREF_SET_NOTISOUND, 0);
                ThisUtil.saveIntPrefer(mContext, Constant.PREF_SET_NOTIVIBRATE, 0);
            }
            goLoginActivity();
            //}
        }
    };

    public void goLogin(){
        //if(goMain){
        if(ThisUtil.getIntPrefer(mContext, Constant.PREF_FIRST_RUN) == Constant.FIRSTRUN){
            ThisUtil.saveIntPrefer(mContext, Constant.PREF_FIRST_RUN, Constant.NOT_FIRSTRUN);
            ThisUtil.saveIntPrefer(mContext, Constant.PREF_SET_NOTI, 0);
            ThisUtil.saveIntPrefer(mContext, Constant.PREF_SET_NOTIPOPUP, 0);
            ThisUtil.saveIntPrefer(mContext, Constant.PREF_SET_NOTISOUND, 0);
            ThisUtil.saveIntPrefer(mContext, Constant.PREF_SET_NOTIVIBRATE, 0);
        }
        goLoginActivity();
        //}
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(mCustomDialog != null){
            mCustomDialog = null;
        }
        goMain = false;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
    }

    private View.OnClickListener xClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mCustomDialog != null)
                mCustomDialog.dismiss();
        }
    };

    private View.OnClickListener leftClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mCustomDialog != null)
                mCustomDialog.dismiss();
            if(mDialogType == Constant.DIALOG_GO_VERSION){
                goUpdateURL();
            }
            mDialogType = -1;
        }
    };

    private View.OnClickListener xPopClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //handler =  new Handler();
            //handler.postDelayed(irun, 1500);
            goLogin();
            mDialogType = -1;
        }
    };

    private View.OnClickListener rightClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mCustomDialog != null)
                mCustomDialog.dismiss();
            mDialogType = -1;
        }
    };

    private void goLoginActivity(){
        Intent intent = null;
        //temporary
        //intent = new Intent(StartActivity.this, TestLoginActivity.class);
        intent = new Intent(StartActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    private void goMainActivity(){
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void checkVersion(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Info.API_URL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        Util.showProgress(StartActivity.this);
        restAdapter.create(Info.class).getVersionCheck(new Callback<VersionInfo>() {
            @Override
            public void success(VersionInfo info, Response response) {
                if (response.getStatus() == HttpStatus.SC_OK) {
                    Util.dismissProgress();
                    appver = info.getMarketAppVer();
                    marketUrl = info.getMarketUrl();
                    showUpdateText = info.getShowUpdateText();
                    showUpdateContents = info.getShowUpdateContents();

                    if(ThisUtil.parseInt(appver) > Util.getVersionCode(mContext)){
                        mDialogType = Constant.DIALOG_GO_VERSION;
                        mCustomDialog = new CustomDialog(StartActivity.this,
                                "",
                                showUpdateText,
                                xClickListener, leftClickListener, null,
                                Constant.DIALOGTYPE_ONEBUTTON);
                        mCustomDialog.setCancelable(false);
                        mCustomDialog.show();
                        return;
                    }else{
                        deviceInfo();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Util.dismissProgress();
            }
        });
    }

    private void deviceInfo(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Info.API_URL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        Util.showProgress(StartActivity.this);
        restAdapter.create(Info.class).getDeviceInfo( Util.getDeviceId(mContext),  ThisUtil.getStrPrefer(mContext, Constant.PREF_ID), ThisUtil.getStrPrefer(mContext, Constant.KEY_REGI_KEY),
                Util.getPhoneModel(), Util.getModelName(), Util.getCountryCode(), Util.getLanguageCode(), Util.getOSVersion(), new Callback<DeviceInfo>() {
            @Override
            public void success(DeviceInfo info, Response response) {
                if (response.getStatus() == HttpStatus.SC_OK) {
                    Util.dismissProgress();
                    ThisUtil.saveStrPrefer(mContext,Constant.PREF_MEMBER_NUM, info.getDeviceIdx());
                    goMainActivity();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Util.dismissProgress();
            }
        });
    }

    private void goUpdateURL(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri u = Uri.parse(marketUrl);
        intent.setData(u);
        startActivity(intent);
        finish();
    }
}
