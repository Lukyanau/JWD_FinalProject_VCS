<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customTags" %>

<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="en"/></c:when>
</c:choose>

<fmt:setBundle basename="pagecontent.language"/>


<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Courgette&family=Lato:wght@300;400;500;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media.css">
    <title><fmt:message key="user_catalog.title"/></title>
</head>

<body>
<div class="layout">
    <c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
    <div class="layout-body" style="background-image: url(${pageContext.request.contextPath}/images/notification.jpg);">
        <div class="booking-section">
            <div class="booking-section__inner">
                <div class="booking-form">
                    <div class="form-header">
                        <h2><fmt:message key="user_catalog.help_subtitle"/></h2>
                        <p><fmt:message key="user_catalog.help_body"/></p>
                    </div>
                    <form action="RentCar" method="post" class="form">
                        <div class="form-grid">
                            <div class="form__item">
                                <div class="form-group">
                                    <span class="form-label"><fmt:message key="user_catalog.date_from"/></span>
                                    <input class="form-control form-control--date" type="date" name="dateFrom"
                                           required>
                                </div>
                                <div class="form-group">
                                    <span class="form-label"><fmt:message key="user_catalog.date_to"/></span>
                                    <input class="form-control form-control--date" type="date" name="dateTo"
                                           required>
                                </div>
                            </div>
                            <div class="form__item">
                                <div class="form-group">
                                    <span class="form-label"><fmt:message key="user_catalog.mark"/></span>
                                    <select class="form-control" required name="mark">
                                        <option value="bmw"><fmt:message key="admin_cars.bmw"/></option>
                                        <option value="mercedes"><fmt:message key="admin_cars.mercedes"/></option>
                                        <option value="peugeot"><fmt:message key="admin_cars.peugeot"/></option>
                                        <option value="ford"><fmt:message key="admin_cars.ford"/></option>
                                        <option value="volkswagen"><fmt:message key="admin_cars.volkswagen"/></option>
                                    </select>
                                    <span class="select-arrow"></span>
                                </div>
                            </div>
                        </div>
                        <span class="error" style="color:#ff340a">${userCatalogErrorMessage}</span>
                        <div class="form-btn">
                            <input type="hidden" name="command" value="filter_cars">
                            <button type="submit" class="submit-btn"><fmt:message key="user_catalog.find_button"/></button>
                        </div>
                    </form>
                </div>
                <div class="rooms">
                    <div class="rooms__header">
                        <h2><fmt:message key="user_catalog.available_cars"/></h2>
                        <c:if test="${not empty cars}">
                            <div class="rooms__sort">
                                <span class="rooms__label"><fmt:message key="user_catalog.sort_by"/></span>
                                <a href="RentCar?command=sort_cars&sort_type=price"
                                   class="rooms__sort-link"><fmt:message key="user_catalog.price"/></a>
                                <a href="RentCar?command=sort_cars&sort_type=model"
                                   class="rooms__sort-link"><fmt:message key="user_catalog.model"/></a>
                            </div>
                        </c:if>
                    </div>
                    <table class="rooms-table">
                        <c:choose>
                            <c:when test="${empty cars && cars.size() == 0}">
                                <tr>
                                    <td><fmt:message key="user_catalog.not_found_message"/></td>
                                </tr>
                            </c:when>
                            <c:when test="${empty cars}">
                                <tr>
                                    <td><fmt:message key="user_catalog.set_parameters_message"/></td>
                                </tr>
                            </c:when>
                            <c:when test="${not empty cars}">
                                <tr>
                                    <th><fmt:message key="user_catalog.mark"/></th>
                                    <th><fmt:message key="user_catalog.model"/></th>
                                    <th><fmt:message key="user_catalog.color"/></th>
                                    <th><fmt:message key="user_catalog.price"/></th>
                                    <th></th>
                                </tr>
                                <c:forEach var="car" items="${paginationCars}">
                                    <tr>
                                        <td>${car.getMark()}</td>
                                        <td>${car.getModel()}</td>
                                        <td>${car.getColor()}</td>
                                        <td>${car.getPrice()}$</td>
                                        <td class="rooms-table__action">
                                            <a href="RentCar?command=make_order&carId=${car.getCarId()}"
                                               class="rooms-table__button">
                                                <fmt:message key="user_catalog.order_button"/>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </table>
                    <c:if test="${not empty quantityPages}">
                        <div class="rooms__footer">
                            <div class="pagination">
                                <ctg:carsPagination quantityPages="${quantityPages}" pageNumber="${pageNumber}"/>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>
