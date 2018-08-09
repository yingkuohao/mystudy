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
 * Descrption:文件操作。
 * Guava提供了source（源）与sink（汇）两个api来处理I/O操作。
 * 字节           字符
 * 读： ByteSource   CharSource
 * 写： ByteSink     CharSink
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
        //使用Charsource来读取文件内容
        String path = "D:\\Project\\workspace\\study\\master\\mystudy\\server\\src\\main\\java\\com\\mylearn\\j2ee\\guava\\EventBusTest.java";
        File file = new File(path);
        CharSource charSource = Files.asCharSource(file, Charset.defaultCharset());
        String content = charSource.read();
        System.out.println("content=" + content);

        //读取所有的行， Files.readLines
        List<String> lines = Files.readLines(file, Charset.defaultCharset());
        System.out.println("Files.readLines:" + Joiner.on("\n").join(lines));

        //读取所有的行，通过  charSource.readLines
        ImmutableList<String> immutableList = charSource.readLines();
        System.out.println("charSource.readLines:" + Joiner.on("\n").join(immutableList));
        getTotalCount(file);
        getTargetStrLine(file);

        //在文件尾部追加写入内容
        CharSink charSink = Files.asCharSink(file, Charset.defaultCharset(), FileWriteMode.APPEND);
        List<String> toWrite = Lists.newArrayList("abc", "ddd");
        charSink.writeLines(toWrite);

        //拷贝
        File fileTocopy = new File("D:\\test\\test.txt");
        CharSink charSinkToCopy = Files.asCharSink(fileTocopy, Charset.defaultCharset());
        charSource.copyTo(charSinkToCopy);
        //获取去除了扩展名的文件名
        System.out.println("getNameWithoutExtension=" + Files.getNameWithoutExtension(path));
    }

    private static void getTotalCount(File file) throws IOException {
        //读取总行数
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
        //读取包含关键字的行
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
