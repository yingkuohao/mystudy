package com.mylearn.domain;

/**
 * 服务端的返回码
 */
public enum ResultCode {

    success("0", "success"),
    /**
     * 网络异常
     */
    networkException("-2", "network exception"),
    /**
     * 系统内部错误
     */
    exception("-1", "system error"),
    /**
     * 输入参数错误
     */
    parameterError("1", "error"),
    /**
     * 未知ID
     */
    unknow("2", "unknow function id"),

    /**
     * 需要登录
     */
    requiredLogin("3", "not login"),
    /**
     * 非法登录
     */
    notValidateLogin("4", "not ValidateLogin"),
    /**
     * 电子书不能购买
     */
    mediabookError("5", "ebook can't buy"),
    /**
     * token错误
     */
    tokenError("6", "tokenError"),
    /**
     * 加密失败
     */
    desFail("7", "des content error!"),
    /**
     * sessionKey:DES加密
     */
    sessionKeyRequired("8", "sessionKey key not exist!"),
    /**
     * 需要key
     */
    envelopeKeyRequired("9", "envelope key not exist!"),
    /**
     * 指纹错误
     */
    envelopeKey("10", "envelope key error!"),
    /**
     * 订单支付，订单不存在或不是待支付状态
     */
    orderListPayError("11", "order is null or stat error"),

    /**
     * 装机需要注册
     */
    register("20", "register machine"),
    /**
     * 输入参数错误
     */
    securityCheckFailure("30", "security check failure"),
    /**
     * 需要验证码
     */
    requiredValidateCode("40", "register validate code"),
    /**
     * 两次密码不一致
     */
    samepwd("50", "password not same"),
    /**
     * 搜索引擎查询失败
     */
    SEARCH_FAILURE("51", "search failure"),
    /**
     * 登录、注册失败
     */
    loginFaild("52", "login or register faild"),

    /**
     * 评论添加失败
     */
    commentSaveFailed("60", "comment save failed"),

    /**
     * 添加关注失败
     */
    attentionFailed("70", "attention save failed"),

    /**
     * 图书找不到，不存在或者已下柜
     */
    bookNotFound("80", "bookNotFound"),

    /**
     * 登录、注册失败
     */
    yinLianPayError("111", "yinlian pay error"),

    /**
     * 合作者,不存在或者处于无效状态
     */
    partnerUnavailable("130", "mall partner is not exist or unavailable"),

    /**
     * 合作者,已经存在,插入失败
     */
    partnerInsertError("131", "mall partner is exist"),

    /**
     * 不可以赠送
     */
    cannotsentError("141", "can not sent"),


    /**
     * 支付密码错误
     */
    securityPayPasswordWrong("60", "security Pay Passsword wrong!"),

    /**
     * 虚拟支付冻结了
     */
    virtualPayFrozen("70", "virtual pay frozen"),

    /**
     * 支付密码为空
     */
    securityPayPasswordIsNull("110", "security Pay Passsword is null!"),


    /**
     * 错误的订单类类型。
     */
    wrongOrderType("161", "order type is wrong");

    /**
     * 返回码。
     * 0为正常
     */
    public String code;

    /**
     * 附加信息
     */
    public String message;

    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return code + ":" + message;
    }


}
