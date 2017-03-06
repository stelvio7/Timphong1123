package com.nhm.timphong.data;

/**
 * Created by AppDev on 2015-08-17.
 */
public class MemberInfo {
    private String resultCode;
    private String resultMsg;
    private String email;
    private String name;
    private String tel;
    private String addr;
    private String imgFile;
    private String comTitle;
    private String comName;
    private String comNumber;
    private String comAddr;
    private String gubun;
    private String created;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getImgFile() {
        return imgFile;
    }

    public void setImgFile(String imgFile) {
        this.imgFile = imgFile;
    }

    public String getComTitle() {
        return comTitle;
    }

    public void setComTitle(String comTitle) {
        this.comTitle = comTitle;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComNumber() {
        return comNumber;
    }

    public void setComNumber(String comNumber) {
        this.comNumber = comNumber;
    }

    public String getComAddr() {
        return comAddr;
    }

    public void setComAddr(String comAddr) {
        this.comAddr = comAddr;
    }

    public String getGubun() {
        return gubun;
    }

    public void setGubun(String gubun) {
        this.gubun = gubun;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public MemberInfo(String resultCode, String resultMsg, String email, String name, String tel, String addr, String imgFile, String comTitle, String comName, String comNumber, String comAddr, String gubun, String created) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.email = email;
        this.name = name;
        this.tel = tel;
        this.addr = addr;
        this.imgFile = imgFile;
        this.comTitle = comTitle;
        this.comName = comName;
        this.comNumber = comNumber;
        this.comAddr = comAddr;
        this.gubun = gubun;
        this.created = created;
    }
}
