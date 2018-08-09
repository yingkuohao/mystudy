package com.mylearn.thread.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-30
 * Time: ????10:41
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class AtomicString {
    private volatile String name;
    AtomicReferenceFieldUpdater<AtomicString, String> nameUpdate = AtomicReferenceFieldUpdater.newUpdater(AtomicString.class, String.class, "name");

    public AtomicString(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean compareAndSetName(String expect, String update) {
        return nameUpdate.compareAndSet(this, expect, update);
    }

}
