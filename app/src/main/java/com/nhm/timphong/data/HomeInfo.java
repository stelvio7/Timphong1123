package com.nhm.timphong.data;

import java.util.ArrayList;

/**
 * Created by AppDev on 2015-08-17.
 */
public class HomeInfo {
    private String resultCode;
    private String resultMsg;
    private String messageCnt;

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

    public String getMessageCnt() {
        return messageCnt;
    }

    public void setMessageCnt(String messageCnt) {
        this.messageCnt = messageCnt;
    }

    public HomeInfo(String messageCnt, String resultCode, String resultMsg) {
        this.messageCnt = messageCnt;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
