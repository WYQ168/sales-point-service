package com.sales.common.core.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理工具列
 */
public abstract class DateHandleUtils {
    // 秒、分、时、天的毫秒数
    public static final long SEC_MILLIS = 1000L;
    public static final long MIN_MILLIS = 60 * SEC_MILLIS;
    public static final long HOUR_MILLIS = 60 * MIN_MILLIS;
    public static final long DAY_MILLIS = 24 * HOUR_MILLIS;
    // 日期/时间格式
    public static final String DATETIME_PATTERN_T = "yyyy-MM-dd'T'HH:mm:ssX";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String YM_PATTERN = "yyyy-MM";
    public static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("Asia/Shanghai");
    public static final String HOUR_INT = "HHmmss";
    public static final String YYYY_PATTERN = "yyyy";

    private static final String YYYY = "yyyy";
    private static final String MM = "MM";
    private static final String DD = "dd";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YYYY_MM = "yyyy-MM";
    private static final String HH_MM_SS = "HH:mm:ss";
    public static final String HH_MM = "HH:mm";
    public static final String YYYYMMDDHHMMSS_SSS = "yyyyMMddHHmmssSSS";
    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDD = "yyyyMMdd";

    /**
     * Get current date
     *
     * @return <code>Date</code>
     */
    public static synchronized Date getCurrDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * Get current date and convert to a String in yyyy-MM-dd pattern.
     *
     * @return <code>String</code> in yyyy-MM-dd pattern
     */
    public static final String getCurrDateStr() {
        return format(getCurrDate(), YYYY_MM_DD);
    }

    /**
     * Get current date and convert to a String in yyyy-MM pattern.
     *
     * @return <code>String</code> in yyyy-MM pattern
     */
    public static final String getCurrMonthStr() {
        return format(getCurrDate(), YYYY_MM);
    }

    /**
     * Get current time and convert to a String in HH:mm:ss pattern.
     *
     * @return <code>String</code> in hh:mm:ss pattern
     */
    public static final String getCurrTimeStr() {
        return format(getCurrDate(), HH_MM_SS);
    }

    /**
     * Get current date and time and convert to a String in yyyy－MM－dd hh:mm:ss
     * pattern.
     *
     * @return <code>String</code> in yyyy-MM-dd hh:mm:ss pattern
     */
    public static final String getCurrDateTimeStr() {
        return format(getCurrDate(), DATETIME_PATTERN);
    }

    /**
     * Get current year and convert to a String in yyyy pattern.
     *
     * @return Year
     */
    public static final String getYear() {
        return format(getCurrDate(), YYYY);
    }

    /**
     * Get current month and convert to a String in MM pattern.
     *
     * @return Month
     */
    public static final String getMonth() {
        return format(getCurrDate(), MM);
    }

    /**
     * Get current day and convert to a String in dd pattern.
     *
     * @return Day
     */
    public static final String getDay() {
        return format(getCurrDate(), DD);
    }

    /**
     * Get current Hour and convert to a String in dd pattern.
     *
     * @return Day
     */
    public static final int getHour() {
        return Integer.valueOf(format(getCurrDate(), "HH"));
    }

    /**
     * Validate date string
     *
     * @param strDate
     * @param pattern
     * @return boolean
     */
    public static final boolean isDate(String strDate, String pattern) {
        return parseDate(strDate, pattern) != null;
    }

    /**
     * 判断给定字符串是否为特定格式年份（格式：yyyy）数据
     *
     * @param strDate 要判断的日期
     * @return true 如果是，否则返回false
     */
    public static final boolean isYYYY(String strDate) {
        return parseDate(strDate, YYYY) != null;
    }

    /**
     * 判断给定字符串是否为特定格式年份（格式：yyyy-MM）数据
     *
     * @param strDate 要判断的日期
     * @return true 如果是，否则返回false
     */
    public static final boolean isYYYY_MM(String strDate) {
        return parseDate(strDate, YYYY_MM) != null;
    }

    /**
     * 判断给定字符串是否为特定格式的年月日（格式：yyyy-MM-dd）数据
     *
     * @param strDate 要判断的日期
     * @return true 如果是，否则返回false
     */
    public static final boolean isYYYY_MM_DD(String strDate) {
        return parseDate(strDate, YYYY_MM_DD) != null;
    }

