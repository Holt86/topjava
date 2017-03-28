<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Meals list</title>
</head>
<body>
<h2 align="center">Meals</h2>
<hr color="blue"/>
<br>
<br>
<table border="1" align="center">
    <tr>
        <td>DateTime</td>
        <td>Description</td>
        <td>Calories</td>
    </tr>
    <c:forEach var="meal" items="${meals}" varStatus="status">
        <tr style="color: ${meal.exceed ? "red" : "green"}">
            <c:set var="format" scope="application" value="${applicationScope['form']}"/>
            <td><c:out value="${meal.dateTime.format(format)}"/></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

