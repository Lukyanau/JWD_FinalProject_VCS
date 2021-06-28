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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>

    <title><fmt:message key="payment_card.title"/></title>


</head>

<body style="background-image: url(${pageContext.request.contextPath}/images/signUp.jpg);background-size: cover;">
<div class="login-page">
    <c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
    <div class="container">
        <div class="row centered-form">
            <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">
                            <fmt:message key="payment_card.title"/></h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="RentCar" method="post">

                            <div class="form-group">
                                <input type="text" name="numberCard"
                                       placeholder="<fmt:message key="payment_card.card_number"/>"
                                       required pattern="^[\d]{4}\s[\d]{4}\s[\d]{4}\s[\d]{4}$"
                                       oninvalid="this.setCustomValidity('<fmt:message
                                               key="payment_card.incorrect_card_number"/>')"
                                       onchange="this.setCustomValidity('')" value="${paymentCardData['numberCard']}"/>
                            </div>
                            <div class="form-group">
                                <input type="text" name="dateCard"
                                       placeholder="<fmt:message key="payment_card.date_card"/>"
                                       required pattern="^\d{2}\/\d{2}$"
                                       oninvalid="this.setCustomValidity('<fmt:message
                                               key="payment_card.incorrect_date_card"/>')"
                                       onchange="this.setCustomValidity('')" value="${paymentCardData['dateCard']}"/>
                            </div>
                            <div class="form-group">
                                <input type="password" name="codeCard"
                                       placeholder="<fmt:message key="payment_card.code_card"/>"
                                       required pattern="^[\d]{3}$"
                                       oninvalid="this.setCustomValidity('<fmt:message
                                               key="payment_card.incorrect_code_card"/>')"
                                       onchange="this.setCustomValidity('')" value="${paymentCardData['codeCard']}"/>
                            </div>
                            <div class="form-group">
                                <input type="text" name="transferAmount"
                                       placeholder="<fmt:message key="payment_card.sum"/>"
                                       required pattern="^\d+\.?\d{1,2}$"
                                       oninvalid="this.setCustomValidity('<fmt:message
                                               key="payment_card.incorrect_sum"/>')"
                                       onchange="this.setCustomValidity('')"
                                       value="${paymentCardData['transferAmount']}"/>
                            </div>
                            <input type="hidden" name="command" value="make_deposit"/>
                            <span class="error" style="color:#ff340a">${paymentErrorMessage}</span>
                            <button><span><fmt:message key="payment_card.button_pay"/></span></button>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>
