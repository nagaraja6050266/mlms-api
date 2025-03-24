package com.example.mlms.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.mlms.utils.constants.UtilityConstants;

public class Utilities
{
	private static final String format = UtilityConstants.DATE_TIME_FORMAT;

	public static boolean isEmpty(String string){
		return string == null || string.isEmpty();
	}
	public static String getDateTime(long milliseconds,String format){
		DateFormat simple = new SimpleDateFormat(format);
		Date result = new Date(milliseconds);
		return simple.format(result);
	}
	public static String getDateTime(long milliseconds){
		return getDateTime(milliseconds,format);
	}
	public static String getDate(long milliseconds){
		return getDateTime(milliseconds, UtilityConstants.DATE_FORMAT);
	}
	public static String getTime(long milliseconds){
		return getDateTime(milliseconds, UtilityConstants.TIME_FORMAT);
	}
	public static Long getDateTime(String date) throws Exception{
		Date result=new Date(date);
		return result.getTime();
	}
}
