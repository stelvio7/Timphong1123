package com.nhm.timphong.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.andreabaccega.widget.FormEditText;
import com.google.gson.GsonBuilder;
import com.nhm.timphong.MainActivity;
import com.nhm.timphong.R;
import com.nhm.timphong.customui.CustomDialog;
import com.nhm.timphong.data.Constant;
import com.nhm.timphong.data.LoginInfo;
import com.nhm.timphong.home.Info;
import com.nhm.timphong.regist.RegistHostActivity;
import com.nhm.timphong.regist.RegistUserActivity;
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


public class LoginActivity extends Activity {
    @Bind(R.id.btnRegist) Button btnRegist;
    @Bind(R.id.btnBack) Button btnBack;
    @Bind(R.id.btnSearchId) Button btnSearchId;
    @Bind(R.id.btnSearchPasswd) Button btnSearchPasswd;
    @Bind(R.id.btnRegistUser) Button btnRegistUser;
    @Bind(R.id.btnRegistCompany) Button btnRegistCompany;

    @Bind(R.id.edEmail) FormEditText edEmail;
    @Bind(R.id.edPasswd) FormEditText edPasswd;

    private CustomDialog mCustomDialog;
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mContext = this.getBaseContext();
    }

    private void goRegist(){
        FormEditText[] allFields = { edEmail, edPasswd };

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


    private void goMainActivity(){
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goSearchIdActivity(){
        Intent intent = new Intent(mContext, SearchIdActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goSearchPasswdActivity(){
        Intent intent = new Intent(mContext, SearchPasswdActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goRegiCompanyActivity(){
        Intent intent = new Intent(mContext, RegistHostActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goRegiUserActivity(){
        Intent intent = new Intent(mContext, RegistUserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void regist(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Info.API_URL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        Util.showProgress(LoginActivity.this);
        restAdapter.create(Info.class).getLoginInfo(edEmail.getText().toString().trim(), edPasswd.getText().toString().trim(), new Callback<LoginInfo>() {
            @Override
            public void success(LoginInfo info, Response response) {
                if (response.getStatus() == HttpStatus.SC_OK) {
                    if(info.getResultCode().equals("0")){
                        goMainActivity();
                        ThisUtil.saveStrPrefer(mContext, Constant.PREF_MEMBER_NUM, info.getMemberIdx());
                    }else if(info.getResultCode().equals("1")){
                        String failMsg = info.getResultMsg();
                        if(failMsg != null){
                            if(!failMsg.equals("")){
                                mCustomDialog = new CustomDialog(LoginActivity.this,
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

    @OnClick({R.id.btnRegist, R.id.btnBack, R.id.btnSearchId, R.id.btnSearchPasswd, R.id.btnRegistUser, R.id.btnRegistCompany})
    void onClickHandler(View v){
        if (v.getId() == R.id.btnBack) {
            finish();
        }else if (v.getId() == R.id.btnRegist) {
            goRegist();
        }else if (v.getId() == R.id.btnSearchId) {
            goSearchIdActivity();
        }else if (v.getId() == R.id.btnSearchPasswd) {
            goSearchPasswdActivity();
        }else if (v.getId() == R.id.btnRegistUser) {
            goRegiUserActivity();
        }else if (v.getId() == R.id.btnRegistCompany) {
            goRegiCompanyActivity();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
