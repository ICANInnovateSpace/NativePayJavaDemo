package com.ican.servlet;

import com.ican.lib.WechatPayData;
import com.ican.lib.WechatPayException;
import com.ican.nativePay.NativePay;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Mrzhou on 2017/1/22.
 */
public class OrderQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 订单查询
     * @param req http请求
     * @param resp http响应
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从请求中取出需要查询订单的微信订单号和商户订单号
        String transaction_id = req.getParameter("transaction_id");
        String out_trade_no = req.getParameter("out_trade_no");
        if ((transaction_id == null || transaction_id.equals("")) && (out_trade_no == null || out_trade_no.equals(""))){
            req.setAttribute("NULL_ID","微信订单号transaction_id和商户订单号out_trade_no必须设置其中一个");
            req.getRequestDispatcher("queryOrder.jsp").forward(req,resp);
        }
        //将订单号封装到新的WechatPayData中
        WechatPayData wechatPayData = new WechatPayData();
        wechatPayData.addSubData("transaction_id",transaction_id);
        wechatPayData.addSubData("out_trade_no",out_trade_no);
        //查询订单
        NativePay nativePay = new NativePay();
        try {
            wechatPayData = nativePay.queryOrder(wechatPayData);
        } catch (WechatPayException e) {
            e.printStackTrace();
        }
        //将查询订单返回的数据放到session中
        HttpSession session = req.getSession();
        session.setAttribute("orderResult",wechatPayData.getData());
        resp.sendRedirect("orderResult.jsp");
    }
}
