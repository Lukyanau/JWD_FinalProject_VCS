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
    <title><fmt:message key="payment_card.title"/></title>
</head>

<body>
<div class="layout">
    <c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
    <div class="layout-body"
         style="background-image: url(${pageContext.request.contextPath}/images/creditCard.jpg);">
        <div class="admin-section">
            <div class="container">
                <div class="add-rooms-form">
                    <h3 class="add-rooms-form__title"><fmt:message key="payment_card.title"/></h3>
                    <form action="RentCar" method="post" class="form">
                        <div class="form-grid">
                            <div class="form__item">
                                <div class="form-group">
                                    <input type="text" name="numberCard" placeholder="<fmt:message key="payment_card.card_number"/>"
                                           required pattern="^[\d]{4}\s[\d]{4}\s[\d]{4}\s[\d]{4}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="payment_card.incorrect_card_number"/>')"
                                           onchange="this.setCustomValidity('')" value="${paymentCardData['numberCard']}"/>
                                </div>
                            </div>
                            <div class="form__item">
                                <div class="form-group">
                                    <input type="text" name="dateCard" placeholder="<fmt:message key="payment_card.date_card"/>"
                                           required pattern="^\d{2}\/\d{2}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="payment_card.incorrect_date_card"/>')"
                                           onchange="this.setCustomValidity('')" value="${paymentCardData['dateCard']}"/>
                                </div>
                            </div>
                            <div class="form__item">
                                <div class="form-group">
                                    <input type="password" name="codeCard" placeholder="<fmt:message key="payment_card.code_card"/>"
                                           required pattern="^[\d]{3}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="payment_card.incorrect_code_card"/>')"
                                           onchange="this.setCustomValidity('')" value="${paymentCardData['codeCard']}"/>
                                </div>
                            </div>
                            <div class="form__item">
                                <div class="form-group">
                                    <input type="text" name="transferAmount" placeholder="<fmt:message key="payment_card.sum"/>"
                                           required pattern="^\d+\.?\d{1,2}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="payment_card.incorrect_sum"/>')"
                                           onchange="this.setCustomValidity('')" value="${paymentCardData['transferAmount']}"/>
                                </div>
                            </div>
                            <div class="form__item">
                                <div class="form-btn">
                                    <input type="hidden" name="command" value="make_deposit"/>
                                    <span class="error" style="color:#ff340a">${paymentErrorMessage}</span>
                                    <button><span><fmt:message key="payment_card.button_pay"/></span></button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>
