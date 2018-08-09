package com.mylearn.domain;

/**
 * ����˵ķ�����
 */
public enum ResultCode {

    success("0", "success"),
    /**
     * �����쳣
     */
    networkException("-2", "network exception"),
    /**
     * ϵͳ�ڲ�����
     */
    exception("-1", "system error"),
    /**
     * �����������
     */
    parameterError("1", "error"),
    /**
     * δ֪ID
     */
    unknow("2", "unknow function id"),

    /**
     * ��Ҫ��¼
     */
    requiredLogin("3", "not login"),
    /**
     * �Ƿ���¼
     */
    notValidateLogin("4", "not ValidateLogin"),
    /**
     * �����鲻�ܹ���
     */
    mediabookError("5", "ebook can't buy"),
    /**
     * token����
     */
    tokenError("6", "tokenError"),
    /**
     * ����ʧ��
     */
    desFail("7", "des content error!"),
    /**
     * sessionKey:DES����
     */
    sessionKeyRequired("8", "sessionKey key not exist!"),
    /**
     * ��Ҫkey
     */
    envelopeKeyRequired("9", "envelope key not exist!"),
    /**
     * ָ�ƴ���
     */
    envelopeKey("10", "envelope key error!"),
    /**
     * ����֧�������������ڻ��Ǵ�֧��״̬
     */
    orderListPayError("11", "order is null or stat error"),

    /**
     * װ����Ҫע��
     */
    register("20", "register machine"),
    /**
     * �����������
     */
    securityCheckFailure("30", "security check failure"),
    /**
     * ��Ҫ��֤��
     */
    requiredValidateCode("40", "register validate code"),
    /**
     * �������벻һ��
     */
    samepwd("50", "password not same"),
    /**
     * ���������ѯʧ��
     */
    SEARCH_FAILURE("51", "search failure"),
    /**
     * ��¼��ע��ʧ��
     */
    loginFaild("52", "login or register faild"),

    /**
     * �������ʧ��
     */
    commentSaveFailed("60", "comment save failed"),

    /**
     * ��ӹ�עʧ��
     */
    attentionFailed("70", "attention save failed"),

    /**
     * ͼ���Ҳ����������ڻ������¹�
     */
    bookNotFound("80", "bookNotFound"),

    /**
     * ��¼��ע��ʧ��
     */
    yinLianPayError("111", "yinlian pay error"),

    /**
     * ������,�����ڻ��ߴ�����Ч״̬
     */
    partnerUnavailable("130", "mall partner is not exist or unavailable"),

    /**
     * ������,�Ѿ�����,����ʧ��
     */
    partnerInsertError("131", "mall partner is exist"),

    /**
     * ����������
     */
    cannotsentError("141", "can not sent"),


    /**
     * ֧���������
     */
    securityPayPasswordWrong("60", "security Pay Passsword wrong!"),

    /**
     * ����֧��������
     */
    virtualPayFrozen("70", "virtual pay frozen"),

    /**
     * ֧������Ϊ��
     */
    securityPayPasswordIsNull("110", "security Pay Passsword is null!"),


    /**
     * ����Ķ��������͡�
     */
    wrongOrderType("161", "order type is wrong");

    /**
     * �����롣
     * 0Ϊ����
     */
    public String code;

    /**
     * ������Ϣ
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
