<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="${user.username} - Profile" />
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

    <c:if test="${empty error}">

        <div class="container"> 

            <div class="row">

                <div class="my-4">
                    <c:out value='${user.username}' />
                    <c:out value='${user.email}' />
                    <c:out value='${user.id}' />
                </div>

                <div class="col-9">
                    <div class="d-flex flex-wrap">
                        <c:if test="${empty self}">
                            <c:forEach items="${ads}" var="ad">
                                <jsp:include page="/WEB-INF/partials/adBox.jsp">
                                    <jsp:param name="id" value="${ad.id}" />
                                    <jsp:param name="userId" value="${ad.userId}" />
                                    <jsp:param name="price" value="${ad.price}" />
                                    <jsp:param name="title" value="${ad.title}" />
                                    <jsp:param name="description" value="${ad.description}" />
                                    <jsp:param name="image" value="${ad.images[0]}" />
                                    <jsp:param name="categories" value="${ad.categories}" />
                                </jsp:include>
                            </c:forEach>
                        </c:if>
                        <c:if test="${not empty self}">
                            <c:forEach items="${ads}" var="ad">
                                test
                                <jsp:include page="/WEB-INF/partials/userAdBox.jsp">
                                    <jsp:param name="id" value="${ad.id}" />
                                    <jsp:param name="userId" value="${ad.userId}" />
                                    <jsp:param name="price" value="${ad.price}" />
                                    <jsp:param name="title" value="${ad.title}" />
                                    <jsp:param name="description" value="${ad.description}" />
                                    <jsp:param name="image" value="${ad.images[0]}" />
                                    <jsp:param name="categories" value="${ad.categories}" />
                                </jsp:include>
                            </c:forEach>
                        </c:if>
                        
    
                    </div>
                </div>

            </div>
        
        </div>
    </c:if>

    </body>
</html>
