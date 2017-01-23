<%--
  Created by IntelliJ IDEA.
  User: Mrzhou
  Date: 2017/1/22
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单查询</title>
</head>
<body>
<h3 align="center">提示：微信订单号和商户订单号至少填一个</h3>
<h4 align="center">${NULL_ID}</h4>
    <table align="center">
        <form action="OrderQueryServlet" method="post">
            <tr>
                <td>微信订单号：</td>
                <td><input type="text" name="transaction_id"/></td>
            </tr>
            <tr>
                <td>商户订单号：</td>
                <td><input type="text" name="out_trade_no"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="查询"/></td>
                <td><input type="reset" value="重置"/></td>
            </tr>
        </form>
    </table>
</body>
</html>
