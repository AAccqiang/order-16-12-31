package com.example.administrator.orderapp.entry;

/**
 * Created by 86409 on 2016/12/2.
 */
//EventBus派发的事件实体类
public class MessageEvent {
    //将要添加LoginFragment
    public static final int TYPE_MENU_FRAGMENT = 1;
    //移除
    public static final int TYPE_MENU_REMOVE = 2;
    //结算页面左右按钮
    public static final int TYPE_Menu_LEFT = 3;
    //visitor确认后的事件
    public static final int TYPE_DIALOG= 4;

    //启动备注碎片
    public static final int TYPE_DIALOG_REMARK = 5;

    //备注碎片返回值
    public static final int TYPE_DIALOG_REMARK_RETURN = 6;

    //菜详情
    public static final int TYPE_DIALOG_MENU = 7;
    //桌号返回值
    public static final int TYPE_DIALOG_TABLE = 8;

    private int type;
    private Menus mMenus;

    private int num;
    private int pay;
    private boolean isLorR;
    private int typeNum;

    //设置备注用的
    private String remark;




    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(int typeNum) {
        this.typeNum = typeNum;
    }

    public boolean isLorR() {
        return isLorR;
    }

    public void setLorR(boolean lorR) {
        isLorR = lorR;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Menus getMenus() {
        return mMenus;
    }

    public void setMenus(Menus menus) {
        mMenus = menus;
    }

    public MessageEvent(int type, Menus menus) {
        this.type = type;
        mMenus = menus;
    }

    public MessageEvent() {
    }
}
