<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="post?id=<c:out value='${param.id}'/>" class="nav-link text-muted">
    <div class="border rounded" style="max-width: 230px;">
        <img class="w-100" src="<c:out value='${param.image}'/>" alt="">
        <hr>
        <h4 class="py-2">
            <c:out value="${param.title}"/>
        </h4>
        <div class="py-2">
            <c:out value="${param.price}"/>
        </div>
        <div class="btn-group w-100" role="group" aria-label="user-controls">
            <a href="post/edit">    
                <button type="button" class="btn btn-primary">Edit</button>
            </a>
            <a href="post/edit">
                <button type="button" class="btn btn-danger">Delete</button>
            </a>
        </div>
    </div>
</a>
