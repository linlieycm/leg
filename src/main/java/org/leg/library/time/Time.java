package org.leg.library.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 时间类
 */
public class Time implements Comparable<Time> {
    /**
     * 时间格式
     */
    public final static String TIME_FORMAT = "HH:mm:ss.ms";


    /**
     * 毫秒数
     */
    protected int millis;


    /**
     * 构造函数
     */
    public Time() { }

    /**
     * 获取系统当前时间
     *
     * @return 当前时间
     */
    public static Time now() {
        Time result = new Time();
        result.millis = (int)(System.currentTimeMillis() % Duration.DAY_MILLIS);
        return result;
    }

    /**
     * 解析日历对象
     *
     * @param calendar 日历对象
     * @return 时间对象
     */
    public static Time parse(Calendar calendar) {
        Time result = new Time();
        result.millis = (int)(calendar.getTimeInMillis() % Duration.DAY_MILLIS);
        return result;
    }

    /**
     * 解析工具日期对象
     *
     * @param date 工具日期对象
     * @return 时间对象
     */
    public static Time parse(java.util.Date date) {
        Time result = new Time();
        result.millis = (int)(date.getTime() % Duration.DAY_MILLIS);
        return result;
    }

    /**
     * 解析日期字符串
     *
     * @param dateString 日期字符串
     * @return 时间对象
     */
    public static Time parse(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return Time.parse(format.parse(dateString));
    }

    /**
     * 解析毫秒
     *
     * @param millis 工具日期对象
     * @return 时间对象
     */
    public static Time parse(long millis) {
        Time result = new Time();
        result.millis = (int)(millis % Duration.DAY_MILLIS);
        return result;
    }

    /**
     * 增加时间跨度
     *
     * @param duration 时间跨度
     * @return 增加时间跨度之后的时间对象
     */
    public Time add(Duration duration) {
        return parse(millis + duration.millis());
    }

    /**
     * 计算与指定时间跨度的差
     *
     * @param duration 日期对象
     * @return 时间对象
     */
    public Time subtract(Duration duration) {
        return parse(millis - duration.millis());
    }

    /**
     * 计算与指定时间的时间跨度
     *
     * @param time 时间对象
     * @return 时间跨度
     */
    public Duration subtract(Time time) {
        return new Duration(millis - time.millis());
    }

    /**
     * 获取小时
     *
     * @return 小时
     */
    public int hour() {
        return (int)(millis / Duration.HOUR_MILLIS);
    }

    /**
     * 获取分钟
     *
     * @return 分钟
     */
    public int minute() {
        int result = (int)(millis % Duration.HOUR_MILLIS);
        return (int)(result / Duration.MINUTE_MILLIS);
    }

    /**
     * 获取秒
     *
     * @return 秒
     */
    public int second() {
        int result = (int)(millis % Duration.MINUTE_MILLIS);
        return (int)(result / Duration.SECOND_MILLIS);
    }

    /**
     * 获取毫秒
     *
     * @return 毫秒
     */
    public int millis() {
        return (int)(millis % Duration.SECOND_MILLIS);
    }

    /**
     * 转为字符串
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        java.util.Date date = new java.util.Date();
        date.setTime(millis);
        return (new SimpleDateFormat(TIME_FORMAT)).format(date);
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
    public int compareTo(Time o) {
        return millis - o.millis;
    }
}
