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

    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            <c:out value="${error}"/>
            </div>
    </c:if>

    <c:if test="${empty error}">
           
    
        <div id="carouselTop" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner" id="slider">

                <c:if test="${not empty ad.images[0]}">
                    <c:forEach items="${ad.images}" var="image">
                        <jsp:include page="/WEB-INF/partials/carouselImage.jsp" >
                            <jsp:param name="image" value='${image}'/>
                        </jsp:include>
                    </c:forEach>
                </c:if>

                <c:if test="${empty ad.images[0]}">
                    <jsp:include page="/WEB-INF/partials/carouselImage.jsp" >
                        <jsp:param name="image" value='img/no-ad-image.png'/>
                    </jsp:include>
                </c:if>
                
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselTop"  data-bs-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselTop"  data-bs-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Next</span>
            </button>
        </div>

        <div class="container"> 
        
            <div class="my-4">
                <h2>
                    <c:out value='${ad.title}' />
                </h2>
                <h4>
                    $<c:out value='${ad.price}' />
                </h4>
                <hr>
    
                <c:out value='${ad.description}' />
    
                <a href="user?id=<c:out value='${ad.userId}' />">
                    <c:out value='${ad.username}' />
                </a>
                
                Categories:  
                <c:forEach items="${ad.categories}" var="category">
                    <c:out value="${category.name}"/>
                </c:forEach>
            </div>
            
            
            
            

        </div>
    </c:if>

    </body>
</html>
