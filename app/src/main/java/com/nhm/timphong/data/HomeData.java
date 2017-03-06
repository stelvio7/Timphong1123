package com.nhm.timphong.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AppDev on 2015-08-17.
 */
public class HomeData {
    private HomeInfo item;
    private List<AdData> itemArray;

    public HomeData(HomeInfo item, ArrayList<AdData> itemArray) {
        this.item = item;
        this.itemArray = itemArray;
    }

    public HomeInfo getItem() {
        return item;
    }

    public void setItem(HomeInfo item) {
        this.item = item;
    }

    public List<AdData> getItemArray() {
        return itemArray;
    }

    public void setItemArray(List<AdData> itemArray) {
        this.itemArray = itemArray;
    }
}
