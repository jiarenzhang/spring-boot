package com.gfl.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * 公共 util
 * 
 * @author MoralIntegrity
 *
 */
public class CommonUtil {

	private static final Logger LOG = LoggerFactory.getLogger(CommonUtil.class);

	private static final IdWorker ID_WORKER = new IdWorker(2);

	/**
	 * 获取id Worker
	 * @return idWorder
	 */
	public static String getIdWorker () {

		String id = String.valueOf(ID_WORKER.nextId());
		LOG.info("====IdWorker生成：" + id + "====");

		return id;

	}
	/**
	 * 获取id Worker
	 * @return idWorder
	 */
	public static Long getIdWorkerIsLong () {

		Long id = ID_WORKER.nextId();

		LOG.info("====IdWorker生成：{}====" , id);

		return id;
	}
	/**
	 * 校验参数
	 * @param obj 校验引用
	 * @param msg 异常信息
	 */
	public static void checkArgument(Object obj, String msg) {
		if (null == obj) {

			LOG.error("==== {} ====",msg);

			throw new RuntimeException(msg);
		}
	}

	/**
	 * 校验参数
	 * @param expression 校验表达式
	 * @param msg 异常信息
	 */
	public static void checkArgument(boolean expression, String msg) {
		if (!expression) {

			LOG.error("==== {} ====", msg);

			throw new RuntimeException(msg);
		}
	}

	public static void checkArgument(boolean expression, String errorMsg,String successMsg) {
		if (!expression) {
			LOG.error("==== {} ====", errorMsg);
			throw new RuntimeException(errorMsg);
		} else {
			LOG.info("==== {} ====", successMsg);
		}
	}

	public static void checkArgument(Integer count, String msg) {
		checkArgument(count != 0,msg);
	}

	public static void checkArgument(Integer count, String errorMsg,String successMsg) {
		checkArgument(count != 0,errorMsg,successMsg);
	}

	/**
	 *
	 * 随机生成随机数，可以用来生成tocken字符串<BR>
	 * @param length 随机生成的位数
	 * @return String<BR> 数字字符串
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static String getRandomString(int length) {
		StringBuffer bu = new StringBuffer();
		/*String[] arr = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
				"d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };*/
		String[] arr = { "1","2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
				"d", "f", "g", "h", "i",  "k", "m", "q", "r", "w", "x", "y",
				"A", "B", "C", "D", "E",  "F", "G", "H", "J", "K", "L", "M",
				"N", "R", "W", "X", "Y", "Z" };
		Random random = new Random();
		while(bu.length() < length){
			String temp = arr[random.nextInt(42)];
			if(bu.indexOf(temp) == -1){
				bu.append(temp);
			}
		}
		return bu.toString();
	}
	/**
	 *
	 * 随机生成数字<BR>
	 * @param length 随机生成数字的个数
	 * @return String<BR> 数字字符串
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static String getRandomNumber(int length){
		StringBuffer bu = new StringBuffer();
		String[] arr = {"2","3","4","5","6","7","8","9","1","0"};
		Random random = new Random();
		while(bu.length() < length){
			String temp = arr[random.nextInt(10)];
			if(bu.indexOf(temp) == -1){
				bu.append(temp);
			}
		}
		return bu.toString();
	}
	/**
	 *
	 * 获取某个区域的随机数<BR>
	 * @param sek
	 *           随机种子
	 * @param min
	 *           最小整数
	 * @param max
	 *           最大整数
	 * @return int<BR>  随机整数
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static int getRandomInt(int sek,int min,int max){
		Random random = new Random();
		int temp = 0;
		do{
			temp = random.nextInt(sek);
		}while(temp < min || temp > max);
		return temp;
	}

	/**
	 * 初始化补位，如：25 --> 000025 ,getFillNum(6,25)
	 * @param len 长度
	 * @param num 数值
	 * @return 补位
	 */
	public static String getFillNum (int len,int num) {
		if (len > 0) {
			return String.format("%0"+len+"d", num);
		}
		return null;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(getRandomString(6));
		}
		System.out.println(getFillNum(20,-0));


	}
}
