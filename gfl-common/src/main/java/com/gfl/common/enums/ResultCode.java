package com.gfl.common.enums;

/**
 * 业务编码定义
 * 定义分成两层:
 * 1、可以显示给前端用户：code > 0
 * 2、不可显示给前端用户：code = -1(调试过程错误信息、用于内部调试使用)
 */
public class ResultCode extends ExtensibleEnum {

    public final static ResultCode OK = new ResultCode(200, "正常");

    /** 服务内部错误，不可直接显示给前端面 **/
    public final static ResultCode ERROR = new ResultCode(-1, "业务方提供错误原因");

    /** NOT_FOUND_EXP异常原因一般不会存在，只会在URL写错的时候存在 **/
    public final static ResultCode NOT_FOUND_EXP = new ResultCode(404, "服务不存在");

    /**
     * 不可预知的异常原因
     */
    public final static ResultCode EXCEPTION = new ResultCode(1020, "网络错误");

    /**
     * auth过程错误信息
     * @param code
     */
    public final static ResultCode NO_PREMISSION = new ResultCode(9000, "无操作权限");
    public final static ResultCode NOT_LOGIN = new ResultCode(9010, "用户未登录");
    public final static ResultCode NO_PERSENT = new ResultCode(9020, "用户不存在");
    public final static ResultCode FROZEN_USER = new ResultCode(9030, "用户被冻结");
    public final static ResultCode ERROR_ACCOUNT = new ResultCode(9040, "用户名或密码错误");
    public final static ResultCode DUPLICATE_PHONE = new ResultCode(9050, "手机号重复");
    public final static ResultCode DUPLICATE_EMAIL = new ResultCode(9060, "邮箱号重复");
    public final static ResultCode DUPLICATE_USERID = new ResultCode(9070, "用户ID重复");
    public final static ResultCode DUPLICATE_USERNAME = new ResultCode(9080, "用户名重复");
    public final static ResultCode DUPLICATE_MACHINE = new ResultCode(9090, "在其他设备上登陆");
    public final static ResultCode ERROR_VALID_CODE = new ResultCode(9999, "验证码错误");
    
    /**
     * pay支付过程错误信息
     * @param code
     */
	public final static ResultCode ERROR_PAYPWD_CODE = new ResultCode(8000, "支付密码有误");
	public final static ResultCode BALANCE_DEFICIENCY = new ResultCode(8010, "余额不足");
	
	
	/**
	 * 未设置打印机错误信息
	 */
	public final static ResultCode NO_PRINTER = new ResultCode(7010, "未设置打印机");
	
	
    //Runtime
    private ResultCode(int code) {
        super(code);
    }

    private ResultCode(int code, String name) {
        super(code, name);
    }
}
