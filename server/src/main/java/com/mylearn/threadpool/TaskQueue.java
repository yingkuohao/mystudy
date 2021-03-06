package com.mylearn.threadpool;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-3-19
 * Time: ????6:20
 * CopyRight:360buy
 * Descrption:   ???????
 * ?????????????��???????????��????????????????????????????????????��??????????
 * ?????????????????????????????????????????��??????????��????????TaskQueue??????????��?????��?????????????
 * FIFO??????????????????????????��?
 * To change this template use File | Settings | File Templates.
 */
public class TaskQueue {

    private List<Task> queue = new LinkedList<Task>();//???????LinkedList??4????????????????

    /**
     * ?????????????????????????????????��???
     * @param task
     */
    public synchronized void addTask(Task task) {
        if (task != null) {
            queue.add(task);
        }
    }

    /**
     * ??????????????????????????????????
     * @param task
     */
    public synchronized void finishTask(Task task) {
        if (task != null) {
            task.setState(Task.State.FINISHIED);
            queue.remove(task);
        }
    }

    /**
     * ????????????��?????
     * @return
     */
    public synchronized Task getTask() {
        Iterator<Task> it = queue.iterator();
        Task task;
        while (it.hasNext()) {
            task = it.next();
            //????????????????????????��?????
            if (Task.State.NEW.equals(task.getState())) {
                task.setState(Task.State.RUNNING);
                return task;
            }
        }
        return null;
    }
}
