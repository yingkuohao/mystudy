package com.mylearn.algorithm.linkloop;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-4-23
 * Time: ????11:12
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class LinkNode {
    private Node head;
    private int size;

    private void init() {
        this.size = 0;
        this.head = new Node();
    }

    public LinkNode() {
        this.init();
    }


    /**
     *  ?§Ø??????????
     * @param value
     * @return
     */
    public boolean contains(Node value) {
        boolean flag = false;
        Node p = head.getNext();

        while (p != null) {
            if (value.getData().equals(p.getData())) {
                flag = true;
                break;
            } else {
                p = p.getNext();
            }
        }

        return flag;
    }

    /**
     * ?????
     * @param value
     * @return
     */
    public boolean add(Node value) {
//        if (contains(value)) {
//            return false;
//        } else {
            Node p =value;
            p.setNext(head.getNext());
            head.setNext(p);
            this.size++;
//        }
        return true;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
