package com.gfl.common.utils;

import java.util.Calendar;
import java.util.Date;


public class MonthUtils {

	/**
	 * 取当前月份(yyyyMM)
	 * @return
	 */
	public static String getCurrentMonth(){
		return DateFormatUtils.format(new Date(), "yyyyMM");
	}

	/**
	 * 取月份month的上一个月份(yyyyMM)
	 * @param month
	 * @return
	 */
	public static String getLastMonth(String month){
		return offsetMonth(month, -1);
	}

	/**
	 * 取当前月份的上一个月份(yyyyMM)
	 * @return
	 */
	public static String getLastMonth(){
		return offsetMonth(getCurrentMonth(), -1);
	}

	/**
	 * 取指定月份第一天(yyyyMMdd)
	 * @param month
	 * @return
	 */
	public static String getBeginDayOfMonth(String month){
		return month + "01";
	}

	/**
	 * 取指定月份的最后一天(yyyyMMdd)
	 * @param month
	 */
	public static String getEndDayOfMonth(String month){
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(new Date(DateUtils.getEndTimeOfMonth(month).getTime()));

		int c_year = calendar.get(Calendar.YEAR);
		int c_month = calendar.get(Calendar.MONTH) + 1;
		int c_day = calendar.get(Calendar.DATE);
		return String.valueOf(c_year) + (c_month < 10 ?("0" + c_month):(String.valueOf(c_month))) + (c_day < 10 ?("0" + c_day):(String.valueOf(c_day)));
	}

	/**
	 * 由指定月份计算相对该月份的偏移月份(yyyyMM)
	 * @param calmonth
	 * @param offset
	 */
	public static String offsetMonth(String calmonth, int offset){
		if (null == calmonth || calmonth.length() < 6)
			return calmonth;

		int yearCt = Integer.parseInt(calmonth.substring(0, 4));
		int monCt = Integer.parseInt(calmonth.substring(4, 6));
		String sb = calmonth.substring(6);
		int monTotal = yearCt * 12 + monCt + offset;

		int curYear = monTotal / 12;
		int curMon = monTotal % 12;
		if(curMon == 0){
			return String.valueOf(curYear - 1) + "12" + sb;
		}else{
			return String.valueOf(curYear) + (curMon < 10 ? ("0" + curMon):String.valueOf(curMon)) + sb;
		}
	}

	/**
	 * 计算left-right的月份偏移量(left-right)(yyyyMM)
	 * @param left
	 * @param right
	 */
	public static int getOffset(String left, String right){
		int leftYearCt = Integer.parseInt(left.substring(0, 4));
		int leftMonCt = Integer.parseInt(left.substring(4, 6), 10);
		int leftTotal = leftYearCt * 12 + leftMonCt;

		int rightYearCt = Integer.parseInt(right.substring(0, 4));
		int rightMonCt = Integer.parseInt(right.substring(4, 6), 10);
		int rightTotal = rightYearCt * 12 + rightMonCt;
		return leftTotal - rightTotal;
	}

}
