package org.leg.library.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 日期类
 */
public class Date implements Comparable<Date> {
    /**
     * 日期格式
     */
    public final static String DATE_FORMAT = "yyyy-MM-dd";


    /**
     * 日期对象
     */
    protected Calendar calendar;


    /**
     * 构造函数
     */
    private Date() { }

    /**
     * 获取年份
     *
     * @return 年份
     */
    public int year() {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     *
     * @return 月份
     */
    public int month() {
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获取日期
     *
     * @return 日期
     */
    public int day() {
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取星期
     *
     * @return 星期
     */
    public int week() {
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取系统当前日期
     *
     * @return 当前日期
     */
    public static Date today() {
        Date result = new Date();
        result.calendar = Calendar.getInstance();
        return result;
    }

    /**
     * 解析日历对象
     *
     * @param calendar 日历对象
     * @return 日期对象
     */
    public static Date parse(Calendar calendar) {
        Date result = new Date();
        result.calendar = calendar;
        return result;
    }

    /**
     * 解析工具日期对象
     *
     * @param date 工具日期对象
     * @return 日期对象
     */
    public static Date parse(java.util.Date date) {
        Date result = new Date();
        result.calendar = Calendar.getInstance();
        result.calendar.setTime(date);
        return result;
    }

    /**
     * 解析日期字符串
     *
     * @param dateString 日期字符串
     * @return 日期对象
     */
    public static Date parse(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return Date.parse(format.parse(dateString));
    }

    /**
     * 解析日期ID
     *
     * @param dayId 日期ID
     * @return 日期对象
     */
    public static Date parse(int dayId) {
        Date result = new Date();
        result.calendar.setTimeInMillis(dayId * Duration.DAY_MILLIS);
        return result;
    }

    /**
     * 增加时间跨度
     *
     * @param duration 时间跨度
     * @return 增加时间跨度之后的日期对象
     */
    public Date add(Duration duration) {
        Calendar result = Calendar.getInstance();
        result.setTimeInMillis(calendar.getTimeInMillis() + duration.millis());
        return Date.parse(result);
    }

    /**
     * 减去时间跨度
     *
     * @param duration 时间跨度
     * @return 减去时间跨度之后日期对象
     */
    public Date subtract(Duration duration) {
        Calendar result = Calendar.getInstance();
        result.setTimeInMillis(calendar.getTimeInMillis() - duration.millis());
        return Date.parse(result);
    }

    /**
     * 计算与指定日期的时间跨度
     *
     * @param date 日期对象
     * @return 时间跨度
     */
    public Duration subtract(Date date) {
        return new Duration(calendar.getTimeInMillis() - date.calendar.getTimeInMillis());
    }

    /**
     * 转为日期ID
     *
     * @return 日期ID
     */
    public int toInteger() {
        return (int)(calendar.getTimeInMillis() / Duration.DAY_MILLIS);
    }

    /**
     * 转为字符串
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        return (new SimpleDateFormat(DATE_FORMAT)).format(calendar.getTime());
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p/>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p/>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p/>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p/>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p/>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Date o) {
        return toInteger() - o.toInteger();
    }
}
