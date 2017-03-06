package com.nhm.timphong.homeview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.andreabaccega.widget.FormEditText;
import com.google.gson.GsonBuilder;
import com.nhm.timphong.R;
import com.nhm.timphong.data.HomeData;
import com.nhm.timphong.data.RegistUserInfo;
import com.nhm.timphong.doblist.DobList;
import com.nhm.timphong.doblist.events.OnLoadMoreListener;
import com.nhm.timphong.doblist.exceptions.NoEmptyViewException;
import com.nhm.timphong.doblist.exceptions.NoListViewException;
import com.nhm.timphong.favorite.FavoriteAdapter;
import com.nhm.timphong.home.Info;
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


public class HomeListActivity extends Activity {

    @Bind(R.id.btnRegist) Button btnRegist;
    @Bind(R.id.btnBack) Button btnBack;

    @Bind(R.id.edTitle) FormEditText edTitle;

    private Context mContext = null;

    private ListView listView;
    private FavoriteAdapter HomeListAdapter = null;
    private List<HomeData> HomeDataList = null;

    private int mPageNum = 1;
    private int totalPage;
    private int pageType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registhome);

        ButterKnife.bind(this);
        //edTitle.setEnabled(false);

        listView = (ListView) findViewById(R.id.list);
        initDobList(listView);

        mContext = getBaseContext();
    }

    DobList dobList;
    private void initDobList( ListView listView) {

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
            dobList.addDefaultLoadingFooterView();

            // Sets the view to show if the adapter is empty
            // see startCentralLoading() method

            //View noItems = findViewById(R.id.noItems);
            //dobList.setEmptyView(noItems);

            // Callback called when reaching last item in ListView
            dobList.setOnLoadMoreListener(new OnLoadMoreListener() {

                @Override
                public void onLoadMore(final int totalItemCount) {
                    Log.e(null, "onStart totalItemCount " + totalItemCount);

                    // Just inserting some dummy data after
                    // period of time to simulate waiting
                    // data from server
                    networkGetHomeListData();
                }
            });

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
        networkGetHomeListData();
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
        FormEditText[] allFields    = { edTitle };

        boolean allValid = true;
        for (FormEditText field: allFields) {
            allValid = field.testValidity() && allValid;
        }
        if (allValid) {
            networkGetHomeListData();
            // YAY
        } else {
            // EditText are going to appear with an exclamation mark and an explicative message.
        }
    }

    private void networkGetHomeListData(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Info.API_URL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        Util.showProgress(HomeListActivity.this);

        restAdapter.create(Info.class).regiUser(Util.getDeviceId(mContext), "I", "C", "", "", edTitle.getText().toString(), edTitle.getText().toString(), new Callback<RegistUserInfo>() {
            @Override
            public void success(RegistUserInfo info, Response response) {
                if (response.getStatus() == HttpStatus.SC_OK) {
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