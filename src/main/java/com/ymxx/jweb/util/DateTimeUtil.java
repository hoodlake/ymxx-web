package com.ymxx.jweb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ymxx.jweb.exception.YmxxException;

/**
 * 
 * @author Administrator
 *
 */
public class DateTimeUtil {

	
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd"; 
	/**
	 * 
	 */
	private DateTimeUtil(){}
	/**
	 * ignore hour,minute and second,count the days between the two parameters
	 * @param first
	 * @param second
	 * @return
	 */
	public static int sub(Date first,Date second){
		
		if (first == null || second == null) {
			throw new YmxxException("parameter illegal,for input null parameter");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		try {
			first = sdf.parse(sdf.format(first));
			second = sdf.parse(sdf.format(second));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long _first = first.getTime();
		long _end = second.getTime();
		long day = ((_first - _end) / 1000 / 60 / 60 / 24);

		return (int)day;
	}
	/**
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date str2Date(String str,String format){
		
		if(StringUtil.isBlank(format)){
			throw new YmxxException("parameter illegal,for input null format");
		}else if(StringUtil.isBlank(str)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
			Date date = sdf.parse(str);
			return date;
		}catch(ParseException e){
			throw new YmxxException("parse execption");
		}
	}
	/**
	 * @param str
	 * @return
	 */
	public static Date str2Date(String str){
		
		return str2Date(str, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		
		if(date == null){
			return "";
		}else if(StringUtil.isBlank(format)){
			throw new YmxxException("parameter illegal,for input null format");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date){
		
		if(date == null){
			return "";
		}
		return date2Str(date,DEFAULT_DATE_FORMAT);
	}
	/**
	 * @param date
	 * @return
	 */
	public static int getYear(java.util.Date date) {

		if(date == null){
			throw new YmxxException("parameter illegal:for input null date");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c.get(Calendar.YEAR);
	}

	/**
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {

		if(date == null){
			throw new YmxxException("parameter illegal:for input null date");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c.get(java.util.Calendar.MONTH) + 1;
	}

	/**
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {

		if(date == null){
			throw new YmxxException("parameter illegal:for input null date");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 以月份为单位，对日期进行加减操作
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date calcDateOnMonth(Date date , int month){
		
		if(date == null){
			throw new YmxxException("parameter illegal:for input null date");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}
	/**
	 * 以天为单位，对日期进行加减操作
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date calcDateOnDay(Date date , int day){
		
		if(date == null){
			throw new YmxxException("parameter illegal:for input null date");
		}
		Calendar calendar = null; 
		try{
			calendar = Calendar.getInstance();
			calendar.setTime(date); 
			calendar.add(Calendar.DAY_OF_MONTH, day);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return calendar.getTime();
	}
	/**
	 * 判断是否是同一年同一月分
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Boolean isTheSameMonth(Date d1,Date d2){
		
		if(d1 == null || d2 == null){
			return false;
		}
		if(getYear(d1) == getYear(d2) 
				&& getMonth(d1) == getMonth(d2)){
			return true;
		}
		return false;
	}
	/**
	 * 判断是否是同一天
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Boolean isTheSameDay(Date d1,Date d2){
		
		if(d1 == null || d2 == null){
			return false;
		}
		if(getYear(d1) == getYear(d2) 
				&& getMonth(d1) == getMonth(d2)
				&& getDay(d1) == getDay(d2)){
			return true;
		}
		return false;
	}
	/**
	 * return current date eg 2013-12-09
	 * @return
	 */
	public static String getCurrentDate() {
		
		return date2Str(new Date(),"yyyy-MM-dd");
	}

	/**
	 * return current time eg 14:23:12
	 * @return
	 */
	public static String getCurrentTime(){
		
		return date2Str(new Date(),"HH:mm:ss");
	}
}
