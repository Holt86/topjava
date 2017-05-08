<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h2><a href="index.html"><spring:message code="app.home"/> </a></h2>


<c:if test="${action == 'create'}">
    <h2><spring:message code="meal.create"/></h2>
</c:if>
<c:if test="${action == null}">
<h2><spring:message code="meal.edit"/> </h2>
</c:if>
<%--<h2>${action == 'create' ? 'Create meal' : 'Edit meal'}</h2>--%>
