//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.com.topspider.test;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

public final class DateTime {
    public static final String DATE_SEPARATOR = "-";
    public static final String TIME_SEPARATOR = ":";
    public static final String DATE_TIME_SEPARATOR = " ";
    public static final String DATE_FORMAT_STR = "yyyy-MM-dd";
    public static final String TIME_FORMAT_STR = "HH:mm:ss";
    public static final String TIMESTAMP_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String MSEL_FORMAT_STR_1 = "yyyy-MM-dd HH:mm:ss,SSS";
    public static final String MSEL_FORMAT_STR_2 = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final int DATE_FORMAT_STR_LENGTH = 10;
    public static final int TIMESTAMP_STR_LENGTH = 19;
    public static final int MSEL_FORMAT_STR_LENGTH = 23;
    public static final long MILLIS_IN_ONE_MIN = 60000L;
    public static final long MILLIS_IN_ONE_HOUR = 3600000L;
    public static final long MILLIS_IN_ONE_DAY = 86400000L;
    public static final int YEAR = 1;
    public static final int MONTH = 2;
    public static final int DAY = 3;
    public static final int HOUR = 4;
    public static final int MINUTE = 5;
    public static final int SECOND = 6;
    public static final int MILLISECOND = 7;
    public static final int QUATER = 11;
    public static final int WEEK = 12;
    public static final int DAYS_OF_MONTH = 13;
    public static final int WEEKS_OF_MONTH = 14;
    public static final int DAYS_OF_YEAR = 15;
    public static final int WEEKS_OF_YEAR = 16;
    public static final int[] DAYS = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public DateTime() {
    }

    public static Date toDate(java.sql.Date date) {
        return new Date(date.getTime());
    }

    public static Date toDate(Timestamp timestamp) {
        return timestamp;
    }

    public static Date toDate(Object obj) {
        if (obj == null) {
            return null;
        } else if (!(obj instanceof Date) && !(obj instanceof java.sql.Date) && !(obj instanceof Timestamp)) {
            if (obj instanceof String) {
                return toDate(obj.toString());
            } else {
                throw new RuntimeException("the parameter [" + obj + " can't be converted to java.util.Date");
            }
        } else {
            return (Date)obj;
        }
    }

