package com.ican.util;

import com.ican.lib.WechatPayConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Mrzhou on 2017/1/20.
 */
public class OutTradeNo {
    /**
     * 生成随机字串的订单号
     * @return 订单号（一定长度的字符串）
     * */
    public static String generateOutTradeNo(){
        //创建一个StringBuffer来保存生成的随机字符串
        StringBuffer sb = new StringBuffer();
        //获取当前系统时间并格式化
        long time=new Date().getTime();
        Date date=new Date(time);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        //格式为：以商户号为开头+当前时间+三个随机数
        sb.append(WechatPayConfig.MCHID);
        sb.append(sdf.format(date));
        Random random = new Random();
        int i = random.nextInt(999);
        sb.append(String.valueOf(i));
        return sb.toString();
    }
}
