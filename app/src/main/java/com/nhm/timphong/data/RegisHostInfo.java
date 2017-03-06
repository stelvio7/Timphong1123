package com.nhm.timphong.data;

/**
 * Created by AppDev on 2015-08-17.
 */
public class RegisHostInfo {
    private String resultCode;
    private String resultMsg;
    private String memberIdx;

    public RegisHostInfo(String resultCode, String resultMsg, String memberIdx) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.memberIdx = memberIdx;
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

    public String getMemberIdx() {
        return memberIdx;
    }

    public void setMemberIdx(String memberIdx) {
        this.memberIdx = memberIdx;
    }
}
