package com.nhm.timphong.myinfo;

/**
 * 검색 메뉴 메인
 * Created by AppDev on 2015-06-03.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andreabaccega.widget.FormEditText;
import com.google.gson.GsonBuilder;
import com.nhm.timphong.R;
import com.nhm.timphong.customui.CustomDialog;
import com.nhm.timphong.data.Constant;
import com.nhm.timphong.data.MemberInfo;
import com.nhm.timphong.data.RegisHomeInfo;
import com.nhm.timphong.home.Info;
import com.nhm.timphong.login.LoginActivity;
import com.nhm.timphong.map.MapActivity;
import com.nhm.timphong.util.ThisUtil;
import com.nhm.timphong.util.Util;

import org.apache.http.HttpStatus;
import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyinfoFragment extends Fragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private View mView = null;
    private Context mContext = null;
    //private Activity mActivity = null;
    private CustomDialog mCustomDialog;

    @Bind(R.id.ivProfile) ImageView ivProfile;
    @Bind(R.id.btnBack) Button btnBack;
    @Bind(R.id.btnCamera) Button btnCamera;
    @Bind(R.id.btnModify) Button btnModify;
    @Bind(R.id.btnLeave) Button btnLeave;
    @Bind(R.id.btnLogout) Button btnLogout;

    @Bind(R.id.txtName) TextView txtName;
    @Bind(R.id.txtEmail) TextView txtEmail;
    @Bind(R.id.txtPhone) TextView txtPhone;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MyinfoFragment newInstance(int sectionNumber) {
        MyinfoFragment fragment = new MyinfoFragment();
        //Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        //fragment.setArguments(args);


        return fragment;
    }

    public MyinfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_myinfo, container, false);
        ButterKnife.bind(this, mView);
        mContext = mView.getContext();
        //mActivity = this.getActivity();

        networkGetMemberInfo();
        return mView;
    }


    private void setMemberInfo(MemberInfo info){
        txtName.setText(info.getName());
        txtEmail.setText(info.getEmail());
        txtPhone.setText(info.getTel());
    }

    @OnClick({R.id.btnBack, R.id.btnLogout, R.id.btnCamera, R.id.btnModify, R.id.btnLeave})
    void onClickHandler(View v){
        if(v.getId() == R.id.btnBack){
            goMapActivity();
        }else if(v.getId() == R.id.btnLogout) {
            goLoginActivity();
        }else if(v.getId() == R.id.btnCamera) {
            goMapActivity();
        }else if(v.getId() == R.id.btnModify) {
            goLoginActivity();
        }else if(v.getId() == R.id.btnLeave) {
            goLoginActivity();
        }
    }

    private void goLoginActivity(){
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void goMapActivity(){

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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

    private void networkGetMemberInfo(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Info.API_URL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        Util.showProgress(getActivity());

        restAdapter.create(Info.class).getMemberInfo(ThisUtil.getStrPrefer(mContext, Constant.PREF_MEMBER_NUM),  new Callback<MemberInfo>() {
            @Override
            public void success(MemberInfo info, Response response) {
                if (response.getStatus() == HttpStatus.SC_OK) {
                    Util.dismissProgress();
                    if (info.getResultCode().equals("0")) {
                        setMemberInfo(info);
                    } else if (info.getResultCode().equals("1")) {
                        String failMsg = info.getResultMsg();
                        if (failMsg != null) {
                            if (!failMsg.equals("")) {
                                mCustomDialog = new CustomDialog(mContext,
                                        "",
                                        failMsg,
                                        xClickListener, leftClickListener, null,
                                        Constant.DIALOGTYPE_ONEBUTTON);
                                mCustomDialog.show();
                                return;
                            }
                        }
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Util.dismissProgress();
            }
        });
    }
}


