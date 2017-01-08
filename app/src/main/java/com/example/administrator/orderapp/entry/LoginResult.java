package com.example.administrator.orderapp.entry;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25 0025.
 */

public class LoginResult  implements Parcelable{
    private String rt;
    private String rtmsg;
    private List<List<String>> list;

    protected LoginResult(Parcel in) {
        rt = in.readString();
        rtmsg = in.readString();
    }

    public static final Creator<LoginResult> CREATOR = new Creator<LoginResult>() {
        @Override
        public LoginResult createFromParcel(Parcel in) {
            return new LoginResult(in);
        }

        @Override
        public LoginResult[] newArray(int size) {
            return new LoginResult[size];
        }
    };

    public List<List<String>> getList() {
        return list;
    }

    public void setList(List<List<String>> list) {
        this.list = list;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRtmsg() {
        return rtmsg;
    }

    public void setRtmsg(String rtmsg) {
        this.rtmsg = rtmsg;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "rt='" + rt + '\'' +
                ", rtmsg='" + rtmsg + '\'' +
                ", list=" + list +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(rt);
        parcel.writeString(rtmsg);
    }
}
