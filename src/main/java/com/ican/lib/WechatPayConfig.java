package com.ican.lib;

/**
 * Created by Mrzhou on 2017/1/19.
 */
public class WechatPayConfig {
    //===========微信公众号基本信息配置==============
    /*
    * APPID：绑定支付的APPID（必须配置）
    * MCHID：商户号（必须配置）
    * KEY：商户支付秘钥，参考开户邮件设置（必须配置）
    * APPSERCRET：公众账号secret（仅JSAPI支付的时候需要）
    * */
    public static final String APPID = "wx6e72da652d4a8dd1";
    public static final String MCHID = "1435402402";
    public static final String KEY = "wenyinbu111111111111111111111111";
    public static final String APPSECRET = "a20553da148998615b4cfe678f415feb";

    //=======【支付结果通知url】=====================================
        /* 支付结果通知回调url，用于商户接收支付结果
        */
    public static final String NOTIFY_URL = "http://paysdk.weixin.qq.com/example/ResultNotifyPage.aspx";

    //=======【商户系统后台机器IP】=====================================
        /* 此参数可手动配置也可在程序中自动获取
        */
    public static final String IP = "8.8.8.8";
}
