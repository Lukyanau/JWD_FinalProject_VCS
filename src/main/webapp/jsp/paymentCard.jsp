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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/log.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media.css">
    <title><fmt:message key="payment_card.title"/></title>

</head>
<body background="${pageContext.request.contextPath}/images/notification.jpg" style="background-repeat: no-repeat">
<div class="login-page">
    <c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
    <div class="form">
        <form action="RentCar" method="post" class="login-form">
            <input type="text" name="numberCard" placeholder="<fmt:message key="payment_card.card_number"/>"
                   required pattern="^[\d]{4}\s[\d]{4}\s[\d]{4}\s[\d]{4}$"
                   oninvalid="this.setCustomValidity('<fmt:message key="payment_card.incorrect_card_number"/>')"
                   onchange="this.setCustomValidity('')" value="${paymentCardData['numberCard']}"/>
            <input type="text" name="dateCard" placeholder="<fmt:message key="payment_card.date_card"/>"
                   required pattern="^\d{2}\/\d{2}$"
                   oninvalid="this.setCustomValidity('<fmt:message key="payment_card.incorrect_date_card"/>')"
                   onchange="this.setCustomValidity('')" value="${paymentCardData['dateCard']}"/>
            <input type="password" name="codeCard" placeholder="<fmt:message key="payment_card.code_card"/>"
                   required pattern="^[\d]{3}$"
                   oninvalid="this.setCustomValidity('<fmt:message key="payment_card.incorrect_code_card"/>')"
                   onchange="this.setCustomValidity('')" value="${paymentCardData['codeCard']}"/>
            <input type="text" name="transferAmount" placeholder="<fmt:message key="payment_card.sum"/>"
                   required pattern="^\d+\.?\d{1,2}$"
                   oninvalid="this.setCustomValidity('<fmt:message key="payment_card.incorrect_sum"/>')"
                   onchange="this.setCustomValidity('')" value="${paymentCardData['transferAmount']}"/>
            <input type="hidden" name="command" value="make_deposit"/>
            <span class="error" style="color:#ff340a">${paymentErrorMessage}</span>
            <button><span><fmt:message key="payment_card.button_pay"/></span></button>
        </form>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>
