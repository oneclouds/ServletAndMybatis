<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
     <base href="<%=path%>/">
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
    <style type="text/css">
        .box {
            width: 200px;
            border: 1px black solid;
            height: 200px;
        }

    </style>
</head>
<body>
<div>
    <div><input type="button" value="发送信息" id="send"></div>
    <div class="box">
        <textarea id="sendMes"></textarea>
    </div>
</div>
<div>
    <div><input type="button" value="获取信息" id="getMes"></div>
    <div class="box" id="box2">

    </div>
</div>
<script type="text/javascript">
    var id;
    $("#send").click(function () {
        var content = $("#sendMes").val();
        var value = {content: content};
        $.ajax({
            type: "post",
            url: "send",
            contentType: "application/x-www-form-urlencoded",
            data: value,
            dataType: "json",
            success: function (data) {
                id = data;
                alert(data);
            }
        });
    });

    $("#getMes").click(function () {
        if (!id) return;
        var data={id:id};
        $.ajax({
            type: "post",
            url: "getInfo",
            contentType: "application/x-www-form-urlencoded",
            data: data,
            dataType: "json",
            success: function (data) {
               var obj= JSON.stringify(data);
                $("#box2").text(obj);
            }
        });
    });

</script>
</body>
</html>
