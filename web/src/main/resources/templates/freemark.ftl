<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form>
    <tr>
        <td>
            商品编号：${course.id}
        </td>
    </tr><br>
    <tr>
        <td>
            商品名称：${course.name}
        </td>
    </tr><br>
    <tr>
        <td>
            商品价格：${course.teacher}
        </td>
    </tr><br>
    <tr>
        <td>
            商品类型：${course.type}
        </td>
    </tr><br>
    <tr>
        <td>
            开课时间：${course.starttime}
        </td>
    </tr><br>
    <tr>
        <td>
            商品状态：<span if="${course.state}==1">
                已上架
            </span>
        </td>
    </tr><br>
    <tr>
        <td>
            商品图片：
            <img src="${course.img}" width="50px" height="50px">
        </td>
    </tr><br>
    <tr>
        <td>
            <a href="http://localhost:8081/buy.html#?id=${course.id}">购买</a>
        </td>
    </tr>
</form>
</body>
</html>