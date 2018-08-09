package com.mylearn.algorithm.conshash;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/10/20
 * Time: 下午12:03
 * CopyRight: taobao
 * Descrption:      服务器节点
 */

public class ServerNode {
    private String ip;
    private String name;


    public ServerNode(String ip, String name) {
        this.ip = ip;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "ServerNode{" +
                "ip='" + ip + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
