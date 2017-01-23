<%--
  Created by IntelliJ IDEA.
  User: Mrzhou
  Date: 2017/1/20
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>扫码支付</title>
    <script type="text/javascript" src="js/qrcode.js"></script>
    <script type="text/javascript">
        window.onload = function(){
            // 二维码对象
            var qrcode;
            var value = '<%--<%=request.getSession().getAttribute("url")%>--%>${url}';
            // 创建二维码
            qrcode = new QRCode(document.getElementById("qrcode"), {
                width : 200,//设置宽高
                height : 200
            });
            qrcode.makeCode(value);
        }
    </script>
</head>
<body>
    <table align="center">
        <tr>
            <th>请扫描以下二维码：</th>
        </tr>
        <tr>
            <td>
                <div id="qrcode"></div>
            </td>
        </tr>
    </table>
</body>
</html>
