package com.example.administrator.orderapp.entry;

/**
 * Created by Administrator on 2017/1/7 0007.
 */

public class Order {

    private String orderId;
    private String menuName; //菜名字
    private String visitorNum; //客人数量
    private String payNum;     //总价格
    private String menuNum;    //菜总量
    private String imgPath;    //图片

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getVisitorNum() {
        return visitorNum;
    }

    public void setVisitorNum(String visitorNum) {
        this.visitorNum = visitorNum;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public String getMenuNum() {
        return menuNum;
    }

    public void setMenuNum(String menuNum) {
        this.menuNum = menuNum;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Order(String orderId, String menuName, String visitorNum, String payNum, String menuNum, String imgPathList) {
        this.orderId = orderId;
        this.menuName = menuName;
        this.visitorNum = visitorNum;
        this.payNum = payNum;
        this.menuNum = menuNum;
        this.imgPath = imgPathList;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", menuName='" + menuName + '\'' +
                ", visitorNum='" + visitorNum + '\'' +
                ", payNum='" + payNum + '\'' +
                ", menuNum='" + menuNum + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}

