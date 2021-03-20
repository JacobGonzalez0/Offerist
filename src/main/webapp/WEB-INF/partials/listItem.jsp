<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="<c:out value='${param.url}'/>" class="nav-link text-muted">
    <li class="nav-item">
        <c:out value="${param.name}"/>
    </li>
</a>
