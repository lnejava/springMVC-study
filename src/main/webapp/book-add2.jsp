<%--
  Created by IntelliJ IDEA.
  User: 18311
  Date: 2022/3/27
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>添加图书信息</h4>
<form action="books/add" method="post" enctype="multipart/form-data">
    <p>图书名称：<input type="text" name="bookName"> </p>
    <p>图书作者：<input type="text" name="bookAuthor"> </p>
    <p>图书价格：<input type="text" name="bookPrice"> </p>
    <p>图书封面：<input type="file" name="bookImage"> </p>
    <p><input type="submit" value="提交"></p>

</form>
</body>
</html>
