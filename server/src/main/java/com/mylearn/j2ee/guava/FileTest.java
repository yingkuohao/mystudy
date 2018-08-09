package com.mylearn.j2ee.guava;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.io.*;
import org.apache.commons.lang.StringUtils;
import sun.nio.cs.ext.GBK;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/4/29
 * Time: 11:24
 * CopyRight: taobao
 * Descrption:�ļ�������
 * Guava�ṩ��source��Դ����sink���㣩����api������I/O������
 * �ֽ�           �ַ�
 * ���� ByteSource   CharSource
 * д�� ByteSink     CharSink
 */

public class FileTest {
    public static void main(String args[]) {
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test() throws IOException {
        //ʹ��Charsource����ȡ�ļ�����
        String path = "D:\\Project\\workspace\\study\\master\\mystudy\\server\\src\\main\\java\\com\\mylearn\\j2ee\\guava\\EventBusTest.java";
        File file = new File(path);
        CharSource charSource = Files.asCharSource(file, Charset.defaultCharset());
        String content = charSource.read();
        System.out.println("content=" + content);

        //��ȡ���е��У� Files.readLines
        List<String> lines = Files.readLines(file, Charset.defaultCharset());
        System.out.println("Files.readLines:" + Joiner.on("\n").join(lines));

        //��ȡ���е��У�ͨ��  charSource.readLines
        ImmutableList<String> immutableList = charSource.readLines();
        System.out.println("charSource.readLines:" + Joiner.on("\n").join(immutableList));
        getTotalCount(file);
        getTargetStrLine(file);

        //���ļ�β��׷��д������
        CharSink charSink = Files.asCharSink(file, Charset.defaultCharset(), FileWriteMode.APPEND);
        List<String> toWrite = Lists.newArrayList("abc", "ddd");
        charSink.writeLines(toWrite);

        //����
        File fileTocopy = new File("D:\\test\\test.txt");
        CharSink charSinkToCopy = Files.asCharSink(fileTocopy, Charset.defaultCharset());
        charSource.copyTo(charSinkToCopy);
        //��ȡȥ������չ�����ļ���
        System.out.println("getNameWithoutExtension=" + Files.getNameWithoutExtension(path));
    }

    private static void getTotalCount(File file) throws IOException {
        //��ȡ������
        Integer lineCount = Files.readLines(file, Charset.defaultCharset(), new LineProcessor<Integer>() {
            int lineCount = 0;

            @Override
            public boolean processLine(String line) throws IOException {
                lineCount++;
                return true;
            }

            @Override
            public Integer getResult() {
                return lineCount;
            }
        });
        System.out.println("lineCount=" + lineCount);
    }

    private static void getTargetStrLine(File file) throws IOException {
        //��ȡ�����ؼ��ֵ���
        final String targetStr = "com";
        LineProcessor<List<Integer>> lineTargeProcessor = new LineProcessor<List<Integer>>() {
            List<Integer> lst = new ArrayList<Integer>();
            int lineIndex = -1;

            @Override
            public boolean processLine(String line) throws IOException {
                lineIndex++;
                if (line.contains(targetStr)) {
                    lst.add(lineIndex);
                }
                return true;
            }

            @Override
            public List<Integer> getResult() {
                return lst;
            }
        };
        List<Integer> lineList = Files.readLines(file, Charset.defaultCharset(), lineTargeProcessor);
        System.out.println("lineList=" + lineList);
    }
}
