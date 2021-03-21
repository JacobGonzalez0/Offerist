<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Post - New Post" />
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
                    <form action="/post/add" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="title">Title</label>
                            <input id="title" name="title" class="form-control" type="text">
                        </div>
                        <div class="form-group">
                            <label for="description">Description</label>
                            <input id="description" name="description" class="form-control" type="text">
                        </div>
                        <div class="form-group">
                            <label for="price">Price</label>
                            <input id="price" name="price" class="form-control" type="text">
                        </div>
                        <div class="form-group">
                            <label for="confirm">Image</label>
                            <input type="file" id="image" name="image">
                        </div>
                        <input type="submit" class="btn btn-primary btn-block">
                    </form>
                </div>
                

            </div>
        
        </div>
    </c:if>

    </body>
</html>
