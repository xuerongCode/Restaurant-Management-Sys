<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
			
				
					<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/backEnd/detail/style/images/title_arrow.gif"/> Update Food
				
				
			
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<div id="MainArea">

	<form action="${pageContext.request.contextPath}/food?action=updateFood" method="post" enctype="multipart/form-data">

		<div class="ItemBlock_Title">
        	<img width="4" height="7" border="0" src="${pageContext.request.contextPath}/backEnd/detail/style/images/item_point.gif"> Food Info&nbsp;
        </div>

        <div class="ItemBlockBorder">
            <div class="ItemBlock">
				<div class="ItemBlock2">
					<table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
							<td width="80px">Category</td>
							<td>
                            <select name="foodType_id" style="width:80px">

								<c:forEach items="${requestScope.foodType}" var="item">
									<c:choose>
										<c:when test="${item.typeName == requestScope.food['typeName']}">
											<option value="${item.id}" selected="selected">${item.typeName}</option>
										</c:when>
										<c:otherwise>
											<option value="${item.id}">${item.typeName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
			   					
                            </select>
                             *<input type="hidden" name="id" value="${requestScope.food['id']}" /></td>
						</tr>
						<tr>
							<td width="80px">Food Name</td>
							<td><input type="text" name="foodName" class="InputStyle" value="${requestScope.food["foodName"]}"/> *</td>
						</tr>
						<tr>
							<td>Price</td>
							<td><input type="text" name="price" class="InputStyle" value="${requestScope.food["price"]}"/> *</td>
						</tr>
                        <tr>
							<td>VIP Price</td>
							<td><input type="text" name="mprice" class="InputStyle" value="${requestScope.food["mprice"]}"/> *</td>
						</tr>
						
						<tr>
							<td>Description</td>
							<td><textarea name="description" class="TextareaStyle">${requestScope.food["description"]}</textarea></td>
						</tr>
						<tr>
							<td width="80px">Food Image</td>
							<td>
								
									<img style='max-width:68px;width:68px;width:expression(width>68?"68px":width "px");max-width: 68px;' 
									src="${requestScope.food['img']}">
									<input type="hidden" name="img" value="${requestScope.food['img']}">
								
								<input type="file" name="image"/> *
							</td>
						</tr>
					</table>
				</div>
            </div>
        </div>
		
		

		<div id="InputDetailBar">
            
				
					 <input type="submit" value="Update" class="FunctionButtonInput">
				
				
			
            
            <a href="javascript:history.go(-1);" class="FunctionButton">Return</a>
        </div>
	</form>
</div>
</body>
</html>
