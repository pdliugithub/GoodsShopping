package com.bawei.goodsshopping.vo;

import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public class Channel {

    private String type;
    private List<HotRootData> channel;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<HotRootData> getChannel() {
        return channel;
    }

    public void setChannel(List<HotRootData> channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "type='" + type + '\'' +
                ", channel=" + channel +
                '}';
    }
}
