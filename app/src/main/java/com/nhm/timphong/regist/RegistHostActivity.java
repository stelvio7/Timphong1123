package com.nhm.timphong.regist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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


public class RegistHostActivity extends Activity {

    @Bind(R.id.btnRegist) Button btnRegist;
    @Bind(R.id.btnBack) Button btnBack;

    @Bind(R.id.edCompanyName) FormEditText edCompanyName;
    @Bind(R.id.edCeoName) FormEditText edCeoName;
    @Bind(R.id.edCompanyAdress) FormEditText edCompanyAdress;
    @Bind(R.id.edCompanyRegiNumber) FormEditText edCompanyRegiNumber;
    @Bind(R.id.edCompanyPhoneNumber) FormEditText edCompanyPhoneNumber;
    @Bind(R.id.edCompanyEmail) FormEditText edCompanyEmail;

    @Bind(R.id.edDamName) FormEditText edDamName;
    @Bind(R.id.edDamCellPhone) FormEditText edDamCellPhone;
    @Bind(R.id.edDamPhone) FormEditText edDamPhone;
    @Bind(R.id.edDamEmail) FormEditText edDamEmail;
    @Bind(R.id.edDamPasswd) FormEditText edDamPasswd;
    @Bind(R.id.edDamPasswdConfirm) FormEditText edDamPasswdConfirm;
    @Bind(R.id.edDamAddress) FormEditText edDamAddress;
    @Bind(R.id.edDamPhoto) FormEditText edDamPhoto;
    private CustomDialog mCustomDialog;

    private Context mContext = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registhost);

        ButterKnife.bind(this);
        edCompanyAdress.setEnabled(false);
        edDamAddress.setEnabled(false);
        edDamPhoto.setEnabled(false);

        mContext = getBaseContext();
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

    @OnClick({R.id.btnRegist, R.id.btnBack})
    void onClickHandler(View v){
        if (v.getId() == R.id.btnBack) {
            finish();
        }else if (v.getId() == R.id.btnRegist) {
            goRegist();
        }
    }

    private void goRegist(){
        FormEditText[] allFields    = { edCompanyName, edCeoName, edCompanyRegiNumber, edCompanyAdress, edCompanyPhoneNumber, edCompanyEmail, edDamName, edDamCellPhone, edDamPhone, edDamEmail,
                edDamPasswd, edDamPasswdConfirm, edDamAddress, edDamPhoto};

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

    private void regist(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Info.API_URL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        Util.showProgress(RegistHostActivity.this);

        restAdapter.create(Info.class).regiCompany(Util.getDeviceId(mContext), "I", "C", edCompanyName.getText().toString().trim(), edCeoName.getText().toString().trim(),
                edCompanyRegiNumber.getText().toString().trim(), edCompanyAdress.getText().toString().trim(), edCompanyPhoneNumber.getText().toString().trim(),
                edCompanyEmail.getText().toString().trim(), edDamName.getText().toString().trim(), edDamCellPhone.getText().toString().trim(),
                edDamPhone.getText().toString().trim(), edDamEmail.getText().toString().trim(), edDamPasswd.getText().toString().trim(),
                edDamPasswdConfirm.getText().toString().trim(), edDamAddress.getText().toString().trim(), edDamPhoto.getText().toString().trim(),
                new Callback<RegistUserInfo>() {
            @Override
            public void success(RegistUserInfo info, Response response) {
                if (response.getStatus() == HttpStatus.SC_OK) {
                    if (info.getResultCode().equals("0")) {
                        String failMsg = info.getResultMsg();
                        if (failMsg != null) {
                            if (!failMsg.equals("")) {
                                mCustomDialog = new CustomDialog(RegistHostActivity.this,
                                        "",
                                        failMsg,
                                        xClickListener, leftClickListener, null,
                                        Constant.DIALOGTYPE_ONEBUTTON);
                                mCustomDialog.show();
                                return;
                            }
                        }
                        ThisUtil.saveStrPrefer(mContext, Constant.PREF_MEMBER_NUM, info.getMemberIdx());
                    } else if (info.getResultCode().equals("1")) {
                        String failMsg = info.getResultMsg();
                        if (failMsg != null) {
                            if (!failMsg.equals("")) {
                                mCustomDialog = new CustomDialog(RegistHostActivity.this,
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
}
