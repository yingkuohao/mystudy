package com.mylearn.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-3-14
 * Time: ????4:10
 * CopyRight:360buy
 * Descrption:  ?????
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *  ???????
 *  java.lang.OutOfMemoryError: Java heap space
 Dumping heap to java_pid1108.hprof ...
 Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    ???????????????????????????????????????dump??4????????????��???
 ?????????????????????��???Memory Leak????????????Memory Overflow????????????��???
 ???????????��?????GC Roots??????t????????????��???????????????��????GC Roots
 ???j??????,???????????????????????
    ?????????��??????��?????????��???????????????????????????????????-Xms??-Xmx????
 ????????????????��???????????????????????????????????????????????
 * To change this template use File | Settings | File Templates.
 */
public class HeapOOM {
    public static void main(String args[]) {

        List<OOMObject> lst = new ArrayList<OOMObject>();
        while (true) {
            lst.add(new OOMObject());
        }

    }

    private static class OOMObject {

    }
}
