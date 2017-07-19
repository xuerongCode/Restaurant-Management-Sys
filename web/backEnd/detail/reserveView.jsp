<%--
  Created by IntelliJ IDEA.
  User: xuero
  Date: 2017/7/15
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Online Order Platform</title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
    <link href="${pageContext.request.contextPath}/backEnd/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backEnd/detail/style/css/index_1.css" />
</head>
<body>
    <!-- Data List -->
    <div id="MainArea">
        <!-- Form -->
        <form action="${pageContext.request.contextPath}/dinnerTable?action=doReserve&id=${requestScope.table.id}" method="post">

            <div class="ItemBlock_Title">
                <img width="4" height="7" border="0" src="${pageContext.request.contextPath}/backEnd/detail/style/images/item_point.gif"> Table Info&nbsp;
            </div>

            <div class="ItemBlockBorder">
                <div class="ItemBlock">
                    <div class="ItemBlock2">
                        <table cellpadding="0" cellspacing="0" class="mainForm">
                            <tr>
                                <td width="80px">ID</td>
                                <td>${requestScope.table.id}</td>
                            </tr>
                            <tr>
                                <td width="80px">Name</td>
                                <td>${requestScope.table.tableName}</td>
                            </tr>
                            <tr>
                                <td width="80px">Status</td>
                                <c:choose>
                                    <c:when test="${requestScope.table.tableStatus == 1}"><td>Idle</td></c:when>
                                    <c:when test="${requestScope.table.tableStatus == 2}"><td>Reserve</td></c:when>
                                </c:choose>
                            </tr>
                            <tr>
                                <td width="80px">Reserve Time</td>
                                <td><input type="text" name="reserveDate" class="InputStyle"/>*</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>


            <div id="InputDetailBar">
                <input type="submit" value="Reserve" class="FunctionButtonInput">
                <a href="javascript:history.go(-1);" class="FunctionButton">Return</a>
            </div>
        </form>

    </div>
</body>
</html>
