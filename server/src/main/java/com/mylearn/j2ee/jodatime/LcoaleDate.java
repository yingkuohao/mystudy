package com.mylearn.j2ee.jodatime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/7/29
 * Time: 下午3:25
 * CopyRight: taobao
 * Descrption:       http://blog.csdn.net/chenleixing/article/details/44408875/
 */

public class LcoaleDate {
    public static void main(String[] args) {

        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
        LocalDate date = LocalDate.parse(dateStr);
        boolean bool = date.minusDays(1).isBefore(LocalDate.now());



        long timestamp=  Instant.now().toEpochMilli();
        String s=  Instant.ofEpochMilli(timestamp).toString();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String dateTimeStr = LocalDateTime.now().format(formatter).toString();
        String dateTimeStr2 = LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE).toString();
        LocalDateTime date2 = LocalDateTime. parse(dateTimeStr,formatter);
               boolean bool2 = date2.minusDays(1).isBefore(LocalDateTime.now());


    }
}
