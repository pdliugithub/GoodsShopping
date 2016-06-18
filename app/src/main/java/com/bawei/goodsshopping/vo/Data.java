package com.bawei.goodsshopping.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public class Data implements Serializable{

    private List<Channel> channel;

    public List<Channel> getChannel() {
        return channel;
    }

    public void setChannel(List<Channel> channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Data{" +
                "channel=" + channel +
                '}';
    }
}
