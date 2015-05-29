package org.leg.library.time;

/**
 * 时间跨度类
 */
public class Duration {
    /**
     * 一秒包含的毫秒数
     */
    public final static long SECOND_MILLIS = 1000L;
    /**
     * 一分包含的秒数
     */
    public final static int MINUTE_SECONDS = 60;
    /**
     * 一分包含的毫秒数
     */
    public final static long MINUTE_MILLIS = SECOND_MILLIS * MINUTE_SECONDS;
    /**
     * 一小时包含的分钟数
     */
    public final static int HOUR_MINUTES = 60;
    /**
     * 一小时包含的秒数
     */
    public final static int HOUR_SECONDS = MINUTE_SECONDS * HOUR_MINUTES;
    /**
     * 一小时包含的毫秒数
     */
    public final static long HOUR_MILLIS = HOUR_SECONDS * HOUR_MINUTES;
    /**
     * 一天包含的小时数
     */
    public final static int DAY_HOURS = 24;
    /**
     * 一天包含的分钟数
     */
    public final static int DAY_MINUTES = HOUR_MINUTES * DAY_HOURS;
    /**
     * 一天包含的秒数
     */
    public final static int DAY_SECONDS = HOUR_SECONDS * DAY_HOURS;
    /**
     * 一天包含的毫秒数
     */
    public final static long DAY_MILLIS = HOUR_MILLIS * DAY_HOURS;
    /**
     * 一周包含的天数
     */
    public final static int WEEK_DAYS = 7;
    /**
     * 一周包含的小时数
     */
    public final static int WEEK_HOURS = DAY_HOURS * WEEK_DAYS;
    /**
     * 一周包含的分钟数
     */
    public final static int WEEK_MINUTES = DAY_MINUTES * WEEK_DAYS;
    /**
     * 一周包含的秒数
     */
    public final static int WEEK_SECONDS = DAY_SECONDS * WEEK_DAYS;
    /**
     * 一周包含的毫秒数
     */
    public final static long WEEK_MILLIS = DAY_MILLIS * WEEK_DAYS;


    /**
     * 毫秒数
     */
    protected long millis;


    /**
     * 构造函数
     *
     * @param millis 毫秒跨度
     */
    public Duration(long millis) {
        this.millis = millis;
    }

    /**
     * 创建时间跨度
     *
     * @param seconds 分钟数
     */
    public static Duration createSeconds(int seconds) {
        return new Duration(Duration.SECOND_MILLIS * seconds);
    }

    /**
     * 创建时间跨度
     *
     * @param minutes 分钟数
     */
    public static Duration createMinutes(int minutes) {
        return new Duration(Duration.MINUTE_MILLIS * minutes);
    }

    /**
     * 创建时间跨度
     *
     * @param hours 小时数
     */
    public static Duration createHours(int hours) {
        return new Duration(Duration.HOUR_MILLIS * hours);
    }

    /**
     * 创建时间跨度
     *
     * @param days 天数
     */
    public static Duration createDays(int days) {
        return new Duration(Duration.DAY_MILLIS * days);
    }

    /**
     * 创建时间跨度
     *
     * @param weeks 星期数
     */
    public static Duration createWeeks(int weeks) {
        return new Duration(Duration.WEEK_MILLIS * weeks);
    }

    /**
     * 获取星期跨度
     *
     * @return 星期跨度
     */
    public int weeks() {
        return (int)(millis / Duration.WEEK_MILLIS);
    }

    /**
     * 获取日跨度
     *
     * @return 日跨度
     */
    public int days() {
        return (int)(millis / Duration.DAY_MILLIS);
    }

    /**
     * 获取小时跨度
     *
     * @return 小时跨度
     */
    public int hours() {
        return (int)(millis / Duration.HOUR_MILLIS);
    }

    /**
     * 获取分钟跨度
     *
     * @return 分钟跨度
     */
    public int minutes() {
        return (int)(millis / Duration.MINUTE_MILLIS);
    }

    /**
     * 获取秒跨度
     *
     * @return 秒跨度
     */
    public int seconds() {
        return (int)(millis / Duration.SECOND_MILLIS);
    }

    /**
     * 获取毫秒跨度
     *
     * @return 毫秒跨度
     */
    public long millis() {
        return millis;
    }

    /**
     * 转为字符串
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        int days = (int)(this.millis / Duration.DAY_MILLIS);
        int hours = (int)((this.millis % Duration.DAY_MILLIS) / Duration.HOUR_MILLIS);
        int minutes = (int)((this.millis % Duration.HOUR_MILLIS) / Duration.MINUTE_MILLIS);
        int seconds = (int)((this.millis % Duration.MINUTE_MILLIS) / Duration.SECOND_MILLIS);
        int millis = (int)(this.millis % Duration.SECOND_MILLIS);
        if(0 == days) {
            if(0 == millis) {
                return String.format("%1$02d:%2$02d:%3$02d", hours, minutes, seconds);
            }
            else {
                return String.format("%1$02d:%2$02d:%3$02d.%4$03d", hours, minutes, seconds, millis);
            }
        }
        else {
            if(0 == millis) {
                return String.format("%1$d %2$02d:%3$02d:%4$02d", days, hours, minutes, seconds);
            }
            else {
                return String.format("%1$d %2$02d:%3$02d:%4$02d.%5$03d", days, hours, minutes, seconds, millis);
            }
        }
    }
}
