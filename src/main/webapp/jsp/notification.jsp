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
         style="background-image: url(${pageContext.request.contextPath}/images/home.jpg);">
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
                <c:when test="${ not empty unbanAccount}">
                    <p class="notification__text">
                        <fmt:message key="notification_page.user_with_login"/> ${unbanAccount} <fmt:message
                            key="notification_page.was_successfully_unbanned"/>
                    </p>
                </c:when>
                <c:when test="${ not empty bannedAccount}">
                    <p class="notification__text">
                        <fmt:message key="notification_page.account_banned"/> ${bannedAccount} <fmt:message
                            key="notification_page.was_banned"/>
                    </p>
                </c:when>
                <c:when test="${ not empty activateCar}">
                    <p class="notification__text">
                        <fmt:message key="notification_page.car_with_id"/> ${activateCar} <fmt:message
                            key="notification_page.was_successfully_activated"/>
                    </p>
                </c:when>
                <c:when test="${ not empty deactivateCar}">
                    <p class="notification__text">
                        <fmt:message key="notification_page.car_with_id"/> ${deactivateCar} <fmt:message
                            key="notification_page.was_successfully_deactivated"/>
                    </p>
                </c:when>
                <c:when test="${ not empty orderCar}">
                    <p class="notification__text">
                        <fmt:message key="notification_page.car_with_model"/> ${orderCar} <fmt:message
                            key="notification_page.was_successfully_ordered"/>
                    </p>
                </c:when>
                <c:when test="${ not empty deleteCar}">
                    <p class="notification__text">
                        <fmt:message key="notification_page.car_with_id"/> ${deleteCar} <fmt:message
                            key="notification_page.was_successfully_deleted"/>
                    </p>
                </c:when>
                <c:when test="${ not empty approveOrderId}">
                    <p class="notification__text">
                        <fmt:message key="notification_page.order_with_id"/> ${approveOrderId} <fmt:message
                            key="notification_page.was_successfully_approved"/>
                    </p>
                </c:when>
                <c:when test="${ not empty rejectOrderId}">
                    <p class="notification__text">
                        <fmt:message key="notification_page.order_with_id"/> ${rejectOrderId} <fmt:message
                            key="notification_page.was_successfully_approved"/>
                    </p>
                </c:when>
                <c:when test="${not empty paymentMessage}">
                    <p class="notification__text">
                        <fmt:message key="notification_page.your_order"/> # ${paymentMessage} <fmt:message
                            key="notification_page.successfully_paid"/>
                    </p>
                </c:when>
                <c:when test="${not empty depositMessage}">
                    <p class="notification__text">
                        <fmt:message key="notification_page.successfully_credited"/> ${depositMessage}$
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