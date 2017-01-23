package com.ican.nativePay;

import com.ican.lib.WechatPayConfig;
import com.ican.lib.WechatPayData;
import com.ican.lib.WechatPayException;
import com.ican.util.*;

/**
 * Created by Mrzhou on 2017/1/19.
 */
public class NativePay {

    /**
     * 扫码支付模式二(写死了，下面的参数是主观设置的。如需设置自己的信息，则自行调用同一订单和查询订单方法）
     * @param wechatPayData 产品id号
     * @return 返回用于生成支付二位码的url
     * */
    public String getPayUrl2(WechatPayData wechatPayData){
        //设置必须配置的交易信息（原生支付即扫码支付的必要信息）
        wechatPayData.addSubData("trade_type","NATIVE");
        wechatPayData.addSubData("out_trade_no", OutTradeNo.generateOutTradeNo());
        WechatPayData unifiedOrderData = null;
        try {
            unifiedOrderData = unifiedOrder(wechatPayData);
        } catch (WechatPayException e) {
            e.printStackTrace();
        }
        //返回成功状态吗时返回url
        if ("SUCCESS".equals(unifiedOrderData.getDataByKey("return_code")) && "SUCCESS".equals(unifiedOrderData.getDataByKey("result_code"))){
            return (String) unifiedOrderData.getDataByKey("code_url");
        }
        return null;
    }

    /**
     * 统一下单
     * @param data 订单信息
     * @return 返回交易订单信息
     * */
    public WechatPayData unifiedOrder(WechatPayData data) throws WechatPayException {
        //统一下单接口url
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        //判断是否缺少必要参数，缺少则抛异常，免得发到服务器端
        if (!data.isExist("body")){
            throw new WechatPayException("缺乏统一下单接口必填参数body！");
        }else if (!data.isExist("out_trade_no")){
            throw new WechatPayException("缺乏统一下单接口必填参数out_trade_no！");
        }else if (!data.isExist("total_fee")){
            throw new WechatPayException("缺乏统一下单接口必填参数total_fee！");
        }else if (!data.isExist("trade_type")){
            throw new WechatPayException("缺乏统一下单接口必填参数trade_type！");
        }
        //判断是否缺乏关联参数
        if (data.getDataByKey("trade_type").equals("NATIVE") && !data.isExist("product_id")){
            throw new WechatPayException("缺乏NATIVE原生支付统一下单接口必填参数product_id！");
        }else if (data.getDataByKey("trade_type").equals("JSAPI") && !data.isExist("openid")){
            throw new WechatPayException("缺乏JSAPI支付统一下单接口必填参数openid！");
        }
        //异步通知url未设置，则使用配置文件的url
        if (!data.isExist("notify_url")){
            data.addSubData("notify_url",WechatPayConfig.NOTIFY_URL);
        }
        //设置公众号账号id、商户号、终端ip、随机字串
        data.addSubData("appid", WechatPayConfig.APPID);
        data.addSubData("mch_id",WechatPayConfig.MCHID);
        data.addSubData("spbill_create_ip",WechatPayConfig.IP);
        data.addSubData("nonce_str", RandomStringGenerator.getRandomStringByLength(32));
        //生成签名
        data.addSubData("sign", Signature.getSign(data.getData()));
        String xml = DataUtil.dataToXml(data);
        String result = HttpService.post(url, xml);
        return DataUtil.xmlToData(result);
    }

    /**
     * 订单查询
     * @param data 订单信息
     * @return 返回交易结果信息
     * */
    public WechatPayData queryOrder(WechatPayData data) throws WechatPayException {
        String url = "https://api.mch.weixin.qq.com/pay/orderquery";
        //微信订单号和商户订单号为空
        if (!data.isExist("transaction_id") && !data.isExist("out_trade_no")){
            throw new WechatPayException("订单查询接口中，out_trade_no、transaction_id至少填一个！");
        }
        //添加查询订单必填参数
        data.addSubData("appid", WechatPayConfig.APPID);
        data.addSubData("mch_id",WechatPayConfig.MCHID);
        data.addSubData("nonce_str", RandomStringGenerator.getRandomStringByLength(32));
        //生成签名
        data.addSubData("sign", Signature.getSign(data.getData()));
        String xml = DataUtil.dataToXml(data);
        String result = HttpService.post(url, xml);
        return DataUtil.xmlToData(result);
    }
}
