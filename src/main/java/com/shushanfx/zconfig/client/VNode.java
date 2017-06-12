package com.shushanfx.zconfig.client;


/**
 * 客户端连接到server的虚拟node
 * Created by dengjianxin on 2017/6/12.
 */
public class VNode {
    private String ip = null;
    private String id = null;
    private String path = null;

    private long connectedTime = 0L;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getConnectedTime() {
        return connectedTime;
    }

    public void setConnectedTime(long connectedTime) {
        this.connectedTime = connectedTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
