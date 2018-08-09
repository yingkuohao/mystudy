package com.alicp.test.log.common;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/5/8
 * Time: 上午11:19
 * CopyRight: taobao
 * Descrption:     票号算法校验
 */

public class TestSG {

    private int sgBCLen = 18;
    private int sgBCCdLen = 2;
    private int sgValLen = 27;
    private int sgValCdLen = 3;
    private int sgCslAdder = 19;


    public static void main(String[] args) {
        TestSG testSG = new TestSG();
        String lotterySn = "350311000199101847";
        int lott = testSG.sgCsBarcodeCD(lotterySn.toCharArray());
        System.out.println("lott=" + lott);
        String lotterySnAll = "350311000199101847371658876";
        int lottB = testSG.sgCsLVirnCD(lotterySnAll.toCharArray());
        System.out.println("lottB=" + lottB);
    }

    /**
     * JJGGGGBBBBBBBTTT(CC)
     * 获取校验码,比如:
     * 票号:     350311000199101847
     * 返回的结果是后两位:47,也就是校验位
     *
     * @param data
     * @return
     */
    private int sgCsBarcodeCD(char[] data) {
        int[] s = {};
        int BCPrime[] = {173, 53, 229, 71, 199, 331, 107, 131, 379, 151, 167, 313, 223, 89, 239, 263};
        int sum = 0;
        for (int i = 0; i < BCPrime.length; i++) {
            sum += (data[i] - '0') * BCPrime[i];
        }
        return (sum + sgCslAdder) % 97;
    }

    /**
     * 35031100019910184737165858876
     * JJGGGGBBBBBBBTTTVVVVVVVV(CCC)
     * 丰彩票不是这个规则,是
     * “JJGGGGBBBBBBBTTTCCVVVVVVVCCC
     * 所以校验出来的不对
     *
     * @param data
     * @return
     */
    int sgCsLVirnCD(char[] data) {
        int VPrime[] = {11, 149, 211, 41, 233, 37, 443, 61, 113, 79, 101, 313, 317, 167, 109, 43, 197, 163, 263, 293, 367, 173, 53, 229};
        int sum = 0;
        for (int i = 0; i < VPrime.length; i++) {
            sum += (data[i] - '0') * VPrime[i];
        }
        return (sum + sgCslAdder) % 997;
    }


}

