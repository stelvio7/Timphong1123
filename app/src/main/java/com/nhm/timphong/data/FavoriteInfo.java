package com.nhm.timphong.data;

import java.util.List;

/**
 * Created by AppDev on 2015-08-17.
 */
public class FavoriteInfo {
    private String resultCode;
    private String resultMsg;
    private List<HomeViewData> itemArray;

    public FavoriteInfo(String resultCode, String resultMsg, List<HomeViewData> itemArray) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.itemArray = itemArray;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public List<HomeViewData> getItemArray() {
        return itemArray;
    }

    public void setItemArray(List<HomeViewData> itemArray) {
        this.itemArray = itemArray;
    }
}
