package com.mylearn.java8.date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import javax.swing.text.ZoneView;
import java.time.*;
import java.util.Date;

import static java.time.ZonedDateTime.of;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/11/9
 * Time: 下午3:08
 * CopyRight: taobao
 * Descrption:
 */

public class DateTest {


    // 默认时间格式
    private static final String TIME_FMROAT = "yyyyMMdd";

    private final static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");

    public static void main(String[] args) {
        Date newDate = java.sql.Date.valueOf(LocalDate.now());
               System.out.println("newDate=" + newDate);
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd_HH");
        String dateTime1 = LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HH"));
        String dateTime2 = new DateTime(Instant.now().getEpochSecond()).toString(dateTimeFormatter);
        int hour = LocalDateTime.now().getHour();


        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);


        String dateTime = new DateTime(Instant.now().getEpochSecond()).toString(formatter);
        ZonedDateTime zoned_now = ZonedDateTime.of(LocalDateTime.now(),
                ZoneId.of("UTC+08:00"));
        System.out
                .println(zoned_now.withZoneSameInstant(ZoneId.of("UTC+00:00")));
        System.out.println(zoned_now.getOffset());
        System.out.println("");

        DateTest dateTest = new DateTest();
        dateTest.LocalDateTimeToUdate();
        dateTest.LocalDateToUdate();
        dateTest.LocalTimeToUdate();
        dateTest.UDateToLocalDate();
        dateTest.UDateToLocalDateTime();
        dateTest.UDateToLocalTime();

/*        LocalDate ye = LocalDate.now().minusDays(1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIME_FMROAT);
        String s = dateTimeFormatter.format(ye);*/
    }

    // 01. java.util.Date --> java.time.LocalDateTime
    public void UDateToLocalDateTime() {
        java.util.Date date = new java.util.Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
    }

    // 02. java.util.Date --> java.time.LocalDate
    public void UDateToLocalDate() {
        java.util.Date date = new java.util.Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
    }

    // 03. java.util.Date --> java.time.LocalTime
    public void UDateToLocalTime() {
        java.util.Date date = new java.util.Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalTime localTime = localDateTime.toLocalTime();
    }

    // 04. java.time.LocalDateTime --> java.util.Date
    public void LocalDateTimeToUdate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);
    }

    // 05. java.time.LocalDate --> java.util.Date
    public void LocalDateToUdate() {
        LocalDate localDate = LocalDate.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);
    }

    // 06. java.time.LocalTime --> java.util.Date
    public void LocalTimeToUdate() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);
    }


    public void getDate() {
        Date newDate = java.sql.Date.valueOf(LocalDate.now());
        System.out.println("newDate=" + newDate);
    }
}
