package com.mylearn.parseq;

import com.linkedin.parseq.Context;
import com.linkedin.parseq.Task;
import com.linkedin.parseq.promise.PromiseException;
import com.linkedin.parseq.promise.PromiseListener;
import com.linkedin.parseq.promise.PromiseUnresolvedException;
import com.linkedin.parseq.trace.ShallowTrace;
import com.linkedin.parseq.trace.ShallowTraceBuilder;
import com.linkedin.parseq.trace.Trace;
import com.linkedin.parseq.trace.TraceBuilder;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/10
 * Time: 15:59
 * CopyRight: taobao
 * Descrption:
 */

public class SeqTask2 implements Task {
    @Override
    public String getName() {
        return "task2";
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean setPriority(int i) {
        return false;
    }

    @Override
    public ShallowTrace getShallowTrace() {
        return null;
    }

    @Override
    public Trace getTrace() {
        return null;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public ShallowTraceBuilder getShallowTraceBuilder() {
        return null;
    }

    @Override
    public TraceBuilder getTraceBuilder() {
        return null;
    }

    @Override
    public void contextRun(Context context, Task task, Collection collection) {
        System.out.println("seqTask2 run");
    }

    @Override
    public void setTraceValueSerializer(Function function) {

    }

    @Override
    public boolean cancel(Exception e) {
        return false;
    }

    @Override
    public Object get() throws PromiseException {
        return null;
    }

    @Override
    public Throwable getError() throws PromiseUnresolvedException {
        return null;
    }

    @Override
    public Object getOrDefault(Object o) throws PromiseUnresolvedException {
        return null;
    }

    @Override
    public void await() throws InterruptedException {

    }

    @Override
    public boolean await(long l, TimeUnit timeUnit) throws InterruptedException {
        return false;
    }

    @Override
    public void addListener(PromiseListener promiseListener) {

    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean isFailed() {
        return false;
    }
}
