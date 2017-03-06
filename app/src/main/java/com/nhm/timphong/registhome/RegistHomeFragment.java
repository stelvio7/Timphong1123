package com.nhm.timphong.registhome;

/**
 * 검색 메뉴 메인
 * Created by AppDev on 2015-06-03.
 */

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.andreabaccega.widget.FormEditText;
import com.google.gson.GsonBuilder;
import com.kyleduo.switchbutton.SwitchButton;
import com.nhm.timphong.R;
import com.nhm.timphong.customui.CustomDialog;
import com.nhm.timphong.data.Constant;
import com.nhm.timphong.data.RegisHomeInfo;
import com.nhm.timphong.home.Info;
import com.nhm.timphong.map.MapActivity;
import com.nhm.timphong.util.ThisUtil;
import com.nhm.timphong.util.Util;

import org.apache.http.HttpStatus;


import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.Query;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegistHomeFragment extends Fragment{
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
    @Bind(R.id.btnAddPosition) Button btnAddPosition;
    @Bind(R.id.btnAddPhoto) Button btnAddPhoto;
    @Bind(R.id.btnRegist) Button btnRegist;

    @Bind(R.id.edExplain) FormEditText edExplain;
    @Bind(R.id.edTitle) FormEditText edTitle;
    @Bind(R.id.edDeposit) FormEditText edDeposit;
    @Bind(R.id.edPrice) FormEditText edPrice;
    @Bind(R.id.edSize) FormEditText edSize;

    @Bind(R.id.btnAddAircon) ToggleButton btnAddAircon;
    @Bind(R.id.btnAddShoerack) ToggleButton btnAddShoerack;
    @Bind(R.id.btnAddWasher) ToggleButton btnAddWasher;
    @Bind(R.id.btnAddFridge) ToggleButton btnAddFridge;
    @Bind(R.id.btnAddBed) ToggleButton btnAddBed;
    @Bind(R.id.btnAddGasstove) ToggleButton btnAddGasstove;
    @Bind(R.id.btnAddDesk) ToggleButton btnAddDesk;
    @Bind(R.id.btnAddInduction) ToggleButton btnAddInduction;
    @Bind(R.id.btnAddWardrobe) ToggleButton btnAddWardrobe;
    @Bind(R.id.btnAddMicrowave) ToggleButton btnAddMicrowave;
    @Bind(R.id.btnAddTv) ToggleButton btnAddTv;
    @Bind(R.id.btnAddElectric) ToggleButton btnAddElectric;
    @Bind(R.id.btnAddCleaning) ToggleButton btnAddCleaning;
    @Bind(R.id.btnAddWater) ToggleButton btnAddWater;
    @Bind(R.id.btnAddGas) ToggleButton btnAddGas;
    @Bind(R.id.btnAddInternet) ToggleButton btnAddInternet;
    @Bind(R.id.btnAddCable) ToggleButton btnAddCable;
    @Bind(R.id.btnAddGuard) ToggleButton btnAddGuard;
    @Bind(R.id.btnAddOption) ToggleButton btnAddOption;

    @Bind(R.id.spType) Spinner spType;
    @Bind(R.id.spNum) Spinner spNum;
    @Bind(R.id.spToiletNum) Spinner spToiletNum;
    @Bind(R.id.spFloor) Spinner spFloor;
    @Bind(R.id.spMoveDay) Button spMoveDay;

    @Bind(R.id.switchKitchen) SwitchButton switchKitchen;
    @Bind(R.id.switchToilet) SwitchButton switchToilet;
    @Bind(R.id.switchManageFee) SwitchButton switchManageFee;
    @Bind(R.id.switchParking) SwitchButton switchParking;
    @Bind(R.id.switchAnimal) SwitchButton switchAnimal;

    private String position;
    private String photoUrl;
    private String moveDay;

    int year, month, day, hour, minute;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static RegistHomeFragment newInstance(int sectionNumber) {
        RegistHomeFragment fragment = new RegistHomeFragment();
        //Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        //fragment.setArguments(args);

        return fragment;
    }

    public RegistHomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_registhome, container, false);
        ButterKnife.bind(this, mView);

        mContext = mView.getContext();
        //mActivity = this.getActivity();

        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(mContext, R.array.number, android.R.layout.simple_spinner_item);
        spType.setAdapter(typeAdapter);
        ArrayAdapter numAdapter = ArrayAdapter.createFromResource(mContext, R.array.number, android.R.layout.simple_spinner_item);
        spNum.setAdapter(numAdapter);
        ArrayAdapter tiletNumAdapter = ArrayAdapter.createFromResource(mContext, R.array.number, android.R.layout.simple_spinner_item);
        spToiletNum.setAdapter(tiletNumAdapter);
        ArrayAdapter floorAdapter = ArrayAdapter.createFromResource(mContext, R.array.number, android.R.layout.simple_spinner_item);
        spFloor.setAdapter(floorAdapter);
        //ArrayAdapter moveDayAdapter = ArrayAdapter.createFromResource(mContext, R.array.number, android.R.layout.simple_spinner_item);
        //spMoveDay.setAdapter(moveDayAdapter);

        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day= calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        spMoveDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(mContext, dateSetListener, year, month, day).show();
            }
        });

        return mView;
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            moveDay = String.format("%d / %d / %d", year,monthOfYear+1, dayOfMonth);
        }
    };

    @OnClick({R.id.btnBack, R.id.btnAddPosition, R.id.btnAddPhoto, R.id.btnRegist})
    void onClickHandler(View v){
        if(v.getId() == R.id.btnBack){
            goMapActivity();
        }else if(v.getId() == R.id.btnAddPosition){
            goMapActivity();
        }else if(v.getId() == R.id.btnAddPhoto){
            goMapActivity();
        }else if(v.getId() == R.id.btnRegist){
            networkRegist();
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

    private void networkRegist(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Info.API_URL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        Util.showProgress(getActivity());

        restAdapter.create(Info.class).getRegiHomeInfo(ThisUtil.getStrPrefer(mContext, Constant.PREF_MEMBER_NUM), "I", position, edTitle.getText().toString().trim(), edExplain.getText().toString().trim(),
                spType.getSelectedItem().toString(), edDeposit.getText().toString().trim(), edPrice.getText().toString().trim(), spNum.getSelectedItem().toString(), switchToilet.isChecked()?"Y":"N",
                spToiletNum.getSelectedItem().toString(), switchKitchen.isChecked()?"Y":"N", spFloor.getSelectedItem().toString(), "totalNumber", "optionYn",
                btnAddAircon.isChecked()?"Y":"N", btnAddWasher.isChecked()?"Y":"N", btnAddBed.isChecked()?"Y":"N", btnAddDesk.isChecked()?"Y":"N", btnAddWardrobe.isChecked()?"Y":"N",
                btnAddTv.isChecked()?"Y":"N", btnAddShoerack.isChecked()?"Y":"N", btnAddFridge.isChecked()?"Y":"N", btnAddGasstove.isChecked()?"Y":"N", btnAddInduction.isChecked()?"Y":"N",
                btnAddMicrowave.isChecked()?"Y":"N", "optionWaterYn", moveDay, switchManageFee.isChecked()?"Y":"N", btnAddElectric.isChecked()?"Y":"N",
                btnAddWater.isChecked()?"Y":"N", btnAddInternet.isChecked()?"Y":"N", btnAddCleaning.isChecked()?"Y":"N", btnAddGas.isChecked()?"Y":"N", btnAddCable.isChecked()?"Y":"N",
                btnAddGuard.isChecked()?"Y":"N", edSize.getText().toString().trim(), switchParking.isChecked()?"Y":"N", switchAnimal.isChecked()?"Y":"N", "memo", new Callback<RegisHomeInfo>() {
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


