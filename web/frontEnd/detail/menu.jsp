<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
<title>Online Order</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/frontEnd/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/frontEnd/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/frontEnd/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontEnd/detail/style/css/index_1.css" />
	<link href="${pageContext.request.contextPath}/frontEnd/detail/style/css/index.css" rel="stylesheet" type="text/css" />
<SCRIPT type="text/javascript">

</SCRIPT>
</head>
<body style="text-align: center">
	<div id="all">
		<div id="menu">

			<div id="top">
				<ul>

					<c:forEach items="${requestScope.pb.pageData}" var="item">

						<li>
							<dl>
								<dt>
									<a href="caixiangxi.jsp">
										<img width="214px" height="145px" src="${item.img}" />
									</a>
								</dt>
								<dd class="f1">
									<a href="caixiangxi.jsp">${item.foodName}</a>
								</dd>
								<dd class="f2">
									<a href="caixiangxi.jsp">${item.price}</a>
								</dd>
							</dl>
						</li>

					</c:forEach>
					
				</ul>
			</div>
			

			<div id="foot">
				
					
					
						<span
							style="float:left; line-height:53PX; margin-left:-50px; font-weight:bold; ">
							<span style="font-weight:bold">&lt;&lt;</span>
						</span>
					
				
				<div id="btn">
					<ul>
						
							<%--<li><a--%>
								<%--href="#">1</a></li>--%>
						<%----%>
							<%--<li><a--%>
								<%--href="#">2</a></li>--%>

						<c:forEach begin="1" end="${requestScope.pb.totalPage}" var="i">
							<li><a
									href="${pageContext.request.contextPath}/restMenuServlet?action=listFood&currentPage=${i}&foodType_id=${requestScope.foodType}">${i}</a></li>
						</c:forEach>
						
					</ul>

				</div>
				
					
						<span style="float:right; line-height:53px; margin-right:10px;  ">
							<a
							href="#"
							style=" text-decoration:none;color:#000000; font-weight:bold">&gt;&gt;</a>
						</span>
					
					
				
			</div>
			
		</div>

		<div id="dish_class">
			<div id="dish_top">
				<ul>
				<li class="dish_num"></li>
					<li>
						<a href="clientOrderList.jsp">
							<img src="${pageContext.request.contextPath}/frontEnd/detail/style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>

			<div id="dish_2">
				<ul>
					<li>
						<a href="${pageContext.request.contextPath}/restMenuServlet?action=listFood">ALL</a>
					</li>
					<c:forEach items="${requestScope.foodTypes}" var="item">
						<li>
							<a href="${pageContext.request.contextPath}/restMenuServlet?action=listFood&foodType_id=${item.id}">${item.typeName}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div id="dish_3">

				<form action="${pageContext.request.contextPath}/food?method=listFood" method="post">
					<table width="166px">
						<tr>
							<td>
								<input type="text" id="dish_name" name="foodName" class="select_value" /> 
								<input type="hidden" value="selectFood" name="method">
							</td>
						</tr>
						<tr>
							<td><input type="submit" id="sub" value="" /></td>
						</tr>
						<tr>
							<%--<td>--%>
								<%--<a href="#">--%>
									<%--<img src="${pageContext.request.contextPath}/frontEnd/detail/style/images/look.gif" />--%>
								<%--</a>--%>
							<%--</td>--%>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>
