<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<h3><spring:message code="meals.title"/></h3>
<form method="post" action="meals">
    <dl>
        <dt><spring:message code="sort.fdate"/> </dt>
        <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
    </dl>
    <dl>
        <dt><spring:message code="sort.tdate"/> </dt>
        <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
    </dl>
    <dl>
        <dt><spring:message code="sort.ftime"/></dt>
        <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
    </dl>
    <dl>
        <dt><spring:message code="sort.ttime"/></dt>
        <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
    </dl>
    <button type="submit"><spring:message code="common.select"/></button>
</form>
