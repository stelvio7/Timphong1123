package com.nhm.timphong.massage;

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

import com.google.gson.GsonBuilder;
import com.nhm.timphong.R;
import com.nhm.timphong.customui.CustomDialog;
import com.nhm.timphong.data.Constant;
import com.nhm.timphong.data.RegisHomeInfo;
import com.nhm.timphong.home.Info;
import com.nhm.timphong.map.MapActivity;
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

/**
 * A placeholder fragment containing a simple view.
 */
public class MessageFragment extends Fragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private View mView = null;
    private Context mContext = null;
    //private Activity mActivity = null;
    private CustomDialog mCustomDialog;

    @Bind(R.id.btnBack) Button btnBack;


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MessageFragment newInstance(int sectionNumber) {
        MessageFragment fragment = new MessageFragment();
        //Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        //fragment.setArguments(args);


        return fragment;
    }

    public MessageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, mView);
        mContext = mView.getContext();
        //mActivity = this.getActivity();

        networkGetHome();
        return mView;
    }



    @OnClick({R.id.btnBack})
    void onClickHandler(View v){
        if(v.getId() == R.id.btnBack){
            goMapActivity();
        }
    }

    private void goMapActivity(){
        Intent intent = new Intent(mContext, MapActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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

    private void networkGetHome(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Info.API_URL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        Util.showProgress(getActivity());

        restAdapter.create(Info.class).getRegiHomeInfo(ThisUtil.getStrPrefer(mContext, Constant.PREF_MEMBER_NUM), "I", "좌표", "타이틀", "컨텐트", "종류", "deposit", "monRent", "roomCnt", "toiletYn",
                "toiletCnt", "kitchenYn", "number", "totalNumber", "optionYn", "optionAirYn", "optionWasherYn", "optionBedYn", "optionDeskYn", "optionWardrobeYn", "optionTvYn", "optionShoeYn",
                "optionFridgeYn", "optionGasYn", "optionElecYn", "optionMicrowaveYn", "optionWaterYn", "inDate", "expensesYn", "expensesElecYn", "expensesWaterYn", "expensesInterYn", "expensesCleanYn",
                "expensesGasYn", "expensesTvYn", "expensesCostYn", "acreage", "parkingYn", "animalYn", "memo", new Callback<RegisHomeInfo>() {
            @Override
            public void success(RegisHomeInfo info, Response response) {
                if (response.getStatus() == HttpStatus.SC_OK) {
                    Util.dismissProgress();
                    if (info.getResultCode().equals("0")) {
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
                        ThisUtil.saveStrPrefer(mContext, Constant.PREF_MEMBER_NUM, info.getMemberIdx());
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


