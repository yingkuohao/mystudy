package com.mylearn.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-3-14
 * Time: ????2:39
 * CopyRight:360buy
 * Descrption:    ????????????
 *
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * ?????��?????????????��????OOM????????????PermGen space???????????????????????
 * ??HotSpot ??????��??>?????????
 * To change this template use File | Settings | File Templates.
 */
public class RuntimeConstantPoolOOM {
    public static void main(String args[]) {
        List<String> lst = new ArrayList<String>();
        int i = 0;
        while(true) {
            lst.add(String.valueOf(i++).intern());
        }
    }
}
