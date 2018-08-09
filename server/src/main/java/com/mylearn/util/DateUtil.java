package com.mylearn.util;

import com.mylearn.util.enumtype.FieldErrorEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * User: Created by chenbaoan@360buy.com
 * Date: 2011-4-29
 * Time: 11:58:50.
 */
public class DateUtil {
    private final static Logger log = LogManager.getLogger(DateUtil.class);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    public static final String DEFAULT_DATE_FORMATE = "yyyymmddhh24mmss";
    public static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATE = "yyyy-MM";
   public static final String Date_MATCH = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$" ;
    /**
     * ??????????????????????????
     *
     * @param format ?????
     * @param date   ??????Date??
     * @return ?????????
     */
    public static synchronized String getDate2Str(String format, Date date) {
        simpleDateFormat.applyPattern(format);
        return null == date ? "" : simpleDateFormat.format(date);
    }

    /**
     * ????????????????????Date????
     *
     * @param format ?????
     * @param str    ?????????
     * @return ??????Date??
     */
    public static synchronized Date getStrToDate(String format, String str) {
        simpleDateFormat.applyPattern(format);
        ParsePosition parseposition = new ParsePosition(0);
        return simpleDateFormat.parse(str, parseposition);
    }

    public static String date2String(Date date) {
        return getDate2Str(DEFAULT_DATE_FORMATE, date);
    }

    public static String date2String(Date date, String format) {
        return getDate2Str(format, date);
    }

    /**
     * ??????????????
     *
     * @param dateTime ??????
     * @param pattern  Eg "yyyy-MM-dd"
     * @return ??????
     */
    public static boolean isDateTime(String dateTime, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        Date dt = df.parse(dateTime, pos);
        return !(dt == null);
    }

    public static XMLGregorianCalendar getXMLGregorianCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            DatatypeFactory dtf = DatatypeFactory.newInstance();
            xmlCalendar = dtf.newXMLGregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                    calendar.get(Calendar.SECOND), calendar.get(Calendar.MILLISECOND), calendar.get(Calendar.ZONE_OFFSET) / (1000 * 60));
        } catch (Exception e) {
            log.error("getXMLGregorianCalendar error!", e);
        }
        return xmlCalendar;
    }

    public boolean passTime(Date tempDate, int hour) {
        return !(tempDate == null || hour <= 0) && tempDate.before(getLimitDate(hour));
    }

    /**
     * ???n§³?????
     *
     * @param hour §³???
     * @return Date
     */
    private Date getLimitDate(int hour) {
        Calendar cl = Calendar.getInstance();
        Long clTemp = cl.getTimeInMillis() - hour * 60 * 60 * 1000;
        cl.setTimeInMillis(clTemp);
        return cl.getTime();
    }

    	/**
	* ???????Éì?????????????
	* ???????yyyy-MM-dd HH:mm:ss ????????????
	* @param inteval ???????????????????????
	* @return
	*/
	public static String resetNowDay(int inteval) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, inteval);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(cal.getTime());
		return time;
	}

    /**
     * ???????????????(???????????????2011-11-03??????????
     * ?????/?????I??-
     * @param date
     * @return
     */
    public static boolean  checkDateFormat(String date){
        if(StringUtils.isBlank(date)){
            return false;
        }
         return Pattern.matches(Date_MATCH, date);
    }

       // §µ????????
    private static String checkDateField(String data) {
       /* if (StringUtils.isNotBlank(data)) {
            Date tempDate = DateUtil.getStrToDate(DateUtil.DATE_FORMATE, data.replaceAll("/", "-"));
            if (tempDate == null) {
                return FieldErrorEnum.FORMAT_ERROR.getValue();
            }
            return FieldErrorEnum.SUCCESS.getValue();
        }*/
        if(StringUtils.isBlank(data)){
            return  FieldErrorEnum.NULL.getValue();
        }
        data =  data.replaceAll("/", "-");
        return DateUtil.checkDateFormat(data)?FieldErrorEnum.SUCCESS.getValue():FieldErrorEnum.FORMAT_ERROR.getValue();
    }


       public static Date getYesterDay() {
           return getNow().minusDays(1).toDate();
       }

       public static String getYesterDayStr() {
           return getNow().minusDays(1).toString(DEFAULT_DATE_FORMATE);
       }

       public static Date getTomorrow() {
           return getNow().plusDays(1).toDate();
       }

       public static DateTime getNow() {
           DateTime now = new DateTime();
           return now;
       }



       /**
        *   //   python?§Ö??????§Ö?????????????2 #????now.weekday()
             //   # ????? |????? ?????? ?????? ?????? ?????? ??????
        * @param dt
        * @return
        */
       public static int getWeekNumOfDateForPython(Date dt) {
           Calendar cal = Calendar.getInstance();
           cal.setTime(dt);
           int w = cal.get(Calendar.DAY_OF_WEEK) - 2;
           if (w < 0)
               w +=7;
           return w;
       }

       public static String getWeekOfDate(Date dt) {
           String[] weekDays = {"??????", "?????", "?????", "??????", "??????", "??????", "??????"};
           int w = getWeekNumOfDateForPython(dt);
           return weekDays[w];
       }

       public static int getWeekNumByZhName(String weekStr) {
           String[] weekDays = {"???", "???", "????", "????", "????", "????", "????",};
           for (int i = 0; i < weekDays.length; i++) {
               if (weekDays[i].equals(weekStr)) {
                   return i;
               }
           }
           return -1;
       }


    public static void main(String[] args) {
        System.out.println("---");
        System.out.println(DateUtil.checkDateField("2011-1-1"));
        System.out.println(DateUtil.checkDateField("2011-01-21"));
        System.out.println(DateUtil.checkDateField("2011-01-01"));
    }

}
