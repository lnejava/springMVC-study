<%--
  Created by IntelliJ IDEA.
  User: 18311
  Date: 2022/3/18
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>book添加</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h3>表单提交</h3>
    <!-- 注意action属性的路径不要用前缀“/”-->
<form action="date/add" method="post">
    <p>图书名称：<input name="bookName" type="text"/></p>
    <p>图书作者：<input name="bookAuthor" type="text"></p>
    <p>图书价格：<input name="bookPrice" type="text"></p>
    <p>出版时间：<input name="publishDate" type="text"> </p>
    <p><input type="submit" value="提交"></p>
</form>
<h3>超链接提交</h3>
    <a href="book/add?bookName=java"></a>

<h3>AJAX提交</h3>
<input type="button" value="ajax提交" id="btn1"/>
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" >
    $("#btn1").click(function(){
    var obj ={};
    obj.bookName = "Java";
    obj.bookAuthor = "张三";
    obj.bookPrice = 3.33;
    $.ajax({
        url: "book/update",
        type: "post",
        headers: {
            token: "12345678"
        },
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(obj),
        success:function(res)
        {
            console.log(res);
        }
    });
        });
</script>
</body>
</html>
