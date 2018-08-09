package com.mylearn.algorithm.linkloop;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-5-7
 * Time: ????6:23
 * CopyRight:360buy
 * Descrption: t????????????pre??????cur?????????????????????????
 * To change this template use File | Settings | File Templates.
 */
public class LinkReverse {
    public static void main(String args[]) {

        LinkNode linkNode = new LinkNode();
        Node target = new Node(null, "a"); //?????????

        for (int i = 0; i < 10; i++) {
            //???????10??????????????????
            if (i == 3) {
                linkNode.add(target);
            } else {
                linkNode.add(new Node(null, i + ""));
            }
        }

        /*       Node head = linkNode.getHead();
        while(head !=null && head.getNext()!=null) {
            System.out.println(head.getData());
            head = head.getNext();
        }*/

        LinkReverse.reverse(linkNode);
        Node head = linkNode.getHead();
        while (head != null && head.getNext() != null) {
            System.out.println(head.getData());
            head = head.getNext();
        }

    }


    /**
     * t???
     *
     * @param linkNode
     */
    private static void reverse(LinkNode linkNode) {

        Node head = linkNode.getHead();
        Node pre = head;   //?څ???pre???
        Node cur = head.getNext();          //???????

        while (cur.getNext() != null) {   // ??????????t??
            Node next = cur.getNext();
            cur.setNext(pre); //????
            pre = cur;    //pre???????
            cur = next;   //????????????????
            System.out.println("pre" + pre.getData());
        }

        cur.setNext(pre); //????????????
        head.setNext(null);//????head???
        linkNode.setHead(cur);  //?????????????????
    }

}
