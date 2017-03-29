<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Meal</title>
</head>
<body>
<h2 align="center">Edit Meal</h2>
<hr color="blue">
<br>
<br>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    <table align="center">
        <tr>
            <td>Select day</td>
            <td>
                <input type="hidden" name="id" value="${meal.id}">
                <input type="date" name="date" value="${meal.dateTime.toString().split('T')[0]}">
            </td>
        </tr>
        <tr>
            <td>Select Time</td>
            <td>
                <input type="time" name="dateTime" value="${meal.dateTime.toString().split('T')[1]}">
            </td>
        </tr>
        <tr>
            <td>Meal</td>
            <td>
                <input type="text" name="description" value="${meal.description}">
            </td>
        </tr>
        <tr>
            <td>Count calories</td>
            <td>
                <input type="text" name="calories" value="${meal.calories}">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Update">
            </td>
        </tr>
    </table>
</form>
<br>
<h4 align="center"><a href="${pageContext.servletContext.contextPath}/delete?id=${meal.id}">Delete Meal</a> </h4>
<h3 align="center"><a href="${pageContext.servletContext.contextPath}/meals">All Meals</a></h3>
</body>
</html>
