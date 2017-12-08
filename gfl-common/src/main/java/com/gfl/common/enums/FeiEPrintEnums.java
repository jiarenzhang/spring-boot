package com.gfl.common.enums;


public class FeiEPrintEnums {

	public static class Type extends ExtensibleEnum{

		public static final Type feiE_print = new Type(1,"打印");
		public static final Type feiE_add = new Type(2,"添加打印机");
		public static final Type feiE_delete = new Type(3,"删除打印机");
		public static final Type feiE_update = new Type(4,"修改打印机");
		public static final Type feiE_findOrderStatus = new Type(5,"查询订单是否打印成功");
		public static final Type feiE_findPrintOrderNum = new Type(6,"查询指定打印机某天的订单统计数");
		public static final Type feiE_queryPrinterStatus = new Type(7,"获取某台打印机状态");
		
		
		private Type(int code){
			this(code,null);
		}
		
		private Type(int code, String name) {
			super(code, name);
		}
		
		
	}
}
