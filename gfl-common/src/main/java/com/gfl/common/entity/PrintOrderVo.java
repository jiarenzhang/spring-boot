package com.gfl.common.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;


/**
 * 打印订单详情
 * @author ljl
 *
 */
public class PrintOrderVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 订单号
	 */
	private String coOrderId;
	/**
	 * 商户数量
	 */
	private String totalSeller;
	/**
	 * 父订单流水号
	 */
	private String flowId;
	
	/**
	 * 店铺id
	 */
	private String shopId;
	/**
	 * 配送流水号
	 */
	private String flowNum;
	/**
	 * 第几个商家
	 */
	private String no;
	/**
	 * 订单提交时间
	 */
	private String submitTime;
	/**
	 * 店铺名称
	 */
	private String shopName;
	/**
	 * 买家地址
	 */
	private String address;
	/**
	 * 买家联系方式
	 */
	private String mobile;
	/**
	 * 买家名字
	 */
	private String userName;
	/**
	 * 支付金额
	 */
	private String amount;
	/**
	 * 合并运费
	 */
	private String mergeCost;
	/**
	 * 是否为第一单
	 */
	private Integer isFrist;
	/**
	 * 订单备注
	 */
	private String remark;
	/**
	 * 缺货选择方式
	 */
	private Integer OnOut; 
	/**
	 * 订单商品详情
	 */
	private List<CoOrderDetail> detailVos;
	
	
	@ApiModelProperty(value="订单编号")
	public String getCoOrderId() {
		return coOrderId;
	}
	public void setCoOrderId(String coOrderId) {
		this.coOrderId = coOrderId;
	}
	@ApiModelProperty(value="下单时间")
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	@ApiModelProperty(value="店铺名称")
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	@ApiModelProperty(value="收货人地址")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@ApiModelProperty(value="电话")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@ApiModelProperty(value="姓名")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@ApiModelProperty(value="合计")
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@ApiModelProperty(value="合并运费")
	public String getMergeCost() {
		return mergeCost;
	}
	public void setMergeCost(String mergeCost) {
		this.mergeCost = mergeCost;
	}
	@ApiModelProperty(value="订单商品详情")
	public List<CoOrderDetail> getDetailVos() {
		return detailVos;
	}
	public void setDetailVos(List<CoOrderDetail> detailVos) {
		this.detailVos = detailVos;
	}
	@ApiModelProperty(value="是否是首单 1、是  2、不是")
	public Integer getIsFrist() {
		return isFrist;
	}
	public void setIsFrist(Integer isFrist) {
		this.isFrist = isFrist;
	}
	@ApiModelProperty(value="备注")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@ApiModelProperty(value="缺货时的处理")
	public Integer getOnOut() {
		return OnOut;
	}
	public void setOnOut(Integer onOut) {
		OnOut = onOut;
	}
	@ApiModelProperty(value="商家数量")
	public String getTotalSeller() {
		return totalSeller;
	}
	public void setTotalSeller(String totalSeller) {
		this.totalSeller = totalSeller;
	}
	@ApiModelProperty(value="配送流水号")
	public String getFlowNum() {
		return flowNum;
	}
	public void setFlowNum(String flowNum) {
		this.flowNum = flowNum;
	}
	@ApiModelProperty(value="第几个商家")
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	@ApiModelProperty(value="父订单流水号")
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	@ApiModelProperty(value="店铺id")
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	
}
