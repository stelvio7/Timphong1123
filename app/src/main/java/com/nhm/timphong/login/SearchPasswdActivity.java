package com.nhm.timphong.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.andreabaccega.widget.FormEditText;
import com.google.gson.GsonBuilder;
import com.nhm.timphong.R;
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


public class SearchPasswdActivity extends Activity {
    @Bind(R.id.btnSearchPasswd) Button btnSearchPasswd;
    @Bind(R.id.btnBack) Button btnBack;

    @Bind(R.id.edEmail) FormEditText edEmail;

    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpasswd);
        ButterKnife.bind(this);

        mContext = this.getBaseContext();
    }

    private void goSearch(){
        FormEditText[] allFields = { edEmail };

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

    private void regist(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Info.API_URL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        Util.showProgress(SearchPasswdActivity.this);
        restAdapter.create(Info.class).regiUser("1111111", "I", "G", "pub2", "1234", "1234", "test", new Callback<RegistUserInfo>() {
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

    @OnClick({R.id.btnSearchPasswd, R.id.btnBack})
    void onClickHandler(View v){
        if (v.getId() == R.id.btnBack) {
            finish();
        }else if (v.getId() == R.id.btnSearchPasswd) {
            goSearch();
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
