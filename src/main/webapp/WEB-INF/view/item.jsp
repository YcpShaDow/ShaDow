<%--
  Created by IntelliJ IDEA.
  User: ycp
  Date: 2018/8/20
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%--<%@ page isELIgnored="false" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询商品列表</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
当前用户：${username }
<c:if test="${username!=null }">
    <a href="${pageContext.request.contextPath}/logout.action">退出</a>
</c:if>
<form action="${pageContext.request.contextPath}/items/queryItems.action" method="post">

    <table width="100%" border=1>
        <tr>
            查询条件：<input name="itemsCustom.name">
            <input type="submit" value="查询"/>
        </tr>
    </table>
</form>
<form action="${pageContext.request.contextPath}/items/queryItems.action" method="post">

    <table width="100%" border=1>
        <tr>
            查询条件：<input name="pageSize">
            <input type="submit" value="跳转"/>
        </tr>
    </table>
</form>
<form action="${pageContext.request.contextPath}/items/deleteItems.action" method="post">
    <table width="100%" border=1>
        <tr>
            <input type="submit" value="批量删除"/>
        </tr>
    </table>

    商品列表：
    <div class="row">
        <div class="col-md-12">
    <table width="100%" border=1 class="table table-hover">
        <tr>
            <td>选择</td>
            <td>商品名称</td>
            <td>商品价格</td>
            <td>生产日期</td>
            <td>图片</td>
            <td>商品描述</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${pageInfo.list}" var="item">
            <tr>
                <td><input type="checkbox" name="item_id" value="${item.id}"></td>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${item.pic}</td>
                <td>${item.detail}</td>
                <td><a href="${pageContext.request.contextPath}/items/editItems.action?id=${item.id}">修改</a></td>
            </tr>
        </c:forEach>
    </table>
    <div class="row">
        <!--文字信息-->
        <div class="col-md-6">
            当前第 ${pageInfo.pageNum} 页
        </div>

        <!--点击分页-->
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="${pageContext.request.contextPath}/items/queryItems?pageNum=1&pageSize=${pageInfo.pageSize}&itemsCustom.name=${name}">首页</a></li>
                    <!--上一页-->
                    <li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <a href="${pageContext.request.contextPath}/items/queryItems?pageNum=${pageInfo.pageNum-1}&pageSize=${pageInfo.pageSize}&itemsCustom.name=${name}" aria-label="Previous">
                                <span aria-hidden="true">«</span>
                            </a>
                        </c:if>
                    </li>

                  <!--循环遍历连续显示的页面，若是当前页就高亮显示，并且没有链接-->
                    <c:forEach items="${pageInfo.navigatepageNums}" var="page_num" >
                        <c:if test="${page_num == pageInfo.pageNum}">
                            <li class="active"><a href="#">${page_num}</a></li>
                        </c:if>
                        <c:if test="${page_num != pageInfo.pageNum}">
                            <li><a href="${pageContext.request.contextPath}/items/queryItems?pageNum=${page_num}&pageSize=${pageInfo.pageSize}&itemsCustom.name=${name}">${page_num}</a></li>
                        </c:if>
                    </c:forEach>

                    <!--下一页-->
                    <li>
                        <c:if test="${pageInfo.hasNextPage}">
                            <a href="${pageContext.request.contextPath}/items/queryItems?pageNum=${pageInfo.pageNum+1}&pageSize=${pageInfo.pageSize}&itemsCustom.name=${name}"
                               aria-label="Next">
                                <span aria-hidden="true">»</span>
                            </a>
                        </c:if>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/items/queryItems?pageNum=${pageInfo.pages}&pageSize=${pageInfo.pageSize}&itemsCustom.name=${name}">尾页</a></li>
                </ul>
            </nav>
        </div>
    </div>
    </div>
    </div>
</form>
</body>
</html>