package com.nhm.timphong.favorite;

/**
 * 검색 메뉴 메인
 * Created by AppDev on 2015-06-03.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.GsonBuilder;
import com.nhm.timphong.R;
import com.nhm.timphong.customui.CustomDialog;
import com.nhm.timphong.data.Constant;
import com.nhm.timphong.data.FavoriteInfo;
import com.nhm.timphong.data.HomeViewData;
import com.nhm.timphong.doblist.DobList;
import com.nhm.timphong.doblist.events.OnLoadMoreListener;
import com.nhm.timphong.doblist.exceptions.NoEmptyViewException;
import com.nhm.timphong.doblist.exceptions.NoListViewException;
import com.nhm.timphong.home.Info;
import com.nhm.timphong.map.MapActivity;
import com.nhm.timphong.util.ThisUtil;
import com.nhm.timphong.util.Util;

import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

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
public class FavoriteFragment extends Fragment implements  RadioGroup.OnCheckedChangeListener{
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
    @Bind(R.id.radioHome) RadioButton radioHome;
    @Bind(R.id.radioInterest) RadioButton radioInterest;
    @Bind(R.id.radioNotice) RadioButton adioNotice;
    @Bind(R.id.radioGroupTab) RadioGroup radioGroupTab;

    private ListView listView;
    private FavoriteAdapter favoriteAdapter = null;
    private List<HomeViewData> homeViewDataList = null;

    private int mPageNum = 1;
    private int totalPage;
    private int pageType = 0;

    private String nowFlag = "S";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FavoriteFragment newInstance(int sectionNumber) {
        FavoriteFragment fragment = new FavoriteFragment();
        //Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        //fragment.setArguments(args);
        return fragment;
    }

    public FavoriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, mView);
        mContext = mView.getContext();
        //mActivity = this.getActivity();

        mPageNum = 1;

        radioGroupTab.setOnCheckedChangeListener(this);

        listView = (ListView) mView.findViewById(R.id.list);
        //listView.addHeaderView(header);
        initDobList(mView, listView);

        //networkGetFavorite(nowFlag);
        return mView;
    }

    DobList dobList;
    private void initDobList(View rootView, ListView listView) {
        // DobList initializing
        dobList = new DobList();
        try {
            // Register ListView
            //
            // NoListViewException will be thrown when
            // there is no ListView
            dobList.register(listView);
            // Add ProgressBar to footers of ListView
            // to be shown in loading more
            //dobList.addDefaultLoadingFooterView();
            // Sets the view to show if the adapter is empty
            // see startCentralLoading() method
            //View noItems = rootView.findViewById(R.id.noItems);
            //dobList.setEmptyView(noItems);
            // Callback called when reaching last item in ListView
            /*dobList.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(final int totalItemCount) {
                    Log.e(null, "onStart totalItemCount " + totalItemCount);
                    // Just inserting some dummy data after
                    // period of time to simulate waiting
                    // data from server
                    networkGetFavorite();
                }
            });*/
        } catch (NoListViewException e) {
            e.printStackTrace();
        }
        try {
            // Show ProgressBar at the center of ListView
            // this can be used while loading data from
            // server at the first time
            //
            // setEmptyView() must be called before
            //
            // NoEmptyViewException will be thrown when
            // there is no EmptyView
            dobList.startCentralLoading();
        } catch (NoEmptyViewException e) {
            e.printStackTrace();
        }
        // Simulate adding data at the first time
        networkGetFavorite(nowFlag);
    }

    private void setList(){
        favoriteAdapter = new FavoriteAdapter(mContext, R.layout.list_item_view, homeViewDataList);
        listView.setAdapter(favoriteAdapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group.getId() == R.id.radioGroupTab){
            if(checkedId == R.id.radioHome) {
                nowFlag = "S";
            }else if(checkedId == R.id.radioInterest) {
                nowFlag = "L";
            }else if(checkedId == R.id.radioNotice) {
                nowFlag = "C";
            }
            networkGetFavorite(nowFlag);
        }
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

    private void networkGetFavorite(String flag){
        homeViewDataList = new ArrayList<HomeViewData>();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Info.API_URL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        Util.showProgress(getActivity());

        restAdapter.create(Info.class).getFavoriteList(ThisUtil.getStrPrefer(mContext, Constant.PREF_MEMBER_NUM), flag, new Callback<FavoriteInfo>() {
            @Override
            public void success(FavoriteInfo info, Response response) {
                if (response.getStatus() == HttpStatus.SC_OK) {
                    Util.dismissProgress();
                    if (info.getResultCode().equals("0")) {
                        for(HomeViewData homeViewData :info.getItemArray()){
                            HomeViewData tempHomeViewData = new HomeViewData(homeViewData.getIdx(), homeViewData.getMemberIdx(), homeViewData.getLocation(), homeViewData.getTitle(),
                                    homeViewData.getImgUrl(), homeViewData.getKind(), homeViewData.getDeposit(), homeViewData.getMonRent(), homeViewData.getRoomCnt(), homeViewData.getToiletYn(),
                                    homeViewData.getToiletCnt(), homeViewData.getKitchenYn(), homeViewData.getNumber(), homeViewData.getTotalNumber(), homeViewData.getOptionYn(),
                                    homeViewData.getOptionAirYn(), homeViewData.getOptionWasherYn(), homeViewData.getOptionBedYn(), homeViewData.getOptionDeskYn(), homeViewData.getOptionWardrobeYn(),
                                    homeViewData.getOptionTvYn(), homeViewData.getOptionShoeYn(), homeViewData.getOptionFridgeYn(), homeViewData.getOptionGasYn(), homeViewData.getOptionElecYn(),
                                    homeViewData.getOptionMicrowaveYn(), homeViewData.getOptionWaterYn(), homeViewData.getInDate(), homeViewData.getExpensesYn(), homeViewData.getExpensesElecYn(),
                                    homeViewData.getExpensesWaterYn(), homeViewData.getExpensesInterYn(), homeViewData.getExpensesCleanYn(), homeViewData.getExpensesGasYn(),
                                    homeViewData.getExpensesTvYn(), homeViewData.getExpensesCostYn(), homeViewData.getAcreage(), homeViewData.getParkingYn(), homeViewData.getAnimalYn(),
                                    homeViewData.getMemo(), homeViewData.getCreated());
                            homeViewDataList.add(tempHomeViewData);
                            setList();
                        }
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


