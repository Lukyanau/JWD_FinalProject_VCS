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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Courgette&family=Lato:wght@300;400;500;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media.css">
    <title><fmt:message key="notification_page.title"/></title>
</head>

<body>
<div class="layout">
    <c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
    <div class="layout-body"
         style="background-image: url(${pageContext.request.contextPath}/images/home.jpg);
                 background-repeat: repeat; background-size: auto; background-attachment: fixed;">
        <div class="notification">
            <h3 class="notification__title"><fmt:message key="notification_page.subtitle"/></h3>
            <c:choose>
                <c:when test="${ not empty addCar}">
                    <p class="notification__text">
                        <fmt:message key="notification_page.car_with_name"/> ${addCar} <fmt:message
                            key="notification_page.successfully_added"/>
                    </p>
                </c:when>
                <c:when test="${ not empty banAccount}">
                    <p class="notification__text">
                        <fmt:message key="notification_page.user_with_login"/> ${banAccount} <fmt:message
                            key="notification_page.was_successfully_banned"/>
                    </p>
                </c:when>

            </c:choose>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>