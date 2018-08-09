package com.mylearn.threadpool.sheduler;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/2/26
 * Time: 15:30
 * CopyRight: taobao
 * Descrption:  ?????????????
 */

public class TestPriorityQueue {
    public static void main(String args[]) {
        Entity entity1 = new Entity("entity1", 1);
        Entity entity2 = new Entity("entity2", 2);
        Entity entity3 = new Entity("entity3", 3);
        Entity entity4 = new Entity("entity4", 4);
        Queue<Entity> priorityQueue = new PriorityQueue<Entity>();
        priorityQueue.offer(entity2);
        priorityQueue.offer(entity4);
        priorityQueue.offer(entity3);
        priorityQueue.offer(entity1);
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll().toString());
        }
    }

    static class Entity implements Comparable {
        private String name;
        private int priority;

        Entity(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }


        @Override
        public int compareTo(Object o) {
            if (o == null || !(o instanceof Entity)) return 1;
            if (o == this) return 0;
            Entity s = (Entity) o;
            if (this.priority > s.priority) {
                return 1;
            } else if (this.priority == s.priority) {
                return 0;
            } else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "name='" + name + '\'' +
                    ", priority=" + priority +
                    '}';
        }
    }
}
