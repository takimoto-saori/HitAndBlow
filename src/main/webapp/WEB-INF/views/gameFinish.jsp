<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hit and Blow</title>
<link rel="stylesheet" href="/hitandblow/resources/styles.css">
</head>
<body>
	<header>
		<h1>Hit and Blow</h1>
		<hr>
		<h2>ルール</h2>
		<p>0から9までの異なった<c:out value="${gameModel.digit}" />桁の数字を推理して当てよう</p>
		<p>場所も数字もあっていればHit、数字はあっているが場所が違う場合はBlowでカウントされます</p>
		<hr>
	</header>
	<main>
		<form:form modelAttribute="gameModel">
		<p class="message"><c:out value="${message1}" /></p>
		<p><c:out value="${message2}" /></p>
		<p>正解は『<c:out value="${gameModel.answers}" />』でした</p>
		<div class="link"><a href="finish">トップへ戻る</a></div>
		</form:form>
		<c:if test="${!empty tableList}">
			<table id="table" border="1">
				<tr>
					<th>count</th>
					<th>number</th>
					<th>hit</th>
					<th>blow</th>
				</tr>
				<c:forEach var="table" items="${tableList}">
					<tr>
						<td><c:out value="${table.count}"></c:out></td>
						<td><c:out value="${table.number}"></c:out></td>
						<td><c:out value="${table.hit}"></c:out></td>
						<td><c:out value="${table.blow}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</main>
</body>
</html>