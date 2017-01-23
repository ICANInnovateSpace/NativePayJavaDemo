<%--
  Created by IntelliJ IDEA.
  User: Mrzhou
  Date: 2017/1/23
  Time: 1:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>订单结果</title>
</head>
<body>
    <h2 align="center">订单查询结果</h2>
    <table align="center">
        <c:forEach  items="${orderResult}"  var="order"  >
            <tr>
                <td style="color: red; size: 20px"><c:out  value="${order.key}："  /></td>
                <td style="color: green; size: 20px"><c:out  value="${order.value}"  /></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
