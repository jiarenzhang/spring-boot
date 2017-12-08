package com.gfl.common.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 订单商品详情
 * </p>
 *
 * @author Generator
 * @since 2017-10-26
 */
@TableName("co_order_detail")
public class CoOrderDetail extends SuperEntity<CoOrderDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主建
     */
	@TableId(value="co_order_detail_id", type= IdType.ID_WORKER)
	private Long coOrderDetailId;
    /**
     * 订单ID
     */
	@TableField("co_order_id")
	private Long coOrderId;
    /**
     * 档口ID
     */
	@TableField("ms_shop_id")
	private String msShopId;
    /**
     * 产品ID(SKUID)
     */
	@TableField("sp_item_id")
	private Long spItemId;
    /**
     * 价格
     */
	private Long price;
    /**
     * 购买数量
     */
	private Integer unit;
    /**
     * 金额小计
     */
	private Long amount;
    /**
     * 实际支付金额
     */
	@TableField("real_pay_amount")
	private Long realPayAmount;
    /**
     * 实际支付费用说明
     */
	@TableField("real_pay_amount_desc")
	private String realPayAmountDesc;
    /**
     * 状态(1默认值正常，2、商家标记缺货、3商户取消订单、4差评平台退款、5、货损用户拒收退款(货损)、6、超时退款)
     */
	private Integer status;
    /**
     * 标注时间
     */
	@TableField("remark_at")
	private Date remarkAt;
    /**
     * 是否退款
     */
	@TableField("is_refunded")
	private Integer isRefunded;
    /**
     * 重量大小
     */
	private BigDecimal weight;
    /**
     * 属性组名称（规格名称）
     */
	private String names;
    /**
     * 商品名称
     */
	@TableField("product_name")
	private String productName;
    /**
     * 商品属性
     */
	@TableField("product_property")
	private String productProperty;
    /**
     * 促销用语
     */
	private String title;
    /**
     * 说明(取消订单说明原因)
     */
	private String memo;
    /**
     * 标志
     */
	private String flag;
    /**
     * 类型
     */
	private Integer types;
    /**
     * 创建人
     */
	private String creator;
    /**
     * 创建时间
     */
	@TableField("created_at")
	private Date createdAt;
    /**
     * 最后更新时间
     */
	@TableField("last_update_time")
	private Date lastUpdateTime;
    /**
     * 最后更新人id
     */
	@TableField("last_updator_id")
	private String lastUpdatorId;
    /**
     * 最后更新人名称
     */
	@TableField("last_updator_name")
	private String lastUpdatorName;
    /**
     * 是否已删除
     */
	@TableField("is_delete")
	private Integer isDelete;
    /**
     * 版本号
     */
	private Integer versionid;


	public Long getCoOrderDetailId() {
		return coOrderDetailId;
	}

	public void setCoOrderDetailId(Long coOrderDetailId) {
		this.coOrderDetailId = coOrderDetailId;
	}

	public Long getCoOrderId() {
		return coOrderId;
	}

	public void setCoOrderId(Long coOrderId) {
		this.coOrderId = coOrderId;
	}

	public String getMsShopId() {
		return msShopId;
	}

	public void setMsShopId(String msShopId) {
		this.msShopId = msShopId;
	}

	public Long getSpItemId() {
		return spItemId;
	}

	public void setSpItemId(Long spItemId) {
		this.spItemId = spItemId;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getRealPayAmount() {
		return realPayAmount;
	}

	public void setRealPayAmount(Long realPayAmount) {
		this.realPayAmount = realPayAmount;
	}

	public String getRealPayAmountDesc() {
		return realPayAmountDesc;
	}

	public void setRealPayAmountDesc(String realPayAmountDesc) {
		this.realPayAmountDesc = realPayAmountDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getRemarkAt() {
		return remarkAt;
	}

	public void setRemarkAt(Date remarkAt) {
		this.remarkAt = remarkAt;
	}

	public Integer getIsRefunded() {
		return isRefunded;
	}

	public void setIsRefunded(Integer isRefunded) {
		this.isRefunded = isRefunded;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductProperty() {
		return productProperty;
	}

	public void setProductProperty(String productProperty) {
		this.productProperty = productProperty;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getTypes() {
		return types;
	}

	public void setTypes(Integer types) {
		this.types = types;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdatorId() {
		return lastUpdatorId;
	}

	public void setLastUpdatorId(String lastUpdatorId) {
		this.lastUpdatorId = lastUpdatorId;
	}

	public String getLastUpdatorName() {
		return lastUpdatorName;
	}

	public void setLastUpdatorName(String lastUpdatorName) {
		this.lastUpdatorName = lastUpdatorName;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getVersionid() {
		return versionid;
	}

	public void setVersionid(Integer versionid) {
		this.versionid = versionid;
	}

	@Override
	protected Serializable pkVal() {
		return this.coOrderDetailId;
	}

	@Override
	public String toString() {
		return "CoOrderDetail{" +
			", coOrderDetailId=" + coOrderDetailId +
			", coOrderId=" + coOrderId +
			", msShopId=" + msShopId +
			", spItemId=" + spItemId +
			", price=" + price +
			", unit=" + unit +
			", amount=" + amount +
			", realPayAmount=" + realPayAmount +
			", realPayAmountDesc=" + realPayAmountDesc +
			", status=" + status +
			", remarkAt=" + remarkAt +
			", isRefunded=" + isRefunded +
			", weight=" + weight +
			", names=" + names +
			", productName=" + productName +
			", productProperty=" + productProperty +
			", title=" + title +
			", memo=" + memo +
			", flag=" + flag +
			", types=" + types +
			", creator=" + creator +
			", createdAt=" + createdAt +
			", lastUpdateTime=" + lastUpdateTime +
			", lastUpdatorId=" + lastUpdatorId +
			", lastUpdatorName=" + lastUpdatorName +
			", isDelete=" + isDelete +
			", versionid=" + versionid +
			"}";
	}
}
