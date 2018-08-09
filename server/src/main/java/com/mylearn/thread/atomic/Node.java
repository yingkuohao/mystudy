package com.mylearn.thread.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-30
 * Time: ????10:12
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Node {

    private volatile String name;
    private volatile Node next;

    //???????nextupdater?????????????????????class????????????????????????,??getName???????String??getNext???????Node??????????????field????
    private static final AtomicReferenceFieldUpdater nextUpdater = AtomicReferenceFieldUpdater.newUpdater(Node.class, Node.class, "next");
    private static final AtomicReferenceFieldUpdater<Node, String> nameUpdater = AtomicReferenceFieldUpdater.newUpdater(Node.class, String.class, "name");

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    /**
     *  nextUpdater????????????????????expect???งา????????????update?????this
     * @param expect
     * @param update
     * @return
     */
    public boolean compareAndSetNext(Node expect, Node update) {
        return nextUpdater.compareAndSet(this, expect, update);
    }

    public boolean compareAndSetNamet(String expect, String update) {
        return nameUpdater.compareAndSet(this, expect, update);
    }

}
