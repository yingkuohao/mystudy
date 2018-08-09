package com.mylearn.thread.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-30
 * Time: ????1:44
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class MyQueue {

    private volatile Node head;
    private volatile Node tail;
    private static final AtomicReferenceFieldUpdater tailUpdater = AtomicReferenceFieldUpdater.newUpdater(MyQueue.class, Node.class, "tail");

    public boolean getTail(Node expect, Node update) {
        return tailUpdater.compareAndSet(this, expect, update);
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }
}
