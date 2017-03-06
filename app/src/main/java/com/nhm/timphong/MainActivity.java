package com.nhm.timphong;

import java.util.Locale;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nhm.timphong.favorite.FavoriteFragment;
import com.nhm.timphong.home.HomeFragment;
import com.nhm.timphong.massage.MessageFragment;
import com.nhm.timphong.myinfo.MyinfoFragment;
import com.nhm.timphong.registhome.RegistHomeFragment;
import com.nhm.timphong.util.ThisUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener , RadioGroup.OnCheckedChangeListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    @Bind(R.id.radioGroupTab) RadioGroup radioGroupTab;
    @Bind(R.id.radioHome) RadioButton radioHome;
    @Bind(R.id.radioInterest) RadioButton radioInterest;
    @Bind(R.id.radioNotice) RadioButton radioNotice;
    @Bind(R.id.radioMypage) RadioButton radioMypage;
    @Bind(R.id.radioRegistration) RadioButton radioRegistration;

    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        radioGroupTab.setOnCheckedChangeListener(this);;

        mContext = this.getBaseContext();

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.hide();
        actionBar.setTitle("");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(5);
        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

        ImageLoader.getInstance().init(ThisUtil.getConfig(mContext));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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


    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    private void setPosition(int position){
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group.getId() == R.id.radioGroupTab){
            if(checkedId == R.id.radioHome) {
                setPosition(0);
            }else if(checkedId == R.id.radioInterest)
                setPosition(1);
            else if(checkedId == R.id.radioNotice)
                setPosition(2);
            else if(checkedId == R.id.radioRegistration)
                setPosition(3);
            else if(checkedId == R.id.radioMypage)
                setPosition(4);

        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
        switch (tab.getPosition()){
            case 0:
                radioHome.setChecked(true);
                break;
            case 1:
                radioInterest.setChecked(true);
                break;
            case 2:
                radioNotice.setChecked(true);
                break;
            case 3:
                radioRegistration.setChecked(true);
                break;
            case 4:
                radioMypage.setChecked(true);
                break;

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch(position){
                case 0:
                    return HomeFragment.newInstance(position + 1);
                case 1:
                    return FavoriteFragment.newInstance(position + 1);
                case 2:
                    return MessageFragment.newInstance(position + 1);
                case 3:
                    return RegistHomeFragment.newInstance(position + 1);
                case 4:
                    return MyinfoFragment.newInstance(position + 1);
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 4:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 5:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    public ViewPager getViewPager() {
        if (null == mViewPager) {
            mViewPager = (ViewPager) findViewById(R.id.pager);
        }
        mSectionsPagerAdapter.notifyDataSetChanged();
        //searchResultFragment.SearchResultFragment();
        return mViewPager;
    }
}
