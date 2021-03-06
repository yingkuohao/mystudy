package com.mylearn.algorithm.linkloop;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-4-28
 * Time: ????4:11
 * CopyRight:360buy
 * Descrption
 * <p/>
 * ?��?}??t???????
 * <p/>
 * To change this template use File | Settings | File Templates.
 */
public class TestIntersect1 {


    public static void main(String args[]) {
//        hashMethod();
//        connectLoopMethod();
        lasthNodeMethod();
    }


    /**
     * ??????
     * ???}????��???t??????????????????????????��????}??t??????????????????????????
     * ????????????????????????????????}??t?????????????????????��???��?????
     * o(length(h1)+length(h2))
     */
    private static void lasthNodeMethod() {
        LinkNode linkNode0 = new LinkNode();
        LinkNode linkNode1 = new LinkNode();

        construct2Link(linkNode0, linkNode1);
        Node p0 = linkNode0.getHead();
        Node p1 = linkNode1.getHead();
        while (p0 != null && p0.getNext() != null) {
            p0 = p0.getNext();
        }

        while (p1 != null && p1.getNext() != null) {
            p1 = p1.getNext();
        }
        if (p0 == p1) {
            System.out.println("??" + p0.getData());
        }

    }

    /**
     * ????????
     *  ????}??4t????��???????????????t??t????????t????????????t???��??????????}??t??????
     *  ????}??t?????????????????????????��????t??????��?
     */
    private static void connectLoopMethod() {
        //????}??t??
        LinkNode linkNode0 = new LinkNode();
        LinkNode linkNode1 = new LinkNode();

        construct2Link(linkNode0, linkNode1);
        Node p0 = linkNode0.getHead();
        while (p0 != null && p0.getNext() != null) {
            p0 = p0.getNext();
        }
        p0.setNext(linkNode1.getHead().getNext());


        TestLoop.isExistsLoop(linkNode0);

        /*           Node head = linkNode1.getHead();
        while(head !=null && head.getNext()!=null) {
            System.out.println(head.getData());
            head = head.getNext();
        }*/

    }


    /**
     *
     * ???????
     * hash?????????��?t???????
     * ?????????????}??t???????????}??t?????��???????????????????��???????????
     * ???????????��?}??t????????????????????????????}??t???????????????????
     * ?????t???????????hash?????bhash????????????t??????????????hash???????
     * ??hash???��?????????}???��?????????????��????O(length(h1) + length(h2)).??????????
     * O(length(h1))??��??????��???
     */

    private static void hashMethod() {

        //????}??t??
        LinkNode linkNode0 = new LinkNode();
        LinkNode linkNode1 = new LinkNode();

        construct2Link(linkNode0, linkNode1);

        HashFunc hashFunc = new HashFunc();

        //??????t??hash?????O(linkNode0.length)????
        Node p0 = linkNode0.getHead();
        while (p0 != null && p0.getNext() != null) {
            p0 = p0.getNext();
            hashFunc.add(p0);
        }

        //??????linkNode1?��????????hash???��?
        Node p1 = linkNode1.getHead();
        while (p1 != null && p1.getNext() != null) {
            p1 = p1.getNext();
            if (hashFunc.contains(p1)) {
                System.out.println("}??t????" + p1.getData());
                break;
            }
        }
    }

    private static void construct2Link(LinkNode linkNode0, LinkNode linkNode1) {
        linkNode0.add(new Node(null, "link0-1"));
        linkNode0.add(new Node(null, "link0-2"));
        linkNode0.add(new Node(null, "link0-3"));
        linkNode1.add(new Node(null, "link1-0"));
        linkNode1.add(new Node(null, "link1-1"));
        linkNode1.add(new Node(null, "link1-2"));

        Node target = new Node(null, "a"); //?????????
        for (int i = 0; i < 10; i++) {
            //???????10??????????????????
            if (i == 3) {
                linkNode0.add(target);
                linkNode1.add(target);
            } else {
                Node node = new Node(null, i + "");
                linkNode0.add(node);
                linkNode1.add(node);
            }
        }
    }
}
