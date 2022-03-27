<%--
  Created by IntelliJ IDEA.
  User: 18311
  Date: 2022/3/27
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table with="100%" height="700">
  <tr>
    <td width="200" style="border-right: aquamarine 2px solid;background: rgba(255,0,0,0.1)">
      <ul>
        <li><a href="book-add2.jsp"  target="mainFram">添加图书</a></li>
        <li><a href="list.jsp" target="mainFram" >图书列表</a></li>

      </ul>
    </td>
    <td>
      <iframe name="mainFram" width="100%" height="700" frameborder="0"></iframe>
    </td>
  </tr>
</table>
</body>
</html>
