package com.fqf.imdemo;

/**
 * Created by 房庆丰 on 2016/11/12.
 */

public class ServerItem implements IItem {
    @Override
    public int getType() {
        return SERVER;
    }

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
