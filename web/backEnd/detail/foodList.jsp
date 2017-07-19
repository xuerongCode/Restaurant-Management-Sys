﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	
<title>Online Order Platform</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/backEnd/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/backEnd/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/backEnd/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backEnd/detail/style/css/index_1.css" />
</head>
<body>

<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/backEnd/detail/style/images/title_arrow.gif"/> Food List
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<div id="QueryArea">
		<form action="${pageContext.request.contextPath}/food?action=searchFood" method="post">
			<input type="hidden" name="method" value="search">
			<input type="text" name="key" title="input a food name">
			<input type="submit" value="Search">
		</form>
	</div>

<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">

        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>Food ID</td>
				<td>Food Name</td>
				<td>Category</td>
				<td>Price</td>
                <td>VIP Price</td>
				<td>Action</td>
			</tr>
		</thead>	

        <tbody id="TableData">

		<c:choose>
			<c:when test="${not empty requestScope.foods}">
				<c:forEach items="${requestScope.foods}" var="item">
					<tr class="TableDetail1">
						<td>${item["id"]}&nbsp;</td>
						<td>${item["foodName"]}&nbsp;</td>
						<td>${item["typeName"]}&nbsp;</td>
						<td>${item["price"]}&nbsp;</td>
						<td>${item["mprice"]}&nbsp;</td>
						<td>
							<a href="${pageContext.request.contextPath}/food?action=updateShow&id=${item["id"]}" class="FunctionButton">Update</a>
							<a href="${pageContext.request.contextPath}/food?action=deleteFood&id=${item["id"]}" onClick="return delConfirm();"class="FunctionButton">Delete</a>
						</td>
					</tr>
				</c:forEach>

			</c:when>
			<c:otherwise>
				<tr class="TableDetail1">
					<td colspan="6">No Data Find</td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath}/food?action=addShow">ADD</a></div>
    </div> 
</div>
</body>
</html>
