package com.example.administrator.orderapp.entry;


/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class TableBean {
    private int id;
    private int num;
    private String tablename;
    private int number;
    private String description;
    private int version;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TableBean(int num, String tablename, int number) {

        this.num = num;
        this.tablename = tablename;
        this.number = number;

    }

    public TableBean() {

    }
}
