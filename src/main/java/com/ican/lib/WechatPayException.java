package com.ican.lib;

/**
 * Created by Mrzhou on 2017/1/21.
 */
public class WechatPayException extends Exception {

    /**
     * 微信支付异常
     * @param message 异常信息
     * */
    public WechatPayException(String message) {
        super(message);
    }
}
