package com.nhm.timphong.data;

import java.io.Serializable;

/**
 * Created by AppDev on 2015-08-17.
 */
public class HomeViewData implements Serializable {
    private String idx;
    private String memberIdx;
    private String location;
    private String title;
    private String imgUrl;
    private String kind;
    private String deposit;
    private String monRent;
    private String roomCnt;
    private String toiletYn;
    private String toiletCnt;
    private String kitchenYn;
    private String number;
    private String totalNumber;
    private String optionYn;
    private String optionAirYn;
    private String optionWasherYn;
    private String optionBedYn;
    private String optionDeskYn;
    private String optionWardrobeYn;
    private String optionTvYn;
    private String optionShoeYn;
    private String optionFridgeYn;
    private String optionGasYn;
    private String optionElecYn;
    private String optionMicrowaveYn;
    private String optionWaterYn;
    private String inDate;
    private String expensesYn;
    private String expensesElecYn;
    private String expensesWaterYn;
    private String expensesInterYn;
    private String expensesCleanYn;
    private String expensesGasYn;
    private String expensesTvYn;
    private String expensesCostYn;
    private String acreage;
    private String parkingYn;
    private String animalYn;
    private String memo;
    private String created;

    public HomeViewData(String idx, String memberIdx, String location, String title, String imgUrl, String kind, String deposit, String monRent, String roomCnt, String toiletYn, String toiletCnt, String kitchenYn, String number, String totalNumber, String optionYn, String optionAirYn, String optionWasherYn, String optionBedYn, String optionDeskYn, String optionWardrobeYn, String optionTvYn, String optionShoeYn, String optionFridgeYn, String optionGasYn, String optionElecYn, String optionMicrowaveYn, String optionWaterYn, String inDate, String expensesYn, String expensesElecYn, String expensesWaterYn, String expensesInterYn, String expensesCleanYn, String expensesGasYn, String expensesTvYn, String expensesCostYn, String acreage, String parkingYn, String animalYn, String memo, String created) {
        this.idx = idx;
        this.memberIdx = memberIdx;
        this.location = location;
        this.title = title;
        this.imgUrl = imgUrl;
        this.kind = kind;
        this.deposit = deposit;
        this.monRent = monRent;
        this.roomCnt = roomCnt;
        this.toiletYn = toiletYn;
        this.toiletCnt = toiletCnt;
        this.kitchenYn = kitchenYn;
        this.number = number;
        this.totalNumber = totalNumber;
        this.optionYn = optionYn;
        this.optionAirYn = optionAirYn;
        this.optionWasherYn = optionWasherYn;
        this.optionBedYn = optionBedYn;
        this.optionDeskYn = optionDeskYn;
        this.optionWardrobeYn = optionWardrobeYn;
        this.optionTvYn = optionTvYn;
        this.optionShoeYn = optionShoeYn;
        this.optionFridgeYn = optionFridgeYn;
        this.optionGasYn = optionGasYn;
        this.optionElecYn = optionElecYn;
        this.optionMicrowaveYn = optionMicrowaveYn;
        this.optionWaterYn = optionWaterYn;
        this.inDate = inDate;
        this.expensesYn = expensesYn;
        this.expensesElecYn = expensesElecYn;
        this.expensesWaterYn = expensesWaterYn;
        this.expensesInterYn = expensesInterYn;
        this.expensesCleanYn = expensesCleanYn;
        this.expensesGasYn = expensesGasYn;
        this.expensesTvYn = expensesTvYn;
        this.expensesCostYn = expensesCostYn;
        this.acreage = acreage;
        this.parkingYn = parkingYn;
        this.animalYn = animalYn;
        this.memo = memo;
        this.created = created;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getMemberIdx() {
        return memberIdx;
    }

    public void setMemberIdx(String memberIdx) {
        this.memberIdx = memberIdx;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getMonRent() {
        return monRent;
    }

    public void setMonRent(String monRent) {
        this.monRent = monRent;
    }

    public String getRoomCnt() {
        return roomCnt;
    }

    public void setRoomCnt(String roomCnt) {
        this.roomCnt = roomCnt;
    }

    public String getToiletYn() {
        return toiletYn;
    }

    public void setToiletYn(String toiletYn) {
        this.toiletYn = toiletYn;
    }

    public String getToiletCnt() {
        return toiletCnt;
    }

    public void setToiletCnt(String toiletCnt) {
        this.toiletCnt = toiletCnt;
    }

    public String getKitchenYn() {
        return kitchenYn;
    }

    public void setKitchenYn(String kitchenYn) {
        this.kitchenYn = kitchenYn;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getOptionYn() {
        return optionYn;
    }

    public void setOptionYn(String optionYn) {
        this.optionYn = optionYn;
    }

    public String getOptionAirYn() {
        return optionAirYn;
    }

    public void setOptionAirYn(String optionAirYn) {
        this.optionAirYn = optionAirYn;
    }

    public String getOptionWasherYn() {
        return optionWasherYn;
    }

    public void setOptionWasherYn(String optionWasherYn) {
        this.optionWasherYn = optionWasherYn;
    }

    public String getOptionBedYn() {
        return optionBedYn;
    }

    public void setOptionBedYn(String optionBedYn) {
        this.optionBedYn = optionBedYn;
    }

    public String getOptionDeskYn() {
        return optionDeskYn;
    }

    public void setOptionDeskYn(String optionDeskYn) {
        this.optionDeskYn = optionDeskYn;
    }

    public String getOptionWardrobeYn() {
        return optionWardrobeYn;
    }

    public void setOptionWardrobeYn(String optionWardrobeYn) {
        this.optionWardrobeYn = optionWardrobeYn;
    }

    public String getOptionTvYn() {
        return optionTvYn;
    }

    public void setOptionTvYn(String optionTvYn) {
        this.optionTvYn = optionTvYn;
    }

    public String getOptionShoeYn() {
        return optionShoeYn;
    }

    public void setOptionShoeYn(String optionShoeYn) {
        this.optionShoeYn = optionShoeYn;
    }

    public String getOptionFridgeYn() {
        return optionFridgeYn;
    }

    public void setOptionFridgeYn(String optionFridgeYn) {
        this.optionFridgeYn = optionFridgeYn;
    }

    public String getOptionGasYn() {
        return optionGasYn;
    }

    public void setOptionGasYn(String optionGasYn) {
        this.optionGasYn = optionGasYn;
    }

    public String getOptionElecYn() {
        return optionElecYn;
    }

    public void setOptionElecYn(String optionElecYn) {
        this.optionElecYn = optionElecYn;
    }

    public String getOptionMicrowaveYn() {
        return optionMicrowaveYn;
    }

    public void setOptionMicrowaveYn(String optionMicrowaveYn) {
        this.optionMicrowaveYn = optionMicrowaveYn;
    }

    public String getOptionWaterYn() {
        return optionWaterYn;
    }

    public void setOptionWaterYn(String optionWaterYn) {
        this.optionWaterYn = optionWaterYn;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getExpensesYn() {
        return expensesYn;
    }

    public void setExpensesYn(String expensesYn) {
        this.expensesYn = expensesYn;
    }

    public String getExpensesElecYn() {
        return expensesElecYn;
    }

    public void setExpensesElecYn(String expensesElecYn) {
        this.expensesElecYn = expensesElecYn;
    }

    public String getExpensesWaterYn() {
        return expensesWaterYn;
    }

    public void setExpensesWaterYn(String expensesWaterYn) {
        this.expensesWaterYn = expensesWaterYn;
    }

    public String getExpensesInterYn() {
        return expensesInterYn;
    }

    public void setExpensesInterYn(String expensesInterYn) {
        this.expensesInterYn = expensesInterYn;
    }

    public String getExpensesCleanYn() {
        return expensesCleanYn;
    }

    public void setExpensesCleanYn(String expensesCleanYn) {
        this.expensesCleanYn = expensesCleanYn;
    }

    public String getExpensesGasYn() {
        return expensesGasYn;
    }

    public void setExpensesGasYn(String expensesGasYn) {
        this.expensesGasYn = expensesGasYn;
    }

    public String getExpensesTvYn() {
        return expensesTvYn;
    }

    public void setExpensesTvYn(String expensesTvYn) {
        this.expensesTvYn = expensesTvYn;
    }

    public String getExpensesCostYn() {
        return expensesCostYn;
    }

    public void setExpensesCostYn(String expensesCostYn) {
        this.expensesCostYn = expensesCostYn;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public String getParkingYn() {
        return parkingYn;
    }

    public void setParkingYn(String parkingYn) {
        this.parkingYn = parkingYn;
    }

    public String getAnimalYn() {
        return animalYn;
    }

    public void setAnimalYn(String animalYn) {
        this.animalYn = animalYn;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
