<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="<c:out value='${ad.title}' />" />
    </jsp:include>
    <body>

        <jsp:include page="/WEB-INF/partials/navbar.jsp">
            <jsp:param name="userType" value="${userType}" />
        </jsp:include>

        ${error}

        <div class="container"> 
            <img src="<c:out value='${ad.images[0]}' /> alt="">

            <h2>
                <c:out value='${ad.title}' />
            </h2>
            <h4>
                <c:out value='${ad.price}' />
            </h4>
            <hr>

            <c:out value='${ad.description}' />

            <a href="user?id=<c:out value='${ad.userId}' />">
                <c:out value='${ad.username}' />
            </a>
            
            Categories: 
            <c:forEach items="${ad.categories}" var="category">
                test
                <c:out value="${category.name}"/>
            </c:forEach>
            
            
            

        </div>
        

    </body>
</html>
