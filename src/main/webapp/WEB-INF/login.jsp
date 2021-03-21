<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Login" />
    </jsp:include>
    <body>

    <jsp:include page="/WEB-INF/partials/navbar.jsp">
        <jsp:param name="userType" value="${userType}" />
    </jsp:include>

    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            <c:out value="${error}"/>
            </div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="alert alert-success" role="alert">
            <c:out value="${message}"/>
        </div>
    </c:if>

    <c:if test="${empty error}">

        <div class="container"> 

            <div class="row">

                <div class="d-flex justify-content-center">
                    <form action="/login" method="post">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input id="username" name="username" class="form-control" type="text">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input id="password" name="password" class="form-control" type="password">
                        </div>
                        <input type="submit" class="btn btn-primary btn-block">
                    </form>
                </div>
                

            </div>
        
        </div>
    </c:if>

    </body>
</html>
