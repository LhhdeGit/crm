<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2021/8/3
  Time: 4:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <title>Title</title>
</head>
<body>

    <table border= "1">
        <caption style="background-color: aqua">表格</caption>
        <thead>
        <tr>
            <th><input type="checkbox"></th>
            <th>姓名</th>
            <th>年龄</th>
            <th>班级</th>
        </tr>
        </thead>

    </table>

    <script>
        $(function () {
            $.ajax({
                url:'/TableServlet',
                type:'GET',
                data:'',
                dataType:'JSON',
                success:function (res) {
                    //console.log(res);
                    $.each(res.rows,function (index,item) {
                        //console.log(item)
                    $("table").append("<tr>"+"<td>"+"<input type='checkbox'>"+"</td>"+"<td>"+item.name+"</td>"+"<td>"+item.age+"</td>"+"<td>"+item.class+"</td>"+"</tr>")
                    })
                }
            });
        })
    </script>

</body>
</html>
