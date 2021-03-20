<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Home" />
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

        <div class="container"> 
            <div class="row">
                <div class="col-3">
                    <ul class="navbar-nav">
                        <c:forEach items="${catagories}" var="item" >
                            <jsp:include page="/WEB-INF/partials/listItem.jsp">
                                <jsp:param name="url" value="search?cat=${item.id}" />
                                <jsp:param name="name" value="${item.name}" />
                            </jsp:include>
                        </c:forEach>
                    </ul>
                </div>
    
                <div class="col-9">
                    <div class="d-flex flex-wrap">
    
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
    
                    </div>
                </div>
            </div>
            

        </div>
        

    </body>
</html>
