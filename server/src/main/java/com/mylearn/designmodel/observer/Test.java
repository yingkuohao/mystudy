package com.mylearn.designmodel.observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-31
 * Time: ????12:06
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String args[]) {

        String TIME_FMROAT = "yyyy-MM-dd HH:mm:ss";         //注意,兑奖报表接口的日期格式一定要按这个来,否则报错
        // 默认时间格式

        //找到一个门店
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIME_FMROAT);
        LocalDateTime today = LocalDateTime.now();          //yingkhtodo:desc:test
        LocalDateTime yesterday = today.minusDays(1);                   //yingkhtodo:desc:test把这个改成1
        String startDate = dateTimeFormatter.format(yesterday);
        String endDate = dateTimeFormatter.format(today);
        String s="该票已在2017-05-17 18:50:01时间进行兑奖，兑奖者为4401055812，中奖金额：5元。";
        Test t=new Test();
        String[] s2=  t.parseErrorMsg1(s);
      GameMsg gameMsg =new GameMsg();
        HeadMsg headMsg =new HeadMsg();

        ObserverA observerA =new ObserverA();
        ObserverB observerB =new ObserverB();
        observerA.setObservable(gameMsg);
        observerB.setObservable(headMsg);

        gameMsg.setMsg("dota ???????");
        headMsg.setMsg("?????????");
    }

    private String[] parseErrorMsg1(String s) {
//        该票已在2017-05-17 18:50:01时间进行兑奖，兑奖者为4401055812，中奖金额：5元。
        String[] str = s.split("，");
        String[] s2 = new String[2];
        if (str != null && str.length == 3) {
            s2[0] = str[0].substring(3, str[0].length() - 6);
            int beginIndex = str[2].indexOf(": ");
            s2[1] = str[2].substring(beginIndex, str[2].length() - 2);
        }
        return s2;
    }
}
