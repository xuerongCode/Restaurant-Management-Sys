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
	<style type="text/css">
	* {
		margin: 0px;
		padding: 0px
	}
	#dish_2 a{
		text-decoration:none;
		font-size:36px;
		color:#000;
	}
	#dish_2 ul { 
		list-style:none;
	} 
	#dish_2 li{
		background:url(${pageContext.request.contextPath}/frontEnd/detail/style/images/img/btn.gif);
		width:164px;
		height:47px;
		text-align:center;
		padding-top:5px;
	}
	</style>
</head>
<body style="text-align: center">

	<div class="index_all" style="text-align:center;">

		<div>
			<img src="${pageContext.request.contextPath}/frontEnd/detail/style/images/flower.gif" />
		</div>

		<div id="index_center">

			<div id="space">
				
			</div>

			<div>

				<div id="index_centerleft"></div>

				<div class="bg_middle">
					<img
						src="${pageContext.request.contextPath}/frontEnd/detail/style/images/index_menu.gif"
						border="0" usemap="#Map" />
					<map name="Map" id="Map">
						<area shape="rect" coords="164,99,354,199" href="#" />
					</map>
				</div>

				<div id="index_centerright"></div>
			</div>


			<div id="center_bottom">
				<ul style=" display:inline-table">
						<c:forEach items="${requestScope.dinnerTables}" var="item">

							<li>
								<a href="${pageContext.request.contextPath}/restMenuServlet?action=listFood&tableId=${item.id}">
									${item.tableName}&nbsp;
								</a>
							</li>
						</c:forEach>

				</ul>
			</div>
		</div>

		<div>
			<img src="${pageContext.request.contextPath}/frontEnd/detail/style/images/flower.gif" />
		</div>
	</div>
</body>
</html>