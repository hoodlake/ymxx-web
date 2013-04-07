package com.ymxx.jweb.util;

import java.math.BigDecimal;

public class Arith {
	private static final int DEF_DIV_SCALE = 10;
	private Arith(){}
	/**
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static double add(double num1,double num2){
		
		BigDecimal b1 = BigDecimal.valueOf(num1);
		BigDecimal b2 = BigDecimal.valueOf(num2);
		
		return b1.add(b2).doubleValue();
	}
	/**
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static double sub(double num1,double num2){
		
		BigDecimal b1 = BigDecimal.valueOf(num1);
		BigDecimal b2 = BigDecimal.valueOf(num2);
		
		return b1.subtract(b2).doubleValue();
	}
	/**
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static double mul(double num1,double num2){
		
		BigDecimal b1 = BigDecimal.valueOf(num1);
		BigDecimal b2 = BigDecimal.valueOf(num2);
		
		return b1.multiply(b2).doubleValue();
	}
	/**
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static double div(double num1,double num2){
		
		BigDecimal b1 = BigDecimal.valueOf(num1);
		BigDecimal b2 = BigDecimal.valueOf(num2);
		
		return b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}