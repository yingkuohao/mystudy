package com.mylearn.j2ee.jodatime;


import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-12-31
 * Time: ????10:44
 * CopyRight:360buy
 * Descrption:
 * http://www.ibm.com/developerworks/cn/java/j-jodatime.html
 * joda-time
 * To change this template use File | Settings | File Templates.
 */
public class JodaTimeTest {
    public static void main(String args[]) {

        //1.????????????
        DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));

        //2.??????????
        DateTime now = DateTime.now();
        System.out.println(now.toString("yyyy-MM-dd HH:mm:ss"));

        //3.???????????????????????§»????
        int hour = now.get(DateTimeFieldType.clockhourOfDay());
        int dayOfYear = now.dayOfYear().get();
        int dayOfMonth = now.dayOfMonth().get();
        int dayOfWeek = now.dayOfWeek().get();
        System.out.println("hour:--" + hour + "dayOfYear:--" + dayOfYear + "--dayOfMonth:--" + dayOfMonth + "--dayOfWeek:--" + dayOfWeek);

        int hourOfDay = now.hourOfDay().get();
        int millisOfDay = now.millisOfDay().get();
        int millisOfSecond = now.millisOfSecond().get();
        int era = now.era().get();
        System.out.println("--hourOfDay:--" + hourOfDay + "millisOfDay:--" + millisOfDay + "--millisOfSecond:--" + millisOfSecond + "--era--" + era);

        //4. ?????????????
        DateTime yesterday = now.minusDays(1);
        DateTime tommorrow = now.plusDays(1);
        System.out.println("yesterday:" + yesterday.toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println("tommorrow:" + tommorrow.toString("yyyy-MM-dd HH:mm:ss"));

        //5. ?????????1?????1???????????????§³??10????????????
        DateTime aDate = now.minusYears(1).minusMonths(1).minusWeeks(1).minusDays(1).minusHours(1).minusMinutes(10).minusSeconds(1);
        System.out.println("aDate:" + aDate.toString("yyyy-MM-dd HH:mm:ss"));

        //5.1??minus?????plus??????????¦Ä4?????
        DateTime aFurtureTime = now.plusYears(1).plusMonths(1).plusWeeks(1).plusDays(1).plusHours(1).plusMinutes(10).plusSeconds(1);
        System.out.println("aFurtureTime:" + aFurtureTime.toString("yyyy-MM-dd HH:mm:ss"));

        //6. ???????
        boolean isBefore = now.isBefore(tommorrow);
        boolean isAfter = now.isAfter(yesterday);
        boolean isNow = now.isEqualNow();
        System.out.println("isBefore=" + isBefore + ",isAfter=" + isAfter + "isNow=" + isNow);

        //7. ?????java API?????????
        Calendar calendar = now.toCalendar(Locale.getDefault());
        System.out.println("calendare date:" + calendar.getTime());

        Date curDate = now.toDate();
        System.out.println("java.util.date=" + curDate);

        //8. ???????????
        DateTime curMonth = now.withMonthOfYear(10).withDayOfMonth(1).withHourOfDay(12).withMinuteOfHour(0).withSecondOfMinute(0);
        System.out.println("dateTime1:" + curMonth.toString("yyyy-MM-dd HH:mm:ss"));


    }
}
