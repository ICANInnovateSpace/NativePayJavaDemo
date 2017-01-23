package com.ican.servlet;

import com.ican.lib.WechatPayData;
import com.ican.lib.WechatPayException;
import com.ican.nativePay.NativePay;
import com.ican.util.OutTradeNo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by Mrzhou on 2017/1/16.
 */
public class NativePayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 从微信后台获取扫码支付的url，并设置到session中用于页面生成二维码
     * @param req http请求
     * @param resp http响应
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取表单传来的产品号，总金额
        String productId = req.getParameter("productId");
        String total_fee = req.getParameter("total_fee");
        if ((productId == null || productId.equals("")) || (total_fee == null || total_fee.equals(""))){
            req.setAttribute("NULL_TEXT","产品号和金额不能为空！");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
        //创建微信支付数据类并添加支付信息
        WechatPayData wechatPayData = new WechatPayData();
        wechatPayData.addSubData("product_id",productId);
        wechatPayData.addSubData("total_fee",total_fee);
        wechatPayData.addSubData("body","just write a wechat native pay by myself");
        wechatPayData.addSubData("trade_type","NATIVE");
        wechatPayData.addSubData("out_trade_no", OutTradeNo.generateOutTradeNo());
        //创建微信原生支付类
        NativePay nativePay = new NativePay();
        //调用统一下单接口
        try {
            wechatPayData = nativePay.unifiedOrder(wechatPayData);
        } catch (WechatPayException e) {
            e.printStackTrace();
        }
        //获取url用于生成支付二维码
        String  code_url = (String) wechatPayData.getDataByKey("code_url");
        //获取订单id用于查询支付信息
        String out_trade_no = (String) wechatPayData.getDataByKey("out_trade_no");
        //获取session并把url放到session中
        HttpSession session = req.getSession();
        session.setAttribute("url",code_url);
        session.setAttribute("out_trade_no",out_trade_no);
        resp.sendRedirect("showQRCode.jsp");
    }

}