    /**
     * 判断给定字符串是否为特定格式年月日时分秒（格式：yyyy-MM-dd HH:mm:ss）数据
     *
     * @param strDate 要判断的日期
     * @return true 如果是，否则返回false
     */
    public static final boolean isYYYY_MM_DD_HH_MM_SS(String strDate) {
        return parseDate(strDate, DATETIME_PATTERN) != null;
    }

    /**
     * 判断给定字符串是否为特定格式时分秒（格式：HH:mm:ss）数据
     *
     * @param strDate 要判断的日期
     * @return true 如果是，否则返回false
     */
    public static final boolean isHH_MM_SS(String strDate) {
        return parseDate(strDate, HH_MM_SS) != null;
    }

    /**
     * 获取给定日前的后intevalDay天的日期
     *
     * @param refenceDate 给定日期（格式为：yyyy-MM-dd）
     * @param intevalDays 间隔天数
     * @return 计算后的日期
     */
    public static final String getNextDate(String refenceDate, int intevalDays) {
        try {
            return getNextDate(parseDate(refenceDate, YYYY_MM_DD), intevalDays);
        } catch (Exception ee) {
            return null;
        }
    }

    /**
     * 获取给定日前的后intevalDay天的日期
     *
     * @param refenceDate 给定日期
     * @param intevalDays 间隔天数
     * @return String 计算后的日期
     */
    public static final String getNextDate(Date refenceDate, int intevalDays) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + intevalDays);
            return format(calendar.getTime(), YYYY_MM_DD);
        } catch (Exception ee) {
            return null;
        }
    }

    /**
     * 获取给定日前的后intevalDay天的日期
     *
     * @param refenceDate 给定日期
     * @param intevalDays 间隔天数
     * @return String 计算后的日期
     */
    public static final String getNextDate(Date refenceDate, int intevalDays, String pattern) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + intevalDays);
            return format(calendar.getTime(), pattern);
        } catch (Exception ee) {
            return null;
        }
    }

    /**
     * 获取给定日前的后intevalDay天的日期
     *
     * @param refenceDate 给定日期
     * @param intevalDays 间隔天数
     * @return String 计算后的日期
     */
    public static final Date getNextDates(Date refenceDate, int intevalDays) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + intevalDays);
            return calendar.getTime();
        } catch (Exception ee) {
            return null;
        }
    }

    /**
     * 返回两个日期间的间隔天数
     *
     * @param startDate represent a date value
     * @param endDate   represent a date value
     * @return 两个日期之间的间隔天数，正数表示起始日期在结束日期之后，0表示两个日期同天，负数表示起始日期在结束日期之前
     */
    public static final long getIntevalDays(String startDate, String endDate) {
        try {
            return getIntevalDays(parseDate(startDate, YYYY_MM_DD), parseDate(endDate, YYYY_MM_DD));
        } catch (Exception ee) {
            return 0l;
        }
    }

    /**
     * 返回两个日期间的间隔天数
     *
     * @param startDate
     * @param endDate
     * @return 两个日期之间的间隔天数，正数表示起始日期在结束日期之后，0表示两个日期同天，负数表示起始日期在结束日期之前
     */
    public static final int getIntevalDays(Date startDate, Date endDate) {
        try {
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);
            long diff = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
            return (int) (diff / DAY_MILLIS);
        } catch (Exception ee) {
            return 0;
        }
    }

    /**
     * 返回两个日期间的间隔分钟
     *
     * @param startDate
     * @param endDate
     * @return 两个日期之间的间隔天数，正数表示起始日期在结束日期之后，0表示两个日期同天，负数表示起始日期在结束日期之前
     */
    public static final int getIntevalMinute(Date startDate, Date endDate) {
        try {
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);
            long diff = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
            return (int) (diff / MIN_MILLIS);
        } catch (Exception ee) {
            return 0;
        }
    }

    /**
     * 求当前日期和指定字符串日期的相差天数
     *
     * @param startDate
     * @return 两个日期之间的间隔天数，正数表示起始日期在结束日期之后，0表示两个日期同天，负数表示起始日期在结束日期之前
     */
    public static final long getTodayIntevalDays(String startDate) {
        try {
            Date currentDate = new Date();
            Date theDate = parseDate(startDate);
            long days = (currentDate.getTime() - theDate.getTime()) / DAY_MILLIS;
            return days;
        } catch (Exception ee) {
            return 0l;
        }
    }

    /**
     * 获得指定年月的最后一天
     *
     * @param year
     * @param month
     * @return
     * @throws ParseException
     */
    public static final String getLastDayOfMonth(String year, String month) throws ParseException {
        String LastDay = "";
        Calendar cal = Calendar.getInstance();
        Date date_;
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-14");
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        date_ = cal.getTime();
        LastDay = new SimpleDateFormat("yyyy-MM-dd").format(date_);
        return LastDay;
    }

    /**
     * @param year
     * @param month
     * @param day
     * @return 输入的年、月、日是否是有效日期
     */
    public static final boolean isValid(int year, int month, int day) {
        if (month > 0 && month < 13 && day > 0 && day < 32) {
            // month of calendar is 0-based
            int mon = month - 1;
            Calendar calendar = new GregorianCalendar(year, mon, day);
            return calendar.get(Calendar.YEAR) == year && calendar.get(Calendar.MONTH) == mon
                    && calendar.get(Calendar.DAY_OF_MONTH) == day;
        }
        return false;
    }

    private static final Calendar convert(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 返回指定年数位移后的日期
     */
    public static final Date yearOffset(Date date, int offset) {
        return offsetDate(date, Calendar.YEAR, offset);
    }

    /**
     * 返回指定月数位移后的日期
     */
    public static final Date monthOffset(Date date, int offset) {
        return offsetDate(date, Calendar.MONTH, offset);
    }

    /**
     * 返回指定天数位移后的日期
     */
    public static final Date dayOffset(Date date, int offset) {
        return offsetDate(date, Calendar.DATE, offset);
    }

    /**
     * 返回指定时间位移后的小时
     *
     * @param date
     * @param offset 支持正负数
     * @return
     */
    public static final Date hourOffset(Date date, int offset) {
        return offsetDate(date, Calendar.HOUR_OF_DAY, offset);
    }

    /**
     * 返回指定时间位移后的分钟
     *
     * @param date
     * @param offset 支持正负数
     * @return
     */
    public static final Date minuteOffset(Date date, int offset) {
        return offsetDate(date, Calendar.MINUTE, offset);
    }

    /**
     * 返回指定时间位移后的数秒
     *
     * @param date
     * @param offset 支持正负数
     * @return
     */
    public static final Date secondOffset(Date date, int offset) {
        return offsetDate(date, Calendar.SECOND, offset);
    }

    /**
     * 返回指定日期相应位移后的日期
     *
     * @param date   参考日期
     * @param field  位移单位，见 {@link Calendar}
     * @param offset 位移数量，正数表示之后的时间，负数表示之前的时间
     * @return 位移后的日期
     */
    public static final Date offsetDate(Date date, int field, int offset) {
        Calendar calendar = convert(date);
        calendar.add(field, offset);
        return calendar.getTime();
    }

    /**
     * Get first day of the month which the specified date belong to
     */
    public static final Date firstDay(Date date) {
        Calendar calendar = convert(date);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * Get last day of the month which the specified date belong to
     */
    public static final Date lastDay(Date date) {
        Calendar calendar = convert(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * <p>
     * Checks if two calendar objects are on the same day ignoring time.
     * </p>
     * <p>
     * <p>
     * 28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true. 28 Mar 2002 13:45
     * and 12 Mar 2002 13:45 would return false.
     * </p>
     *
     * @param startDate the first calendar, not altered, not null
     * @param endDate   the second calendar, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either calendar is <code>null</code>
     * @since 2.1
     */
    public static final boolean isSameDay(Calendar startDate, Calendar endDate) {
        if (startDate == null && endDate == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (startDate == null || endDate == null) {
            return false;
        }
        return (startDate.get(Calendar.ERA) == endDate.get(Calendar.ERA)
                && startDate.get(Calendar.YEAR) == endDate.get(Calendar.YEAR)
                && startDate.get(Calendar.DAY_OF_YEAR) == endDate.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * Check whether the range between startDate and endDate exceed months
     * <p>
     *
     * <pre>
     * DateUtils.isInMonth(2012-01-31, 2012-02-28, 2) = false
     * DateUtils.isInMonth(2012-01-31, 2012-03-30, 2) = false
     * DateUtils.isInMonth(2012-01-31, 2012-03-31, 2) = true
     * </pre>
     */
    public static final boolean isInMonth(Date startDate, Date endDate, int months) {
        if (startDate == null && endDate == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (startDate == null || endDate == null) {
            return false;
        }
        Date tmpDate = org.apache.commons.lang3.time.DateUtils.addMonths(startDate, months);
        return getIntevalDays(tmpDate, endDate) >= 0;
    }

    /**
     * Check whether two date are in same day
     *
     * @param date1
     * @param date2
     * @return
     */
    public static final boolean isSameDay(Date date1, Date date2) {
        if ((date1 != null && date2 == null) || (date1 == null && date2 != null)) {
            return false;
        }
        return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
    }

    /**
     * Parse date from string
     *
     * @param date the string represent a date value
     * @return null if string is not in yyyy-MM-dd format
     */
    public static final Date parseDate(String date) {
        return parseDate(date, DATE_PATTERN);
    }

    /**
     * Parse datetime from string
     *
     * @param datetime the string represent a datetime value
     * @return null if string is not in yyyy-MM-dd HH:mm:ss format
     */
    public static final Date parseDatetime(String datetime) {
        return parseDate(datetime, DATETIME_PATTERN);
    }

    /**
     * Parse date from string according pattern
     *
     * @param date    the string represent a date value
     * @param pattern the string represent a date format
     * @return null if string is not in pattern or pattern is not a valid value
     */
    public static final Date parseDate(String date, String pattern) {
        if (org.apache.commons.lang3.StringUtils.isBlank(date))
            return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final int getHours(Date date) {
        return getHours(date, DEFAULT_TIME_ZONE);
    }

    public static final int getHours(Date date, TimeZone timeZone) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static final long getMinutes(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * Format a Date to String in yyyy-MM-dd pattern.
     *
     * @param date
     * @return String
     */
    public static final String formatDate(Date date) {
        if (date == null)
            return null;
        return format(date, DATE_PATTERN);
    }

    /**
     * Format a Date to String in yyyy-MM-dd mm:HH:ss pattern.
     *
     * @param date
     * @return
     */
    public static final String formatDatetime(Date date) {
        return format(date, DATETIME_PATTERN);
    }

    /**
     * @param date
     * @return 指定日期对应月份的第1天开始时间
     */
    public static final Date getMonthFirstDateBeginTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获得指定日期的起始时间
     *
     * @param date
     * @return
     */
    public static final Date getStartTime(Date date) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 月头开始时间
     *
     * @param date
     * @return
     */
    public static final Date getMonthStartTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 月头结束时间
     *
     * @param date
     * @return
     */
    public static final Date getMonthEndTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59,
                59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得指定日期的结束时间
     *
     * @param date
     * @return
     */
    public static final Date getEndTime(Date date) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return cal.getTime();
    }

    /**
     * 获取当天剩余时间(秒)
     *
     * @return
     */
    public static final long getCurrDayRemainTime() {
        Date now = new Date();
        // (今天的结束时间 - 当前时间) / 1000
        return (getEndTime(now).getTime() - now.getTime()) / 1000;
    }

    /**
     * 获得当天的起始时间
     *
     * @return
     */
    public static final Date today() {
        return getStartTime(new Date());
    }

    /**
     * 日期格式化为字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static final String format(Date date, String pattern) {
        if (date == null)
            return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 日期格式转换
     *
     * @param dateFormat
     * @return
     */
    public static String transDate(String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);// 设置日期格式
        String date = df.format(new Date());
        return date;
    }

    /**
     * 日期格式化为字符串，yyyy-MM-dd HH:mm:ss
     *
     * @param calendar
     * @return
     */
    public static final String format(Calendar calendar) {
        return format(calendar, DATETIME_PATTERN);
    }

    public static final String format(Calendar calendar, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(calendar.getTime());
    }

    public static final int getWeekOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return w;
    }

    /**
     * 获取日期所在月最后一天
     *
     * @param date
     * @return
     */
    public static final Date getMonthLastDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 获取本周的开始时间
     *
     * @return
     */
    @SuppressWarnings("unused")
    public static Date getBeginDayOfWeek() {
        return getBeginDayOfWeek(new Date());
    }

    public static Date getBeginDayOfWeek(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取本周的结束时间
     *
     * @return
     */
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    public static Date getEndDayOfWeek(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek(data));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    /**
     * 获取上周的开始时间
     *
     * @return
     */
    @SuppressWarnings("unused")
    public static Date getBeginDayOfLastWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取上周的结束时间
     *
     * @return
     */
    public static Date getEndDayOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    /**
     * 获取上月的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取上月的结束时间
     *
     * @return
     */
    public static Date getEndDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 2, day);
        return getDayEndTime(calendar.getTime());
    }

    /**
     * 获取上个自然季度开始时间
     *
     * @return
     */
    public static Date getBeginDayOfLastSeason() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month >= 1 && month <= 3) {
            calendar.add(Calendar.YEAR, -1);
            calendar.set(Calendar.MONTH, Calendar.OCTOBER);
            calendar.set(Calendar.DATE, 1);
        } else if (month >= 4 && month <= 6) {
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            calendar.set(Calendar.DATE, 1);
        } else if (month >= 7 && month <= 9) {
            calendar.set(Calendar.MONTH, Calendar.APRIL);
            calendar.set(Calendar.DATE, 1);
        } else {
            calendar.set(Calendar.MONTH, Calendar.JULY);
            calendar.set(Calendar.DATE, 1);
        }
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取上个自然季度结束时间
     *
     * @return
     */
    public static Date getEndDayOfLastSeason() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month >= 1 && month <= 3) {
            calendar.add(Calendar.YEAR, -1);
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.DATE, 31);
        } else if (month >= 4 && month <= 6) {
            calendar.set(Calendar.MONTH, Calendar.MARCH);
            calendar.set(Calendar.DATE, 31);
        } else if (month >= 7 && month <= 9) {
            calendar.set(Calendar.MONTH, Calendar.JUNE);
            calendar.set(Calendar.DATE, 30);
        } else {
            calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
            calendar.set(Calendar.DATE, 30);
        }
        return getDayEndTime(calendar.getTime());
    }

    /**
     * 获取本季度开始时间
     *
     * @return
     */
    public static Date getBeginDayOfSeason() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month >= 1 && month <= 3) {
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
        } else if (month >= 4 && month <= 6) {
            calendar.set(Calendar.MONTH, Calendar.APRIL);
        } else if (month >= 7 && month <= 9) {
            calendar.set(Calendar.MONTH, Calendar.JULY);
        } else {
            calendar.add(Calendar.YEAR, -1);
            calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        }
        calendar.set(Calendar.DATE, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取本季度结束时间
     *
     * @return
     */
    public static Date getEndDayOfSeason() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month >= 1 && month <= 3) {
            calendar.set(Calendar.MONTH, Calendar.MARCH);
            calendar.set(Calendar.DATE, 31);
        } else if (month >= 4 && month <= 6) {
            calendar.set(Calendar.MONTH, Calendar.JUNE);
            calendar.set(Calendar.DATE, 30);
        } else if (month >= 7 && month <= 9) {
            calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
            calendar.set(Calendar.DATE, 30);
        } else {
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.DATE, 31);
        }
        return getDayEndTime(calendar.getTime());
    }

    /**
     * 获取去年的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfLastYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -1);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取去年的结束时间
     *
     * @return
     */
    public static Date getEndDayOfLastYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -1);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DATE, 31);
        return getDayEndTime(calendar.getTime());
    }

    /**
     * 获取某个日期的结束时间
     *
     * @param d
     * @return
     */
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23,
                59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某个日期的开始时间
     *
     * @param d
     * @return
     */
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,
                0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取本月的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取本月的结束时间
     *
     * @return
     */
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    /**
     * 获取今年是哪一年
     *
     * @return
     */
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    /**
     * 获取本月是哪一月
     *
     * @return
     */
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    /**
     * 是否在指定的时间范围
     *
     * @param now
     * @param start
     * @param end
     * @return
     */
    public static boolean dateIsBetween(Date now, Date start, Date end) {
        return now.after(start) && end.after(now);
    }

    /**
     * 是否在指定的时间范围
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean nowIsBetween(Date start, Date end) {
        Date now = new Date();
        return now.after(start) && end.after(now);
    }

    /**
     * 判断两个时间区间是否有交集的方法
     * <p>
     * date1_1 should not be great than date1_2 and date2_1 should not be great than
     * date2_2
     * </p>
     *
     * @param date1_1            区间1的时间始
     * @param date1_2            区间1的时间止
     * @param date2_1            区间2的时间始
     * @param date2_2            区间2的时间止
     * @return 区间1和区间2如果存在交集, 则返回true, 否则返回falses
     */
    public static boolean isDateCross(Date date1_1, Date date1_2, Date date2_1, Date date2_2) {
        boolean flag = false;// 默认无交集
        long l1_1 = date1_1.getTime();
        long l1_2 = date1_2.getTime();
        long l2_1 = date2_1.getTime();
        long l2_2 = date2_2.getTime();

        if (((l1_1 <= l2_1) && (l2_1 <= l1_2)) || ((l1_1 <= l2_2) && (l2_2 <= l1_2))
                || ((l2_1 <= l1_1) && (l1_1 <= l2_2)) || ((l2_1 <= l1_2) && (l1_2 <= l2_2))) {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取去年的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取去年的结束时间
     *
     * @return
     */
    public static Date getEndDayOfCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DATE, 31);
        return getDayEndTime(calendar.getTime());
    }


    public static List<String> getMonthBetween(Date minDate, Date maxDate) {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(minDate);
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(maxDate);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /**
     * 根据月份返回当月每天日期
     *
     * @param year
     * @param month
     * @return
     */
    public static List<Integer> getDayByMonth(int year, int month) {
        List<Integer> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int day = getMonthMaxNum(year, month);
        for (int i = 1; i <= day; i++) {
            list.add(i);
        }
        return list;
    }

    public static int getMonthMaxNum(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        return cal.get(Calendar.DATE);
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static final String dateToWeek(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 根据年月日计算年龄
     *
     * @param birthTimeString 2020-09-09
     * @return 年龄
     */

    public static int getAgeFromBirthTime(String birthTimeString) {
        // 先截取到字符串中的年、月、日
        String strs[] = birthTimeString.trim().split("-");
        int selectYear = Integer.parseInt(strs[0]);
        int selectMonth = Integer.parseInt(strs[1]);
        int selectDay = Integer.parseInt(strs[2]);
        // 得到当前时间的年、月、日
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayNow = cal.get(Calendar.DATE);

        // 用当前年月日减去生日年月日
        int yearMinus = yearNow - selectYear;
        int monthMinus = monthNow - selectMonth;
        int dayMinus = dayNow - selectDay;

        int age = yearMinus;// 先大致赋值
        if (yearMinus < 0) {// 选了未来的年份
            age = 0;
        } else if (yearMinus == 0) {// 同年的，要么为1，要么为0
            if (monthMinus < 0) {// 选了未来的月份
                age = 0;
            } else if (monthMinus == 0) {// 同月份的
                if (dayMinus < 0) {// 选了未来的日期
                    age = 0;
                } else if (dayMinus >= 0) {
                    age = 1;
                }
            } else if (monthMinus > 0) {
                age = 1;
            }
        } else if (yearMinus > 0) {
            if (monthMinus < 0) {// 当前月>生日月
            } else if (monthMinus == 0) {// 同月份的，再根据日期计算年龄
                if (dayMinus < 0) {
                } else if (dayMinus >= 0) {
                    age = age + 1;
                }
            } else if (monthMinus > 0) {
                age = age + 1;
            }
        }
        return age;
    }
}
