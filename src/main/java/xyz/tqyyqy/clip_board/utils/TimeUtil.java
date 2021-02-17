package xyz.tqyyqy.clip_board.utils;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * PackageName:UtilsforTime
 * Author:feiyc
 * Date:19-1-17
 * Description:
 */
public class TimeUtil {


    public static String getToken(String dataStr) {
        try {
            dataStr = dataStr + System.currentTimeMillis();
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 获取日周月的开始时间戳
     *
     * @param timeStr 1代表今天 ， 2代表本周 3代表本月 4代表上月 其他代表100
     * @return
     */
    public static Long getBegin(String timeStr) {
        if (timeStr.equals("1")) {
            return getDayBegin(new Date());
        } else if (timeStr.equals("2")) {
            return getWeekStartDate(new Date());
        } else if (timeStr.equals("3")) {
            return getMonthBegin(new Date());
        } else if (timeStr.equals("4")) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.MONTH, -1);
            Date m = c.getTime();
            return getMonthBegin(m);
        } else {
            return (long) 100;
        }

    }


    /**
     * 获取今天开始时间戳
     *
     * @param date 指定日期
     * @return
     */
    public static Long getDayBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);


        //	c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 获取今天结束时间戳
     *
     * @param date 指定日期
     * @return
     */
    public static Long getDayEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);


        //	c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 获取指定日期所在月份开始的时间戳
     *
     * @param date 指定日期
     * @return
     */
    public static Long getMonthBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 获取指定日期所在月份结束的时间戳
     *
     * @param date 指定日期
     * @return
     */
    public static Long getMonthEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 获取时间戳是否超过12个小时
     *
     * @param time
     * @return
     */
    public static boolean decide12hour(Long time) {
        //Long time = Long.parseLong(timeStr);
        Long timeout = time + 43200000;
        //	if ( time<System.currentTimeMillis()&& timeout>System.currentTimeMillis()){
        if (timeout > System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    /**
     * 获取12个小时的时间戳
     *
     * @return
     */
    public static Long get12hour() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        Long zero = c.getTimeInMillis();
        c.set(Calendar.HOUR_OF_DAY, 12);


        return c.getTimeInMillis() - zero;
    }

    /**
     * 获取周开始的时间戳
     *
     * @param date
     * @return
     */

    public static Long getWeekStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        //将毫秒至0
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDateweb(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 时间戳差转时间
     */
    public static String stampDifferenceToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        long lt = new Long(s);
        lt = lt -28800000l;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间转换为Date类
     */
    public static Date dateToStamp(String s) {
        String res;
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//		long ts = date.getTime();
//		res = String.valueOf(ts);
        return date;
    }

    /*
     * 将时间转换为Date类
     */
    public static Date monthToDate(String s) {
        String res;
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//		long ts = date.getTime();
//		res = String.valueOf(ts);
        return date;
    }

    /**
     * @param num  1为当日开始时间戳 其他为当日结束时间戳
     * @param time 时间
     * @return
     */
    public static Long getBeginByTime(String num, String time) {
        Date date = dateToStamp(time);
        if (num.equals("1"))
            return getDayBegin(date);
        else
            return getDayEnd(date);
    }

    public static boolean isNumber1(String str) {// 判断整型
        return str.matches("^\\d+$$");
    }

    public static boolean isNumber2(String str) {// 判断小数，与判断整型的区别在与d后面的小数点（红色）
        return str.matches("\\d+\\.\\d+$");
    }

    public static boolean isNumber3(String str) {// 判断小数点开头
        return str.matches("\\.\\d+$");
    }

    /**
     * 获取指定月份的所有天数时间戳 次日的时间戳需减一
     * 月份的天数为返回的Long型变量的长度减2 下标为0和最后一个数字分别为月份的起始与结束时间戳
     *
     * @param Time
     * @return
     */
    public static Long[] getMonthToLong(String Time) {
        Long monthStart = getMonthBegin(monthToDate(Time));
        Long monthEnd = getMonthEnd(monthToDate(Time));
        Long month = monthEnd - monthStart + 1;
        //	System.out.println(month/86400000);
        int index = (int) (month / 86400000);
        Long[] MonthTime = new Long[index + 2];
        int j = 0;
        MonthTime[j] = monthStart;
        MonthTime[index + 1] = monthEnd;
        for (Long i = monthStart; i < monthEnd; i = i + 86400000) {
            //System.out.println(i);
            j++;
            MonthTime[j] = i;
        }
        return MonthTime;
    }

    public static void main(String[] args) {
		System.out.println(stampDifferenceToDate("0"));

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.MONTH, -1);
//
//        Date m = c.getTime();
//        System.out.println(format.format(getMonthBegin(m)));
//        String mon = format.format(m);
//		System.out.println("过去一个月："+mon);

//		System.out.println(getBeginByTime("1","2017-07-9"));
//	System.out.println(getBeginByTime("2","2017-7-09"));
        //System.out.println(stampToDateweb(System.currentTimeMillis()+""));

//		monthToDate
        //	System.out.println(86400000);
//		String Time = "2019-02";
//		Long[] longs = getMonthToLong(Time);
////		for (Long l : longs){
////			System.out.println(l);
////		}
//		System.out.println(longs.length);
//		for (int i = 1;i<longs.length;i++){
//			System.out.println(i+" 数组下标 "+ longs[i]);
//		}
//
////		Long ttime = getMonthEnd(monthToDate(Time));
//////		System.out.println(stampToDateweb(ttime.toString()));
//		System.out.println(stampToDateweb("1548950400000"));
//		System.out.println(stampToDateweb("1549036799999"));
//		for (int i = 2; i <= (longs.length-1); i++) {
//			System.out.println("数组下标"+i);
//			System.out.println((longs[i-1])+" 时间间隔  "+(longs[i]-1));
//		}
        //	System.out.println(stampToDatewx(getWeekStartDate(new Date()).toString()));

//		Long monthStart = getMonthBegin(monthToDate(Time));
//		Long monthEnd = getMonthEnd(monthToDate(Time));
//		Long month = monthEnd-monthStart+1;
//		System.out.println(month/86400000);
//		int j =1;
//		for (Long i = monthStart; i < monthEnd; i=i+86400000) {
//			System.out.println(i);
//			System.out.println(j);
//			j++;
//		}
//		System.out.println("测试方法");
//		Long[] MonthTime = getMonthToLong(Time);
//		System.out.println(MonthTime[MonthTime.length-1]);
//		System.out.println("日期含有天数"+(MonthTime.length-2));
//		for (Long l:MonthTime){
//			System.out.println(l);
//		}

//		1548950399999   1548863999999
    }

}
