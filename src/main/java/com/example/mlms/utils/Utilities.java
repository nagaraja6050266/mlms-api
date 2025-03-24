package com.example.mlms.utils;

import java.util.Date;

public class Utilities
{
	private static final String format = "dd-MM-yyyy HH:mm:ss";

	public static boolean isEmpty(String string){
		return string == null || string.isEmpty();
	}

	public static Long getDateTime(String date) throws Exception{
		Date result=new Date(date);
		return result.getTime();
	}
}
