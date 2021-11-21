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
			<p><c:out value="${gameModel.digit}" />桁の数字を入力してね</p>
			<p>
				<form:input path="number" />

				<input type="submit" name="challenge" value="Challenge" class="btn" /><input type="submit" name="giveup" value="Give up" class="btn"/>
			</p>
			<p class="errors">
				<form:errors path="number" />
				<c:out value="${errorMessage}" />
			</p>
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