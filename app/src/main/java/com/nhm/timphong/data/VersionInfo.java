package com.nhm.timphong.data;

/**
 * Created by AppDev on 2015-08-17.
 */
public class VersionInfo {
    private String resultCode;
    private String resultMsg;
    private String marketUrl;
    private String showUpdateText;
    private String showUpdateContents;
    private String marketAppVer;

    public VersionInfo(String resultCode, String resultMsg, String marketUrl, String showUpdateText, String showUpdateContents, String marketAppVer) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.marketUrl = marketUrl;
        this.showUpdateText = showUpdateText;
        this.showUpdateContents = showUpdateContents;
        this.marketAppVer = marketAppVer;
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

    public String getMarketUrl() {
        return marketUrl;
    }

    public void setMarketUrl(String marketUrl) {
        this.marketUrl = marketUrl;
    }

    public String getShowUpdateText() {
        return showUpdateText;
    }

    public void setShowUpdateText(String showUpdateText) {
        this.showUpdateText = showUpdateText;
    }

    public String getShowUpdateContents() {
        return showUpdateContents;
    }

    public void setShowUpdateContents(String showUpdateContents) {
        this.showUpdateContents = showUpdateContents;
    }

    public String getMarketAppVer() {
        return marketAppVer;
    }

    public void setMarketAppVer(String marketAppVer) {
        this.marketAppVer = marketAppVer;
    }
}
