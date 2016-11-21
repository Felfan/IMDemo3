package com.fqf.imdemo;

/**
 * Created by 房庆丰 on 2016/11/12.
 */

public class ClientItem implements IItem{
    @Override
    public int getType() {
        return CLIENT;
    }
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
