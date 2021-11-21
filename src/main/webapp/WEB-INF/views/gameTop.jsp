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
		<p>0から9までの異なった3から5桁の数字を推理して当てよう</p>
		<p>場所も数字もあっていればHit、数字はあっているが場所が違う場合はBlowでカウントされます</p>
		<hr>
	</header>
	<main>
		<form:form modelAttribute="gameModel">
		<p>挑戦する桁数を選んでね</p>
		<p>
			<input type="submit" name="3" value="3桁" class="btn"/>
			<input type="submit" name="4" value="4桁" class="btn"/>
			<input type="submit" name="5" value="5桁" class="btn"/>
		</p>
		</form:form>
	</main>
</body>
</html>