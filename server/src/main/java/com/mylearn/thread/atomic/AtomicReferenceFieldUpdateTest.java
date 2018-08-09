package com.mylearn.thread.atomic;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-30
 * Time: ????10:12
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class AtomicReferenceFieldUpdateTest {

    public static void main(String args[]) {

        AtomicReferenceFieldUpdateTest atomicReferenceFieldUpdateTest = new AtomicReferenceFieldUpdateTest();
//        atomicReferenceFieldUpdateTest.testAtomicString();
//        atomicReferenceFieldUpdateTest.testNode();
        atomicReferenceFieldUpdateTest.testMyqueue();
    }

    private void testNode() {
        Node node0 = new Node("head");
        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        Node node3 = new Node("node3");

        Node tail = node0;
        node0.setNext(node1);
        System.out.println(tail == node0);
        boolean bol = tail.compareAndSetNext(node1, node2);
        System.out.println("result =" + node0.getName());
    }

    private void testMyqueue() {
        MyQueue myQueue = new MyQueue();
        Node node0 = new Node("head");
        Node node1 = new Node("node1");
        myQueue.setHead(node0);
        myQueue.setTail(node0);  //???§Ñ? tail ???node0

        myQueue.getTail(node0, node1);  //1.????tailUpdater??????myQueue??tail????node0 2. ???CAS???tail??node0????????????tail????node1.
        System.out.println(myQueue.getTail().getName());
    }

    private void testAtomicString() {
        AtomicString atomicString = new AtomicString("abc");
        atomicString.compareAndSetName("abc", "love");
        System.out.println("atomicString=" + atomicString.getName());
    }


}
