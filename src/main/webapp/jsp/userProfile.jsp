<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="en"/></c:when>
</c:choose>

<fmt:setBundle basename="pagecontent.language"/>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Courgette&family=Lato:wght@300;400;500;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media.css">
    <title><fmt:message key="user_profile.title"/></title>
</head>

<body>
<div class="layout">
    <c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
    <div class="layout-body"
         style="background-image: url(${pageContext.request.contextPath}/images/notification.jpg); background-size: cover;">
        <div class="account-section">
            <div class="container">
                <div class="account-section__head">
                    <h2 class="account-section__title"><fmt:message key="user_profile.subtitle"/></h2>
                    <div class="account-section__info">
                        <span class="account-section__login"><fmt:message key="user_profile.login"/>: ${login}</span>
                        <span class="account-section__balance"><fmt:message key="user_profile.balance"/>: ${String.format('%.2f', balance)}$</span>
                    </div>
                </div>
                <c:if test="${not empty paymentErrorMessage}">
                    <div class="account-error">
                            ${paymentErrorMessage}
                        <a href="RentCar?command=passing_payment_card"><fmt:message key="user_profile.button_deposit"/></a>
                    </div>
                </c:if>
                <div class="default-table-wrapper">
                    <table class="default-table">
                        <c:choose>
                            <c:when test="${empty personalOrders || personalOrders.size() == 0}">
                                <tr>
                                    <td><fmt:message key="user_profile.not_found_message"/></td>
                                </tr>
                            </c:when>
                            <c:when test="${not empty personalOrders}">
                                <tr>
                                    <th><fmt:message key="user_profile.order_number"/> №</th>
                                    <th><fmt:message key="user_profile.car_mark"/> </th>
                                    <th><fmt:message key="user_profile.car_model"/> </th>
                                    <th><fmt:message key="user_profile.date_from"/></th>
                                    <th><fmt:message key="user_profile.date_to"/></th>
                                    <th><fmt:message key="user_profile.price"/></th>
                                    <th><fmt:message key="user_profile.status"/></th>
                                    <th class="default-table__sort">
                                        <span><fmt:message key="user_profile.sort_tag"/></span>
                                        <a href="RentCar?command=sort_orders&sortType=date_from">
                                            <fmt:message key="user_profile.date_from"/>
                                        </a>
                                    </th>
                                </tr>
                                <c:forEach var="order" items="${personalOrders}">
                                    <tr>
                                        <td>${order.getOrderId()}</td>
                                        <td>${order.getCar().getMark()}</td>
                                        <td>${order.getCar().getModel()}</td>
                                        <td>${order.getArrivalDateString()}</td>
                                        <td>${order.getDepartureDateString()}</td>
                                        <td>${order.getTotalPrice()}$</td>
                                        <td>${order.getStatus()}</td>
                                        <c:choose>
                                            <c:when test="${order.getStatus().getNameStatus() eq 'payment'}">
                                                <td class="default-table__action">
                                                    <a href="RentCar?command=payment&orderId=${order.getOrderId()}"
                                                       class="default-table__button default-table__button--green default-table__button--lg">
                                                        <fmt:message key="user_profile.button_pay"/>
                                                    </a>
                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <td> </td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>

</html>

