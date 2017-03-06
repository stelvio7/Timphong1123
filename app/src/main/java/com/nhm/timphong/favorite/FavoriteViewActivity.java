package com.nhm.timphong.favorite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andreabaccega.widget.FormEditText;
import com.google.gson.GsonBuilder;
import com.nhm.timphong.R;
import com.nhm.timphong.data.HomeViewData;
import com.nhm.timphong.data.RegistUserInfo;
import com.nhm.timphong.home.Info;
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


public class FavoriteViewActivity extends Activity {

    @Bind(R.id.btnSelect) Button btnSelect;
    @Bind(R.id.btnBack) ImageView btnBack;
    @Bind(R.id.txtTitle) TextView txtTitle;

    @Bind(R.id.txtType) TextView txtType;
    @Bind(R.id.txtArea) TextView txtArea;
    @Bind(R.id.txtFloor) TextView txtFloor;
    @Bind(R.id.txtKitchen) TextView txtKitchen;
    @Bind(R.id.txtToilet) TextView txtToilet;
    @Bind(R.id.txtToiletNum) TextView txtToiletNum;
    @Bind(R.id.txtOption) TextView txtOption;
    @Bind(R.id.txtFees) TextView txtFees;
    @Bind(R.id.txtFeesList) TextView txtFeesList;
    @Bind(R.id.txtMoveDay) TextView txtMoveDay;
    @Bind(R.id.txtParking) TextView txtParking;
    @Bind(R.id.txtPets) TextView txtPets;

    HomeViewData homeViewData;

    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        ButterKnife.bind(this);
        //edTitle.setEnabled(false);

        mContext = getBaseContext();

        Intent intent = getIntent();
        homeViewData = (HomeViewData)intent.getSerializableExtra("viewObject");

        setContent();
}

    private void setContent(){

        /*private String location;
        private String imgUrl;

        private String deposit;
        private String monRent;
        private String roomCnt;

        private String number;
        private String totalNumber;

        private String optionAirYn;
        private String optionWasherYn;
        private String optionBedYn;
        private String optionDeskYn;
        private String optionWardrobeYn;
        private String optionTvYn;
        private String optionShoeYn;
        private String optionFridgeYn;
        private String optionGasYn;
        private String optionElecYn;
        private String optionMicrowaveYn;
        private String optionWaterYn;

        private String expensesElecYn;
        private String expensesWaterYn;
        private String expensesInterYn;
        private String expensesCleanYn;
        private String expensesGasYn;
        private String expensesTvYn;
        private String expensesCostYn;
        private String acreage;

        private String memo;
        private String created;*/

        txtTitle.setText(homeViewData.getTitle());
        txtType.setText(homeViewData.getKind());
        txtArea.setText(homeViewData.getTitle());
        txtFloor.setText(homeViewData.getTitle());
        txtKitchen.setText(homeViewData.getKitchenYn());
        txtToilet.setText(homeViewData.getToiletYn());
        txtToiletNum.setText(homeViewData.getToiletCnt());
        txtOption.setText(homeViewData.getOptionYn());
        txtFees.setText(homeViewData.getExpensesYn());
        //txtFeesList.setText(homeViewData.getExpensesCostYn());
        txtMoveDay.setText(homeViewData.getInDate());
        txtParking.setText(homeViewData.getParkingYn());
        txtPets.setText(homeViewData.getAnimalYn());
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

    @OnClick({R.id.btnSelect, R.id.btnBack})
    void onClickHandler(View v){
        if (v.getId() == R.id.btnBack) {
            finish();
        }else if (v.getId() == R.id.btnSelect) {

        }
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