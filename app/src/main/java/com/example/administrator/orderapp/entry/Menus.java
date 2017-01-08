package com.example.administrator.orderapp.entry;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class Menus implements Parcelable {

    private String dishId;
    private String dishName;
    private String price;
    private String introduction;
    private String dishClass;
    private String imgDownload;
    private String imgPath;

    private String dishNum;
    private String remark;
    private String person;
    private String pricesum;
    private String tableNum;
    private String obj;

    private String checkout;

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getTableNum() {
        return tableNum;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }

    protected Menus(Parcel in) {
        dishId = in.readString();
        dishName = in.readString();
        price = in.readString();
        introduction = in.readString();
        dishClass = in.readString();
        imgDownload = in.readString();
        imgPath = in.readString();
        dishNum = in.readString();
        remark = in.readString();
        person = in.readString();
        pricesum = in.readString();
    }

    public static final Creator<Menus> CREATOR = new Creator<Menus>() {
        @Override
        public Menus createFromParcel(Parcel in) {
            return new Menus(in);
        }

        @Override
        public Menus[] newArray(int size) {
            return new Menus[size];
        }
    };

    public String getPricesum() {
        return pricesum;
    }

    public void setPricesum(String pricesum) {
        this.pricesum = pricesum;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDishNum() {
        return dishNum;
    }

    public void setDishNum(String dishNum) {
        this.dishNum = dishNum;
    }




    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDishClass() {
        return dishClass;
    }

    public void setDishClass(String dishClass) {
        this.dishClass = dishClass;
    }

    public String getImgDownload() {
        return imgDownload;
    }

    public void setImgDownload(String imgDownload) {
        this.imgDownload = imgDownload;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }


    public Menus(String  dishId,String  dishName, String price, String introduction, String dishClass, String imgDownload, String imgPath) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.price = price;
        this.introduction = introduction;
        this.dishClass = dishClass;
        this.imgDownload = imgDownload;
        this.imgPath = imgPath;
    }

    public Menus() {
    }

    @Override
    public String toString() {
        return "Menus{" +
                "dishId='" + dishId + '\'' +
                ", dishName='" + dishName + '\'' +
                ", price='" + price + '\'' +
                ", introduction='" + introduction + '\'' +
                ", dishClass='" + dishClass + '\'' +
                ", imgDownload='" + imgDownload + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", dishNum='" + dishNum + '\'' +
                ", remark='" + remark + '\'' +
                ", person='" + person + '\'' +
                ", pricesum='" + pricesum + '\'' +
                ", tableNum='" + tableNum + '\'' +
                ", obj='" + obj + '\'' +
                ", checkout=" + checkout +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dishId);
        parcel.writeString(dishName);
        parcel.writeString(price);
        parcel.writeString(introduction);
        parcel.writeString(dishClass);
        parcel.writeString(imgDownload);
        parcel.writeString(imgPath);
        parcel.writeString(dishNum);
        parcel.writeString(remark);
        parcel.writeString(person);
        parcel.writeString(pricesum);
    }


    public Menus(String dishId, String dishName, String dishClass, String imgPath, String dishNum, String person, String remark, String price,String tableNum,String obj,String checkout) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishClass = dishClass;
        this.imgPath = imgPath;
        this.dishNum = dishNum;
        this.person = person;
        this.remark = remark;
        this.price = price;
        this.tableNum = tableNum;
        this.obj = obj;
        this.checkout = checkout;
    }


}
