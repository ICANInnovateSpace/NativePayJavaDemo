<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
<h2 align="center">=====扫码支付Demo=====</h2>
<h4 align="center">${NULL_TEXT}</h4>
    <table align="center">
        <form action="NativePayServlet" method="post">
            <tr>
                <td>产品号：</td>
                <td><input type="text" name="productId"/></td>
            </tr>
            <tr>
                <td>总金额：</td>
                <td><input type="text" name="total_fee"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="提交"/></td>
                <td><input type="reset" value="重置"/></td>
            </tr>
        </form>
    </table>
</body>
</html>
