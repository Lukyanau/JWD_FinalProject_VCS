<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<nav class="navbar navbar-light navbar-expand-lg bg-dark py-lg-4" id="mainNav">
    <div class="container"><a class="navbar-brand text-uppercase d-lg-none text-expanded" href="#">Brand</a><button data-toggle="collapse" data-target="#navbarResponsive" class="navbar-toggler" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div
                class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="nav navbar-nav mx-auto">
                <c:if test="${not empty sessionUser}" >
                    <c:if test="${sessionRole == 'USER'}">
                        <li class="nav-item" role="presentation"><a class="nav-link" href="controller?command=passing_home">HOME</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="controller?command=passing_about_us">ABOUT US</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="controller?command=passing_user_catalog">CATALOG</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="controller?command=log_out">LOG OUT</a></li>
                    </c:if>
                    <c:if test="${sessionRole == 'ADMIN'}">
                        <li class="nav-item" role="presentation"><a class="nav-link" href="controller?command=passing_admin_add_car">ADD CAR</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="controller?command=passing_admin_catalog">CHECK CATALOG</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="controller?command=log_out">LOG OUT</a></li>
                    </c:if>
                    <c:if test = "${empty sessionUser}">
                        <li class="nav-item" role="presentation"><a class="nav-link" href="controller?command=passing_sign_in">SIGN IN</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="controller?command=passing_registration">SIGN UP</a></li>

                    </c:if>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
