package com.mylearn.util.querySame;

import com.mylearn.util.ExcelUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Package:com.mylearn.util.querySame
 * User: yingkuohao
 * Date: 12-4-12
 * Time: ????2:12
 * CopyRight:360buy
 * Descrption:
 */
public class QuerySame {

    public static int catid = 0;


    public void execute() {

//        Set<String[]> setResult = new HashSet<String[]>();
        List<String[]> setResult = new ArrayList<String[]>();

        String[] strTitle = new String[22];
        strTitle[0] = "????快?id";
        strTitle[1] = "????快???????";
        strTitle[2] = "????快?isbn";
        strTitle[3] = "????快???";
        strTitle[4] = "????快?????";
        strTitle[5] = "????快??????";
        strTitle[6] = "????快???";
        strTitle[7] = "????快?????";
        strTitle[8] = "????快????";
        if (catid == 1) {
            strTitle[9] = "????快?????????";
            strTitle[19] = "?????快?????????";
        }

        strTitle[10] = "?????快?id";
        strTitle[11] = "?????快???????";
        strTitle[12] = "??????isbn";
        strTitle[13] = "?????快???";
        strTitle[14] = "?????快?????";
        strTitle[15] = "?????快??????";
        strTitle[16] = "?????快???";
        strTitle[17] = "?????快?????";
        strTitle[18] = "?????快????";

        setResult.add(strTitle);

        //????????????????sheet???忪??

        //1.??????

        String path = "C:\\Users\\hh\\Desktop\\????\\????\\test\\";
        String totalName = "??????????????20120411.xls";
//        String totalName = "test.xls";
//        String totalName = "2012-04-12???????????????扭????.xls";
        String tarName = "2012-04-12???????????????扭????.xls";

        String pathTotal = path + totalName;
        String pathTar = path + tarName;
        File file_total = new File(pathTotal);
        File file_tar = new File(pathTar);

        List<String[]> lstTotal = ExcelUtil.readExcel2003(pathTotal, catid);

        //1.1??????";?????????

        //2.???????
        List<String[]> lstTar = ExcelUtil.readExcel2003(pathTar, catid);


        //3.???????";??????????????????";

        System.out.println("begin");
        Iterator<String[]> itTar = lstTar.iterator();
        itTar.next();  //???????????
        for (; itTar.hasNext(); ) {
            String[] strsTar = itTar.next();
            String ebookIdTar = strsTar[1];
            String ebookNameTar = strsTar[6];   //??????
            String isbnTar = strsTar[8];      //isbn


            for (Iterator<String[]> it = lstTotal.iterator(); it.hasNext(); ) {
                String[] strsTotal = it.next();
                String ebookIdTotal = strsTotal[1];
                String ebookNameTotal = strsTotal[6];   //??????
                String isbnTotal = strsTotal[8];      //isbn

                if (isMatch(ebookNameTotal, isbnTotal, ebookNameTar, isbnTar)) {
                    //????????????妊?

                    //??????????";??????4
                    String[] strSame = new String[22];
                    strSame[0] = ebookIdTotal;
                    strSame[1] = ebookNameTotal;
                    strSame[2] = isbnTotal;
                    strSame[3] = strsTotal[9];    //???
                    strSame[4] = strsTotal[20];     //?????
                    strSame[5] = strsTotal[22];   //??????
                    strSame[6] = strsTotal[21];  //???
                    strSame[7] = strsTotal[17];  //????
                    strSame[8] = strsTotal[18];  //????


                    strSame[10] = ebookIdTar; // "?????快?id";
                    strSame[11] = ebookNameTar;
                    strSame[12] = isbnTar;
                    strSame[13] = strsTar[9];    //???
                    strSame[14] = strsTar[20];     //?????
                    strSame[15] = strsTar[22];   //??????
                    strSame[16] = strsTar[21];  //???
                    strSame[17] = strsTar[17];  //????
                    strSame[18] = strsTar[18];  //????


                    if (catid == 1) {
                        // ???
                        strSame[3] = strsTotal[9];    //???
                        strSame[4] = strsTotal[21];     //?????
                        strSame[5] = strsTotal[23];   //??????
                        strSame[6] = strsTotal[22];  //???
                        strSame[7] = strsTotal[17];  //????
                        strSame[8] = strsTotal[19];  //????
                        strSame[9] = strsTotal[18]; //????????

                        //????
                        strSame[13] = strsTar[9];    //???
                        strSame[14] = strsTar[21];     //?????
                        strSame[15] = strsTar[23];   //??????
                        strSame[16] = strsTar[22];  //???
                        strSame[17] = strsTar[17];  //????
                        strSame[18] = strsTar[19];  //????
                        strSame[19] = strsTar[18]; //????????

                    } else if (catid == 3) {
                        //???
                        strSame[3] = strsTotal[11];    //???
                        strSame[4] = strsTotal[22];     //?????
                        strSame[5] = strsTotal[24];   //??????
                        strSame[6] = strsTotal[23];  //???
                        strSame[7] = strsTotal[19];  //????
                        strSame[8] = strsTotal[20];  //????

                        //????
                        strSame[13] = strsTar[11];    //???
                        strSame[14] = strsTar[22];     //?????
                        strSame[15] = strsTar[24];   //??????
                        strSame[16] = strsTar[23];  //???
                        strSame[17] = strsTar[19];  //????
                        strSame[18] = strsTar[20];  //????

                    }

                    setResult.add(strSame);
                    //??????,}????remove";??????還???
                    it.remove();
//                    itTar.remove();
                }

            }

        }
        //??????????????????????????????";???}?????";????????????";????????excel
        ExcelUtil.writeExcel(setResult, path + "tar", null, "result.xls");
        System.out.println("end");

    }

    /**
     * ?忪???????????????
     *
     * @param ebookNameTotal
     * @param isbnTotal
     * @param ebookNameTar
     * @param isbnTar
     * @return
     */
    private boolean isMatch(String ebookNameTotal, String isbnTotal, String ebookNameTar, String isbnTar) {

        if (ebookNameTotal.contains(ebookNameTar) || ebookNameTar.contains(ebookNameTotal)) {
            return true;
        }

        if (isbnTotal.equals(isbnTar)) {
            return true;
        }

        return false;
    }


    public static void main(String args[]) {
        QuerySame querySame = new QuerySame();
        querySame.execute();
    }

}
