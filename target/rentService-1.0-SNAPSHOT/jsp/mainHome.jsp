<%--
  Created by Yanushkevich Ivan
  Date: 10.09.2020
  Time: 23:03
  Home JSP
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <title><fmt:message key="main_page.title"/></title>
</head>

<body>
<div class="layout">
    <c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
    <div class="layout-body">
        <div class="section-welcome"
             style="background-image: url(${pageContext.request.contextPath}/images/home.jpg);">
            <div class="section-welcome__content">
                <div class="container">
                    <div class="hotel-info">
                        <div class="hotel-info__subtitle"><fmt:message key="main_page.business_card"/></div>
                        <h3 class="hotel-info__title"><fmt:message key="main_page.business_card_title"/></h3>
                        <div class="hotel-info__desc">
                            <fmt:message key="main_page.business_card_text"/>
                        </div>
                        <c:choose>
                            <c:when test="${not empty user}">
                                <c:if test="${userRole != 'ADMIN'}">
                                    <a href="RentCar?command=passing_filter_rooms"
                                       class="hotel-info__button"><fmt:message
                                            key="main_page.business_card_button"/></a>
                                </c:if>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
        <div class="promise-section">
            <div class="container">
                <div class="promise-section__subtitle"><fmt:message key="main_page.promise"/></div>
                <div class="promise-section__title"><fmt:message key="main_page.promise_to"/></div>
                <div class="promise-section__desc">

                </div>
            </div>
        </div>
        <div class="advantages-grid">
            <div class="container">
                <div class="advantages-grid__inner">
                    <div class="advantages-grid__item">
                        <div class="advantages-grid__icon">

                        </div>
                        <div class="advantages-grid__title"></div>
                        <div class="advantages-grid__details">
                                </div>
                    </div>
                    <div class="advantages-grid__item">
                        <div class="advantages-grid__icon">

                        </div>
                        <div class="advantages-grid__title"></div>
                        <div class="advantages-grid__details"></div>
                    </div>
                    <div class="advantages-grid__item">
                        <div class="advantages-grid__icon">

                        </div>
                        <div class="advantages-grid__title"></div>
                        <div class="advantages-grid__details"></div>
                    </div>
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
