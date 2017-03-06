package com.nhm.timphong.data;

import java.util.ArrayList;

/**
 * Created by AppDev on 2015-08-17.
 */
public class AdData {
    private String idx;
    private String title;
    private String imgUrl;
    private String link;
    private String created;

    public AdData(String idx, String title, String imgUrl, String link, String created) {
        this.idx = idx;
        this.title = title;
        this.imgUrl = imgUrl;
        this.link = link;
        this.created = created;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
