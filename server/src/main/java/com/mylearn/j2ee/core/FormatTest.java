package com.mylearn.j2ee.core;

import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/12/7
 * Time: 下午1:33
 * CopyRight: taobao
 * Descrption:
 */

public class FormatTest {
    public static void main(String[] args) {
        Integer payStyle = 1;
        String lotterySn = "1234535";
        String lotteryPayNo = "asdadf";
        Integer storeId = 1388000001;
//        logger.info("兑奖检查-award check begin! lotterySn={},lotteryPayNo={},payStyle={},storeId={}", lotterySn, lotteryPayNo, payStyle, storeId);

        Object[] array = new Object[]{lotterySn, lotteryPayNo, payStyle, storeId.toString(),"aasd"};
        String msg = MessageFormat.format("lotterySn:{0},lotteryPayNo:{1},payStyle:{2},storeId:{3},test{4} ",
                array);


        System.out.println("msg=" + msg);
    }
}
