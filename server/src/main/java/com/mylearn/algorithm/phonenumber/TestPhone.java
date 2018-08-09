package com.mylearn.algorithm.phonenumber;

import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-5-3
 * Time: ????3:28
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class TestPhone {

    static String[] str = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    public static void main(String args[]) {
        StringBuilder sb = new StringBuilder();
        String target = "18701521008";


        int n = 10;
        TestPhone testPhone = new TestPhone();

         String[] str1 = { "ABC", "DEFG","HIJ"   };

        String[] targetStr = new String[target.length()];
        for(int i=0;i<target.length();i++) {

            targetStr[i]   = str[Integer.valueOf(target.charAt(i)+"")];
        }

//        testPhone.mehtod1(targetStr, 0, targetStr.length,sb);
        testPhone.mehtod1(str1, 0, str1.length,sb);


/*        for (int i = 0; i < target.length(); i++) {
            char number = target.charAt(i); //8
            String curAnswer = str[i];      //TUV

            for(int k = 0; k < curAnswer.length();k++) {
                char curAnswerTmp = curAnswer.charAt(k);   //T
                sb.append(curAnswerTmp);

                for(int j= i+1;j<target.length();j++) {
                    String curAnswerSnd = str[j];      //PQRS
                    for(int m = 0; m < curAnswerSnd.length();m++) {
                        char sndAnswer = curAnswerSnd.charAt(m); //P
                        sb.append(sndAnswer);
                        //yingkhtodo:description:???????
                        break;
                    }

                }

            }
        }*/

    }

    public void mehtod1(String[] answer, int index, int n,StringBuilder sb) {
        if (index == n-1) {
            if(StringUtils.isNotBlank(answer[n-1]))  {
            for (int i = 0; i < answer[n-1].length(); i++) {
                char c = answer[n-1].charAt(i) ;
                System.out.println(sb.toString() + c);
                System.out.println("----------");
            }      }
        } else {
            if (StringUtils.isNotBlank(answer[index])) {
                for (int j = 0; j < answer[index].length(); j++) {
                    if(sb.length() > index) {
                       sb =new StringBuilder(sb.toString().substring(0,index));
                    }
                    sb.append(answer[index].charAt(j));
                    mehtod1(answer, index + 1, n, sb);
                }
            } else {
                mehtod1(answer, index + 1, n,sb);
            }
        }


    }
}
