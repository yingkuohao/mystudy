package com.mylearn.j2ee.guava.example;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import com.mylearn.j2ee.guava.DateFilter;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/4/30
 * Time: 15:49
 * CopyRight: taobao
 * Descrption:
 */

public class BuildingFilter {
    public static void main(String args[]) throws IOException {
        //        test();
        String path = "D:\\test\\db.txt";
        File file = new File(path);
        //        CharSource charSource = Files.asCharSource(file, Charset.defaultCharset());
        getTargetPhoneLine(file);
        getTargetRightLimitLine(file);
    }
/*

    private static void getTargetPhoneLine(File file) throws IOException {
        //读取包含关键字的行
        final String targetStr = "com";
        LineProcessor<List<String>> lineTargeProcessor = new LineProcessor<List<String>>() {
            List<String> lst = new ArrayList<String>();
            int lineIndex = 0;
            int succsCount = 0;
            int nullCount = 0;

            @Override
            public boolean processLine(String line) throws IOException {
                lineIndex++;
                String[] lines = line.split(",");
                Iterable<String> lineItr = Splitter.on(",").split(line);
                String phoneOrgin = Lists.newArrayList(lineItr).get(1);
                if (StringUtils.isNotBlank(phoneOrgin)) {
                    phoneOrgin = phoneOrgin.trim().replace("\"", "");
                } else {
                    nullCount++;
                }
                String phone = DateFilter.checkPhone(phoneOrgin);
                if (phone == null) {
                    //                    System.out.println("---error---" + line);
                    lst.add(line);
                } else {
                    succsCount++;
                    //                    System.out.println("---error---" + line);
                    System.out.println("---success: {}-" + phoneOrgin + "----succsCount={}" + succsCount);

                }
                return true;
            }

            @Override
            public List<String> getResult() {
                lst.add("lineIndex=" + lineIndex);
                lst.add("nullCount=" + nullCount);
                lst.add("succsCount+" + succsCount);
                return lst;
            }
        };

        List<String> lineList = Files.readLines(file, Charsets.UTF_8, lineTargeProcessor);
        System.out.println("totalLine:" + lineList.get(lineList.size() - 3));
        System.out.println("failCount.size=" + (lineList.size() - 3));
        System.out.println("nullCount:" + lineList.get(lineList.size() - 2));
        System.out.println("succsCount:" + lineList.get(lineList.size() - 1));
        //           System.out.println("lineList=" + lineList);
        //在文件尾部追加写入内容
        File fileReusult = new File("D:\\test\\phone_result.txt");
        CharSink charSink = Files.asCharSink(fileReusult, Charset.defaultCharset(), FileWriteMode.APPEND);
        charSink.writeLines(lineList);
    }
*/

    private static void getTargetRightLimitLine(File file) {
        getTargetLine(file, 2, "right_limit");
    }

    private static void getTargetPhoneLine(File file) {
          getTargetLine(file, 1, "phone");
      }

    private static void getTargetLine(File file, final int type, String resultName) {
        try {
            LineProcessor<List<String>> lineTargeProcessor = new LineProcessor<List<String>>() {
                List<String> lst = new ArrayList<String>();
                int lineIndex = 0;
                int succsCount = 0;
                int nullCount = 0;

                @Override
                public boolean processLine(String line) throws IOException {
                    lineIndex++;
                    String[] lines = line.split(",");
                    Iterable<String> lineItr = Splitter.on(",").split(line);
                    String phoneOrgin = Lists.newArrayList(lineItr).get(type);
                    if (StringUtils.isNotBlank(phoneOrgin)) {
                        phoneOrgin = phoneOrgin.trim().replace("\"", "");
                    } else {
                        nullCount++;
                    }
                    String phone = null;
                    if (type == 1) {
                        phone = DateFilter.checkPhone(phoneOrgin);
                    } else if (type == 2) {
                        phone = DateFilter.checkRigthLimit(phoneOrgin);
                    }

                    if (phone == null) {
                        //                    System.out.println("---error---" + line);
                        lst.add(line);
                    } else {
                        succsCount++;
                        //                    System.out.println("---error---" + line);
                        System.out.println("---success: {}-" + phoneOrgin + "----succsCount={}" + succsCount);

                    }
                    return true;
                }

                @Override
                public List<String> getResult() {
                    lst.add("lineIndex=" + lineIndex);
                    lst.add("nullCount=" + nullCount);
                    lst.add("succsCount+" + succsCount);
                    return lst;
                }
            };

            List<String> lineList = Files.readLines(file, Charsets.UTF_8, lineTargeProcessor);
            System.out.println("totalLine:" + lineList.get(lineList.size() - 3));
            System.out.println("failCount.size=" + (lineList.size() - 3));
            System.out.println("nullCount:" + lineList.get(lineList.size() - 2));
            System.out.println("succsCount:" + lineList.get(lineList.size() - 1));
            //           System.out.println("lineList=" + lineList);
            //在文件尾部追加写入内容
            File fileReusult = new File("D:\\test\\" + resultName + ".txt");
            CharSink charSink = Files.asCharSink(fileReusult, Charset.defaultCharset(), FileWriteMode.APPEND);
            charSink.writeLines(lineList);
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }
}