    public static java.sql.Date toSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.sql.Date toSqlDate(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj instanceof java.sql.Date) {
            return (java.sql.Date)obj;
        } else {
            Date date = toDate(obj);
            return new java.sql.Date(date.getTime());
        }
    }

    public static Timestamp toTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static Timestamp toTimestamp(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj instanceof Timestamp) {
            return (Timestamp)obj;
        } else {
            Date date = toDate(obj);
            return new Timestamp(date.getTime());
        }
    }

    public static Time toTime(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj instanceof Time) {
            return (Time)obj;
        } else if (obj instanceof Timestamp) {
            return new Time(((Timestamp)obj).getTime());
        } else if (obj instanceof java.sql.Date) {
            return new Time(((Timestamp)obj).getTime());
        } else if (obj instanceof Date) {
            return new Time(((Date)obj).getTime());
        } else if (obj instanceof String) {
            return Time.valueOf(obj.toString());
        } else {
            throw new IllegalArgumentException("the parameter {@obj[" + obj + "]} is a bad Argument, which can't be converted to java.sql.Time.");
        }
    }

    public static Date now() {
        return new Date(System.currentTimeMillis());
    }

    public static String nowIdentity() {
        return getFormat(now(), "yyyyMMddHHmmssSSS");
    }

    public static Long nowTimeFormat() {
        return Long.parseLong(getFormat(now(), "yyyyMMddHHmmss"));
    }

    public static Long nowTimeFormatNew() {
        return Long.parseLong(getFormat(now(), "yyyyMMdd"));
    }

    public static String getDateFormat(Date date) {
        return getFormat(date, "yyyy-MM-dd");
    }

    public static String getTimeFormat(Date date) {
        return getFormat(date, "HH:mm:ss");
    }

    public static String getTimeStampFormat(Date date) {
        return getFormat(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String currentDate() {
        return getDateFormat(now());
    }

    public static String currentTime() {
        return getTimeFormat(now());
    }

    public static String currentTimeStamp() {
        return getTimeStampFormat(now());
    }

    public static String currentMselFormat() {
        return getFormat(now(), "yyyy-MM-dd HH:mm:ss,SSS");
    }

    public static String getFormat(Date date, String parseFormat) {
        if (date == null) {
            return null;
        } else {
            return parseFormat != null && !"".equalsIgnoreCase(parseFormat) ? FastDateFormat.getInstance(parseFormat).format(date) : date.toString();
        }
    }

    public static long getIntervalDays(Calendar startday, Calendar endday) {
        if (startday.after(endday)) {
            Calendar cal = startday;
            startday = endday;
            endday = cal;
        }

        long sl = startday.getTimeInMillis();
        long el = endday.getTimeInMillis();
        long cl = el - sl;
        return cl / 86400000L;
    }

    public static long getIntervalDays(Date startday, Date endday) {
        if (startday.after(endday)) {
            Date cal = startday;
            startday = endday;
            endday = cal;
        }

        long sl = startday.getTime();
        long el = endday.getTime();
        long cl = el - sl;
        return cl / 86400000L;
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static Date backward(Date date, long millis) {
        Date d = new Date();
        d.setTime(date.getTime() - millis);
        return d;
    }

    public static Date forward(Date date, long millis) {
        Date d = new Date();
        d.setTime(date.getTime() + millis);
        return d;
    }

    public static int get(Date date, int field) throws RuntimeException {
        if (date == null) {
            throw new RuntimeException("date is null");
        } else {
            GregorianCalendar cal = toCalendar(date);
            switch(field) {
                case 1:
                    return cal.get(1);
                case 2:
                    return cal.get(2) + 1;
                case 3:
                    return cal.get(5);
                case 4:
                    return cal.get(11);
                case 5:
                    return cal.get(12);
                case 6:
                    return cal.get(13);
                case 7:
                    return cal.get(14);
                case 8:
                case 9:
                case 10:
                case 11:
                default:
                    throw new RuntimeException("invalid date field " + field);
                case 12:
                    return (cal.get(7) - 2 + 7) % 7;
                case 13:
                    return cal.getActualMaximum(5);
                case 14:
                    return cal.getActualMaximum(4);
                case 15:
                    return cal.getActualMaximum(6);
                case 16:
                    return cal.getActualMaximum(3);
            }
        }
    }

    public static Date add(Date date, int field, int amount) {
        Calendar cal = toCalendar(date);
        byte nCalendarField;
        switch(field) {
            case 1:
                nCalendarField = 1;
                break;
            case 2:
                nCalendarField = 2;
                break;
            case 3:
                nCalendarField = 6;
                break;
            case 4:
                nCalendarField = 10;
                break;
            case 5:
                nCalendarField = 12;
                break;
            case 6:
                nCalendarField = 13;
                break;
            case 7:
                nCalendarField = 14;
                break;
            case 8:
            case 9:
            case 10:
            case 11:
            default:
                throw new RuntimeException("invalid date time field: " + field);
            case 12:
                nCalendarField = 3;
        }

        cal.add(nCalendarField, amount);
        return cal.getTime();
    }

    public Date add(Date date, int day) {
        Date newDate = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, day);
        newDate = cal.getTime();
        return newDate;
    }

    public static Calendar getCalendar(int year, int month, int day) {
        if (year >= 2000 && year <= 2100) {
            if (month >= 1 && month <= 12) {
                if (day < 1) {
                    throw new IllegalArgumentException();
                } else {
                    if (month == 2 && isLeapYear(year)) {
                        if (day > 29) {
                            throw new IllegalArgumentException();
                        }
                    } else if (day > DAYS[month - 1]) {
                        throw new IllegalArgumentException();
                    }

                    --month;
                    Calendar c = Calendar.getInstance();
                    c.set(1, year);
                    c.set(2, month);
                    c.set(5, day);
                    c.set(11, 0);
                    c.set(12, 0);
                    c.set(13, 0);
                    c.set(14, 0);
                    return c;
                }
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static int[] getToday() {
        return getDate(Calendar.getInstance());
    }

    public static int[] getDate(long t) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(t);
        return getDate(c);
    }

    public static int[] getDate(Calendar c) {
        int week = c.get(7) - 1;
        if (week == 0) {
            week = 7;
        }

        return new int[]{c.get(1), c.get(2) + 1, c.get(5), week};
    }

    public static int[] getTime(long t) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(t);
        return getTime(c);
    }

    public static int[] getTime(Date date) {
        return getTime(date.getTime());
    }

    public static int[] getTime(Calendar c) {
        int week = c.get(7) - 1;
        if (week == 0) {
            week = 7;
        }

        return new int[]{c.get(1), c.get(2) + 1, c.get(5), week, c.get(11), c.get(12), c.get(13)};
    }

    public static int[] getPreviousDay(int year, int month, int day) {
        --day;
        if (day < 1) {
            --month;
            if (month < 1) {
                --year;
                month = 12;
            }

            int lastDay = DAYS[month - 1];
            if (month == 2 && isLeapYear(year)) {
                ++lastDay;
            }

            day = lastDay;
        }

        return new int[]{year, month, day};
    }

    public static int[] getNextDay(int year, int month, int day) {
        ++day;
        int max = DAYS[month - 1];
        if (month == 2 && isLeapYear(year)) {
            ++max;
        }

        if (day > max) {
            day = 1;
            ++month;
            if (month > 12) {
                ++year;
                month = 1;
            }
        }

        return new int[]{year, month, day};
    }

    public static GregorianCalendar toCalendar(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeZone(TimeZone.getDefault());
        cal.setTime(date);
        return cal;
    }

    public static Date randomDate(long begin, long end) {
        if (begin >= end) {
            throw new IllegalArgumentException("end must greater than begin...");
        } else {
            long rtn = (long)((double)begin + Math.random() * (double)(end - begin));
            return rtn != begin && rtn != end ? new Date(rtn) : randomDate(begin, end);
        }
    }

    public static Date randomDate(String beginDate, String endDate) {
        Date begin = toDate(beginDate);
        Date end = toDate(endDate);
        return begin != null && end != null && begin.getTime() < end.getTime() ? randomDate(begin.getTime(), end.getTime()) : null;
    }

    public static boolean isClosely(Date date, Date baseDate, int seconds) {
        long m_time = date.getTime();
        long b_time = baseDate.getTime();
        long ms = (long)seconds * 1000L;
        if (m_time == b_time) {
            return true;
        } else if (m_time > b_time) {
            return b_time + ms > m_time;
        } else if (m_time < b_time) {
            return m_time + ms > b_time;
        } else {
            return true;
        }
    }

    public static String timeSpan(long msUsed) {
        if (msUsed < 0L) {
            return String.valueOf(msUsed);
        } else if (msUsed < 1000L) {
            return String.valueOf(msUsed) + " 毫秒";
        } else {
            msUsed /= 1000L;
            if (msUsed < 60L) {
                return String.valueOf(msUsed) + " 秒";
            } else {
                long nDay;
                long nHour;
                if (msUsed < 3600L) {
                    nDay = msUsed / 60L;
                    nHour = msUsed % 60L;
                    return String.valueOf(nDay) + " 分" + nHour + " 秒";
                } else {
                    long nMinute;
                    if (msUsed < 86400L) {
                        nDay = msUsed / 3600L;
                        nHour = (msUsed - nDay * 3600L) / 60L;
                        nMinute = (msUsed - nDay * 3600L) % 60L;
                        return String.valueOf(nDay) + " 小时" + nHour + " 分" + nMinute + " 秒";
                    } else {
                        nDay = msUsed / 86400L;
                        nHour = (msUsed - nDay * 86400L) / 3600L;
                        nMinute = (msUsed - nDay * 86400L - nHour * 3600L) / 60L;
                        long nSecond = (msUsed - nDay * 86400L - nHour * 3600L) % 60L;
                        return String.valueOf(nDay) + " 天" + nHour + " 小时" + nMinute + " 分" + nSecond + " 秒";
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        System.out.println(StringUtils.join(list, ","));
        String input = "2008-05-18 22:18:58";
        //Date date = toDate(input);
        //System.out.println(date);
        //System.out.println(get(new Date(), 3));
        //System.out.println(nowTimeFormat());
       // System.out.println(toTime(new Date()));
        //System.out.println(timeSpan(System.currentTimeMillis()));
    }
}
