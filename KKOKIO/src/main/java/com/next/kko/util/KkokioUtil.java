package com.next.kko.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class KkokioUtil {
	
	
	public static String removeHyphen(String s) {
		return s.replace("-", "");
	}
	
	public static String removeTime(String s) {
		return s.substring(0,10);
	}
 
	public static Date stringToDate(String s) throws ParseException {
		
		return new SimpleDateFormat("yyyyMMdd").parse(s);
	}
	
	public static String addDates(String start,int count) throws ParseException {
		Date startDate = new SimpleDateFormat("yyyyMMdd").parse(start);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.DATE, count);
		return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		
	}
	
	/**
	 * <h1>날짜 차이 구하기  yyyyMMdd</h1>
	 * <p>설명 : 입력받은 두 날짜 사이의 차이를 구한다.</p>
	 * @author 박지연
	 * @since 2018.11.12
	 * @version 1.0
	 * @package com.next.kko.util
	 * @param start
	 * @param end
	 * @return int
	 * @throws ParseException
	 */
	public static int diffDates(String start,String end) throws ParseException {
		Date startDate = new SimpleDateFormat("yyyyMMdd").parse(start);
		Date endDate = new SimpleDateFormat("yyyyMMdd").parse(end);
		
		long diff = endDate.getTime()-startDate.getTime();
		
		int diffdays = (int)(diff/(24*60*60*1000));
		
		return diffdays;
	}
	
	/**
	 * <h1>파스 기간 구하기 yyyy-MM-dd</h1>
	 * <p>설명 : 파스 종료일과 파스 시작일 사이의 차이를 구한다.</p>
	 * @author 박지연
	 * @since 2018.11.20
	 * @version 1.0
	 * @package com.next.kko.util
	 * @param startpass
	 * @param endpass
	 * @return int
	 * @throws ParseException
	 */
	public static int getPeriod(String startpass, String endpass) throws ParseException {
		
		System.out.println("startpass: "+startpass);
		System.out.println("endpass: "+endpass);
		
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startpass);
		System.out.println("startDate: "+startDate);
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endpass);
		System.out.println("endDate: "+endDate);
		
		long diff = endDate.getTime()-startDate.getTime();
		
		int diffdays = (int)(diff/(24*60*60*1000));
		
		return diffdays;
	}
	
	public static String createUUID() {
		//1231k-1454h-
		return UUID.randomUUID().toString().replaceAll("-","");
	}
}
