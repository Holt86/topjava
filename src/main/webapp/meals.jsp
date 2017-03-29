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
        <td>Delete</td>
    </tr>
    <c:forEach var="meal" items="${meals}" varStatus="status">
        <tr style="color: ${meal.exceed ? "red" : "green"}">
            <c:set var="format" value="${applicationScope['form']}"/>
            <td><c:out value="${meal.dateTime.format(format)}"/></td>
            <td><a href="${pageContext.servletContext.contextPath}/add?id=${meal.id}">${meal.description}</a></td>
            <td>${meal.calories}</td>
            <td><a href="${pageContext.servletContext.contextPath}/delete?id=${meal.id}">Delete</a> </td>
        </tr>
    </c:forEach>
</table>
<br>
<h3 align="center"><a href="${pageContext.servletContext.contextPath}/add.jsp">Add meal</a></h3>
</body>
</html>

