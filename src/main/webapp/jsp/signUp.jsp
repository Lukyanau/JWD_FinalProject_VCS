<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="en"/></c:when>
</c:choose>

<fmt:setBundle basename="pagecontent.language"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <title><fmt:message key="sign_up_page.title"/></title>
</head>

<body style="background-image: url(${pageContext.request.contextPath}/images/signUp.jpg);background-size: cover;">
<div class="container">
    <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title" align="center">
                        <fmt:message key="sign_up_page.please_sign_up"/></h3>
                </div>
                <div class="panel-body">
                    <form role="form" action="RentCar" method="post">
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="name" id="name" class="form-control input-sm"
                                           placeholder=
                                    <fmt:message key="sign_up_page.name"/>>
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="surname" id="surname" class="form-control input-sm"
                                           placeholder=
                                    <fmt:message key="sign_up_page.surname"/>>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="text" name="email" id="email" class="form-control input-sm"
                                   placeholder=
                            <fmt:message key="sign_up_page.email"/>>
                        </div>
                        <div class="form-group">
                            <input type="text" name="phoneNumber" id="phoneNumber" class="form-control input-sm"
                                   placeholder="+375(xx)xxx-xx-xx">
                        </div>
                        <div class="form-group">
                            <input type="text" name="login" id="login" class="form-control input-sm"
                                   placeholder=
                            <fmt:message key="sign_up_page.login"/>>
                        </div>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password" id="password" class="form-control input-sm"
                                           placeholder=
                                    <fmt:message key="sign_up_page.password"/>>
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password_confirmation" id="password_confirmation"
                                           class="form-control input-sm" placeholder=
                                    <fmt:message key="sign_up_page.password_confirmation"/>>
                                </div>
                            </div>
                        </div>
                        <div class="someDiv">
                            <input type="hidden" name="command" value="sign_up"/>
                            <input type="submit" class="btn btn-info btn-block" value=
                            <fmt:message key="sign_up_page.sign_up"/>>
                            <p class="message">
                                <fmt:message key="sign_up_page.return_to_sign_in"/>
                                <a
                                        href="RentCar?command=passing_sign_in">
                                    <fmt:message key="sign_up_page.sign_in"/>
                                </a>
                            </p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>