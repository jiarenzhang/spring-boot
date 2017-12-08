
package com.gfl.common.utils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gfl.common.entity.CoOrderDetail;
import com.gfl.common.entity.PrintOrderVo;
import com.gfl.common.entity.ResultMsg;
import com.gfl.common.enums.FeiEPrintEnums;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 飞鹅云打印工具类
 * @author ljl
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class FeieCloundPrintUtils {

	public static final String URL = "http://api.feieyun.cn/Api/Open/";//不需要修改
	
	public static final String USER = "caicheng1313@ccw163.com";//*必填*：账号名
	
	public static final String UKEY = "ShJm3ZHJ6IvWwGvA";//*必填*: 注册账号后生成的UKEY
	
	public static final String SN = "817508871";//*必填*：打印机编号，必须要在管理后台里添加打印机之后，才能调用API
	
	public static final Logger logger = LoggerFactory.getLogger(FeieCloundPrintUtils.class);
	
	/**
	 * 订单格式化
	 * @param orderVos
	 * @return
	 * @throws Exception
	 */
	public static boolean printOrder(PrintOrderVo orderVos, String sn) throws Exception {
		logger.info("---------------------------");
		try {
			if(orderVos == null || "".equals(orderVos)){
				return false;
			}
			if(sn == null || "".equals(sn)){
				sn = SN;
			}
			Integer isFrist = orderVos.getIsFrist();
			List<CoOrderDetail> detailVos = orderVos.getDetailVos();
			//设置打印格式和内容
			String content = "";
			int lengt = orderVos.getMobile().length();
			if(orderVos.getFlowNum() == null){
				orderVos.setFlowNum("123");
			}
			if(orderVos.getTotalSeller() == null){
				orderVos.setTotalSeller("1");
			}
			if(orderVos.getNo() == null){
				orderVos.setNo("1");
			}
			content += "<CB>#"+orderVos.getFlowNum()+"-"+orderVos.getTotalSeller()+"-"+orderVos.getNo()+"菜城</CB><BR>";
			content += "<C>"+orderVos.getShopName()+"</C><BR>";
			content += "订单编号:"+orderVos.getCoOrderId()+"<BR>";
			content += "下单时间:"+orderVos.getSubmitTime()+"<BR>";
			content += "********************************<BR>";
			content += "<B>"+orderVos.getAddress()+"</B><BR>";
			content +="<B>"+orderVos.getMobile().substring(0, 3)+"****"+orderVos.getMobile().substring(lengt - 4, lengt)+"</B><BR>";
			
			if(1 == isFrist){
				content +="<L><BOLD>"+orderVos.getUserName().substring(0, 1)+"**【首单用户】</BOLD></L><BR>";
			} else {
				content +="<L><BOLD>"+orderVos.getUserName().substring(0, 1)+"**</BOLD><BR>";
			}
			if(orderVos.getRemark() == null || "".equals(orderVos.getRemark())){
				content += "<L>订单备注:无</L><BR>";
			} else {
				content += "<L>订单备注:"+orderVos.getRemark()+"</L><BR>";
			}
			content += "--------------------------------<BR>";
			//设置打印内容
			for (int j = 0; j < detailVos.size(); j++) {
				String name = detailVos.get(j).getProductName();
				String price = (detailVos.get(j).getPrice()/100) + "";
				String num = detailVos.get(j).getUnit().toString();
				String property = detailVos.get(j).getNames();
				String attribute = detailVos.get(j).getProductProperty();
				if(attribute == null ){
					attribute = "      ";
				}
				if(detailVos.get(j).getStatus() == 2){
					content += "<L>"+name+"(缺货)</L><BR>";
				} else {
					content += "<L>"+name+"</L><BR>";
				}
				String kw1 = "";
				String kw2 = "";
				String kw3 = "";
				String kw4 = "";
				int count = checkHasChinese(property);
				int count1 = checkHasChinese(attribute);
				for (int i = 0; i < 8 - property.length()-count; i++) {
					kw1 +=" ";
				}
				for (int i = 0; i < 4 - num.length()-1; i++) {
					kw2 +=" ";
				}
				for (int i = 0; i < 14 - attribute.length()- count1; i++) {
					kw3 +=" ";
				}
				for (int i = 0; i < 6 - price.length(); i++) {
					kw4 +=" ";
				}
				content += "<L>"+property+kw1;
				content += kw2+"*"+num;
				content += kw3+attribute;
				content += kw4+price+"</L><BR>";
				content += "  <BR>";
			}
			content += "--------------------------------<BR>";
			content += "<RIGHT>合计：￥"+orderVos.getAmount()+"</RIGHT><BR>";
			content += "************其他说明************<BR>";
			if(orderVos.getMergeCost() != null || !"".equals(orderVos.getMergeCost())){
				content += "本次配送费合计               "+ (orderVos.getMergeCost()) +"<BR>";
				content += "********************************<BR>";
			} 
			content += "缺货备注:商品缺货时其他商品继续配送<BR>";
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("expressId", orderVos.getFlowId());
			map.put("shopId", orderVos.getShopId());
			map.put("flowId", orderVos.getFlowNum()+"-"+orderVos.getTotalSeller()+"-"+orderVos.getNo());
			content += "<QR>"+JsonUtils.toJson(map)+"</QR>";
			content += "  <BR>";
			content += "<C>菜城,让生活更简单！</C>";
			content += "  <BR>";
			content += "<CB>#"+orderVos.getFlowNum()+"-"+orderVos.getTotalSeller()+"-"+orderVos.getNo()+"</CB><BR>";
			feiEPrint(content, FeiEPrintEnums.Type.feiE_print.getCode(), sn);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("" + e.getMessage());
			logger.info("=========================");
			throw e;
		}
		
		return true;
	}
	
	public static boolean addPrinter(String printerContent) throws Exception {
		String result = feiEPrint(printerContent, FeiEPrintEnums.Type.feiE_add.getCode(), "");
		HashMap<String, Object> map = JsonUtils.fromJson(result, HashMap.class);
		System.out.println("-----------------:"+((Map)map.get("data")).get("ok"));
		if(((Map)map.get("data")).get("ok") != null){
			return true;
		}
		return false;
	}
	
	/**
	 * 添加打印机
	 * @param printerContent
	 * @return
	 * @throws Exception
	 */
	public static ResultMsg<String> addPrinter1(String printerContent) throws Exception {
		return CRUDPrinter(printerContent,FeiEPrintEnums.Type.feiE_add.getCode(),null);
	}
	/**
	 * 修改打印机
	 * @param printerContent
	 * @return
	 * @throws Exception
	 */
	public static ResultMsg<String> deletePrinter(String printerContent) throws Exception {
		return CRUDPrinter(printerContent,FeiEPrintEnums.Type.feiE_delete.getCode(),null);
	}
	
	public static ResultMsg<String> testPrinter(String sn) throws Exception {
		StringBuilder printerContent = new StringBuilder();
		printerContent.append("<C>打印机正常工作</C>")
			.append("<BR>")
			.append("祝老板生意兴隆!");
		String result = feiEPrint(printerContent.toString(), FeiEPrintEnums.Type.feiE_print.getCode(), sn);
		HashMap<String, String> map = JsonUtils.fromJson(result, HashMap.class);
		String msg = map.get("msg");
		if(msg.equals("ok")){
			return ResultMsg.ok("打印机正常工作");
		}else {
			return ResultMsg.error("打印机有误");
		}
	}
	
	private static ResultMsg<String> CRUDPrinter(String printerContent,int type,String sn) throws Exception {
		String result = feiEPrint(printerContent, type, sn);
		HashMap<String, Object> map = JsonUtils.fromJson(result, HashMap.class);
		List<String> ok = ((List<String>) ((Map) map.get("data")).get("ok"));
		if (ok.size() > 0) {
			return ResultMsg.ok();
		} 
		List<String> no = ((List<String>) ((Map) map.get("data")).get("no"));
		return ResultMsg.error(no.get(0));
	}
	/**
	 * 打印订单
	 * @param content 订单内容
	 * @return
	 * @throws Exception
	 */
	private static String feiEPrint(String content,int type,String sn) throws Exception {
	   //通过POST请求，发送打印信息到服务器
		RequestConfig requestConfig = RequestConfig.custom()  
	            .setSocketTimeout(30000)//读取超时  
	            .setConnectTimeout(30000)//连接超时
	            .build();
		
		CloseableHttpClient httpClient = HttpClients.custom()
				 .setDefaultRequestConfig(requestConfig)
				 .build();	
		
	    HttpPost post = new HttpPost(URL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		String STIME = String.valueOf(System.currentTimeMillis()/1000);
		nvps.add(new BasicNameValuePair("user",USER));
		nvps.add(new BasicNameValuePair("stime",STIME));
		nvps.add(new BasicNameValuePair("sig",signature(USER,UKEY,STIME)));
		if(FeiEPrintEnums.Type.feiE_print.getCode() == type){
			nvps.add(new BasicNameValuePair("apiname","Open_printMsg"));//固定值,不需要修改
			nvps.add(new BasicNameValuePair("sn",sn));
			nvps.add(new BasicNameValuePair("content",content));
			nvps.add(new BasicNameValuePair("times","1"));//打印联数
		}
		if(FeiEPrintEnums.Type.feiE_add.getCode() == type){
			nvps.add(new BasicNameValuePair("apiname","Open_printerAddlist"));//固定值,不需要修改
			nvps.add(new BasicNameValuePair("printerContent",content));
		}
		if(FeiEPrintEnums.Type.feiE_delete.getCode() == type){
			nvps.add(new BasicNameValuePair("apiname","Open_printerDelList"));//固定值,不需要修改
			nvps.add(new BasicNameValuePair("snlist",content));
		}
		CloseableHttpResponse response = null;
		String result = null;
        try
        {
    	   post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
    	   response = httpClient.execute(post);
       	   int statecode = response.getStatusLine().getStatusCode();
       	   if(statecode == 200){
	        	HttpEntity httpentity = response.getEntity(); 
	            if (httpentity != null){
	            	//服务器返回
	            	result = EntityUtils.toString(httpentity);
	            }
           }
       }
       catch (Exception e)
       {
    	   e.printStackTrace();
    	   throw e;
       }
       finally{
    	   try {
    		   if(response!=null){
    			   response.close();
    		   }
    	   } catch (IOException e) {
    		   e.printStackTrace();
    		   throw e;
    	   }
    	   try {
    		   post.abort();
    	   } catch (Exception e) {
    		   e.printStackTrace();
    		   throw e;
    	   }
    	   try {
    		   httpClient.close();
    	   } catch (IOException e) {
    		   e.printStackTrace();
    		   throw e;
    	   }
       }
        System.out.println(result);
       return result;
	}
	
	//生成签名字符串
	private static String signature(String USER,String UKEY,String STIME){
		String s = DigestUtils.sha1Hex(USER+UKEY+STIME);
		return s;
	}
	
	
	/**
	 * 检查字符串中有几个中文
	 * @param str 字符串
	 * @return 中文个数
	 */
	public static int checkHasChinese(String str){
		if(str == null){
			return 0;
		}
		String regex = "[\u4e00-\u9fff]";        
		int count = (" " + str + " ").split (regex).length - 1;
		return count;
	}
}
