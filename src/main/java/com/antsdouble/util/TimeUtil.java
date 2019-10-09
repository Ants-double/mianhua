package com.antsdouble.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/9
 */
public class TimeUtil {
    public static String getTimeStamp() {
        return Instant.now().toString();
    }


//    public static  LocalDateTime changeLocalDateTime(LocalDateTime localDateTime){
//        localDateTime.withMinute()
//    }

    //互转

    public static Date timeStampConvertDate(Instant instant) {
        return Date.from(instant);
    }

    public static Instant dateConvertTimeStamp(Date date) {
        return date.toInstant();
    }

    public static LocalDateTime localDateConvertLocalDateTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.of(0, 0, 0, 0));
    }

    public static LocalDate localDateTimeConvertLocalDate(LocalDateTime localDateTime) {
        return LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
    }

    public static LocalTime localDateTimeConvertLocalTime(LocalDateTime localDateTime) {
        return LocalTime.of(localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
    }

    public static LocalDateTime calendarConvertLocalDateTime(Calendar calendar) {
        return LocalDateTime.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
    }

    public static Calendar dateConvertCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    //字符串与对象互转

    public static String localDateTimeConvertString(LocalDateTime localDateTime, String timeFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeFormat);
        return localDateTime.format(dateTimeFormatter);
    }

    public static LocalDateTime stringConvertLocalDateTime(String localTime, String timeFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeFormat);
        return LocalDateTime.parse(localTime, dateTimeFormatter);
    }

    public static LocalDate stringConvertLocalDate(String localDate, String timeFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeFormat);
        return LocalDate.parse(localDate, dateTimeFormatter);
    }

    public static LocalTime stringConvertLocalTime(String localTime, String timeFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeFormat);
        return LocalTime.parse(localTime, dateTimeFormatter);
    }

    public static String localDateConvertString(LocalDate localDate, String timeFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeFormat);
        return localDate.format(dateTimeFormatter);
    }

    public static String localTimeConvertString(LocalTime localTime, String timeFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeFormat);
        return localTime.format(dateTimeFormatter);
    }

    /**
     * 功能描述 java 老的Api
     */

    public static String dateConvertString(Date date, String timeFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(timeFormat);
        return dateFormat.format(date);
    }

    public static Date stringConvertDate(String strDate, String timeFormat) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(timeFormat);
        return dateFormat.parse(strDate);

    }
}
