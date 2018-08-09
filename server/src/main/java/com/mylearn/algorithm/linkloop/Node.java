package com.mylearn.algorithm.linkloop;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-4-23
 * Time: ????10:44
 * CopyRight:360buy
 * Descrption:    ????node???????t??
 * To change this template use File | Settings | File Templates.
 */
public class Node {
    private Node next;
    public String data;

    public Node(Node next, String data) {
        this.next = next;
        this.data = data;
    }

    public Node() {
        this.next = null;
        this.data = null;

    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
