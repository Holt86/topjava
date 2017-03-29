<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Meal</title>
</head>
<body>
<h2 align="center">Add Meal</h2>
<hr color="blue">
<br>
<br>
<form action="${pageContext.servletContext.contextPath}/add" method="post">
    <table align="center">
        <tr>
            <td>Select day</td>
            <td>
                <input type="date" name="date">
            </td>
        </tr>
        <tr>
            <td>Select Time</td>
            <td>
                <input type="time" name="dateTime">
            </td>
        </tr>
        <tr>
            <td>Meal</td>
            <td>
                <input type="text" name="description">
            </td>
        </tr>
        <tr>
            <td>Count calories</td>
            <td>
                <input type="text" name="calories">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Add Meal">
            </td>
        </tr>
    </table>
</form>
<br>
<br>
<h3 align="center"><a href="${pageContext.servletContext.contextPath}/meals">All Meals</a></h3>


</body>
</html>
