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
    <title><fmt:message key="admin_orders.title"/></title>
</head>

<body>
<div class="layout">
    <c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
    <div class="layout-body"
         style="background-image: url(${pageContext.request.contextPath}/images/signUp.jpg); background-repeat: repeat;
                 background-size: cover; background-attachment: fixed;">
        <div class="admin-section">
            <div class="container">
                <h2 class="admin-section__title"><fmt:message key="admin_orders.active_orders"/></h2>
                <div class="default-table-wrapper">
                    <table class="default-table">
                        <c:choose>
                            <c:when test="${empty orders || orders.size() == 0}">
                                <tr>
                                    <td><fmt:message key="admin_orders.not_found_message"/></td>
                                </tr>
                            </c:when>
                            <c:when test="${not empty orders}">
                                <tr>
                                    <th><fmt:message key="admin_orders.login"/></th>
                                    <th><fmt:message key="admin_orders.car_id"/></th>
                                    <th><fmt:message key="admin_orders.date_from"/></th>
                                    <th><fmt:message key="admin_orders.date_to"/></th>
                                    <th><fmt:message key="admin_orders.status"/></th>
                                    <c:choose>
                                        <c:when test="${showCommand}">
                                            <th class="default-table__sort">
                                        <span>
                                            <a href="RentCar?command=passing_admin_orders">
                                                <fmt:message key="admin_orders.show_active_orders"/>
                                            </a>
                                        </span>
                                            </th>
                                        </c:when>
                                        <c:otherwise>
                                            <th class="default-table__sort">
                                        <span>
                                            <a href="RentCar?command=show_all_orders">
                                                <fmt:message key="admin_orders.show_all_orders"/>
                                            </a>
                                        </span>
                                            </th>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                                <c:forEach var="order" items="${orders}">
                                    <tr>
                                        <td>${order.getUser().getLogin()}</td>
                                        <td>${order.getCar().getCarId()}</td>
                                        <td>${order.getArrivalDateString()}</td>
                                        <td>${order.getDepartureDateString()}</td>
                                        <td>${order.getStatus()}</td>
                                        <c:choose>
                                            <c:when test="${order.getStatus() eq 'WAITING'}">
                                                <td class="default-table__action">
                                                    <a href="RentCar?command=approve_order&orderId=${order.getOrderId()}"
                                                       class="default-table__button default-table__button--green">
                                                        <fmt:message key="admin_orders.approve_button"/>
                                                    </a>
                                                    <a href="RentCar?command=reject_order&orderId=${order.getOrderId()}"
                                                       class="default-table__button default-table__button--red">
                                                        <fmt:message key="admin_orders.reject_button"/>
                                                    </a>
                                                </td>
                                            </c:when>
                                            <c:when test="${order.getStatus() eq 'REJECTED'}">
                                                <td class="default-table__action"></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="default-table__action">
                                                    <a href="RentCar?command=reject_order&orderId=${order.getOrderId()}"
                                                       class="default-table__button default-table__button--red">
                                                        <fmt:message key="admin_orders.reject_button"/>
                                                    </a>
                                                </td>
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