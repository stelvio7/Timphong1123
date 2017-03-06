package com.nhm.timphong.homeview;

/**
 * Created by AppDev on 2015-08-17.
 */
public class HomeListData {
    private String idx;
    private String imgUrl;
    private String title;
    private String locate;
    private String locateDetail;
    private String imgTypeUrl;

    public HomeListData(String idx, String imgUrl, String title, String locate, String locateDetail, String imgTypeUrl) {
        this.idx = idx;
        this.imgUrl = imgUrl;
        this.title = title;
        this.locate = locate;
        this.locateDetail = locateDetail;
        this.imgTypeUrl = imgTypeUrl;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getLocateDetail() {
        return locateDetail;
    }

    public void setLocateDetail(String locateDetail) {
        this.locateDetail = locateDetail;
    }

    public String getImgTypeUrl() {
        return imgTypeUrl;
    }

    public void setImgTypeUrl(String imgTypeUrl) {
        this.imgTypeUrl = imgTypeUrl;
    }
}
