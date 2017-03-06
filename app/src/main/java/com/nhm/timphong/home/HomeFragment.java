package com.nhm.timphong.home;

/**
 * 검색 메뉴 메인
 * Created by AppDev on 2015-06-03.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.GsonBuilder;
import com.nhm.timphong.R;
import com.nhm.timphong.custom.viewpager.BaseViewPager;
import com.nhm.timphong.custom.viewpager.ChartPagerAdapter;
import com.nhm.timphong.custom.viewpager.PageControl;
import com.nhm.timphong.data.AdData;
import com.nhm.timphong.data.Constant;
import com.nhm.timphong.data.HomeData;
import com.nhm.timphong.homeview.HomeRegiActivity;
import com.nhm.timphong.map.MapActivity;
import com.nhm.timphong.search.SearchAreaActivity;
import com.nhm.timphong.search.SearchEntryActivity;
import com.nhm.timphong.util.ScalableLayout;
import com.nhm.timphong.util.ThisUtil;
import com.nhm.timphong.util.Util;
import org.apache.http.HttpStatus;
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
public class HomeFragment extends Fragment implements View.OnKeyListener{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private View mView = null;
    private Context mContext = null;

    @Bind(R.id.btnSearchMap) Button btnSearchMap;
    @Bind(R.id.btnSearchRegion) Button btnSearchRegion;
    @Bind(R.id.btnHomeOne) Button btnHomeOne;
    @Bind(R.id.btnHomeRow) Button btnHomeRow;
    @Bind(R.id.btnHomeStore) Button btnHomeStore;
    @Bind(R.id.btnHomeOffice) Button btnHomeOffice;
    @Bind(R.id.btnHomeApart) Button btnHomeApart;
    @Bind(R.id.btnHomeDetached) Button btnHomeDetached;

    @Bind(R.id.btnSearch) Button btnSearch;
    @Bind(R.id.edSearch) EditText edSearch;

    private BaseViewPager pager;
    private ViewPager viewPager;
    private PageControl pageControl;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeFragment newInstance(int sectionNumber) {
        HomeFragment fragment = new HomeFragment();
        //Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        //fragment.setArguments(args);

        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, mView);
        mContext = mView.getContext();

        networkGetHome();
        return mView;
    }

    private void setPager(List<AdData> AdList){
        edSearch.setOnKeyListener(this);

        pager = new BaseViewPager(mContext);
        pageControl = new PageControl(mContext);
        pager.setPageControl(pageControl);
        pager.setAdapter(new ChartPagerAdapter(mContext, AdList));
        ScalableLayout llTutorial = (ScalableLayout)mView.findViewById(R.id.llViewPager);
        llTutorial.addView(pager);

        viewPager = pager.getMViewPager();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                if (pageControl != null) {
                    pageControl.setPageIndex(arg0);
                }
            }

            @Override
            public void onPageSelected(int arg0) {

            }
        });
    }

    @OnClick({R.id.btnSearchMap, R.id.btnSearchRegion, R.id.btnHomeOne, R.id.btnHomeRow, R.id.btnHomeStore, R.id.btnHomeOffice, R.id.btnHomeApart, R.id.btnHomeDetached, R.id.btnSearch})
    void onClickHandler(View v){
        if(v.getId() == R.id.btnSearchMap){
            goMapActivity();
        }else if(v.getId() == R.id.btnSearchRegion){
            goSearchEntryActivity();
        }else if(v.getId() == R.id.btnHomeOne){
            goMapActivity();
        }else if(v.getId() == R.id.btnHomeRow){
            goMapActivity();
        }else if(v.getId() == R.id.btnHomeStore){
            goMapActivity();
        }else if(v.getId() == R.id.btnHomeOffice){
            goMapActivity();
        }else if(v.getId() == R.id.btnHomeApart){
            goMapActivity();
        }else if(v.getId() == R.id.btnHomeDetached){
            goMapActivity();
        }else if(v.getId() == R.id.btnSearch){
            btnSearch.setBackgroundResource(R.drawable.home_search_s);
            edSearch.setText("");
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(v.getId() == R.id.edSearch){
            if(!edSearch.getText().toString().equals("")){
                btnSearch.setBackgroundResource(R.drawable.home_search_input_x);
            }else{
                btnSearch.setBackgroundResource(R.drawable.home_search_s);
            }
        }
        return false;
    }

    private void goSelectSkillActivity() {
//        Intent intent = new Intent(mContext, SelectSkillActivity.class);
//        Bundle myData = new Bundle();
//        //myData.putString(Constant.BUNDLE_IDX, "1");
//        intent.putExtras(myData);
//        startActivityForResult(intent, 0);
    }

    private void goHomeRegiActivity(){
        Intent intent = new Intent(mContext, HomeRegiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void goMapActivity(){
        Intent intent = new Intent(mContext, MapActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goSearchDetailActivity() {
//        Intent intent = new Intent(mContext, SearchDetailActivity.class);
//        Bundle myData = new Bundle();
//        Constant.BUNDLE_CLASSTYPE = mClassType;
//        Constant.BUNDLE_WEAPON = mWeapon;
//        Constant.BUNDLE_SEX =  mSex;
//        Constant.BUNDLE_CHARMTYPE = mCharmType;
//        Constant.BUNDLE_SKILL1 = selectSkillList.get(0);
//        Constant.BUNDLE_SKILL2 = selectSkillList.get(1);
//        Constant.BUNDLE_SKILL3 = selectSkillList.get(2);
//        Constant.BUNDLE_SKILL4 = selectSkillList.get(3);
//        Constant.BUNDLE_SKILL5 = selectSkillList.get(4);
//        Constant.BUNDLE_SKILL6 = selectSkillList.get(5);
//        intent.putExtras(myData);
//        startActivityForResult(intent, 1);

    }

    private void goSearchAreaActivity() {
        Intent intent = new Intent(mContext, SearchAreaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
//        //Intent intent = new Intent(mContext, SearchResultActivity.class);
//        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        //Bundle myData = new Bundle();
//        Constant.BUNDLE_IS_BASICSEARCH = 0;
//        Constant.BUNDLE_CLASSTYPE = mClassType;
//        Constant.BUNDLE_WEAPON = mWeapon;
//        Constant.BUNDLE_SEX =  mSex;
//        Constant.BUNDLE_CHARMTYPE = mCharmType;
//        Constant.BUNDLE_SKILL1 = selectSkillList.get(0);
//        Constant.BUNDLE_SKILL2 = selectSkillList.get(1);
//        Constant.BUNDLE_SKILL3 = selectSkillList.get(2);
//        Constant.BUNDLE_SKILL4 = selectSkillList.get(3);
//        Constant.BUNDLE_SKILL5 = selectSkillList.get(4);
//        Constant.BUNDLE_SKILL6 = selectSkillList.get(5);
//        //intent.putExtras(myData);
//        //startActivity(intent);
//        ((MainActivity)getActivity()).getViewPager().setCurrentItem(1, true);
    }

    private void goSearchEntryActivity() {
        Intent intent = new Intent(mContext, SearchEntryActivity.class);
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        /*if(requestCode == 0) {
            switch(resultCode){
                case Activity.RESULT_OK:
                    int result = intent.getIntExtra(Constant.BUNDLE_SKILL, 0);
                    for(int i = 0; i < skillList.size(); i++){
                        if(skillList.get(i).getIdx() == result)
                            result = i;
                    }
                    btnSkills[selectSkill].setText(skillListLanguage.get(result));
                    //btnSkills[selectSkill].setText(skillTypeLanguageList.get(result));
                    selectSkillList.set(selectSkill, skillList.get(result).getName());
                    for(int i = 0; i < selectSkillList.size(); i++) {
                        if(!selectSkillList.get(i).equals("") && i != selectSkill) {
                            Skill skillData = null;
                            for (Skill skill : skillList) {
                                if(selectSkillList.get(i).equals(skill.getName())){
                                    skillData = skill;
                                }
                            }
                            if(skillData != null) {
                                if(skillData.getSystem().equals(skillList.get(result).getSystem())){ekrcu
                                    if(skillData.getPoint() <= skillList.get(result).getPoint()) {
                                        btnSkills[i].setText("");
                                        selectSkillList.set(i, "");
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
        }else if(requestCode == 1) {
            //호석 추가하기 후 처리
            switch (resultCode)
                case Activity.RESULT_OK:
                    ((MainActivity)getActivity()).getViewPager().setCurrentItem(1, true);
                    break;
            }
        }*/
    }

    private void networkGetHome(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Info.API_URL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        Util.showProgress(getActivity());
        restAdapter.create(Info.class).getHome(Util.getDeviceId(mContext) , ThisUtil.getStrPrefer(mContext, Constant.PREF_MEMBER_NUM), new Callback<HomeData>() {
            @Override
            public void success(HomeData info, Response response) {
                if (response.getStatus() == HttpStatus.SC_OK) {
                    Util.dismissProgress();
                    setPager(info.getItemArray());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Util.dismissProgress();
            }
        });
    }
}


