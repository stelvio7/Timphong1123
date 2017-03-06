package com.nhm.timphong.data;

/**
 * Created by AppDev on 2015-08-17.
 */
public class DeviceInfo {
    private String resultCode;
    private String resultMsg;
    private String deviceIdx;

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

    public String getDeviceIdx() {
        return deviceIdx;
    }

    public void setDeviceIdx(String deviceIdx) {
        this.deviceIdx = deviceIdx;
    }

    public DeviceInfo(String resultCode, String resultMsg, String deviceIdx) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.deviceIdx = deviceIdx;
    }
}
