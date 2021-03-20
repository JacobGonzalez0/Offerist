<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="carousel-item active" style="overflow: hidden; height: 300px;">
    <div class="w-100" style="background: url('${param.image}') 100% 50% / cover; z-index: -3; height: 300px; filter: blur(8px);"></div>
    <img class="d-block m-auto" src='${param.image}' style="height: 300px; position: relative; transform: translate(0%, -100%);" title=""> 
</div>