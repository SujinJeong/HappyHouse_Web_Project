<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 결과 출력</title>
</head>
<body>
<div>
	<c:if test="${deals.size() != 0}">
	<table class="table table-active" border="1">
		    <tbody>
		      <tr class="table-info">
		        <th>아파트이름</th>
		        <th>동</th>
		        <th>거래가</th>
		        <th>전용면적</th>
				<th>거래일시</th>
		      </tr>
	  <c:forEach var="d" items="${deals}">
		      <tr class="table-info">
		        <td>${d.aptName}</td>
		        <td>${d.dong}</td>
		        <td>${d.dealAmount}</td>
		        <td>${d.area}</td>
		        <td>${d.dealDay}</td>
		      </tr>
		    
	  	</c:forEach>
	  	</tbody>
		  </table>
	</c:if>
	  <c:if test="${deals.size() == 0}">
	  <table class="table table-active">
	    <tbody>
	      <tr class="table-info" align="center">
	        <td>해당 결과가 없습니다.</td>
	      </tr>
	    </tbody>
	  </table>
	  </c:if>
</div>
</body>
</html>