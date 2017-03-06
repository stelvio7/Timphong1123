package com.nhm.timphong.regist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.andreabaccega.widget.FormEditText;
import com.google.gson.GsonBuilder;
import com.nhm.timphong.R;
import com.nhm.timphong.customui.CustomDialog;
import com.nhm.timphong.data.Constant;
import com.nhm.timphong.data.RegistUserInfo;
import com.nhm.timphong.home.Info;
import com.nhm.timphong.util.ThisUtil;
import com.nhm.timphong.util.Util;

import org.apache.http.HttpStatus;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;


public class RegistUserActivity extends Activity {
    @Bind(R.id.btnRegist) Button btnRegist;
    @Bind(R.id.btnBack) Button btnBack;
    @Bind(R.id.edName) FormEditText edName;
    @Bind(R.id.edEmail) FormEditText edEmail;
    @Bind(R.id.edEmailConfirm) FormEditText edEmailConfirm;
    @Bind(R.id.edPasswd) FormEditText edPasswd;
    @Bind(R.id.edPhone) FormEditText edPhone;
    private Context mContext = null;
    private CustomDialog mCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registuser);
        ButterKnife.bind(this);

        mContext = this.getBaseContext();
    }

    private void goRegist(){
        FormEditText[] allFields    = { edName, edEmail, edEmailConfirm, edPasswd, edPhone };

        boolean allValid = true;
        for (FormEditText field: allFields) {
            allValid = field.testValidity() && allValid;
        }
        if (allValid) {
            regist();
            // YAY
        } else {
            // EditText are going to appear with an exclamation mark and an explicative message.
        }
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
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btnRegist, R.id.btnBack})
    void onClickHandler(View v){
        if (v.getId() == R.id.btnBack) {
            finish();
        }else if (v.getId() == R.id.btnRegist) {
            goRegist();
        }
    }

    private void regist(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Info.API_URL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        Util.showProgress(RegistUserActivity.this);

        restAdapter.create(Info.class).regiUser(Util.getDeviceId(mContext), "I", "G", edName.getText().toString().trim(), edEmail.getText().toString().trim(), edPasswd.getText().toString().trim(), edPhone.getText().toString().trim(), new Callback<RegistUserInfo>() {
            @Override
            public void success(RegistUserInfo info, Response response) {
                if (response.getStatus() == HttpStatus.SC_OK) {
                    if(info.getResultCode().equals("0")){
                        String failMsg = info.getResultMsg();
                        if(failMsg != null){
                            if(!failMsg.equals("")){
                                mCustomDialog = new CustomDialog(RegistUserActivity.this,
                                        "",
                                        failMsg,
                                        xClickListener, leftClickListener, null,
                                        Constant.DIALOGTYPE_ONEBUTTON);
                                mCustomDialog.show();
                                return;
                            }
                        }
                        ThisUtil.saveStrPrefer(mContext, Constant.PREF_MEMBER_NUM, info.getMemberIdx());
                    }else if(info.getResultCode().equals("1")){
                        String failMsg = info.getResultMsg();
                        if(failMsg != null){
                            if(!failMsg.equals("")){
                                mCustomDialog = new CustomDialog(RegistUserActivity.this,
                                        "",
                                        failMsg,
                                        xClickListener, leftClickListener, null,
                                        Constant.DIALOGTYPE_ONEBUTTON);
                                mCustomDialog.show();
                                return;
                            }
                        }
                    }
                    Util.dismissProgress();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Util.dismissProgress();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
