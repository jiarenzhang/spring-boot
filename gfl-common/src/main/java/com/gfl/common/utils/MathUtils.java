package com.gfl.common.utils;

import java.math.BigDecimal;


public class MathUtils {

	private static final int DEF_DIV_SCALE = 10;

	private MathUtils() {
	}

	/**
	 * @Desc		精确加法运算
	 * @param v1	被加数
	 * @param v2	加数
	 * @return
	 */
	public static double add(double v1, double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * @Desc		精确减法运算
	 * @param v1	被减数
	 * @param v2	减数
	 * @return
	 */
	public static double subtract(double v1, double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * @Desc		精确乘法运算
	 * @param v1	被乘数
	 * @param v2	乘数
	 * @return
	 */
	public static double multiply(double v1, double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * @Desc		相对准确除法运算,当除不尽时精确到小数点后DEF_DIV_SCALE位
	 * @param v1	被除数
	 * @param v2	除数
	 * @return
	 */
	public static double divide(double v1, double v2){
		return divide(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * @Desc		相对准确除法运算,当除不尽时精确到小数点后scale位
	 * @param v1	被除数
	 * @param v2	除数
	 * @param scale
	 * @return
	 */
	public static double divide(double v1, double v2, int scale){
		if (scale < 0) {
			throw new IllegalArgumentException( "The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * @Desc		精确小数位四舍五入处理
	 * @param v		需要四舍五入处理的数字
	 * @param scale	精确(小数位数)
	 * @return
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException( "The scale must be a positive integer or zero");
		}
		return new BigDecimal(Double.toString(v)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
