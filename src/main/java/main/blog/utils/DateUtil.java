package main.blog.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 验证码工具类
 */
public class DateUtil{
	
	/**
     * 日期时间转换成时间戳
     * @param time
     * @return
     */
    public static long dateToTimestamp(String time)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(time);
            long ts = date.getTime() / 1000;
            return ts;
        } catch (ParseException e) 
        {
            return 0;
        }
    }
    
	/**
	 * 时间戳转时间,转日期时间
	 * @param time
	 * @return
	 */
	public static String timestampToDate(long time) 
	{
	    String dateTime;
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    long timeLong = Long.valueOf(time);
	    dateTime = simpleDateFormat.format(new Date(timeLong * 1000L));
	    return dateTime;
	}
	
	/**
	 * 获取当前时间
	 * @param time
	 * @return 
	 * @return 
	 */
	public static Date NowString() 
	{
		Date date = new Date();
		return date;
	}
}