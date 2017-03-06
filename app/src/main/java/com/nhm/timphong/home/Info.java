package com.nhm.timphong.home;

import com.nhm.timphong.data.DeviceInfo;
import com.nhm.timphong.data.EscapeInfo;
import com.nhm.timphong.data.FavoriteInfo;
import com.nhm.timphong.data.HomeData;
import com.nhm.timphong.data.LoginInfo;
import com.nhm.timphong.data.MemberInfo;
import com.nhm.timphong.data.RegisHomeInfo;
import com.nhm.timphong.data.RegistUserInfo;
import com.nhm.timphong.data.VersionInfo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by AppDev on 2015-08-17.
 */
public interface Info {
    public static final String API_URL = "http://123.30.104.251";

    //어플 시작시 버전 체크
    @GET("/timphong/version_check.json")
    void getVersionCheck(
            Callback<VersionInfo> callback);

        //어플 시작시 정보 가져오기
    @GET("/timphong/device_info.json")
    void getDeviceInfo(
            @Query("deviceId") String param1,
            @Query("uniqueId") String param2,
            @Query("regId") String param3,
            @Query("model") String param4,
            @Query("brand") String param5,
            @Query("country") String param6,
            @Query("language") String param7,
            @Query("osVer") String param8,
            Callback<DeviceInfo> callback);

        //메인 홈 페이지
    @GET("/timphong/main_info.json")
    void getHome(
            @Query("deviceId") String param1,
            @Query("memberIdx") String param2,
            Callback<HomeData> callback);

    @GET("/timphong/member_process.json")
    void regiUser(
            @Query("deviceId") String param1,
            @Query("mode") String param2,
            @Query("gubun") String param3,
            @Query("name") String param4,
            @Query("email") String param5,
            @Query("password") String param6,
            @Query("tel") String param7,
            Callback<RegistUserInfo> callback);

    @GET("/timphong/member_process.json")
    void regiCompany(
            @Query("deviceId") String param1,
            @Query("mode") String param2,
            @Query("gubun") String param3,
            @Query("comTitle") String param4,
            @Query("comName") String param5,
            @Query("comNumber") String param6,
            @Query("comAddr") String param7,
            @Query("tel") String param8,
            @Query("email") String param9,
            @Query("name") String param10,
            @Query("tel") String param11,
            @Query("tel") String param12,
            @Query("email") String param13,
            @Query("password") String param14,
            @Query("password2") String param15,
            @Query("addr") String param16,
            @Query("imgFile") String param17,
            Callback<RegistUserInfo> callback);

    @GET("/timphong/member_login.json")
    void getLoginInfo(
            @Query("email") String param1,
            @Query("password") String param2,
            Callback<LoginInfo> callback);

        @GET("/timphong//member_id_find.json")
        void getSearchId(
                @Query("name") String param1,
                @Query("password") String param2,
                Callback<LoginInfo> callback);

        @GET("/timphong/member_pass_find.json")
        void getSearchPassword(
                @Query("email") String param1,
                Callback<LoginInfo> callback);


    @GET("/timphong/imgTest_house.php")
    void getRegiHomeInfo(
            @Query("memberIdx") String param1,
            @Query("mode") String param2,
            @Query("location") String param3,
            @Query("title") String param4,
            @Query("content") String param5,
            @Query("kind") String param6,
            @Query("deposit") String param7,
            @Query("monRent") String param8,
            @Query("roomCnt") String param9,
            @Query("toiletYn") String param10,
            @Query("toiletCnt") String param11,
            @Query("kitchenYn") String param12,
            @Query("number") String param13,
            @Query("totalNumber") String param14,
            @Query("optionYn") String param15,
            @Query("optionAirYn") String param16,
            @Query("optionWasherYn") String param17,
            @Query("optionBedYn") String param18,
            @Query("optionDeskYn") String param19,
            @Query("optionWardrobeYn") String param20,
            @Query("optionTvYn") String param21,
            @Query("optionShoeYn") String param22,
            @Query("optionFridgeYn") String param23,
            @Query("optionGasYn") String param24,
            @Query("optionElecYn") String param25,
            @Query("optionMicrowaveYn") String param26,
            @Query("optionWaterYn") String param27,
            @Query("inDate") String param28,
            @Query("expensesYn") String param29,
            @Query("expensesElecYn") String param30,
            @Query("expensesWaterYn") String param31,
            @Query("expensesInterYn") String param32,
            @Query("expensesCleanYn") String param33,
            @Query("expensesGasYn") String param34,
            @Query("expensesTvYn") String param35,
            @Query("expensesCostYn") String param36,
            @Query("acreage") String param37,
            @Query("parkingYn") String param38,
            @Query("animalYn") String param39,
            @Query("memo") String param40,
            Callback<RegisHomeInfo> callback);

        @GET("/timphong/house_list.json")
        void getHomeList(
                @Query("deviceId") String param1,
                @Query("memberIdx") String param2,
                Callback<FavoriteInfo> callback);

        @GET("/timphong/house_interest_list.json")
        void getFavoriteList(
                @Query("memberIdx") String param1,
                @Query("flag") String param2,
                Callback<FavoriteInfo> callback);


        @GET("/timphong/member_info.json")
        void getMemberInfo(
                @Query("memberIdx") String param1,
                Callback<MemberInfo> callback);

        @GET("/timphong/member_process.json")
        void escapeMember(
                @Query("memberIdx") String param1,
                @Query("mode") String param2,
                @Query("password") String param3,
                Callback<EscapeInfo> callback);

        @GET("/timphong/member_process.json")
        void modifyMember(
                @Query("memberIdx") String param1,
                @Query("mode") String param2,
                @Query("gubun") String param3,
                @Query("email") String param4,
                @Query("password") String param5,
                @Query("password2") String param6,
                @Query("name") String param7,
                @Query("tel") String param8,
                @Query("addr") String param9,
                @Query("comTitle") String param10,
                @Query("comName") String param11,
                @Query("comNumber") String param12,
                @Query("comAddr") String param13,
                Callback<LoginInfo> callback);

}
