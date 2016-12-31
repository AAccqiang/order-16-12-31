package com.example.administrator.orderapp.entry;

/**
 * Created by Administrator on 2016/12/25 0025.
 */

public class LoginResult {
    private String rt;
    private String rtmsg;

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
                '}';
    }
}
